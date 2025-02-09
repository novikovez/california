package org.california.dto.analysis;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.california.entity.competitor.Competitor;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AnalysisRequestDto {
    private Long id;
    private String externalId;
    private String sku;
    private double price;
    private double purchase;
    private int quantity;
    private List<Competitor> competitors;

    public List<Competitor> getCompetitors() {
        return competitors;
    }

    public void setCompetitors(List<Competitor> competitors) {
        this.competitors = competitors;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPurchase() {
        return purchase;
    }

    public void setPurchase(double purchase) {
        this.purchase = purchase;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }
}
