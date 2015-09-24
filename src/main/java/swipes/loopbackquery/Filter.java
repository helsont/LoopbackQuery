package swipes.loopbackquery;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class Filter extends FieldsQuery<Filter> {

    public Where where;
    public Include[] include;
    public String order;
    public Integer offset;
    public Integer limit;

    private Filter() { }

    @Override
    public void field(String fieldName, Object value) {
        super.field(fieldName, value);
    }

    public static class FilterBuilder implements IBuild<Filter> {

        private Filter filter;

        Where.Builder whereBuilder;
        private ArrayList<Include.Builder> includes;
        private String order;
        private int offset;
        private int limit;

        public FilterBuilder() { }

        @Override
        public Filter build() {
            filter = new Filter();

            if(whereBuilder != null) {
                filter.where = whereBuilder.build();
            }

            if(this.includes != null && this.includes.size() > 0) {
                filter.include = new Include[includes.size()];
                for (int i = 0; i < includes.size(); i++) {
                    filter.include[i] = includes.get(i).build();
                }
            }

            if(this.order != null && !order.isEmpty()) {
                filter.order = this.order;
            }

            if(this.offset > 0) {
                filter.offset = this.offset;
            }

            if(this.limit > 0) {
                filter.limit = this.limit;
            }
            return filter;
        }

        public FilterBuilder include(Include.Builder includeBuilder) {
            if(includes == null) {
                this.includes = new ArrayList<>();
            }
            this.includes.add(includeBuilder);
            return this;
        }

        public FilterBuilder where(Where.Builder whereBuilder) {
            if(this.whereBuilder == null) {
                this.whereBuilder = whereBuilder;
            } else {
                mergeSafe(this.whereBuilder, whereBuilder);
                this.whereBuilder.fields.putAll(whereBuilder.fields);
            }
            return this;
        }

        public FilterBuilder offset(int offset) {
            this.offset = offset;
            return this;
        }

        public FilterBuilder limit(int limit) {
            this.limit = limit;
            return this;
        }

        public FilterBuilder order(String order) {
            this.order = order;
            return this;
        }
    }

    public static <T> void mergeSafe (T a, T b) {
        try {
            merge(a, b);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static <T> void merge (T a, T b) throws NoSuchFieldException, IllegalAccessException {
        Field[] fields = a.getClass().getDeclaredFields();
        for(int i = 0; i < fields.length; i++) {
            Field current = fields[i];

            if(!Modifier.isPrivate(current.getModifiers())) {
                if (current.get(a) != null  && current.get(b) == null) {
                    current.set(b, current.get(a));
                } else if (current.get(a) == null  && current.get(b) != null) {
                    current.set(a, current.get(b));
                }
            }
        }
    }
}
