package servlet.view;

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
 * Created by ZY on 2015/12/21.
 */
@WebServlet(name = "ItemView", value = "/view/item")
public class ItemViewServlet extends HttpServlet {
    private Viewer viewer;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ServletHelper.setJSON(response, viewer.getItemInfo(Integer.parseInt(request.getParameter("iid"))));
        } catch (NumberFormatException e) {
            e.printStackTrace();
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
