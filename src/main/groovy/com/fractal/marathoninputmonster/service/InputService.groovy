package com.fractal.marathoninputmonster.service
import groovy.json.JsonSlurper
//import groovy.json.JsonOutput
//import groovy.transform.Field
import org.springframework.stereotype.Service;
/**
 * Created by mikeyb on 2/14/19.
 */
@Service
class InputService{

    //here we are going to have to figure out where we are going to save
    //this marathon.json file....because technically in practice
    //it is a config file....
    def inputFile = new File('/home/mikeyb/data/json/marathon.json')
    //def inputFile = new File('/Users/mdb/data/json/marathon.json')
    //def json = new JsonSlurper().parseText(inputFile.text)

    def buildResponse(String company_name,
                      String observed_system_name,
                      String observed_system_api,
                      String observed_system_secret){

        String  COMPANY_NAME = company_name
        String  OBSERVED_SYSTEM_NAME = observed_system_name
        String  OBSERVED_SYSTEM_API = observed_system_api
        String  OBSERVED_SYSTEM_SECRET = observed_system_secret


        println COMPANY_NAME
        println OBSERVED_SYSTEM_NAME
        println OBSERVED_SYSTEM_API
        println OBSERVED_SYSTEM_SECRET




        def json = new JsonSlurper().parseText(inputFile.text)
        json['id'] = "/${COMPANY_NAME}/${OBSERVED_SYSTEM_NAME}"
        json.apps[0].id = "/${COMPANY_NAME}/${OBSERVED_SYSTEM_NAME}/conmon-agent-handler"
        json.apps[0].container.portMappings[0]['labels']['VIP_0'] = "/${COMPANY_NAME}-${OBSERVED_SYSTEM_NAME}-conmon-agent-handler:443"
        json.apps[0].dependencies[0] = "${COMPANY_NAME}/${OBSERVED_SYSTEM_NAME}/redis"
        json.apps[0].env['REDIS_HOST'] = "${COMPANY_NAME}-${OBSERVED_SYSTEM_NAME}-redis.marathon.l4lb.thisdcos.directory"
        json.apps[0]['labels']['HAPROXY_0_VHOST']= "${COMPANY_NAME}-${OBSERVED_SYSTEM_NAME}-conmon-agent-handler.fos"
        json.apps[1].id = "/${COMPANY_NAME}/${OBSERVED_SYSTEM_NAME}/orsmongo"
        json.apps[1].cmd = "/start.sh && echo \"127.0.1.1 ${COMPANY_NAME}-${OBSERVED_SYSTEM_NAME}-ors-mongo.marathon.l4lb.thisdcos.directory\" >> /etc/hosts && sleep 60 && mongod --bind_ip  0.0.0.0,:: --replSet rs0 --oplogSize 2048"
        json.apps[1].container.docker.parameters[1].value = "size=100,repl=3,name=${COMPANY_NAME}-${OBSERVED_SYSTEM_NAME}-ors-mongo:/data/db"
        json.apps[1].container.portMappings[0]['labels']['VIP_0'] = "${COMPANY_NAME}-${OBSERVED_SYSTEM_NAME}-ors-mongo:27017"
        json.apps[1].container.portMappings[0].name = "${COMPANY_NAME}-${OBSERVED_SYSTEM_NAME}-ors-mongo"
        json.apps[1].env['MONGO_DNS_PREFIX']="${COMPANY_NAME}-${OBSERVED_SYSTEM_NAME}-ors-mongo"

        json.apps[2].id = "/${COMPANY_NAME}/${OBSERVED_SYSTEM_NAME}/osquery-handler"
        json.apps[2].container.portMappings[0]['labels']['VIP_0'] = "${COMPANY_NAME}-${OBSERVED_SYSTEM_NAME}-osquery-handler:443"
        json.apps[2].container.portMappings[0].name = "${COMPANY_NAME}-${OBSERVED_SYSTEM_NAME}-osquery-handler"
        json.apps[2]['labels']['HAPROXY_0_VHOST']= "${COMPANY_NAME}-${OBSERVED_SYSTEM_NAME}-osquery-handler.fos"

        json.apps[3].id = "/${COMPANY_NAME}/${OBSERVED_SYSTEM_NAME}/ors"
        json.apps[3].container.portMappings[0]['labels']['VIP_0'] = "${COMPANY_NAME}-${OBSERVED_SYSTEM_NAME}-ors:8002"
        json.apps[3].container.portMappings[1]['labels']['VIP_1'] = "${COMPANY_NAME}-${OBSERVED_SYSTEM_NAME}-ors.fractalos.com:443"
        json.apps[3].env.CONMON_OSQUERY_SECRET_KEY="${OBSERVED_SYSTEM_SECRET}"
        json.apps[3].env.ORS_DNS_PREFIX="${COMPANY_NAME}-${OBSERVED_SYSTEM_NAME}-ors"
        json.apps[3].env.CONMON_OSQUERY_BASE_URL="http://${COMPANY_NAME}-${OBSERVED_SYSTEM_NAME}-osquery-handler.fos"
        json.apps[3].env.SWAGGER_APIS_CLIENT_HOST="${COMPANY_NAME}-${OBSERVED_SYSTEM_NAME}-ors.fos"
        json.apps[3].env.CONMON_API_KEY="${OBSERVED_SYSTEM_API}"
        json.apps[3].env.CONMON_SECRET_KEY="${OBSERVED_SYSTEM_SECRET}"
        json.apps[3].env.CONMON_OSQUERY_API_KEY="${OBSERVED_SYSTEM_API}"
        json.apps[3].env.ORS_DB_HOST="${COMPANY_NAME}-${OBSERVED_SYSTEM_NAME}-ors-mongo.marathon.l4lb.thisdcos.directory"
        json.apps[3].env.SWAGGER_APIS_OSQUERY_HOST="${COMPANY_NAME}-${OBSERVED_SYSTEM_NAME}-ors.marathon.l4lb.thisdcos.directory:443"
        json.apps[3]['labels']['HAPROXY_0_VHOST']= "${COMPANY_NAME}-${OBSERVED_SYSTEM_NAME}-ors.fos"

        json.apps[4].id = "/${COMPANY_NAME}/${OBSERVED_SYSTEM_NAME}/rabbitmq"
        json.apps[4].container.docker.parameters[1].value = "size=800,repl=3,name=${COMPANY_NAME}-${OBSERVED_SYSTEM_NAME}-rabbitmq:/var/lib/rabbitmq"
        json.apps[4].container.portMappings[0]['labels']['VIP_0'] = "/${COMPANY_NAME}-${OBSERVED_SYSTEM_NAME}-rabbitmq:15674"
        json.apps[4].container.portMappings[1]['labels']['VIP_1'] = "/${COMPANY_NAME}-${OBSERVED_SYSTEM_NAME}-rabbit-mgmt:15672"
        json.apps[4].container.portMappings[2]['labels']['VIP_2'] = "/${COMPANY_NAME}-${OBSERVED_SYSTEM_NAME}-rabbitmq:5671"
        json.apps[4].container.portMappings[3]['labels']['VIP_3'] = "/${COMPANY_NAME}-${OBSERVED_SYSTEM_NAME}-rabbitmq-default:5672"
        json.apps[4].container.portMappings[4]['labels']['VIP_4'] = "/${COMPANY_NAME}-${OBSERVED_SYSTEM_NAME}-rabbitmq-stomp:61613"
        json.apps[4].env['RABBITMQ_DNS_PREFIX']="${COMPANY_NAME}-${OBSERVED_SYSTEM_NAME}"
        json.apps[4]['labels']['HAPROXY_1_VHOST']= "${COMPANY_NAME}-${OBSERVED_SYSTEM_NAME}-rabbitmq.fos"

        json.apps[5].id = "/${COMPANY_NAME}/${OBSERVED_SYSTEM_NAME}/redis"
        json.apps[5].container.docker.parameters[1].value = "size=100,repl=3,name=${COMPANY_NAME}-${OBSERVED_SYSTEM_NAME}-redis:/data"
        json.apps[5].container.portMappings[0]['labels']['VIP_0'] = "/${COMPANY_NAME}-${OBSERVED_SYSTEM_NAME}-redis:6379"

        println(json.toString())

        def req = json.toString()
        return req
    }

}//end of class