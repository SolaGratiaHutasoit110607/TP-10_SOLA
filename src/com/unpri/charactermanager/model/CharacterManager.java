
package com.unpri.charactermanager.model;

import java.util.ArrayList;

/**
 * Mengelola kumpulan karakter.
 */
public class CharacterManager {

    private ArrayList<Character> characterList;

    // Jumlah maksimal karakter
    private static final int MAX_CHARACTERS = 20;

    // Constructor
    public CharacterManager() {
        characterList = new ArrayList<>();
    }

    // Memeriksa nama yang sudah digunakan
    public boolean isNameTaken(String name) {

        if (name == null) {
            return false;
        }

        for (Character c : characterList) {

            if (c.getName().equalsIgnoreCase(name.trim())) {
                return true;
            }
        }

        return false;
    }

    // Menambahkan karakter
    public boolean addCharacter(Character character) {

        if (character == null) {
            return false;
        }

        // Validasi jumlah maksimal
        if (characterList.size() >= MAX_CHARACTERS) {
            throw new IllegalArgumentException(
                "Daftar karakter sudah mencapai batas maksimal 20"
            );
        }

        // Validasi nama duplikat
        if (isNameTaken(character.getName())) {
            return false;
        }

        characterList.add(character);

        System.out.println(
            "Karakter berhasil ditambahkan: "
            + character.getName()
        );

        return true;
    }

    // Mencari karakter berdasarkan nama
    public Character findByName(String name) {

        if (name == null) {
            return null;
        }

        for (Character c : characterList) {

            if (c.getName().equalsIgnoreCase(name.trim())) {
                return c;
            }
        }

        return null;
    }

    // Menghapus karakter
    public boolean removeCharacter(String name) {

        Character target = findByName(name);

        if (target == null) {
            return false;
        }

        characterList.remove(target);

        return true;
    }

    // Menghitung jumlah karakter
    public int getTotalCharacters() {
        return characterList.size();
    }

    // Mengembalikan salinan daftar
    public ArrayList<Character> getAllCharacters() {
        return new ArrayList<>(characterList);
    }

    // Menghitung rata-rata level
    public double getAverageLevel() {

        if (characterList.isEmpty()) {
            return 0.0;
        }

        int total = 0;

        for (Character c : characterList) {
            total += c.getLevel();
        }

        return (double) total / characterList.size();
    }

    // Mencari karakter berdasarkan role
    public ArrayList<Character> findByType(CharacterType type) {

        if (type == null) {
            throw new IllegalArgumentException(
                "Role tidak boleh kosong"
            );
        }

        ArrayList<Character> result = new ArrayList<>();

        for (Character c : characterList) {

            if (c.getType() == type) {
                result.add(c);
            }
        }

        return result;
    }

    // Menghitung jumlah karakter berdasarkan role
    public int countByType(CharacterType type) {

        if (type == null) {
            throw new IllegalArgumentException(
                "Role tidak boleh kosong"
            );
        }

        int count = 0;

        for (Character c : characterList) {

            if (c.getType() == type) {
                count++;
            }
        }

        return count;
    }
}
