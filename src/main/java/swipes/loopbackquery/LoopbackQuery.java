package swipes.loopbackquery;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by Helson Taveras on 9/15/15.
 */
public class LoopbackQuery {

    private static LoopbackQuery loopbackQuery = new LoopbackQuery();
    private GsonBuilder gsonBuilder;
    private Gson gson;

    public static LoopbackQuery get() {
        return loopbackQuery;
    }

    public static void setGsonBuilder(GsonBuilder builder) {
        loopbackQuery.gsonBuilder = builder;
    }

    public void build() {
        if (loopbackQuery.gsonBuilder == null) {
            loopbackQuery.gsonBuilder = new GsonBuilder()
                    .setPrettyPrinting();
        }
        loopbackQuery.gsonBuilder
                .registerTypeAdapter(Where.class, new WhereSerializer())
                .registerTypeAdapter(Query.Value.class, new ValueSerializer());
        gson = loopbackQuery.gsonBuilder.create();
        Query.setGson(gson);
    }
}
