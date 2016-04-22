/*
 * Copyright (c) 2015. Zuercher Hochschule fuer Angewandte Wissenschaften
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
package ch.icclab.cyclops.load;

import ch.icclab.cyclops.load.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Properties;

/**
 * Author: Skoviera
 * Created: 21/01/16
 * Upgraded by: Oleksii
 * Upgraded: 01/04/16
 * Description: Parent for specific environmental settings
 */
public class Settings {

    final static Logger logger = LogManager.getLogger(Settings.class.getName());

    // Object for reading and accessing configuration properties
    private Properties properties;

    // List of different settings that are being loaded from configuration file
    protected RabbitMQCredentials rabbitMQCredentials;
    protected QueueNames queueNames;
    protected RabbitMQCredentials rabbitMQClientCredentials;
    protected QueueNames queueClientNames;

    /**
     * Load settings based on provided settings
     */
    public Settings(Properties prop) {
        properties = prop;
    }

    //========== Loading configuration file settings
    /**
     * Load RabbitMQ credentials
     * @return credentials
     */
    private RabbitMQCredentials loadRabbitMQCredentials() {
        RabbitMQCredentials rabbitMQCredentials = new RabbitMQCredentials();

        rabbitMQCredentials.setRabbitMQHost(properties.getProperty("RabbitMQHost"));
        rabbitMQCredentials.setRabbitMQUsername(properties.getProperty("RabbitMQUsername"));
        rabbitMQCredentials.setRabbitMQPassword(properties.getProperty("RabbitMQPassword"));
        rabbitMQCredentials.setRabbitMQPort(Integer.parseInt(properties.getProperty("RabbitMQPort")));
        rabbitMQCredentials.setRabbitMQVirtualHost(properties.getProperty("RabbitMQVirtualHost"));

        return rabbitMQCredentials;
    }

    /**
     * Load RabbitMQClient credentials
     * @return credentials
     */
    private RabbitMQCredentials loadRabbitMQClientCredentials() {
        RabbitMQCredentials rabbitMQClientCredentials = new RabbitMQCredentials();

        rabbitMQClientCredentials.setRabbitMQHost(properties.getProperty("RabbitMQClientHost"));
        rabbitMQClientCredentials.setRabbitMQUsername(properties.getProperty("RabbitMQClientUsername"));
        rabbitMQClientCredentials.setRabbitMQPassword(properties.getProperty("RabbitMQClientPassword"));
        rabbitMQClientCredentials.setRabbitMQPort(Integer.parseInt(properties.getProperty("RabbitMQClientPort")));
        rabbitMQClientCredentials.setRabbitMQVirtualHost(properties.getProperty("RabbitMQClientVirtualHost"));

        return rabbitMQClientCredentials;
    }

    /**
     * Load Queue names from configuration file
     * @return queue names
     */
    private QueueNames loadQueueNames() {
        QueueNames queueNames = new QueueNames();

        queueNames.setDefaultQueue(properties.getProperty("DefaultQueue"));

        return queueNames;
    }

    /**
     * Load ClientQueue names from configuration file
     * @return client queue names
     */
    private QueueNames loadQueueClientNames() {
        QueueNames queueClientNames = new QueueNames();

        queueClientNames.setDefaultQueue(properties.getProperty("DefaultClientQueue"));

        return queueClientNames;
    }
    /**
     * Access loaded Rabbit MQ credentials
     * @return cached credentials
     */
    public RabbitMQCredentials getRabbitMQCredentials() {

        if (rabbitMQCredentials == null) {
            try {
                rabbitMQCredentials = loadRabbitMQCredentials();
            } catch (Exception e) {
                logger.error("Could not load RabbitMQ credentials from configuration file: " + e.getMessage());
            }
        }

        return rabbitMQCredentials;
    }

    /**
     * Access loaded Rabbit MQ  client credentials
     * @return cached credentials
     */
    public RabbitMQCredentials getRabbitMQClientCredentials() {

        if (rabbitMQClientCredentials == null) {
            try {
                rabbitMQClientCredentials = loadRabbitMQClientCredentials();
            } catch (Exception e) {
                logger.error("Could not load RabbitMQ credentials from configuration file: " + e.getMessage());
            }
        }

        return rabbitMQClientCredentials;
    }

    /**
     * Access loaded Queue names
     * @return queue names
     */
    public QueueNames getQueueNames() {
        if (queueNames == null) {
            try {
                queueNames = loadQueueNames();
            } catch (Exception e) {
                logger.error("Could not load Queue names from configuration file: " + e.getMessage());
            }
        }

        return queueNames;
    }

    /**
     * Access loaded Queue client names
     * @return queue names
     */
    public QueueNames getQueueClientNames() {
        if (queueClientNames == null) {
            try {
                queueClientNames = loadQueueClientNames();
            } catch (Exception e) {
                logger.error("Could not load Queue names from configuration file: " + e.getMessage());
            }
        }

        return queueClientNames;
    }
}
