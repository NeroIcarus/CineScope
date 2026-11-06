
package cinema;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Locale;

public class DetailFilm extends JFrame {

    // WARNA DASAR CINESCOPE
    private final Color BG = new Color(16, 11, 29);
    private final Color PANEL = new Color(29, 21, 48);
    private final Color CARD = new Color(39, 29, 62);
    private final Color LAVENDER = new Color(199, 177, 235);
    private final Color WHITE = new Color(245, 240, 252);
    private final Color MUTED = new Color(164, 150, 190);
    private final Color GOLD = new Color(255, 199, 92);

    // WARNA AKSEN FILM
    private Color accent;
    private Color accentLight;
    private Color accentDark;

    private boolean favorite = false;
    private boolean expanded = false;

    private int personalRating = 0;

    private String judulFilm;

    private JLabel favoriteButton;
    private JLabel ratingMessage;
    private JLabel synopsisLabel;
    private JLabel[] stars = new JLabel[5];

    private final String synopsisText =
            "Nikmati perjalanan cerita yang membawa penonton "
            + "ke dalam dunia karakter, konflik, dan berbagai "
            + "kejadian yang membentuk keseluruhan cerita film ini. "
            + "Temukan detail cerita dan pengalaman sinematiknya "
            + "dengan menonton film secara langsung.";

    // FONT
    private Font display(int size) {
        return new Font("Space Grotesk", Font.BOLD, size);
    }

    private Font body(int size) {
        return new Font("DM Sans", Font.PLAIN, size);
    }

    private Font bold(int size) {
        return new Font("DM Sans", Font.BOLD, size);
    }

    // MENENTUKAN WARNA BERDASARKAN FILM
    private Color[] getMovieTheme(String judul, String genre) {

        String movie = judul.toLowerCase(Locale.ROOT);
        String category = genre.toLowerCase(Locale.ROOT);

        // SCI-FI DAN LUAR ANGKASA
        if (movie.contains("interstellar")
                || movie.contains("avatar")
                || movie.contains("dune")
                || movie.contains("gravity")
                || movie.contains("arrival")
                || movie.contains("blade runner")) {

            return new Color[]{
                new Color(65, 145, 205),
                new Color(145, 205, 240),
                new Color(18, 48, 85)
            };
        }

        // BATMAN DAN CRIME
        if (movie.contains("batman")
                || movie.contains("joker")
                || movie.contains("se7en")
                || movie.contains("dark knight")) {

            return new Color[]{
                new Color(175, 55, 65),
                new Color(240, 125, 125),
                new Color(65, 20, 30)
            };
        }

        // HORROR DAN THRILLER
        if (category.contains("horror")
                || category.contains("thriller")
                || movie.contains("conjuring")
                || movie.contains("insidious")) {

            return new Color[]{
                new Color(160, 55, 95),
                new Color(225, 115, 150),
                new Color(65, 18, 42)
            };
        }

        // ROMANCE DAN MUSICAL
        if (category.contains("romance")
                || category.contains("musical")
                || movie.contains("la la land")
                || movie.contains("barbie")) {

            return new Color[]{
                new Color(195, 105, 155),
                new Color(245, 165, 180),
                new Color(90, 35, 85)
            };
        }

        // ACTION
        if (category.contains("action")) {

            return new Color[]{
                new Color(205, 115, 55),
                new Color(245, 175, 100),
                new Color(85, 40, 20)
            };
        }

        // DEFAULT
        return new Color[]{
            new Color(143, 105, 205),
            new Color(199, 177, 235),
            new Color(55, 32, 90)
        };
    }

