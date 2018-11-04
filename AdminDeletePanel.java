import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminDeletePanel extends JPanel {
    Query query;
    JComboBox startupIDcb = new JComboBox();
    JComboBox userNamecb = new JComboBox();

    JPanel searchBar = new JPanel();
    JPanel resultBar = new JPanel();

    JPanel status = new JPanel();

    JLabel statusIndicator = new JLabel();

    JButton deleteBtn = new JButton("Delete Startup");
    JButton deleteUserBtn = new JButton("Delete User ");

    JComboBox fetchStartups(){

        ArrayList startupIds = new ArrayList();
        ResultSet rs = query.fetchData("startups","name");
        try{
            while(rs.next()){
                startupIds.add(rs.getString(1));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return new JComboBox(startupIds.toArray());
    }

    void fetchUsers(){
        ResultSet rs = query.fetchData("users","username","startup_id="+startupIDcb.getSelectedIndex());
        try{
            while(rs.next()){
                System.out.println(rs.getString(1));
                userNamecb.addItem(rs.getString(1));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }


    public AdminDeletePanel(Query newQuery){
        query = newQuery;

        try{
            if(!query.conn.isClosed()){
                statusIndicator.setText("DB connection established (idle)");
                statusIndicator.setFont(new Font("San-Serif",Font.PLAIN,17));
                status.setBackground(new Color(1, 163, 55));
                statusIndicator.setForeground(Color.white);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        status.setPreferredSize(new Dimension(1300, 30));
        status.add(statusIndicator);

        startupIDcb = fetchStartups();


        startupIDcb.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
        startupIDcb.setPreferredSize(new Dimension(200,50));

        userNamecb.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
        userNamecb.setPreferredSize(new Dimension(200,50));

        searchBar.setPreferredSize(new Dimension(1300,100));
        searchBar.add(startupIDcb);
        searchBar.add(userNamecb);
        searchBar.setBorder(new TitledBorder(new LineBorder(Color.lightGray, 1),"Look up a startup"));


        deleteBtn.setBackground(new Color(78, 84, 94));
        deleteBtn.setForeground(Color.white);
        deleteBtn.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));

        deleteUserBtn.setBackground(new Color(78, 84, 94));
        deleteUserBtn.setForeground(Color.white);
        deleteUserBtn.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));

        resultBar.setPreferredSize(new Dimension(1300,300));
        resultBar.setLayout(new BorderLayout());
        resultBar.add(deleteBtn,BorderLayout.WEST);
        resultBar.add(deleteUserBtn,BorderLayout.EAST);
        resultBar.setBorder(new TitledBorder(new LineBorder(Color.lightGray, 1),"Result"));

        startupIDcb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userNamecb.removeAllItems();
                fetchUsers();
            }
        });

        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!startupIDcb.getSelectedItem().toString().equals("admin")) {
                    String deleteKey = JOptionPane.showInputDialog(null,"Enter Deletion Key");
                    if (deleteKey.equals("8374732")) {
                        if (query.deleteStartup(startupIDcb.getSelectedItem().toString())) {
                            statusIndicator.setText(startupIDcb.getSelectedItem().toString() + " deletion success");
                            status.setBackground(new Color(1, 163, 55));
                        } else {
                            statusIndicator.setText("Deletion failed:First delete users in this startup");
                            status.setBackground(new Color(175, 26, 3));
                        }
                    } else {
                        statusIndicator.setText("Access Denied: Invalid Deletion Key");
                        status.setBackground(new Color(175, 26, 3));
                    }
                } else {
                    statusIndicator.setText("Admin not deletable");
                    status.setBackground(new Color(175, 26, 3));
                }
            }
        });

        deleteUserBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!startupIDcb.getSelectedItem().toString().equals("admin")) {
                    String deleteKey = JOptionPane.showInputDialog(null, "Enter Deletion Key");
                    if (deleteKey.equals("8374732")) {
                        if (query.deleteUser(userNamecb.getSelectedItem().toString())) {
                            statusIndicator.setText(userNamecb.getSelectedItem().toString() + " deletion success");
                            userNamecb.removeItemAt(userNamecb.getSelectedIndex());
                            status.setBackground(new Color(1, 163, 55));

                        } else {
                            statusIndicator.setText("Deletion failed");
                            status.setBackground(new Color(175, 26, 3));
                        }
                    }else{
                        statusIndicator.setText("Access Denied: Invalid Deletion Key");
                        status.setBackground(new Color(175, 26, 3));
                    }
                }else{
                    statusIndicator.setText("Admin not deletable");
                    status.setBackground(new Color(175, 26, 3));
                }
            }
        });


        setLayout(new BorderLayout());
        add(status,BorderLayout.SOUTH);
        add(searchBar,BorderLayout.NORTH);
        add(resultBar,BorderLayout.CENTER);
    }

}
