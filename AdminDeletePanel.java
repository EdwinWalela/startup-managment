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
    JComboBox startupIDcb;

    JPanel searchBar = new JPanel();
    JPanel resultBar = new JPanel();

    JPanel status = new JPanel();

    JLabel statusIndicator = new JLabel();

    JButton updateBtn = new JButton("Delete");

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


    public AdminDeletePanel(Query newQuery){
        query = newQuery;

        try{
            if(!query.conn.isClosed()){
                statusIndicator.setText("DB connection established (idle)");
                statusIndicator.setFont(new Font("San-Serif",Font.PLAIN,15));
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


        updateBtn.setPreferredSize(new Dimension(100,50));
        updateBtn.setBackground(new Color(175, 26, 3));
        updateBtn.setForeground(Color.white);
        updateBtn.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));

        resultBar.setLayout(new BorderLayout());
        resultBar.add(updateBtn,BorderLayout.SOUTH);
        resultBar.setBorder(new TitledBorder(new LineBorder(Color.lightGray, 1),"Result"));

        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //delete data
                //updateData(startupIDcb.getSelectedItem().toString());
            }
        });

        setLayout(new BorderLayout());
        add(status,BorderLayout.SOUTH);
        add(searchBar,BorderLayout.NORTH);
        add(resultBar,BorderLayout.CENTER);
    }

}
