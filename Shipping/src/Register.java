import javax.swing.*;

public class Register extends JFrame {
    private JButton backButton;
    private JTextField nameT;
    private JTextField emailT;
    private JTextField phoneT;
    private JTextField addresssT;
    private JButton REGISTERACCOUNTButton;
    private JPasswordField passwordT;
    private JPanel registerPanel;

    public Register(){
        setContentPane(registerPanel);
        setTitle("Register");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public static void main(String[] args) {
        Register loginFrame = new Register();
    }
}
