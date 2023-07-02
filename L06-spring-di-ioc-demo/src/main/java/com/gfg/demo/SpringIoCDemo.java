package com.gfg.demo;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class SpringIoCDemo {

    public static void main(String[] args) {
//         Spring IoC
        ClassPathXmlApplicationContext factory = new ClassPathXmlApplicationContext("projectbeans.xml");
        Engine engine1 = (Engine) factory.getBean("engine1");
        System.out.println(engine1);

        Engine engine2 = (Engine) factory.getBean("engine1");
        System.out.println(engine2);
        if(engine1 == engine2){
            System.out.println("both objects(beans) are same");
        }
        else {
            System.out.println("both objects(beans) are not same");
        }


        Car car1 = (Car) factory.getBean("car1");
        System.out.println(car1);
        Engine engine3 = (Engine) factory.getBean("engine3");
        if(engine3 == car1.getEngine()){
            System.out.println("both objects(beans) are same");
        }
        else {
            System.out.println("both objects(beans) are not same");
        }

        factory.close();


    }

}
