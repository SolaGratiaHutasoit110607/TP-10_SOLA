# Character Manager

**TP10 — Sprint 4: Theming**

## 1. Deskripsi Proyek

Character Manager merupakan aplikasi sederhana berbasis Java Swing untuk mengelola dan menampilkan daftar karakter. Setiap karakter memiliki informasi berupa nama, role, level, hit points (HP), dan attack power (ATK).

Pada TP10 Sprint 4, aplikasi dikembangkan dengan pembaruan tampilan menggunakan sistem tema terpusat melalui kelas `AppTheme`. Tujuannya adalah membuat antarmuka lebih menarik, konsisten, dan mudah dikembangkan.

## 2. Fitur Aplikasi

Fitur yang tersedia pada aplikasi Character Manager:

- Menampilkan daftar karakter.
- Menampilkan nama, role, level, HP, dan ATK setiap karakter.
- Memberikan warna kartu berbeda berdasarkan role karakter.
- Menampilkan teks emas dan tebal untuk karakter dengan level 5 atau lebih.
- Menampilkan informasi tambahan melalui tooltip.
- Memberikan tampilan nonaktif pada karakter dengan level 1.
- Mengatur tata letak kartu agar lebih rapi dan konsisten.

## 3. Implementasi AppTheme

Kelas `AppTheme` dibuat pada package:

`com.unpri.charactermanager.view`

Kelas ini berfungsi sebagai pusat konfigurasi tampilan aplikasi, meliputi:

- Warna latar belakang aplikasi.
- Warna teks utama dan teks terang.
- Warna khusus untuk karakter dengan level tinggi.
- Font judul dan font isi.
- Border kartu karakter.
- Fungsi `colorFor()` untuk menentukan warna berdasarkan role.
- Fungsi `describe()` untuk memberikan deskripsi role.

Dengan adanya `AppTheme`, pengaturan tampilan tidak perlu ditulis berulang kali di dalam `MainWindow`.

## 4. Screenshot Tampilan Aplikasi

![Screenshot Sebelum](screenshots/Sebelum.png)
Tampilan aplikasi sebelum penerapan tema pada Sprint 4.

### After — Character Manager v0.4

Tampilan aplikasi setelah penerapan `AppTheme` pada Sprint 4.

![Screenshot Sesudah](screenshots/Sesudah.png)

Deskripsi: Pada versi terbaru, setiap kartu karakter memiliki warna berdasarkan role, teks emas tebal untuk level 5 ke atas, tooltip informasi, dan tampilan khusus untuk karakter level 1.

## 5. Data Karakter

Aplikasi menggunakan enam karakter sebagai data pengujian:

| Nama | Role | Level |
|---|---|---:|
| Arjuna | WARRIOR | 5 |
| Merlin | MAGE | 3 |
| Athena | ARCHER | 4 |
| Gatotkaca | WARRIOR | 7 |
| Morgana | MAGE | 1 |
| Leonidas | WARRIOR | 6 |

Karakter dengan level 5 atau lebih mendapatkan teks berwarna emas dan tebal. Sementara itu, Morgana dengan level 1 ditampilkan dalam keadaan nonaktif.

## 6. Alasan Warna Diletakkan di AppTheme

Pengaturan warna diletakkan di kelas `AppTheme`, bukan di kelas `Character`, karena warna merupakan bagian dari tampilan antarmuka, bukan data utama karakter.

Kelas `Character` sebaiknya hanya bertanggung jawab menyimpan informasi dan perilaku karakter. Dengan memisahkan konfigurasi tema dari model, perubahan warna atau font dapat dilakukan tanpa mengubah struktur data karakter.

Pemisahan ini juga membuat kode lebih terorganisasi, mengurangi pengulangan kode, dan memudahkan pemeliharaan aplikasi.

## 7. Dua Properti Tambahan

Selain pengaturan warna dan font, terdapat dua properti tambahan yang diterapkan pada kartu karakter:

### 1. Tooltip

Tooltip digunakan untuk menampilkan informasi tambahan ketika kursor diarahkan ke kartu karakter. Informasi tersebut mencakup deskripsi role, HP, dan ATK.

Alasan penggunaan: Tooltip membantu pengguna memperoleh informasi tambahan tanpa membuat tampilan utama terlalu penuh.

Implementasi menggunakan:

`setToolTipText()`

### 2. Tampilan Nonaktif untuk Level 1

Karakter dengan level 1 ditampilkan dalam keadaan nonaktif menggunakan:

`setEnabled(false)`

Alasan penggunaan: Properti ini memberikan pembeda visual untuk karakter level 1 dibandingkan karakter lainnya.

## 8. Teknologi yang Digunakan

- Bahasa pemrograman: Java
- GUI Framework: Java Swing
- Konsep pemrograman: Object-Oriented Programming (OOP)
- Arsitektur: Model-View-Controller (MVC)
- Pengaturan tema: AppTheme
- Layout Manager: BoxLayout

## 9. Dokumentasi Part D

Dokumentasi pembahasan teori Java Swing dan MVC untuk Part D dapat diakses melalui tautan berikut:

**Google Docs:** [https://docs.google.com/document/d/1TvsUyntcHHmm_9-Lou-6bEuZz8Y6NI611JOY23A4jD4/edit?usp=sharing]

## 10. Kesimpulan

Melalui TP10 Sprint 4, aplikasi Character Manager mengalami peningkatan dari sisi tampilan dan pengorganisasian kode. Penggunaan `AppTheme` membuat konfigurasi visual lebih terpusat, sedangkan penerapan tooltip dan tampilan nonaktif menambah informasi serta variasi visual pada kartu karakter.

Penerapan pemisahan Model dan View juga membantu menjaga struktur program agar lebih mudah dipahami, dirawat, dan dikembangkan pada tahap berikutnya.