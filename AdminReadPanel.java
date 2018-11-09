import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class AdminReadPanel extends JPanel
{
    Query query;

    ResultSet rs;

    JPanel status = new JPanel();
    JLabel statusIndicator = new JLabel();

    public AdminReadPanel(Query newQuery)
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
            System.out.println(e.getMessage());
        }

        status.setPreferredSize(new Dimension(1300, 30));
        status.add(statusIndicator);

        JLabel tablelabel = new JLabel("Table name");
        JLabel criteria1label = new JLabel("Field name");
        JLabel criteria2label = new JLabel("Value");

        JTextField table = new JTextField();
        JTextField criteria1 = new JTextField();
        JTextField criteria2 = new JTextField();

        JButton search = new JButton("Search");

        tablelabel.setBounds(50, 100, 200, 50);
        tablelabel.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
        criteria1label.setBounds(300, 100, 200, 50);
        criteria1label.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
        criteria2label.setBounds(550, 100, 200, 50);
        criteria2label.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));

        search.setBounds(800, 180, 200, 50);
        search.setFont(new Font(Font.DIALOG,Font.PLAIN,18));
        search.setBackground(new Color(78, 84, 94));
        search.setForeground(Color.white);

        table.setBounds(50, 180, 200, 50);
        table.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
        criteria1.setBounds(300, 180, 200, 50);
        criteria1.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
        criteria2.setBounds(550, 180, 200, 50);
        criteria2.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));

        setBorder(new TitledBorder(new LineBorder(Color.lightGray, 1),"Search"));

        search.addActionListener(new ActionListener()
                                 {

                                     public void actionPerformed(ActionEvent e)
                                     {
                                         String crit1 = criteria1.getText();
                                         String crit2 = criteria2.getText();
                                         String Table = table.getText();

                                         if (Table == "startups")
                                         {
                                             String[] columns = {"Name", "Founder","DOJ","Domain","Email address", "Contact", "Startup ID"};

                                             String[][] data = new String[query.tableCount(Table)][7];
                                             try
                                             {
                                                 int row = 0;
                                                 while (rs.next()) {
                                                     for (int col = 0; col < 7; col++) {
                                                         data[row][col] = rs.getString(col + 1);
                                                     }
                                                     row++;
                                                 }
                                             }
                                             catch (SQLException r)
                                             {
                                                 System.out.println(r.getMessage());
                                             }

                                             JTable resultTable = new JTable(data, columns);
                                             resultTable.setRowHeight(35);
                                             resultTable.setBounds(10, 300, 850, data.length * resultTable.getRowHeight());
                                             resultTable.setFont(new Font("Sans-Serif", Font.PLAIN, 20));

                                             add(resultTable);
                                         }

                                         else if (Table == "users")
                                         {
                                             String[] columns = {"User ID", "Startup ID","Username","Email address"};

                                             String[][] data = new String[query.tableCount(Table)][7];
                                             try
                                             {
                                                 int row = 0;
                                                 while (rs.next()) {
                                                     for (int col = 0; col < 5; col++) {
                                                         data[row][col] = rs.getString(col + 1);
                                                     }
                                                     row++;
                                                 }
                                             }
                                             catch (SQLException r)
                                             {
                                                 System.out.println(r.getMessage());
                                             }

                                             JTable resultTable = new JTable(data, columns);
                                             resultTable.setRowHeight(35);
                                             resultTable.setBounds(10, 300, 850, data.length * resultTable.getRowHeight());
                                             resultTable.setFont(new Font("Sans-Serif", Font.PLAIN, 20));
                                             add(resultTable);
                                         }

                                         rs = query.fetchData(Table, crit1, crit2);

                                     }
                                 }
        );
        setLayout(null);
        add(tablelabel); add(table);
        add(criteria1label); add(criteria1);
        add(criteria2label); add(criteria2);
        add(search);
        add(status,BorderLayout.SOUTH);
    }

}

