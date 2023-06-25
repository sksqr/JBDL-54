package org.gfg;

import org.gfg.analyzer.KeywordAnalyzerInterface;
import org.gfg.analyzer.RandomKeywordAnalyzerImpl;
import org.gfg.analyzer.UniqueKeywordAnalyzerImpl;

public class KeywordAnalyzerDemo {
    public static void main(String[] args) {

        KeywordAnalyzerInterface keywordAnalyzerInterface = new RandomKeywordAnalyzerImpl();
        keywordAnalyzerInterface.recordKeyword("pen");
        keywordAnalyzerInterface.recordKeyword("laptop");
        keywordAnalyzerInterface.recordKeyword("pen");

        System.out.println(keywordAnalyzerInterface.getAllKeyword());
    }
}
