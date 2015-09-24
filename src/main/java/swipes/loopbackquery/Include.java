package swipes.loopbackquery;

public class Include {

    public Scope scope;
    public String relation;
    public String order;

    private Include() { }

    public static class Builder implements IBuild<Include> {

        private Include include;

        private Scope.Builder scopeBuilder;
        private String relation;
        private String order;

        public Builder() { }

        @Override
        public Include build() {
            include = new Include();

            if(scopeBuilder != null) {
                include.scope = scopeBuilder.build();
            }

            if(this.order != null && !this.order.isEmpty()) {
                include.order = this.order;
            }

            if(this.relation != null && !this.relation.isEmpty()) {
                include.relation = this.relation;
            }

            return include;
        }

        public Builder scope(Scope.Builder scopeBuilder) {
            this.scopeBuilder = scopeBuilder;
            return this;
        }

        public Builder order(String order) {
            this.order = order;
            return this;
        }

        public Builder relation(String relation) {
            this.relation = relation;
            return this;
        }
    }
}
