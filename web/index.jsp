<%--
  Created by IntelliJ IDEA.
  User: ZY
  Date: 2015/12/9
  Time: 18:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title></title>
  </head>
  <body>
    <form name="f1" action="servlet/CalculatorServlet" method="post">
      <table>
        <tr>
          <td>number1:<input type="text" name="n1"/></td>
        </tr>
        <tr>
          <td>number2:<input type="text" name="n2"/></td>
        </tr>
        <tr>
          <td><input type="submit" name="Submit" value="add"/><input type="submit" name="Submit" value="substract"/></td>
        </tr>
      </table>
    </form>
    <form   method="post"   action= "test"   name= "test">
      <input   type= "submit" name="button"/>
    </form>
  </body>
</html>
