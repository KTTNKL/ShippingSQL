import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
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
    public User(String id, String name, String address, String email, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
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

    Connection connectData(String login) {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser(login);
        ds.setPassword("123");
        ds.setServerName("DESKTOP-J84I4CF\\KYLUONG");
        ds.setPortNumber(1433);
        ds.setDatabaseName("Online_Shopping");
        Connection conn = null;


        try{
            conn = ds.getConnection();
            if (conn.getMetaData().supportsTransactionIsolationLevel(Connection.TRANSACTION_REPEATABLE_READ)) {
                conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            }


            //Fix phantom
//            if (conn.getMetaData().supportsTransactionIsolationLevel(Connection.TRANSACTION_SERIALIZABLE)) {
//                conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
//            }
        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }


}

class ManageUsers{
    public static List<User> ViewAllCustomers(String login){
        List<User> userList = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        try {
            DataConnection app = new DataConnection();
            connection = app.connectData(login);
            String sql = "SELECT * FROM KhachHang ";
            statement = connection.createStatement();
            ResultSet res = statement.executeQuery(sql);
            while (res.next()) {
                User usr = new User(res.getString("MaKhachHang"),
                        res.getString("TenKhachHang"),
                        res.getString("DiaChi"),
                        res.getString("Email"),
                        res.getString("SDT"));

                //usr.show();
                userList.add(usr);
            }
        } catch (SQLException  ex) {
            Logger.getLogger(ManageUsers.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {

                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {

                }
            }
        }
        return userList;
    }

    public static List<User> ReadWriteDelay(User customer, String login) throws SQLException {

        List<User> userList = new ArrayList<>();
        PreparedStatement pstatement = null;
        Connection connection = null;
        Statement statement = null;



        try {

            DataConnection app = new DataConnection();
            connection = app.connectData(login);
            String sql = "SELECT * FROM KhachHang ";
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            ResultSet res = statement.executeQuery(sql);
            while (res.next()) {
                User usr = new User(res.getString("MaKhachHang"),
                        res.getString("TenKhachHang"),
                        res.getString("DiaChi"),
                        res.getString("Email"),
                        res.getString("SDT"));

                //usr.show();
                userList.add(usr);
            }

            TimeUnit.SECONDS.sleep(10);

            sql = "UPDATE Khachhang SET TenKhachHang = ? , DiaChi = ?, Email = ?, SDT = ?"
                    + " WHERE MaKhachHang = ?";
            pstatement = connection.prepareStatement(sql);

            pstatement.setString(1, customer.getName());
            pstatement.setString(2, customer.getAddress());
            pstatement.setString(3, customer.getEmail());
            pstatement.setString(4, customer.getPhone());
            pstatement.setString(5, customer.getId());


            pstatement.execute();
            connection.commit();

        } catch (SQLException | InterruptedException ex) {
            Logger.getLogger(ManageUsers.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    connection.rollback();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    connection.rollback();
                }
            }
        }
        return userList;
    }

    public static List<User> ReadWrite(User customer, String login) throws SQLException {
        List<User> userList = new ArrayList<>();
        PreparedStatement pstatement = null;
        Connection connection = null;
        Statement statement = null;
        try {
            DataConnection app = new DataConnection();
            connection = app.connectData(login);
            String sql = "SELECT * FROM KhachHang ";
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            ResultSet res = statement.executeQuery(sql);
            while (res.next()) {
                User usr = new User(res.getString("MaKhachHang"),
                        res.getString("TenKhachHang"),
                        res.getString("DiaChi"),
                        res.getString("Email"),
                        res.getString("SDT"));

                //usr.show();
                userList.add(usr);
            }

            sql = "UPDATE Khachhang SET TenKhachHang = ? , DiaChi = ?, Email = ?, SDT = ?"
                    + " WHERE MaKhachHang = ?";
            pstatement = connection.prepareStatement(sql);

            pstatement.setString(1, customer.getName());
            pstatement.setString(2, customer.getAddress());
            pstatement.setString(3, customer.getEmail());
            pstatement.setString(4, customer.getPhone());
            pstatement.setString(5, customer.getId());


            pstatement.execute();
            connection.commit();

        } catch (SQLException ex) {
            Logger.getLogger(ManageUsers.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    connection.rollback();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    connection.rollback();
                }
            }
        }
        return userList;
    }


    public static void deleteCustomer(String id, String login) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            DataConnection app = new DataConnection();
            connection = app.connectData(login);
            String sql = "delete from KhachHang where MaKhachHang = ?";
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            statement.execute();
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(ManageUsers.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ManageUsers.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ManageUsers.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static void editCustomer(User customer, String login){
        Connection connection = null;
        PreparedStatement statement = null;
        try {

            DataConnection app = new DataConnection();
            connection = app.connectData(login);

            String sql = "UPDATE Khachhang SET TenKhachHang = ? , DiaChi = ?, Email = ?, SDT = ?"
                    + " WHERE MaKhachHang = ?";
            statement = connection.prepareStatement(sql);

            statement.setString(1, customer.getName());
            statement.setString(2, customer.getAddress());
            statement.setString(3, customer.getEmail());
            statement.setString(4, customer.getPhone());
            statement.setString(5, customer.getId());


            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ManageUsers.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ManageUsers.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ManageUsers.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static void editCustomerDelay(User customer, String login){
        Connection connection = null;
        PreparedStatement statement = null;
        try {

            DataConnection app = new DataConnection();
            connection = app.connectData(login);
            connection.setAutoCommit(false);


            TimeUnit.SECONDS.sleep(10);
            if(customer.getName().equals("1")){
                connection.rollback();
            }
            else{
                connection.commit();
            }

        } catch (SQLException | InterruptedException ex) {
            Logger.getLogger(ManageUsers.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ManageUsers.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ManageUsers.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static  List<User> Unrepeatable(String ID, String login) {
        List<User> userList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            DataConnection app = new DataConnection();
            connection = app.connectData(login);
            connection.setAutoCommit(false);
            //query
            String sql = "select * from KhachHang where MaKhachHang = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1,  ID );

            ResultSet res = statement.executeQuery();

            while (res.next()) {
                User usr = new User(res.getString("MaKhachHang"),
                        res.getString("TenKhachHang"),
                        res.getString("DiaChi"),
                        res.getString("Email"),
                        res.getString("SDT"));

                //usr.show();
                userList.add(usr);
            }
            if(userList.size() > 0){
                JOptionPane.showMessageDialog(null,"User exist");
            }
            userList.clear();
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            statement = connection.prepareStatement(sql);
            statement.setString(1,  ID );

            res = statement.executeQuery();

            while (res.next()) {
                User usr = new User(res.getString("MaKhachHang"),
                        res.getString("TenKhachHang"),
                        res.getString("DiaChi"),
                        res.getString("Email"),
                        res.getString("SDT"));

                //usr.show();
                userList.add(usr);
            }
            if(userList.size() == 0){
                JOptionPane.showMessageDialog(null,"Cannot show user's information");
            }
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(ManageUsers.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ManageUsers.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ManageUsers.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return userList;
    }

}

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
    private JButton deleteCustomerButton;
    private JButton deleteDriverButton;
    private JButton deleteBusinessButton;
    private JLabel countCustomerT;
    private JButton btnPhantom;
    private JButton btnUnrepeatable;
    private JButton btnC1;
    private JButton btnRefresh;
    private JButton C1Button;
    private JButton C2Button;


    List<User> customerList = new ArrayList<>();
    public ManageAccount(String login){
        setContentPane(managePanel);
        setTitle("Profile");
        setSize(1000,700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        showUser(login);
        setCountCustomerT(login);

        deleteCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customerList = ManageUsers.ViewAllCustomers(login);
                int selectedIndex = customerTable.getSelectedRow();
                if (selectedIndex >= 0) {
                    User usr = customerList.get(selectedIndex);

                    int opt = JOptionPane.showConfirmDialog(null, "Do you want to delete this Customer?");
                    // yes is 0, no is 1, cancel is 2
                    if (opt == 0) {
                        ManageUsers.deleteCustomer(usr.getId(), login);
                        setCountCustomerT(login);
                        showUser(login);

                    }
                }

            }
        });
        editCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customerList = ManageUsers.ViewAllCustomers(login);
                int selectedIndex = customerTable.getSelectedRow();
                if(selectedIndex >= 0){
                    User customer = customerList.get(selectedIndex);
                    if(!nameT.getText().equals((""))) customer.setName(nameT.getText());
                    if(!emailT.getText().equals((""))) customer.setName(emailT.getText());
                    if(!addressT.getText().equals((""))) customer.setName(addressT.getText());
                    if(!phoneT.getText().equals((""))) customer.setName(phoneT.getText());
                    int opt = JOptionPane.showConfirmDialog(null, "Do you want to update this customer?");
                    if (opt == 0) {
                        ManageUsers.editCustomer(customer, login);
                        setCountCustomerT(login);
                        showUser(login);

                    }
                }
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Login();
            }
        });
        btnPhantom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    StringBuilder finalCount = new StringBuilder ();
                    List<User> customerList = new ArrayList<>();

                    customerList = showCountAndUsers(login, finalCount);

                    System.out.println(finalCount);
                    System.out.println(customerList.size());
                    countCustomerT.setText("Number of customers:   " + finalCount);
                    String data[][] = new String[customerList.size()][];
                    for (int i = 0; i < customerList.size(); i++) {
                        data[i] = new String[4];
                        data[i] = customerList.get(i).ObjectConverter();
                    }
                    customerTable.setModel(new DefaultTableModel(
                            data,
                            new String[]{"Name", "Address", "Email", "Phone"}
                    ));


                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

            }
        });
        btnUnrepeatable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Find By ID", "Enter ID to search");
                customerList = ManageUsers.Unrepeatable(input, login);
                String data[][] = new String[customerList.size()][];
                for (int i = 0; i < customerList.size(); i++) {
                    data[i] = new String[4];
                    data[i] = customerList.get(i).ObjectConverter();
                }
                customerTable.setModel(new DefaultTableModel(
                        data,
                        new String[]{"Name", "Address", "Email", "Phone"}
                ));
                clearTextField();
            }

        });

        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showUser(login);
                setCountCustomerT(login);
            }
        });
        C1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customerList = ManageUsers.ViewAllCustomers(login);
                int selectedIndex = customerTable.getSelectedRow();
                if(selectedIndex >= 0){
                    User customer = customerList.get(selectedIndex);
                    if(!nameT.getText().equals((""))) customer.setName(nameT.getText());
                    if(!emailT.getText().equals((""))) customer.setName(emailT.getText());
                    if(!addressT.getText().equals((""))) customer.setName(addressT.getText());
                    if(!phoneT.getText().equals((""))) customer.setName(phoneT.getText());
                    int opt = JOptionPane.showConfirmDialog(null, "Do you want to update this customer?");
                    if (opt == 0) {
                        try {
                            C1(customer, login);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        showUser(login);
                        setCountCustomerT(login);

                    }
                }
            }
        });
        C2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customerList = ManageUsers.ViewAllCustomers(login);
                int selectedIndex = customerTable.getSelectedRow();
                if(selectedIndex >= 0){
                    User customer = customerList.get(selectedIndex);
                    if(!nameT.getText().equals((""))) customer.setName(nameT.getText());
                    if(!emailT.getText().equals((""))) customer.setName(emailT.getText());
                    if(!addressT.getText().equals((""))) customer.setName(addressT.getText());
                    if(!phoneT.getText().equals((""))) customer.setName(phoneT.getText());
                    int opt = JOptionPane.showConfirmDialog(null, "Do you want to update this customer?");
                    if (opt == 0) {
                        try {
                            C2(customer, login);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        showUser(login);
                        setCountCustomerT(login);

                    }
                }
            }
        });
    }

    private void showUser(String login){
        customerList = ManageUsers.ViewAllCustomers(login);
        String data[][] = new String[customerList.size()][];
        for (int i = 0; i < customerList.size(); i++) {
            data[i] = new String[4];
            data[i] = customerList.get(i).ObjectConverter();
        }
        customerTable.setModel(new DefaultTableModel(
                data,
                new String[]{"Name", "Address", "Email", "Phone"}
        ));
        clearTextField();
    }

    private   List<User> showCountAndUsers(String login, StringBuilder finalCount) throws InterruptedException {
        int count =0;
        List<User> userList = new ArrayList<>();
        List<User> finalcustomerList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        try {
            DataConnection app = new DataConnection();
            connection = app.connectData(login);
            String sql = "SELECT * FROM KhachHang ";
//            connection.setAutoCommit(false);
            statement = connection.createStatement();
            ResultSet res = statement.executeQuery(sql);
            while (res.next()) {
                User usr = new User(res.getString("MaKhachHang"),
                        res.getString("TenKhachHang"),
                        res.getString("DiaChi"),
                        res.getString("Email"),
                        res.getString("SDT"));

                //usr.show();
                userList.add(usr);
            }
            List<User> customerList = userList;

            for(int i=0; i<customerList.size(); ++i){
                count++;
            }
            finalCount.append(String.valueOf(count));



            TimeUnit.SECONDS.sleep(10);
            sql = "SELECT * FROM KhachHang ";

            statement = connection.createStatement();
            res = statement.executeQuery(sql);
            while (res.next()) {
                User usr = new User(res.getString("MaKhachHang"),
                        res.getString("TenKhachHang"),
                        res.getString("DiaChi"),
                        res.getString("Email"),
                        res.getString("SDT"));

                //usr.show();
                finalcustomerList.add(usr);
            }
//            connection.commit();

        } catch (SQLException  ex) {
            Logger.getLogger(ManageUsers.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {

                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {

                }
            }
        }


        System.out.println(finalcustomerList.size());
        return finalcustomerList;
    }

    private void C1(User customer, String login) throws SQLException {
        customerList = ManageUsers.ReadWriteDelay(customer,login);
        String data[][] = new String[customerList.size()][];
        for (int i = 0; i < customerList.size(); i++) {
            data[i] = new String[4];
            data[i] = customerList.get(i).ObjectConverter();
        }
        customerTable.setModel(new DefaultTableModel(
                data,
                new String[]{"Name", "Address", "Email", "Phone"}
        ));
        clearTextField();
    }

    private void C2(User customer, String login) throws SQLException {
        customerList = ManageUsers.ReadWrite(customer,login);
        String data[][] = new String[customerList.size()][];
        for (int i = 0; i < customerList.size(); i++) {
            data[i] = new String[4];
            data[i] = customerList.get(i).ObjectConverter();
        }
        customerTable.setModel(new DefaultTableModel(
                data,
                new String[]{"Name", "Address", "Email", "Phone"}
        ));
        clearTextField();
    }


    private void setCountCustomerT(String login){
        this.countCustomerT.setText("Number of customers:   " + String.valueOf(countCustomer(login)));
    }

    private void clearTextField(){
        nameT.setText("");
        emailT.setText("");
        addressT.setText("");
        phoneT.setText("");
    }

    public int countCustomer(String login){
        int count =0;
        List<User> customerList = ManageUsers.ViewAllCustomers(login);
        for(int i=0; i<customerList.size(); ++i){
            count++;
        }
        return count;
    }

//    public static void main(String[] args) {
//        ManageAccount loginFrame = new ManageAccount("login3");
//
//
//    }
}
