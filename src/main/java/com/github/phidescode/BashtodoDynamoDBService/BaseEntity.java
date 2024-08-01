package com.github.phidescode.BashtodoDynamoDBService;

import java.time.Instant;

public class BaseEntity {

    protected String content;
    protected String taskStatus;
    protected long completedOn;

    // Jackson requires a default (no-argument) constructor to create an instance of BaseEntity during deserialization
    public BaseEntity() {
    }

    // this constructor exists so that we can call super(newEntity); in the Entity class 
    public BaseEntity(BaseEntity newBaseEntity) {
        this.content = newBaseEntity.getContent();
        this.taskStatus = newBaseEntity.getTaskStatus();
        this.completedOn = this.taskStatus.equals("COMPLETED") ? Instant.now().getEpochSecond() : 0;
    }

    public BaseEntity(String content, String taskStatus, long completedOn) {
        this.content = content;
        this.taskStatus = taskStatus;
        this.completedOn = completedOn;
    }

    public BaseEntity(String content) {
        this.content = content;
        this.taskStatus = "PENDING";
        this.completedOn = 0;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public long getCompletedOn() {
        return completedOn;
    }

    public void setCompletedOn(long completedOn) {
        this.completedOn = completedOn;
    }
}
