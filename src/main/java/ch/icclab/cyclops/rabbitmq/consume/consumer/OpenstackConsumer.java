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
package ch.icclab.cyclops.rabbitmq.consume.consumer;

import ch.icclab.cyclops.load.Loader;
import ch.icclab.cyclops.model.NovaData;
import ch.icclab.cyclops.model.Oslo;
import ch.icclab.cyclops.model.Response;
import ch.icclab.cyclops.rabbitmq.AbstractConsumer;
import ch.icclab.cyclops.rabbitmq.listen.RabbitMQClient;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Author: Oleksii
 * Created: 05/04/16
 * Description: Consumer of openstack messages
 */
public class OpenstackConsumer extends AbstractConsumer {

    final static Logger logger = LogManager.getLogger(OpenstackConsumer.class.getName());

    @Override
    protected void consume(String message) {

        Gson mapper = new Gson();
        Map map = (Map) mapper.fromJson(message, Object.class);
        List<String> without_metadata = Arrays.asList( "pausing", "unpausing", "[powering-off]", "powering-on", "suspending", "resuming");
        List<String> with_metadata = Arrays.asList("spawning", "resize_finish");
        try{
            Oslo oslo = mapper.fromJson(map.get("oslo.message").toString(), Oslo.class);
            String method = oslo.getArgs().getKwargs().getExpected_task_state().toString();
            if (method != null){
                System.out.println(method);
            }
            if(with_metadata.contains(method) | without_metadata.contains(method)) {
                Map data = (Map) mapper.fromJson( mapper.toJson(oslo.getArgs().getObjinst()), Object.class);
                NovaData novaData = mapper.fromJson(mapper.toJson(data.get("nova_object.data")), NovaData.class);
                String instanceId = novaData.getUuid();
                Response response = new Response();
                response.setAction(method);
                response.setInstanceId(instanceId);
                response.setUserName(oslo.get_context_user_name());
                response.setService_type("novaInstanceUpTime");
                System.out.println(mapper.toJson(response));
                if (with_metadata.contains(method)){
                    Response.ObjectData objectData = new Response.ObjectData();
                    objectData.setMemory(novaData.getMemory_mb());
                    objectData.setVcpus(novaData.getVcpus());
                    response.setObjectData(objectData);
                }

                RabbitMQClient rabbitMQClient = RabbitMQClient.getInstance();
                String queue = Loader.getSettings().getQueueClientNames().getDefaultQueue();
                rabbitMQClient.sendMessage(mapper.toJson(response), queue);

                System.out.println(mapper.toJson(response));
                System.out.println(queue);
            }


        } catch (NullPointerException e){

        }
        // implement your consumer here
    }
}
