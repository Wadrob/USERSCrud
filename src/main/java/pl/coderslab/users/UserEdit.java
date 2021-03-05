package pl.coderslab.users;

import pl.coderslab.program.User;
import pl.coderslab.program.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/user/edit")
public class UserEdit extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserDao userDao = new UserDao();
        User user = userDao.read(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("user", user);
        session.setAttribute("user", user);
        getServletContext().getRequestDispatcher("/users/edit.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new UserDao();
        User user = (User) request.getSession().getAttribute("user");
        user.setUsername(request.getParameter("userName"));
        user.setPassword(request.getParameter("userPassword"));
        user.setEmail(request.getParameter("userEmail"));
        userDao.update(user);
        response.sendRedirect("/user/list");
    }
}
