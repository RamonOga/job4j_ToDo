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

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserStore store = HbrUserStore.instOf();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = store.findByName(login);

        if ( user == null || user.getId() <= 0 ) {
            req.setAttribute("error", "Введенный логин не зарегистрирован в системе.");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } else if (!user.getPassword().equals(password)) {
            req.setAttribute("error", "Не верный пароль");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } else {
            HttpSession hs = req.getSession();
            hs.setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/");

        }

    }
}
