package club.doyoudo.emotional.model;

import java.time.LocalDateTime;

public class AnalysisSentiment {
    private String id;

    private String phoneId;

    private LocalDateTime createTime;

    private Integer sentiment;

    private Double positiveProb;

    private Double negativeProb;

    private Double confidence;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(String phoneId) {
        this.phoneId = phoneId == null ? null : phoneId.trim();
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Integer getSentiment() {
        return sentiment;
    }

    public void setSentiment(Integer sentiment) {
        this.sentiment = sentiment;
    }

    public Double getPositiveProb() {
        return positiveProb;
    }

    public void setPositiveProb(Double positiveProb) {
        this.positiveProb = positiveProb;
    }

    public Double getNegativeProb() {
        return negativeProb;
    }

    public void setNegativeProb(Double negativeProb) {
        this.negativeProb = negativeProb;
    }

    public Double getConfidence() {
        return confidence;
    }

    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }
}