    // CONSTRUCTOR
    public DetailFilm(String judul, String genre,
                      int tahun, double rating) {

        this.judulFilm = judul;

        // AMBIL RATING PENGGUNA SEBELUMNYA
        this.personalRating =
                RatingManager.getRatingPengguna(judulFilm);

        // PILIH WARNA FILM
        Color[] theme = getMovieTheme(judul, genre);

        accent = theme[0];
        accentLight = theme[1];
        accentDark = theme[2];

        setTitle("CineScope | " + judul);
        setSize(1050, 760);
        setMinimumSize(new Dimension(850, 650));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(BG);

        // HEADER
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(BG);
        header.setBorder(new EmptyBorder(18, 35, 18, 35));

        JLabel logo = new JLabel("CINESCOPE");
        logo.setFont(display(22));
        logo.setForeground(LAVENDER);

        JLabel subtitle = new JLabel("  YOUR MOVIE UNIVERSE");
        subtitle.setFont(bold(9));
        subtitle.setForeground(MUTED);

        JPanel brand = new JPanel(
                new FlowLayout(FlowLayout.LEFT, 0, 0));

        brand.setOpaque(false);
        brand.add(logo);
        brand.add(subtitle);

        JButton back = new JButton("←  Back");
        styleButton(back, false);
        back.addActionListener(e -> dispose());

        header.add(brand, BorderLayout.WEST);
        header.add(back, BorderLayout.EAST);

        root.add(header, BorderLayout.NORTH);

        // CONTENT
        JPanel content = new JPanel();

        content.setLayout(new BoxLayout(
                content, BoxLayout.Y_AXIS));

        content.setBackground(BG);
        content.setBorder(
                new EmptyBorder(10, 35, 35, 35));

        // HERO SECTION
        JPanel hero = new JPanel(
                new BorderLayout(28, 0));

        hero.setBackground(PANEL);
        hero.setBorder(
                new EmptyBorder(25, 25, 25, 25));

        hero.setMaximumSize(new Dimension(1200, 430));
        hero.setAlignmentX(Component.LEFT_ALIGNMENT);

        PosterPanel poster = new PosterPanel(judul);
        poster.setPreferredSize(new Dimension(260, 365));

        hero.add(poster, BorderLayout.WEST);

        JPanel details = new JPanel();

        details.setLayout(new BoxLayout(
                details, BoxLayout.Y_AXIS));

        details.setBackground(PANEL);

        JLabel category = new JLabel("✦  MOVIE SPOTLIGHT");
        category.setFont(bold(11));
        category.setForeground(accent);

        JLabel title = new JLabel(
                "<html><div style='width:450px;'>"
                + escapeHtml(judul) + "</div></html>");

        title.setFont(display(37));
        title.setForeground(WHITE);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        // GOOGLE RATING
        JLabel meta = new JLabel(
                tahun + "   •   " + genre + "   •   "
                + String.format("%.1f", rating) + " / 10");

        meta.setFont(bold(13));
        meta.setForeground(accentLight);

        JLabel description = new JLabel(
                "A story worth adding to your collection.");

        description.setFont(body(13));
        description.setForeground(MUTED);

        details.add(Box.createVerticalStrut(15));
        details.add(category);
        details.add(Box.createVerticalStrut(22));
        details.add(title);
        details.add(Box.createVerticalStrut(17));
        details.add(meta);
        details.add(Box.createVerticalStrut(15));
        details.add(description);
        details.add(Box.createVerticalStrut(27));

        // ACTION BUTTONS
        JPanel actions = new JPanel(
                new FlowLayout(FlowLayout.LEFT, 10, 0));

        actions.setOpaque(false);
        actions.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton addButton = new JButton("+  Add to Watchlist");
        styleButton(addButton, true);

        addButton.addActionListener(e -> {

            int before = Watchlist.getJumlahFilm();

            Watchlist.tambahFilm(
                    judul, genre, tahun, rating);

            if (Watchlist.getJumlahFilm() > before) {

                addButton.setText("✓  Added to Watchlist");

                addButton.setBackground(
                        new Color(76, 145, 111));

                JOptionPane.showMessageDialog(
                        this,
                        judul + " berhasil ditambahkan!",
                        "CineScope",
                        JOptionPane.INFORMATION_MESSAGE);

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Film sudah ada di Watchlist.",
                        "CineScope",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        favoriteButton = new JLabel("♡  Favorite");
        favoriteButton.setFont(bold(12));
        favoriteButton.setForeground(accentLight);
        favoriteButton.setOpaque(true);
        favoriteButton.setBackground(CARD);

        favoriteButton.setBorder(
                new EmptyBorder(13, 17, 13, 17));

        favoriteButton.setCursor(
                Cursor.getPredefinedCursor(
                        Cursor.HAND_CURSOR));

        favoriteButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                favorite = !favorite;

                if (favorite) {

                    favoriteButton.setText("♥  Favorited");

                    favoriteButton.setForeground(
                            new Color(255, 130, 160));

                } else {

                    favoriteButton.setText("♡  Favorite");
                    favoriteButton.setForeground(accentLight);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                favoriteButton.setBackground(
                        new Color(58, 42, 85));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                favoriteButton.setBackground(CARD);
            }
        });

        actions.add(addButton);
        actions.add(favoriteButton);

        details.add(actions);
        details.add(Box.createVerticalGlue());

        hero.add(details, BorderLayout.CENTER);

        content.add(hero);
        content.add(Box.createVerticalStrut(25));

        // SYNOPSIS
        JPanel synopsisCard = createCard();

        synopsisCard.setLayout(new BoxLayout(
                synopsisCard, BoxLayout.Y_AXIS));

        JLabel synopsisTitle = sectionTitle("The Story");

        synopsisLabel = new JLabel();
        synopsisLabel.setFont(body(13));
        synopsisLabel.setForeground(MUTED);

        synopsisLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        updateSynopsis();

        JButton readMore = new JButton("Read More ↓");
        styleTextButton(readMore);

        readMore.addActionListener(e -> {

            expanded = !expanded;

            updateSynopsis();

            readMore.setText(
                    expanded ? "Show Less ↑" : "Read More ↓");
        });

        synopsisCard.add(synopsisTitle);
        synopsisCard.add(Box.createVerticalStrut(12));
        synopsisCard.add(synopsisLabel);
        synopsisCard.add(Box.createVerticalStrut(8));
        synopsisCard.add(readMore);

        content.add(synopsisCard);
        content.add(Box.createVerticalStrut(20));

        // PERSONAL RATING
        JPanel ratingCard = createCard();

        ratingCard.setLayout(new BoxLayout(
                ratingCard, BoxLayout.Y_AXIS));

        JLabel ratingTitle = sectionTitle("Your Take");

        JLabel ratingSubtitle = new JLabel(
                "How much did you enjoy this movie?");

        ratingSubtitle.setFont(body(12));
        ratingSubtitle.setForeground(MUTED);

        JPanel starPanel = new JPanel(
                new FlowLayout(FlowLayout.LEFT, 5, 5));

        starPanel.setOpaque(false);
        starPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        ratingMessage = new JLabel();
        ratingMessage.setFont(bold(12));
        ratingMessage.setForeground(accentLight);

        for (int i = 0; i < 5; i++) {

            final int selected = i + 1;

            stars[i] = new JLabel("★");

            stars[i].setFont(
                    new Font("SansSerif", Font.PLAIN, 30));

            stars[i].setForeground(MUTED);

            stars[i].setCursor(
                    Cursor.getPredefinedCursor(
                            Cursor.HAND_CURSOR));

            stars[i].addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    simpanRating(selected);
                }

                @Override
                public void mouseEntered(MouseEvent e) {

                    for (int j = 0; j < 5; j++) {

                        stars[j].setForeground(
                                j < selected ? GOLD : MUTED);
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    updateStars();
                }
            });

            starPanel.add(stars[i]);
        }

