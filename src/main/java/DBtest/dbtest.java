//package DBtest;
//import javax.naming.InitialContext;
//import javax.sql.DataSource;
//import java.sql.*;
//
//public class dbtest {
//
//    public static void main(String[] args) {
//        Connection conn = null;
//        Statement stmt = null;
//        try{
//            InitialContext context = new InitialContext();
//            DataSource dataSource = (DataSource)context.lookup("java:/MySqlDS");
//
//            //STEP 3: Open a connection
//            System.out.println("Connecting to database...");
//            conn = dataSource.getConnection();
//
//            //STEP 4: Execute a query
//            System.out.println("Creating statement...");
//            stmt = conn.createStatement();
//            String sql;
//            sql = "SELECT ID, NAME, PASSWORD FROM USER ";
//            ResultSet rs = stmt.executeQuery(sql);
//
//            //STEP 5: Extract data from result set
//            while(rs.next()){
//                //Retrieve by column name
//                int id  = rs.getInt("ID");
//                String name = rs.getString("NAME");
//                String pswd = rs.getString("PASSWORD");
//
//                //Display values
//                System.out.print("ID: " + id);
//                System.out.print(", NAME: " + name);
//                System.out.print(", PASSWORD: " + pswd);
//            }
//            //STEP 6: Clean-up environment
//            rs.close();
//            stmt.close();
//            conn.close();
//        }catch(SQLException se){
//            //Handle errors for JDBC
//            se.printStackTrace();
//        }catch(Exception e){
//            //Handle errors for Class.forName
//            e.printStackTrace();
//        }finally{
//            //finally block used to close resources
//            try{
//                if(stmt!=null)
//                    stmt.close();
//            }catch(SQLException se2){
//            }// nothing we can do
//            try{
//                if(conn!=null)
//                    conn.close();
//            }catch(SQLException se){
//                se.printStackTrace();
//            }//end finally try
//        }//end try
//        System.out.println("Goodbye!");
//    }//end main
//}//end FirstExample