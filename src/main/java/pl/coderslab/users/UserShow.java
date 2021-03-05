package pl.coderslab.users;

import pl.coderslab.program.User;
import pl.coderslab.program.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/user/show")
public class UserShow extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new UserDao();
        User user = userDao.read(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("userShow", user);
        getServletContext().getRequestDispatcher("/users/show.jsp").forward(request,response);

    }

}
