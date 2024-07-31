package com.github.phidescode.BashtodoDynamoDBService;

import java.time.Instant;
import java.util.UUID;

public class Entity extends BaseEntity {

    private String id;
    private long createdOn;

    public Entity(BaseEntity newEntity) {
        super(newEntity);
        this.id = UUID.randomUUID().toString();
        this.completedOn = Instant.now().getEpochSecond();
    }

    public Entity(String id, long createdOn, BaseEntity newEntity) {
        super(newEntity);
        this.id = id;
        this.createdOn = createdOn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(long createdOn) {
        this.createdOn = createdOn;
    }
}
