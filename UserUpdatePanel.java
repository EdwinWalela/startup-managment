import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserUpdatePanel extends JFrame
{
    Query query;

    JPanel searchBar = new JPanel();
    JPanel resultBar = new JPanel();
    JPanel resultRight = new JPanel();
    JPanel resultLeft = new JPanel();
    JPanel status = new JPanel();

    JLabel userIdLabel = new JLabel("User ID");
    JLabel userNameLabel = new JLabel("Username");
    JLabel startupIDLabel = new JLabel("Startup");
    JLabel emailLabel = new JLabel("Email");

    JTextField userId = new JTextField("");
    JTextField userName = new JTextField();
    JTextField startupID = new JTextField();
    JTextField email = new JTextField();

    JLabel statusIndicator = new JLabel();

    JButton updateBtn = new JButton("Update");
    JButton logoutBtn = new JButton("Logout");

    JComboBox fetchStartups()
    {
        ArrayList startupIds = new ArrayList();
        ResultSet rs = query.fetchData("startups","*");
        try
        {
            while(rs.next())
            {
                startupIds.add(rs.getString(1));
            }
        }catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return new JComboBox(startupIds.toArray());
    }

    void fetchUserData(String name){
        ResultSet rs = query.fetchData("startups","*","name = '"+name+"'");
        try{
            while(rs.next()){
                userName.setEditable(true);
                userId.setText(rs.getString(7));
                email.setText(rs.getString(7));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }


    public UserUpdatePanel(Query newQuery,String[] userData)
    {
        query = newQuery;
        String startup_name="";
        try
        {
            if(!query.conn.isClosed())
            {
                statusIndicator.setText("DB connection established (idle)");
                statusIndicator.setFont(new Font("San-Serif",Font.PLAIN,17));
                status.setBackground(new Color(1, 163, 55));
                statusIndicator.setForeground(Color.white);
                ResultSet rs = query.fetchData("startups","name","startup_id="+userData[1]+"");
                while(rs.next()) {
                    startup_name = rs.getString(1);
                }
            }
        }
        catch (SQLException e)
        {

        }

        status.setPreferredSize(new Dimension(1300,30));
        status.add(statusIndicator);

        userId.setEditable(false);
        startupID.setEditable(false);

        userId.setText(userData[0]);
        userName.setText(userData[2]);
        email.setText(userData[3]);
        startupID.setText(startup_name);
        System.out.println(startup_name);

        userId.setPreferredSize(new Dimension(350,50));
        userName.setPreferredSize(new Dimension(350,50));

        startupID.setPreferredSize(new Dimension(350,50));
        email.setPreferredSize(new Dimension(350,50));

        userIdLabel.setPreferredSize(new Dimension(350,50));
        userNameLabel.setPreferredSize(new Dimension(350,50));
        startupIDLabel.setPreferredSize(new Dimension(350,50));
        emailLabel.setPreferredSize(new Dimension(350,50));

        userId.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
        userName.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
        startupID.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
        email.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));

        userIdLabel.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
        userNameLabel.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
        startupIDLabel.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
        emailLabel.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));

        updateBtn.setPreferredSize(new Dimension(100,80));
        updateBtn.setBackground(new Color(78, 84, 94));
        updateBtn.setForeground(Color.white);
        updateBtn.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));

        logoutBtn.setPreferredSize(new Dimension(200,70));
        logoutBtn.setFont(new Font(Font.DIALOG,Font.PLAIN,18));
        logoutBtn.setBackground(new Color(145, 63, 43));
        logoutBtn.setForeground(Color.white);

        BorderLayout bl = new BorderLayout();
        bl.setHgap(20);
        searchBar.setLayout(bl);
        searchBar.add(logoutBtn,BorderLayout.EAST);

        resultBar.setLayout(new FlowLayout());
        resultRight.add(userId);
        resultRight.add(userName);
        resultRight.add(startupID);
        resultRight.add(email);

        resultLeft.add(userIdLabel);
        resultLeft.add(userNameLabel);
        resultLeft.add(startupIDLabel);
        resultLeft.add(emailLabel);

        resultLeft.setPreferredSize(new Dimension(500,500));
        resultRight.setPreferredSize(new Dimension(500,500));
        BorderLayout bd = new BorderLayout();
        bd.setVgap(50);
        resultBar.setLayout(bd);

        resultBar.add(updateBtn,BorderLayout.SOUTH);
        resultBar.add(resultLeft,BorderLayout.WEST);
        resultBar.add(resultRight,BorderLayout.EAST);


        resultBar.setBorder(new TitledBorder(new LineBorder(Color.lightGray, 1),"My Profile"));

        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateData(startupID.getText());
            }
        });
        logoutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new details(query).setVisible(true);
                dispose();
            }
        });


        setLayout(new BorderLayout());
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        add(status,BorderLayout.SOUTH);
        add(searchBar,BorderLayout.NORTH);
        add(resultBar,BorderLayout.CENTER);
        setVisible(true);
    }

    void updateData(String name)
    {
        String newUser = userName.getText();
        String newEmail= email.getText();
        String userID= userId.getText();
        if(query.updateUser(new String[]{newUser,newEmail,userID})){
            statusIndicator.setText("Update Success");
            status.setBackground(new Color(1, 163, 55));
        }else{
            status.setBackground(new Color(140, 0, 0));
            statusIndicator.setText("Update Failed");
        }
    }
}


