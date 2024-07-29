package com.github.phidescode.BashtodoDynamoDBService;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import software.amazon.awssdk.services.dynamodb.model.AttributeAction;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.AttributeValueUpdate;

public class EntityUtils {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    public static BaseEntity validateRequestBody(String requestBody) throws JsonMappingException, JsonProcessingException {
        if (requestBody == null || requestBody.isEmpty()) {
            throw new ClassCastException("Invalid data format");
        }

        JsonNode jsonNode = objectMapper.readTree(requestBody);

        if (!jsonNode.has("content") || !jsonNode.get("content").isTextual()) {
            throw new ClassCastException("Invalid data format");
        }

        String content = jsonNode.get("content").asText();

        // return objectMapper.treeToValue(jsonNode, BaseEntity.class);
        return new BaseEntity(content);
    }

    public static Entity getEntityFromDBItem(Map<String, AttributeValue> item) {
        String itemId = item.get("id").s();
        String itemContent = item.get("content").s();
        String itemStatus = item.get("status").s();
        int itemCreatedOn = Integer.parseInt(item.get("createdOn").n());
        int itemCompletedOn = Integer.parseInt(item.get("completedOn").n());

        return new Entity(itemId, new BaseEntity(itemContent, itemStatus, itemCreatedOn, itemCompletedOn));
    }

    public static HashMap<String, AttributeValueUpdate> getUpdatedValues(BaseEntity entity) {
        HashMap<String, AttributeValueUpdate> updatedValues = new HashMap<>();

        updatedValues.put("content", AttributeValueUpdate.builder()
                .value(AttributeValue.builder()
                        .s(entity.getContent())
                        .build())
                .action(AttributeAction.PUT)
                .build());

        updatedValues.put("status", AttributeValueUpdate.builder()
                .value(AttributeValue.builder()
                        .s(entity.getStatus())
                        .build())
                .action(AttributeAction.PUT)
                .build());

        updatedValues.put("createdOn", AttributeValueUpdate.builder()
                .value(AttributeValue.builder()
                        .n(entity.getCreatedOn() + "")
                        .build())
                .action(AttributeAction.PUT)
                .build());

        updatedValues.put("completedOn", AttributeValueUpdate.builder()
                .value(AttributeValue.builder()
                        .n(entity.getCompletedOn() + "")
                        .build())
                .action(AttributeAction.PUT)
                .build());

        return updatedValues;
    }

    public static HashMap<String, AttributeValue> getItemValues(Entity entity) {
        HashMap<String, AttributeValue> itemValues = new HashMap<>();

        itemValues.put("id", AttributeValue.builder()
                .s(entity.getId())
                .build());

        itemValues.put("content", AttributeValue.builder()
                .s(entity.getContent())
                .build());

        itemValues.put("status", AttributeValue.builder()
                .s(entity.getStatus())
                .build());

        itemValues.put("createdOn", AttributeValue.builder()
                .n(entity.getCreatedOn() + "")
                .build());

        itemValues.put("compeltedOn", AttributeValue.builder()
                .n(entity.getCompletedOn() + "")
                .build());

        return itemValues;
    }
}
