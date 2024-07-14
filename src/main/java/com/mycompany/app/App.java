package com.mycompany.app;
import java.io.IOException;
import com.mycompany.app.model.Result;
import java.sql.*;


 
 public class App {
 
   public static Result   newResult;
 
     public static void main(String[] args) throws IOException, SQLException 
     {
       
        String [] argsm ={"3-1 2-1 3-2","discountCard=3232","balanceDebitCard=200","PostGree"}; 

        //connectP();

        String pathm=System.getProperty("user.dir");  
        
        newResult=new Result(0,argsm,pathm+"\\src\\main\\java\\com\\mycompany\\app\\resources\\");          

   
    }

     
 }
 