package org.yara.movies.data.remote.model.response.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchList {

    @SerializedName("Search")
    @Expose
    public List<Search> search;

    @SerializedName("totalResults")
    @Expose
    public String totalResults;

    @SerializedName("Response")
    @Expose
    public String response;

    public SearchList(List<Search> search, String totalResults, String response) {
        this.search = search;
        this.totalResults = totalResults;
        this.response = response;
    }

    public SearchList() {
    }

    public List<Search> getSearch() {
        return search;
    }

    public void setSearch(List<Search> search) {
        this.search = search;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
