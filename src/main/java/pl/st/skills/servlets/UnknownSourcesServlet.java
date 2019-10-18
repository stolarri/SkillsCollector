package pl.st.skills.servlets;

import org.hibernate.SessionFactory;
import pl.st.skills.listeners.HibernateInitializer;
import pl.st.skills.model.dao.SourceDao;
import pl.st.skills.model.dao.UserDao;
import pl.st.skills.model.entity.Source;
import pl.st.skills.model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/user/unknown-sources")
public class UnknownSourcesServlet extends HttpServlet {
    SourceDao sourceDao;
    UserDao userDao;

    @Override
    public void init() throws ServletException {
        SessionFactory sessionFactory = (SessionFactory) getServletContext().getAttribute(HibernateInitializer.SESSION_FACTORY);
        sourceDao = new SourceDao(sessionFactory);
        userDao = new UserDao(sessionFactory);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String username = user.getUsername();
        List<Source> knownSources = userDao.getAllKnownSources(username);
        List<Source> unknownSources = sourceDao.getAll();

        unknownSources.removeAll(knownSources);
        req.setAttribute("unknownSrcs", unknownSources);
        req.getRequestDispatcher("/WEB-INF/views/user-unknown-sources.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User loggedUser = (User) req.getSession().getAttribute("user");

        Long sourceId = Long.parseLong(req.getParameter("confirmedSource"));
        userDao.sourceConfirmation(loggedUser.getUsername(), sourceId);

        resp.sendRedirect("/user/sources");
    }
}
