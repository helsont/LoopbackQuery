package swipes.loopbackquery;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class WhereSerializer implements JsonSerializer<Where> {
    @Override
    public JsonElement serialize(Where src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        FieldsQuery.addMapToJsonObjectAsObject(src.fields, object);
        if(src.id != null && !src.id.isEmpty()) {
            object.addProperty("id", src.id);
        }
        if(src.or != null && src.or.length > 0) {
            JsonObject val = new JsonObject();
            for(int i = 0; i < src.or.length; i++) {
                Object value = src.or[i].getValue();
                JsonObject result = getJsonObject(value);
                val.add();

            }
            object.add("or", val);
        }
        return object;
    }

    private JsonObject getJsonObject(Object value) {
        JsonObject result = new JsonObject();
        if(value instanceof Query.Value) {
            Query.Value query = (Query.Value) value;

        } else if(value instanceof String){
            String field =
        } else {
            throw new IllegalArgumentException();
        }
    }

    private JsonObject getValues(Query.Value[] values) {
        for(int i = 0; i < values.length; i++) {

        }
    }

    private JsonObject getValue(Query.Value value) {
        JsonObject obj = new JsonObject();
        obj.add(value.field, getJsonObject(value.getValue()));
        return obj;
    }
}
