/*
 * Copyright (c) 2016. Zuercher Hochschule fuer Angewandte Wissenschaften
 *  All Rights Reserved.
 *
 *     Licensed under the Apache License, Version 2.0 (the "License"); you may
 *     not use this file except in compliance with the License. You may obtain
 *     a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 *     WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 *     License for the specific language governing permissions and limitations
 *     under the License.
 */
package ch.icclab.cyclops.rabbitmq;


import ch.icclab.cyclops.load.model.RabbitMQCredentials;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

/**
 * Author: Olesksii
 * Created: 05/04/16
 * Description: Abstract class for RabbitMQ
 */
public abstract class RabbitMQAbstract {
    final static Logger logger = LogManager.getLogger(RabbitMQAbstract.class.getName());
    protected List<ConsumerEntry> listOfConsumers;

    protected Channel channel;
    protected Connection connection;

    private RabbitMQCredentials credentials = getCredentials();

    /**
     * This class holds settings for consumer jobs
     */
    public class ConsumerEntry {
        private AbstractConsumer clazz;
        private String queue;
        private Consumer consumer;
        private String tag;

        public AbstractConsumer getClazz() {
            return clazz;
        }
        public void setClazz(AbstractConsumer clazz) {
            this.clazz = clazz;
        }
        public String getQueue() {
            return queue;
        }
        public void setQueue(String queue) {
            this.queue = queue;
        }
        public Consumer getConsumer() {
            return consumer;
        }
        public void setConsumer(Consumer consumer) {
            this.consumer = consumer;
        }
        public String getTag() {
            return tag;
        }
        public void setTag(String tag) {
            this.tag = tag;
        }
    }

    /**
     * Start consuming
     */

    public void start() {

        // we cannot do it if it's already running
        if (connection == null && channel == null) {
            logger.trace("Starting up RabbitMQ manager and added consumers");

            // first get channel
            channel = getChannel();

            // if didn't fail
            if (channel != null) {

                // iterate over saved consumers
                for (ConsumerEntry entry: listOfConsumers) {

                    // declare necessary queues
                    try {
                        channel.queueDeclare(entry.getQueue(), true, false, false, null);
                    } catch (Exception e) {
                        logger.error("Couldn't declare queue named: " + entry.getQueue() + " because: " + e.getMessage());
                    }

                    // attach consumers
                    Consumer con = entry.getClazz().handleDelivery(channel);
                    entry.setConsumer(con);

                    // start listening
                    try {
                        String tag = channel.basicConsume(entry.getQueue(), true, entry.getConsumer());
                        entry.setTag(tag);
                    } catch (Exception e) {
                        logger.error("Couldn't start consuming for queue: " + entry.getQueue() + "because of: " + e.getMessage());
                    }
                }
            }
        }
    }

    /**
     * Stop consuming
     */
    public void stop() {
        logger.trace("Shutting down RabbitMQ manager and individual consumers");

        // shut down channel
        if (channel != null) {
            for (ConsumerEntry entry: listOfConsumers) {
                try {
                    channel.basicCancel(entry.getTag());
                    channel.close();

                    channel = null;
                } catch (Exception ignored) {
                }
            }
        }

        // close connection
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (Exception ignored) {
            }
        }
    }

    /**
     * Restarting consumption
     */
    public void restart() {
        stop();
        start();
    }

    /**
     * Add consumer for RabbitMQ
     * @param queue name
     * @param clazz consumer
     */
    public void addConsumer(String queue, AbstractConsumer clazz) {

        ConsumerEntry entry = new ConsumerEntry();

        logger.trace("Adding RabbitMQ consumer for queue: " + queue);

        entry.setClazz(clazz);
        entry.setQueue(queue);

        listOfConsumers.add(entry);
    }

    public void sendMessage (String message, String queue){
        try {
        getChannel().basicPublish("", queue, null, message.getBytes() );
        } catch(IOException e){
        }

    }

    /**
     * Will return channel for RabbitMQ connection
     * @return channel reference or null
     */
    protected Channel getChannel() {
        // connect to the RabbitMQ based on settings from Load
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername(credentials.getRabbitMQUsername());
        factory.setPassword(credentials.getRabbitMQPassword());
        factory.setHost(credentials.getRabbitMQHost());
        factory.setPort(credentials.getRabbitMQPort());
        factory.setVirtualHost(credentials.getRabbitMQVirtualHost());

        Channel chan;

        try {

            logger.trace("Creating connection to RabbitMQ for host: " + credentials.getRabbitMQHost() + " and port: " + credentials.getRabbitMQPort());

            // create new connection
            connection = factory.newConnection();

            logger.trace("Creating and connecting to RabbitMQ channel");

            // create/connect to the channel
            chan = connection.createChannel();

        } catch (Exception ex) {
            logger.error("Couldn't start Rabbit MQ: " + ex.getMessage());
            connection = null;
            chan = null;
        }

        // return channel reference, or null
        return chan;
    }

    protected abstract RabbitMQCredentials getCredentials();
}

