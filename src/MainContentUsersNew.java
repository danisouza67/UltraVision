import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainContentUsersNew extends JPanel implements ActionListener {

    private BorderLayout baseBorderLayout = new BorderLayout();
    private JPanel basePanelCenter = new JPanel();
    private JPanel bottomMenuNew;
    private JButton buttonNewOne = new JButton();
    private JButton buttonNewTwo = new JButton();
    String selectedTitleString;

    //Components for center panel
    private JEditorPane jeFName;
    private JEditorPane jeLName;
    private JEditorPane jeEmail;
    private JEditorPane jePassword;
    private JEditorPane jePhone;
    private JComboBox jcAdmin;
    private JEditorPane jeUsername;


    UVModel model = new UVModel();


    public MainContentUsersNew(String userFullName, VLCatalog vlCatalog) {


        System.out.println("Welcome " + userFullName + ". You r in MainContentNew session: " + selectedTitleString + " :)");
        this.setLayout(baseBorderLayout);


        this.add(basePanelCenter, BorderLayout.CENTER);
        basePanelCenter.setPreferredSize(new Dimension(663,443));
        basePanelCenter.setLayout(new GridBagLayout());
        basePanelCenter.setOpaque(true);

        GridBagConstraints gc2 = new GridBagConstraints();

        ////////////////FIRST LINE///////////////////


        JLabel jlFName = new JLabel("First name:");
        jeFName = new JEditorPane("text", "Type Here the Name:");

        JLabel jlLName = new JLabel("Last name:");
        jeLName = new JEditorPane("text", "Type Here:");

        JLabel jlAdmin = new JLabel("Administrator?:");
        String[] jcAdminStrings = {"Yes", "No"};
        jcAdmin = new JComboBox(jcAdminStrings);


        gc2.weightx = 1;
        gc2.weighty = 1;
        gc2.fill = GridBagConstraints.NONE;


        gc2.gridx = 0;
        gc2.gridy = 0;
        gc2.anchor = GridBagConstraints.LINE_END;
        basePanelCenter.add(jlFName, gc2);
        gc2.gridx = 1;
        gc2.gridy = 0;
        gc2.anchor = GridBagConstraints.LINE_START;
        basePanelCenter.add(jeFName, gc2);

        gc2.gridx = 4;
        gc2.gridy = 0;
        gc2.anchor = GridBagConstraints.LINE_END;
        basePanelCenter.add(jlLName, gc2);
        gc2.gridx = 5;
        gc2.gridy = 0;
        gc2.anchor = GridBagConstraints.LINE_START;
        basePanelCenter.add(jeLName, gc2);

        gc2.gridx = 8;
        gc2.gridy = 0;
        gc2.anchor = GridBagConstraints.LINE_END;
        basePanelCenter.add(jlAdmin, gc2);
        gc2.gridx = 9;
        gc2.gridy = 0;
        gc2.anchor = GridBagConstraints.LINE_START;
        basePanelCenter.add(jcAdmin, gc2);

        /////////////////SECOND LINE/////////////////

        JLabel jlEmail = new JLabel("E-mail:");
        jeEmail = new JEditorPane("text", "Type Here:");

        JLabel jlPassword = new JLabel("Password:");
        jePassword = new JEditorPane("text", "Type Here:");

        gc2.gridx = 0;
        gc2.gridy = 2;
        gc2.anchor = GridBagConstraints.LINE_END;
        basePanelCenter.add(jlEmail, gc2);
        gc2.gridx = 1;
        gc2.gridy = 2;
        gc2.anchor = GridBagConstraints.LINE_START;
        basePanelCenter.add(jeEmail, gc2);

        gc2.gridx = 8;
        gc2.gridy = 2;
        gc2.anchor = GridBagConstraints.LINE_END;
        basePanelCenter.add(jlPassword, gc2);
        gc2.gridx = 9;
        gc2.gridy = 2;
        gc2.anchor = GridBagConstraints.LINE_START;
        basePanelCenter.add(jePassword, gc2);

        ///////////////////THIRD LINE////////////////////

        JLabel jlPhone = new JLabel("Phone:");
        jePhone = new JEditorPane("text", "Type Here:");

        JLabel jlUsername = new JLabel("Username:");
        jeUsername = new JEditorPane("text", "Type Here:");


        gc2.gridx = 0;
        gc2.gridy = 4;

        gc2.fill = GridBagConstraints.NONE;
        gc2.anchor = GridBagConstraints.LINE_END;
        basePanelCenter.add(jlPhone, gc2);
        gc2.gridx = 1;
        gc2.gridy = 4;
        gc2.anchor = GridBagConstraints.LINE_START;
        basePanelCenter.add(jePhone, gc2);

        gc2.gridx = 4;
        gc2.gridy = 4;
        gc2.anchor = GridBagConstraints.LINE_END;
        basePanelCenter.add(jlUsername, gc2);
        gc2.gridx = 5;
        gc2.gridy = 4;
        gc2.anchor = GridBagConstraints.LINE_START;
        basePanelCenter.add(jeUsername, gc2);

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

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equalsIgnoreCase("update")){

            insertNewBank();
        }
    }

    private void insertNewBank() {
//      String columnNames[] = {"Cust ID", "First name", "Last Name", "Email", "Password", "Phone", "Administrator", "Username", "Quantity Rented", "Points"};


        String[] allGuys = new String[7];
        allGuys[0] = jeFName.getText();
        allGuys[1] = jeLName.getText();
        allGuys[2] = jeEmail.getText();
        allGuys[3] = jePassword.getText();
        allGuys[4] = jePhone.getText();
        allGuys[5] = jcAdmin.getSelectedItem().toString();
        allGuys[6] = jeUsername.getText();



//        System.out.println("Title ID: " + jeTitleId.getText());
        System.out.println("First name: " + allGuys[0]);
        System.out.println("Last Name: " + allGuys[1]);
//        System.out.println("Type: " + jcType.getSelectedItem().toString());
//        System.out.println("Abbreviation: " + jeTypeAbbr.getText());
        System.out.println("Email: " + allGuys[2]);
        System.out.println("Password: " + allGuys[3]);
        System.out.println("Administrator: " + allGuys[4]);
        System.out.println("Username: " + allGuys[5]);
        System.out.println("Username: " + allGuys[6]);
//        System.out.println("Rent ID: " + jeRentID.getText());

        if (jeFName.getText().trim().isEmpty() || jeFName.getText().trim().equalsIgnoreCase("Type Here the Title:")){
            JOptionPane.showMessageDialog(null, "You haven't typed the Title :/");
            return;
        }else if (jcAdmin.getSelectedItem().toString().isEmpty()){
            JOptionPane.showMessageDialog(null, "You haven't selected the Media Type :/");
            return;
        }else if (jeLName.getText().trim().isEmpty() || jeLName.getText().trim().equalsIgnoreCase("Type Here:")){
            JOptionPane.showMessageDialog(null, "You haven't selected the Type :/");
            return;
        }else if (jeEmail.getText().trim().isEmpty() || jeEmail.getText().trim().equalsIgnoreCase("Type Here:")){
            JOptionPane.showMessageDialog(null, "You haven't typed the Band or Director name :/");
            return;
        }else if (jePassword.getText().trim().isEmpty() || jePassword.getText().trim().equalsIgnoreCase("Type Here:")){
            JOptionPane.showMessageDialog(null, "You haven't typed the Description :/");
            return;
        }else if (jePhone.getText().trim().isEmpty() || jePhone.getText().trim().equalsIgnoreCase("Type Here:")){
            JOptionPane.showMessageDialog(null, "You haven't typed the genre :/");
            return;
        }else if (jeUsername.getText().trim().isEmpty() || jeUsername.getText().trim().equalsIgnoreCase("Type Here:")){
            JOptionPane.showMessageDialog(null, "You haven't typed the genre :/");
            return;
        }


        try{

            model.insertNewBird(allGuys);

        }catch (Exception e){
            System.out.println("the bank had a problem");
            System.out.println(e.getMessage());
            System.out.println(e.getClass());
            System.out.println(e.getCause());

            //new UVControl();
        }
    }
}
