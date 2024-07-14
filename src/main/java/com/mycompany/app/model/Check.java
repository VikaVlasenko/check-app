package com.mycompany.app.model;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.sql.Date;


import com.mycompany.app.model.Product.CARDp;

public class Check 
{
   
    public static List<ItemCheck> list=new ArrayList<>();
    static database DB;

    public static class ItemCheck
    {
        int qty=0;
        String description="";
        float price=0;
        boolean isWholesale=false;

        ItemCheck()
        {

        }
        ItemCheck(int a, String b, float pp, boolean s )
        {
            qty=a;
            description=b;
            price=pp;
            isWholesale=s;
        }
       
    }

    public Check(){
        
    }


     void  setDB(database db) {
        Check.DB = db;
      }  

    //  Добавление товара в чек
    public static void addItem(String descr, float price, int quantity, boolean isWholesale)
    {
        
        ItemCheck item= new ItemCheck(quantity, descr, price, isWholesale );
        list.add(item);
    }

    //  Формирование чека 
    public static void CheckFormer(Discount discount,Product product, Map<Integer,Integer> listIn,Integer discontIn)
    {
        float disc;

        disc=Discount.GedDiscont(discontIn);

        //  Проход по listIn
        for(int k: listIn.keySet()) 
            for(CARDp i: Product.listProduct )
                if(i.index==k)
                {
                  float nPrice=i.price;
                  if(disc==0) disc=2;

                  nPrice=i.price - i.price * (disc/100);
                  addItem(i.description, nPrice, i.quantity, i.isWholesale);
                  //System.out.println("name="+i.description+"\t price="+nPrice+"\t");
                }


    }

    
    //  Запись чека в файл CSV
    public static void saveCSV(String filepath)  throws IOException 
    {

        try {
            FileWriter writer = new FileWriter(filepath+"result.csv");

            long ml=System.currentTimeMillis();  
            java.sql.Date date = new Date(ml);

            
            writer.write(date.toString()+"\r");
            for(ItemCheck i: list)
            {
                String str=i.description+";"+i.qty+";"+i.price+";\r";
                writer.write(str);

            }
            writer.close();

            } catch (IOException e) {
                    System.out.println("Ошибка при записи в файл");
                    e.printStackTrace();
            }
    }


    // Запись чека в базу данных
    public static void saveDB() throws SQLException
    {
        
        long ml=System.currentTimeMillis();  
        java.sql.Date date=new Date(ml);
        
        PreparedStatement st;
        int npp=1;


        st=DB.insertSQL("insert into public.result(date_check,index,description,price,"+
        "quantity,isWholesale) values(?,?,?,?,?,?)");

        for(ItemCheck i: list)
        {
            try
            {
            st.setDate(1, date) ;
            st.setInt(2, npp);
            st.setString(3, i.description);
            st.setFloat(4, i.price);
            st.setInt(5, i.qty);
            st.setBoolean(6, i.isWholesale);
            st.executeUpdate();
            }
            catch(SQLException ex)
            { System.out.println(ex.toString());}

            npp++;

        }       

    }

}
