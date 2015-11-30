
//STEP 1. Import required packages
import java.sql.*;
import java.util.Arrays;

public class DBConnector {

   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/twitterDB";

   //  Database credentials
   static final String USER = "root";
   static final String PASS = "sri";
   static int[] Tot= new int[13370];
   public static void main(String[] args) {
   Connection conn = null;
   Statement stmt = null;
   try{
      // Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");
      //Open a connection

      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      
      //Execute a query

      stmt = conn.createStatement();

      String sql = "SELECT Rt,Ft,diff FROM data";
      ResultSet rs = stmt.executeQuery(sql);
      //STEP 5: Extract data from result set
      int[] ReTweet= new int[10000];
      int[] Favouriye= new int[10000];      
      int[] DateDif= new int[10000];
      int[][]val=new int[13370][2];
      int i=0;
      while(rs.next()){
         //Retrieve by column name
         int Rt = rs.getInt("Rt");
         int Ft= rs.getInt("Ft");
         int diff=(int)rs.getLong("diff");
         Tot[i]=Rt+Ft;
        // val[i][0]=Rt+Ft;
        // val[i][1]=diff;
         //System.out.println(Tot[i]);
         i++;
         
 
      } 
      /*
      Arrays.sort(Tot);
      for (int j = 0; j < 200; j++) {
          System.out.println("tot val :"+Tot[Tot.length-1-j]);
      }
      */
      Statistics st=new Statistics(Tot);
      Double stdDevDouble=st.getStdDev();
      System.out.println("Deviation  :"+stdDevDouble);
      Double mean=st.getMean();
      System.out.println("mean  :"+mean);
      Double meand=st.median();
      System.out.println("median :"+meand);
      Double max=st.getMax();
      System.out.println("max :"+max);
      System.out.println("No of data :"+Tot.length);
     // System.out.println(mean-stdDevDouble);
     // System.out.println(mean+stdDevDouble);
      
      int y=0;
      for (int j = 0; j < Tot.length; j++) {
          if(Tot[j]>meand){
              y=y+1;
          }else if(Tot[j]<meand){
             y=y-1;
             
          }
          
          
    }
      Double lamda=(double) (y/45000);
      System.out.println(y);
      rs.close();
   }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            conn.close();
      }catch(SQLException se){
      }// do nothing
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
   System.out.println("Goodbye!");
}//end main
}//end JDBCExample