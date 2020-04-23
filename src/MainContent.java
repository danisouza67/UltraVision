import sun.applet.Main;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class MainContent extends JPanel {

    private GridBagLayout baseLayout = new GridBagLayout();
    private JPanel base = new JPanel();
    private JLabel title = new JLabel("Title");
    private JLabel mediaType = new JLabel("Media");
    private JLabel type = new JLabel("type");
    private JLabel typeAbbr = new JLabel("Type Abbreviation");
    //private JTextArea;
    private JEditorPane description = new JEditorPane("text", "Description blablablalba bla bal bal bal lsa lbvsas"); //Pane("Description blablablalba bla bal bal bal lsa lbvsas");
    private JScrollPane editorScroll = new JScrollPane(description);
    String[][] data;

    UVModel model = new UVModel();

    public MainContent(){
        this.setLayout(baseLayout);
        this.add(base);
        //setGuysSize();

        base.add(title);
        base.add(mediaType);
        base.add(type);
        base.add(typeAbbr);

        editorScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        description.setEditable(false);
        //description.add();
        base.add(description);
    }

    private void setGuysSize() {
        title.setPreferredSize(new Dimension(500, 10));
        mediaType.setPreferredSize(new Dimension(25,25));
        type.setPreferredSize(new Dimension(35, 25));
        editorScroll.setPreferredSize(new Dimension(250, 145));
        editorScroll.setMinimumSize(new Dimension(10,10));
        typeAbbr.setPreferredSize(new Dimension(50,50));

    }

    public void createContentTitlesAdmin(String selectedTitleString) {

        System.out.println("Title category MainContent: " + selectedTitleString);

        try {
            data = model.selectInTheBeach2D(selectedTitleString);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //System.out.println(data[0][0] + data[0][1] + data[0][2]);
        //System.out.println(data[1][0] + data[1][1] + data[1][2]);
        //System.out.println(data[2][0] + data[2][1] + data[2][2]);
    }
}
