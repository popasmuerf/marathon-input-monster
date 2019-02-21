package com.fractal.marathoninputmonster.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 * Created by mikeyb on 2/14/19.
 */



@Entity
class Input {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id ;
    private String COMPANY_NAME ;
    private String OBSERVED_SYSTEM_NAME;
    private String OBSERVED_SYSTEM_API ;
    private String OBSERVED_SYSTEM_SECRET ;


    Input(){}
    Input(String COMPANY_NAME ,
    String OBSERVED_SYSTEM_NAME,
    String OBSERVED_SYSTEM_API,
    String OBSERVED_SYSTEM_SECRET){
        this.COMPANY_NAME = COMPANY_NAME ;
        this.OBSERVED_SYSTEM_NAME = OBSERVED_SYSTEM_NAME ;
        this.OBSERVED_SYSTEM_API = OBSERVED_SYSTEM_API ;
        this.OBSERVED_SYSTEM_SECRET = OBSERVED_SYSTEM_SECRET ;
    }



    public void setId(Long id){this.id = id}
    public Long getId(){return id}

    public void setCOMPANY_NAME(String COMPANY_NAME){this.COMPANY_NAME = COMPANY_NAME}
    public String getCOMPANY_NAME(){return COMPANY_NAME}

    public void setOBSERVED_SYSTEM_NAME(String OBSERVED_SYSTEM_NAME){this.OBSERVED_SYSTEM_NAME = OBSERVED_SYSTEM_NAME}
    public String getOBSERVED_SYSTEM_NAME(){return OBSERVED_SYSTEM_NAME}

    public void setOBSERVED_SYSTEM_API(String OBSERVED_SYSTEM_API){this.OBSERVED_SYSTEM_API = OBSERVED_SYSTEM_API}
    public String getOBSERVED_SYSTEM_API(){return OBSERVED_SYSTEM_API}

    public void setOBSERVED_SYSTEM_SECRET(String OBSERVED_SYSTEM_SECRET){this.OBSERVED_SYSTEM_SECRET = OBSERVED_SYSTEM_SECRET}
    public String getOBSERVED_SYSTEM_SECRET(){return OBSERVED_SYSTEM_SECRET}

}
