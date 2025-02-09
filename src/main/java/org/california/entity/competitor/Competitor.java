package org.california.entity.competitor;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "competitors")
public class Competitor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String site;
    @Column(nullable = false)
    private String url;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false)
    private boolean relevant;
    @Column(nullable = false)
    private int position;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private Long analysis_id;

    public Competitor(
            Long id,
            String site,
            String url,
            double price,
            boolean relevant,
            int position,
            LocalDateTime createdAt,
            Long analysis_id
    ) {
        this.id = id;
        this.site = site;
        this.url = url;
        this.price = price;
        this.relevant = relevant;
        this.position = position;
        this.createdAt = createdAt;
        this.analysis_id = analysis_id;
    }

    public Long getAnalysisId() {
        return analysis_id;
    }

    public Competitor setAnalysisId(Long analysisId) {
        this.analysis_id = analysisId;
        return this;
    }

    public Competitor() {
    }

    public Long getId() {
        return id;
    }

    public Competitor setId(Long id) {
        this.id = id;
        return this;
    }

    public String getSite() {
        return site;
    }

    public Competitor setSite(String site) {
        this.site = site;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Competitor setUrl(String url) {
        this.url = url;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public Competitor setPrice(double price) {
        this.price = price;
        return this;
    }

    public boolean isRelevant() {
        return relevant;
    }

    public Competitor setRelevant(boolean relevant) {
        this.relevant = relevant;
        return this;
    }

    public int getPosition() {
        return position;
    }

    public Competitor setPosition(int position) {
        this.position = position;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Competitor setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }
}
