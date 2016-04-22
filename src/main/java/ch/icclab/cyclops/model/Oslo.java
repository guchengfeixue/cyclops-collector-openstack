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
 * Description: This class holds the Oslo data
 */
public class Oslo {
    Args args;
    String _context_user_name;
    String _context_timestamp;

    public String get_context_user_name(){ return _context_user_name; }

    public String get_context_timestamp(){
        return _context_timestamp;
    }

    public Args getArgs() {
        return args;
    }

    public class Args{
        Kwargs kwargs;
        Object objinst;
        String objmethod;

        public Object getObjinst() {
            return objinst;
        }

        public Kwargs getKwargs() {
            return kwargs;
        }

        public String getObjmethod() { return objmethod; }

        public class Kwargs{
            Object expected_task_state;

            public Object getExpected_task_state() {
                return expected_task_state;
            }
        }
    }
}

