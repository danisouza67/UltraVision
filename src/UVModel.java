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

    public boolean connection(User userLogged) {

        boolean login = false;

        try{
            // to load usr credentials

            //data base drive
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String dbServer = "jdbc:mysql://database-1.czpswiukhzqy.eu-west-1.rds.amazonaws.com:3306/UltraVision?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String user = "connectionUltra";
            String password = "!Pass123!";
            String query = "SELECT * FROM customers where username = '" + userLogged.getUsr() + "' and password = '" + userLogged.getPass() + "';";

            //get a connection to the database
            Connection conn = DriverManager.getConnection(dbServer, user, password);

            //get a statement from the connection
            Statement stmt = conn.createStatement();

            //execute the query
            ResultSet rs = stmt.executeQuery(query);


            if (rs.next()) {
                loginAdmin = rs.getString("administrator");
                loginID = rs.getString("cust_id");
                loginFname = rs.getString("fname");
                loginLname = rs.getString("lname");
                loginUsername = rs.getString("username");
            }

            //loginAdmin = login;
            //login = rs.next();
            if(loginUsername != null){
                login=true;
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

            //Close the result set, statement and the connection
            rs.close();
            stmt.close();
            conn.close();

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

        }

        return login;
    }

    public String[][] bringTitlesForLeftBar(String typeAbbr) {

        String data[][] = null;
        //String typeAbbr[];
        String query = null;

        System.out.println(typeAbbr);

        switch (typeAbbr){
            case "ML":
                query = "SELECT * FROM titles_Music;";
                break;
            case "VL":
                query = "SELECT 'title', 'title_id' FROM titles_Movies";
                break;
            case "TV":
                query = "SELECT 'title', 'title_id' FROM titles_TVBox";
                break;
        }


        System.out.println(query);

        try{

            //data base drive
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String dbServer = "jdbc:mysql://database-1.czpswiukhzqy.eu-west-1.rds.amazonaws.com:3306/UltraVision?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String user = "connectionUltra";
            String password = "!Pass123!";
            //String sql = "SELECT 'title', 'title_id' FROM ";

            //Get connection
            Connection conn = DriverManager.getConnection(dbServer, user, password);

            //get stmt
            Statement stmt = conn.createStatement();

            //result set
            ResultSet rs = stmt.executeQuery(query);

            //System.out.println(rs);
            //just a little scan to count the rs's size
            /*
            int size = 0;
            System.out.println("Size: " +size);
            while (rs != null){
                rs.last();
                size = rs.getRow();
            }
            System.out.println("Size: " +size);
            */

            //array for the titles & ids
            data = new String[2][2];
            int row = 0;

            //loop the thing
            while (rs.next()) {

                //console view
                //System.out.println(rs.getString("title") + "\t" + rs.getString("title_id"));

                //filling the jobs ^^
                data[row][0] = rs.getString("title");
                data[row][1] = rs.getString("title_id");

                row++;
            }

            rs.close();
            stmt.close();
            conn.close();

        }catch (SQLException se) {
            System.out.println("SQL Exception: ");

            //Loop through the sql exceptions
            while (se != null){
                System.out.println("State :" + se.getSQLState());
                System.out.println("Message :" + se.getMessage());
                System.out.println("Error Code :" + se.getErrorCode());

                se.getNextException();
            }

        }catch (Exception e){
            System.out.println("Error Exception is: " + e);
        }

        return data;
    }
}
