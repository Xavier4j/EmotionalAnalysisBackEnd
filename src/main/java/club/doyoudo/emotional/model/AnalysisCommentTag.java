package club.doyoudo.emotional.model;

public class AnalysisCommentTag {
    private String id;

    private String phoneId;

    private String commentId;

    private Integer sentiment;

    private String adj;

    private String prop;

    private String abstractText;

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

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId == null ? null : commentId.trim();
    }

    public Integer getSentiment() {
        return sentiment;
    }

    public void setSentiment(Integer sentiment) {
        this.sentiment = sentiment;
    }

    public String getAdj() {
        return adj;
    }

    public void setAdj(String adj) {
        this.adj = adj == null ? null : adj.trim();
    }

    public String getProp() {
        return prop;
    }

    public void setProp(String prop) {
        this.prop = prop == null ? null : prop.trim();
    }

    public String getAbstractText() {
        return abstractText;
    }

    public void setAbstractText(String abstractText) {
        this.abstractText = abstractText == null ? null : abstractText.trim();
    }
}