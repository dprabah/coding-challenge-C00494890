package io.twodigits.urlshortener.model.dto;

import io.twodigits.urlshortener.model.URL;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class StatsDto {
    private List<Details> urlCounts = new ArrayList<>();

    public List<Details> getUrlCounts() {
        return urlCounts;
    }

    public void setUrlCounts(Details urlCounts) {
        this.urlCounts.add(urlCounts);
    }

    @Data
    public static class Details{
        private URL url;
        private Integer count;
        private String user;
    }
}
