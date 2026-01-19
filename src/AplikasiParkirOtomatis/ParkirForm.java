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
    JTextField txtSearch;

    JLabel lblTanggal;
    JLabel lblJam;
    JLabel lblCounter;

    int selectedId = -1;

    public ParkirForm() {
        setTitle("Data Parkir");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // bar menu
        JMenuBar menuBar = new JMenuBar();
        JMenu menuParkir = new JMenu("Parkir");

        JMenuItem menuInput = new JMenuItem("Input Kendaraan");
        JMenuItem menuData = new JMenuItem("Data Parkir");
        JMenuItem menuHistori = new JMenuItem("Histori Parkir");
        menuParkir.add(menuInput);
        menuParkir.add(menuData);
        menuParkir.add(menuHistori);
        menuBar.add(menuParkir);
        setJMenuBar(menuBar);

        menuInput.addActionListener(e -> {
            if (jumlahKendaraanParkir() >= 100) {
                JOptionPane.showMessageDialog(
                        this,
                        "Kapasitas parkir sudah penuh!\nMaksimal 100 kendaraan.",
                        "Peringatan",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }
            new InputParkirForm();
            dispose();
        });
        menuHistori.addActionListener(e -> {
            new HistoriParkirForm();
            dispose();
        });
        // panel atas
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("Search"));

        txtSearch = new JTextField(20);
        topPanel.add(txtSearch);

        lblCounter = new JLabel("Jumlah Kendaraan: 0");
        lblCounter.setFont(new Font("Arial", Font.BOLD, 12));
        lblCounter.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        topPanel.add(lblCounter);

        add(topPanel, BorderLayout.NORTH);

        // ===== TABLE (ID TIDAK BISA DIEDIT) =====
        String[] kolom = {
                "ID",
                "Plat Nomor",
                "Jenis Kendaraan",
                "Waktu Masuk"
        };

        model = new DefaultTableModel(null, kolom) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // kolom read-only
            }
        };

        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(table), BorderLayout.CENTER);

        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);
        table.getColumnModel().getColumn(0).setWidth(0);
        table.getColumnModel().getColumn(0).setPreferredWidth(0);

        JScrollPane scrollPane = new JScrollPane(table);

        scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 50, 50, 50));

        add(scrollPane, BorderLayout.CENTER);

        // pengambilan id saat di 'klik'
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                selectedId = Integer.parseInt(
                        table.getValueAt(table.getSelectedRow(), 0).toString()
                );
            }
        });

        // search
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent e) {
                loadDataKendaraan(txtSearch.getText());
            }
        });

        loadDataKendaraan("");

        // panel bawah
        JPanel bottomPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 8, 5, 8);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0;
        bottomPanel.add(new JLabel("Bayar"), gbc);

        gbc.gridx = 1;
        txtBayar = new JTextField("", 8);
        bottomPanel.add(txtBayar, gbc);

        gbc.gridx = 2;
        JButton btnOk = new JButton("OK");
        bottomPanel.add(btnOk, gbc);
        btnOk.addActionListener(e -> updateWaktuKeluar());

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

    // update saat kendaraan keluar
    private void updateWaktuKeluar() {
        if (selectedId == -1) {
            JOptionPane.showMessageDialog(this, "Pilih kendaraan terlebih dahulu!");
            return;
        }

        String inputBayar = txtBayar.getText().trim();

        if (inputBayar.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Kolom 'Bayar' tidak boleh kosong!", "Error Input", JOptionPane.ERROR_MESSAGE);
            txtBayar.requestFocus();
            return;
        }

        try {
            double nominal = Double.parseDouble(inputBayar);

            if (nominal < 0) {
                JOptionPane.showMessageDialog(this, "Nominal bayar tidak boleh minus!", "Error Input", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Connection conn = DBConnection.getConnection();
            String waktuKeluar = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

            String sql = "UPDATE kendaraan SET waktu_keluar = ? WHERE id_kendaraan = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, waktuKeluar);
            ps.setInt(2, selectedId);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Kendaraan berhasil keluar");
            selectedId = -1;
            txtBayar.setText("");
            loadDataKendaraan("");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Masukkan angka yang valid pada kolom bayar!", "Format Salah", JOptionPane.ERROR_MESSAGE);
            txtBayar.selectAll();
            txtBayar.requestFocus();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal update data ke database: " + e.getMessage());
        }
    }

    // menghitung kendaraan yang di parkirkan
    private int jumlahKendaraanParkir() {
        int total = 0;
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = conn.prepareStatement(
                    "SELECT COUNT(*) FROM kendaraan WHERE waktu_keluar IS NULL"
            ).executeQuery();
            if (rs.next()) total = rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }

    // load data
    private void loadDataKendaraan(String keyword) {
        try {
            Connection conn = DBConnection.getConnection();

            String sql =
                    "SELECT id_kendaraan, plat_nomor, jenis_kendaraan, waktu_masuk, waktu_keluar " +
                            "FROM kendaraan WHERE waktu_keluar IS NULL AND plat_nomor LIKE ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();

            model.setRowCount(0);
            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id_kendaraan"),
                        rs.getString("plat_nomor"),
                        rs.getString("jenis_kendaraan"),
                        rs.getString("waktu_masuk")
                });
            }

            lblCounter.setText("Jumlah Kendaraan: " + jumlahKendaraanParkir());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // jam(real-time)
    private void startDateTime() {
        Timer timer = new Timer(1000, e -> {
            Date now = new Date();
            lblTanggal.setText(new SimpleDateFormat("dd-MM-yyyy").format(now));
            lblJam.setText(new SimpleDateFormat("HH:mm:ss").format(now));
        });
        timer.start();
    }
}
