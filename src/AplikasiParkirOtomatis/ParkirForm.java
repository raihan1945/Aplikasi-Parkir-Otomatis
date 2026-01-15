package AplikasiParkirOtomatis;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ParkirForm extends JFrame {

    JTable table;
    JTextField txtBayar;

    public ParkirForm() {
        setTitle("Data Parkir");
        setSize(650, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Search
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("Search"));
        topPanel.add(new JTextField(20));
        add(topPanel, BorderLayout.NORTH);

        // Table
        DefaultTableModel model = new DefaultTableModel(
                new String[]{"Plat", "Jenis", "Jam", "Tanggal"}, 0
        );
        model.addRow(new Object[]{"B 6561 PMS", "Motor", "20:48:07", "02-06-2024"});

        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Bottom
        JPanel bottomPanel = new JPanel(new GridLayout(2, 4, 5, 5));

        bottomPanel.add(new JLabel("Bayar"));
        txtBayar = new JTextField("0");
        bottomPanel.add(txtBayar);

        JButton btnOk = new JButton("OK");
        bottomPanel.add(btnOk);
        bottomPanel.add(new JLabel(""));

        bottomPanel.add(new JLabel("Tanggal"));
        bottomPanel.add(new JLabel("02-06-2024"));

        bottomPanel.add(new JLabel("Jam"));
        bottomPanel.add(new JLabel("20:48:48"));

        btnOk.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Pembayaran Berhasil")
        );

        add(bottomPanel, BorderLayout.SOUTH);
        setVisible(true);
    }
}
