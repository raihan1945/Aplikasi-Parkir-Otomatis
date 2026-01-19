package AplikasiParkirOtomatis;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HistoriParkirForm extends JFrame {

    JTable table;
    DefaultTableModel model;
    JTextField txtSearch;
    JLabel lblCounter;

    public HistoriParkirForm() {
        setTitle("Histori Parkir");
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
            new InputParkirForm();
            dispose();
        });
        menuData.addActionListener(e -> {
            new ParkirForm();
            dispose();
        });
        // panel atas
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("Search"));

        txtSearch = new JTextField(20);
        topPanel.add(txtSearch);

        lblCounter = new JLabel("Total Kendaraan: 0");
        lblCounter.setFont(new Font("Arial", Font.BOLD, 12));
        lblCounter.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        topPanel.add(lblCounter);

        add(topPanel, BorderLayout.NORTH);

        // tabel
        String[] kolom = {
                "ID",
                "Plat Nomor",
                "Jenis Kendaraan",
                "Waktu Masuk",
                "Waktu Keluar"
        };

        model = new DefaultTableModel(null, kolom) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
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

        // search
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent e) {
                loadHistori(txtSearch.getText());
            }
        });

        loadHistori("");
        setVisible(true);
    }

    // load histori parkir
    private void loadHistori(String keyword) {
        try {
            Connection conn = DBConnection.getConnection();

            String sql =
                    "SELECT id_kendaraan, plat_nomor, jenis_kendaraan, waktu_masuk, waktu_keluar " +
                            "FROM kendaraan " +
                            "WHERE waktu_keluar IS NOT NULL " +
                            "AND waktu_keluar <> '0000-00-00 00:00:00' " +
                            "AND plat_nomor LIKE ? " +
                            "ORDER BY waktu_keluar DESC";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();

            model.setRowCount(0);
            int total = 0;

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id_kendaraan"),
                        rs.getString("plat_nomor"),
                        rs.getString("jenis_kendaraan"),
                        rs.getString("waktu_masuk"),
                        rs.getString("waktu_keluar")
                });
                total++;
            }

            lblCounter.setText("Total Kendaraan: " + total);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal memuat histori parkir");
        }
    }
}
