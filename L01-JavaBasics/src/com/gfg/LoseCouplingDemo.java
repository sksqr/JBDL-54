package com.gfg;

import java.util.ArrayList;

public class LoseCouplingDemo {

    public static void main(String[] args) {
        SortingInterface sortingInterface1 = new BubleSort();
        SortingInterface sortingInterface2 = new MergeSort();
        SortingInterface sortingInterface3 = new CountSort();

        SortingService sortingService = new SortingService(sortingInterface1);
        sortingService.doSorting(new ArrayList<>());

        SortingService sortingService2 = new SortingService(sortingInterface2);
        sortingService2.doSorting(new ArrayList<>());



        SortingService sortingService3 = new SortingService(sortingInterface3);
        sortingService3.doSorting(new ArrayList<>());


    }
}
