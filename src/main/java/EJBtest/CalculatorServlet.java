package EJBtest;

import DBtest.User;
import DBtest.UserManager;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by ZY on 2015/12/17.
 */
public class CalculatorServlet extends HttpServlet {
    private Calculator calculator;
    public Calculator getCalculator() {
        return calculator;
    }
    public void setCalculator(Calculator calculator) {
        this.calculator = calculator;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String submit=request.getParameter("Submit");
        int num1=Integer.parseInt(request.getParameter("n1"));
        int num2=Integer.parseInt(request.getParameter("n2"));

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<HTML>");
        out.println("  <HEAD><TITLE>A CalculatorServlet</TITLE></HEAD>");
        out.println("  <BODY>");
        int result=0;
        if(submit.equals("add")){
            result=calculator.add(num1, num2);
        }else{
            result=calculator.substact(num1,num2);
        }
        out.println(num1+" "+submit+" "+num2+ "= "+result);
        out.println("  </BODY>");
        out.println("</HTML>");
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public void init() throws ServletException {
        try {
            InitialContext context=new InitialContext();
            Calculator calculator=(Calculator)context.lookup("java:module/CalculatorEJB");
            setCalculator(calculator);
            UserManager userManager=(UserManager)context.lookup("java:module/UserManagerEJB");
            User user=new User();
            user.setName("2");
            user.setPassword("3");
            userManager.addUser(user);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
