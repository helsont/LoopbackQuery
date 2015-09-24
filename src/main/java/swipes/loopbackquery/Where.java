package swipes.loopbackquery;

public class Where extends FieldsQuery {

    public String id;
    public Query.Value[] or;

    private Where() { }

    public static class Builder extends FieldsQuery<Builder> implements IBuild<Where> {

        private Where where;
        private String id;
        private Query.Value[] or;

        public Builder() { }

        @Override
        public Where build() {
            where = new Where();

            if(this.id != null && !id.isEmpty()) {
                where.id = this.id;
            }

            if(this.fields.size() > 0) {
                where.fields = this.fields;
            }

            if(this.or != null && this.or.length > 0) {
                where.or = this.or;
            }

            return where;
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder add(String key, Object val) {
            return add(this, key, val);
        }

        public Builder or(String key, Object val) {
            if(this.or == null && or.length == 0) {
                or = new Query.Value[1];
            } else {
                throw new IllegalStateException();
            }
            or[0] = new Query.Value(key, val.toString());

            return this;
        }

        public Builder and(Query.Value[] values) {
            return this;
        }

        public Builder or(Query.Value value) {
            return or(new Query.Value[] {value});
        }

        public Builder or(Query.Value[] value) {
            this.or = value;
            return this;
        }

        public Builder add(String key, Object... vals) {
            return add(this, key, vals);
        }
    }
}
