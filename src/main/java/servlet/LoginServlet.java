package servlet;

import session.Login;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ZY on 2015/12/17.
 */
public class LoginServlet extends HttpServlet {
    private Login login;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
//        JSONObject responseJSON = login.execute(name, password);
//        JSONResponse.set(response, responseJSON);
    }

    public void init() throws ServletException {
        try {
            InitialContext context = new InitialContext();
            login = (Login)context.lookup("java:module/LoginEJB");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
