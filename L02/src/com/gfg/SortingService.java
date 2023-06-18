package com.gfg;

import java.util.List;

public class SortingService {

    private SortingInterface sortingInterface;

    public SortingService(SortingInterface sortingInterface) {
        this.sortingInterface = sortingInterface;
    }

    public List<Integer> doSorting(List<Integer>  list){
        return sortingInterface.sort(list);
    }
}
