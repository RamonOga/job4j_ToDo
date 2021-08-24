package filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest sReq, ServletResponse sResp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) sReq;
        HttpServletResponse resp = (HttpServletResponse) sResp;
        String url = req.getRequestURI();
        if (url.endsWith("login.do") || url.endsWith("reg.do")) {
            chain.doFilter(sReq, sResp);
            return;
        }
        if (req.getSession().getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }
        chain.doFilter(sReq, sResp);
    }

    @Override
    public void destroy() {
    }
}
