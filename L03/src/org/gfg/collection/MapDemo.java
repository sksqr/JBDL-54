package org.gfg.collection;

import java.util.*;

public class MapDemo {

    public static void main(String[] args) {
//        Map<String,String> cityMap = new HashMap<>();
//        cityMap.put("shashi","lucknow");
//        cityMap.put("ravi","delhi");
//        cityMap.put("rakesh","lucknow");
//        System.out.println(cityMap.put("parul","bangalore"));
//        System.out.println(cityMap.get("rakesh"));
//        System.out.println(cityMap.get("parul"));
//        //put return previous value
//        System.out.println(cityMap.put("parul","Mumbai"));
//        System.out.println(cityMap.get("parul"));

        Batch batch1 = new Batch("JBDL-54", "Shashi");
        Batch batch2 = new Batch("JBDL-53", "Shashi");
        Map<Batch,String> locMap = new HashMap<>();
        locMap.put(batch1,"Noida");
        locMap.put(batch2,"Delhi");
        System.out.println(locMap.get(batch1));
        Batch batch3 = new Batch("JBDL-54", "Shashi");
        System.out.println(batch1.equals(batch3));
        System.out.println(locMap.get(batch3));

        // multi map
        Map<String, Map<Integer,String>> mapOfMap = new HashMap<>();

    }
}
