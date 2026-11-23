
package com.unpri.charactermanager.model;

import java.util.Objects;

/**
 * Class Character untuk merepresentasikan karakter.
 */
public class Character {

    // Atribut
    private int id;
    private String name;
    private CharacterType type;
    private int level;
    private int hitPoints;
    private int attackPower;
    private int mana;

    // Menghitung jumlah objek yang dibuat
    private static int totalCharacterCreated = 0;

    // Constructor utama
    public Character(String name, CharacterType type, int level) {

        setName(name);
        setType(type);
        setLevel(level);

        this.mana = 0;

        totalCharacterCreated++;
    }

    // Constructor untuk data database
    public Character(int id, String name, CharacterType type,
                     int level, int hitPoints, int attackPower) {

        setName(name);
        setType(type);
        setLevel(level);

        if (hitPoints < 0 || attackPower < 0) {
            throw new IllegalArgumentException(
                "Statistik karakter tidak boleh negatif"
            );
        }

        this.id = id;
        this.hitPoints = hitPoints;
        this.attackPower = attackPower;
        this.mana = 0;

        totalCharacterCreated++;
    }

    // Menghitung HP berdasarkan level
    private int calculateBaseHP() {
        return 100 + (level * 10);
    }

    // Menghitung attack berdasarkan level
    private int calculateBaseAttack() {
        return 10 + (level * 5);
    }

    // Getter
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CharacterType getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public int getMana() {
        return mana;
    }

    public static int getTotalCharacterCreated() {
        return totalCharacterCreated;
    }

    // Setter ID
    public void setId(int id) {
        this.id = id;
    }

    // Validasi nama
    public void setName(String name) {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                "Nama tidak boleh kosong"
            );
        }

        this.name = name.trim();
    }

    // Validasi role
    public void setType(CharacterType type) {

        if (type == null) {
            throw new IllegalArgumentException(
                "Role wajib dipilih"
            );
        }

        if (type == CharacterType.MAGE && level > 60) {
            throw new IllegalArgumentException(
                "Karakter MAGE tidak boleh memiliki level di atas 60"
            );
        }

        this.type = type;
    }

    // Validasi level
    public void setLevel(int level) {

        if (level < 1 || level > 99) {
            throw new IllegalArgumentException(
                "Level harus berada di antara 1-99"
            );
        }

        if (type == CharacterType.MAGE && level > 60) {
            throw new IllegalArgumentException(
                "Karakter MAGE tidak boleh memiliki level di atas 60"
            );
        }

        this.level = level;

        this.hitPoints = calculateBaseHP();
        this.attackPower = calculateBaseAttack();
    }

    // Setter HP
    public void setHitPoints(int hitPoints) {

        if (hitPoints < 0) {
            throw new IllegalArgumentException(
                "Hit points tidak boleh negatif"
            );
        }

        this.hitPoints = hitPoints;
    }

    // Setter attack
    public void setAttackPower(int attackPower) {

        if (attackPower < 0) {
            throw new IllegalArgumentException(
                "Attack power tidak boleh negatif"
            );
        }

        this.attackPower = attackPower;
    }

    // Setter mana
    public void setMana(int mana) {

        if (mana < 0) {
            throw new IllegalArgumentException(
                "Mana tidak boleh negatif"
            );
        }

        this.mana = mana;
    }

    // Memeriksa apakah karakter masih hidup
    public boolean isAlive() {
        return hitPoints > 0;
    }

    // Menampilkan informasi karakter
    @Override
    public String toString() {

        return "Character{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", level=" + level +
                ", hp=" + hitPoints +
                ", atk=" + attackPower +
                ", mana=" + mana +
                '}';
    }

    // Membandingkan karakter berdasarkan nama
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Character)) {
            return false;
        }

        Character other = (Character) obj;

        return Objects.equals(name, other.name);
    }

    // Hash code berdasarkan nama
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
