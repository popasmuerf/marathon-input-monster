package com.fractal.marathoninputmonster.controller
import com.fractal.marathoninputmonster.entity.Color
import com.fractal.marathoninputmonster.entity.Input
import com.fractal.marathoninputmonster.service.InputService
import org.springframework.beans.factory.annotation.Autowired
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
    @GetMapping("/set_params/{COMPANY_NAME}/{OBSERVED_SYSTEM_NAME}/{OBSERVED_SYSTEM_API}/{OBSERVED_SYSTEM_SECRET}")
    Input  setParams(@PathVariable("COMPANY_NAME") final String COMPANY_NAME ,
                          @PathVariable ("OBSERVED_SYSTEM_NAME") final String OBSERVED_SYSTEM_NAME,
                          @PathVariable ("OBSERVED_SYSTEM_API") final String OBSERVED_SYSTEM_API ,
                          @PathVariable ("OBSERVED_SYSTEM_SECRET") final String OBSERVED_SYSTEM_SECRET){
        Input _input = new Input()
        _input.setCOMPANY_NAME(COMPANY_NAME)
        _input.setOBSERVED_SYSTEM_NAME(OBSERVED_SYSTEM_NAME)
        _input.setOBSERVED_SYSTEM_API(OBSERVED_SYSTEM_API)
        _input.setOBSERVED_SYSTEM_SECRET(OBSERVED_SYSTEM_SECRET)
        inputList.add(_input)
        inputService.buildResponse("red","blue","green","yellow")
        return _input

    }//end of method



}//end of controller