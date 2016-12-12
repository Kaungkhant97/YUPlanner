package com.kaungkhantthu.yuplanner.data.entity.subjectserializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.kaungkhantthu.yuplanner.data.entity.mSubjectResponse;

import java.lang.reflect.Type;

/**
 * Created by kaungkhantthu on 12/11/16.
 */

public class SubjectResponseSerializer implements JsonSerializer<mSubjectResponse> {


    @Override
    public JsonElement serialize(mSubjectResponse src, Type typeOfSrc, JsonSerializationContext context) {
        final JsonObject jsonObject = new JsonObject();

        jsonObject.add("data", context.serialize(src.getSubjects()));
        return jsonObject;
    }
}
