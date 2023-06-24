package org.gfg.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UtilDemo {

    public static void main(String[] args) {
        int[] arr = {10,12,9,13,15};
        Arrays.sort(arr);

        //immutable list
        List<Integer> list = Arrays.asList(4,5,7,10);
        Collections.sort(list);

        //mutable list
        List<Integer> list2 = new ArrayList<>();
        list2.add(1);

        Collections.addAll(list2, 14, 16, 87);
        for (Integer num : list2){
            System.out.println(num);
        }
    }
}
