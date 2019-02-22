package com.fractal.marathoninputmonster.controller
import com.fractal.marathoninputmonster.entity.Color
import com.fractal.marathoninputmonster.service.OutputService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Class FooBar is a RestController class used for testing stuff out.
 */

@RestController
class FooBarController{

    @Autowired
    OutputService outputService

    @GetMapping("/foobar")
    Color  foobar(){
        new Color(name:"red")
    }

    @GetMapping("/barfoo")
    String barfoo(){
        return new String("barfoo")
    }

    @GetMapping("/alpha")
    String alpha(){
        return outputService.sendReq()
    }





}
