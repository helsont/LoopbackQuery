package swipes.loopbackquery;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.HashMap;

public class Query {

    public Filter filter;
    public Include include;
    private static Gson gson;

    @Override
    public String toString() {
        return getGson().toJson(this);
    }

    public static void setGson(Gson gson) {
        Query.gson = gson;
    }

    public static Gson getGson() {
        if(gson == null) {
            throw new IllegalStateException("Gson not set using Query.setGson");
        }
        return gson;
    }

    public static class Builder implements IBuild<Query> {
        private Query query;
        private Include.Builder include;
        private Filter.FilterBuilder filterBuilder;
        private And.Builder andBuilder;
        private Or.Builder orBuilder;

        public Builder() {

        }

        public Builder filter(Filter.FilterBuilder filterBuilder) {
            this.filterBuilder = filterBuilder;
            return this;
        }

        public Builder where(Where.Builder where) {
            if(this.filterBuilder == null) {
                this.filterBuilder = new Filter.FilterBuilder();
            }
            this.filterBuilder.where(where);

            return this;
        }

        public Builder include(Include.Builder include) {
            this.include = include;
            return this;
        }

        @Override
        public Query build() {
            query = new Query();

            if(filterBuilder != null) {
                query.filter = filterBuilder.build();
            }

            if(this.include != null) {
                query.include = this.include.build();
            }

            return query;
        }
    }

    public static class Value {
        private HashMap<String, Object> fields;

        private Value() {
            fields = new HashMap<>();
        }

        public Value(String field, String value) {
            this();
            fields.put(field, value);
        }

        public Value(String field, Value...values) {
            this();


        }
    }
}
