import javax.swing.*;

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
    }
}
