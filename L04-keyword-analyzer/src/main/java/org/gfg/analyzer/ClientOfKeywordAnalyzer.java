package org.gfg.analyzer;

import java.util.List;
// by Dev A
public class ClientOfKeywordAnalyzer {

    private KeywordAnalyzerInterface keywordAnalyzerInterface;

    public ClientOfKeywordAnalyzer(KeywordAnalyzerInterface keywordAnalyzerInterface) {
        this.keywordAnalyzerInterface = keywordAnalyzerInterface;
    }

    public void recordKeyword(String keyword){
        keywordAnalyzerInterface.recordKeyword(keyword);
    }

    public List<String> getAllKeyword(){
        return keywordAnalyzerInterface.getAllKeyword();
    }

    //other methods

}
