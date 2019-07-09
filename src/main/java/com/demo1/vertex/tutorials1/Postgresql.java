package com.demo1.vertex.tutorials1;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.google.gson.JsonObject;


public class Postgresql {

	
	String URL = "jdbc:postgresql://localhost:5432/testdemo";
    String USER = "postgres";
    String PASS = "pass";
    
    
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

