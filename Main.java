
/*
* STARTUP MANAGEMENT PROGRAM ENTRY POINT
*   THIS IS THE FIRST SCREEN USER WILL SEE(LOGIN&REGISTER)
*   FROM HERE USER IS DIRECTED TO (ADMIN DASHBOARD/USER DASHBOARD)
* */


import java.sql.Connection;

public class Main {

    public static void main(String[] args) {
        Connection conn = new Configuration().newConnection();
        Query query = new Query(conn);
        //JFrame frame = new JFrame();
        // Login / Registration goes here


        AdminDashboardFrame adminView = new AdminDashboardFrame(query);


    }
}
