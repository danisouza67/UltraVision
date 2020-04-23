import resources.User;

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

        String data[][] = null;
        //String typeAbbr[];
        String query = null;
        String query2 = null;

        //String typeAbbrBeach = typeAbbr.replaceAll("^[ \\t]+|[ \\t]+$\", "");

        System.out.println( typeAbbr.replaceAll("^[ \t]+|[ \t]+$", "") );    //"Hello World !!"
        System.out.println( typeAbbr.replaceAll("^[ \t]+", "") );            //"Hello World !!   "
        System.out.println( typeAbbr.replaceAll("[ \t]+$", "") );            //"  Hello World !!"

        System.out.println(typeAbbr);


        switch (typeAbbr) {
            case "ML":
                query = "SELECT * FROM titles_Music;";
                query2 = "SELECT * FROM titles_LiveConcerts;";
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
        System.out.println(query2);


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


                data = new String[size2][3];
                //rs2.close();
            } else {
                size = checkSizeTypeAbbr(typeAbbr);

                data = new String[3][size];
            }

            System.out.println("dataX: " + data.length);
            System.out.println("dataX: " + data[0].length);
            //array for the titles & ids


            //loop the thing
            if(typeAbbr.equalsIgnoreCase("Live Concert")) {
                System.out.println("Live Concert:" + typeAbbr.equalsIgnoreCase("Live Concert"));

                data = null;
                data = new String[11][size];

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
        System.out.println("dataX: " + data[0].length);

            while (rs.next()) {

            //console view
            System.out.println(rs.getString("title") + "\t" + rs.getString("title_id") + "\t" + rs.getString("mediaType") + "\t" + rs.getString("type") + "\t" + rs.getString("typeAbbr") + "\t" + rs.getString("band") + "\t" + rs.getString("description") + "\t" + rs.getString("genre") + "\t" + rs.getString("yearOfRelease") + "\t" + rs.getString("price") + "\t" + rs.getString("fk_Live_rent_id"));

            //filling the jobs ^^
            data[0][row] = rs.getString("title");
            data[1][row] = rs.getString("title_id");
            data[2][row] = rs.getString("mediaType");
            data[3][row] = rs.getString("type");
            data[4][row] = rs.getString("typeAbbr");
            data[5][row] = rs.getString("band");
            data[6][row] = rs.getString("description");
            data[7][row] = rs.getString("genre");
            data[8][row] = rs.getString("yearOfRelease");
            data[9][row] = rs.getString("price");
            data[10][row] = rs.getString("fk_Live_rent_id");


            row++;
        }
    }

    private void fetchStringsForLeftMenu(String query, String[][] data) throws SQLException {
        int row = 0;
        rs = stmt.executeQuery(query);
        while (rs.next()) {

            //console view
            //System.out.println(rs.getString("title") + "\t" + rs.getString("title_id"));

            //filling the jobs ^^
            data[row][0] = rs.getString("title");
            data[row][1] = rs.getString("title_id");
            data[row][2] = rs.getString("type");

            row++;
        }
    }


    private void fetchStringsForLeftMenuML(String query, String query2, String[][] data) throws SQLException {
        int row = 0;
        rs = stmt.executeQuery(query);
        while (rs.next()) {

            //console view
            //System.out.println(rs.getString("title") + "\t" + rs.getString("title_id"));

            //filling the jobs ^^
            data[row][0] = rs.getString("title");
            data[row][1] = rs.getString("title_id");
            data[row][2] = rs.getString("type");

            row++;
        }
        rs2 = stmt2.executeQuery(query2);
        while (rs2.next()) {

            //console view
            //System.out.println(rs.getString("title") + "\t" + rs.getString("title_id"));

            //filling the jobs2 ^^
            data[row][0] = rs2.getString("title");
            data[row][1] = rs2.getString("title_id");
            data[row][2] = rs2.getString("type");
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
}
