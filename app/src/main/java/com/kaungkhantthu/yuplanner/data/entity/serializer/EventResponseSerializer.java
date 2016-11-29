package com.kaungkhantthu.yuplanner.data.entity.serializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.kaungkhantthu.yuplanner.data.entity.EventResponse;

import java.lang.reflect.Type;

/**
 * Created by kaungkhantthu on 11/16/16.
 */

public class EventResponseSerializer implements JsonSerializer<EventResponse> {
    @Override
    public JsonElement serialize(EventResponse src, Type typeOfSrc, JsonSerializationContext context) {
        final JsonObject jsonObject = new JsonObject();

        jsonObject.add("data", context.serialize(src.getData()));
        return jsonObject;
    }
}
