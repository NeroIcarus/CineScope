
package cinema;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Watchlist extends JFrame {

    private static DefaultTableModel model =
        new DefaultTableModel(
            new Object[]{
                "Judul", "Genre", "Tahun", "Rating", "Status"
            }, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4;
            }
        };

    public Watchlist() {
        setTitle("CineScope - Watchlist Saya");
        setSize(800, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel title = new JLabel("Watchlist Saya");
        title.setFont(new Font("Arial", Font.BOLD, 24));

        JTable table = new JTable(model);

        String[] statuses = {
            "Want to Watch",
            "Currently Watching",
            "Watched",
            "Dropped"
        };

        JComboBox<String> statusCombo =
            new JComboBox<>(statuses);

        table.getColumnModel()
            .getColumn(4)
            .setCellEditor(
                new DefaultCellEditor(statusCombo)
            );

        JButton deleteButton =
            new JButton("Hapus Film");

        deleteButton.addActionListener(e -> {
            int row = table.getSelectedRow();

            if (row != -1) {
                model.removeRow(
                    table.convertRowIndexToModel(row)
                );
            } else {
                JOptionPane.showMessageDialog(
                    this, "Pilih film yang ingin dihapus."
                );
            }
        });

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(title, BorderLayout.WEST);
        topPanel.add(deleteButton, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public static void tambahFilm(
        String judul, String genre,
        int tahun, double rating
    ) {
        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 0)
                    .toString().equalsIgnoreCase(judul)) {
                return;
            }
        }

        model.addRow(new Object[]{
            judul,
            genre,
            tahun,
            rating,
            "Want to Watch"
        });
    }
    public static int getJumlahFilm() {
    return model.getRowCount();
}
}
