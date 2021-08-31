package console;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class RunConsoleTest {
    private static final Gson GSON = new GsonBuilder().create();
    public static void main(String[] args) {
        String s = "1,2,3";

        String sa = GSON.toJson(s);

        System.out.println(sa);
    }
}
