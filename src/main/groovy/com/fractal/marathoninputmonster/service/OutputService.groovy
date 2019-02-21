package com.fractal.marathoninputmonster.service


import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

//https://www.baeldung.com/rest-template

@Service
class OutputService{

    def sendReq() {
        String uri = "http://quotes.rest/qod.json"
        RestTemplate restTemplate = new RestTemplate()

        ResponseEntity<String> response = restTemplate.getForEntity(uri,String.class)
        //assertThat(response.getStatusCode(),equalto(HttpStatus.OK))
        println (response.getBody())
        //ObjectMapper mapper = ObjectMapper()
        //JsonNode root = mapper.readTree(response.)
        return response.getBody()
    }

    def sendReq(String jsonStr) {
        String uri = "http://quotes.rest/qod.json"
        RestTemplate restTemplate = new RestTemplate()

        ResponseEntity<String> response = restTemplate.getForEntity(uri,String.class)
        println (response.getBody())
        //ObjectMapper mapper = ObjectMapper()
        //JsonNode root = mapper.readTree(response.)
        return response.getBody()
    }

    def sendReqToMarathon(String jsonStr){
        String uri = "http://quotes.rest/qod.json"
        RestTemplate restTemplate = new RestTemplate()

        ResponseEntity<String> response = restTemplate.getForEntity(uri,String.class)
        //assertThat(response.getStatusCode(),equalto(HttpStatus.OK))
        println (response.getBody())
        //ObjectMapper mapper = ObjectMapper()
        //JsonNode root = mapper.readTree(response.)
        return response.getBody()
    }


}
