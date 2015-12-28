package util;

import net.sf.json.JSONObject;
import session.ActiveUser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * Created by ZY on 2015/12/17.
 */
abstract public class ServletHelper {
    private static final String USER_SESSION_KEY = "ActiveUser";

    public static HttpServletResponse setJSON(HttpServletResponse response,
                                              JSONObject responseJSON) throws IOException{
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append(responseJSON.toString());
        } finally {
            if (out != null) {
                out.close();
            }
        }
        return response;
    }

    public static ActiveUser checkLogin(HttpServletRequest request,
                                  HttpServletResponse response) throws ServletException, IOException {
        ActiveUser activeUser = (ActiveUser) request.getSession().getAttribute(USER_SESSION_KEY);
        if(activeUser == null) {
            //ÓÃ»§Î´µÇÂ¼£¬Ìø×ªµ½µÇÂ¼Ò³
            RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
            request.getSession().setAttribute("request", request.getRequestURL());
            request.getSession().setAttribute("iid", request.getParameter("iid"));
            rd.forward(request, response);  //Ìø×ªµ½µÇÂ¼Ò³
        }
        return activeUser;
    }
}