package com.unpri.charactermanager.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterType;

public class MainWindow extends JFrame {

    private static final String VERSION = "v0.5";   // satu sumber kebenaran versi

    private ArrayList<Character> characterList;     // data dari Main.java (Model)

    public MainWindow(ArrayList<Character> characterList) {
        super("Character Manager " + VERSION);
        this.characterList = characterList;

        initComponents();                           // 1. susun semua area

        pack();                                     // 2. ukuran awal = preferred size isi
        setMinimumSize(new Dimension(560, 460));    // 3. batas terkecil yang masih rapi
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);                // 4. pusatkan SETELAH pack()
    }

    private void initComponents() {
        getContentPane().setBackground(AppTheme.BG_MAIN);
        setLayout(new BorderLayout());

        add(createTopPanel(),    BorderLayout.NORTH);   // header + statistik
        add(createListArea(),    BorderLayout.CENTER);  // daftar berscroll
        add(createLegendPanel(), BorderLayout.EAST);    // legenda role
        add(createStatusPanel(), BorderLayout.SOUTH);   // bar status
    }

    /** NORTH: pembungkus header (atas) dan statistik (bawah). */
    private JPanel createTopPanel() {
        JPanel top = new JPanel(new BorderLayout());
        top.setBackground(AppTheme.BG_MAIN);
        top.add(createHeaderPanel(), BorderLayout.NORTH);
        top.add(createStatsPanel(),  BorderLayout.CENTER);
        return top;
    }

    /** Header: judul di kiri, jumlah karakter di kanan. */
    private JPanel createHeaderPanel() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(AppTheme.BG_MAIN);
        header.setBorder(BorderFactory.createEmptyBorder(16, 16, 8, 16));

        JLabel title = new JLabel("Character Manager");
        title.setFont(AppTheme.FONT_TITLE);
        title.setForeground(AppTheme.TEXT_MAIN);

        JLabel count = new JLabel(characterList.size() + " karakter");
        count.setFont(AppTheme.FONT_BODY);
        count.setForeground(AppTheme.GOLD);

        header.add(title, BorderLayout.WEST);
        header.add(count, BorderLayout.EAST);
        return header;
    }

    /** Panel statistik 2x2 (GridLayout: semua sel sama besar). */
    private JPanel createStatsPanel() {
        int total = characterList.size();
        int sum = 0;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (Character c : characterList) {
            sum += c.getLevel();
            max = Math.max(max, c.getLevel());
            min = Math.min(min, c.getLevel());
        }
        double avg = (total == 0) ? 0 : (double) sum / total;

        JPanel stats = new JPanel(new GridLayout(2, 2, 8, 8));
        stats.setBackground(AppTheme.BG_MAIN);
        stats.setBorder(BorderFactory.createEmptyBorder(0, 16, 8, 16));

        stats.add(createStatCell("Total karakter",  String.valueOf(total)));
        stats.add(createStatCell("Rata-rata level", String.format("%.1f", avg)));
        stats.add(createStatCell("Level tertinggi", total == 0 ? "-" : String.valueOf(max)));
        stats.add(createStatCell("Level terendah",  total == 0 ? "-" : String.valueOf(min)));
        return stats;
    }

    /** Satu sel statistik: judul kecil di atas, nilai di bawah. */
    private JPanel createStatCell(String title, String value) {
        JPanel cell = new JPanel(new BorderLayout());
        cell.setBackground(AppTheme.BG_STATUS);
        cell.setBorder(BorderFactory.createEmptyBorder(6, 10, 6, 10));

        JLabel t = new JLabel(title);
        t.setFont(AppTheme.FONT_MONO);
        t.setForeground(AppTheme.TEXT_MUTED);

        JLabel v = new JLabel(value);
        v.setFont(AppTheme.FONT_BODY);
        v.setForeground(AppTheme.GOLD);

        cell.add(t, BorderLayout.NORTH);
        cell.add(v, BorderLayout.CENTER);
        return cell;
    }

    /** CENTER: kartu karakter di dalam JScrollPane. */
    private JScrollPane createListArea() {
        JPanel cards = new JPanel(new GridLayout(0, 1, 0, 8));
        cards.setBackground(AppTheme.BG_MAIN);
        for (Character c : characterList) {
            cards.add(createCharacterLabel(c));      // kartu ditambah ke PANEL
        }

        // holder: cards di NORTH agar kartu mempertahankan tinggi alaminya
        JPanel holder = new JPanel(new BorderLayout());
        holder.setBackground(AppTheme.BG_MAIN);
        holder.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 8));
        holder.add(cards, BorderLayout.NORTH);

        JScrollPane scroll = new JScrollPane(holder); // bungkus HOLDER
        scroll.setBorder(null);
        scroll.getViewport().setBackground(AppTheme.BG_MAIN);
        scroll.setPreferredSize(new Dimension(460, 280));
        return scroll;
    }

    /** Satu kartu karakter. */
    private JLabel createCharacterLabel(Character c) {
        String info = c.getName() + " | " + c.getType()
                    + " | Lv." + c.getLevel()
                    + " | HP:" + c.getHitPoints()
                    + " | ATK:" + c.getAttackPower();

        JLabel lbl = new JLabel(info);
        lbl.setFont(AppTheme.FONT_BODY);
        lbl.setForeground(AppTheme.TEXT_LIGHT);
        lbl.setBackground(AppTheme.colorFor(c.getType()));
        lbl.setOpaque(true);
        lbl.setBorder(AppTheme.CARD_BORDER);
        lbl.setToolTipText(AppTheme.describe(c.getType()));
        lbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return lbl;
    }

    /** EAST: legenda warna per role, BoxLayout sumbu Y. */
    private JPanel createLegendPanel() {
        JPanel legend = new JPanel();
        legend.setLayout(new BoxLayout(legend, BoxLayout.Y_AXIS)); // target = panel ini sendiri
        legend.setBackground(AppTheme.BG_MAIN);
        legend.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 16));

        JLabel head = new JLabel("Legenda Role");
        head.setFont(AppTheme.FONT_BODY.deriveFont(Font.BOLD));
        head.setForeground(AppTheme.TEXT_MAIN);
        head.setAlignmentX(Component.LEFT_ALIGNMENT);
        legend.add(head);
        legend.add(Box.createVerticalStrut(8));

        for (CharacterType type : CharacterType.values()) {
            JLabel chip = new JLabel(type.name());
            chip.setOpaque(true);
            chip.setBackground(AppTheme.colorFor(type));
            chip.setForeground(AppTheme.TEXT_LIGHT);
            chip.setFont(AppTheme.FONT_MONO);
            chip.setBorder(AppTheme.CARD_BORDER);
            chip.setToolTipText(AppTheme.describe(type));
            chip.setAlignmentX(Component.LEFT_ALIGNMENT);
            legend.add(chip);
            legend.add(Box.createVerticalStrut(6));
        }
        legend.add(Box.createVerticalGlue());        // dorong semua ke atas
        return legend;
    }

    /** SOUTH: bar status, teks di kiri dan versi di kanan (Mini-Challenge). */
    private JPanel createStatusPanel() {
        JPanel status = new JPanel(new BorderLayout());
        status.setBackground(AppTheme.BG_STATUS);
        status.setBorder(BorderFactory.createEmptyBorder(6, 16, 6, 16));

        JLabel left = new JLabel("Total: " + characterList.size() + " karakter  |  Siap");
        left.setFont(AppTheme.FONT_MONO);
        left.setForeground(AppTheme.TEXT_MUTED);

        JLabel right = new JLabel(VERSION);
        right.setFont(AppTheme.FONT_MONO);
        right.setForeground(AppTheme.TEXT_MUTED);

        status.add(left,  BorderLayout.WEST);
        status.add(right, BorderLayout.EAST);
        return status;
    }
}