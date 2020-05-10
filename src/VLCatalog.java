import resources.User;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VLCatalog extends JFrame implements ActionListener {
    private JPanel panel1 = new JPanel();
    private JPanel containerTop = new JPanel();
    private JPanel containerLeft;
    private JPanel containerLeftDash = new JPanel();
    private JPanel containerCenter = new JPanel();
    private JPanel containerCenterID = new JPanel();
    private JPanel containerCenterID2 = new JPanel();
    private JPanel containerCenterNew = new JPanel();
    private JPanel containerBottom = new JPanel();
    private String userFullName;
    private String userAdmin;
    private String selectedTitleString;

    User session;
    UVControl listener;
    NavTop navTop = new NavTop(this);
    MainContent content;
    MainContentID contentID;
    MainContentID contentID2;
    MainContentNew contentNew;
    MainContentUsers contentUsers;
    MainContentUsersID contentUsersID;
    Footer footer = new Footer();
    LeftMenu leftMenu;
    LeftMenuDash leftMenuDash;
    UVModel model;


    public VLCatalog(User session, UVControl listener) {

        System.out.print("Welcome:");
        System.out.println(" " + session.getLoginFname() + " " + session.getLoginLname());
        setUserFullName(session.getLoginFname() + " " + session.getLoginLname());
        //this.username = session.getLoginUsername();
        setUserAdmin(session.getLoginAdmin());
        this.listener = listener;
        //this.session = session;
        //System.out.println(username);

        attributesSetter();

        components(session);

        validation();
    }

    private void validation() {
        this.validate();
        this.repaint();
    }

    private void components(User session) {

        //backGrnd
        BorderLayout backgr = new BorderLayout();
        this.setLayout(backgr);

        this.add(containerTop, BorderLayout.NORTH);
        containerTop.add(navTop);
        containerTop.setBorder(BorderFactory.createEtchedBorder(1, Color.BLACK, Color.CYAN));


        content = new MainContent(userFullName, this);
        this.add(containerCenter, BorderLayout.CENTER);
        containerCenter.add(content);
        containerCenter.setBorder(BorderFactory.createEtchedBorder(1, Color.BLACK, Color.CYAN));
        //containerLeft.setVisible(false);


        this.add(containerBottom, BorderLayout.SOUTH);
        containerBottom.add(footer);
        containerBottom.setBorder(BorderFactory.createEtchedBorder(1, Color.BLACK, Color.CYAN));

    }

    private void attributesSetter() {
        this.setSize(1000, 700);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Ultra Vision - Catalog");

    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getActionCommand().equalsIgnoreCase("ml")) {
            containerLeft = new JPanel();
//            containerLeft.removeAll();
            System.out.println("vc esta em Session ML");
            containerLeft.setVisible(true);
            containerLeft.setOpaque(true);
            containerLeftDash.setVisible(false);
            this.add(containerLeft, BorderLayout.WEST);
            leftMenu = new LeftMenu("ML", containerLeft, this);
            containerLeft.setPreferredSize(new Dimension(210, 500));
            containerLeft.add(leftMenu);
            //containerLeft.setBorder(BorderFactory.createEtchedBorder(1,Color.BLACK,Color.CYAN));
            Border innerBorder = BorderFactory.createTitledBorder("List of Titles");
            Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
            containerLeft.setBorder(BorderFactory.createCompoundBorder(innerBorder, outerBorder));
        }
        else if (ae.getActionCommand().equalsIgnoreCase("vl")) {
//            containerLeft = new JPanel();
            System.out.println("vc esta em Session VL");
            containerLeft = new JPanel();
            containerLeft.setVisible(true);
            containerLeft.setOpaque(true);
            containerLeftDash.setVisible(false);
            this.add(containerLeft, BorderLayout.WEST);
            leftMenu = new LeftMenu("VL", containerLeft, this);
            containerLeft.setPreferredSize(new Dimension(210, 500));
            containerLeft.add(leftMenu);
            //containerLeft.setBorder(BorderFactory.createEtchedBorder(1,Color.BLACK,Color.CYAN));
            Border innerBorder = BorderFactory.createTitledBorder("List of Titles");
            Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
            containerLeft.setBorder(BorderFactory.createCompoundBorder(innerBorder, outerBorder));
        }
        else if (ae.getActionCommand().equalsIgnoreCase("tv")) {
//            containerLeft = new JPanel();
            System.out.println("vc esta em Session TV");
            containerLeft = new JPanel();
            containerLeft.setVisible(true);
            containerLeft.setOpaque(true);
            containerLeftDash.setVisible(false);
            this.add(containerLeft, BorderLayout.WEST);
            leftMenu = new LeftMenu("TV", containerLeft, this);
            containerLeft.setPreferredSize(new Dimension(210, 500));
            containerLeft.add(leftMenu);
            //containerLeft.setBorder(BorderFactory.createEtchedBorder(1,Color.BLACK,Color.CYAN));
            Border innerBorder = BorderFactory.createTitledBorder("List of Titles");
            Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
            containerLeft.setBorder(BorderFactory.createCompoundBorder(innerBorder, outerBorder));
        }
        else if (ae.getActionCommand().equalsIgnoreCase("dashboard")) {
            containerLeftDash = new JPanel();
            System.out.println("You have been transferred to DashBoard, enjoy!");
            containerLeftDash.setVisible(true);
            containerLeftDash.setOpaque(true);
//            containerLeft.setVisible(false);
            this.add(containerLeftDash, BorderLayout.WEST);
            System.out.println(userAdmin);
            leftMenuDash = new LeftMenuDash("dashboard", containerLeftDash, userAdmin, this);
            containerLeftDash.setPreferredSize(new Dimension(210, 500));
            containerLeftDash.add(leftMenuDash);
            Border innerBorder = BorderFactory.createTitledBorder("Dashboard");
            Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
            containerLeftDash.setBorder(BorderFactory.createCompoundBorder(innerBorder, outerBorder));
        }
        else if (ae.getActionCommand().equalsIgnoreCase("updateUsers")){
            updateUsersMethod();
        }
        else if (ae.getActionCommand().equalsIgnoreCase("selectUser")){
            selectUserMethod();
        }
        else if (ae.getActionCommand().equalsIgnoreCase("createNewUser")){
            selectUserMethod();
        }
        else if (ae.getActionCommand().equalsIgnoreCase("updateTitles")) {
            updateTitlesMethod();
        }
        else if(ae.getActionCommand().equalsIgnoreCase("createNewButton")){
           createNewButton(selectedTitleString);
        }
        if (ae.getActionCommand().equalsIgnoreCase("selectLeft")){

            // getting row from the table
            try {
                System.out.println("Selected ID: " + leftMenu.getListTitlesTable().getValueAt(leftMenu.getListTitlesTable().getSelectedRow(), 1));
                System.out.println("Selected Type: " + leftMenu.getListTitlesTable().getValueAt(leftMenu.getListTitlesTable().getSelectedRow(), 2));
            } catch (Exception e) {
                System.out.println("no row selected");
            }
            String id = leftMenu.getListTitlesTable().getValueAt(leftMenu.getListTitlesTable().getSelectedRow(), 1).toString();
            String type = leftMenu.getListTitlesTable().getValueAt(leftMenu.getListTitlesTable().getSelectedRow(), 2).toString();
            //titlesTPane.setVisible(false);
            //data = model.selectInTheBeachAlone(id, typeAbbr);
//            System.out.println("getData.length: " + content.getData(0).length);
            String[] dataSelected = new String[leftMenu.getData(0).length];
            //little code to create a array with all content needed
            fetchSelectedData(leftMenu.data, id, type, dataSelected);

            //now creating the swing components..
            containerCenter.setVisible(false);
            containerCenterNew.setVisible(false);
            containerCenterID.setOpaque(true);
            containerCenterID = new JPanel();
            containerCenterID.setVisible(true);
            this.add(containerCenterID, BorderLayout.CENTER);
            containerCenterID.setBorder(BorderFactory.createEtchedBorder(1, Color.BLACK, Color.CYAN));
            contentID = new MainContentID(selectedTitleString, dataSelected, userFullName);
            containerCenterID.add(contentID);


        }
        else if (ae.getActionCommand().equalsIgnoreCase("select")) {

/*            //content.buttonOne.setEnabled(false);
            content.buttonOne.setVisible(false);
            //content.buttonTwo.setEnabled(false);
            content.buttonTwo.setVisible(false);*/
//            content.bottomMenu.setVisible(false);

            try {
                System.out.println("Selected ID: " + content.getTitlesTable().getValueAt(content.getTitlesTable().getSelectedRow(), 0));
                System.out.println("Selected Type: " + content.getTitlesTable().getValueAt(content.getTitlesTable().getSelectedRow(), 3));
            } catch (Exception e) {
                System.out.println("no row selected");
            }
            String id = content.getTitlesTable().getValueAt(content.getTitlesTable().getSelectedRow(), 0).toString();
            String type = content.getTitlesTable().getValueAt(content.getTitlesTable().getSelectedRow(), 3).toString();
            //titlesTPane.setVisible(false);
            //data = model.selectInTheBeachAlone(id, typeAbbr);
            System.out.println("getData.length: " + content.getData(0).length);
            String[] dataSelected = new String[content.getData(0).length];
            fetchSelectedData(content.data, id, type, dataSelected);

               /* for (int i=0; i < dataSelected.length; i++){

                    System.out.print(dataSelected[i] + "\t ");
                }*/
            //containerCenter = new JPanel();
            containerCenter.setVisible(false);
            containerCenterID.setVisible(false);
            containerCenterID2.setOpaque(true);
            this.add(containerCenterID2, BorderLayout.CENTER);
            containerCenterID2.setBorder(BorderFactory.createEtchedBorder(1, Color.BLACK, Color.CYAN));
            contentID2 = new MainContentID(selectedTitleString, dataSelected, userFullName);
            containerCenterID2.add(contentID2);
            //containerCenter.add(content);
//            containerCenter.setBorder(BorderFactory.createEtchedBorder(1, Color.BLACK, Color.CYAN));
//                createInterfaceForID(dataSelected);
        }
    }

    private void selectUserMethod() {

        try {
            System.out.println("Selected ID: " + contentUsers.getTitlesTable().getValueAt(contentUsers.getTitlesTable().getSelectedRow(), 0));
        } catch (Exception e) {
            System.out.println("no row selected");
        }
        String id = contentUsers.getTitlesTable().getValueAt(contentUsers.getTitlesTable().getSelectedRow(), 0).toString();
        String[] dataSelected = new String[contentUsers.getData(0).length];

        for(int i=0; i < contentUsers.data.length; i++){
            if (contentUsers.data[i][0].equals(id)){
                System.out.print("dataSelected: ");
                for (int k=0; k < dataSelected.length; k++){
                    dataSelected[k] = contentUsers.data[i][k];
                    System.out.print(dataSelected[k] + "\t ");
                }
            }
        }


        containerCenter = new JPanel();
        this.add(containerCenter, BorderLayout.CENTER);
        containerCenter.setBorder(BorderFactory.createEtchedBorder(1, Color.BLACK, Color.CYAN));
        contentUsersID = new MainContentUsersID(dataSelected, userFullName);
        containerCenterID2.add(contentID2);

    }

    private void updateUsersMethod() {

        containerCenter = new JPanel();
        contentUsers = new MainContentUsers(this);
        this.add(containerCenter, BorderLayout.CENTER);
        containerCenter.add(contentUsers);
        containerCenter.setBorder(BorderFactory.createEtchedBorder(1, Color.BLACK, Color.CYAN));

    }

    private void createNewButton(String selectedTitleString) {

        containerCenter.setVisible(false);
        containerCenterID.setVisible(false);
        System.out.println(selectedTitleString);
//        containerCenterNew = new JPanel();
        this.add(containerCenterNew, BorderLayout.CENTER);
        containerCenterNew.setOpaque(true);
        containerCenterNew.setBorder(BorderFactory.createEtchedBorder(1, Color.BLACK, Color.CYAN));
        contentNew = new MainContentNew(selectedTitleString, userFullName);
        containerCenterNew.add(contentNew);



    }

    private void fetchSelectedData(String[][] data, String id, String type, String[] dataSelected) {

        System.out.println("row_id: " + id);
        System.out.println(data[1].length);
        System.out.println(dataSelected.length);

        for(int i=0; i < data.length; i++){
            //System.out.println();
//            System.out.println("data " + data[i][1] + " equals id? " + data[i][0].equals(id));
//            System.out.println("data " + data[i][1] + " equals Type? " + data[i][3].equals(type));
//            for (int j = 0; j < data.length; j++){
//                System.out.println("data " + data[i][1] + " equals Type? " + data[i][3].equals(type));
            if (data[i][0].equals(id) && data[i][3].equals(type)){
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

    private void updateTitlesMethod() {

        //content.setVisible(false);

       /* String typesOfTitles[] = new String[4];
        typesOfTitles[0] = "Live Concerts";
        typesOfTitles[1] = "Music";
        typesOfTitles[2] = "Movies";
        typesOfTitles[3] = "TV Box";*/

        Object selectedTitle = JOptionPane.showInputDialog(null, "Please choose an option", "Title Category",
                JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Live Concert", "Movies", "TV Box", "Music"}, "TypesOfTitles");

        System.out.println("Title category: " + selectedTitle.toString());

        selectedTitleString = selectedTitle.toString();
        //System.out.println(selectedTitle.toString());

        containerCenter = new JPanel();
        content = new MainContent(userFullName, this);
        this.add(containerCenter, BorderLayout.CENTER);
        containerCenter.add(content);
        containerCenter.setBorder(BorderFactory.createEtchedBorder(1, Color.BLACK, Color.CYAN));
        content.createContentTitlesAdmin(selectedTitleString);
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public void setPanel1(JPanel panel1) {
        this.panel1 = panel1;
    }

    public JPanel getContainerTop() {
        return containerTop;
    }

    public void setContainerTop(JPanel containerTop) {
        this.containerTop = containerTop;
    }



    public JPanel getContainerLeftDash() {
        return containerLeftDash;
    }

    public void setContainerLeftDash(JPanel containerLeftDash) {
        this.containerLeftDash = containerLeftDash;
    }

    public JPanel getContainerCenter() {
        return containerCenter;
    }

    public void setContainerCenter(JPanel containerCenter) {
        this.containerCenter = containerCenter;
    }

    public JPanel getContainerBottom() {
        return containerBottom;
    }

    public void setContainerBottom(JPanel containerBottom) {
        this.containerBottom = containerBottom;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getUserAdmin() {
        return userAdmin;
    }

    public void setUserAdmin(String userAdmin) {
        this.userAdmin = userAdmin;
    }


}