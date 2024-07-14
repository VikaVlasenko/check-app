package com.mycompany.app;

 import java.io.IOException;

import com.mycompany.app.model.Result;
import com.mycompany.app.model.database;

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


    //  Тестирование
    public static void connectP()
    {
      Connection connection = null;

       String url = "jdbc:postgresql://127.0.0.1:5432/ctcdb";
       String name = "u_ctc";
       String password = "uc";

       try {
        //Загружаем драйвер
        Class.forName("org.postgresql.Driver");
        System.out.println("Драйвер подключен");

        connection = DriverManager.getConnection(url, name, password);
        System.out.println("Соединение установлено");

        Statement statement = null;

        statement = connection.createStatement();
        //Выполним запрос
        ResultSet result1 = statement.executeQuery("SELECT id,name FROM ctcviewad.users ");

        System.out.println("Выводим statement");
        while (result1.next()) {
            System.out.println("np #" + result1.getRow()
                    + "\t Номер  #" + result1.getInt("id")
                    + "\t Имя " + result1.getString("name"));
        }                

        statement.close();
        connection.close();

       }catch (Exception ex) {
        throw new RuntimeException("Не удалось подключить драйвер", ex);
       }

    }

     
 
     
 }
 