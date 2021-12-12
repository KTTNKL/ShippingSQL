import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.SQLException;

class UserObject{
    private String name;
    private String email;
    private String address;
    private String phone;

    public UserObject(String username, String userEmail, String userAddress, String userPhone) {
        name = username;
        email = userEmail;
        address = userAddress;
        phone = userPhone;
    }
    public String getName(){return name;}
    public String getEmail(){return email;}
    public String getAddress(){return address;}
    public String getPhone(){return phone;}


}

public class Profile extends JFrame {
    private JButton logoutButton;
    private JLabel E;
    private JLabel nameT;
    private JLabel emailT;
    private JLabel addressT;
    private JLabel phoneT;
    private JPanel profilePanel;

    private Connection conn;

    UserObject values;
    public void loadData(){
        String sql = "SELECT * FROM THONGTIN_KH";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
                values = new UserObject ( rs.getString("TenKhachHang") ,rs.getString("Email"),rs.getString("DiaChi"),rs.getString("SDT"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        nameT.setText(values.getName());
        emailT.setText(values.getEmail());
        addressT.setText(values.getAddress());
        phoneT.setText(values.getPhone());
    }
    public Profile(){

        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser("login1");
        ds.setPassword("123");
        ds.setServerName("MSI\\SQLEXPRESS");
        ds.setPortNumber(1433);
        ds.setDatabaseName("Online_Shopping");
        try{
            conn = ds.getConnection();
            System.out.println("connect to database successfully");
        }catch(SQLServerException e){
            e.printStackTrace();
        }catch(SQLException e){
            e.printStackTrace();
        }
        loadData();
        setContentPane(profilePanel);
        setTitle("Profile");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);


        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login();
                dispose();
            }
        });
    }
    public static void main(String[] args) {
        Profile loginFrame = new Profile();
    }
}
