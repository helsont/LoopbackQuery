package swipes.loopbackquery;

public class Or {

    public String[] fields;

    public static class Builder extends FieldsQuery<Builder> implements IBuild<Or>{
        private Or or;
        private And.Builder andBuilder;

        public Builder() { }

        @Override
        public Or build() {
            or = new Or();

            if (this.fields.size() > 0) {

            }

            return or;
        }

        public Builder and(And.Builder andBuilder) {
            this.andBuilder = andBuilder;
            return this;
        }

        public Builder add(String key, Object val) {
            return add(this, key, val);
        }

        public Builder add(String key, Object... vals) {
            return add(this, key, vals);
        }
    }
}
