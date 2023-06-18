package com.gfg.collections;

import java.util.List;

public interface KeywordAnalyzerInterface {

    void recordKeyword(String keyword);

    List<String> getAllKeyword();

    default List<KeywordFrequency> getKeywordsWithFrequency(){
        return null;
    }
}
