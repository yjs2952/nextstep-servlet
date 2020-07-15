package next.controller;

import core.db.DataBase;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/users")
public class ListUserController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        if (!UserSessionUtils.isLogined(req.getSession())) {
            resp.sendRedirect("/users/loginForm");
            return "redirect:/users/loginForm";
        }

        req.setAttribute("users", DataBase.findAll());
        return "/user/list.jsp";
    }
}
