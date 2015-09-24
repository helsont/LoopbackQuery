package swipes.loopbackquery;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Test {

    static int DEFAULT = 0, NAME_SEARCH = 1, CATEGORY = 2, BOTH = 3;

    static int offset = 40;
    static int QUERY_LIMIT = 20;

    private static void setUpGson() {
        GsonBuilder builder = new GsonBuilder()
                .registerTypeAdapter(Where.class, new WhereSerializer())
                .registerTypeAdapter(Query.Value.class, new ValueSerializer())
                .setPrettyPrinting();
        Gson gson = builder.create();
        Query.setGson(gson);
    }

    private static void test(int q) {
        Query.Builder builder = new Query.Builder().filter(new Filter.FilterBuilder()
                .order("fullName")
                .offset(offset)
                .limit(QUERY_LIMIT));

        if (q == NAME_SEARCH || q == BOTH) {
            builder.where(new Where.Builder().add("fullName", "like", "Search Name", "options", "i"));
        }
        if (q == CATEGORY || q == BOTH) {
            builder.where(new Where.Builder().add("category", "Sponsor"));
        }

        System.out.println(builder.build());
    }

    private static void test1() {
        Query q = new Query.Builder().include(new Include.Builder()
                    .relation("owner")
                    .scope(new Scope.Builder().fields("username", "emails")
                            .include(new Include.Builder()
                                    .relation("orders")
                                    .scope(new Scope.Builder()
                                            .where(new Where.Builder()
                                                    .add("orderId", 5))))))
                    .build();
        System.out.println(q);
    }

    private static void test2() {
        Query q = new Query.Builder().where(
                new Where.Builder().or(
                        new Query.Value(
                                "and",
                                new Query.Value("field1", "food"),
                                new Query.Value("field2", "bar"))
                        )
                )
                .build();
        System.out.println(q);
    }

    public static void main(String[] args) {
        setUpGson();
//        test(BOTH);
        test2();
    }

    public static void merge() {
        Foo a = new Foo();
        Foo b = new Foo("POOP");
        Filter.mergeSafe(a, b);
        System.out.println(a);
        System.out.println(b);
    }

    static class Foo {
        String name = "Yo";
        String poop;

        public Foo() {
        }

        public Foo(String poop) {
            this.poop = poop;
        }

        @Override
        public String toString() {
            return "Foo{" +
                    "name='" + name + '\'' +
                    ", poop='" + poop + '\'' +
                    '}';
        }
    }
}
