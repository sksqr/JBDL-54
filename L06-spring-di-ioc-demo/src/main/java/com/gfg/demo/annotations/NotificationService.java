package com.gfg.demo.annotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("notificationService")
public class NotificationService {
    @Autowired
    private SMSGateway smsGateway;

    public void sendNotification(String data){
        smsGateway.sendSMS(data);
    }

    public SMSGateway getSmsGateway() {
        return smsGateway;
    }
}
