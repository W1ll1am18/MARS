package org.application.mars.MarketData.models.News;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.application.mars.MarketData.models.Objects.Insights;
import org.application.mars.MarketData.models.Objects.Publisher;

import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class News {
    @JsonProperty("amp_url")
    private String ampUrl;

    @JsonProperty("article_url")
    private String articleUrl;

    @JsonProperty("author")
    private String author;

    @JsonProperty("description")
    private String description;

    @JsonProperty("id")
    private String id;

    @JsonProperty("image_url")
    private String imageUrl;

    @JsonProperty("insights")
    private ArrayList<Insights> insights;

    @JsonProperty("keywords")
    private ArrayList<String> keywords;

    @JsonProperty("published_utc")
    private String publishedUtc;

    @JsonProperty("publisher")
    private Publisher publisher;

    @JsonProperty("tickers")
    private ArrayList<String> tickers;

    @JsonProperty("title")
    private String title;
}
