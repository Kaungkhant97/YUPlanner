package com.kaungkhantthu.yuplanner.data.entity.subjectserializer;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.kaungkhantthu.yuplanner.data.entity.period;

import java.lang.reflect.Type;

import io.realm.RealmList;

/**
 * Created by kaungkhantthu on 12/11/16.
 */

public class periodSerializer implements JsonSerializer<RealmList<period>>,JsonDeserializer<RealmList<period>> {
    @Override
    public JsonElement serialize(RealmList<period> src, Type typeOfSrc, JsonSerializationContext context) {
        JsonArray ja = new JsonArray();
        for (period tag : src) {
            ja.add(context.serialize(tag.getP()));
        }
        return ja;
    }

    @Override
    public RealmList<period> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

            RealmList<period> periods = new RealmList<>();
            JsonArray ja = json.getAsJsonArray();
            for (JsonElement je : ja) {
                int e = context.deserialize(je, Integer.class);
                periods.add(new period(e));
            }
            return periods;
        }



}
