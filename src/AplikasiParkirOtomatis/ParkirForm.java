package AplikasiParkirOtomatis;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ParkirForm extends JFrame {

    JTable table;
    JTextField txtBayar;

    // Label realtime
    JLabel lblTanggal;
    JLabel lblJam;

    public ParkirForm() {
        setTitle("Data Parkir");
        setSize(700, 350);
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

        menuInput.addActionListener(e -> {
            new InputParkirForm();
            dispose();
        });

        // ===== SEARCH =====
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("Search"));
        topPanel.add(new JTextField(20));
        add(topPanel, BorderLayout.NORTH);

        // ===== TABLE =====
        DefaultTableModel model = new DefaultTableModel(
                new String[]{"Plat", "Jenis", "Jam", "Tanggal"}, 0
        );
        model.addRow(new Object[]{"B 6561 PMS", "Motor", "20:48:07", "02-06-2024"});

        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // ===== BOTTOM PANEL =====
        JPanel bottomPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 8, 5, 8);
        gbc.anchor = GridBagConstraints.WEST;

        // Bayar
        gbc.gridx = 0; gbc.gridy = 0;
        bottomPanel.add(new JLabel("Bayar"), gbc);

        gbc.gridx = 1;
        txtBayar = new JTextField("0", 8);
        bottomPanel.add(txtBayar, gbc);

        gbc.gridx = 2;
        JButton btnOk = new JButton("OK");
        bottomPanel.add(btnOk, gbc);

        // Tanggal (REAL TIME)
        gbc.gridx = 3;
        bottomPanel.add(new JLabel("Tanggal"), gbc);

        gbc.gridx = 4;
        lblTanggal = new JLabel();
        bottomPanel.add(lblTanggal, gbc);

        // Jam (REAL TIME)
        gbc.gridx = 5;
        bottomPanel.add(new JLabel("Jam"), gbc);

        gbc.gridx = 6;
        lblJam = new JLabel();
        bottomPanel.add(lblJam, gbc);

        add(bottomPanel, BorderLayout.SOUTH);

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
