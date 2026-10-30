
package cinema;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class KatalogFilm extends JFrame {

    private JTextField searchField;
    private JTable table;
    private DefaultTableModel model;

    public KatalogFilm() {
        setTitle("CineScope - Katalog Film");
        setSize(800, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel topPanel = new JPanel(new BorderLayout(10, 10));

        JLabel title = new JLabel("Katalog Film");
        title.setFont(new Font("Arial", Font.BOLD, 24));

        searchField = new JTextField();
        JButton searchButton = new JButton("Cari");

        JPanel searchPanel = new JPanel(new BorderLayout(5, 5));
        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);

        topPanel.add(title, BorderLayout.NORTH);
        topPanel.add(searchPanel, BorderLayout.SOUTH);

        String[] columns = {
            "Judul", "Genre", "Tahun", "Rating"
        };

        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);

        model.addRow(new Object[]{
            "Interstellar", "Sci-Fi", 2014, 8.7
        });
        model.addRow(new Object[]{
            "The Dark Knight", "Action", 2008, 9.0
        });
        model.addRow(new Object[]{
            "Inception", "Sci-Fi", 2010, 8.8
        });
        model.addRow(new Object[]{
            "Parasite", "Thriller", 2019, 8.5
        });
        model.addRow(new Object[]{
            "The Conjuring", "Horror", 2013, 7.5
        });

        searchButton.addActionListener(e -> searchMovie());

        searchField.addActionListener(e -> searchMovie());
        
        JButton detailButton = new JButton("Lihat Detail");

            detailButton.addActionListener(e -> {
                int row = table.getSelectedRow();

                if (row != -1) {
                    String judul = model.getValueAt(row, 0).toString();
                    String genre = model.getValueAt(row, 1).toString();
                    int tahun = Integer.parseInt(
                        model.getValueAt(row, 2).toString()
                );
                double rating = Double.parseDouble(
                    model.getValueAt(row, 3).toString()
                );

                new DetailFilm(
                    judul, genre, tahun, rating
                ).setVisible(true);

            } else {
                JOptionPane.showMessageDialog(
                    this, "Pilih film terlebih dahulu."
            );
        }
    });
        
        
table.addMouseListener(new java.awt.event.MouseAdapter() {
    public void mouseClicked(java.awt.event.MouseEvent e) {

        if (e.getClickCount() == 2) {

            int row = table.getSelectedRow();

            if (row != -1) {
                String judul = model.getValueAt(row, 0).toString();
                String genre = model.getValueAt(row, 1).toString();
                int tahun = Integer.parseInt(
                    model.getValueAt(row, 2).toString()
                );
                double rating = Double.parseDouble(
                    model.getValueAt(row, 3).toString()
                );

                new DetailFilm(
                    judul, genre, tahun, rating
                ).setVisible(true);
            }
        }
    }
});


        JPanel bottomPanel = new JPanel();
        bottomPanel.add(detailButton);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void searchMovie() {
        String keyword = searchField.getText()
                .trim().toLowerCase();

        for (int i = 0; i < model.getRowCount(); i++) {
            String title = model.getValueAt(i, 0)
                    .toString().toLowerCase();
            String genre = model.getValueAt(i, 1)
                    .toString().toLowerCase();

            if (title.contains(keyword)
                    || genre.contains(keyword)) {
                table.setRowSelectionInterval(i, i);
                table.scrollRectToVisible(
                    table.getCellRect(i, 0, true)
                );
                return;
            }
        }

        JOptionPane.showMessageDialog(
            this, "Film tidak ditemukan."
        );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
            new KatalogFilm().setVisible(true)
        );
    }
}
