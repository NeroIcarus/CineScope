
package cinema;

import javax.swing.*;
import java.awt.*;

public class DetailFilm extends JFrame {

    public DetailFilm(String judul, String genre,
                      int tahun, double rating) {

        setTitle("CineScope - Detail Film");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(
                30, 40, 30, 40
        ));

        JLabel title = new JLabel(judul);
        title.setFont(new Font("Arial", Font.BOLD, 28));

        JLabel genreLabel = new JLabel("Genre: " + genre);
        JLabel yearLabel = new JLabel("Tahun Rilis: " + tahun);
        JLabel ratingLabel = new JLabel("Rating: " + rating);

        JTextArea synopsis = new JTextArea(
            "Informasi dan sinopsis film " + judul +
            " akan ditampilkan di bagian ini."
        );

        synopsis.setLineWrap(true);
        synopsis.setWrapStyleWord(true);
        synopsis.setEditable(false);
        synopsis.setOpaque(false);

        JButton watchlistButton =
                new JButton("Tambah ke Watchlist");

        watchlistButton.addActionListener(e -> {
            int jumlahSebelum = Watchlist.getJumlahFilm();

            Watchlist.tambahFilm(judul, genre, tahun, rating);

            if (Watchlist.getJumlahFilm() > jumlahSebelum) {
                JOptionPane.showMessageDialog(
                 this, judul + " berhasil ditambahkan!"
            );
        } else {
         JOptionPane.showMessageDialog(
            this, "Film sudah ada di Watchlist."
        );
    }
});

        panel.add(title);
        panel.add(Box.createVerticalStrut(20));
        panel.add(genreLabel);
        panel.add(yearLabel);
        panel.add(ratingLabel);
        panel.add(Box.createVerticalStrut(20));
        panel.add(new JLabel("Sinopsis:"));
        panel.add(synopsis);
        panel.add(Box.createVerticalStrut(25));
        panel.add(watchlistButton);

        add(panel);
    }
}
