package com.gfg.collections;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class KeywordAnalyzerWithFrequency implements KeywordAnalyzerInterface{

//    private Map<String,Integer> dataStore2 = new HashMap<>();
    private Map<String,KeywordFrequency> dataStore;

    public KeywordAnalyzerWithFrequency() {
        this.dataStore = new HashMap<>();
    }

    @Override
    public void recordKeyword(String keyword) {
        if(dataStore.containsKey(keyword)){
           KeywordFrequency keywordFrequency = dataStore.get(keyword);
           keywordFrequency.setCount(keywordFrequency.getCount()+1);
        }
        else {
            dataStore.put(keyword,new KeywordFrequency(keyword,1));
        }
    }

    @Override
    public List<String> getAllKeyword() {
        return dataStore.keySet().stream().toList();
    }

    @Override
    public List<KeywordFrequency> getKeywordsWithFrequency() {
        List<KeywordFrequency> list = dataStore.values().stream().collect(Collectors.toList());
//        list.sort(new Comparator<KeywordFrequency>() {
//            @Override
//            public int compare(KeywordFrequency keywordFrequency, KeywordFrequency t1) {
//                return t1.getCount() - keywordFrequency.getCount();
//            }
//        });

        list.sort((keywordFrequency, t1) -> t1.getCount() - keywordFrequency.getCount());
        return list;
    }


//    public Map<String,Integer> getKeywordsWithFrequencyMap(){
//        return dataStore2;
//    }
}

/*
laptop, 1
pen, 2
mobile, 1
 */