import javax.swing.*;

public class Profile extends JFrame {
    private JButton logoutButton;
    private JLabel E;
    private JLabel nameT;
    private JLabel emailT;
    private JLabel addressT;
    private JLabel phoneT;
    private JPanel profilePanel;

    public Profile(){
        setContentPane(profilePanel);
        setTitle("Profile");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public static void main(String[] args) {
        Profile loginFrame = new Profile();
    }
}
