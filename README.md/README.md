# CharacterManager — TP8 Implementasi GUI dan Analisis MVC

## 1. Deskripsi Proyek

CharacterManager adalah program berbasis Java yang digunakan untuk mengelola kumpulan objek karakter. Pada TP8, proyek dikembangkan dari program berbasis console menjadi aplikasi Graphical User Interface (GUI) menggunakan Java Swing.

Program ini menerapkan konsep Object-Oriented Programming (OOP), pemisahan Model dan View, serta pemrograman event-driven. Data karakter dikelola oleh `CharacterManager`, sedangkan `MainWindow` bertanggung jawab menampilkan daftar karakter dan statistik rata-rata level.

## 2. Fitur Program

### A. Implementasi GUI

- Menampilkan jendela utama menggunakan `JFrame`.
- Menggunakan judul jendela `Character Manager v0.3`.
- Menampilkan data karakter secara dinamis menggunakan perulangan `for` dan `JLabel`.
- Menerima daftar karakter melalui constructor `MainWindow`.
- Menjalankan GUI menggunakan `SwingUtilities.invokeLater()` pada `Main.java`.

### B. Perluasan Data Karakter

Pada TP8, jumlah karakter ditambah dari tiga menjadi lima karakter. Dua karakter baru yang ditambahkan adalah:

- **Bima** — WARRIOR, Level 7.
- **Luna** — MAGE, Level 5.

Penambahan karakter dilakukan melalui `Main.java` dengan memanggil method `addCharacter()` milik `CharacterManager`.

### C. Mini-Challenge: Statistik Rata-rata Level

Fitur statistik rata-rata level ditambahkan pada `MainWindow.java` menggunakan `JLabel`.

Program menjumlahkan level seluruh karakter, kemudian membaginya dengan jumlah karakter yang tersedia. Hasil perhitungan diformat menggunakan dua angka desimal dengan tanda koma.

Contoh hasil:
- Sebelum penambahan karakter: 3 karakter, rata-rata level 4,00.
- Sesudah penambahan karakter: 5 karakter, rata-rata level 4,80.

Nilai statistik dihitung berdasarkan data karakter yang diterima oleh `MainWindow`, sehingga perubahan data yang diberikan melalui `Main.java` akan memengaruhi hasil perhitungan.

## 3. Struktur Proyek
TP-8_SOLA/
├── README.md
├── Screenshot/
│   ├── TP8_Sebelum.png
│   └── TP8_Sesudah.png
└── src/
    ├── Main.java
    └── com/unpri/charactermanager/
        ├── model/
        │   ├── Character.java
        │   ├── CharacterManager.java
        │   └── CharacterType.java
        └── view/
            └── MainWindow.java

## 4. Konsep GUI, OOP, dan MVC yang Diterapkan

### A. JFrame dan JPanel

`MainWindow` merupakan class yang mewarisi `JFrame`, sehingga dapat digunakan sebagai jendela utama aplikasi. `JPanel` digunakan sebagai wadah untuk mengatur komponen tampilan.

### B. JLabel dan Tampilan Dinamis

`JLabel` digunakan untuk menampilkan judul, informasi setiap karakter, dan statistik rata-rata level. Perulangan `for` membuat satu label untuk setiap objek karakter, sehingga penambahan karakter tidak memerlukan penulisan label secara manual satu per satu.

### C. Model

Class `Character`, `CharacterManager`, dan `CharacterType` berada pada bagian model. `Character` menyimpan informasi karakter, `CharacterType` menentukan tipe karakter, sedangkan `CharacterManager` mengelola kumpulan karakter.

### D. View

`MainWindow` berperan sebagai View karena bertanggung jawab menampilkan data karakter kepada pengguna melalui antarmuka Java Swing.

### E. Aliran Data dan Prinsip MVC

