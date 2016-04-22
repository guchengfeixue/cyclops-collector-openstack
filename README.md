<a href="http://icclab.github.io/cyclops" target="_blank"><img align="middle" src="http://icclab.github.io/cyclops/assets/images/logo_big.png"></img></a>

# <code>Openstack Collector</code>

## Openstack Collector microservice
Openstack Collector micro service as part of <a href="http://icclab.github.io/cyclops">Cyclops framework</a>.

### Download
     $ git clone https://github.com/icclab/cyclops-collector-openstack.git
### Installation
If you have already deployed <a href="https://github.com/icclab/cyclops-udr" target="_blank">UDR</a> microservice, you can skip <code>install_prereq</code> script, as Openstack Collector has the same prerequisites.

     $ cd cyclops-collector-openstack/install
     $ chmod +x ./*
     $ bash install_prereq.sh
     $ bash setup_openstack_collector.sh

#### Configuration
 * At the end of the installation process you will be asked for your deployment credentials and to modify any configuration parameters, **please do not ignore this step.**
 * If there is a need to update your configuration, you can find it stored here cyclops-collector-openstack/src/main/webapp/WEB-INF/configuration.txt

### Deployment
     $ bash deploy_openstack_collector.sh

### Documentation
  Visit the <a href="https://github.com/icclab/cyclops-collector-openstack/wiki">Wiki</a> for detailed explanation and API reference guide.

### Cyclops architecture
<img align="middle" src="http://blog.zhaw.ch/icclab/files/2013/05/overall_architecture.png" alt="CYCLOPS Architecture" height="500" width="600"></img>

### Bugs and issues
  To report any bugs or issues, please use <a href="https://github.com/icclab/cyclops-collector-openstack/issues">Github Issues</a>
  
### Communication
  * Email: icclab-rcb-cyclops[at]dornbirn[dot]zhaw[dot]ch
  * Website: <a href="http://icclab.github.io/cyclops" target="_blank">icclab.github.io/cyclops</a>
  * Blog: <a href="http://blog.zhaw.ch/icclab" target="_blank">http://blog.zhaw.ch/icclab</a>
  * Tweet us @<a href="https://twitter.com/ICC_Lab">ICC_Lab</a>
   
### Developed @
<img src="http://blog.zhaw.ch/icclab/files/2014/04/icclab_logo.png" alt="ICC Lab" height="180" width="620"></img>

### License
 
      Licensed under the Apache License, Version 2.0 (the "License"); you may
      not use this file except in compliance with the License. You may obtain
      a copy of the License at
 
           http://www.apache.org/licenses/LICENSE-2.0
 
      Unless required by applicable law or agreed to in writing, software
      distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
      WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
      License for the specific language governing permissions and limitations
      under the License.
