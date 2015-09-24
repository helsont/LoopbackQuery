package swipes.loopbackquery;

import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

public class FieldsQuery<T> {
    protected HashMap<String, Object> fields;

    public void field(String fieldName, Object value) {
        if(this.fields == null) {
            this.fields = new HashMap<>();
        }
        fields.put(fieldName, value);
    }

    public static void addMapToJsonObjectAsObject(Map<String, Object> map, JsonObject toAdd) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            toAdd.addProperty(entry.getKey(), entry.getValue().toString());
        }
    }

    protected T add(T returnVal, String key, Object val) {
        this.field(key, val);
        return returnVal;
    }

    protected T add(T returnVal, String key, Object... vals) {
        if(vals.length % 2 != 0) {
            throw new IllegalArgumentException("Each key must have a corresponding value.");
        }
        HashMap<Object, Object> hashMap = new HashMap<>();
        for(int i = 0; i < vals.length; i+=2) {
            hashMap.put(vals[i], vals[i+1]);
        }
        this.field(key, hashMap);
        return returnVal;
    }
}
