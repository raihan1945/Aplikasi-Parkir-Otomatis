## Authors

- [@koutasatouu](https://github.com/koutasatouu) Raditya Abiba Shanau [2400018051]
- [@raihan1945](https://github.com/raihan1945) Raihan Rafi Samudra [2400018041]
- [@stellar48](https://github.com/stellar48) Muhammad Athaillah [2400018010]


# Aplikasi Parkir Otomatis
Aplikasi Parkir Pintar adalah aplikasi desktop berbasis Java Swing untuk mengelola data parkir kendaraan di sebuah area parkir tertutup.
​
Aplikasi terhubung ke database MySQL untuk menyimpan data kendaraan yang masuk dan keluar, serta menampilkan histori parkir.



# Deskripsi Proyek
Aplikasi ini adalah sistem parkir pintar berbasis desktop yang membantu petugas mengelola kendaraaan masuk dan keluar secara terkomputerisasi menggunakan java dan mysql. Aplikasi menyediakan form login untuk otentikasi petugas, kemudian memungkinkan input data kendaraan yang masuk (plat nomor, jenis kendaraan, waktu masuk) ke tabel kendaraan di database.
Kendaraan yang masih parkir ditampilkan dalam tabel data parkir dengan fitur pencarian dan informasi jumlah kendaraan yang sedang berada di area parkir. Saaat kendaraan keluar, petugas memindahkan data tersebut ke histori parkir yang bisa dilihat lengkap dengan fitur pencarian dan total kendaraan. Seluruh proses terhubung melalui kelas dbconnection yang mengatur koneksi ke database parkirpintar, sehingga pengelolaan parkir menjadi lebih terstruktur, rapi, dan mudah dipantau secara realtime

## Fitur Utama

- Login pengguna sebelum masuk ke sistem parkir.
- Input data kendaraan masuk (plat nomor, jenis kendaraan, dll).
- Monitoring kendaraan yang sedang parkir (belum keluar) dengan tabel yang tidak bisa diedit.
- Pencarian data kendaraan berdasarkan plat nomor di tampilan utama dan histori.
- Update waktu keluar kendaraan dan validasi input nominal bayar.
- Histori parkir kendaraan yang sudah keluar, lengkap dengan pencarian dan total kendaraan.
- Informasi tanggal dan jam realtime di form data parkir.
- Pembatasan kapasitas maksimal parkir (contoh: 100 kendaraan).

## Spesifikasi fungsional
- Sistem login untuk otentikasi user menggunakan tabel user di database.
- Input data kendaraan masuk: plat nomor, jenis kendaraan (motor/mobil), waktu otomatis (now).
- Menampilkan daftar kendaraan yang sedang parkir dalam tabel read only.
- Pencarian kendaraan berdasarkan plat nomor di tampilan data parkir aktif.
- Menampilkan jumlah kendaraan yang sedang parkir (counter)
- Validasi kapasitas maksimum parkir, menolak input baru jika penuh.
- Input nominal bayar ketika kendaraan keluar dengan validasi tidak boleh kosong, tidak boleh minus, dan harus angka.
- Update waktu keluar kendaraaan (otomatis pakai waktu saat ini) dan simpan ke database.
- Menampilkn histori parkir kendaraan yang sudah keluar beserta pencarian berdasarkan plat nomor.
- Menampilkan total kendaraan pada halaman histori parkir.
- Navigasi antar form: login > data parkir > input kendaraaan > histori parkir melalui menu bar.
- Penampilan tanggal dan jam realtime di form input dan form data parkit

## Teknologi Yang Digunakan

**Bahasa Pemrograman:** Java (Java SE)

**GUI Framework:** Java Swing (JFrame, JPanel, JTable, JMenuBar, dsb).

**Database:** MySQL dengan skema parkir_pintar.

**Driver Database:** JDBC MySQL.

**IDE yang Disarankan:** IntelliJ IDEA / NetBeans / Eclipse.

**Rancangan diagram
![Login use case)[Image/login-usecase.png]
