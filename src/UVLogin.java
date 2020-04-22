import javax.swing.*;

public class UVLogin extends JFrame {

    private JTextField tf1;
    private JTextField tf2;
    private UVControl controller;


    public UVLogin (UVControl controller){

        this.controller = controller;

        attributesSetter();

        components();

        validation();

    }

    public String getUsrName(){
        return (tf1.getText());
    }

    public String getUsrPass(){
        return (tf2.getText());
    }

    private void validation() {
        this.validate();
        this.repaint();
    }

    private void components() {
        JPanel panel1 = new JPanel();
        this.add(panel1);

        JPanel panel2 = new JPanel();
        //this.add(panel2);

        tf1 = new JTextField(20);
        tf2 = new JTextField(20);

        JLabel labelLogin = new JLabel("Your Username");
        JLabel labelPassword = new JLabel("Your Password");

        JButton bLogin = new JButton("Login");
        bLogin.addActionListener(controller);
        bLogin.setActionCommand("login");

        JButton bRegister = new JButton("Register");
        bRegister.addActionListener(controller);
        bRegister.setActionCommand("register");

        panel1.add(labelLogin);
        panel1.add(tf1);
        panel1.add(labelPassword);
        panel1.add(tf2);
        panel1.add(bLogin);
        panel1.add(bRegister);
    }

    private void attributesSetter() {
        this.setSize(700, 600);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Ultra Vision - Login");
    }

}
