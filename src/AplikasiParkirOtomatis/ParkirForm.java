package AplikasiParkirOtomatis;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ParkirForm extends JFrame {

    JTable table;
    DefaultTableModel model;
    JTextField txtBayar;

    JLabel lblTanggal;
    JLabel lblJam;
    JLabel lblCounter; // COUNTER KENDARAAN

    public ParkirForm() {
        setTitle("Data Parkir");
        setSize(900, 500);
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

        // ===== TOP PANEL (SEARCH + COUNTER) =====
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("Search"));
        topPanel.add(new JTextField(20));

        lblCounter = new JLabel("Jumlah Kendaraan: 0");
        lblCounter.setFont(new Font("Arial", Font.BOLD, 12));
        lblCounter.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));

        topPanel.add(lblCounter);
        add(topPanel, BorderLayout.NORTH);

        // ===== TABLE (DATABASE) =====
        String[] kolom = {
                "Plat Nomor",
                "Jenis Kendaraan",
                "Waktu Masuk",
                "Waktu Keluar"
        };

        model = new DefaultTableModel(null, kolom);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // LOAD DATA
        loadDataKendaraan();

        // ===== BOTTOM PANEL =====
        JPanel bottomPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 8, 5, 8);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0;
        bottomPanel.add(new JLabel("Bayar"), gbc);

        gbc.gridx = 1;
        txtBayar = new JTextField("0", 8);
        bottomPanel.add(txtBayar, gbc);

        gbc.gridx = 2;
        JButton btnOk = new JButton("OK");
        bottomPanel.add(btnOk, gbc);

        gbc.gridx = 3;
        bottomPanel.add(new JLabel("Tanggal"), gbc);

        gbc.gridx = 4;
        lblTanggal = new JLabel();
        bottomPanel.add(lblTanggal, gbc);

        gbc.gridx = 5;
        bottomPanel.add(new JLabel("Jam"), gbc);

        gbc.gridx = 6;
        lblJam = new JLabel();
        bottomPanel.add(lblJam, gbc);

        add(bottomPanel, BorderLayout.SOUTH);

        startDateTime();
        setVisible(true);
    }

    // ===== LOAD DATA + UPDATE COUNTER =====
    private void loadDataKendaraan() {
        try {
            Connection conn = DBConnection.getConnection();

            // DATA TABEL
            String sqlData =
                    "SELECT plat_nomor, jenis_kendaraan, waktu_masuk, waktu_keluar " +
                            "FROM kendaraan WHERE waktu_keluar IS NULL";
            PreparedStatement ps = conn.prepareStatement(sqlData);
            ResultSet rs = ps.executeQuery();

            model.setRowCount(0);

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("plat_nomor"),
                        rs.getString("jenis_kendaraan"),
                        rs.getString("waktu_masuk"),
                        rs.getString("waktu_keluar")
                });
            }

            // COUNTER
            String sqlCount =
                    "SELECT COUNT(*) AS total FROM kendaraan WHERE waktu_keluar IS NULL";
            PreparedStatement psCount = conn.prepareStatement(sqlCount);
            ResultSet rsCount = psCount.executeQuery();

            if (rsCount.next()) {
                lblCounter.setText("Jumlah Kendaraan: " + rsCount.getInt("total"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal memuat data parkir");
        }
    }

    // ===== REAL TIME DATE & TIME =====
    private void startDateTime() {
        Timer timer = new Timer(1000, e -> {
            Date now = new Date();
            lblTanggal.setText(new SimpleDateFormat("dd-MM-yyyy").format(now));
            lblJam.setText(new SimpleDateFormat("HH:mm:ss").format(now));
        });
        timer.start();
    }
}
