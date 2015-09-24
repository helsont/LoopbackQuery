package swipes.loopbackquery;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class ValueSerializer implements JsonSerializer<Query.Value> {
    @Override
    public JsonElement serialize(Query.Value src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();
//        object.addProperty(src.field, src.value);
        return object;
    }
}