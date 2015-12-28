package servlet.user;

import net.sf.json.JSONObject;
import session.ActiveUser;
import util.ServletHelper;

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
    private ActiveUser activeUser;
    private static final String USER_SESSION_KEY = "ActiveUser";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name, password;
        name = request.getParameter("name");
        password = request.getParameter("password");
        JSONObject responseJSON = activeUser.login(name, password);

        if(responseJSON.getInt("status") == 1) {
            request.getSession().setAttribute(USER_SESSION_KEY, activeUser);  //创建用户登陆session
            if(request.getSession().getAttribute("request") != null) {
                response.sendRedirect(request.getSession().getAttribute("request").toString()
                        + "?iid=" + request.getSession().getAttribute("iid")); //跳转到登录前页面
                System.out.println(request.getSession().getAttribute("request").toString()
                        + "?iid=" + request.getSession().getAttribute("iid"));
                request.getSession().removeAttribute("request");
                request.getSession().removeAttribute("iid");
            }
        }
        ServletHelper.setJSON(response, responseJSON);
    }

    public void init() throws ServletException {
        try {
            InitialContext ic = new InitialContext();
            activeUser = (ActiveUser)ic.lookup("java:module/ActiveUserEJB");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
