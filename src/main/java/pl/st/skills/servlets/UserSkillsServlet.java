package pl.st.skills.servlets;

import org.hibernate.SessionFactory;
import pl.st.skills.listeners.HibernateInitializer;
import pl.st.skills.model.dao.UserDao;
import pl.st.skills.model.entity.Skill;
import pl.st.skills.model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@WebServlet(urlPatterns = "/user/skills")
public class UserSkillsServlet extends HttpServlet {
    UserDao userDao;

    @Override
    public void init() throws ServletException {
        SessionFactory sessionFactory = (SessionFactory) getServletContext().getAttribute(HibernateInitializer.SESSION_FACTORY);
        userDao = new UserDao(sessionFactory);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User loggedUser = (User) req.getSession().getAttribute("user");
        String username = loggedUser.getUsername();
        List<Skill> userSkills = userDao.getAllUserSkills(username);
        Map<Skill, Integer> skillsMap = new TreeMap<>(Comparator.comparing(Skill::getName));
        userSkills.forEach(skill -> skillsMap.merge(skill, 1, (initVal, newVal) -> initVal + newVal));

        req.setAttribute("skills", skillsMap);

        req.getRequestDispatcher("/WEB-INF/views/user-skills.jsp").forward(req, resp);
    }
}
