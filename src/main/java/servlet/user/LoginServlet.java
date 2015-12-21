package servlet.user;

import net.sf.json.JSONObject;
import session.ActiveUser;
import session.UserManager;
import util.JSONResponse;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ZY on 2015/12/17.
 */
@WebServlet(name = "Login", value = "user/login")
public class LoginServlet extends HttpServlet {
    private UserManager um;
    private static final String USER_SESSION_KEY = "ActiveUser";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name, password;
        name = request.getParameter("name");
        password = request.getParameter("password");
        JSONObject responseJSON = um.login(name, password);
        if(responseJSON.getInt("status") == 1){
            try {
                InitialContext ic = new InitialContext();
                ActiveUser au = (ActiveUser) ic.lookup("java:global/QRLibrarian_Web_exploded/ActiveUserEJB!session.ActiveUser");
                request.getSession().setAttribute(USER_SESSION_KEY, au);  //创建用户session
                au.init(responseJSON.getInt("uid"), responseJSON.getString("name"));
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
        responseJSON.put("action", request.getSession().getAttribute("action"));
        responseJSON.put("actionIid", request.getSession().getAttribute("actionIid"));//tiaozhuan
        request.getSession().removeAttribute("action");
        request.getSession().removeAttribute("actionIid");
        JSONResponse.set(response, responseJSON);
    }

    public void init() throws ServletException {
        try {
            InitialContext ic = new InitialContext();
            um = (UserManager)ic.lookup("java:global/QRLibrarian_Web_exploded/UserManagerEJB!session.UserManager");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
