#Marathon Monster is MicroService !!!!  BAM!!


#####What does it do ?:

    inputs:
    curl http://apphost.com/api/set_params/uri/{COMPANY_NAME}/{OBSERVED_SYSTEM_NAME}/{OBSERVED_SYSTEM_API}/{OBSERVED_SYSTEM_SECRET}
    
    
    -OR-
    
    
    curl -d '{"COMPANY_NAME":"value1", "OBSERVED_SYSTEM_NAME":"value2","OBSERVED_SYSTEM_SECRET":"value1", "OBSERVED_SYSTEM_SECRET":"value2"}' -H "Content-Type: application/json" -X POST http://localhost:3000/data


    outputs:
    
     curl -X PUT -d '{id=/red/blue, apps=[{id=/red/blue/conmon-agent-handler, args=[/runtime/entrypoint-handler.sh], backoffFactor=1.15, backoffSeconds=1, container={type=DOCKER, docker={forcePullImage=true,......}' -H "Content-Type: application/json" -X POST http://marathon.mesos:8080/v2/apps/$app_name



#####What you need to know about the build....
* This app was written using Spring Boot ver 2.1.2

* This app is coded using Groovy 2.5.6

* Java bytecode compatibility 1.8

* For more build info...check the pom.xml



#####How to compile and run this mutha ?
    > mvn clean
    > mvn validate
    > mvn compile
    > mvn spring-boot:run  #All of the previous steps are executed with this one command...if you are lazy

#####This code looks weird...where do I start ?....
* All code pertaining to the app's endpoints is located in the the controller package:
       
       com.fractal.marathonmonster.controller
       
        
  All controllers contain logic for constructing REST endpoints and once a REST request has been intercepted, the data contained within will then be passed to a "service" object to be processed.
   
   


* All code pertaining to running "business logic"(we keep the code responsible for different purposes seperated and compartmenatlized as much as possible so that it will be easier to maintain/modify the app as much as possible..for example..we keep code responsible for constructing REST endpoints should not be contained withint he same module as logic responsible for processing data contained withing REST POST/PUT requests....) is contained within the package:

        com.fractal.marathonmonster.service

* What are the "repositories" for ?  They aren't currently being used in this app...but with that being said, "repositories" are used to as interfaces to some persistance layer(normally databases such as Postgres, or Mongo...).  I have included them in the app if by chance later the current maintainer would like to store data such as any particular incomming, valid REST request

        com.fractal.marathonmonster.repo


* What are these "entities" ?  Entities are basically just POJO/POGOS that are used as an abstraction/unmarshalling of the
data extracted from the incomming uri/json. These objects are used for easy transport of data to other components for storage or processing within Service objects

        com.fractal.marathonmonster.entity
        
        
 
* What are the  "configuration" classes for  ?  These aren't currently being used in this app.  Ideally they would be used  within the Service classes to provide configuration details...but we are providing such functionality via using the @Value annotation...drawing config paramters from application.properties

        com.fractal.marathonmonster.configuration
        
        
 #####For detailed information pertaining to how you build REST services endpoints in Spring Boot:
 
         https://spring.io/guides/tutorials/rest/
         



 #####Misc
 FooBarController:
 
         Used for quick testing.  I kept it in the source for just that....testing.


ColorRepository:

    Left this in for the same reason I left in FooBarController
    
    
####So...What actually works ?
The endpoints work.....but I have not
yet tested the OutputService that is supposed to reach out to Marathon's endpoint with the mondified marathon.json payload...because I
1. I don't have access to Marathon
2. Lack of time.  As I write this...I will not longer be the maintainer of this app.
3. The current maintainer(You) will have to pick that effort back up.

####What else do you need to know ?

*   Due to my time constraints ; this code doesn't really have any test coverage...

*   Again...do to time constraints this code doesn't handle exceptions gracefully at all....

* Both of the above issues can be remedied with out too much hassled by whom ever will be this app's current maintainer....

* This app isn't logging to anything....again...you can fix this if you want.


######The imagined workflow:

user
|
|->(url/json req)
     |
     |--> controller
          |
          |-->InputService(modify json)
              |
              |->OutputService(send json)
                 |
                 |-->MarathonEndpt
                 
######The actual workflow 

