
package com.unpri.charactermanager.view;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import com.unpri.charactermanager.model.Character;

public class MainWindow extends JFrame {

    private final JPanel mainPanel;
    private final ArrayList<Character> characterList;

    public MainWindow(ArrayList<Character> characterList) {
        super("Character Manager v0.4");

        this.characterList = characterList;
        this.mainPanel = new JPanel();

        initComponents();

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        setContentPane(scrollPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pack();
        setMinimumSize(new Dimension(500, 300));
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        mainPanel.setLayout(
            new BoxLayout(mainPanel, BoxLayout.Y_AXIS)
        );

        mainPanel.setBackground(AppTheme.BG_MAIN);
        mainPanel.setBorder(
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );

        // Judul aplikasi
        JLabel titleLabel = new JLabel(
            "Daftar Karakter (" + characterList.size() + ")",
            SwingConstants.CENTER
        );

        titleLabel.setFont(AppTheme.FONT_TITLE);
        titleLabel.setForeground(AppTheme.TEXT_MAIN);
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);
        titleLabel.setMaximumSize(
            new Dimension(Integer.MAX_VALUE, 40)
        );

        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(20));

        // Menampilkan seluruh karakter
        for (Character c : characterList) {
            JLabel characterLabel = createCharacterLabel(c);

            characterLabel.setAlignmentX(CENTER_ALIGNMENT);

            mainPanel.add(characterLabel);
            mainPanel.add(Box.createVerticalStrut(10));
        }
    }

    private JLabel createCharacterLabel(Character c) {

        String info = c.getName()
            + "  |  " + c.getType()
            + "  |  Lv." + c.getLevel()
            + "  |  HP: " + c.getHitPoints()
            + "  |  ATK: " + c.getAttackPower();

        // Teks berada tepat di tengah kartu
        JLabel lbl = new JLabel(info, SwingConstants.CENTER);

        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        lbl.setVerticalAlignment(SwingConstants.CENTER);

        // Warna dan font kartu
        lbl.setOpaque(true);
        lbl.setBackground(AppTheme.colorFor(c.getType()));
        lbl.setForeground(AppTheme.TEXT_LIGHT);
        lbl.setFont(AppTheme.FONT_BODY);

        lbl.setBorder(AppTheme.CARD_BORDER);

        // Level 5 ke atas: teks emas dan tebal
        if (c.getLevel() >= AppTheme.HIGH_LEVEL_THRESHOLD) {
            lbl.setForeground(AppTheme.GOLD);
            lbl.setFont(
                AppTheme.FONT_BODY.deriveFont(Font.BOLD)
            );
        }

        // Tooltip informasi karakter
        lbl.setToolTipText(
            AppTheme.describe(c.getType())
                + " | HP: " + c.getHitPoints()
                + " | ATK: " + c.getAttackPower()
        );

        // Karakter level 1 ditampilkan sebagai nonaktif
        if (c.getLevel() == 1) {
            lbl.setEnabled(false);
        }

        // Kursor berbentuk tangan
        lbl.setCursor(
            Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
        );

        // Ukuran kartu seragam
        lbl.setPreferredSize(new Dimension(440, 42));
        lbl.setMinimumSize(new Dimension(440, 42));
        lbl.setMaximumSize(
            new Dimension(Integer.MAX_VALUE, 42)
        );

        return lbl;
    }
}
