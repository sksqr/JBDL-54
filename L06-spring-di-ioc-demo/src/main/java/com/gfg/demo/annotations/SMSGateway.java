package com.gfg.demo.annotations;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component("smsGateway")
@Scope("prototype")
public class SMSGateway {

    public SMSGateway() {
        System.out.println("Creating SMS gateway");
    }

    public void sendSMS(String data){
        System.out.println("Sending SMS: "+data);
    }

    @PostConstruct
    public void initMethod(){
        System.out.println("Calling init method in SMS gateway");
    }

    @PreDestroy
    public void destroyMethod(){
        System.out.println("Calling destroy method in SMS gateway");
    }

}
