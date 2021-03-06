package next.web;

import core.db.DataBase;
import next.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/login")
public class LoginUserServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(LoginUserServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info(req.getRequestURI());
        RequestDispatcher rd = req.getRequestDispatcher("/user/login.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("{}", req.getRequestURI());
        User user = DataBase.findUserById(req.getParameter("userId"));
        log.debug("user : {}", user);

        if (user != null) {
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("user", user);
        }
        resp.sendRedirect("/");
    }
}
