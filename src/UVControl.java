import resources.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;


public class UVControl implements ActionListener {

        UVModel model;
        UVLogin view;
        private VLCatalog CusDash;
     // DashboardAdmin AdminDash;
        private User userLogged;
        private User session;

    public UVControl(){

        model = new UVModel();
        view = new UVLogin(this);

    }



    @Override
    public void actionPerformed(ActionEvent ae) {
        String usr = view.getUsrName();
        String pss = view.getUsrPass();
        User userLogged = new User(usr, pss);

        if(ae.getActionCommand().equals("login")){
            //recognizing action command for login button
            boolean login = false;
            try {
                login = model.connection(userLogged);
            } catch (SQLException e) {
                System.out.println("the bank had a problem");
                System.out.println(e.getMessage());
                System.out.println(e.getClass());
                System.out.println(e.getCause());            }

            this.session = model.session;

            //int administrator = Integer.parseInt(login);
            System.out.println("session.getLoginUsername(): " + session.getLoginUsername());

            System.out.println("session.loginID: " + session.getLoginID());

            //this.session = model.session;
            if(login){
                CusDash = new VLCatalog(session, this);
                view.dispose();
            }
        }
    }
}
