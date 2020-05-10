import javax.swing.*;
import java.awt.*;


public class NavTop extends JPanel{

    private GridBagLayout baseLayout = new GridBagLayout();
    private JPanel base = new JPanel();
    private JButton VL = new JButton("VL");
    private JButton ML = new JButton("ML");
    private JButton TV = new JButton("TV");
    private JButton updateMembership = new JButton("Update Membership");
    private JButton dashBoard = new JButton("DashBoard");

    VLCatalog listener;

    public NavTop(VLCatalog listener){

        this.listener = listener;

        setBorder(BorderFactory.createEtchedBorder(1,Color.BLACK,Color.CYAN));


        this.add(base);
        base.setLayout(baseLayout);

        ML.addActionListener(listener);
        ML.setActionCommand("ML");
        base.add(ML);

        base.add(VL);
        VL.addActionListener(listener);
        VL.setActionCommand("VL");

        base.add(TV);
        TV.addActionListener(listener);
        TV.setActionCommand("TV");

        base.add(updateMembership);
        updateMembership.addActionListener(listener);
        updateMembership.setActionCommand("updateMembership");

        base.add(dashBoard);
        dashBoard.addActionListener(listener);
        dashBoard.setActionCommand("dashboard");



    }


}
