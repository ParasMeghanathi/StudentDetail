
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Data extends HttpServlet {

    public Connection con;
     Statement st;
      PreparedStatement pst;
      ResultSet rs;
     String nm,email;
     int contact;
                    
     
    public void init(){
       
        
      /*try {
           Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","");
           
             st=con.createStatement();
        
          
          
         } catch (SQLException ex) {
          
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
            
            System.out.println(ex);
        }*/
       
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            String nm=request.getParameter("name");
            String c=request.getParameter("contact");
              String e1=request.getParameter("email");
            
            try {
           Class.forName("oracle.jdbc.driver.OracleDriver");
            //con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","");
            con=DriverManager.getConnection("jdbc:oracle:thin:@student.cmh94zidvyek.us-east-1.rds.amazonaws.com:1521:DATABASE","student","Student123");
                
             st=con.createStatement();
        
          
          
         } catch (SQLException ex) {
          
            out.println(ex);
        } catch (ClassNotFoundException ex) {
            
            out.println(ex);
        }
              
               out.println("<h3>Data Of Student<h3><br><br>");
             String qry="INSERT INTO data (name,contact,email) VALUES ('"+nm+"','"+c+"','"+e1+"')";   
         
             
            try {         
                pst=con.prepareStatement(qry);
                 // pst.setString(1,nm);
              //pst.setString(2,c);
             //pst.setString(3,e1);       
             
             pst.executeUpdate();
            } catch (SQLException ex) {
                out.println(ex);
            }
            
           
              
             
              
           String s="select name,contact,email from data";
           try {    
            rs= st.executeQuery(s);
                       
                 while(rs.next())
                {
                     nm=rs.getString(1);
                    contact=rs.getInt(2);
                    email=rs.getString(3);
                     out.println("<br>Name of student  :::"+nm);
            out.println("<br>contact:::"+contact);
            out.println("<br>email :::"+email);
            out.println("<br>========================");
                }
                 } catch (SQLException ex) {
                out.println(ex);
            } 
           
            
        }
    }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    @Override
     protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
      protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
