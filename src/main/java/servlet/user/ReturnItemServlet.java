package servlet.user;

import net.sf.json.JSONObject;
import session.ActiveUser;
import session.UserManager;
import util.JSONResponse;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Rain on 2015/12/21.
 */
@WebServlet(name = "ReturnItem", value = "/user/return")
public class ReturnItemServlet extends HttpServlet {
    private ActiveUser au;
    private UserManager um;
    private static final String USER_SESSION_KEY = "ActiveUser";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        au = (ActiveUser) request.getSession().getAttribute(USER_SESSION_KEY);
        JSONObject responseJSON = new JSONObject();
        Integer iid;
        iid = Integer.parseInt(request.getParameter("iid"));
        if(au == null) {
            //用户未登录
            RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
            request.getSession().setAttribute("action", "return");
            request.getSession().setAttribute("actionIid", iid);  //session存值
            rd.forward(request, response);  //tiaozhuan
        } else {
            //用户已登录
            responseJSON = au.returnItem(iid);
            responseJSON.put("borrowItem", um.getBorrowInfo(responseJSON.getInt("uid")));
        }
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
