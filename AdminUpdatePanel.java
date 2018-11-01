import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminUpdatePanel extends JPanel {
    Query query;
    JComboBox startupIDcb;

    JPanel searchBar = new JPanel();
    JPanel resultBar = new JPanel();

    JPanel resultRight = new JPanel();
    JPanel resultLeft = new JPanel();
    JPanel status = new JPanel();

    JLabel startupIdLabel = new JLabel("Startup ID");
    JLabel startupNameLabel = new JLabel("Startup Name");
    JLabel founderLabel = new JLabel("Founder");
    JLabel dojLabel = new JLabel("Date of Joining");
    JLabel domainLabel = new JLabel("Domain");
    JLabel emailLabel = new JLabel("Email");
    JLabel contactLabel = new JLabel("Contact");

    JTextField startupId = new JTextField();
    JTextField startupName = new JTextField();
    JTextField doj = new JTextField();
    JTextField domain = new JTextField();
    JTextField email = new JTextField();
    JTextField contact = new JTextField();
    JTextField founder = new JTextField();

    JLabel statusIndicator = new JLabel();

    JButton updateBtn = new JButton("Update");

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

    void fetchStartupData(String name){
        ResultSet rs = query.fetchData("startups","*","name = '"+name+"'");
        try{
            while(rs.next()){
                startupName.setEditable(true);
                startupId.setText(rs.getString(7));
                startupName.setText(rs.getString(1));
                if(startupName.getText().equals("admin")){startupName.setEditable(false);}
                founder.setText(rs.getString(2));
                doj.setText(rs.getString(3));
                domain.setText(rs.getString(4));
                contact.setText(rs.getString(6));
                email.setText(rs.getString(5));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    void updateData(String name ){
        String newName = startupName.getText();
        String newDomain = domain.getText();
        String newEmail = email.getText();
        String newContact = contact.getText();
        if(query.updateStartup(new String[]{newName,newDomain,newEmail,newContact},name)){
            statusIndicator.setText("Update Success");
            status.setBackground(new Color(1, 163, 55));
        }else{
            status.setBackground(new Color(140, 0, 0));
            statusIndicator.setText("Update Failed");
        }
    }

    public AdminUpdatePanel(Query newQuery){

        query = newQuery;
        try{
            if(!query.conn.isClosed()){
                statusIndicator.setText("DB connection established (idle)");
                statusIndicator.setFont(new Font("San-Serif",Font.PLAIN,17));
                status.setBackground(new Color(1, 163, 55));
                statusIndicator.setForeground(Color.white);
            }
        }catch(SQLException e){

        }

        status.setPreferredSize(new Dimension(1300, 30));
        status.add(statusIndicator);


        startupIDcb = fetchStartups();

        startupIDcb.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
        startupIDcb.setPreferredSize(new Dimension(200,50));

        searchBar.setPreferredSize(new Dimension(1300,100));
        searchBar.add(startupIDcb);
        searchBar.setBorder(new TitledBorder(new LineBorder(Color.lightGray, 1),"Look up a startup"));

        startupId.setEditable(false);
        doj.setEditable(false);
        founder.setEditable(false);
        domain.setEditable(false);

        startupId.setPreferredSize(new Dimension(350,50));
        startupName.setPreferredSize(new Dimension(350,50));
        founder.setPreferredSize(new Dimension(350,50));
        doj.setPreferredSize(new Dimension(350,50));
        domain.setPreferredSize(new Dimension(350,50));
        contact.setPreferredSize(new Dimension(350,50));
        email.setPreferredSize(new Dimension(350,50));

        startupIdLabel.setPreferredSize(new Dimension(350,50));
        startupNameLabel.setPreferredSize(new Dimension(350,50));
        founderLabel.setPreferredSize(new Dimension(350,50));
        dojLabel.setPreferredSize(new Dimension(350,50));
        domainLabel.setPreferredSize(new Dimension(350,50));
        contactLabel.setPreferredSize(new Dimension(350,50));
        emailLabel.setPreferredSize(new Dimension(350,50));

        startupId.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
        startupName.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
        founder.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
        doj.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
        domain.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
        contact.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
        email.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));

        startupIdLabel.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
        startupNameLabel.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
        founderLabel.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
        dojLabel.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
        domainLabel.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
        contactLabel.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
        emailLabel.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));

        updateBtn.setPreferredSize(new Dimension(100,80));
        updateBtn.setBackground(new Color(78, 84, 94));
        updateBtn.setForeground(Color.white);
        updateBtn.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));

        resultBar.setLayout(new FlowLayout());

        resultRight.add(startupId);
        resultRight.add(startupName);
        resultRight.add(founder);
        resultRight.add(doj);
        resultRight.add(domain);
        resultRight.add(email);
        resultRight.add(contact);

        resultLeft.add(startupIdLabel);
        resultLeft.add(startupNameLabel);
        resultLeft.add(founderLabel);
        resultLeft.add(dojLabel);
        resultLeft.add(domainLabel);
        resultLeft.add(emailLabel);
        resultLeft.add(contactLabel);

        resultLeft.setPreferredSize(new Dimension(500,500));
        resultRight.setPreferredSize(new Dimension(500,500));

        resultBar.setLayout(new BorderLayout());

        resultBar.add(updateBtn,BorderLayout.SOUTH);
        resultBar.add(resultLeft,BorderLayout.WEST);
        resultBar.add(resultRight,BorderLayout.EAST);

        resultBar.setBorder(new TitledBorder(new LineBorder(Color.lightGray, 1),"Results"));
        startupIDcb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fetchStartupData(startupIDcb.getSelectedItem().toString());
            }
        });

        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateData(startupIDcb.getSelectedItem().toString());
            }
        });


        setLayout(new BorderLayout());
        add(status,BorderLayout.SOUTH);
        add(searchBar,BorderLayout.NORTH);
        add(resultBar,BorderLayout.CENTER);
    }

}
