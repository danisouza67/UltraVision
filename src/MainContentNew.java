import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainContentNew extends JPanel implements ActionListener {

    private BorderLayout baseBorderLayout = new BorderLayout();
    private JPanel basePanelCenter = new JPanel();
    private JPanel bottomMenuNew;
    private JButton buttonNewOne = new JButton();
    private JButton buttonNewTwo = new JButton();
    String selectedTitleString;

    //Components for center panel
    private JTextField jtTitleId;
    private JTextField jtTitle;
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


    public MainContentNew(String selectedTitleString, String userFullName) {

        setSelectedTitleString(selectedTitleString);

        System.out.println("Welcome " + userFullName + ". You r in MainContentNew session: " + selectedTitleString + " :)");
        this.setLayout(baseBorderLayout);


        this.add(basePanelCenter, BorderLayout.CENTER);
        basePanelCenter.setPreferredSize(new Dimension(663,443));
        basePanelCenter.setLayout(new GridBagLayout());
        basePanelCenter.setOpaque(true);


    /*     for testing propose only

        String labels[] = {"Title ID", "Title", "Media Type", "Type", "Abbreviation", "Band/Director", "Description", "Genre", "Year of release", "Price", "Rent ID"};
        int numPairs = labels.length;
        System.out.println(labels.length);

        for (int i = 0; i < numPairs; i++){
            JLabel l = new JLabel(labels[i], JLabel.TRAILING);
            basePanelCenter.add(l);
            JTextField textField = new JTextField(10);
            l.setLabelFor(textField);
            basePanelCenter.add(textField);
        }
    */



        GridBagConstraints gc2 = new GridBagConstraints();

        //reference only
//      String columnNames[] = {"Title ID", "Title", "Media Type", "Type", "Abbreviation", "Band/Director", "Description", "Genre", "Year of release", "Price", "Rent ID"};


        ////////////////FIRST LINE///////////////////

        JLabel jlTitleId = new JLabel("Title_ID:");
//        jtTitleId = new JTextField(50);
//        jtTitleId.setOpaque(true);
//        jlTitleId.setOpaque(true);
//        jlTitleId.setLabelFor(jtTitleId);
        jeTitleId = new JEditorPane("int", "Type Here:");
//        jeTitleId.setEditable(false);

        JLabel jlTitle = new JLabel("Title:");
//        jtTitle = new JTextField(100);
//        jtTitle.setPreferredSize(new Dimension(20,50));
//        JButton btnTitle = new JButton("Tittle");
        jeTitle = new JEditorPane("text", "Type Here the Title:");
//        jlTitle.setOpaque(true);
//        jeTitle.setOpaque(true);
//        jeTitle.setEditable(true);

        JLabel jlMediaType = new JLabel("Media Type:");
        String[] jcMediaTypeStrings = {"CD", "DVD", "Blue Ray"};
        jcMediaType = new JComboBox(jcMediaTypeStrings);
        jlMediaType.setOpaque(true);
        jcMediaType.setOpaque(true);
//        int m = getMediaType(dataSelected[2]);
//        jcMediaType.setSelectedIndex(m);

        JLabel jlType = new JLabel("Type:");
        String[] jcTypeStrings = {"Music", "Live Concert", "Movie", "Box Set"};
        jcType = new JComboBox(jcTypeStrings);
//        int n = getType(dataSelected[3]);
//        jcMediaType.setSelectedIndex(n);

        gc2.weightx = 1;
        gc2.weighty = 1;
        gc2.fill = GridBagConstraints.NONE;


/*      Let's go unuse this, not necessary at the momento
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
        basePanelCenter.add(jlTitle, gc2);
        gc2.gridx = 1;
        gc2.gridy = 0;
        gc2.anchor = GridBagConstraints.LINE_START;
        basePanelCenter.add(jeTitle, gc2);

        gc2.gridx = 4;
        gc2.gridy = 0;
        gc2.anchor = GridBagConstraints.LINE_END;
        basePanelCenter.add(jlMediaType, gc2);
        gc2.gridx = 5;
        gc2.gridy = 0;
        gc2.anchor = GridBagConstraints.LINE_START;
        basePanelCenter.add(jcMediaType, gc2);

/*        gc2.gridx = 8;
        gc2.gridy = 0;
        gc2.anchor = GridBagConstraints.LINE_END;
        basePanelCenter.add(jlType, gc2);
        gc2.gridx = 9;
        gc2.gridy = 0;
        gc2.anchor = GridBagConstraints.LINE_START;
        basePanelCenter.add(jcType, gc2);   */

        /////////////////SECOND LINE/////////////////

        JLabel jlTypeAbbr = new JLabel("Abbreviation:");
        jeTypeAbbr = new JEditorPane("text", "Type Here:");  //being validated at the preparation statements, to update
        jeTypeAbbr.setEditable(false);

        JLabel jlBand = new JLabel("Band / Director:");
        jeBand = new JEditorPane("text", "Type Here:");
        jeBand.setEditable(true);

        JLabel jlDescription = new JLabel("Description:");
        jeDescription = new JEditorPane("text", "Type Here:");
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
        basePanelCenter.add(jlBand, gc2);
        gc2.gridx = 1;
        gc2.gridy = 2;
        gc2.anchor = GridBagConstraints.LINE_START;
        basePanelCenter.add(jeBand, gc2);

        gc2.gridx = 8;
        gc2.gridy = 2;
        gc2.anchor = GridBagConstraints.LINE_END;
        basePanelCenter.add(jlDescription, gc2);
        gc2.gridx = 9;
        gc2.gridy = 2;
        gc2.anchor = GridBagConstraints.LINE_START;
        basePanelCenter.add(jeDescription, gc2);

        ///////////////////THIRD LINE////////////////////

        JLabel jlGenre = new JLabel("Genre:");
        jeGenre = new JEditorPane("text", "Type Here:");
        jeGenre.setEditable(true);

        JLabel jlYearOfRelease = new JLabel("Year of Release:");
        jeYearOfRelease = new JEditorPane("int", "Type Here:");
        jeYearOfRelease.setEditable(true);

        JLabel jlPrice = new JLabel("Price:");
        jePrice = new JEditorPane("int", "Type Here:");
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
        basePanelCenter.add(jlGenre, gc2);
        gc2.gridx = 1;
        gc2.gridy = 4;
        gc2.anchor = GridBagConstraints.LINE_START;
        basePanelCenter.add(jeGenre, gc2);

        gc2.gridx = 4;
        gc2.gridy = 4;
        gc2.anchor = GridBagConstraints.LINE_END;
        basePanelCenter.add(jlYearOfRelease, gc2);
        gc2.gridx = 5;
        gc2.gridy = 4;
        gc2.anchor = GridBagConstraints.LINE_START;
        basePanelCenter.add(jeYearOfRelease, gc2);

        gc2.gridx = 8;
        gc2.gridy = 4;
        gc2.anchor = GridBagConstraints.LINE_END;
        basePanelCenter.add(jlPrice, gc2);
        gc2.gridx = 9;
        gc2.gridy = 4;
        gc2.anchor = GridBagConstraints.LINE_START;
        basePanelCenter.add(jePrice, gc2);

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


        bottomMenuNew = new JPanel();
        //bottomMenuID.setVisible(true);
        this.add(bottomMenuNew, BorderLayout.SOUTH);
        buttonNewOne = new JButton("Update");
        buttonNewOne.addActionListener(this);
        buttonNewOne.setActionCommand("update");
        buttonNewTwo = new JButton("Cancel");
        bottomMenuNew.add(buttonNewOne);
        bottomMenuNew.add(buttonNewTwo);

//        this.validate();
//        this.repaint();
//        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equalsIgnoreCase("update")){

            insertNewBank();
        }
    }

    private void insertNewBank() {
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

        if (jeTitle.getText().trim().isEmpty() || jeTitle.getText().trim().equalsIgnoreCase("Type Here the Title:")){
            JOptionPane.showMessageDialog(null, "You haven't typed the Title :/");
            return;
        }else if (jcMediaType.getSelectedItem().toString().isEmpty()){
            JOptionPane.showMessageDialog(null, "You haven't selected the Media Type :/");
            return;
        }else if (jcType.getSelectedItem().toString().isEmpty()){
            JOptionPane.showMessageDialog(null, "You haven't selected the Type :/");
            return;
        }else if (jeBand.getText().trim().isEmpty() || jeBand.getText().trim().equalsIgnoreCase("Type Here:")){
            JOptionPane.showMessageDialog(null, "You haven't typed the Band or Director name :/");
            return;
        }else if (jeDescription.getText().trim().isEmpty() || jeDescription.getText().trim().equalsIgnoreCase("Type Here:")){
            JOptionPane.showMessageDialog(null, "You haven't typed the Description :/");
            return;
        }else if (jeGenre.getText().trim().isEmpty() || jeGenre.getText().trim().equalsIgnoreCase("Type Here:")){
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
            }

        try{

            model.insertInTheBeach2D(getSelectedTitleString(), allGuys);

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
