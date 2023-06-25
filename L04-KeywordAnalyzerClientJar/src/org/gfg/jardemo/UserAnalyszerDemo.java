package org.gfg.jardemo;

import org.gfg.keyword.KeywordAnalyzerInterface;
import org.gfg.keyword.UniqueKeywordAnalyzerImpl;

public class UserAnalyszerDemo {

    public static void main(String[] args) {
        KeywordAnalyzerInterface keywordAnalyzerInterface = new UniqueKeywordAnalyzerImpl();

        keywordAnalyzerInterface.recordKeyword("Pen");
        keywordAnalyzerInterface.recordKeyword("Pen");
        keywordAnalyzerInterface.recordKeyword("Laptop");

        System.out.println(keywordAnalyzerInterface.getAllKeyword());
    }
}
