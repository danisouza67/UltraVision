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
    private JPanel containerLeftDash;r
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

        this.username = session.getLoginUsername();
        this.userPass = session.getPass();
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
            this.add(containerLeft, BorderLayout.WEST);
            containerLeftDash.setVisible(false);
            containerLeft.setVisible(true);
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
            containerLeft.setVisible(false);
            containerLeftDash.setVisible(true);
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

        JOptionPane.showInputDialog(null, "Please choose an option", "Title Category",
                JOptionPane.QUESTION_MESSAGE, null, new Object[] {"Live Concert", "Movies", "TV Box", "Music"}, "TypesOfTitles" );

        content.AskUpdateTitles();
    }
}
