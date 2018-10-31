import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class AdminUpdatePanel extends JPanel {
    Query query;

    public AdminUpdatePanel(Query newQuery){
        query = newQuery;

        //TODO:

        setBorder(new TitledBorder(new LineBorder(Color.lightGray, 1),"Update details"));
    }

}
