package servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Category;
import model.Item;
import model.User;
import store.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class IndexServlet extends HttpServlet {

    private static final Gson GSON = new GsonBuilder().create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Item> userItemList = HbrItemStore.instOf()
                .findByUserId(req.getParameter("user_id"));
        eraseUserPasswords(userItemList);
        String json = GSON.toJson(userItemList);
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
        String catsIds = req.getParameter("itemCats");
        User user = HbrUserStore.instOf().findById(user_id);
        Item item = new Item(0, description, user);
        item.addCategories(getCategories(catsIds));
        store.add(item);
    }

    private void eraseUserPasswords(List<Item> items) {
        for (Item item : items) {
            item.getUser().setPassword("empty");
            item.getUser().setLogin("empty");
        }
        return;
    }

    private List<Category> getCategories(String cats) {
        List<Category> rsl = new ArrayList<>();
        String[] tmp = cats.split(",");
        for (String cat : tmp) {
            rsl.add(HbrCategoryStore.instOf().findById(cat));
        }
        return rsl;
    }

}