        ratingCard.add(ratingTitle);
        ratingCard.add(Box.createVerticalStrut(8));
        ratingCard.add(ratingSubtitle);
        ratingCard.add(Box.createVerticalStrut(10));
        ratingCard.add(starPanel);
        ratingCard.add(ratingMessage);

        // TAMPILKAN RATING AWAL
        updateStars();
        updateRatingTampilan();

        content.add(ratingCard);
        content.add(Box.createVerticalStrut(20));

        // RECOMMENDATIONS
        JLabel recommendationTitle =
                sectionTitle("You Might Also Like");

        content.add(recommendationTitle);
        content.add(Box.createVerticalStrut(12));

        JPanel recommendations = new JPanel(
                new GridLayout(1, 3, 15, 0));

        recommendations.setBackground(BG);

        recommendations.setMaximumSize(
                new Dimension(1200, 160));

        recommendations.setAlignmentX(
                Component.LEFT_ALIGNMENT);

        addRecommendation(
                recommendations,
                "Inception", "Sci-Fi", 2010, 8.8);

        addRecommendation(
                recommendations,
                "Interstellar", "Sci-Fi", 2014, 8.7);

        addRecommendation(
                recommendations,
                "The Dark Knight", "Action", 2008, 9.0);

        content.add(recommendations);

