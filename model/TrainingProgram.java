package model;

public class TrainingProgram {
    private String title;
    private String description;
    private String videoUrl;
    private String additionalText;
    private boolean completed;

    public TrainingProgram(String title, String description, String videoUrl, String additionalText) {
        this.title = title;
        this.description = description;
        this.videoUrl = videoUrl;
        this.additionalText = additionalText;
        this.completed = false;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getAdditionalText() {
        return additionalText;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return title;
    }
}