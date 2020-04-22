import resources.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class UVControl implements ActionListener {

        UVModel model;
        UVLogin view;
        private VLCatalog CusDash;
     // DashboardAdmin AdminDash;
        private User userLogged;

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
            boolean login = model.connection(userLogged);

            //int administrator = Integer.parseInt(login);
            System.out.println(model.session.getLoginUsername());

            System.out.println(model.loginID);

            if(login){
                CusDash = new VLCatalog(model.session, this);
                view.dispose();
            }
        }
    }
}
