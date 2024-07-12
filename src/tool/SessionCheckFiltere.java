package tool;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/student/*"})
public class SessionCheckFiltere implements Filter {

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        // 初期化が必要な場合はここに記述
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String path = req.getRequestURI().substring(req.getContextPath().length());

        // /login と 公開リソースを除外
        if (path.startsWith("/student/login") ||  path.equals("/")) {
            chain.doFilter(request, response);
            return;
        }
        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
            res.setHeader("Pragma", "no-cache"); // HTTP 1.0
            res.setDateHeader("Expires", 0); // Proxies

            res.sendRedirect(req.getContextPath() + "/student/login/login.jsp"); // ログインページへリダイレクト
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        // リソースのクリーンアップが必要な場合はここに記述
    }
}