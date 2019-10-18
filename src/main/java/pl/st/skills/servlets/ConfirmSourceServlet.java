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

@WebServlet(urlPatterns = "/sources/confirm")
public class ConfirmSourceServlet extends HttpServlet {
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

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String idStr = req.getParameter("sourceID");
        Long id = Long.parseLong(idStr);
        req.setAttribute("Id", id);
        List<Source> sources = sourceDao.getAll();
        req.setAttribute("sources", sources);
        req.getRequestDispatcher("/WEB-INF/views/confirm-source.jsp").forward(req,resp);
    }
}
