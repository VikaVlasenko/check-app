package com.mycompany.app.model;

import java.sql.*;


public class database {
    
  public static int db;

  static String url;
  static String name;
  static String password;

  static Statement statement = null;
  static Connection connection = null;
  static ResultSet result=null;

  public database()
  {

    url = "jdbc:postgresql://127.0.0.1:5432/ctcdb";
    name = "u_ctc";
    password = "uc";

  }


  //----------------------------------------------------------------
  static void mConnect() throws SQLException
  {
    
    try{
    Class.forName("org.postgresql.Driver");
    connection=DriverManager.getConnection(url, name, password);
    statement = connection.createStatement();
    }catch (Exception ex) {
        throw new RuntimeException("Не удалось подключить драйвер", ex);
    }

  }

  //----------------------------------------------------------------
  public static ResultSet getSQL(String SQL) throws SQLException
  {
    ResultSet result = null;

    mConnect();

    try{
    result=statement.executeQuery("SELECT id,name FROM ctcviewad.users ");}
    catch (Exception ex) {
    throw new RuntimeException("Ошибка запроса", ex);
    }

    return result;
  }


  //----------------------------------------------------------------
  public static void closeConnet() throws SQLException
  {
    try{
    statement.close();
    connection.close();}
    catch (Exception ex) {
        throw new RuntimeException("Ошибка запроса", ex);
        }
      }

}