user
|
| ->(url/json req)
     |
     |--> controller
          |
          |-->InputService(modify json)
              |
              |->OutputService(send json)
                 |
                 |-->QouteOfTheDayEndpt
                 
^^^Why?  Again...I didn't have time to actually test aagainst marathon.....so I used a freely avaible REST endpt that is external to the app to prove that we can make a call out that is triggered by successful execution of the app's endpts....If you look inside the class:

    
          com.fractal.marathonmonster.service.OutputService

You will notice that there exists these two methods:


      def sendReq(String jsonStr) {
              String uri = "http://quotes.rest/qod.json"
              RestTemplate restTemplate = new RestTemplate()
              ResponseEntity<String> response = restTemplate.getForEntity(uri,String.class)
              println (response.getBody())
              return response.getBody()
          }
          
    def sendReqToMarathon(String jsonStr){
            String uri = 'http://marathon.mesos:8080/v2/apps/' + marathonAppName
            HttpHeaders httpHeaders = new HttpHeaders()
            httpHeaders.set("Content-Type","application/json")
            HttpEntity<String> httpEntity = new HttpEntity<String>(jsonStr, httpHeaders)
    
            RestTemplate restTemplate = new RestTemplate()
            String resp = restTemplate.postForObject(uri,httpEntity,String.class)
            org.json.JSONObject jsonObj = new org.json.JSONObject(resp)
            String jsonRespStr = jsonObj.toString()
            return jsonRespStr
        }
                
                
    
Right now, sendReq() is being used withing the endpt methods within:

    com.fractal.marathoninputmonster.controller.MarathonInputMonsterController 
    
    
...as such:

    @GetMapping("/api/set_params/uri/{COMPANY_NAME}/{OBSERVED_SYSTEM_NAME}/{OBSERVED_SYSTEM_API}/{OBSERVED_SYSTEM_SECRET}")
        InputUri  setParamsUri(@PathVariable("COMPANY_NAME") final String COMPANY_NAME ,
                              @PathVariable ("OBSERVED_SYSTEM_NAME") final String OBSERVED_SYSTEM_NAME,
                              @PathVariable ("OBSERVED_SYSTEM_API") final String OBSERVED_SYSTEM_API ,
                              @PathVariable ("OBSERVED_SYSTEM_SECRET") final String OBSERVED_SYSTEM_SECRET){
            InputUri _input = new InputUri()
            _input.setCOMPANY_NAME(COMPANY_NAME)
            _input.setOBSERVED_SYSTEM_NAME(OBSERVED_SYSTEM_NAME)
            _input.setOBSERVED_SYSTEM_API(OBSERVED_SYSTEM_API)
            _input.setOBSERVED_SYSTEM_SECRET(OBSERVED_SYSTEM_SECRET)
            inputList.add(_input)
            String getResp = inputService.buildResponse(COMPANY_NAME,OBSERVED_SYSTEM_NAME,OBSERVED_SYSTEM_API,OBSERVED_SYSTEM_SECRET)
            String postResp = outputService.sendReq(getResp)
            return _input
    
        }//end of method
    
    
        @RequestMapping(value = "/api/set_params/json",method = RequestMethod.POST)
        public ResponseEntity<InputJson> setParamsJson(@RequestBody final InputJson _input){
    
            println _input.getCompany_name()
            println _input.getObserved_system_name()
            println _input.getObserved_system_api()
            println _input.getObserved_system_secret()
    
    
            String getResp = inputService.buildResponse(_input.getCompany_name(),
                                                        _input.getObserved_system_name(),
                                                        _input.getObserved_system_api(),
                                                        _input.getObserved_system_secret())
            String postResp = outputService.sendReq(getResp)
    
            inputList.add(_input)
    
            return new ResponseEntity<InputJson>(_input, HttpStatus.OK)
        }       
        
        
        
^^^When you run the app as is, you will get back JSON that includes the Qoute of the day...switch the method signature to :

    sendReqToMarathon(String jsonStr)
    
When you are ready to test sending the modified marathon.json to the appropiate Marathon endpt.




####Other Stuff

*   Spring makes it easy to add persistence
        https://docs.spring.io/spring-data/data-commons/docs/1.6.1.RELEASE/reference/html/repositories.html

*   Spring makes it easy to add Swagger documentation

    https://springframework.guru/spring-boot-restful-api-documentation-with-swagger-2/



