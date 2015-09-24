package swipes.loopbackquery;

public class And extends FieldsQuery<And> {
    public Or or;

    public static class Builder extends FieldsQuery<Builder> implements IBuild<And>{
        private And and;
        private Or.Builder orBuilder;

        public Builder() { }

        @Override
        public And build() {
            and = new And();

            if(this.orBuilder != null) {
                and.or = this.orBuilder.build();
            }

            if(this.fields.size() > 0) {
                and.fields = this.fields;
            }

            return and;
        }

        public Builder or(Or.Builder orBuilder) {
            this.orBuilder = orBuilder;
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
