import resources.User;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class UVModel {

    String loginID = null;
    String loginFname = null;
    String loginLname = null;
    String loginUsername = null;
    String loginAdmin = null;


    public User session;
    //DashboardAdmin adminDash;

    Connection conn;
    Statement stmt;
    Statement stmt2;
    ResultSet rs;
    ResultSet rs2;


    public boolean connection(User userLogged) throws SQLException {

        boolean login = false;

        try {
            // to load usr credentials

            //data base drive
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String dbServer = "jdbc:mysql://database-1.czpswiukhzqy.eu-west-1.rds.amazonaws.com:3306/UltraVision?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String user = "connectionUltra";
            String password = "!Pass123!";
            String query = "SELECT * FROM customers where username = '" + userLogged.getUsr() + "' and password = '" + userLogged.getPass() + "';";

            //get a connection to the database
            conn = DriverManager.getConnection(dbServer, user, password);

            //get a statement from the connection
            stmt = conn.createStatement();

            //execute the query
            rs = stmt.executeQuery(query);


            if (rs.next()) {
                loginAdmin = rs.getString("administrator");
                loginID = rs.getString("cust_id");
                loginFname = rs.getString("fname");
                loginLname = rs.getString("lname");
                loginUsername = rs.getString("username");
            }

            //loginAdmin = login;
            //login = rs.next();
            if (loginUsername != null) {
                login = true;
            }


            System.out.println("Admin: " + loginAdmin);
            System.out.println("ID: " + loginID);
            System.out.println("fname: " + loginFname);
            System.out.println("lname " + loginLname);
            System.out.println("userame: " + loginUsername);
            System.out.println("Login: " + login);

            //in this class, the set method is working fine..
            session = new User(loginID, loginFname, loginLname, loginUsername, loginAdmin);
            session.setLoginID(loginID);
            session.setLoginFname(loginFname);
            session.setLoginLname(loginLname);
            session.setLoginUsername(loginUsername);
            session.setLoginAdmin(loginAdmin);

            //I can also get the information in here as well..   But when I want to request this information on my DashboardAdmin class, it returns null. Any suggestion or clarifications are well come.:)
            System.out.println("Session LoginID: " + session.getLoginID());


        } catch (SQLException se) {
            System.out.println("SQL Exception:");

            //loop through the SQL Exceptions
            while (se != null) {
                System.out.println("State : " + se.getSQLState());
                System.out.println("Message: " + se.getMessage());
                System.out.println("Error: " + se.getErrorCode());

                se = se.getNextException();
            }
        } catch (Exception e) {
            System.out.println(e);

        } finally {
            //Close the result set, statement and the connection
            rs.close();
            stmt.close();
            conn.close();
        }

        return login;
    }

    public String[][] selectInTheBeach2D(String typeAbbr) throws SQLException {
        //method designed to select typeAbbr from bnk
        String data[][] = null;
        //String typeAbbr[];
        String query = null;
        String query2 = null;

        //String typeAbbrBeach = typeAbbr.replaceAll("^[ \\t]+|[ \\t]+$\", "");

        /*System.out.println( typeAbbr.replaceAll("^[ \t]+|[ \t]+$", "") );    //"Hello World !!"
        System.out.println( typeAbbr.replaceAll("^[ \t]+", "") );            //"Hello World !!   "
        System.out.println( typeAbbr.replaceAll("[ \t]+$", "") );            //"  Hello World !!"
        */
        System.out.println(typeAbbr);


        switch (typeAbbr) {
            case "ML":
                query = "SELECT * FROM titles_Music;";
                query2 = "SELECT * FROM titles_LiveConcerts;";
                break;
            case ("VL"):
                query = "SELECT * FROM titles_Movies";
                break;
            case "TV":
                query = "SELECT * FROM titles_TVBox";
                break;
            //{"Music", "Live Concert", "Movie", "Box Set"}
            case ("Live Concert"):
                query = "SELECT * FROM titles_LiveConcerts;";
                break;
            case ("Music"):
                query = "SELECT * FROM titles_Music;";
                break;
            case ("Movies"):
                query = "SELECT * FROM titles_Movies;";
                break;
            case ("TV Box"):
                query = "SELECT * FROM titles_TVBox;";
                break;
        }

        System.out.println(query);
        System.out.println("query2 is: " + query2);


        try {

            //data base drive
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String dbServer = "jdbc:mysql://database-1.czpswiukhzqy.eu-west-1.rds.amazonaws.com:3306/UltraVision?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String user = "connectionUltra";
            String password = "!Pass123!";
            //String sql = "SELECT 'title', 'title_id' FROM ";

            //Get connection
            conn = DriverManager.getConnection(dbServer, user, password);

            //get stmt
            stmt = conn.createStatement();
            stmt2 = conn.createStatement();

            //result set
            rs = stmt.executeQuery(query);
            rs2 = null;

            //System.out.println(rs);
            //just a little scan to count the rs's size
            int size = 0;

            if (typeAbbr.equalsIgnoreCase("ML")) {
                int size2 = checkSizeTypeML(typeAbbr, query2);


                data = new String[size2][10];
                //rs2.close();
            } else {
                size = checkSizeTypeAbbr(typeAbbr);

                data = new String[size][10];
            }

            System.out.println("dataX: " + data.length);
            System.out.println("dataY: " + data[0].length);
            //array for the titles & ids


            //loop the thing
            if(typeAbbr.equalsIgnoreCase("Live Concert")) {
                System.out.println("Live Concert:" + typeAbbr.equalsIgnoreCase("Live Concert"));

                data = null;
                data = new String[size][11];

                fetchStringsForMainContent(query, data);

            }else if (!typeAbbr.equalsIgnoreCase("ML")) {
                fetchStringsForLeftMenu(query, data);

            }else{
                fetchStringsForLeftMenuML(query, query2, data);

            }


        } catch (SQLException se) {
            System.out.println("SQL Exception: ");

            System.out.println("State :" + se.getSQLState());
            System.out.println("Message :" + se.getMessage());
            System.out.println("Error Code :" + se.getErrorCode());


        } catch (Exception e) {
            System.out.println("Error Exception is: " + e);

        } finally {
            //Close the result set, statement and the connection
            rs.close();
            stmt.close();
            //stmt2.close();
            conn.close();
        }

        return data;
    }

    private void fetchStringsForMainContent(String query, String[][] data) throws SQLException {
        int row = 0;
            rs = stmt.executeQuery(query);
        System.out.println("dataX: " + data.length);
        System.out.println("dataY: " + data[0].length);

            while (rs.next()) {

            //console view
            System.out.println(rs.getString("title_id") + "\t" + rs.getString("title") + "\t" + rs.getString("mediaType") + "\t" + rs.getString("type") + "\t" + rs.getString("typeAbbr") + "\t" + rs.getString("bandDirector") + "\t" + rs.getString("description") + "\t" + rs.getString("genre") + "\t" + rs.getString("yearOfRelease") + "\t" + rs.getString("price") + "\t" + rs.getString("fk_Live_rent_id"));

            //filling the jobs ^^
            data[row][0] = rs.getString("title_id");
            data[row][1] = rs.getString("title");
            data[row][2] = rs.getString("mediaType");
            data[row][3] = rs.getString("type");
            data[row][4] = rs.getString("typeAbbr");
            data[row][5] = rs.getString("bandDirector");
            data[row][6] = rs.getString("description");
            data[row][7] = rs.getString("genre");
            data[row][8] = rs.getString("yearOfRelease");
            data[row][9] = rs.getString("price");
            data[row][10] = rs.getString("fk_Live_rent_id");


            row++;
        }
    }

    private void fetchStringsForLeftMenu(String query, String[][] data) throws SQLException {
        int row = 0;
        rs = stmt.executeQuery(query);
        while (rs.next()) {

            //console view
            System.out.println(rs.getString("title_id") + "\t" + rs.getString("title") + "\t" + rs.getString("mediaType") + "\t" + rs.getString("type") + "\t" + rs.getString("typeAbbr") + "\t" + rs.getString("bandDirector") + "\t" + rs.getString("description") + "\t" + rs.getString("genre") + "\t" + rs.getString("yearOfRelease") + "\t" + rs.getString("price"));

            //filling the jobs ^^
            data[row][0] = rs.getString("title_id");
            data[row][1] = rs.getString("title");
            data[row][2] = rs.getString("mediaType");
            data[row][3] = rs.getString("type");
            data[row][4] = rs.getString("typeAbbr");
            data[row][5] = rs.getString("bandDirector");
            data[row][6] = rs.getString("description");
            data[row][7] = rs.getString("genre");
            data[row][8] = rs.getString("yearOfRelease");
            data[row][9] = rs.getString("price");
            //data[row][10] = rs.getString("fk_Live_rent_id");

            row++;
        }

    }


    private void fetchStringsForLeftMenuML(String query, String query2, String[][] data) throws SQLException {
//        System.out.println("Estamos no caminho certo");
        int row = 0;
        rs = stmt.executeQuery(query);
        while (rs.next()) {

            //console view
            System.out.println(rs.getString("title_id") + "\t" + rs.getString("title") + "\t" + rs.getString("mediaType") + "\t" + rs.getString("type") + "\t" + rs.getString("typeAbbr") + "\t" + rs.getString("bandDirector") + "\t" + rs.getString("description") + "\t" + rs.getString("genre") + "\t" + rs.getString("yearOfRelease") + "\t" + rs.getString("price"));

            //filling the jobs ^^
            data[row][0] = rs.getString("title_id");
            data[row][1] = rs.getString("title");
            data[row][2] = rs.getString("mediaType");
            data[row][3] = rs.getString("type");
            data[row][4] = rs.getString("typeAbbr");
            data[row][5] = rs.getString("bandDirector");
            data[row][6] = rs.getString("description");
            data[row][7] = rs.getString("genre");
            data[row][8] = rs.getString("yearOfRelease");
            data[row][9] = rs.getString("price");
            //data[row][10] = rs.getString("fk_Live_rent_id");

            row++;
        }

//        System.out.println("Segundo caminho certo");
        rs2 = stmt2.executeQuery(query2);
        while (rs2.next()) {

            //console view
            System.out.println(rs2.getString("title_id") + "\t" + rs2.getString("title") + "\t" + rs2.getString("mediaType") + "\t" + rs2.getString("type") + "\t" + rs2.getString("typeAbbr") + "\t" + rs2.getString("bandDirector") + "\t" + rs2.getString("description") + "\t" + rs2.getString("genre") + "\t" + rs2.getString("yearOfRelease") + "\t" + rs2.getString("price"));

            //filling the jobs2 ^^
            data[row][0] = rs2.getString("title_id");
            data[row][1] = rs2.getString("title");
            data[row][2] = rs2.getString("mediaType");
            data[row][3] = rs2.getString("type");
            data[row][4] = rs2.getString("typeAbbr");
            data[row][5] = rs2.getString("bandDirector");
            data[row][6] = rs2.getString("description");
            data[row][7] = rs2.getString("genre");
            data[row][8] = rs2.getString("yearOfRelease");
            data[row][9] = rs2.getString("price");
            row++;
        }

        rs2.close();
        stmt2.close();
    }


    private int checkSizeTypeAbbr(String typeAbbr) throws SQLException {
        int size = 0;
        System.out.println("Size: " + size);
        System.out.println("typeAbbr inside checkSizeTypeAbbr: " + typeAbbr);
        while (rs.next()) {
            //String line1 = rs.next();
            size++;
        }
        System.out.println("Size: " + size);
        return size;
    }


    private int checkSizeTypeML(String typeAbbr, String query2) throws SQLException {
        int size = 0;
        System.out.println("Size: " + size);
        System.out.println(typeAbbr.equalsIgnoreCase("ML"));
        while (rs.next()) {
            //String line1 = rs.next();
            size++;
        }

        System.out.println("Size: " + size);
        //data = new String[3][size];

        rs2 = stmt2.executeQuery(query2);
        int size2 = 0;
        while (rs2.next()) {
            size2++;
        }

        System.out.println("Size2: " + size2);

        size2 = size + size2;
        System.out.println("Size2: " + size2);

//        rs.close();
//        rs2.close();
        return size2;
    }

    public String[][] selectInTheBeachAlone(String id, String typeAbbr) {
        //method designed to select with an id

        String data[][] = null;
        //String typeAbbr[];
        String query = null;

        System.out.println(typeAbbr);


        switch (typeAbbr) {
            case "ML":
                query = "SELECT * FROM titles_Music;";
//                query2 = "SELECT * FROM titles_LiveConcerts;";
                break;
            case ("VL"):
                query = "SELECT 'title', 'title_id' FROM titles_Movies";
                break;
            case "TV":
                query = "SELECT 'title', 'title_id' FROM titles_TVBox";
                break;
            case ("Live Concert"):
                query = "SELECT * FROM titles_LiveConcerts;";
                break;
        }

        System.out.println(query);

        return data;
    }

    public void insertInTheBeach2D(String selectedTitleString, String[] allGuys) {

        String query = null;
        System.out.println(selectedTitleString);


        switch (selectedTitleString){
            //{"Music", "Live Concert", "Movie", "Box Set"}
            case ("Live Concert"):
                //query = "INSERT INTO 'titles_LiveConcerts' ('title', 'mediaType', 'band/director', 'description', 'genre', 'yearOfRelease', 'price') VALUES ('" + allGuys[0] + "', '" + allGuys[1] + "', '" + allGuys[2] + "', '" + allGuys[3] + "', '" + allGuys[4] + "', '" + allGuys[5] + "', '" + allGuys[6] + "');";
                query = "INSERT INTO titles_LiveConcerts (title, mediaType, bandDirector, description, genre, yearOfRelease, price)" + " VALUES (?, ?, ?, ?, ?, ?, ?);";
                break;
            case ("Music"):
                //query = "INSERT INTO 'titles_LiveConcerts' ('title', 'mediaType', 'band/director', 'description', 'genre', 'yearOfRelease', 'price') VALUES ('" + allGuys[0] + "', '" + allGuys[1] + "', '" + allGuys[2] + "', '" + allGuys[3] + "', '" + allGuys[4] + "', '" + allGuys[5] + "', '" + allGuys[6] + "');";
                query = "INSERT INTO titles_Music (title, mediaType, bandDirector, description, genre, yearOfRelease, price)" + " VALUES (?, ?, ?, ?, ?, ?, ?);";
                break;
            case ("Movie"):
                //query = "INSERT INTO 'titles_LiveConcerts' ('title', 'mediaType', 'band/director', 'description', 'genre', 'yearOfRelease', 'price') VALUES ('" + allGuys[0] + "', '" + allGuys[1] + "', '" + allGuys[2] + "', '" + allGuys[3] + "', '" + allGuys[4] + "', '" + allGuys[5] + "', '" + allGuys[6] + "');";
                query = "INSERT INTO titles_Movies (title, mediaType, bandDirector, description, genre, yearOfRelease, price)" + " VALUES (?, ?, ?, ?, ?, ?, ?);";
                break;
            case ("Box Set"):
                //query = "INSERT INTO 'titles_LiveConcerts' ('title', 'mediaType', 'band/director', 'description', 'genre', 'yearOfRelease', 'price') VALUES ('" + allGuys[0] + "', '" + allGuys[1] + "', '" + allGuys[2] + "', '" + allGuys[3] + "', '" + allGuys[4] + "', '" + allGuys[5] + "', '" + allGuys[6] + "');";
                query = "INSERT INTO titles_TVBox (title, mediaType, bandDirector, description, genre, yearOfRelease, price)" + " VALUES (?, ?, ?, ?, ?, ?, ?);";
                break;

        }

        try {

            //data base drive
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String dbServer = "jdbc:mysql://database-1.czpswiukhzqy.eu-west-1.rds.amazonaws.com:3306/UltraVision?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String user = "connectionUltra";
            String password = "!Pass123!";
            //String sql = "SELECT 'title', 'title_id' FROM ";

            //Get connection
            conn = DriverManager.getConnection(dbServer, user, password);

            //creating statment
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, allGuys[0]);
            pst.setString(2, allGuys[1]);
            pst.setString(3, allGuys[2]);
            pst.setString(4, allGuys[3]);
            pst.setString(5, allGuys[4]);
            pst.setString(6, allGuys[5]);
            pst.setString(7, allGuys[6]);

            pst.executeUpdate();

            conn.close();

            JOptionPane.showMessageDialog(null, "updated successfully");


        }catch (HeadlessException | ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex) {
            System.out.println(ex);
            System.out.println(ex.getCause());
            System.out.println(ex.getMessage());
            System.out.println(ex.toString());
        }

    }

    public void updateInTheBeach2D(String selectedTitleString, String[] allGuys, String id) {

        String query = null;
        System.out.println(selectedTitleString);


        switch (selectedTitleString){
            //{"Music", "Live Concert", "Movie", "Box Set"}
            case ("Live Concert"):
                //query = "INSERT INTO 'titles_LiveConcerts' ('title', 'mediaType', 'band/director', 'description', 'genre', 'yearOfRelease', 'price') VALUES ('" + allGuys[0] + "', '" + allGuys[1] + "', '" + allGuys[2] + "', '" + allGuys[3] + "', '" + allGuys[4] + "', '" + allGuys[5] + "', '" + allGuys[6] + "');";
                //query = "INSERT INTO titles_LiveConcerts (title, mediaType, bandDirector, description, genre, yearOfRelease, price)" + " VALUES (?, ?, ?, ?, ?, ?, ?);";
                query = "UPDATE titles_LiveConcerts SET title=?, mediaType=?, bandDirector=?, description=?, genre=?, yearOfRelease=?, price=? WHERE title_id='"
                        + id + "';";
                break;
            case ("Music"):
                //query = "INSERT INTO 'titles_LiveConcerts' ('title', 'mediaType', 'band/director', 'description', 'genre', 'yearOfRelease', 'price') VALUES ('" + allGuys[0] + "', '" + allGuys[1] + "', '" + allGuys[2] + "', '" + allGuys[3] + "', '" + allGuys[4] + "', '" + allGuys[5] + "', '" + allGuys[6] + "');";
                //query = "INSERT INTO titles_LiveConcerts (title, mediaType, bandDirector, description, genre, yearOfRelease, price)" + " VALUES (?, ?, ?, ?, ?, ?, ?);";
                query = "UPDATE titles_Music SET title=?, mediaType=?, bandDirector=?, description=?, genre=?, yearOfRelease=?, price=? WHERE title_id='"
                        + id + "';";
                break;
            case ("Movie"):
                //query = "INSERT INTO 'titles_LiveConcerts' ('title', 'mediaType', 'band/director', 'description', 'genre', 'yearOfRelease', 'price') VALUES ('" + allGuys[0] + "', '" + allGuys[1] + "', '" + allGuys[2] + "', '" + allGuys[3] + "', '" + allGuys[4] + "', '" + allGuys[5] + "', '" + allGuys[6] + "');";
                //query = "INSERT INTO titles_LiveConcerts (title, mediaType, bandDirector, description, genre, yearOfRelease, price)" + " VALUES (?, ?, ?, ?, ?, ?, ?);";
                query = "UPDATE titles_Movies SET title=?, mediaType=?, bandDirector=?, description=?, genre=?, yearOfRelease=?, price=? WHERE title_id='"
                        + id + "';";
                break;
            case ("Box Set"):
                //query = "INSERT INTO 'titles_LiveConcerts' ('title', 'mediaType', 'band/director', 'description', 'genre', 'yearOfRelease', 'price') VALUES ('" + allGuys[0] + "', '" + allGuys[1] + "', '" + allGuys[2] + "', '" + allGuys[3] + "', '" + allGuys[4] + "', '" + allGuys[5] + "', '" + allGuys[6] + "');";
                //query = "INSERT INTO titles_LiveConcerts (title, mediaType, bandDirector, description, genre, yearOfRelease, price)" + " VALUES (?, ?, ?, ?, ?, ?, ?);";
                query = "UPDATE titles_TVBox SET title=?, mediaType=?, bandDirector=?, description=?, genre=?, yearOfRelease=?, price=? WHERE title_id='"
                        + id + "';";
                break;

        }

        try {

            //data base drive
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String dbServer = "jdbc:mysql://database-1.czpswiukhzqy.eu-west-1.rds.amazonaws.com:3306/UltraVision?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String user = "connectionUltra";
            String password = "!Pass123!";
            //String sql = "SELECT 'title', 'title_id' FROM ";

            //Get connection
            conn = DriverManager.getConnection(dbServer, user, password);

            //creating statment
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, allGuys[0]);
            pst.setString(2, allGuys[1]);
            pst.setString(3, allGuys[2]);
            pst.setString(4, allGuys[3]);
            pst.setString(5, allGuys[4]);
            pst.setString(6, allGuys[5]);
            pst.setString(7, allGuys[6]);

            pst.executeUpdate();

            conn.close();

            JOptionPane.showMessageDialog(null, "updated successfully");


        }catch (HeadlessException | ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex) {
            System.out.println(ex);
            System.out.println(ex.getCause());
            System.out.println(ex.getMessage());
            System.out.println(ex.toString());
        }


    }

    public void deleteThisHeck(String type, String id) {

        System.out.println("Your in the hell session");

        String query = null;
        System.out.println(id);


        switch (type){
            //{"Music", "Live Concert", "Movie", "Box Set"}
            case ("Live Concert"):
                //query = "INSERT INTO 'titles_LiveConcerts' ('title', 'mediaType', 'band/director', 'description', 'genre', 'yearOfRelease', 'price') VALUES ('" + allGuys[0] + "', '" + allGuys[1] + "', '" + allGuys[2] + "', '" + allGuys[3] + "', '" + allGuys[4] + "', '" + allGuys[5] + "', '" + allGuys[6] + "');";
                //query = "INSERT INTO titles_LiveConcerts (title, mediaType, bandDirector, description, genre, yearOfRelease, price)" + " VALUES (?, ?, ?, ?, ?, ?, ?);";
                query = "DELETE from titles_LiveConcerts WHERE title_id='" + id + "';";
                break;
            case ("Music"):
                //query = "INSERT INTO 'titles_LiveConcerts' ('title', 'mediaType', 'band/director', 'description', 'genre', 'yearOfRelease', 'price') VALUES ('" + allGuys[0] + "', '" + allGuys[1] + "', '" + allGuys[2] + "', '" + allGuys[3] + "', '" + allGuys[4] + "', '" + allGuys[5] + "', '" + allGuys[6] + "');";
                //query = "INSERT INTO titles_LiveConcerts (title, mediaType, bandDirector, description, genre, yearOfRelease, price)" + " VALUES (?, ?, ?, ?, ?, ?, ?);";
                query = "DELETE from titles_Music WHERE title_id='" + id + "';";
                break;
            case ("Movie"):
                //query = "INSERT INTO 'titles_LiveConcerts' ('title', 'mediaType', 'band/director', 'description', 'genre', 'yearOfRelease', 'price') VALUES ('" + allGuys[0] + "', '" + allGuys[1] + "', '" + allGuys[2] + "', '" + allGuys[3] + "', '" + allGuys[4] + "', '" + allGuys[5] + "', '" + allGuys[6] + "');";
                //query = "INSERT INTO titles_LiveConcerts (title, mediaType, bandDirector, description, genre, yearOfRelease, price)" + " VALUES (?, ?, ?, ?, ?, ?, ?);";
                query = "DELETE from titles_Movies WHERE title_id='" + id + "';";
                break;
            case ("Box Set"):
                //query = "INSERT INTO 'titles_LiveConcerts' ('title', 'mediaType', 'band/director', 'description', 'genre', 'yearOfRelease', 'price') VALUES ('" + allGuys[0] + "', '" + allGuys[1] + "', '" + allGuys[2] + "', '" + allGuys[3] + "', '" + allGuys[4] + "', '" + allGuys[5] + "', '" + allGuys[6] + "');";
                //query = "INSERT INTO titles_LiveConcerts (title, mediaType, bandDirector, description, genre, yearOfRelease, price)" + " VALUES (?, ?, ?, ?, ?, ?, ?);";
                query = "DELETE from titles_TVBox WHERE title_id='" + id + "';";
                break;

        }

        try {

            //data base drive
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String dbServer = "jdbc:mysql://database-1.czpswiukhzqy.eu-west-1.rds.amazonaws.com:3306/UltraVision?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String user = "connectionUltra";
            String password = "!Pass123!";
            //String sql = "SELECT 'title', 'title_id' FROM ";

            //Get connection
            conn = DriverManager.getConnection(dbServer, user, password);

            //creating statment
            Statement stmt = conn.createStatement();

            stmt.executeUpdate(query);

            conn.close();

            JOptionPane.showMessageDialog(null, "Deleted successfully");


        }catch (HeadlessException | ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex) {
            System.out.println(ex);
            System.out.println(ex.getCause());
            System.out.println(ex.getMessage());
            System.out.println(ex.toString());
        }


    }

    public String[][] selectTheBirds2D() throws SQLException {

        String data[][] = null;
        String query = "SELECT * FROM customers;";

        try {

            //data base drive
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String dbServer = "jdbc:mysql://database-1.czpswiukhzqy.eu-west-1.rds.amazonaws.com:3306/UltraVision?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String user = "connectionUltra";
            String password = "!Pass123!";
            //String sql = "SELECT 'title', 'title_id' FROM ";

            //Get connection
            conn = DriverManager.getConnection(dbServer, user, password);

            //get stmt
            stmt = conn.createStatement();

            //result set
            rs = stmt.executeQuery(query);

            int size = 0;
            while (rs.next()) {
                //String line1 = rs.next();
                size++;
            }

            System.out.println(size + " Users");
            data = new String[size][10];

            int row = 0;
            rs = stmt.executeQuery(query);
            while (rs.next()) {

                //`cust_id`, `fname`, `lname`, `email`, `password`, `phone`, `administrator`, `username`, `quantRented`, `points`
                //filling the jobs ^^
                data[row][0] = rs.getString("cust_id");
                data[row][1] = rs.getString("fname");
                data[row][2] = rs.getString("lname");
                data[row][3] = rs.getString("email");
                data[row][4] = rs.getString("password");
                data[row][5] = rs.getString("phone");
                data[row][6] = rs.getString("administrator");
                data[row][7] = rs.getString("username");
                data[row][8] = rs.getString("quantRented");
                data[row][9] = rs.getString("points");

                row++;
            }

        } catch (SQLException se) {
            System.out.println("SQL Exception: ");

            System.out.println("State :" + se.getSQLState());
            System.out.println("Message :" + se.getMessage());
            System.out.println("Error Code :" + se.getErrorCode());
        } catch (Exception e) {
            System.out.println("Error Exception is: " + e);

        } finally {
            //Close the result set, statement and the connection
            rs.close();
            stmt.close();
            //stmt2.close();
            conn.close();
        }

        return data;

        }

    public void insertNewBird(String[] allGuys) {

        String query = "INSERT INTO customers (fname, lname, email, password, phone, administrator, username)" + " VALUES (?, ?, ?, ?, ?, ?, ?);" ;

        try {

            //data base drive
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String dbServer = "jdbc:mysql://database-1.czpswiukhzqy.eu-west-1.rds.amazonaws.com:3306/UltraVision?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String user = "connectionUltra";
            String password = "!Pass123!";

            //Get connection
            conn = DriverManager.getConnection(dbServer, user, password);

            //creating statment
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, allGuys[0]);
            pst.setString(2, allGuys[1]);
            pst.setString(3, allGuys[2]);
            pst.setString(4, allGuys[3]);
            pst.setString(5, allGuys[4]);
            if (allGuys[5].equalsIgnoreCase("Yes")) {
                pst.setString(6, "1");
            } else if (allGuys[5].equalsIgnoreCase("No")) {
                pst.setString(6, "0");
            }
            pst.setString(7, allGuys[6]);

            pst.executeUpdate();

            conn.close();

            JOptionPane.showMessageDialog(null, "updated successfully");


        }catch (HeadlessException | ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex) {
            System.out.println(ex);
            System.out.println(ex.getCause());
            System.out.println(ex.getMessage());
            System.out.println(ex.toString());
        }

    }
}
