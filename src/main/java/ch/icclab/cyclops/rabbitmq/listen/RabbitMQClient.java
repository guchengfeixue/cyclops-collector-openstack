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
package ch.icclab.cyclops.rabbitmq.listen;

import ch.icclab.cyclops.load.Loader;
import ch.icclab.cyclops.load.model.RabbitMQCredentials;
import ch.icclab.cyclops.rabbitmq.RabbitMQAbstract;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**
 * Author: Oleksii
 * Created: 05/04/16
 * Description: RabbitMQ client
 */
public class RabbitMQClient extends RabbitMQAbstract{
    final static Logger logger = LogManager.getLogger(RabbitMQClient.class.getName());

    // this class has to be a singleton
    private static RabbitMQClient singleton = new RabbitMQClient();

    // credentials to be used with RabbitMQ
    private RabbitMQCredentials credentials;

    // our internal list of consumer tasks


    // variables needed for RabbitMQ connection

    /**
     * Constructor has to be hidden
     */
    private RabbitMQClient() {
        credentials = Loader.getSettings().getRabbitMQClientCredentials();
        listOfConsumers = new ArrayList<RabbitMQAbstract.ConsumerEntry>();
    }

    public RabbitMQCredentials getCredentials(){
        return Loader.getSettings().getRabbitMQClientCredentials();
    }

    /**
     * Access RabbitMQ object
     * @return singleton instance
     */
    public static RabbitMQClient getInstance() { return singleton; }

}

