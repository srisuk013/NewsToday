package com.srisuk.newstoday.models;



import com.srisuk.newstoday.models.ListModel;

import java.util.List;

public class NewResponse {
    //@SerializedName("status")
    private String status ;
    private  int totalResults;
    private List<ListModel> articles;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<ListModel> getArticles() {
        return articles;
    }

    public void setArticles(List<ListModel> articles) {
        this.articles = articles;
    }
}
