import javax.swing.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

class User {
    private String id;
    private String name;
    private String address;
    private String email;
    private String phone;

    public User(String name, String address, String email, String phone) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    public User(String maKhachHang, String tenKhachHang, String diaChi, String email, String sdt) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String[] ObjectConverter(){
        String data[]= new String[5];
        data[0] = id;
        data[1] = name;
        data[2] = address;
        data[3] = email;
        data[4] = phone;
        return data;
    }

    void show(){
        System.out.println(id);
        System.out.println(name);
        System.out.println(address);
        System.out.println(email);
        System.out.println(phone);
    }
}

class DataConnection{

    void connectData() {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser("login1");
        ds.setPassword("123");
        ds.setServerName("DESKTOP-J84I4CF\\KYLUONG");
        ds.setPortNumber(1433);
        ds.setDatabaseName("Online_Shopping");
        try(Connection conn = ds.getConnection()){
            System.out.println("ngon lanh");
            System.out.println(conn.getMetaData());
        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}

//class ManageUsers{
//    public static List<User> ViewAll(){
//        List<User> userList = new ArrayList<>();
//
//        Connection connection = null;
//        Statement statement = null;
//        try {
//            connection = DriverManager.getConnection("jdbc:sqlserver://yourserver.database.windows.net:1433;"
//                    + "database=AdventureWorks;"
//                    + "user=yourusername@yourserver;"
//                    + "password=yourpassword;"
//                    + "encrypt=true;"
//                    + "trustServerCertificate=false;"
//                    + "loginTimeout=30;");
//            String sql = "SELECT * FROM KhachHang ";
//            statement = connection.createStatement();
//            ResultSet res = statement.executeQuery(sql);
//
//            while (res.next()) {
//                User usr = new User(res.getString("MaKhachHang"),
//                        res.getString("TenKhachHang"),
//                        res.getString("DiaChi"),
//                        res.getString("Email"),
//                        res.getString("SDT"));
//
//                userList.add(usr);
//            }
//
//        } catch (SQLException  ex) {
//            Logger.getLogger(ManageUsers.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            if(statement != null) {
//                try {
//                    statement.close();
//                } catch (SQLException ex) {
//
//                }
//            }
//
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//
//                }
//            }
//        }
//
//        return userList;
//    }
//}

public class ManageAccount extends JFrame  {
    private JTable BusinessTable;
    private JTable customerTable;
    private JTable driverTable;
    private JTextField nameT;
    private JTextField emailT;
    private JButton logoutButton;
    private JButton editBusinessButton;
    private JButton editCustomerButton;
    private JButton editDriverButton;
    private JTextField addressT;
    private JTextField phoneT;
    private JPanel managePanel;

    public ManageAccount(){
        setContentPane(managePanel);
        setTitle("Profile");
        setSize(2050,1000);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public static void main(String[] args) {
        ManageAccount loginFrame = new ManageAccount();
        DataConnection a = new DataConnection();
        a.connectData();

    }
}
