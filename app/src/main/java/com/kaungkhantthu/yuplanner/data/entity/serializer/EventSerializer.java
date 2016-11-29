package com.kaungkhantthu.yuplanner.data.entity.serializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.kaungkhantthu.yuplanner.data.entity.Event;

import java.lang.reflect.Type;

/**
 * Created by kaungkhantthu on 11/16/16.
 */

public class EventSerializer implements JsonSerializer<Event> {
    @Override
    public JsonElement serialize(Event src, Type typeOfSrc, JsonSerializationContext context) {
        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("_id", src.getId());
        jsonObject.addProperty("title", src.getTitle());
        jsonObject.addProperty("Place", src.getPlace());
        jsonObject.addProperty("Time", src.getTime());
        jsonObject.addProperty("Date", src.getDate());
        jsonObject.addProperty("publishedDate", src.getPublishedDate());
        jsonObject.addProperty("Description", src.getDescription());
        jsonObject.addProperty("byUser", src.getUsername());
        jsonObject.addProperty("byUserid", src.getUserid());


        return jsonObject;
    }
}
