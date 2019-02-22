package com.fractal.marathoninputmonster.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.PropertySource

import org.springframework.stereotype.Component


@Component
@EnableConfigurationProperties
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix="marathon")
class ServiceConfig{


    private String marathontemplate
    private String marathonappname
    private String marathonuri


    public String getMarathontemplate(){return marathontemplate}
    public void setMarathontemplate(String marathontemplate){this.marathontemplate = marathontemplate}


    public String getMarathonappname(){return marathonappname}
    public void setMarathonappname(String marathonappName){this.marathonappname = marathonappname}

    public String getMarathonuri(){return marathonuri}
    public void setMarathonuri(String marathonuri){this.marathonuri = marathonuri}


}