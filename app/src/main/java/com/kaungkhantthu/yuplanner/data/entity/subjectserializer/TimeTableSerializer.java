package com.kaungkhantthu.yuplanner.data.entity.subjectserializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.kaungkhantthu.yuplanner.data.entity.Timetable;

import java.lang.reflect.Type;

/**
 * Created by kaungkhantthu on 12/11/16.
 */

public class TimeTableSerializer implements JsonSerializer<Timetable> {
    @Override
    public JsonElement serialize(Timetable src, Type typeOfSrc, JsonSerializationContext context) {
        final JsonObject jsonObject = new JsonObject();

        jsonObject.add("_id", context.serialize(src.getId()));
        jsonObject.add("day", context.serialize(src.getDay()));
        jsonObject.add("period", context.serialize(src.getPeriod()));


        return jsonObject;
    }
}
