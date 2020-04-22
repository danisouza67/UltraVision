import com.mysql.cj.xdevapi.Table;
import sun.plugin.javascript.JSContext;

import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class LeftMenu extends JPanel {

    JButton btmID;
    String typeAbbr;
    String data[][];


    Titles titlesClass[];
    String title;
    String title_id;
    UVModel model = new UVModel();



    public LeftMenu(String typeAbbr, JPanel containerLeft){
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

            data = model.bringTitlesForLeftBar(typeAbbr);

        }catch (Exception e){
            System.out.println("the bank had a problem");
            System.out.println(e.getMessage());
            System.out.println(e.getClass());
            System.out.println(e.getCause());
        }

        System.out.println(data[0][0] + data[0][1]);
        System.out.println(data[1][0] + data[1][1]);
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


        String columnNames[] = {"Titles", "ID"};
        JTable listTitlesTable = new JTable(data, columnNames);
        listTitlesTable.setRowHeight(20);

        JScrollPane titlesScrollPane = new JScrollPane(listTitlesTable);
        TableColumnModel columnModel = listTitlesTable.getColumnModel();
        //columnModel.getColumn(0).setWidth(100);
        columnModel.getColumn(1).setPreferredWidth(20);
        titlesScrollPane.setPreferredSize(new Dimension(190, 450));
        JPanel content = new JPanel();
        //this.add(content);
        containerLeft.add(titlesScrollPane);



    }

}
