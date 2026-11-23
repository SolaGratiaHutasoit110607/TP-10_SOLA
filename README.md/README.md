
# Character Manager - TP14
## Validasi Input dan Error Handling

**Nama:** Sola Gratia Hutasoit
**NIM:** 253303621286
**Kelas:** SI pagi B
**Mata Kuliah:** Java Visual

---

## 1. Deskripsi Program

Character Manager merupakan aplikasi berbasis Java Swing yang digunakan untuk mengelola data karakter. Aplikasi ini memungkinkan pengguna menambahkan karakter dengan mengisi nama, memilih role, dan menentukan level.

Pada TP14, aplikasi dikembangkan dengan menerapkan validasi input dan error handling agar data yang dimasukkan sesuai dengan aturan yang telah ditentukan.

## 2. Implementasi Validasi Input

Validasi dilakukan pada dua lapisan, yaitu View dan Model.

### A. Validasi pada View

View bertugas memeriksa input pengguna sebelum data diteruskan ke Model.

Aturan validasi pada View:
1. Nama karakter tidak boleh kosong.
2. Panjang nama harus antara 3 sampai 20 karakter.
3. Nama hanya boleh mengandung huruf dan spasi.
4. Nama tidak boleh memiliki dua spasi berturut-turut.
5. Nama karakter tidak boleh duplikat.

### B. Validasi pada Model

Model bertugas memastikan data karakter tetap valid meskipun dibuat tanpa melalui form.

Aturan validasi pada Model:
1. Nama karakter tidak boleh kosong.
2. Role karakter tidak boleh null.
3. Level karakter harus berada pada rentang 1–99.
4. Role MAGE memiliki level maksimal 60.
5. Jumlah karakter dalam daftar maksimal 20 karakter.

## 3. Screenshot Pengujian

### A. Pengujian Nama Kosong

![Error Nama Kosong](img/error-nama-kosong.png)

Keterangan: Pengujian dilakukan dengan membiarkan kolom nama kosong. Sistem menampilkan pesan kesalahan karena nama wajib diisi.

### B. Pengujian Nama Duplikat

![Error Nama Duplikat](img/error-nama-duplikat.png)

Keterangan: Pengujian dilakukan dengan memasukkan nama karakter yang sudah terdaftar. Sistem menolak nama tersebut.

### C. Pengujian Penambahan Karakter Berhasil

![Karakter Berhasil Ditambahkan](img/karakter-berhasil.png)

Keterangan: Pengujian dilakukan dengan memasukkan nama, memilih role, dan menentukan level yang valid. Karakter berhasil ditambahkan ke daftar.

## 4. Tabel Pengujian


| No. | Input | Hasil yang Diharapkan | Hasil Aktual | Layer |
|---|---|---|---|---|
| 1 | Nama kosong | Ditolak | Muncul pesan "Nama wajib diisi" | View |
| 2 | Nama 2 karakter | Ditolak | Muncul pesan nama minimal 3 karakter | View |
| 3 | Nama mengandung angka | Ditolak | Muncul pesan format nama tidak valid | View |
| 4 | Nama duplikat | Ditolak | Muncul pesan "Nama sudah dipakai" | View |
| 5 | Nama dengan spasi ganda | Ditolak | Muncul pesan kesalahan spasi ganda | View |
| 6 | MAGE level 61 | Ditolak | Muncul pesan bahwa level MAGE maksimal 60 | Model |
| 7 | MAGE level 60 | Diterima | Karakter MAGE berhasil ditambahkan ke daftar | Model |
| 8 | Karakter ke-21 | Ditolak | Muncul pesan "Daftar karakter sudah mencapai batas maksimal 20" | Model |
| 9 | Nama valid | Diterima | Karakter berhasil ditambahkan dan muncul pada daftar | View dan Model |


## 5. Penjelasan Kompromi Sementara

Dalam pengerjaan TP14, terdapat beberapa kompromi sementara untuk menjaga implementasi program tetap sederhana dan mudah dipahami.

1. Penggunaan ActionListener dibuat secara minimal agar proses penyimpanan karakter lebih mudah dipahami.
2. CharacterFormPanel memanggil CharacterManager secara langsung untuk menambahkan karakter tanpa melalui lapisan Controller.
3. Pembaruan daftar karakter dilakukan menggunakan method refreshList() setelah karakter berhasil ditambahkan.

Kompromi ini digunakan untuk menyederhanakan proses implementasi selama tahap pembelajaran.


## 6. Google Docs - Analisis TP14

Dokumen analisis TP14:

[https://docs.google.com/document/d/1dWkIAyR8GnuMyFX1BjWM6SGfRMlF5D0YzCHw3WlDsnQ/edit?usp=sharing]

---

## 7. Kesimpulan

Melalui TP14, aplikasi Character Manager telah menerapkan validasi input pada lapisan View dan Model. Validasi tersebut membantu mencegah data yang tidak sesuai aturan masuk ke dalam sistem. Selain itu, penggunaan IllegalArgumentException membantu menangani kesalahan input dengan menampilkan pesan yang dapat dipahami pengguna.
