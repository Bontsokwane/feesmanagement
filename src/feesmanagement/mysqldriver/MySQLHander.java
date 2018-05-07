package feesmanagement.mysqldriver;


import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import javax.swing.JOptionPane;


/**
 *
 * @author jabari
 */
public class MySQLHander {

    private final String Host_Address;
    
    private final String DB_DRIVERCLASS = "com.mysql.jdbc.Driver";
    public static Connection CONNECTION = null;
    public static Statement STATEMENT = null;
   

    public MySQLHander() {
           
        
        
        Host_Address = "127.0.0.1";
       
        if(dbConnect()){
            System.out.println("Connection established...");
        }else{
            System.out.println("Connection failed...");
        }
        
        setupfeeTable();
        
    }
    
    /**
     * Initialize database connect handler
     * @return 
     */
    public boolean dbConnect(){
        
        try{
            //-- Load the database class driver
            Class.forName(DB_DRIVERCLASS);
            
            //String dbURL = "jdbc:mysql://"+ Host_Address + ":3306/"+dbName;
             String dbURL = "jdbc:mysql://127.0.0.1/school_management";

            //-- Get database connection handler
            CONNECTION  =  DriverManager.getConnection( dbURL, "root", "" );
            if(CONNECTION != null){ 
                //-- create a Statement object for sending SQL statements to the database.  
               STATEMENT = CONNECTION.createStatement();
               
               //-- 
               return true;
            }
            return false;
        }catch(Exception error ){
            System.out.println(error.getMessage());
            return false;
        }
    }
    
     public ResultSet execQuery(String query) {
        ResultSet result;
        try {
            STATEMENT = CONNECTION.createStatement();
            result = STATEMENT.executeQuery(query);
        } catch (SQLException ex) {
            System.out.println("Exception at execQuery:dataHandler" + ex.getLocalizedMessage());
            return null;
        } finally {
        }
        return result;
    }

    public boolean execAction(String qu) {
        try {
            STATEMENT = CONNECTION.createStatement();
            STATEMENT.execute(qu);
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error:" + ex.getMessage(), "Error Occured", JOptionPane.ERROR_MESSAGE);
            System.out.println("Exception at execQuery:dataHandler" + ex.getLocalizedMessage());
            return false;
        } finally {
        }
    }
    
    void setupfeeTable(){
    
        String TABLE_NAME = "FEE";
        try{
        
        STATEMENT = CONNECTION.createStatement();
        DatabaseMetaData dbs = CONNECTION.getMetaData();
        ResultSet tables = dbs.getTables(null,null,TABLE_NAME.toUpperCase(),null);
        
        if (tables.next()){
          System.out.println("Table already exist");
        }else{
           String sql = "CREATE TABLE FEE( " +
                   
                   " name VARCHAR(255) NOT NULL, " + 
                   " description VARCHAR(255), " + 
                   " payment VARCHAR(255), " + 
                   " PRIMARY KEY (name))"; 

          STATEMENT.execute(sql);
        }
        
        }catch(SQLException e){
            System.err.println(e.getMessage()+ "..setUpDB");
        
        }finally{}
    
        
        
    }
    
}