
package com.unpri.charactermanager.view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.model.CharacterType;

public class CharacterFormPanel extends JPanel {

    private final CharacterManager manager;
    private final Runnable onAdded;

    private final JTextField nameField = new JTextField(14);

    private final JComboBox<CharacterType> roleBox =
            new JComboBox<>(CharacterType.values());

    private final JSpinner levelSpinner =
            new JSpinner(new SpinnerNumberModel(1, 1, 99, 1));

    private final JLabel messageLabel = new JLabel(" ");

    private final Border nameBorder;
    private final Border levelBorder;

    public CharacterFormPanel(CharacterManager manager, Runnable onAdded) {

        this.manager = manager;
        this.onAdded = onAdded;

        nameBorder = nameField.getBorder();
        levelBorder = levelSpinner.getBorder();

        setLayout(new BorderLayout());
        setBackground(AppTheme.BG_MAIN);

        setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 8));
        setPreferredSize(new Dimension(250, 0));

        JLabel title = new JLabel("Tambah Karakter");
        title.setFont(AppTheme.FONT_BODY.deriveFont(
                java.awt.Font.BOLD, 16f
        ));
        title.setForeground(AppTheme.TEXT_MAIN);

        messageLabel.setFont(AppTheme.FONT_BODY);

        JButton saveButton = new JButton("Simpan");

        saveButton.setCursor(
                Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
        );

        saveButton.addActionListener(e -> onSave());

        nameField.addActionListener(e -> onSave());

        JPanel rows = new JPanel(new GridLayout(0, 1, 0, 4));
        rows.setOpaque(false);

        rows.add(title);
        rows.add(makeLabel("Nama:", nameField));
        rows.add(nameField);

        rows.add(makeLabel("Role:", roleBox));
        rows.add(roleBox);

        rows.add(makeLabel("Level:", levelSpinner));
        rows.add(levelSpinner);

        rows.add(messageLabel);
        rows.add(saveButton);

        add(rows, BorderLayout.NORTH);
    }

    private JLabel makeLabel(String text, JComponent target) {

        JLabel label = new JLabel(text);
        label.setFont(AppTheme.FONT_BODY);
        label.setForeground(AppTheme.TEXT_MAIN);
        label.setLabelFor(target);

        return label;
    }

    // Proses penyimpanan karakter
    private void onSave() {

        clearMessage();

        // Membersihkan spasi di awal dan akhir
        String name = nameField.getText().trim();

        // Memvalidasi nama
        String problem = validateName(name);

        if (problem != null) {

            showError(problem, nameField);
            nameField.requestFocusInWindow();

            return;
        }

        // Memastikan nilai spinner sudah dikomit
        try {

            levelSpinner.commitEdit();

        } catch (ParseException ex) {

            showError("Level harus berupa angka", levelSpinner);
            levelSpinner.requestFocusInWindow();

            return;
        }

        // Mengambil role dan level
        CharacterType role =
                (CharacterType) roleBox.getSelectedItem();

        int level = (Integer) levelSpinner.getValue();

        try {

            // Membuat objek karakter
            Character character =
                    new Character(name, role, level);

            // Menambahkan karakter ke manager
            boolean success = manager.addCharacter(character);

            if (!success) {

                showError("Nama sudah dipakai", nameField);
                nameField.requestFocusInWindow();

                return;
            }

        } catch (IllegalArgumentException ex) {

            // Menampilkan pesan langsung dari Model
            showError(ex.getMessage(), levelSpinner);
            levelSpinner.requestFocusInWindow();

            return;
        }

        // Jika berhasil, kosongkan form
        nameField.setText("");
        roleBox.setSelectedIndex(0);
        levelSpinner.setValue(1);

        showSuccess("Karakter berhasil ditambahkan!");

        nameField.requestFocusInWindow();

        // Memperbarui daftar karakter
        onAdded.run();
    }

    // Validasi nama
    private String validateName(String name) {

        // 1. Kelengkapan
        if (name.isEmpty()) {
            return "Nama wajib diisi";
        }

        // 2. Panjang
        if (name.length() < 3 || name.length() > 20) {
            return "Nama harus 3-20 karakter";
        }

        // 3. Format
        if (!name.matches("[A-Za-z ]+")) {
            return "Nama hanya boleh berisi huruf dan spasi";
        }

        // Aturan tambahan: tidak boleh dua spasi berurutan
        if (name.contains("  ")) {
            return "Nama tidak boleh memiliki spasi ganda";
        }

        // 4. Keunikan
        if (manager.isNameTaken(name)) {
            return "Nama sudah dipakai";
        }

        return null;
    }

    // Menampilkan pesan error
    private void showError(String text, JComponent source) {

        messageLabel.setText(text);
        messageLabel.setForeground(AppTheme.ERROR);

        source.setBorder(
                BorderFactory.createLineBorder(AppTheme.ERROR, 2)
        );
    }

    // Menampilkan pesan sukses
    private void showSuccess(String text) {

        messageLabel.setText(text);
        messageLabel.setForeground(AppTheme.SUCCESS);
    }

    // Menghapus pesan dan memulihkan border
    private void clearMessage() {

        messageLabel.setText(" ");

        nameField.setBorder(nameBorder);
        levelSpinner.setBorder(levelBorder);
    }
}
