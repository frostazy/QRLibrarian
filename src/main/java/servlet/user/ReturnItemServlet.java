package servlet.user;

import net.sf.json.JSONObject;
import session.ActiveUser;
import session.Viewer;
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
 * Created by Rain on 2015/12/21.
 */
@WebServlet(name = "ReturnItem", value = "/user/return")
public class ReturnItemServlet extends HttpServlet {
    private ActiveUser activeUser;
    private Viewer viewer;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        activeUser = ServletHelper.checkLogin(request, response);
        if(activeUser != null) {
            //用户已登录
            try {
                JSONObject responseJSON;
                responseJSON = activeUser.returnItem(Integer.parseInt(request.getParameter("iid")));
                responseJSON.put("borrowItem", viewer.getUserBorrowInfo(responseJSON.getInt("uid")));
                ServletHelper.setJSON(response, responseJSON);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }

    public void init() throws ServletException {
        try {
            InitialContext ic = new InitialContext();
            viewer = (Viewer)ic.lookup("java:module/ViewerEJB");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
