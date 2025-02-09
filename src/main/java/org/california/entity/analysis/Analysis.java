package org.california.entity.analysis;

import jakarta.persistence.*;
import org.california.entity.competitor.Competitor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "analysis")
public class Analysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "external_id")
    private String externalId;
    @Column(nullable = false, name = "sku")
    private String sku;
    @Column(nullable = false, name = "price")
    private double price;
    @Column(nullable = false, name = "purchase")
    private double purchase;
    @Column(nullable = false, name = "quantity")
    private int quantity;
    @Column(nullable = false, name = "created_at")
    private LocalDateTime createdAt;
    @Transient
    private List<Competitor> competitor;

    public Analysis(
            Long id,
            String externalId,
            String sku,
            double price,
            double purchase,
            int quantity,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.externalId = externalId;
        this.sku = sku;
        this.price = price;
        this.purchase = purchase;
        this.quantity = quantity;
        this.createdAt = createdAt;
    }

    public Analysis() {}

    public List<Competitor> getCompetitor() {
        return competitor;
    }

    public Analysis setCompetitor(List<Competitor> competitor) {
        this.competitor = competitor;
        return this;
    }

    public String getSku() {
        return sku;
    }

    public Analysis setSku(String sku) {
        this.sku = sku;
        return this;
    }

    public Analysis setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getId() {
        return id;
    }

    public String getExternalId() {
        return externalId;
    }

    public Analysis setExternalId(String externalId) {
        this.externalId = externalId;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public Analysis setPrice(double price) {
        this.price = price;
        return this;
    }

    public double getPurchase() {
        return purchase;
    }

    public Analysis setPurchase(double purchase) {
        this.purchase = purchase;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public Analysis setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Analysis setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }
}
