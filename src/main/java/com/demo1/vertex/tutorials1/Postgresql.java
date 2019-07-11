package com.demo1.vertex.tutorials1;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;


import com.google.gson.JsonObject;


public class Postgresql {

	
	String URL = "jdbc:postgresql://localhost:5432/testdemo";
    String USER = "postgres";
    String PASS = "pass";
    
    ////////////////////////////////////////////////////////////////////////////
    public Object GetDetails() {
    	
    	List<String> lst1 = new ArrayList<String>();
    	JsonObject list = new JsonObject();
    	
  	   ResultSet resultSet = null;
  	 ResultSet resultSet1 = null;
  	   
  	   try{

             Class.forName("org.postgresql.Driver"); 

        }

        catch(ClassNotFoundException e)
        {
           System.out.println("error class not found exception");
           e.printStackTrace();

        }

        try{
            String URL = "jdbc:postgresql://localhost:5432/testdemo";
            String USER = "postgres";
            String PASS = "pass";
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            String query1 = "select column_name from information_schema.columns where table_name='stockmanagement'";
            Statement st = conn.createStatement();
            PreparedStatement prSt1 = conn.prepareStatement(query1);
            resultSet = st.executeQuery("select * from stockmanagement");
            resultSet1 = prSt1.executeQuery();
            System.out.println(resultSet1);
            while (resultSet.next()) {
                
            	list.addProperty("item_no",resultSet.getString(1));
            	list.addProperty("item_name",resultSet.getString(2));
            	list.addProperty("item_type",resultSet.getString(3));
            	list.addProperty("price",resultSet.getString(4));
            	list.addProperty("instock",resultSet.getString(5));
            	
                lst1.add(list.toString());
                //System.out.println(resultSet.getString("name1"));
              
            }

  	   
        }catch(Exception es){
            es.printStackTrace();
        }
        
        return(lst1.toString());
    	
    	
    }
    
    
    
    
    public void Addstock(io.vertx.core.json.JsonObject body) {
  	  //System.out.println(body.getString("name"));
     
  	  try{

             Class.forName("org.postgresql.Driver"); 

        }

        catch(ClassNotFoundException e)
        {
           System.out.println("error class not found exception");
           e.printStackTrace();

        }

        try{
            String URL = "jdbc:postgresql://localhost:5432/testdemo";
            String USER = "postgres";
            String PASS = "pass";
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            String sql = "INSERT INTO stockmanagement VALUES (?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,Integer.parseInt(body.getString("item_no")));
            pstmt.setString(2,body.getString("item_name"));
            pstmt.setString(3,body.getString("item_type"));
            pstmt.setInt(4,Integer.parseInt(body.getString("price")));
            pstmt.setString(5,body.getString("instock"));
           
            pstmt.executeUpdate();
            
        }

        catch(Exception es){
            es.printStackTrace();
        }	   
     }
    
    
    
    
    public void Delstock(String body) {
  	  //System.out.println(body.getString("name"));
    	
     
  	  try{

             Class.forName("org.postgresql.Driver"); 

        }

        catch(ClassNotFoundException e)
        {
           System.out.println("error class not found exception");
           e.printStackTrace();

        }

        try{
            String URL = "jdbc:postgresql://localhost:5432/testdemo";
            String USER = "postgres";
            String PASS = "pass";
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            String sql = "DELETE FROM stockmanagement WHERE item_name=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,body);
            pstmt.executeUpdate();
           
            
        }

        catch(Exception es){
            es.printStackTrace();
        }	   
     }
     
    
    public void Updatestock(String id, io.vertx.core.json.JsonObject json) {
    	
  	  try{

            Class.forName("org.postgresql.Driver"); 

       }

       catch(ClassNotFoundException e)
       {
          System.out.println("error class not found exception");
          e.printStackTrace();

       }

       try{
           String URL = "jdbc:postgresql://localhost:5432/testdemo";
           String USER = "postgres";
           String PASS = "pass";
           Connection conn = DriverManager.getConnection(URL, USER, PASS);
           String sql = "UPDATE stockmanagement SET item_no=?,item_name=?,item_type=?,price=?,instock=?  where item_name=?";
           PreparedStatement pstmt = conn.prepareStatement(sql);
           pstmt.setInt(1,Integer.parseInt(json.getString("item_no")));
           pstmt.setString(2,json.getString("item_name"));
           pstmt.setString(3,json.getString("item_type"));
           pstmt.setInt(4,Integer.parseInt(json.getString("price")));
           pstmt.setString(5,json.getString("instock"));
           pstmt.setString(6,id);
           
           pstmt.executeUpdate();
           
       }

       catch(Exception es){
           es.printStackTrace();
       }
  	
     }

    
 ////////////////////////////////////////////////////////////////////////////   
    
    
    
    
    
    
    
