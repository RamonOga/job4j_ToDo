package servlet;

import store.HbrItemStore;
import store.HbrService;
import store.ItemStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DoneServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ItemStore store = HbrItemStore.instOf();
        String id = req.getParameter("id");
        store.done(id);
    }
}
