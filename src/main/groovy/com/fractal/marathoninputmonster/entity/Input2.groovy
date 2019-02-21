package com.fractal.marathoninputmonster.entity


import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id


@Entity

class Input2{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id
    private String company_name ;
    private String observed_system_name;
    private String observed_system_api ;
    private String observed_system_secret ;



    Input2(){}

    Input2(String company_name ,
          String observed_system_name,
          String observed_system_api,
          String observed_system_secret){
        this.company_name = company_name ;
        this.observed_system_name = observed_system_name ;
        this.observed_system_api = observed_system_api ;
        this.observed_system_secret = observed_system_secret ;

    }



    public void setId(Long id){this.id = id}
    public Long getId(){return id}

    public void setCompany_name(String company_name){this.company_name = company_name}
    public String getCompany_name(){return company_name}

    public void setObserved_system_name(String observed_system_name){this.observed_system_name = observed_system_name}
    public String getObserved_system_name(){return observed_system_name}

    public void setObserved_system_api(String observed_system_api){this.observed_system_api = observed_system_api}
    public String getObserved_system_api(){return observed_system_api}

    public void setObserved_system_secret(String observed_system_secret){this.observed_system_secret = observed_system_secret}
    public String getObserved_system_secret(){return observed_system_secret}
    
    
    
}