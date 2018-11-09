/*
*   ALL QUERIES TO BE MADE TO DATABASE ARE DEFINED HERE
* */


import java.security.NoSuchAlgorithmException;
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
        String passwordHash = values[5];
        try{
            passwordHash = Hash.sha256(values[5]+values[0]);
        }catch (NoSuchAlgorithmException e){
            System.out.println(e.getMessage());
        }
        String query = "INSERT INTO users(USER_ID,STARTUP_ID,EMALL,ADMIN,USERNAME,PASSWORD)";
        query +="VALUES("+values[0]+","+values[1]+",'"+values[2]+"',"+values[3]+",'"+values[4]+"','"+passwordHash+"')";
        System.out.println(query);
        return (executeQuery(query));
    }

    public ResultSet fetchData(String table, String field){
        String query = "SELECT "+field+" FROM "+table+"";
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
        String query = "SELECT "+field+" FROM "+table+" WHERE "+where;
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
        String query = "UPDATE startups  SET name='"+values[0]+"',domain='"+values[1]+"',email='"+values[2]+"',contact='"+values[3]+"'";
        query+= " WHERE name ='"+name+"'";
        System.out.println(query);
        return executeQuery(query);
    }

    public boolean deleteStartup(String name){
        String query = "DELETE FROM startups WHERE NAME='"+name+"'";
        System.out.println(query);
        return executeQuery(query);
    }

    public boolean deleteUser(String name){
        String query = "DELETE FROM users WHERE USERNAME='"+name+"'";
        System.out.println(query);
        return executeQuery(query);
    }

    public boolean updateUser(String[] data){
        String query = "UPDATE users SET USERNAME='"+data[0]+"',EMALL='"+data[1]+"'WHERE USER_ID="+data[2];
        System.out.println(query);
        return executeQuery(query);
    }

    public boolean[] userLogin(String[]values){
        String query = "SELECT * FROM users WHERE USER_ID="+values[0]+"";
        System.out.println(query);

        ResultSet rs =null;
        String savedPasswordHash="";
        String inputPasswordHash="";
        Boolean admin=false;

        try {
            inputPasswordHash = Hash.sha256(values[1]+values[0]);
        }catch (NoSuchAlgorithmException e){
            System.out.println(e.getCause());
        }

        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        try{
            while(rs.next()) {
                savedPasswordHash = rs.getString(5);
                admin = rs.getBoolean(6);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        if(inputPasswordHash.equals(savedPasswordHash)){
            return new boolean[]{true,admin};
        }else{
            return new boolean[]{false,admin};
        }
    }

    public boolean startupRegistration(String[]values){
        String query = "INSERT INTO startups(STARTUP_ID,NAME,FOUNDER,DOJ,DOMAIN,EMAIL,CONTACT)";
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
