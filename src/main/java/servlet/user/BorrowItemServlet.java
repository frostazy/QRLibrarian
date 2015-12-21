package servlet.user;

import net.sf.json.JSONObject;
import session.ActiveUser;
import session.UserManager;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Rain on 2015/12/21.
 */
@WebServlet("/user/borrow")
public class BorrowItemServlet extends HttpServlet {
    private ActiveUser au;
    private static final String CART_SESSION_KEY = "shoppingCart";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        au = (ActiveUser) request.getSession().getAttribute("CART_SESSION_KEY");
        if(au == null) {
            //用户未登录
        } else {
            //用户已登录
            Integer uid, iid ;
            uid = au.getUid();
            iid = Integer.parseInt(request.getParameter("iid"));
            //JSONObject responseJSON = um.login(name, password);
        }
    }

    public void init() throws ServletException {
    }
}
