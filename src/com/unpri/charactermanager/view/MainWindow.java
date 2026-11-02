
package com.unpri.charactermanager.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;

import java.util.ArrayList;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import com.unpri.charactermanager.model.Character;

public class MainWindow extends JFrame {

    private JPanel mainPanel;
    private ArrayList<Character> characterList;

    public MainWindow(ArrayList<Character> characterList) {
        super("Character Manager v0.3");

        this.characterList = characterList;

        initComponents();

        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        mainPanel = new JPanel();

        // Susun komponen dari atas ke bawah
        mainPanel.setLayout(
            new BoxLayout(mainPanel, BoxLayout.Y_AXIS)
        );

        mainPanel.setBorder(
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        );

        // Judul daftar karakter
        JLabel titleLabel = new JLabel(
            "=== Daftar Karakter (" + characterList.size() + ") ==="
        );
        mainPanel.add(titleLabel);

        // Hitung total level dan tampilkan karakter secara dinamis
        int totalLevel = 0;

        for (Character c : characterList) {
            String info = c.getName()
                    + " | " + c.getType()
                    + " | Lv." + c.getLevel()
                    + " | HP:" + c.getHitPoints()
                    + " | ATK:" + c.getAttackPower();

            JLabel characterLabel = new JLabel(info);
            mainPanel.add(characterLabel);

            totalLevel += c.getLevel();
        }

        // Hitung rata-rata level
        double averageLevel = 0.0;

        if (!characterList.isEmpty()) {
            averageLevel =
                (double) totalLevel / characterList.size();
        }

        // Format angka dengan dua desimal dan koma
        DecimalFormatSymbols symbols =
            DecimalFormatSymbols.getInstance(
                Locale.forLanguageTag("id-ID")
            );

        DecimalFormat df = new DecimalFormat("0.00", symbols);

        // Tampilkan statistik di bawah daftar karakter
        JLabel averageLabel = new JLabel(
            "Rata-rata Level: " + df.format(averageLevel)
        );

        mainPanel.add(averageLabel);

        add(mainPanel);
    }
}
