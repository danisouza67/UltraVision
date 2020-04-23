import resources.User;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VLCatalog extends JFrame implements ActionListener {
    private JPanel panel1 = new JPanel();
    private JPanel containerTop = new JPanel();
    private JPanel containerLeft = new JPanel();
    private JPanel containerLeftDash = new JPanel();
    private JPanel containerCenter = new JPanel();
    private JPanel containerBottom = new JPanel();
    private String username;
    private String userPass;


    UVControl controller;
    NavTop navTop = new NavTop(this);
    MainContent content = new MainContent();
    LeftMenu leftMenu;
    LeftMenuDash leftMenuDash;
    User session;


    public VLCatalog(User session, UVControl controller){

        setUsername(session.getLoginUsername());
        //this.username = session.getLoginUsername();
        setUserPass(session.getPass());
        this.controller = controller;
        this.session = session;
        System.out.println(username);

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
        containerTop.setBorder(BorderFactory.createEtchedBorder(1,Color.BLACK,Color.CYAN));

        this.add(containerCenter, BorderLayout.CENTER);
        containerCenter.add(content);
        containerCenter.setBorder(BorderFactory.createEtchedBorder(1,Color.BLACK,Color.CYAN));


        //containerLeft.setVisible(false);

    }

    private void attributesSetter() {
        this.setSize(700, 600);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Ultra Vision - Catalog");

    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if(ae.getActionCommand().equalsIgnoreCase("ml")){
            containerLeft = new JPanel();
            System.out.println("vc esta aqui (Session ML)");
            getContainerLeft().setVisible(true);
            getContainerLeftDash().setVisible(false);
            this.add(containerLeft, BorderLayout.WEST);
            leftMenu = new LeftMenu("ML", containerLeft);
            containerLeft.setPreferredSize(new Dimension(210, 500));
            containerLeft.add(leftMenu);
            //containerLeft.setBorder(BorderFactory.createEtchedBorder(1,Color.BLACK,Color.CYAN));
            Border innerBorder = BorderFactory.createTitledBorder("List of Titles");
            Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
            containerLeft.setBorder(BorderFactory.createCompoundBorder(innerBorder, outerBorder));
        }else if (ae.getActionCommand().equalsIgnoreCase("dashboard")){
            containerLeftDash = new JPanel();
            System.out.println("You have been transferred to DashBoard, enjoy!");
            getContainerLeftDash().setVisible(true);
            getContainerLeft().setVisible(false);
            this.add(containerLeftDash, BorderLayout.WEST);
            leftMenuDash = new LeftMenuDash("dashboard", containerLeftDash, session, this);
            containerLeftDash.setPreferredSize(new Dimension(210,500));
            containerLeftDash.add(leftMenuDash);
            Border innerBorder = BorderFactory.createTitledBorder("Dashboard");
            Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
            containerLeftDash.setBorder(BorderFactory.createCompoundBorder(innerBorder, outerBorder));
        }else if(ae.getActionCommand().equalsIgnoreCase("updateTitles")){
            updateTitlesMethod();
        }
    }

    private void updateTitlesMethod() {

        content.setVisible(false);

       /* String typesOfTitles[] = new String[4];
        typesOfTitles[0] = "Live Concerts";
        typesOfTitles[1] = "Music";
        typesOfTitles[2] = "Movies";
        typesOfTitles[3] = "TV Box";*/

        Object selectedTitle = JOptionPane.showInputDialog(null, "Please choose an option", "Title Category",
                JOptionPane.QUESTION_MESSAGE, null, new Object[] {"Live Concert", "Movies", "TV Box", "Music"}, "TypesOfTitles" );

        System.out.println("Title category: " + selectedTitle.toString());

        String selectedTitleString = selectedTitle.toString();
        //System.out.println(selectedTitle.toString());


        content = new MainContent();
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

    public JPanel getContainerLeft() {
        return containerLeft;
    }

    public void setContainerLeft(JPanel containerLeft) {
        this.containerLeft = containerLeft;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }
}
