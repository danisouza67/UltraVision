import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class LeftMenu extends JPanel implements ActionListener {

    JButton btmID;
    String typeAbbr;
    String data[][];

    JTable listTitlesTable;

    Titles titlesClass[];
    String title;
    String title_id;
    UVModel model = new UVModel();
    VLCatalog listener;



    public LeftMenu(String typeAbbr, JPanel containerLeft, VLCatalog listener){

//        this.removeAll();
//        this.removeAncestorListener();

        this.listener = listener;
        Dimension dim = getPreferredSize();
        dim.width = 170;
        setPreferredSize(dim);


        //getting the titles from the bank that we need
        getThoseThingsFromTheBank(typeAbbr);

        //ok, time to populating some titles ans creating an arraylist
        //populatingTitlesClass(data);

        printingAllTheTitles(data, containerLeft);

    }


    private void getThoseThingsFromTheBank(String typeAbbr) {

        this.typeAbbr = typeAbbr;

        System.out.println("typeAbbr: " + typeAbbr);



        try{

            data = model.selectInTheBeach2D(typeAbbr);

        }catch (Exception e){
            System.out.println("the bank had a problem");
            System.out.println(e.getMessage());
            System.out.println(e.getClass());
            System.out.println(e.getCause());

            //new UVControl();
        }

        if(data == null){

            System.out.println("The system found an error;");
            new UVControl();

        }

//        System.out.println(data[0][0] + data[0][1] + data[0][2]);
//        System.out.println(data[1][0] + data[1][1] + data[1][2]);
//        System.out.println(data[2][0] + data[2][1] + data[2][2]);
    }


    private void populatingTitlesClass(String[][] data) {

        ArrayList<String[]> myAlOfTitles = new ArrayList();
        //int count = 0;

        System.out.println(data[0].length);
        for (int i = 0; i < data.length; i++){
            //String abs = data[i][0];
            System.out.println(Arrays.toString(data[i]));
            System.out.println(myAlOfTitles.add(data[i]));
            //System.out.println(Arrays.toString(myAlOfTitles));
            //System.out.println("I:" + count);

            //for (int j = 1; j <= data[1].length; j++) {
            //System.out.println(myAlOfTitles.add(titlesClass[count] = new Titles(data[0][count], data[1][count])));
            //System.out.println(abs + data[i][j]);
            //titlesClass[count] = new Titles(data[i][i], data[i][j]);
            //System.out.println(titlesClass[count]);
            // count++;
            //System.out.println("J:" + count);
            //}
        }
    }


    private void printingAllTheTitles(String[][] data, JPanel containerLeft) {

        String[][] dataForLeftTable = new String[data.length][3];
        for (int i = 0; i < data.length; i++){
            dataForLeftTable[i][0] = data[i][1];
            dataForLeftTable[i][1] = data[i][0];
            dataForLeftTable[i][2] = data[i][3];
        }


        String columnNames[] = {"Titles", "ID", "Type"};
        listTitlesTable = new JTable(dataForLeftTable, columnNames);
        listTitlesTable.setRowHeight(20);

        JScrollPane titlesScrollPane = new JScrollPane(listTitlesTable);
        TableColumnModel columnModel = listTitlesTable.getColumnModel();
        //columnModel.getColumn(0).setWidth(100);
        columnModel.getColumn(1).setPreferredWidth(20);
        titlesScrollPane.setPreferredSize(new Dimension(190, 450));
        JPanel content = new JPanel();
        JButton selectLeft = new JButton("Select");
        selectLeft.addActionListener(listener);
        selectLeft.setActionCommand("selectLeft");
        //this.add(content);
        containerLeft.add(titlesScrollPane);
        containerLeft.add(selectLeft);

        this.revalidate();
        this.repaint();


    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equalsIgnoreCase("selectLeft")){

            try {
                System.out.println("Selected ID: " + getListTitlesTable().getValueAt(getListTitlesTable().getSelectedRow(), 1));
                System.out.println("Selected Type: " + getListTitlesTable().getValueAt(getListTitlesTable().getSelectedRow(), 2));
            } catch (Exception e) {
                System.out.println("no row selected");
            }
            String id = getListTitlesTable().getValueAt(getListTitlesTable().getSelectedRow(), 1).toString();
            String type = getListTitlesTable().getValueAt(getListTitlesTable().getSelectedRow(), 2).toString();
            //titlesTPane.setVisible(false);
            //data = model.selectInTheBeachAlone(id, typeAbbr);
//            System.out.println("getData.length: " + content.getData(0).length);
            String[] dataSelected = new String[getData(0).length];
            fetchSelectedData(data, id, type, dataSelected);
        }
    }

    private void fetchSelectedData(String[][] data, String id, String type, String[] dataSelected) {

        System.out.println("row_id: " + id);

        for(int i=0; i < data.length; i++){
            //System.out.println();
            System.out.println("data " + data[i][1] + " equals id? " + data[i][0].equals(id));
            System.out.println("data " + data[i][1] + " equals Type? " + data[i][3].equals(type));
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

    public JTable getListTitlesTable() {
        return listTitlesTable;
    }

    public void setListTitlesTable(JTable listTitlesTable) {
        this.listTitlesTable = listTitlesTable;
    }

    public String[] getData(int i) {
        return data[i];
    }

    public void setData(String[][] data) {
        this.data = data;
    }
}
