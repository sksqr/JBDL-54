package org.gfg.analyzer;

public class KeywordFrequency {

    private String keyword;

    private Integer count;

    public KeywordFrequency(String keyword, Integer count) {
        this.keyword = keyword;
        this.count = count;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "KeywordFrequency{" +
                "keyword='" + keyword + '\'' +
                ", count=" + count +
                '}';
    }
}
