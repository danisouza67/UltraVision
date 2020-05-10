import resources.User;

import javax.swing.*;
import java.awt.*;

public class LeftMenuDash extends JPanel{

    private JButton updateProf = new JButton("Update Profile");
    private JButton search = new JButton(("Search"));
    private GridBagLayout baseLayout = new GridBagLayout();

    //User session;
//    UVModel model;
    VLCatalog listener;

    public LeftMenuDash(String dashboard, JPanel containerLeftDash, String userAdmin, VLCatalog listener) {
        //model = new UVModel();
        this.listener = listener;
        //this.session = session;

        System.out.println(userAdmin);
//        System.out.println(" " + session.getLoginFname() + " " + session.getLoginLname());
        getDashboardAccess(containerLeftDash, userAdmin);

    }

    private void getDashboardAccess(JPanel containerLeftDash, String userAdmin) {


        //String username = model.session.getLoginUsername();
//        System.out.println(session.getLoginAdmin());
//        System.out.println(session.getLoginUsername());
       // System.out.println("Administrator?: " + model.session.getLoginAdmin());
        //System.out.println(username);


        int administrator = Integer.parseInt(userAdmin);

        System.out.println("admin: " + administrator);

        if(administrator == 1){
            generateAdminLeftMenu(containerLeftDash);
        }else{
            generateCustLeftMenu(containerLeftDash);
        }

        }

    private void generateCustLeftMenu(JPanel containerLeftDash) {

        containerLeftDash.add(updateProf);
        containerLeftDash.add(search);

    }

    private void generateAdminLeftMenu(JPanel containerLeftDash) {

        JButton updateUser = new JButton("Update Users");
        JButton updateTitles = new JButton("Update Titles");

        containerLeftDash.add(updateProf);
        containerLeftDash.add(updateUser);

        containerLeftDash.add(updateTitles);
        updateTitles.addActionListener(listener);
        updateTitles.setActionCommand("updateTitles");

        containerLeftDash.add(search);


    }
}

