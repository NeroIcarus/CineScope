package cinema.main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Cinema extends JFrame {

    // BRAND COLORS
    private final Color BG = new Color(16, 11, 29);
    private final Color SURFACE = new Color(27, 19, 47);
    private final Color CARD = new Color(35, 25, 59);
    private final Color CARD_LIGHT = new Color(48, 35, 77);
    private final Color LAVENDER = new Color(199, 177, 235);
    private final Color WHITE = new Color(245, 240, 252);
    private final Color MUTED = new Color(164, 150, 190);

    // DYNAMIC FEATURED COLORS
    private Color featuredAccent;
    private Color featuredLight;
    private Color featuredDark;

    private JLabel featuredTitle;
    private JLabel featuredGenre;
    private JLabel featuredYear;
    private JLabel featuredIndex;
    private JLabel featuredCategory;

    private JTextArea featuredDescription;
    private JPanel artwork;
    private JButton exploreButton;

    private int currentIndex = 0;

    private final String[][] movies = {
        {
            "Interstellar",
            "SCI-FI  /  DRAMA",
            "2014",
            "A journey beyond the stars, exploring love, "
            + "time, and humanity's future.",
            "8.7"
        },
        {
            "Inception",
            "SCI-FI  /  THRILLER",
            "2010",
            "A skilled thief enters the world of dreams "
            + "to complete an impossible mission.",
            "8.8"
        },
        {
            "The Grand Budapest Hotel",
            "COMEDY  /  DRAMA",
            "2014",
            "An eccentric concierge and his young "
            + "assistant become involved in an unusual adventure.",
            "8.1"
        }
    };

    public Cinema() {

        setTitle("CineScope");
        setSize(1250, 820);
        setMinimumSize(new Dimension(1050, 700));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(BG);

        root.add(createHeader(), BorderLayout.NORTH);
        root.add(createContent(), BorderLayout.CENTER);

        setContentPane(root);
    }

    // ================= FONTS =================

    private Font display(int size) {
        return new Font("Space Grotesk", Font.BOLD, size);
    }

    private Font body(int size) {
        return new Font("DM Sans", Font.PLAIN, size);
    }

    private Font bold(int size) {
        return new Font("DM Sans", Font.BOLD, size);
    }

    // ================= MOVIE COLOR THEMES =================

    private Color[] getMovieTheme(String title, String genre) {

        String movie = title.toLowerCase();
        String category = genre.toLowerCase();

        // SCI-FI
        if (movie.contains("interstellar")
                || movie.contains("inception")
                || movie.contains("avatar")
                || movie.contains("dune")
                || movie.contains("gravity")
                || movie.contains("arrival")
                || category.contains("sci-fi")) {

            return new Color[]{
                new Color(65, 145, 205),
                new Color(145, 205, 240),
                new Color(18, 48, 85)
            };
        }

        // CRIME
        if (movie.contains("batman")
                || movie.contains("joker")
                || movie.contains("se7en")
                || category.contains("crime")) {

            return new Color[]{
                new Color(175, 55, 65),
                new Color(240, 125, 125),
                new Color(65, 20, 30)
            };
        }

        // HORROR AND THRILLER
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

        // ROMANCE
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

        // COMEDY AND DRAMA
        if (category.contains("comedy")
                || movie.contains("grand budapest")
                || movie.contains("parasite")) {

            return new Color[]{
                new Color(205, 135, 65),
                new Color(250, 195, 115),
                new Color(85, 48, 24)
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

        // DEFAULT CINESCOPE
        return new Color[]{
            new Color(143, 105, 205),
            new Color(199, 177, 235),
            new Color(55, 32, 90)
        };
    }

    // ================= HEADER =================

    private JPanel createHeader() {

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(BG);
        header.setBorder(
                new EmptyBorder(24, 55, 20, 55));

        JPanel brand = new JPanel();
        brand.setBackground(BG);
        brand.setLayout(
                new BoxLayout(brand, BoxLayout.Y_AXIS));

        JLabel logo = new JLabel("CINESCOPE");
        logo.setFont(display(27));
        logo.setForeground(LAVENDER);

        JLabel tagline = new JLabel(
                "A PERSONAL WORLD OF CINEMA");
        tagline.setFont(bold(9));
        tagline.setForeground(MUTED);

        brand.add(logo);
        brand.add(Box.createVerticalStrut(3));
        brand.add(tagline);

        JPanel navigation = new JPanel(
                new FlowLayout(FlowLayout.RIGHT, 27, 10));
        navigation.setBackground(BG);

        navigation.add(navButton(
                "DISCOVER", true, () -> {}));

        navigation.add(navButton(
                "CATALOG", false,
                () -> new KatalogFilm().setVisible(true)));

        navigation.add(navButton(
                "WATCHLIST", false,
                () -> new Watchlist().setVisible(true)));

        header.add(brand, BorderLayout.WEST);
        header.add(navigation, BorderLayout.EAST);

        return header;
    }

    private JButton navButton(
            String text, boolean active, Runnable action) {

        JButton button = new JButton(text);
        button.setFont(bold(11));
        button.setForeground(active ? LAVENDER : MUTED);
        button.setBackground(BG);
        button.setBorder(
                new EmptyBorder(8, 2, 8, 2));
        button.setFocusPainted(false);
        button.setCursor(
                new Cursor(Cursor.HAND_CURSOR));

        button.addActionListener(e -> action.run());

        return button;
    }

    // ================= MAIN CONTENT =================

    private JScrollPane createContent() {

        JPanel content = new JPanel();
        content.setBackground(BG);
        content.setBorder(
                new EmptyBorder(20, 55, 40, 55));

        content.setLayout(
                new BoxLayout(content, BoxLayout.Y_AXIS));

        content.add(createWelcome());
        content.add(Box.createVerticalStrut(28));

        content.add(createFeatured());
        content.add(Box.createVerticalStrut(30));

        content.add(createQuickAccess());
        content.add(Box.createVerticalStrut(32));

        content.add(createRecommendations());
        content.add(Box.createVerticalStrut(30));

        content.add(createFooter());

        JScrollPane scroll = new JScrollPane(content);
        scroll.setBorder(null);
        scroll.setBackground(BG);
        scroll.getViewport().setBackground(BG);
        scroll.getVerticalScrollBar().setUnitIncrement(16);

        return scroll;
    }

    // ================= WELCOME =================

    private JPanel createWelcome() {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(BG);
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel left = new JPanel();
        left.setBackground(BG);
        left.setLayout(
                new BoxLayout(left, BoxLayout.Y_AXIS));

        JLabel eyebrow = new JLabel(
                "YOUR PERSONAL FILM COLLECTION");
        eyebrow.setForeground(LAVENDER);
        eyebrow.setFont(bold(10));

        JLabel title = new JLabel("Discover your");
        title.setForeground(WHITE);
        title.setFont(display(43));

        JLabel title2 = new JLabel(
                "next favorite story.");
        title2.setForeground(LAVENDER);
        title2.setFont(
                new Font("DM Sans", Font.ITALIC, 43));

        JLabel description = new JLabel(
                "Explore cinema, collect memories, "
                + "and find something worth watching.");
        description.setForeground(MUTED);
        description.setFont(body(13));

        left.add(eyebrow);
        left.add(Box.createVerticalStrut(12));
        left.add(title);
        left.add(title2);
        left.add(Box.createVerticalStrut(12));
        left.add(description);

        JPanel right = new JPanel();
        right.setBackground(BG);
        right.setLayout(
                new BoxLayout(right, BoxLayout.Y_AXIS));

        JLabel issue = new JLabel("COLLECTION");
        issue.setForeground(MUTED);
        issue.setFont(body(10));

        JLabel number = new JLabel("NO. 001");
        number.setForeground(LAVENDER);
        number.setFont(display(25));

        right.add(issue);
        right.add(Box.createVerticalStrut(5));
        right.add(number);

        panel.add(left, BorderLayout.WEST);
        panel.add(right, BorderLayout.EAST);

        return panel;
    }

    // ================= FEATURED FILM =================

    private JPanel createFeatured() {

        JPanel section = new JPanel(
                new BorderLayout(0, 14));
        section.setBackground(BG);
        section.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel heading = new JPanel(new BorderLayout());
        heading.setBackground(BG);

        JLabel label = new JLabel("EDITOR'S PICK");
        label.setForeground(WHITE);
        label.setFont(bold(15));

        JLabel sublabel = new JLabel(
                "A FILM WORTH YOUR TIME");
        sublabel.setForeground(LAVENDER);
        sublabel.setFont(body(9));

        heading.add(label, BorderLayout.WEST);
        heading.add(sublabel, BorderLayout.EAST);

        JPanel hero = new JPanel(new GridLayout(1, 2));
        hero.setBackground(SURFACE);
        hero.setPreferredSize(new Dimension(1000, 285));

        artwork = createArtwork();
        hero.add(artwork);

        JPanel details = new JPanel();
        details.setBackground(SURFACE);
        details.setBorder(
                new EmptyBorder(27, 32, 25, 32));

        details.setLayout(
                new BoxLayout(details, BoxLayout.Y_AXIS));

        featuredCategory = new JLabel(
                "FEATURED COLLECTION");
        featuredCategory.setFont(bold(10));

        featuredTitle = new JLabel();
        featuredTitle.setForeground(WHITE);
        featuredTitle.setFont(display(29));

        featuredGenre = new JLabel();
        featuredGenre.setFont(bold(10));

        featuredYear = new JLabel();
        featuredYear.setForeground(MUTED);
        featuredYear.setFont(body(12));

        featuredDescription = new JTextArea();
        featuredDescription.setForeground(
                new Color(205, 195, 222));
        featuredDescription.setBackground(SURFACE);
        featuredDescription.setFont(body(13));
        featuredDescription.setLineWrap(true);
        featuredDescription.setWrapStyleWord(true);
        featuredDescription.setEditable(false);
        featuredDescription.setFocusable(false);
        featuredDescription.setOpaque(false);

        exploreButton = new JButton("EXPLORE FILM  →");
        exploreButton.setForeground(WHITE);
        exploreButton.setFont(bold(11));
        exploreButton.setBorder(
                new EmptyBorder(12, 19, 12, 19));
        exploreButton.setFocusPainted(false);
        exploreButton.setCursor(
                new Cursor(Cursor.HAND_CURSOR));
        exploreButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        exploreButton.addActionListener(
                e -> openFeatured());

        JPanel controls = new JPanel(
                new FlowLayout(FlowLayout.LEFT, 8, 0));
        controls.setBackground(SURFACE);

        JButton previous = sliderButton("‹");
        JButton next = sliderButton("›");

        previous.addActionListener(e -> previousMovie());
        next.addActionListener(e -> nextMovie());

        featuredIndex = new JLabel("01 / 03");
        featuredIndex.setForeground(MUTED);
        featuredIndex.setFont(body(10));

        controls.add(previous);
        controls.add(next);
        controls.add(featuredIndex);

        details.add(featuredCategory);
        details.add(Box.createVerticalStrut(14));
        details.add(featuredTitle);
        details.add(Box.createVerticalStrut(8));
        details.add(featuredGenre);
        details.add(Box.createVerticalStrut(5));
        details.add(featuredYear);
        details.add(Box.createVerticalStrut(18));
        details.add(featuredDescription);
        details.add(Box.createVerticalStrut(18));
        details.add(exploreButton);
        details.add(Box.createVerticalGlue());
        details.add(controls);

        hero.add(details);

        updateFeatured();

        section.add(heading, BorderLayout.NORTH);
        section.add(hero, BorderLayout.CENTER);

        return section;
    }

    // ================= FEATURED ARTWORK =================

    private JPanel createArtwork() {

        return new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);

                Graphics2D g2 = (Graphics2D) g.create();

                g2.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

                GradientPaint gradient = new GradientPaint(
                        0, 0, featuredAccent,
                        getWidth(), getHeight(), featuredDark);

                g2.setPaint(gradient);
                g2.fillRect(
                        0, 0, getWidth(), getHeight());

                // DECORATIVE CIRCLES
                g2.setColor(new Color(
                        featuredLight.getRed(),
                        featuredLight.getGreen(),
                        featuredLight.getBlue(),
                        35));

                g2.fillOval(
                        getWidth() / 2 - 100,
                        getHeight() / 2 - 100,
                        200, 200);

                g2.setColor(
                        new Color(255, 255, 255, 30));

                g2.fillOval(
                        getWidth() / 2 - 70,
                        getHeight() / 2 - 70,
                        140, 140);

                // MOVIE NUMBER
                g2.setColor(featuredLight);
                g2.setFont(display(76));

                String number = String.format(
                        "%02d", currentIndex + 1);

                FontMetrics fm = g2.getFontMetrics();

                g2.drawString(
                        number,
                        (getWidth()
                        - fm.stringWidth(number)) / 2,
                        getHeight() / 2 + 25);

                // LABEL
                g2.setColor(
                        new Color(235, 220, 255, 180));
                g2.setFont(body(9));

                g2.drawString(
                        "CINESCOPE  /  FEATURED",
                        22, getHeight() - 22);

                g2.dispose();
            }
        };
    }

    // ================= SLIDER CONTROLS =================

    private JButton sliderButton(String text) {

        JButton button = new JButton(text);
        button.setFont(display(19));
        button.setForeground(LAVENDER);
        button.setBackground(CARD_LIGHT);
        button.setBorder(
                new EmptyBorder(2, 12, 2, 12));
        button.setFocusPainted(false);
        button.setCursor(
                new Cursor(Cursor.HAND_CURSOR));

        return button;
    }

    // ================= UPDATE FEATURED =================

    private void updateFeatured() {

        String[] movie = movies[currentIndex];

        Color[] theme = getMovieTheme(
                movie[0], movie[1]);

        featuredAccent = theme[0];
        featuredLight = theme[1];
        featuredDark = theme[2];

        featuredTitle.setText(movie[0]);
        featuredGenre.setText(movie[1]);

        featuredYear.setText(
                "RELEASE YEAR  /  " + movie[2]
                + "     •     ★ " + movie[4]);

        featuredDescription.setText(movie[3]);

        featuredCategory.setForeground(featuredAccent);
        featuredGenre.setForeground(featuredLight);

        exploreButton.setBackground(featuredAccent);

        featuredIndex.setText(
                String.format(
                        "%02d / %02d",
                        currentIndex + 1,
                        movies.length));

        artwork.repaint();
    }

    private void nextMovie() {

        currentIndex =
                (currentIndex + 1) % movies.length;

        updateFeatured();
    }

    private void previousMovie() {

        currentIndex =
                (currentIndex - 1 + movies.length)
                % movies.length;

        updateFeatured();
    }

    // ================= OPEN FEATURED =================

    private void openFeatured() {

        String[] movie = movies[currentIndex];

        String genre = movie[1]
                .replace("  /  ", " / ");

        int year = Integer.parseInt(movie[2]);
        double rating = Double.parseDouble(movie[4]);

        new DetailFilm(
                movie[0],
                genre,
                year,
                rating
        ).setVisible(true);
    }

    // ================= QUICK ACCESS =================

    private JPanel createQuickAccess() {

        JPanel section = new JPanel(
                new BorderLayout(0, 13));
        section.setBackground(BG);
        section.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel heading = new JLabel("YOUR CINEMA SPACE");
        heading.setForeground(WHITE);
        heading.setFont(bold(15));

        JPanel cards = new JPanel(
                new GridLayout(1, 2, 15, 0));
        cards.setBackground(BG);

        cards.add(createAccessCard(
                "01",
                "Movie Collection",
                "Browse the complete film catalog.",
                "EXPLORE CATALOG  →",
                () -> new KatalogFilm().setVisible(true)
        ));

        cards.add(createAccessCard(
                "02",
                "My Watchlist",
                "Keep track of films you want to watch.",
                "OPEN WATCHLIST  →",
                () -> new Watchlist().setVisible(true)
        ));

        section.add(heading, BorderLayout.NORTH);
        section.add(cards, BorderLayout.CENTER);

        return section;
    }

    private JPanel createAccessCard(
            String number,
            String title,
            String description,
            String actionText,
            Runnable action) {

        JPanel card = new JPanel();
        card.setBackground(CARD);
        card.setBorder(
                new EmptyBorder(20, 23, 20, 23));

        card.setLayout(
                new BoxLayout(card, BoxLayout.Y_AXIS));

        JLabel code = new JLabel(
                "COLLECTION  /  " + number);
        code.setForeground(LAVENDER);
        code.setFont(body(9));

        JLabel name = new JLabel(title);
        name.setForeground(WHITE);
        name.setFont(display(22));

        JLabel info = new JLabel(description);
        info.setForeground(MUTED);
        info.setFont(body(12));

        JButton button = new JButton(actionText);
        button.setForeground(LAVENDER);
        button.setBackground(CARD);
        button.setFont(bold(9));
        button.setBorder(
                new EmptyBorder(8, 0, 0, 0));
        button.setFocusPainted(false);
        button.setCursor(
                new Cursor(Cursor.HAND_CURSOR));
        button.setAlignmentX(Component.LEFT_ALIGNMENT);

        button.addActionListener(e -> action.run());

        card.add(code);
        card.add(Box.createVerticalStrut(10));
        card.add(name);
        card.add(Box.createVerticalStrut(7));
        card.add(info);
        card.add(Box.createVerticalStrut(12));
        card.add(button);

        return card;
    }

    // ================= RECOMMENDATIONS =================

    private JPanel createRecommendations() {

        JPanel section = new JPanel(
                new BorderLayout(0, 15));
        section.setBackground(BG);
        section.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel heading = new JPanel(new BorderLayout());
        heading.setBackground(BG);

        JLabel title = new JLabel("CURATED FOR YOU");
        title.setForeground(WHITE);
        title.setFont(bold(15));

        JLabel subtitle = new JLabel(
                "A FEW STORIES TO EXPLORE");
        subtitle.setForeground(LAVENDER);
        subtitle.setFont(body(9));

        heading.add(title, BorderLayout.WEST);
        heading.add(subtitle, BorderLayout.EAST);

        JPanel cards = new JPanel(
                new GridLayout(1, 3, 15, 0));
        cards.setBackground(BG);

        cards.add(createRecommendation(
                "01",
                "The Dark Knight",
                "ACTION  /  CRIME",
                "2008",
                "9.0"
        ));

        cards.add(createRecommendation(
                "02",
                "Parasite",
                "THRILLER  /  DRAMA",
                "2019",
                "8.5"
        ));

        cards.add(createRecommendation(
                "03",
                "The Conjuring",
                "HORROR",
                "2013",
                "7.5"
        ));

        section.add(heading, BorderLayout.NORTH);
        section.add(cards, BorderLayout.CENTER);

        return section;
    }

    private JPanel createRecommendation(
            String number,
            String title,
            String genre,
            String year,
            String rating) {

        Color[] theme = getMovieTheme(title, genre);

        JPanel card = new JPanel(
                new BorderLayout(0, 12));
        card.setBackground(CARD);
        card.setBorder(
                new EmptyBorder(14, 14, 16, 14));

        JPanel poster = new JPanel(
                new GridBagLayout());

        poster.setPreferredSize(
                new Dimension(100, 115));

        poster.setBackground(theme[2]);

        JLabel posterNumber = new JLabel(number);
        posterNumber.setForeground(theme[1]);
        posterNumber.setFont(display(45));

        poster.add(posterNumber);

        JPanel info = new JPanel();
        info.setBackground(CARD);
        info.setLayout(
                new BoxLayout(info, BoxLayout.Y_AXIS));

        JLabel name = new JLabel(title);
        name.setForeground(WHITE);
        name.setFont(display(17));

        JLabel category = new JLabel(genre);
        category.setForeground(theme[1]);
        category.setFont(body(9));

        JLabel metadata = new JLabel(
                year + "   ·   ★ " + rating);
        metadata.setForeground(MUTED);
        metadata.setFont(body(11));

        info.add(name);
        info.add(Box.createVerticalStrut(6));
        info.add(category);
        info.add(Box.createVerticalStrut(8));
        info.add(metadata);

        card.add(poster, BorderLayout.NORTH);
        card.add(info, BorderLayout.CENTER);

        return card;
    }

    // ================= FOOTER =================

    private JPanel createFooter() {

        JPanel footer = new JPanel(
                new BorderLayout());

        footer.setBackground(BG);

        footer.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(
                                1, 0, 0, 0, CARD_LIGHT),
                        new EmptyBorder(17, 0, 0, 0)
                ));

        JLabel left = new JLabel(
                "CINESCOPE  ©  2026");
        left.setForeground(MUTED);
        left.setFont(body(9));

        JLabel right = new JLabel(
                "MADE FOR THE LOVE OF CINEMA");
        right.setForeground(LAVENDER);
        right.setFont(body(9));

        footer.add(left, BorderLayout.WEST);
        footer.add(right, BorderLayout.EAST);

        return footer;
    }

    // ================= MAIN =================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            try {
                UIManager.setLookAndFeel(
                        UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            new Cinema().setVisible(true);
        });
    }
}
