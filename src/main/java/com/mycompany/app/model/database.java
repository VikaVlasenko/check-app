package com.mycompany.app.model;

import java.sql.*;


public class database {
    
  public static int db;

  private static String url;
  private static String name;
  private static String password;

  private static Statement statement = null;
  private static Connection connection = null;
  static ResultSet result=null;

  public database()
  {

    url = "jdbc:postgresql://127.0.0.1:5432/postgres";
    name = "userdb";
    password = "us";

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
  public ResultSet getSQL(String SQL) throws SQLException
  {
    ResultSet result = null;

    mConnect();

    try{
    result=statement.executeQuery(SQL);}
    catch (Exception ex) {
    throw new RuntimeException("Ошибка запроса", ex);
    }

    return result;
  }


  public PreparedStatement insertSQL(String SQL) throws SQLException
  {
    PreparedStatement statement;
    mConnect();
    try{
    statement = connection.prepareStatement(SQL);}
    catch (Exception ex) {
    throw new RuntimeException("Ошибка insert", ex);
    }
    return statement;
  }



  //----------------------------------------------------------------
  public void closeConnet() throws SQLException
  {
    try{
    statement.close();
    connection.close();}
    catch (Exception ex) {
        throw new RuntimeException("Ошибка запроса", ex);
        }
      }

}
