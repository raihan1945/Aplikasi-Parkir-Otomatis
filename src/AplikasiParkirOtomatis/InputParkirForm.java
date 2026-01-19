package AplikasiParkirOtomatis;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InputParkirForm extends JFrame {

    JLabel lblTanggal, lblJam;
    JTextField txtPlat;
    JComboBox<String> cmbJenis;

    public InputParkirForm() {
        setTitle("Input Kendaraan");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // bar
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

        menuData.addActionListener(e -> {
            new ParkirForm();
            dispose();
        });

        menuHistori.addActionListener(e -> {
            new HistoriParkirForm();
            dispose();
        });

        // form
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // plat nomor
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Plat Nomor"), gbc);

        gbc.gridx = 1;
        txtPlat = new JTextField(20);
        panel.add(txtPlat, gbc);

        // jenis kendaraan
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Jenis Kendaraan"), gbc);

        gbc.gridx = 1;
        cmbJenis = new JComboBox<>(new String[]{"Motor", "Mobil"});
        panel.add(cmbJenis, gbc);

        // tanggal masuk
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Tanggal Masuk"), gbc);

        gbc.gridx = 1;
        lblTanggal = new JLabel();
        panel.add(lblTanggal, gbc);

        // jam Masuk
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Jam Masuk"), gbc);

        gbc.gridx = 1;
        lblJam = new JLabel();
        panel.add(lblJam, gbc);

        // submit
        gbc.gridx = 1; gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        JButton btnSubmit = new JButton("Submit");
        panel.add(btnSubmit, gbc);

        add(panel, BorderLayout.CENTER);

        startDateTime();

        // button submit
        btnSubmit.addActionListener(e -> simpanData());

        setVisible(true);
    }

    // real time clock
    private void startDateTime() {
        Timer timer = new Timer(1000, e -> {
            Date now = new Date();
            lblTanggal.setText(new SimpleDateFormat("yyyy-MM-dd").format(now));
            lblJam.setText(new SimpleDateFormat("HH:mm:ss").format(now));
        });
        timer.start();
    }

    // insert ke database
    private void simpanData() {
        String plat = txtPlat.getText().trim();
        String jenis = cmbJenis.getSelectedItem().toString();

        if (plat.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Plat nomor wajib diisi!");
            return;
        }
        try {
            Connection conn = DBConnection.getConnection();

            String sql =
                    "INSERT INTO kendaraan (plat_nomor, jenis_kendaraan, waktu_masuk) " +
                            "VALUES (?, ?, NOW())";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, plat);
            ps.setString(2, jenis);
            System.out.println(plat);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Data kendaraan berhasil disimpan");

            txtPlat.setText("");
            cmbJenis.setSelectedIndex(0);

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal menyimpan data!");
//             error catcher
//             JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}
