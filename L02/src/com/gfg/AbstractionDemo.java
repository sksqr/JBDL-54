package com.gfg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class AbstractionDemo {

    public static void main(String[] args) {
        SortingInterface sortingInterface = new BubleSort();
        SortingService sortingService = new SortingService(sortingInterface);

        sortingService.doSorting(new ArrayList<>());
        sortingService.doSorting(new LinkedList<>());

        List<Integer> list = new ArrayList<>();

        list.add(10);
        list.add(1);
        list.add(5);
        list.add(12);

        Collections.sort(list);

        System.out.println(list);


//        TataCar tataCar = new TataCar();

        TataTiago tataTiago = new TataTiago();

        System.out.println(tataTiago.getRC());

        System.out.println(tataTiago.getCompany());

        System.out.println(tataTiago.startEngine());
    }
}
