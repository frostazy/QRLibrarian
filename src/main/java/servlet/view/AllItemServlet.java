package servlet.view;

import session.ItemManager;
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
 * Created by ZY on 2015/12/21.
 */
@WebServlet(name = "AllItem", value = "/view/allitem")
public class AllItemServlet extends HttpServlet {
    private ItemManager im;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONResponse.set(response, im.getAllItem());
    }

    public void init() throws ServletException {
        try {
            InitialContext ic = new InitialContext();
            im = (ItemManager)ic.lookup("java:global/QRLibrarian_Web_exploded/ItemManagerEJB!session.ItemManager");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
