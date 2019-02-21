package com.fractal.marathoninputmonster.controller

import com.fractal.marathoninputmonster.entity.Color
import com.fractal.marathoninputmonster.entity.Input
import com.fractal.marathoninputmonster.entity.Input2

import com.fractal.marathoninputmonster.service.InputService
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



    List<Input> inputList = new ArrayList<>()


    @RequestMapping(value="/colors",method = RequestMethod.GET)
    Color colors(){
        new Color(name:"red")
    }


    @RequestMapping(value="/get_inputs",method = RequestMethod.GET)
    List<Input> inputs(){

       // List<Input> inputList = new ArrayList<>()
        for(int i = 0 ; i <= 3 ; i++){
            Input _input = new Input()
            _input.setCOMPANY_NAME("COMPANY_NAME")
            _input.setOBSERVED_SYSTEM_NAME("OBSERVED_SYSTEM_NAME")
            _input.setOBSERVED_SYSTEM_API("OBSERVED_SYSTEM_API")
            _input.setOBSERVED_SYSTEM_SECRET("OBSERVED_SYSTEM_SECRET")
            inputList.add(_input)
        }

        inputList
    }


    //@RequestMapping(value='/set_params/{COMPANY_NAME}/{OBSERVED_SYSTEM_NAME}/{OBSERVED_SYSTEM_API}/{OBSERVED_SYSTEM_SECRET}',method = RequestMethod.POST)
    @GetMapping("/api/set_params/uri/{COMPANY_NAME}/{OBSERVED_SYSTEM_NAME}/{OBSERVED_SYSTEM_API}/{OBSERVED_SYSTEM_SECRET}")
    Input  setParamsUri(@PathVariable("COMPANY_NAME") final String COMPANY_NAME ,
                          @PathVariable ("OBSERVED_SYSTEM_NAME") final String OBSERVED_SYSTEM_NAME,
                          @PathVariable ("OBSERVED_SYSTEM_API") final String OBSERVED_SYSTEM_API ,
                          @PathVariable ("OBSERVED_SYSTEM_SECRET") final String OBSERVED_SYSTEM_SECRET){
        Input _input = new Input()
        _input.setCOMPANY_NAME(COMPANY_NAME)
        _input.setOBSERVED_SYSTEM_NAME(OBSERVED_SYSTEM_NAME)
        _input.setOBSERVED_SYSTEM_API(OBSERVED_SYSTEM_API)
        _input.setOBSERVED_SYSTEM_SECRET(OBSERVED_SYSTEM_SECRET)
        inputList.add(_input)
        String resp = inputService.buildResponse(COMPANY_NAME,OBSERVED_SYSTEM_NAME,OBSERVED_SYSTEM_API,OBSERVED_SYSTEM_SECRET)
        return _input

    }//end of method



    //@PostMapping(value="/api/set_params/json",consumes = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/api/set_params/json",method = RequestMethod.POST)
    public ResponseEntity<Input2> setParamsJson(@RequestBody final Input2 _input){

        println _input.getCompany_name()
        println _input.getObserved_system_name()
        println _input.getObserved_system_api()
        println _input.getObserved_system_secret()

        /*
        String resp = inputService.buildResponse(_input.getCOMPANY_NAME(),
                                                    _input.getOBSERVED_SYSTEM_NAME(),
                                                    _input.getOBSERVED_SYSTEM_API(),
                                                    _input.getOBSERVED_SYSTEM_SECRET())
        */
        inputList.add(_input)

        return new ResponseEntity<Input>(_input, HttpStatus.OK);
    }



}//end of controller