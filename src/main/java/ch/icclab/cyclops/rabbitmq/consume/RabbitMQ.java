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

package ch.icclab.cyclops.rabbitmq.consume;

import ch.icclab.cyclops.rabbitmq.RabbitMQAbstract;
import ch.icclab.cyclops.load.Loader;
import ch.icclab.cyclops.load.model.RabbitMQCredentials;
import com.rabbitmq.client.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;

/**
 * Author: Skoviera
 * Created: 25/01/16
 * Description: Implementation of RabbitMQ manager for consuming tasks
 * NOTE: Queue parallelisation not utilised, if someone adds more consumers via "addConsumer", message processing will be sequential
 */
public class RabbitMQ extends RabbitMQAbstract {
    final static Logger logger = LogManager.getLogger(RabbitMQ.class.getName());

    // this class has to be a singleton
    private static RabbitMQ singleton = new RabbitMQ();

    // credentials to be used with RabbitMQ

    // our internal list of consumer tasks


    // variables needed for RabbitMQ connection


    public RabbitMQCredentials getCredentials(){
        return Loader.getSettings().getRabbitMQCredentials();
    }

    /**
     * Constructor has to be hidden
     */
    private RabbitMQ() {

        listOfConsumers = new ArrayList<ConsumerEntry>();
    }

    /**
     * Access RabbitMQ object
     *
     * @return singleton instance
     */
    public static RabbitMQ getInstance() {
        return singleton;
    }

}
