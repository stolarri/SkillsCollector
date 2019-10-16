package pl.st.skills.servlets;

import org.hibernate.SessionFactory;
import pl.st.skills.listeners.HibernateInitializer;
import pl.st.skills.model.dao.SkillDao;
import pl.st.skills.model.dao.SourceDao;
import pl.st.skills.model.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/test")
public class TestServlet extends HttpServlet {
    private UserDao userDao;
    private SkillDao skillDao;
    private SourceDao sourceDao;

    @Override
    public void init() throws ServletException {
        SessionFactory sessionFactory = (SessionFactory) getServletContext().getAttribute(HibernateInitializer.SESSION_FACTORY);

        this.sourceDao = new SourceDao(sessionFactory);
        this.skillDao = new SkillDao(sessionFactory);
        this.userDao = new UserDao(sessionFactory);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/plain;charset-utf8");

        PrintWriter printWriter = resp.getWriter();
        printWriter.println("====Użytkownicy====");
        userDao.getAll().forEach(item -> printWriter.println(item));
        printWriter.println();
        printWriter.println("====Skille====");
        skillDao.getAll().forEach(printWriter::println);
        printWriter.println();
        printWriter.println("====Źródła====");
        sourceDao.getAll().forEach(printWriter::println);
    }
}
