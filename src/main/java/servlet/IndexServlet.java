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

public class IndexServlet extends HttpServlet {

    private static final Gson GSON = new GsonBuilder().create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ItemStore store = HbrItemStore.instOf();
        String json = GSON.toJson(store.findAll());
        OutputStream out = resp.getOutputStream();
        out.write(json.getBytes(StandardCharsets.UTF_8));
        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ItemStore store = HbrItemStore.instOf();
        String description = req.getParameter("description");
        String user_id = req.getParameter("description");
        User user = HbrUserStore.instOf().findById(user_id);
        store.add(new Item(0, description, user));
    }
}