Aliran data dimulai dari `Main.java`, yang membuat objek `CharacterManager` dan menambahkan karakter melalui method `addCharacter()`. Selanjutnya, daftar karakter diperoleh melalui `getAllCharacters()` dan diberikan kepada `MainWindow` melalui constructor. `MainWindow` menampilkan data karakter secara dinamis menggunakan `JLabel` dan menghitung rata-rata level untuk ditampilkan kepada pengguna. Alur ini menunjukkan pemisahan tanggung jawab antara pengelolaan data pada Model dan penyajian informasi pada View, sesuai prinsip dasar MVC.

### F. Event Dispatch Thread

`SwingUtilities.invokeLater()` digunakan untuk menjadwalkan pembuatan dan penampilan GUI pada Event Dispatch Thread (EDT). Mekanisme ini membantu menjaga agar operasi antarmuka Swing dijalankan pada thread yang sesuai.

## 5. Dokumentasi Screenshot Output

Screenshot berikut digunakan untuk membuktikan bahwa aplikasi berjalan dan statistik berubah setelah data karakter ditambahkan.

### Screenshot 1 — Sebelum Perubahan Data

![Output sebelum penambahan karakter](Screenshot/TP8_Sebelum.png)

Screenshot ini menunjukkan aplikasi dengan tiga karakter awal, yaitu Arjuna, Merlin, dan Athena. Statistik rata-rata level yang ditampilkan adalah 4,00.

### Screenshot 2 — Sesudah Perubahan Data

![Output sesudah penambahan karakter](Screenshot/TP8_Sesudah.png)

Screenshot ini menunjukkan aplikasi setelah penambahan Bima dan Luna, sehingga jumlah karakter menjadi lima. Statistik rata-rata level berubah menjadi 4,80.

Kedua screenshot tersebut menjadi bukti bahwa penambahan data karakter memengaruhi statistik yang ditampilkan pada GUI.

## 6. Google Docs — Bagian D

Bagian D berisi analisis GUI dan MVC dengan panjang minimal 500 kata menggunakan kata-kata sendiri.

Topik yang dibahas meliputi:

1. Perbedaan program sekuensial berbasis console dan program event-driven berbasis GUI dengan analogi kehidupan sehari-hari.
2. Alasan GUI Swing dijalankan menggunakan `SwingUtilities.invokeLater()` serta risiko jika mekanisme tersebut diabaikan.
3. Identifikasi kesalahan dan perbaikan kode Java Swing yang menyebabkan jendela tidak muncul.
4. Penjelasan relasi *is-a* pada `MainWindow extends JFrame` dan *has-a* pada `CharacterManager` yang memiliki daftar karakter.
5. Penentuan tanggung jawab pengambilan data dan penampilan data berdasarkan prinsip MVC.

**Link Google Docs Bagian D:**

[https://docs.google.com/document/d/18RHBzT30HZFWkxn-mPbZDtSBxyVVRqAcSK6tWVO_w68/edit?tab=t.0]

## 7. Teknologi

- Bahasa pemrograman: Java.
- IDE: Visual Studio Code.
- GUI framework: Java Swing.
- Komponen GUI: `JFrame`, `JPanel`, `JLabel`, dan `BoxLayout`.
- Struktur data: `ArrayList`.
- Konsep: OOP, MVC, dan event-driven programming.

## 8. Kesimpulan

Pada TP8, CharacterManager dikembangkan dari program berbasis console menjadi aplikasi GUI menggunakan Java Swing. Aplikasi menampilkan data karakter secara dinamis dan menyediakan statistik rata-rata level yang dihitung berdasarkan data yang tersedia. Penambahan dua karakter baru membuktikan bahwa tampilan dapat menyesuaikan jumlah data tanpa membuat label secara manual satu per satu. Penerapan `SwingUtilities.invokeLater()` membantu memastikan GUI dijalankan pada Event Dispatch Thread. Melalui tugas ini, konsep OOP, pemisahan Model dan View, serta dasar event-driven programming dapat dipraktikkan dalam aplikasi Java.