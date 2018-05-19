package com.arrk.arrkandroidtest.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Kajal Sharma
 * @date 19/05/2018.
 */
public class RestResponse {

    @SerializedName("count")
    private int count;

    @SerializedName("next")
    private String next;

    @SerializedName("previous")
    private String previous;

    @SerializedName("results")
    private List<Character> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }


    public void setResults(List<Character> results) {
        this.results = results;
    }
    public List<Character> getResults() {
        return results;
    }


}