package servlet;

import model.User;
import store.HbrUserStore;
import store.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserStore store = HbrUserStore.instOf();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user =  store.findByName(login);

        if (user.getId() <= 0) {
            HttpSession sc = req.getSession();
            user = new User(login, password);
            store.add(user);
            sc.setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        } else {
            req.setAttribute("error", "Введенный пользовтель уже зарегистрирован");
            req.getRequestDispatcher("reg.jsp").forward(req, resp);
        }
    }
    }
