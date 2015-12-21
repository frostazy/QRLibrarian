package servlet.user;

import session.ActiveUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Rain on 2015/12/21.
 */
@WebServlet(name = "BorrowItem", value = "/user/borrow")
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
            Integer iid;
            iid = Integer.parseInt(request.getParameter("iid"));
            au.borrowItem(iid);
        }
    }

    public void init() throws ServletException {
    }
}
