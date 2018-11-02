/*
*   ALL QUERIES TO BE MADE TO DATABASE ARE DEFINED HERE
* */


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;



public class Query {

    public Connection conn;
    private Statement stmt = null;

    public Query(Connection newConn){ conn = newConn;}

    private boolean executeQuery(String query){
        try{
            stmt = conn.createStatement();
            if(stmt.executeUpdate(query) == 1){
                return true;
            }
            stmt.close();

        }catch(SQLException e){
            System.out.println(e.getMessage());

        }
        return  false;
    }

    public boolean registerUser(String[]values){
        String query = "INSERT INTO USERS(USER_ID,USERNAME,STARTUP_ID,EMALL,PASSWORD,ADMIN)";
        query +="VALUES("+values[0]+",'"+values[1]+"',"+values[2]+",'"+values[3]+"','"+values[4]+"',"+values[5]+")";
        return (executeQuery(query));
    }

    public ResultSet fetchData(String table, String field){
        String query = "SELECT "+field+" FROM "+table.toUpperCase()+"";
        System.out.println(query);
        ResultSet rs =null;
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return rs;
    }

    public ResultSet fetchData(String table, String field,String where){
        String query = "SELECT "+field+" FROM "+table.toUpperCase()+" WHERE "+where;
        System.out.println(query);
        ResultSet rs =null;
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return rs;
    }

    public boolean updateStartup(String[] values,String name){
        String query = "UPDATE STARTUPS  SET name='"+values[0]+"',domain='"+values[1]+"',email='"+values[2]+"',contact='"+values[3]+"'";
        query+= " WHERE name ='"+name+"'";
        System.out.println(query);
        return executeQuery(query);
    }

    public boolean deleteStartup(String name){
        String query = "DELETE FROM STARTUPS WHERE NAME='"+name+"'";
        System.out.println(query);
        return executeQuery(query);
    }

    public boolean deleteUser(String name){
        String query = "DELETE FROM USERS WHERE USERNAME='"+name+"'";
        System.out.println(query);
        return executeQuery(query);
    }

    public boolean[] userLogin(String[]values){
        String query = "SELECT * FROM USERS WHERE USER_ID="+values[0]+"";
        System.out.println(query);
        ResultSet rs =null;
        String inputPass = values[1];
        String password="";
        Boolean admin=false;
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        try{
            while(rs.next()) {
                password = rs.getString(5);
                admin = rs.getBoolean(6);
            }
        }catch(SQLException e){System.out.println(e.getMessage());}


        if(Objects.equals(inputPass,password)){
            return new boolean[]{true,admin};
            
        }else{
            return new boolean[]{false,admin};
        }

    }

    public boolean startupRegistration(String[]values){
        String query = "INSERT INTO STARTUPS(NAME,FOUNDER,DOJ,DOMAIN,EMAIL,CONTACT,STARTUP_ID)";
        query +="VALUES('"+values[0]+"','"+values[1]+"','"+values[2]+"','"+values[3]+"','"+values[4]+"','"+values[5]+"',"+values[6]+")";
       System.out.println(query);
        return (executeQuery(query));
    }

    public int tableCount(String table){
        String primary_key;
        if(table == "users"){
            primary_key = "user_id";
        }else{
            primary_key = "startup_id";
        }
        String query = "SELECT COUNT("+primary_key+") FROM "+table+"";
        //System.out.println(query);
        ResultSet rs = null;
        int count = 0;
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        try {
            while(rs.next()){
               count = rs.getInt(1);
               System.out.println(rs.getString(1));
            }
        }catch(SQLException e){System.out.println(e.getMessage());}

        return  count;
    }
    }

