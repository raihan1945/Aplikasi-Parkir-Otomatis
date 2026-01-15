package AplikasiParkirOtomatis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginForm extends JFrame {

    JTextField txtUser;
    JPasswordField txtPass;

    public LoginForm() {
        setTitle("Login");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel lblTitle = new JLabel("Login", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblTitle, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Username"), gbc);

        gbc.gridx = 1;
        txtUser = new JTextField(15);
        txtUser.setText("admin");
        panel.add(txtUser, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Password"), gbc);

        gbc.gridx = 1;
        txtPass = new JPasswordField(15);
        panel.add(txtPass, gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        JButton btnLogin = new JButton("Login");
        panel.add(btnLogin, gbc);

        btnLogin.addActionListener(e -> {
            String user = txtUser.getText();
            String pass = new String(txtPass.getPassword());

            if(user.equals("admin") && pass.equals("admin")) {
                JOptionPane.showMessageDialog(this, "Login Berhasil");
                new ParkirForm();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Login Gagal");
            }
        });

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginForm();
    }
}
