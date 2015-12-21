package servlet;

import session.ActiveUserUnitTest;
import session.ItemManagerUnitTest;
import session.UserManagerUnitTest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ZY on 2015/12/21.
 */
@WebServlet(name = "Test", value = "/test")
public class TestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        new ActiveUserUnitTest().main();
        new ItemManagerUnitTest().main();
        new UserManagerUnitTest().main();
    }
}
