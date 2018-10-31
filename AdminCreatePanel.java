import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class AdminCreatePanel extends JPanel {
    Query query;

    public AdminCreatePanel(Query newQuery){
        query = newQuery;

        //TODO:

        setBorder(new TitledBorder(new LineBorder(Color.lightGray, 1),"Register a new Startup"));
        setVisible(true);
    }

}
