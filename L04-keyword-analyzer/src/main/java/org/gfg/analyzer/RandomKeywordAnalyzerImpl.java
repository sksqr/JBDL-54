package org.gfg.analyzer;

import java.util.ArrayList;
import java.util.List;

public class RandomKeywordAnalyzerImpl implements KeywordAnalyzerInterface{

    private List<String> dataStore;

    public RandomKeywordAnalyzerImpl() {
        dataStore = new ArrayList<>();
    }

    @Override
    public void recordKeyword(String keyword) {
        dataStore.add(keyword);
    }

    @Override
    public List<String> getAllKeyword() {
        return dataStore;
    }
}