        // SCROLL
        JScrollPane scroll = new JScrollPane(content);

        scroll.setBorder(null);
        scroll.setBackground(BG);

        scroll.getViewport().setBackground(BG);

        scroll.getVerticalScrollBar().setUnitIncrement(18);

        root.add(scroll, BorderLayout.CENTER);

        setContentPane(root);
    }

    // SIMPAN RATING
    private void simpanRating(int nilai) {

        RatingManager.beriRating(judulFilm, nilai);

        personalRating = nilai;

        updateStars();
        updateRatingTampilan();
    }

    // UPDATE TAMPILAN RATING
    private void updateRatingTampilan() {

        int ratingPribadi =
                RatingManager.getRatingPengguna(judulFilm);

        String ratingKomunitas =
                RatingManager.formatRatingKomunitas(judulFilm);

        ratingMessage.setText(
                "Your Rating: "
                + (ratingPribadi == 0
                        ? "Belum memberi rating"
                        : ratingPribadi + " / 5")
                + "     |     CineScope: "
                + ratingKomunitas
        );
    }

    // UPDATE STARS
    private void updateStars() {

        for (int i = 0; i < 5; i++) {

            stars[i].setForeground(
                    i < personalRating ? GOLD : MUTED);
        }
    }

    // UPDATE SYNOPSIS
    private void updateSynopsis() {

        String text;

        if (expanded) {

            text = synopsisText;

        } else {

            text = synopsisText.substring(
                    0, Math.min(105, synopsisText.length()))
                    + "...";
        }

        synopsisLabel.setText(
                "<html><div style='width:850px;'>"
                + text + "</div></html>");
    }

    // CARD STYLE
    private JPanel createCard() {

        JPanel panel = new JPanel();

        panel.setBackground(PANEL);

        panel.setBorder(
                new EmptyBorder(20, 22, 20, 22));

        panel.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.setMaximumSize(new Dimension(1200, 220));

        return panel;
    }

    // SECTION TITLE
    private JLabel sectionTitle(String text) {

        JLabel label = new JLabel(text);

        label.setFont(display(21));
        label.setForeground(WHITE);

        label.setAlignmentX(Component.LEFT_ALIGNMENT);

        return label;
    }

    // BUTTON STYLE
    private void styleButton(
            JButton button, boolean primary) {

        button.setFont(bold(12));
        button.setForeground(WHITE);

        button.setBackground(primary ? accent : CARD);

        button.setFocusPainted(false);
        button.setBorderPainted(false);

        button.setBorder(
                new EmptyBorder(13, 18, 13, 18));

        button.setCursor(
                Cursor.getPredefinedCursor(
                        Cursor.HAND_CURSOR));

        button.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {

                if (primary) {

                    button.setBackground(accentLight);

                } else {

                    button.setBackground(
                            new Color(58, 42, 85));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {

                if (primary) {

                    if (!button.getText().startsWith("✓")) {
                        button.setBackground(accent);
                    }

                } else {

                    button.setBackground(CARD);
                }
            }
        });
    }

    // TEXT BUTTON
    private void styleTextButton(JButton button) {

        button.setFont(bold(11));
        button.setForeground(accentLight);
        button.setBackground(PANEL);

        button.setBorderPainted(false);
        button.setFocusPainted(false);

        button.setAlignmentX(Component.LEFT_ALIGNMENT);

        button.setCursor(
                Cursor.getPredefinedCursor(
                        Cursor.HAND_CURSOR));
    }

    // RECOMMENDATION CARD
    private void addRecommendation(
            JPanel parent,
            String title,
            String genre,
            int year,
            double rating) {

        JPanel card = new JPanel(new BorderLayout());

        card.setBackground(CARD);

        card.setBorder(
                new EmptyBorder(15, 15, 15, 15));

        card.setCursor(
                Cursor.getPredefinedCursor(
                        Cursor.HAND_CURSOR));

        JLabel name = new JLabel(title);

        name.setFont(bold(14));
        name.setForeground(WHITE);

        JLabel information = new JLabel(
                genre + "  •  " + year + "  •  "
                + String.format("%.1f", rating) + " ★");

        information.setFont(body(11));
        information.setForeground(MUTED);

        JLabel open = new JLabel("View details →");

        open.setFont(bold(11));
        open.setForeground(accentLight);

        JPanel text = new JPanel();

        text.setLayout(new BoxLayout(
                text, BoxLayout.Y_AXIS));

        text.setOpaque(false);

        text.add(name);
        text.add(Box.createVerticalStrut(8));
        text.add(information);
        text.add(Box.createVerticalStrut(12));
        text.add(open);

        card.add(text, BorderLayout.CENTER);

        card.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                new DetailFilm(
                        title, genre, year, rating)
                        .setVisible(true);
            }

            @Override
            public void mouseEntered(MouseEvent e) {

                card.setBackground(
                        new Color(62, 44, 91));
            }

            @Override
            public void mouseExited(MouseEvent e) {

                card.setBackground(CARD);
            }
        });

        parent.add(card);
    }

    // HTML ESCAPE
    private String escapeHtml(String text) {

        return text.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;");
    }

    // CUSTOM POSTER
    private class PosterPanel extends JPanel {

        private final String title;

        PosterPanel(String title) {

            this.title = title;

            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {

            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth();
            int h = getHeight();

            // GRADIENT
            g2.setPaint(new GradientPaint(
                    0, 0, accent,
                    w, h, accentDark));

            g2.fillRoundRect(0, 0, w, h, 22, 22);

            // DEKORASI CAHAYA
            g2.setColor(new Color(
                    accentLight.getRed(),
                    accentLight.getGreen(),
                    accentLight.getBlue(),
                    35));

            g2.fillOval(50, 60, 170, 170);

            g2.setColor(new Color(255, 255, 255, 30));

            g2.setStroke(new BasicStroke(1.5f));

            for (int i = 0; i < 7; i++) {

                g2.drawOval(
                        45 + i * 8,
                        75 + i * 8,
                        140 + i * 12,
                        140 + i * 12);
            }

            // LOGO POSTER
            g2.setColor(accentLight);
            g2.setFont(bold(10));

            g2.drawString(
                    "CINESCOPE COLLECTION", 20, 30);

            // JUDUL FILM
            g2.setColor(WHITE);
            g2.setFont(display(24));

            FontMetrics fm = g2.getFontMetrics();

            String[] words = title.split(" ");

            String line = "";

            int y = h - 80;

            for (String word : words) {

                String test = line.isEmpty()
                        ? word : line + " " + word;

                if (fm.stringWidth(test) > w - 35
                        && !line.isEmpty()) {

                    g2.drawString(line, 20, y);

                    y += 30;

                    line = word;

                } else {

                    line = test;
                }
            }

            if (!line.isEmpty()) {

                g2.drawString(line, 20, y);
            }

            g2.dispose();
        }
    }
}
