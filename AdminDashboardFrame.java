import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AdminDashboardFrame extends JFrame {
    Query query;
    JPanel navigation = new JPanel();
    JPanel activity = new JPanel();
    JPanel status = new JPanel();

    JButton createBtn = new JButton("Register");
    JButton readBtn = new JButton("Search");
    JButton updateBtn = new JButton("Update");
    JButton deleteBtn = new JButton("Delete");
    JButton logoutBtn = new JButton("Logout");
    JLabel statusIndicator = new JLabel();

    BorderLayout borderLayout = new BorderLayout();
    CardLayout cardLayout = new CardLayout();

    AdminCreatePanel createPanel;
    AdminReadPanel readPanel;
    AdminUpdatePanel updatePanel;
    AdminDeletePanel deletePanel;

    AdminDashboardFrame(Query newQuery){

        query = newQuery;

        createPanel = new AdminCreatePanel(query);
        readPanel = new AdminReadPanel(query);
        updatePanel = new AdminUpdatePanel(query);
        deletePanel = new AdminDeletePanel(query);

        try{
            if(!query.conn.isClosed()){
               statusIndicator.setText("DB connection established (idle)");
               statusIndicator.setFont(new Font("San-Serif",Font.PLAIN,15));
               status.setBackground(new Color(1, 163, 55));
               statusIndicator.setForeground(Color.white);
            }
        }catch(SQLException e){

        }

        navigation.setPreferredSize(new Dimension(1300, 80));
        activity.setPreferredSize(new Dimension(1300, 600));
        status.setPreferredSize(new Dimension(1300, 30));

        navigation.setBorder(new TitledBorder(new LineBorder(Color.lightGray, 1),"Navigation"));

        activity.setLayout(cardLayout);

        activity.add("createPanel",createPanel);
        activity.add("readPanel",readPanel);
        activity.add("updatePanel",updatePanel);
        activity.add("deletePanel",deletePanel);

        cardLayout.show(activity,"createPanel");

        createBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(activity,"createPanel");
            }
        });

        readBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(activity,"readPanel");
            }
        });

        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(activity,"updatePanel");
            }
        });

        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(activity,"deletePanel");
            }
        });




        borderLayout.setVgap(10);

        status.add(statusIndicator);

        createBtn.setPreferredSize(new Dimension(150,50));
        readBtn.setPreferredSize(new Dimension(150,50));
        updateBtn.setPreferredSize(new Dimension(150,50));
        deleteBtn.setPreferredSize(new Dimension(150,50));
        logoutBtn.setPreferredSize(new Dimension(100,50));

        createBtn.setFont(new Font(Font.DIALOG,Font.PLAIN,18));
        createBtn.setBackground(new Color(78, 84, 94));
        createBtn.setForeground(Color.white);

        readBtn.setFont(new Font(Font.DIALOG,Font.PLAIN,18));
        readBtn.setBackground(new Color(78, 84, 94));
        readBtn.setForeground(Color.white);

        updateBtn.setFont(new Font(Font.DIALOG,Font.PLAIN,18));
        updateBtn.setBackground(new Color(78, 84, 94));
        updateBtn.setForeground(Color.white);

        deleteBtn.setFont(new Font(Font.DIALOG,Font.PLAIN,18));
        deleteBtn.setBackground(new Color(78, 84, 94));
        deleteBtn.setForeground(Color.white);

        logoutBtn.setFont(new Font(Font.DIALOG,Font.PLAIN,18));
        logoutBtn.setBackground(new Color(145, 63, 43));
        logoutBtn.setForeground(Color.white);


        navigation.add(createBtn);
        navigation.add(readBtn);
        navigation.add(updateBtn);
        navigation.add(deleteBtn);
        navigation.add(logoutBtn);
        navigation.setLayout(new FlowLayout(FlowLayout.LEFT));

        // Frame configuraion
        setLayout(borderLayout);
        add(navigation,BorderLayout.NORTH);
        add(activity,BorderLayout.CENTER);
        add(status,BorderLayout.SOUTH);
        setSize(1300,800);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setTitle("Admin Dashboard");
    }

}
