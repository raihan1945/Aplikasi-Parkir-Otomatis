package AplikasiParkirOtomatis;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InputParkirForm extends JFrame {

    // Label realtime
    JLabel lblTanggal;
    JLabel lblJam;

    public InputParkirForm() {
        setTitle("Input Kendaraan");
        setSize(500, 280);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===== MENU BAR =====
        JMenuBar menuBar = new JMenuBar();
        JMenu menuParkir = new JMenu("Parkir");

        JMenuItem menuInput = new JMenuItem("Input Kendaraan");
        JMenuItem menuData = new JMenuItem("Data Parkir");

        menuParkir.add(menuInput);
        menuParkir.add(menuData);
        menuBar.add(menuParkir);
        setJMenuBar(menuBar);

        menuData.addActionListener(e -> {
            new ParkirForm();
            dispose();
        });

        // ===== FORM =====
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ID Kendaraan
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("ID Kendaraan"), gbc);

        gbc.gridx = 1;
        panel.add(new JTextField("", 20), gbc);

        // Jenis Kendaraan
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Jenis Kendaraan"), gbc);

        gbc.gridx = 1;
        JComboBox<String> cmbJenis = new JComboBox<>(
                new String[]{"Motor", "Mobil"}
        );
        panel.add(cmbJenis, gbc);

        // Tanggal Masuk (REAL TIME)
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Tanggal Masuk"), gbc);

        gbc.gridx = 1;
        lblTanggal = new JLabel();
        panel.add(lblTanggal, gbc);

        // Jam Masuk (REAL TIME)
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Jam Masuk"), gbc);

        gbc.gridx = 1;
        lblJam = new JLabel();
        panel.add(lblJam, gbc);

        // Submit
        gbc.gridx = 1; gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(new JButton("Submit"), gbc);

        add(panel, BorderLayout.CENTER);

        // ===== AKTIFKAN REAL TIME =====
        startDateTime();

        setVisible(true);
    }

    // ===== FUNGSI REAL TIME =====
    private void startDateTime() {
        Timer timer = new Timer(1000, e -> {
            Date now = new Date();

            SimpleDateFormat sdfTanggal = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat sdfJam = new SimpleDateFormat("HH:mm:ss");

            lblTanggal.setText(sdfTanggal.format(now));
            lblJam.setText(sdfJam.format(now));
        });
        timer.start();
    }
}