    /// retrive database values of perticular user
    
   public Object GetUsername(String username) {
	   
	   JsonObject list = new JsonObject();
	   ResultSet resultSet = null;
	   
	   try{

           Class.forName("org.postgresql.Driver"); 

      }

      catch(ClassNotFoundException e)
      {
         System.out.println("error class not found exception");
         e.printStackTrace();

      }

      try{
          String URL = "jdbc:postgresql://localhost:5432/testdemo";
          String USER = "postgres";
          String PASS = "pass";
          Connection conn = DriverManager.getConnection(URL, USER, PASS);
          String query = "select * from demo where email=?";
          PreparedStatement prSt = conn.prepareStatement(query);
          prSt.setString(1,(String) username);
          resultSet = prSt.executeQuery();
          
          while (resultSet.next()) {
              list.addProperty("name",resultSet.getString("name1"));
              //System.out.println(resultSet.getString("name1"));
              list.addProperty("email",resultSet.getString("email"));
              list.addProperty("address",resultSet.getString("address"));
              list.addProperty("mobno",resultSet.getString("mobno"));
              list.addProperty("username",resultSet.getString("username"));
              list.addProperty("password",resultSet.getString("password1"));
              list.addProperty("re_password",resultSet.getString("re_password"));
              
          }

	   
      }catch(Exception es){
          es.printStackTrace();
      }
      
      
      return (list.toString());
   }
   
   
   //store user all information in database
   
   public void AddValues(io.vertx.core.json.JsonObject body) {
	  //System.out.println(body.getString("name"));
   
	  try{

           Class.forName("org.postgresql.Driver"); 

      }

      catch(ClassNotFoundException e)
      {
         System.out.println("error class not found exception");
         e.printStackTrace();

      }

      try{
          String URL = "jdbc:postgresql://localhost:5432/testdemo";
          String USER = "postgres";
          String PASS = "pass";
          Connection conn = DriverManager.getConnection(URL, USER, PASS);
          String sql = "INSERT INTO demo VALUES (?,?,?,?,?,?,?)";
          PreparedStatement pstmt = conn.prepareStatement(sql);
          pstmt.setString(1,body.getString("name"));
          pstmt.setString(2,body.getString("add"));
          pstmt.setString(3,body.getString("password"));
          pstmt.setString(4,body.getString("re_password"));
          pstmt.setString(5,body.getString("mob"));
          pstmt.setString(6,body.getString("username"));
          pstmt.setString(7,body.getString("email"));
          pstmt.executeUpdate();
          
      }

      catch(Exception es){
          es.printStackTrace();
      }	   
   }
   
   
   // Update the user value in database
   
   public void UpdateValues(String id, io.vertx.core.json.JsonObject json) {
	
	  try{

          Class.forName("org.postgresql.Driver"); 

     }

     catch(ClassNotFoundException e)
     {
        System.out.println("error class not found exception");
        e.printStackTrace();

     }

     try{
         String URL = "jdbc:postgresql://localhost:5432/testdemo";
         String USER = "postgres";
         String PASS = "pass";
         Connection conn = DriverManager.getConnection(URL, USER, PASS);
         String sql = "UPDATE demo SET name1=?,address=?,mobno=?,username=?  where email=?";
         PreparedStatement pstmt = conn.prepareStatement(sql);
         pstmt.setString(1,json.getString("name"));
         pstmt.setString(2,json.getString("add"));
         pstmt.setString(3,json.getString("mob"));
         pstmt.setString(4,json.getString("username"));
         pstmt.setString(5,id);
         
         pstmt.executeUpdate();
         
     }

     catch(Exception es){
         es.printStackTrace();
     }
	
   }

}

