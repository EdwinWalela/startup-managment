import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AdminCreatePanel extends JPanel 
{
    Query query;

    
    JLabel LabelID = new JLabel ("Startup ID:");
    JLabel NameLabel = new JLabel ("Startup Name:");
    JLabel FoundLabel = new JLabel ("Founder:");
    JLabel DateLabel = new JLabel ("Date of Joining:");
    JLabel DomLabel = new JLabel ("Domain:");
    JLabel EmaLabel = new JLabel ("Email Address:");
    JLabel ContLabel = new JLabel ("Contact:");

    JTextField stpID = new JTextField();
    JTextField startupname = new JTextField();
    JTextField founder = new JTextField();
    JTextField date = new JTextField();
    JTextField domain = new JTextField();
    JTextField email = new JTextField();
    JTextField contact = new JTextField();
    JButton submit = new JButton("Register");
    
    JPanel status = new JPanel();
    JLabel statusIndicator = new JLabel();
    
    public AdminCreatePanel(Query newQuery)
    {
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
    
    JPanel inputForm = new JPanel();
    stpID.setText(""+(query.tableCount("startups")+1));
    LabelID.setBounds (30,100,320,50);
    LabelID.setFont(new Font ("San Serif",Font.PLAIN, 25));
    stpID.setBounds (250,100,320,50);
    stpID.setFont(new Font("San Serif",Font.PLAIN, 25));

    NameLabel.setBounds (30,180,320,50);
    NameLabel.setFont(new Font("San Serif",Font.PLAIN,25));
    startupname.setBounds (250,180,320,50);
    startupname.setFont(new Font("San Serif",Font.PLAIN, 25));

    FoundLabel.setBounds (30,260,320,50);
    FoundLabel.setFont(new Font("San Serif",Font.PLAIN, 25));
    founder.setBounds(250,260,320,50);
    founder.setFont(new Font("San Serif",Font.PLAIN, 25));

    DateLabel.setBounds(30,340,320,50);
    DateLabel.setFont(new Font("San Serif",Font.PLAIN, 25));
    date.setBounds(250,340,320,50);
    date.setFont(new Font("San Serif",Font.PLAIN, 25));

    DomLabel.setBounds(30,420,320,50);
    DomLabel.setFont(new Font("San Serif",Font.PLAIN, 25));
    domain.setBounds(250,420,320,50);
    domain.setFont(new Font("San Serif",Font.PLAIN, 25));

    EmaLabel.setBounds(30,500,320,50);
    EmaLabel.setFont(new Font("San Serif",Font.PLAIN, 25));
    email.setBounds(250,500,320,50);
    email.setFont(new Font("San Serif",Font.PLAIN, 25));

    ContLabel.setBounds(30,580,320,50);
    ContLabel.setFont(new Font("San Serif",Font.PLAIN, 25));
    contact.setBounds(250,580,320,50);
    contact.setFont(new Font("San Serif",Font.PLAIN, 25));

    submit.setBounds(210,750,300,70);
    submit.setFont(new Font(Font.DIALOG,Font.PLAIN,18));
    submit.setBackground(new Color(78, 84, 94));
    submit.setForeground(Color.white);

    add(LabelID);add(stpID);
    add(NameLabel);add(startupname);
    add(FoundLabel);add(founder);
    add(DateLabel);add(date);
    add(DomLabel);add(domain);
    add(EmaLabel);add(email);
    add(ContLabel);add(contact);
    add(submit);

    submit.addActionListener(new ActionListener()
    {
        public void actionPerfomed(ActionEvent e)
        {
            formSubmit();
        }

            @Override
            public void actionPerformed(ActionEvent ae)
            {
                formSubmit();
            }
    }
    );

    add(status,BorderLayout.SOUTH);
    setBorder(new TitledBorder(new LineBorder(Color.lightGray, 1),"Register a new Startup"));
    setSize(600,500);
    setLayout(null);
    add(inputForm);
    setSize(800,700);
    setVisible(true);
  }

  public void formSubmit()
  {
      try
      {
          String newID = stpID.getText();
          String newname = startupname.getText();
          String newfounder = founder.getText();
          String newdate = date.getText();
          String newdom = domain.getText();
          String newema = email.getText();
          String newcont = contact.getText();

          String[] values = {newID, newname, newfounder, newdate, newdom, newema, newcont};

          if (query.startupRegistration(values))
          {
              statusIndicator.setText("Insert Success");
              status.setBackground(new Color(1, 163, 55));
        }
          else
          {
              status.setBackground(new Color(140, 0, 0));
              statusIndicator.setText("Insert Failed");
          }
      } catch (NumberFormatException ex)
      {

      }
      

    }

}
