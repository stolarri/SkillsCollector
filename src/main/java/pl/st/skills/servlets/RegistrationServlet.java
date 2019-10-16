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
import java.io.IOException;

@WebServlet(urlPatterns = "/register")
public class RegistrationServlet extends HttpServlet {
    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        SessionFactory sessionFactory = (SessionFactory) this.getServletContext().getAttribute(HibernateInitializer.SESSION_FACTORY);
        userDao = new UserDao(sessionFactory);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        if(!userDao.isUsernameAvailable(username)) {
            req.setAttribute("error", "Nazwa użytkownika zajęta.");
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
            return;
        }
        User user = new User();
        user.setFirst_name(firstName);
        user.setLast_name(lastName);
        user.setUsername(username);
        user.setPassword(password);

        userDao.save(user);
        resp.sendRedirect("/login");
    }


}
