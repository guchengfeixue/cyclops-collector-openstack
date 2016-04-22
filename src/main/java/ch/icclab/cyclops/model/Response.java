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
package ch.icclab.cyclops.model;

/**
 * Author: Oleksii
 * Date: 01/04/2016
 * Description: This class holds the Response data
 */
public class Response {

    private String userName;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private String instanceId;


    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    private String action;


    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    private String service_type;


    public String getService_type() {
        return service_type;
    }

    public void setService_type(String service_type) {
        this.service_type = service_type;
    }


    private ObjectData objectData;


    public ObjectData getObjectData() {
        return objectData;
    }

    public void setObjectData(ObjectData objectData) {
        this.objectData = objectData;
    }

    public static class ObjectData{
        public Double memory;
        public Double vcpus;


        public void setMemory(Double memory) {this.memory = memory;}

        public void setVcpus(Double vcpus) {this.vcpus = vcpus;}
    }

}
