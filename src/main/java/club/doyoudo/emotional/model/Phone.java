package club.doyoudo.emotional.model;

import java.time.LocalDateTime;

public class Phone {
    private String id;

    private String model;

    private LocalDateTime releaseTime;

    private Long comentNum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model == null ? null : model.trim();
    }

    public LocalDateTime getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(LocalDateTime releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Long getComentNum() {
        return comentNum;
    }

    public void setComentNum(Long comentNum) {
        this.comentNum = comentNum;
    }
}