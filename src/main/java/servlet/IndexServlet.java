package servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Item;
import model.User;
import store.HbrItemStore;
import store.HbrService;
import store.HbrUserStore;
import store.ItemStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IndexServlet extends HttpServlet {

    private static final Gson GSON = new GsonBuilder().create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ItemStore store = HbrItemStore.instOf();
        String json = "[" + GSON.toJson(getJSON(store.findAll())) + "]";
        OutputStream out = resp.getOutputStream();
        out.write(json.getBytes(StandardCharsets.UTF_8));
        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ItemStore store = HbrItemStore.instOf();
        String description = req.getParameter("description");
        String user_id = req.getParameter("user_id");
        User user = HbrUserStore.instOf().findById(user_id);
        store.add(new Item(0, description, user));
    }

    private Map<String, String> getJSON(List<Item> items) {
        Map<String, String> rsl = new HashMap<>();
        for (Item item : items) {
            rsl.put("id", String.valueOf(item.getId()));
            rsl.put("description", item.getDescription());
            rsl.put("created", String.valueOf(item.getCreated()));
            rsl.put("done", String.valueOf(item.isDone()));
            rsl.put("user_id", String.valueOf(item.getUser().getId()));
        }
        return rsl;
    }

}
