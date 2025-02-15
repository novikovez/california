package org.california.dto.competitor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.california.entity.competitor.Competitor;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CompetitorRequestDto {
    private Long id;
    @JsonProperty("analysis_id")
    private Long analysis_id;
    private String site;
    private String url;
    private double price;
    private boolean relevant;
    private int position;
    private String productName;


    public CompetitorRequestDto setProduct(String product) {
        this.productName = product;
        return this;
    }

    public CompetitorRequestDto setId(Long id) {
        this.id = id;
        return this;
    }

    public CompetitorRequestDto setAnalysis_id(Long analysis_id) {
        this.analysis_id = analysis_id;
        return this;
    }

    public String getProduct() {
        return productName;
    }

    public Long getId() {
        return id;
    }


    public Long getAnalysisId() {
        return analysis_id;
    }

    public CompetitorRequestDto setAnalysisId(Long analysis_id) {
        this.analysis_id = analysis_id;
        return this;
    }

    public String getSite() {
        return site;
    }

    public CompetitorRequestDto setSite(String site) {
        this.site = site;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public CompetitorRequestDto setUrl(String url) {
        this.url = url;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public CompetitorRequestDto setPrice(double price) {
        this.price = price;
        return this;
    }

    public boolean isRelevant() {
        return relevant;
    }

    public CompetitorRequestDto setRelevant(boolean relevant) {
        this.relevant = relevant;
        return this;
    }

    public int getPosition() {
        return position;
    }

    public CompetitorRequestDto setPosition(int position) {
        this.position = position;
        return this;
    }
}
