package cinema;

import javax.swing.*;
import java.awt.*;

public class Cinema {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Cinema");
            frame.setSize(800, 500);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setBorder(BorderFactory.createEmptyBorder(
                    60, 80, 60, 80
            ));
            
            JLabel title = new JLabel("Cinema");
            title.setFont(new Font("Ärial", Font.BOLD, 36));
            title.setAlignmentX(Component.CENTER_ALIGNMENT);
            
           JLabel subtitle = new JLabel(
                    "Movie Libary & Recomendation System"
           );
           subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
           
           JButton catalog = new JButton("Katalog Film");
           JButton watchlist = new JButton("Watchlist Saya");
           JButton exit = new JButton("Keluar");
           
           catalog.setAlignmentX(Component.CENTER_ALIGNMENT);
           watchlist.setAlignmentX(Component.CENTER_ALIGNMENT);
           exit.setAlignmentX(Component.CENTER_ALIGNMENT);
           
           catalog.addActionListener(e ->
                new KatalogFilm().setVisible(true)
            );
           
           watchlist.addActionListener(e ->
                new Watchlist().setVisible(true)
            );
           
           exit.addActionListener(e -> frame.dispose());
           
           panel.add(title);
           panel.add(Box.createVerticalStrut(10));
           panel.add(subtitle);
           panel.add(Box.createVerticalStrut(50));
           panel.add(catalog);
           panel.add(Box.createVerticalStrut(15));
           panel.add(watchlist);
           panel.add(Box.createVerticalStrut(15));
           panel.add(exit);
     
           frame.add(panel);
           frame.setVisible(true);  
       });
    }
}
