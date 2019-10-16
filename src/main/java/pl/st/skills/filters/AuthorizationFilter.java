package pl.st.skills.filters;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@WebFilter(urlPatterns = "/*")
public class AuthorizationFilter extends HttpFilter {
    Set<String> withoutAuth = new HashSet<>();
    Set<String> withAuth = new HashSet<>();

    @Override
    public void init() throws ServletException {


        withoutAuth.add("/register");
        withoutAuth.add("/login");

        withAuth.add("/user/skills");
        withAuth.add("/logout");
    }

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String path = req.getServletPath();

        if(withoutAuth.contains(path)) {
            chain.doFilter(req, res);
        } else if(withAuth.contains(path) && req.getSession().getAttribute("user") != null) {
            chain.doFilter(req, res);
        } else if(req.getSession().getAttribute("user") == null) {
            res.sendRedirect("/login");
        } else {
            res.sendError(500, "Brak autoryzacji dla ścieżki: " + path);
        }
    }
}
