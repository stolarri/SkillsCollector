package pl.st.skills.servlets;

import org.hibernate.SessionFactory;
import pl.st.skills.listeners.HibernateInitializer;
import pl.st.skills.model.dao.UserDao;
import pl.st.skills.model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        SessionFactory sessionFactory = (SessionFactory) this.getServletContext().getAttribute(HibernateInitializer.SESSION_FACTORY);
        userDao = new UserDao(sessionFactory);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        List<User> result = userDao.getAllByUsernameAndPassword(username, password);
        if(result.isEmpty()) {
            req.setAttribute("error", "Błędne dane logowania");
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
            return;
        }


        req.getSession().invalidate();
        HttpSession httpSession = req.getSession(true);
        User user = result.iterator().next();
        httpSession.setAttribute("user", user);
        resp.sendRedirect("/user/skills");
    }
}
