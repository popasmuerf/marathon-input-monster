package com.fractal.marathoninputmonster.controller

import com.fractal.marathoninputmonster.entity.InputJson
import com.fractal.marathoninputmonster.entity.InputUri
import com.fractal.marathoninputmonster.service.InputService
import com.fractal.marathoninputmonster.service.OutputService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
/**
 * Created by mikeyb on 2/14/19.
 */


@RestController
class MarathonInputMonsterController {


    @Autowired
    InputService inputService

    @Autowired
    OutputService outputService


    List<InputUri> inputList = new ArrayList<>()


    @RequestMapping(value="/get_inputs",method = RequestMethod.GET)
    List<InputUri> inputs(){

        for(int i = 0 ; i <= 3 ; i++){
            InputUri _input = new InputUri()
            _input.setCOMPANY_NAME("COMPANY_NAME")
            _input.setOBSERVED_SYSTEM_NAME("OBSERVED_SYSTEM_NAME")
            _input.setOBSERVED_SYSTEM_API("OBSERVED_SYSTEM_API")
            _input.setOBSERVED_SYSTEM_SECRET("OBSERVED_SYSTEM_SECRET")
            inputList.add(_input)
        }

        inputList
    }


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



}//end of controller