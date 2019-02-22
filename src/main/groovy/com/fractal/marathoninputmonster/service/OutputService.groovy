package com.fractal.marathoninputmonster.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class OutputService{
    @Value('${marathon.appname}')
    private final String  marathonAppName



    def sendReq() {
        String uri = "http://quotes.rest/qod.json"
        RestTemplate restTemplate = new RestTemplate()
        ResponseEntity<String> response = restTemplate.getForEntity(uri,String.class)
        println (response.getBody())
        return response.getBody()
    }

    def sendReq(String jsonStr) {
        String uri = "http://quotes.rest/qod.json"
        RestTemplate restTemplate = new RestTemplate()
        ResponseEntity<String> response = restTemplate.getForEntity(uri,String.class)
        println (response.getBody())
        return response.getBody()
    }

    def sendReqAlpha(String jsonStr){
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






}
