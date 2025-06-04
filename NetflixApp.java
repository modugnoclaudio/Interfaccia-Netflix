import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Classe che rappresenta un film con titolo, descrizione e miniatura.
 */
class Film {
    private final String title;
    private final String description;
    private final ImageIcon thumbnail;

    public Film(String title, String description, ImageIcon thumbnail) {
        this.title = title;
        this.description = description;
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public ImageIcon getThumbnail() {
        return thumbnail;
    }

    @Override
    public String toString() {
        return title;
    }
}

/**
 * Interfaccia grafica principale che simula Netflix.
 */
class NetflixGUI extends JFrame {
    private final DefaultListModel<Film> filmModel = new DefaultListModel<>();
    private final JList<Film> filmList = new JList<>(filmModel);

    public NetflixGUI() {
        super("Netflix");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        initializeFilms();
        initializeUI();
    }

    /**
     * Inizializza la lista dei film da mostrare.
     */
    private void initializeFilms() {
        filmModel.addElement(new Film(
                "Inception",
                "A mind-bending thriller about dreams within dreams.",
                createPlaceholderIcon()));
        filmModel.addElement(new Film(
                "Stranger Things",
                "A group of kids uncover supernatural mysteries in their town.",
                createPlaceholderIcon()));
        filmModel.addElement(new Film(
                "The Witcher",
                "A monster hunter struggles to find his place in a turbulent world.",
                createPlaceholderIcon()));
        filmModel.addElement(new Film(
                "Interstellar",
                "Explorers travel through a wormhole in space to save humanity.",
                createPlaceholderIcon()));
        filmModel.addElement(new Film(
                "Dark",
                "A time-travel mystery that spans multiple generations.",
                createPlaceholderIcon()));
    }

    /**
     * Costruisce l'interfaccia grafica.
     */
    private void initializeUI() {
        filmList.setCellRenderer(new FilmCellRenderer());
        JScrollPane scrollPane = new JScrollPane(filmList);

        JButton watchButton = new JButton("Guarda", UIManager.getIcon("OptionPane.informationIcon"));
        JButton detailsButton = new JButton("Dettagli", UIManager.getIcon("OptionPane.questionIcon"));

        watchButton.addActionListener(e -> watchSelectedFilm());
        detailsButton.addActionListener(e -> showFilmDetails());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(watchButton);
        buttonPanel.add(detailsButton);

        getContentPane().setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    /** Mostra un messaggio che simula la riproduzione del film selezionato. */
    private void watchSelectedFilm() {
        Film film = filmList.getSelectedValue();
        if (film != null) {
            JOptionPane.showMessageDialog(this,
                    "Avvio film: " + film.getTitle(),
                    "Riproduzione",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Seleziona un film dall'elenco.",
                    "Nessun film selezionato",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    /** Mostra la descrizione del film selezionato. */
    private void showFilmDetails() {
        Film film = filmList.getSelectedValue();
        if (film != null) {
            JOptionPane.showMessageDialog(this,
                    film.getDescription(),
                    film.getTitle(),
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Seleziona un film dall'elenco.",
                    "Nessun film selezionato",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Crea un'icona segnaposto per i film.
     */
    private ImageIcon createPlaceholderIcon() {
        int width = 80;
        int height = 120;
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = img.createGraphics();
        g2.setColor(Color.DARK_GRAY);
        g2.fillRect(0, 0, width, height);
        g2.setColor(Color.WHITE);
        g2.drawRect(5, 5, width - 10, height - 10);
        g2.drawString("Poster", 15, height / 2);
        g2.dispose();
        return new ImageIcon(img);
    }

    /**
     * Renderer personalizzato per mostrare icona e titolo nella lista.
     */
    private static class FilmCellRenderer extends JLabel implements ListCellRenderer<Film> {
        public FilmCellRenderer() {
            setOpaque(true);
            setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends Film> list,
                                                      Film value,
                                                      int index,
                                                      boolean isSelected,
                                                      boolean cellHasFocus) {
            setText(value.getTitle());
            setIcon(value.getThumbnail());
            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }
            return this;
        }
    }
}

/**
 * Classe principale con il metodo main.
 */
public class NetflixApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new NetflixGUI().setVisible(true);
        });
    }
}
