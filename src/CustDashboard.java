import resources.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustDashboard extends JFrame implements ActionListener {

    //I need this variables here to access them in the actionlistener
    JTextField tfFname;
    JTextField tfLname;
    JTextField tfEmail;
    JTextField tfPassword;
    JTextField tfPhone;
    JComboBox adminList;
    JTextField tfUsername;
    JTextField tfStreet;
    JTextField tfCity;
    JTextField tfCountry;
    private String username;
    private String userPass;
    private JPanel content = new JPanel();


    UVControl controller;
    User session;
    //CVModel model;


    public CustDashboard(User userLogged) {

        this.username = userLogged.getUsr();
        this.userPass = userLogged.getPass();
        this.controller = controller;


        attributesSetter();

        components(userLogged);

        hiddenRegistry();

        validation();

      /*  class hiddenRegistry{

            hiddenRegistry(){


            }
        }
        */
    }

    private void hiddenRegistry() {

        class hiddenRegistry{

            hiddenRegistry(){

                //content.setVisible(false);

                //registry content
                JPanel updatePane = new JPanel();
                content.add(updatePane, BorderLayout.CENTER);
                GridLayout updatePaneGrid = new GridLayout(9, 2);
                updatePane.setLayout(updatePaneGrid);

                JLabel tfLFname = new JLabel("First Name:");
                tfFname = new JTextField(50);
                JLabel tfLLname = new JLabel("Last Name:");
                tfLname = new JTextField(50);
                JLabel tfLEmail = new JLabel("E-mail:");
                tfEmail = new JTextField(50);
                JLabel tfLPassword = new JLabel("Password:");
                tfPassword = new JTextField(50);
                JLabel tfLPhone = new JLabel("Phone:");
                tfPhone = new JTextField(50);
                JLabel tfLadminList = new JLabel("Administrator:");
//        JTextField tfAdministrator = new JTextField(50);
                String[] adminStrings = {"Yes", "No"};
                adminList = new JComboBox(adminStrings);
                adminList.setSelectedIndex(0);

                JLabel tfLUsername = new JLabel("Username");
                tfUsername = new JTextField(50);
                JLabel tfLStreet = new JLabel("Street");
                tfStreet = new JTextField(60);
                JLabel tfLCity = new JLabel("City");
                tfCity = new JTextField(50);
                JLabel tfLCountry = new JLabel("Country");
                tfCountry = new JTextField(50);

                updatePane.add(tfLFname);
                updatePane.add(tfFname);
                updatePane.add(tfLLname);
                updatePane.add(tfLname);
                updatePane.add(tfLEmail);
                updatePane.add(tfEmail);
                updatePane.add(tfLPassword);
                updatePane.add(tfPassword);
                updatePane.add(tfLPhone);
                updatePane.add(tfPhone);
                updatePane.add(tfLUsername);
                updatePane.add(tfUsername);
                updatePane.add(tfLStreet);
                updatePane.add(tfStreet);
                updatePane.add(tfLCity);
                updatePane.add(tfCity);
                updatePane.add(tfLCountry);
                updatePane.add(tfCountry);


            }
        }

    }

    private void attributesSetter() {
        this.setSize(700, 600);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Ultra Vision - Catalog");

        JPanel content = new JPanel();
        this.add(content, BorderLayout.CENTER);


    }

    private void components(User userLogged) {

        //background
        BorderLayout backgr = new BorderLayout();
        this.setLayout(backgr);

        // panel north of background
        JPanel dashMenu = new JPanel();
        this.add(dashMenu, BorderLayout.NORTH);
        //layout of above panel
        BorderLayout inner = new BorderLayout();
        dashMenu.setLayout(inner);


        this.add(content, BorderLayout.CENTER);

        BorderLayout innerContent = new BorderLayout();
        content.setLayout(innerContent);

        JPanel centerNorthPane = new JPanel();
        content.add(centerNorthPane, BorderLayout.NORTH);
        JLabel label = new JLabel("Welcome " + userLogged.getUsr());

        centerNorthPane.add(label);
        JButton bInsert = new JButton("Update your Profile");
        centerNorthPane.add(bInsert);
        bInsert.addActionListener(this);
        bInsert.setActionCommand("updateProfile");


        class mainContent{

            mainContent(){
                JPanel panelMainContent = new JPanel();

            }
        }
        /*content.setVisible(false);

        //registry content
        JPanel updatePane = new JPanel();
        content.add(updatePane, BorderLayout.CENTER);
        GridLayout updatePaneGrid = new GridLayout(9, 2);
        updatePane.setLayout(updatePaneGrid);

        JLabel tfLFname = new JLabel("First Name:");
        tfFname = new JTextField(50);
        JLabel tfLLname = new JLabel("Last Name:");
        tfLname = new JTextField(50);
        JLabel tfLEmail = new JLabel("E-mail:");
        tfEmail = new JTextField(50);
        JLabel tfLPassword = new JLabel("Password:");
        tfPassword = new JTextField(50);
        JLabel tfLPhone = new JLabel("Phone:");
        tfPhone = new JTextField(50);
        JLabel tfLadminList = new JLabel("Administrator:");
//        JTextField tfAdministrator = new JTextField(50);
        String[] adminStrings = {"Yes", "No"};
        adminList = new JComboBox(adminStrings);
        adminList.setSelectedIndex(0);

        JLabel tfLUsername = new JLabel("Username");
        tfUsername = new JTextField(50);
        JLabel tfLStreet = new JLabel("Street");
        tfStreet = new JTextField(60);
        JLabel tfLCity = new JLabel("City");
        tfCity = new JTextField(50);
        JLabel tfLCountry = new JLabel("Country");
        tfCountry = new JTextField(50);

        updatePane.add(tfLFname);
        updatePane.add(tfFname);
        updatePane.add(tfLLname);
        updatePane.add(tfLname);
        updatePane.add(tfLEmail);
        updatePane.add(tfEmail);
        updatePane.add(tfLPassword);
        updatePane.add(tfPassword);
        updatePane.add(tfLPhone);
        updatePane.add(tfPhone);
        updatePane.add(tfLUsername);
        updatePane.add(tfUsername);
        updatePane.add(tfLStreet);
        updatePane.add(tfStreet);
        updatePane.add(tfLCity);
        updatePane.add(tfCity);
        updatePane.add(tfLCountry);
        updatePane.add(tfCountry);
*/
    }

    private void validation() {
        this.validate();
        this.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("updateProfile")) {
            content.setVisible(false);
        }
        /*if (ae.getActionCommand().equals("updateProfile")) {
            System.out.println("tfFname: " + tfFname.getText());
            System.out.println("Session usr: " + username + " and pass: " +userPass);
//            System.out.println("Session usr: " + session.getUsr());
            System.out.println("tfFname: " + tfFname.getText());
            System.out.println("tfLname: " + tfLname.getText());
            System.out.println("tfemail: " + tfEmail.getText());
            System.out.println("tfphone + username: " + tfPhone.getText() + tfUsername.getText());
            System.out.println(adminList.getSelectedItem());

            if (tfFname.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "you haven't typed First Name");
                return;

            }
            if (tfLname.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "you haven't typed Last Name");
                return;

            }
            if (tfEmail.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "you haven't typed E-mail");
                return;
            }
            if (tfPassword.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "you haven't typed Password");
                return;
            }
            if (tfPhone.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "you haven't typed Phone");
                return;
            }
            if (tfUsername.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "you haven't typed Username");
                return;
            }
            if (tfStreet.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "you haven't typed Street");
//                return;
            }
            if (tfCity.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "you haven't typed City");
//                return;
            }
            if (tfCountry.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "you haven't typed Country");
//                return;
            }
            try {
                //        {"User ID", "Name", "E-mail", "Phone", "Administrator", "Username", "Street", "City", "Country"}
                String sql = "UPDATE customers SET fname=?,lname=?,email=?, password=?, phone=?,username=? WHERE username='"
                        + username + "' and password = '" + userPass + "' ;";
                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                String dbServer = "jdbc:mysql://database-1.cptrcvahtkfl.eu-west-1.rds.amazonaws.com:3306/DanielSouza_2018364?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
                String user = "danielSouza_2018364";
                String password = "Pass1234!";
                Connection conn = DriverManager.getConnection(dbServer, user, password);

                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, tfFname.getText());
                pst.setString(2, tfLname.getText());
                pst.setString(3, tfEmail.getText());
                pst.setString(4, tfPassword.getText());
                pst.setString(5, tfPhone.getText());
//                String adminType = adminList.getSelectedItem().toString();
//                if (adminType.equalsIgnoreCase("Yes")) {
//                    pst.setString(6, "1");
//                } else if (adminType.equalsIgnoreCase("No")) {
//                    pst.setString(6, "0");
//                }
                pst.setString(6, tfUsername.getText());
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "updated successfully");
            } catch (HeadlessException | ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
   */
    }


}
