# Mini-Project-Backend

Ini adalah proyek back-end untuk sistem Point of Sale (POS) yang dibuat selama bootcamp. Backend ini dibangun menggunakan Java dan Spring Boot, serta dilengkapi dengan autentikasi pengguna untuk memastikan keamanan akses ke sistem.

## Daftar Isi

- [Fitur](#fitur)
- [Teknologi yang Digunakan](#teknologi-yang-digunakan)
- [Instalasi](#instalasi)
- [Cara Penggunaan](#cara-penggunaan)
- [Kontribusi](#kontribusi)
- [Kontak](#kontak)

## Fitur

- CRUD (Create, Read, Update, Delete) untuk produk, transaksi, dan kategori.
- Autentikasi pengguna dengan JWT (JSON Web Token).
- Manajemen user role (admin dan pengguna).
- API RESTful untuk berinteraksi dengan front-end.

## Teknologi yang Digunakan

Proyek ini dibangun dengan teknologi berikut:

- **Java**: Bahasa pemrograman utama untuk backend.
- **Spring Boot**: Framework untuk membangun aplikasi backend dengan cepat dan mudah.
- **Spring Security**: Untuk menangani autentikasi dan otorisasi pengguna.
- **JWT (JSON Web Token)**: Untuk mengelola token autentikasi.
- **PostgreSQL**: Database yang digunakan untuk menyimpan data.

## Instalasi

Ikuti langkah-langkah berikut untuk menginstal dan menjalankan proyek ini secara lokal:

1. Clone repositori ini:
   ```bash
   git clone https://github.com/kattriananda/Mini-Project-Backend.git
2. Masuk ke direktori proyek:
    ```bash
    cd Mini-Project-Backend
3. Install dependencies:
    ```bash
    npm install
4. Jalankan aplikasi:
   ```bash
    npm start

## Cara Penggunaan
API ini menyediakan endpoint untuk melakukan operasi CRUD pada data produk, kategori, dan transaksi. Selain itu, API ini juga dilengkapi dengan autentikasi pengguna menggunakan JWT. Pengguna harus melakukan login untuk mendapatkan token, yang kemudian digunakan untuk mengakses endpoint yang memerlukan autentikasi.

## Kontribusi
Jika Anda ingin berkontribusi, silakan fork repositori ini, buat branch baru, dan buka Pull Request. Kami menghargai semua kontribusi yang dapat meningkatkan proyek ini.

## Kontak
Jika ada pertanyaan atau masukan, silakan hubungi saya melalui email di kattriananda@gmail.com.
