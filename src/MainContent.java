import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class MainContent extends JPanel{

    private BorderLayout baseBorderLayout = new BorderLayout();
    private GridBagLayout baseLayout = new GridBagLayout();

    private JPanel basePanel = new JPanel();
    private JPanel basePanel1 = new JPanel();
    private JPanel basePanel2 = new JPanel();
    JPanel bottomMenu = new JPanel();
    private JPanel bottomMenu2 = new JPanel();
    private JTable titlesTable = new JTable();
    private JScrollPane titlesTPane = new JScrollPane();
    JButton buttonOne = new JButton();
    JButton buttonTwo = new JButton();
    //private JTextArea;

    String userAdmin;
    UVModel model = new UVModel();
    String[][] data;

    MainContentID createInterfaceForID;
    VLCatalog listener;


    public MainContent(String userFullName, VLCatalog listener){
        this.listener = listener;
        //first content (center) style
        basePanel = new JPanel();

        setUserAdmin(userFullName);
        //System.out.println(" " + session.getLoginFname() + " " + session.getLoginLname());
        JLabel jlWelcome = new JLabel("Welcome " + userFullName);
        JLabel jlChooseOption = new JLabel("|  Choose an option in Menu Bar");
        /*JLabel type = new JLabel("type");
        JLabel typeAbbr = new JLabel("Type Abbreviation");
        JEditorPane description = new JEditorPane("text", "Description blablablalba bla bal bal bal lsa lbvsas"); //Pane("Description blablablalba bla bal bal bal lsa lbvsas");
        description.setEditable(false);
        JScrollPane editorScroll = new JScrollPane(description);
        editorScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);*/

        /*title.setPreferredSize(new Dimension(500, 10));
        mediaType.setPreferredSize(new Dimension(25,25));
        type.setPreferredSize(new Dimension(35, 25));
        editorScroll.setPreferredSize(new Dimension(250, 145));
        editorScroll.setMinimumSize(new Dimension(10,10));
        typeAbbr.setPreferredSize(new Dimension(50,50));*/

        //this.setBase(base);
        this.setLayout(baseLayout);
        this.add(basePanel);
        //setGuysSize();

        basePanel.add(jlWelcome);
        basePanel.add(jlChooseOption);
        /*basePanel.add(type);
        basePanel.add(typeAbbr);*/


//        description.setEditable(false);
        //description.add();
//        basePanel.add(description);
    }


    public void createContentTitlesAdmin(String selectedTitleString) {

        basePanel2.setVisible(false);
//        basePanel = new JPanel();
//        bottomMenu2 = new JPanel();
//        basePanel2 = new JPanel();

        System.out.println("Title category MainContent: " + selectedTitleString);

        try {
            data = model.selectInTheBeach2D(selectedTitleString);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(data.length);
        System.out.println(data[0].length);

        //System.out.println(rs.getString("title") + "\t" + rs.getString("title_id") + "\t" + rs.getString("mediaType") + "\t" + rs.getString("type") + "\t" + rs.getString("typeAbbr") + "\t" + rs.getString("band") + "\t" + rs.getString("description") + "\t" + rs.getString("genre") + "\t" + rs.getString("yearOfRelease") + "\t" + rs.getString("price") + "\t" + rs.getString("fk_Live_rent_id"));
        //reference only
        String[] columnNames = {"Title ID", "Title", "Media Type", "Type", "Abbreviation", "Band", "Description", "Genre", "Year of release", "Price", "Rent ID"};

        //data table
        titlesTable = new JTable(data, columnNames);
        titlesTPane = new JScrollPane(titlesTable);
        buttonOne = new JButton("Select");
        buttonOne.addActionListener(listener);
        buttonOne.setActionCommand("select");
        buttonTwo = new JButton("Create New");
        buttonTwo.setActionCommand("createNewButton");
        buttonTwo.addActionListener(listener);

        basePanel.setVisible(false);
        basePanel.isEnabled();
        basePanel1 = new JPanel();
        basePanel1.setVisible(true);
        this.setLayout(baseBorderLayout);
        this.add(basePanel1, BorderLayout.CENTER);
        basePanel1.setPreferredSize(new Dimension(663,443));
        basePanel1.add(titlesTPane);
        //base.add(typeAbbr);

        bottomMenu2.setVisible(false);
        bottomMenu = new JPanel();
        bottomMenu.setVisible(true);
        this.add(bottomMenu, BorderLayout.SOUTH);
        bottomMenu.add(buttonOne);
        bottomMenu.add(buttonTwo);

        /*for (int i = 0; i < data.length; i++){
            System.out.println();
            for (int j = 0; data[i] != null && j < data[i].length; j++){
                System.out.print(data[i][j] + " ");
            }
        }*/
        //System.out.println(data[0][0] + data[0][1] + data[0][2]);
        //System.out.println(data[1][0] + data[1][1] + data[1][2]);
        //System.out.println(data[2][0] + data[2][1] + data[2][2]);
    }

   /* @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equalsIgnoreCase("select")){


            try{
                System.out.println("Selected ID: " + titlesTable.getValueAt(titlesTable.getSelectedRow(), 0));
                System.out.println("Selected Type: " + titlesTable.getValueAt(titlesTable.getSelectedRow(), 3));
            }catch (Exception e){
                System.out.println("no row selected");
            }
                String id = titlesTable.getValueAt(titlesTable.getSelectedRow(), 0).toString();
                String type = titlesTable.getValueAt(titlesTable.getSelectedRow(), 3).toString();
                //titlesTPane.setVisible(false);
                //data = model.selectInTheBeachAlone(id, typeAbbr);
                //System.out.println("getData.length" + getData().length);
                String[] dataSelected = new String[data[0].length];
                fetchSelectedData(data, id, type, dataSelected);

               *//* for (int i=0; i < dataSelected.length; i++){

                    System.out.print(dataSelected[i] + "\t ");
                }*//*

               createInterfaceForID = new MainContentID(dataSelected);

//                createInterfaceForID(dataSelected);


        }else if(actionEvent.getActionCommand().equalsIgnoreCase("createNewButton")){

        }
    }*/

    private void createInterfaceForID(String[] dataSelected) {
//      Method to create JThings into MainContent

        titlesTPane.setVisible(false);
        basePanel.setVisible(false);
        basePanel1.setVisible(false);
        basePanel2 = new JPanel();
        basePanel2.setVisible(true);
        this.setLayout(baseBorderLayout);
        this.add(basePanel2, BorderLayout.CENTER);
        basePanel2.setPreferredSize(new Dimension(663,443));
        basePanel2.setLayout(new GridBagLayout());

        GridBagConstraints gc1 = new GridBagConstraints();

        bottomMenu.setVisible(false);
        bottomMenu2 = new JPanel();
        bottomMenu2.setVisible(true);
        this.add(bottomMenu2, BorderLayout.SOUTH);
        buttonOne = new JButton("Update");
        buttonTwo = new JButton("Cancel");
        bottomMenu2.add(buttonOne);
        bottomMenu2.add(buttonTwo);

        System.out.println();
        System.out.println("deu bon");

        //reference only
//      String columnNames[] = {"Title ID", "Title", "Media Type", "Type", "Abbreviation", "Band", "Description", "Genre", "Year of release", "Price", "Rent ID"};


         ////////////////FIRST LINE///////////////////

        JLabel jlTitleId = new JLabel("Title_ID:");
        JEditorPane jeTitleId = new JEditorPane("int", dataSelected[0]);
        jeTitleId.setEditable(false);

        JLabel jlTitle = new JLabel("Title:");
        JEditorPane jeTitle = new JEditorPane("text", dataSelected[1]);
        jeTitle.setEditable(true);

        JLabel jlMediaType = new JLabel("Media Type:");
        String[] jcMediaTypeStrings = {"CD", "DVD", "Blue Ray"};
        JComboBox jcMediaType = new JComboBox(jcMediaTypeStrings);
        int m = getMediaType(dataSelected[2]);
        jcMediaType.setSelectedIndex(m);

        JLabel jlType = new JLabel("Type:");
        String[] jcTypeStrings = {"Music", "Live Concert", "Movie", "Box Set"};
        JComboBox jcType = new JComboBox(jcTypeStrings);
        int n = getType(dataSelected[3]);
        jcMediaType.setSelectedIndex(n);

        gc1.gridx = 0;
        gc1.gridy = 0;
        gc1.weightx = 1;
        gc1.weighty = 1;
        gc1.fill = GridBagConstraints.NONE;
        gc1.anchor = GridBagConstraints.LINE_END;
        basePanel2.add(jlTitleId, gc1);
        gc1.gridx = 1;
        gc1.gridy = 0;
        gc1.anchor = GridBagConstraints.LINE_START;
        basePanel2.add(jeTitleId, gc1);

        gc1.gridx = 0;
        gc1.gridy = 1;
        gc1.anchor = GridBagConstraints.LINE_END;
        basePanel2.add(jlTitle, gc1);
        gc1.gridx = 1;
        gc1.gridy = 1;
        gc1.anchor = GridBagConstraints.LINE_START;
        basePanel2.add(jeTitle, gc1);

        gc1.gridx = 0;
        gc1.gridy = 2;
        gc1.anchor = GridBagConstraints.LINE_END;
        basePanel2.add(jlMediaType, gc1);
        gc1.gridx = 1;
        gc1.gridy = 2;
        gc1.anchor = GridBagConstraints.LINE_START;
        basePanel2.add(jcMediaType, gc1);

        gc1.gridx = 0;
        gc1.gridy = 3;
        gc1.anchor = GridBagConstraints.LINE_END;
        basePanel2.add(jlType, gc1);
        gc1.gridx = 1;
        gc1.gridy = 3;
        gc1.anchor = GridBagConstraints.LINE_START;
        basePanel2.add(jcType, gc1);

        /////////////////SECOND LINE/////////////////

        JLabel jlTypeAbbr = new JLabel("Abbreviation:");
        JEditorPane jeTypeAbbr = new JEditorPane("text", dataSelected[4]);  //being validated at the preparation statements, to update
        jeTypeAbbr.setEditable(false);

        JLabel jlBand = new JLabel("Band:");
        JEditorPane jeBand = new JEditorPane("text", dataSelected[5]);
        jeBand.setEditable(true);

        JLabel jlDescription = new JLabel("Description:");
        JEditorPane jeDescription = new JEditorPane("text", dataSelected[6]);
        jeDescription.setEditable(true);
//        jeDescription.setPreferredSize(new Dimension(50, 50));
        JScrollPane descriptionScroll = new JScrollPane(jeDescription);
        descriptionScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //jeDescription.setPreferredSize(new Dimension(30,30));

        gc1.gridx = 4;
        gc1.gridy = 0;
        gc1.anchor = GridBagConstraints.LINE_END;
        basePanel2.add(jlTypeAbbr, gc1);
        gc1.gridx = 5;
        gc1.gridy = 0;
        gc1.anchor = GridBagConstraints.LINE_START;
        basePanel2.add(jeTypeAbbr, gc1);

        gc1.gridx = 4;
        gc1.gridy = 1;
        gc1.anchor = GridBagConstraints.LINE_END;
        basePanel2.add(jlBand, gc1);
        gc1.gridx = 5;
        gc1.gridy = 1;
        gc1.anchor = GridBagConstraints.LINE_START;
        basePanel2.add(jeBand, gc1);

        gc1.gridx = 4;
        gc1.gridy = 3;
        gc1.anchor = GridBagConstraints.LINE_END;
        basePanel2.add(jlDescription, gc1);
        gc1.gridx = 5;
        gc1.gridy = 3;
        gc1.anchor = GridBagConstraints.LINE_START;
        basePanel2.add(jeDescription, gc1);

        ///////////////////THIRD LINE//////////////////

        JLabel jlGenre = new JLabel("Genre:");
        JEditorPane jeGenre = new JEditorPane("text", dataSelected[7]);
        jeGenre.setEditable(true);

        JLabel jlYearOfRelease = new JLabel("Year of Release:");
        JEditorPane jeYearOrRelease = new JEditorPane("int", dataSelected[8]);
        jeYearOrRelease.setEditable(true);

        JLabel jlPrice = new JLabel("Price:");
        JEditorPane jePrice = new JEditorPane("int", dataSelected[9]);
        jePrice.setEditable(true);

        JLabel jlRentID = new JLabel("Rent ID:");
        JEditorPane jeRentID = new JEditorPane("int", dataSelected[10]);
        jeRentID.setEditable(true);

        gc1.gridx = 8;
        gc1.gridy = 0;
//        gc1.weightx = 1;
//        gc1.weighty = 1;
        gc1.fill = GridBagConstraints.NONE;
        gc1.anchor = GridBagConstraints.LINE_END;
        basePanel2.add(jlGenre, gc1);
        gc1.gridx = 9;
        gc1.gridy = 0;
        gc1.anchor = GridBagConstraints.LINE_START;
        basePanel2.add(jeGenre, gc1);

        gc1.gridx = 8;
        gc1.gridy = 1;
        gc1.anchor = GridBagConstraints.LINE_END;
        basePanel2.add(jlYearOfRelease, gc1);
        gc1.gridx = 9;
        gc1.gridy = 1;
        gc1.anchor = GridBagConstraints.LINE_START;
        basePanel2.add(jeYearOrRelease, gc1);

        gc1.gridx = 8;
        gc1.gridy = 2;
        gc1.anchor = GridBagConstraints.LINE_END;
        basePanel2.add(jlPrice, gc1);
        gc1.gridx = 9;
        gc1.gridy = 2;
        gc1.anchor = GridBagConstraints.LINE_START;
        basePanel2.add(jePrice, gc1);

        gc1.gridx = 8;
        gc1.gridy = 3;
        gc1.anchor = GridBagConstraints.LINE_END;
        basePanel2.add(jlRentID, gc1);
        gc1.gridx = 9;
        gc1.gridy = 3;
        gc1.anchor = GridBagConstraints.LINE_START;
        basePanel2.add(jeRentID, gc1);



    }

    private int getType(String s) {

        int i = 0;
        switch (s){
            case "Music":
                i = 0;
                break;
            case "Live Concert":
                i = 1;
                break;
            case "Movie":
                i = 2;
                break;
            case "Box Set":
                i = 3;
                break;
        }

        return i;

    }

    private int getMediaType(String s) {

        int i = 0;
        switch (s){
            case "CD":
                i = 0;
                break;
            case "DVD":
                i = 1;
                break;
            case "Blue Ray":
                i = 2;
                break;
        }

        return i;
    }

    private void fetchSelectedData(String[][] data, String id, String type, String[] dataSelected) {

        System.out.println("row_id: " + id);

        for(int i=0; i < data.length; i++){
            //System.out.println();
            System.out.println("data " + data[i][0] + " equals id? " + data[i][0].equals(id));
            if (data[i][0].equals(id)){
                System.out.print("dataSelected: ");
                for (int k=0; k < dataSelected.length; k++){
                    dataSelected[k] = data[i][k];
                    System.out.print(dataSelected[k] + "\t ");
                    //break;
                }
                /*for (int j=0; j < data[i].length; j++){

                    if (data[i][j].equals(type)){
                        for (int k=0; i < dataSelected.length; i++){
                            dataSelected[k] = data[i][k];
                            System.out.print(dataSelected[k] + "\t ");
                        }
                    }
                }*/
            }
            //break;
        }

        //return data[i];
    }



    public String getUserAdmin() {
        return userAdmin;
    }

    public void setUserAdmin(String userAdmin) {
        this.userAdmin = userAdmin;
    }



    public String[] getData(int i) {
        return data[i];
    }

    public void setData(String[][] data) {
        this.data = data;
    }

    public JTable getTitlesTable() {
        return titlesTable;
    }

    public void setTitlesTable(JTable titlesTable) {
        this.titlesTable = titlesTable;
    }


    public void setButtonOne(JButton buttonOne) {
        this.buttonOne = buttonOne;
    }



}
