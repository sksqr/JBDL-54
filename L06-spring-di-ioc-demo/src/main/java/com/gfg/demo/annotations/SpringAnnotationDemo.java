package com.gfg.demo.annotations;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

public class SpringAnnotationDemo {


    public static void main(String[] args) {

//        ApplicationContext context = new AnnotationConfigApplicationContext(SpringAnnotationDemo.class);
        ApplicationContext context = new AnnotationConfigApplicationContext("com.gfg.demo.annotations");
        NotificationService notificationService = (NotificationService) context.getBean("notificationService");
        notificationService.sendNotification("OTP: 8687");

        SMSGateway smsGateway = (SMSGateway) context.getBean("smsGateway");

        System.out.println(smsGateway == notificationService.getSmsGateway());

        NotificationService notificationService2 = new NotificationService();


//        context.close();
    }
}