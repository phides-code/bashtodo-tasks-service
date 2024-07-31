package com.github.phidescode.BashtodoDynamoDBService;

public class BaseEntity {

    protected String content;
    protected String status;
    // protected long createdOn;
    protected long completedOn;

    // Jackson requires a default (no-argument) constructor to create an instance of BaseEntity during deserialization
    public BaseEntity() {
    }

    // this constructor exists so that we can call super(newEntity); in the Entity class 
    public BaseEntity(BaseEntity newBaseEntity) {
        this.content = newBaseEntity.getContent();
        this.status = newBaseEntity.getStatus();
        // this.createdOn = newBaseEntity.getCreatedOn();
        this.completedOn = newBaseEntity.getCompletedOn();
    }

    public BaseEntity(String content, String status, long completedOn) {
        // public BaseEntity(String content, String status, long createdOn, long completedOn) {
        this.content = content;
        this.status = status;
        // this.createdOn = createdOn;
        this.completedOn = completedOn;
    }

    public BaseEntity(String content) {
        this.content = content;
        this.status = "PENDING";
        // this.createdOn = Instant.now().getEpochSecond();
        this.completedOn = 0;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // public long getCreatedOn() {
    //     return createdOn;
    // }
    // public void setCreatedOn(long createdOn) {
    //     this.createdOn = createdOn;
    // }
    public long getCompletedOn() {
        return completedOn;
    }

    public void setCompletedOn(long completedOn) {
        this.completedOn = completedOn;
    }

}
