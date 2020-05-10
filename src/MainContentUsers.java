import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class MainContentUsers extends JPanel {

    private BorderLayout baseBorderLayout = new BorderLayout();

    private JTable titlesTable;
    private JScrollPane titlesTPane = new JScrollPane();
    private JButton buttonOne = new JButton();
    private JButton buttonTwo = new JButton();
    private JPanel basePanel1 = new JPanel();
    private JPanel bottomMenu = new JPanel();

    String[][] data;

    UVModel model = new UVModel();
    VLCatalog listener;

    public MainContentUsers(VLCatalog listener){
        this.listener = listener;

        System.out.println("This is the users table:");

        try {
            data = model.selectTheBirds2D();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(data.length);
        System.out.println(data[0].length);

        //`cust_id`, `fname`, `lname`, `email`, `password`, `phone`, `administrator`, `username`, `quantRented`, `points`
        String[] columnNames = {"Cust ID", "First name", "Last Name", "Email", "Password", "Phone", "Administrator", "Username", "Quantity Rented", "Points"};

        //data table
        titlesTable = new JTable(data, columnNames);
        titlesTPane = new JScrollPane(titlesTable);
        buttonOne = new JButton("Select");
        buttonOne.addActionListener(listener);
        buttonOne.setActionCommand("selectUser");
        buttonTwo = new JButton("Create New");
        buttonTwo.setActionCommand("createNewUser");
        buttonTwo.addActionListener(listener);

//        basePanel.setVisible(false);
//        basePanel.isEnabled();
        basePanel1 = new JPanel();
        basePanel1.setVisible(true);
        this.setLayout(baseBorderLayout);
        this.add(basePanel1, BorderLayout.CENTER);
        basePanel1.setPreferredSize(new Dimension(663,443));
        basePanel1.add(titlesTPane);

//        bottomMenu2.setVisible(false);
        bottomMenu = new JPanel();
        bottomMenu.setVisible(true);
        this.add(bottomMenu, BorderLayout.SOUTH);
        bottomMenu.add(buttonOne);
        bottomMenu.add(buttonTwo);

        this.validate();
        this.repaint();
    }

    public String[] getData(int i) {
        return data[i];
    }

    public void setData(String[][] data) {
        this.data = data;
    }

    public JTable getTitlesTable() {
        return titlesTable;
    }

    public void setTitlesTable(JTable titlesTable) {
        this.titlesTable = titlesTable;
    }
}
