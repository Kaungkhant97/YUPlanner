package com.kaungkhantthu.yuplanner.data.entity.subjectserializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.kaungkhantthu.yuplanner.data.entity.Subject;

import java.lang.reflect.Type;

/**
 * Created by kaungkhantthu on 12/11/16.
 */

public class SubjectSerializer implements JsonSerializer<Subject> {
    @Override
    public JsonElement serialize(Subject src, Type typeOfSrc, JsonSerializationContext context) {
        final JsonObject jsonObject = new JsonObject();

        jsonObject.add("_id", context.serialize(src.getId()));
        jsonObject.add("major", context.serialize(src.getMajor()));
        jsonObject.add("year", context.serialize(src.getYear()));
        jsonObject.add("subjectname", context.serialize(src.getSubjectname()));
        jsonObject.add("subjectId", context.serialize(src.getSubjectId()));
        jsonObject.add("__v", context.serialize(src.getV()));
        jsonObject.add("timetable", context.serialize(src.getTimetable()));
        jsonObject.add("description", context.serialize(src.getDescription()));
        jsonObject.add("_class", context.serialize(src.getClass_()));
        jsonObject.add("_mtype", context.serialize(src.getMtype()));


        return jsonObject;
    }
}
