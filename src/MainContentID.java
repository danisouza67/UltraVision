import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainContentID extends JPanel implements ActionListener {


    private BorderLayout baseBorderLayout = new BorderLayout();
    private GridBagLayout baseLayout = new GridBagLayout();

    private JPanel basePanel = new JPanel();
    private JPanel basePanelNorth = new JPanel();
    private JPanel basePanel2 = new JPanel();
    private JPanel bottomMenuID = new JPanel();
    private JTable titlesTable = new JTable();
    private JScrollPane titlesTPane = new JScrollPane();
    private JButton buttonIDOne = new JButton();
    private JButton buttonIDTwo = new JButton();
    private String[] dataSelected;
    private String selectedTitleString;

    //content components
    private JComboBox jcMediaType;
    private JComboBox jcType;
    private JEditorPane jeTypeAbbr;
    private JEditorPane jeBand;
    private JEditorPane jeDescription;
    private JEditorPane jeGenre;
    private JEditorPane jeYearOfRelease;
    private JEditorPane jePrice;
    private JEditorPane jeRentID;
    private JEditorPane jeTitleId;
    private JEditorPane jeTitle;

    UVModel model = new UVModel();

    public MainContentID(String selectedTitleString, String[] dataSelected, String userFullName){

        this.selectedTitleString = selectedTitleString;
        this.dataSelected = dataSelected;
        this.setLayout(baseBorderLayout);

        // Content Center Heading
/*        basePanelNorth = new JPanel();
        this.add(basePanelNorth, BorderLayout.NORTH);
        JLabel jlWelcome = new JLabel("Welcome " + userFullName);
        //JLabel jlChooseOption = new JLabel("|  Choose an option in Menu Bar");
        basePanelNorth.add(jlWelcome);*/

        this.add(basePanel2, BorderLayout.CENTER);
        basePanel2.setPreferredSize(new Dimension(663,443));
        basePanel2.setLayout(new GridBagLayout());

        GridBagConstraints gc2 = new GridBagConstraints();

        //reference only
//      String columnNames[] = {"Title ID", "Title", "Media Type", "Type", "Abbreviation", "Band/Director", "Description", "Genre", "Year of release", "Price", "Rent ID"};


        ////////////////FIRST LINE///////////////////

        JLabel jlTitleId = new JLabel("Title_ID:");
//        jtTitleId = new JTextField(50);
//        jtTitleId.setOpaque(true);
//        jlTitleId.setOpaque(true);
//        jlTitleId.setLabelFor(jtTitleId);
        jeTitleId = new JEditorPane("int", dataSelected[0]);
//        jeTitleId.setEditable(false);

        JLabel jlTitle = new JLabel("Title:");
//        jtTitle = new JTextField(100);
//        jtTitle.setPreferredSize(new Dimension(20,50));
//        JButton btnTitle = new JButton("Tittle");
        jeTitle = new JEditorPane("text", dataSelected[1]);
//        jlTitle.setOpaque(true);
//        jeTitle.setOpaque(true);
//        jeTitle.setEditable(true);

        JLabel jlMediaType = new JLabel("Media Type:");
        String[] jcMediaTypeStrings = {"CD", "DVD", "Blue Ray"};
        jcMediaType = new JComboBox(jcMediaTypeStrings);
        jlMediaType.setOpaque(true);
        jcMediaType.setOpaque(true);
        int m = getMediaType(dataSelected[2]);
        jcMediaType.setSelectedIndex(m);

        JLabel jlType = new JLabel("Type:");  //dataselect[3]
        String[] jcTypeStrings = {"Music", "Live Concert", "Movie", "Box Set"};
        jcType = new JComboBox(jcTypeStrings);
//        int n = getType(dataSelected[3]);
//        jcMediaType.setSelectedIndex(n);

        gc2.weightx = 1;
        gc2.weighty = 1;
        gc2.fill = GridBagConstraints.NONE;


/*      Let's go unuse somethings, not necessary at the momento
        gc2.gridx = 0;
        gc2.gridy = 0;
        gc2.anchor = GridBagConstraints.LINE_END;
        basePanelCenter.add(jlTitleId, gc2);
        gc2.gridx = 1;
        gc2.gridy = 0;
        gc2.anchor = GridBagConstraints.LINE_START;
        basePanelCenter.add(jeTitleId, gc2);
//        basePanelCenter.add(btnTitle, gc2);*/

        gc2.gridx = 0;
        gc2.gridy = 0;
        gc2.anchor = GridBagConstraints.LINE_END;
        basePanel2.add(jlTitle, gc2);
        gc2.gridx = 1;
        gc2.gridy = 0;
        gc2.anchor = GridBagConstraints.LINE_START;
        basePanel2.add(jeTitle, gc2);

        gc2.gridx = 8;
        gc2.gridy = 0;
        gc2.anchor = GridBagConstraints.LINE_END;
        basePanel2.add(jlMediaType, gc2);
        gc2.gridx = 9;
        gc2.gridy = 0;
        gc2.anchor = GridBagConstraints.LINE_START;
        basePanel2.add(jcMediaType, gc2);

/*        gc2.gridx = 8;
        gc2.gridy = 0;
        gc2.anchor = GridBagConstraints.LINE_END;
        basePanelCenter.add(jlType, gc2);
        gc2.gridx = 9;
        gc2.gridy = 0;
        gc2.anchor = GridBagConstraints.LINE_START;
        basePanelCenter.add(jcType, gc2);   */

        /////////////////SECOND LINE/////////////////

        JLabel jlTypeAbbr = new JLabel("Abbreviation:"); //dataselect[4]
        jeTypeAbbr = new JEditorPane("text", "Type Here:");  //being validated at the preparation statements, to update
        jeTypeAbbr.setEditable(false);

        JLabel jlBand = new JLabel("Band / Director:"); //dataselect[5]
        jeBand = new JEditorPane("text", dataSelected[5]);
        jeBand.setEditable(true);

        JLabel jlDescription = new JLabel("Description:"); //dataselect[6]
        jeDescription = new JEditorPane("text", dataSelected[6]);
        jeDescription.setEditable(true);
//        jeDescription.setPreferredSize(new Dimension(50, 50));
        JScrollPane descriptionScroll = new JScrollPane(jeDescription);
        descriptionScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //jeDescription.setPreferredSize(new Dimension(30,30));

/*        gc2.gridx = 4;
        gc2.gridy = 0;
        gc2.anchor = GridBagConstraints.LINE_END;
        basePanelCenter.add(jlTypeAbbr, gc2);
        gc2.gridx = 5;
        gc2.gridy = 0;
        gc2.anchor = GridBagConstraints.LINE_START;
        basePanelCenter.add(jeTypeAbbr, gc2);*/

        gc2.gridx = 0;
        gc2.gridy = 2;
        gc2.anchor = GridBagConstraints.LINE_END;
        basePanel2.add(jlBand, gc2);
        gc2.gridx = 1;
        gc2.gridy = 2;
        gc2.anchor = GridBagConstraints.LINE_START;
        basePanel2.add(jeBand, gc2);

        gc2.gridx = 8;
        gc2.gridy = 2;
        gc2.anchor = GridBagConstraints.LINE_END;
        basePanel2.add(jlDescription, gc2);
        gc2.gridx = 9;
        gc2.gridy = 2;
        gc2.anchor = GridBagConstraints.LINE_START;
        basePanel2.add(jeDescription, gc2);

        ///////////////////THIRD LINE////////////////////

        JLabel jlGenre = new JLabel("Genre:"); //dataselect[7]
        jeGenre = new JEditorPane("text", dataSelected[7]);
        jeGenre.setEditable(true);

        JLabel jlYearOfRelease = new JLabel("Year of Release:"); //dataselect[8]
        jeYearOfRelease = new JEditorPane("int", dataSelected[8]);
        jeYearOfRelease.setEditable(true);

        JLabel jlPrice = new JLabel("Price:"); //dataselect[9]
        jePrice = new JEditorPane("int", dataSelected[9]);
        jePrice.setEditable(true);

        //not using
//        JLabel jlRentID = new JLabel("Rent ID:");
//        jeRentID = new JEditorPane("int", "Type Here");
//        jeRentID.setEditable(true);

        gc2.gridx = 0;
        gc2.gridy = 4;
//        gc1.weightx = 1;
//        gc1.weighty = 1;
        gc2.fill = GridBagConstraints.NONE;
        gc2.anchor = GridBagConstraints.LINE_END;
        basePanel2.add(jlGenre, gc2);
        gc2.gridx = 1;
        gc2.gridy = 4;
        gc2.anchor = GridBagConstraints.LINE_START;
        basePanel2.add(jeGenre, gc2);

        gc2.gridx = 4;
        gc2.gridy = 4;
        gc2.anchor = GridBagConstraints.LINE_END;
        basePanel2.add(jlYearOfRelease, gc2);
        gc2.gridx = 5;
        gc2.gridy = 4;
        gc2.anchor = GridBagConstraints.LINE_START;
        basePanel2.add(jeYearOfRelease, gc2);

        gc2.gridx = 8;
        gc2.gridy = 4;
        gc2.anchor = GridBagConstraints.LINE_END;
        basePanel2.add(jlPrice, gc2);
        gc2.gridx = 9;
        gc2.gridy = 4;
        gc2.anchor = GridBagConstraints.LINE_START;
        basePanel2.add(jePrice, gc2);

 /*     //RentID, not using, bluah.! xc
        gc2.gridx = 8;
        gc2.gridy = 3;
        gc2.anchor = GridBagConstraints.LINE_END;
        basePanelCenter.add(jlRentID, gc2);
        gc2.gridx = 9;
        gc2.gridy = 3;
        gc2.anchor = GridBagConstraints.LINE_START;
        basePanelCenter.add(jeRentID, gc2);*/

        //////////////////////////////////////END OF GC ///////////////////////////////////////


        bottomMenuID = new JPanel();
        //bottomMenuID.setVisible(true);
        this.add(bottomMenuID, BorderLayout.SOUTH);
        buttonIDOne = new JButton("Update");
        buttonIDOne.addActionListener(this);
        buttonIDOne.setActionCommand("update");
        buttonIDTwo = new JButton("Delete");
        buttonIDTwo.addActionListener(this);
        buttonIDTwo.setActionCommand("delete");
        bottomMenuID.add(buttonIDOne);
        bottomMenuID.add(buttonIDTwo);

        this.repaint();
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

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equalsIgnoreCase("update")){

            updateIDBank(dataSelected[3], dataSelected[0]);
        }
        else if (actionEvent.getActionCommand().equalsIgnoreCase("delete")){

            deleteIDBank(dataSelected[3], dataSelected[0]);
        }
    }

    private void deleteIDBank(String type, String id) {

        int n = JOptionPane.showConfirmDialog(
                null,
                "Do you really want to delete this entry??",
                "An Inane Question",
                JOptionPane.YES_NO_OPTION);

        if(true){
            model.deleteThisHeck(type, id);
        }
        else {
            JOptionPane.showMessageDialog(null, "Operation canceled");
        }


    }

    private void updateIDBank(String type, String id) {

        //      String columnNames[] = {"Title ID", "Title", "Media Type", "Type", "Abbreviation", "Band/Director", "Description", "Genre", "Year of release", "Price", "Rent ID"};

        String[] allGuys = new String[7];
        allGuys[0] = jeTitle.getText();
        allGuys[1] = jcMediaType.getSelectedItem().toString();
        allGuys[2] = jeBand.getText();
        allGuys[3] = jeDescription.getText();
        allGuys[4] = jeGenre.getText();
        allGuys[5] = jeYearOfRelease.getText();
        allGuys[6] = jePrice.getText();



//        System.out.println("Title ID: " + jeTitleId.getText());
        System.out.println("Title: " + allGuys[0]);
        System.out.println("Media Type: " + allGuys[1]);
//        System.out.println("Type: " + jcType.getSelectedItem().toString());
//        System.out.println("Abbreviation: " + jeTypeAbbr.getText());
        System.out.println("Band/Director: " + allGuys[2]);
        System.out.println("Description: " + allGuys[3]);
        System.out.println("Genre: " + allGuys[4]);
        System.out.println("Year of release: " + allGuys[5]);
        System.out.println("Price: " + allGuys[6]);
//        System.out.println("Rent ID: " + jeRentID.getText());

        /*if (jeTitle.getText().trim().isEmpty() || jeTitle.getText().trim().equalsIgnoreCase(allGuys[0])){
            JOptionPane.showMessageDialog(null, "You haven't typed the Title :/");
            return;
        }else if (jcMediaType.getSelectedItem().toString().isEmpty()){
            JOptionPane.showMessageDialog(null, "You haven't selected the Media Type :/");
            return;
        }else if (jeBand.getText().trim().isEmpty() || jeBand.getText().trim().equalsIgnoreCase(allGuys[2])){
            JOptionPane.showMessageDialog(null, "You haven't typed the Band or Director name :/");
            return;
        }else if (jeDescription.getText().trim().isEmpty() || jeDescription.getText().trim().equalsIgnoreCase(allGuys[3])){
            JOptionPane.showMessageDialog(null, "You haven't typed the Description :/");
            return;
        }else if (jeGenre.getText().trim().isEmpty() || jeGenre.getText().trim().equalsIgnoreCase(allGuys[4])){
            JOptionPane.showMessageDialog(null, "You haven't typed the genre :/");
            return;
        }
        try {
            int i = Integer.parseInt(jeYearOfRelease.getText().trim());
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Year of Release must be an integer :/");
            System.out.println(e);
            return;
        }
        try {
            int i = Integer.parseInt(jePrice.getText().trim());
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Price of the Item must be an integer :/");
            System.out.println(e);
            return;
        }*/

        try{

            model.updateInTheBeach2D(type, allGuys, id);

        }catch (Exception e){
            System.out.println("the bank had a problem");
            System.out.println(e.getMessage());
            System.out.println(e.getClass());
            System.out.println(e.getCause());

            //new UVControl();
        }

    }

    public String getSelectedTitleString() {
        return selectedTitleString;
    }

    public void setSelectedTitleString(String selectedTitleString) {
        this.selectedTitleString = selectedTitleString;
    }
}
