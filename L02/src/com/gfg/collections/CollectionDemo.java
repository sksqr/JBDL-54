package com.gfg.collections;

public class CollectionDemo {

    public static void main(String[] args) {
//        KeywordAnalyzerInterface keywordAnalyzerInterface = new KeywordAnalyzerImpl();
//        KeywordAnalyzerInterface keywordAnalyzerInterface = new UniqueKeywordAnalyzerImpl();
        KeywordAnalyzerInterface keywordAnalyzerInterface = new KeywordAnalyzerWithFrequency();


        keywordAnalyzerInterface.recordKeyword("Laptop");
        keywordAnalyzerInterface.recordKeyword("Java");
        keywordAnalyzerInterface.recordKeyword("Mobile");
        keywordAnalyzerInterface.recordKeyword("Java");
        keywordAnalyzerInterface.recordKeyword("C++");
        keywordAnalyzerInterface.recordKeyword("Pen");
        keywordAnalyzerInterface.recordKeyword("Mobile");

        System.out.println(keywordAnalyzerInterface.getAllKeyword());

        System.out.println(keywordAnalyzerInterface.getKeywordsWithFrequency());
    }
}
