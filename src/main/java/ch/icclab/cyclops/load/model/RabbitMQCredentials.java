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
package ch.icclab.cyclops.load.model;

/**
 * Author: Skoviera
 * Created: 25/01/16
 * Description: RabbitMQ credentials
 */
public class RabbitMQCredentials {

    // These fields correspond with the configuration file
    private String rabbitMQUsername;
    private String rabbitMQPassword;
    private String rabbitMQHost;
    private int rabbitMQPort;
    private String rabbitMQVirtualHost;

    //==== Getters and Setters

    public String getRabbitMQUsername() {
        return rabbitMQUsername;
    }
    public void setRabbitMQUsername(String rabbitMQUsername) {
        this.rabbitMQUsername = rabbitMQUsername;
    }
    public String getRabbitMQPassword() {
        return rabbitMQPassword;
    }
    public void setRabbitMQPassword(String rabbitMQPassword) {
        this.rabbitMQPassword = rabbitMQPassword;
    }
    public String getRabbitMQHost() {
        return rabbitMQHost;
    }
    public void setRabbitMQHost(String rabbitMQHost) {
        this.rabbitMQHost = rabbitMQHost;
    }
    public int getRabbitMQPort() {
        return rabbitMQPort;
    }
    public void setRabbitMQPort(int rabbitMQPort) {
        this.rabbitMQPort = rabbitMQPort;
    }
    public String getRabbitMQVirtualHost() {
        return rabbitMQVirtualHost;
    }
    public void setRabbitMQVirtualHost(String rabbitMQVirtualHost) {
        this.rabbitMQVirtualHost = rabbitMQVirtualHost;
    }
}
