package swipes.loopbackquery;

public class Scope {

    public Include include;
    public String[] fields;
    public String relation;
    public String order;
    public Where where;

    private Scope() { }

    public static class Builder implements IBuild<Scope> {

        private Scope scope;

        private Include.Builder includeBuilder;
        private Where.Builder whereBuilder;
        private String[] fields;
        private String relation;
        private String order;
        private Where where;

        public Builder() { }

        @Override
        public Scope build() {
            scope = new Scope();

            if(includeBuilder != null) {
                this.scope.include = includeBuilder.build();
            }

            if(whereBuilder != null) {
                this.scope.where = whereBuilder.build();
            }

            if(this.fields != null && this.fields.length > 0) {
                this.scope.fields = fields;
            }

            if(this.relation != null && this.relation.isEmpty()) {
                this.scope.relation = this.relation;
            }

            if(this.order != null && this.order.isEmpty()) {
                this.scope.order = this.order;
            }

            return this.scope;
        }

        public Builder where(Where.Builder whereBuilder) {
            this.whereBuilder = whereBuilder;
            return this;
        }

        public Builder fields(String...fields) {
            this.fields = fields;
            return this;
        }

        public Builder relation(String relation) {
            this.relation = relation;
            return this;
        }

        public Builder order(String order) {
            this.order = order;
            return this;
        }

        public Builder include(Include.Builder includeBuilder) {
            this.includeBuilder = includeBuilder;
            return this;
        }
    }
}
