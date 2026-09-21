# 🎟️ Ticket App

Aplikasi sederhana berbasis **Android Jetpack Compose** untuk melakukan pemesanan tiket.  
Project ini dibuat untuk menerapkan konsep **State Hoisting**, **rememberSaveable**, dan **LaunchedEffect**.

## ✨ Fitur

- 👤 Input nama pembeli
- 🎟️ Menentukan jumlah tiket
- ➕ Menambah jumlah tiket
- ➖ Mengurangi jumlah tiket
- 💰 Menghitung total harga tiket secara otomatis
- ⚠️ Validasi jika nama pembeli masih kosong
- ⏳ Menampilkan status proses pemesanan
- ✅ Menampilkan status tiket berhasil dipesan
- 🔄 State tetap tersimpan saat terjadi perubahan konfigurasi menggunakan `rememberSaveable`

## 📱 Alur Aplikasi

1. Pengguna mengisi nama pembeli.
2. Pengguna menentukan jumlah tiket menggunakan tombol `+` dan `-`.
3. Total harga dihitung berdasarkan jumlah tiket.
4. Pengguna menekan tombol **Pesan Tiket**.
5. Jika nama masih kosong, akan muncul:

   ```text
   Status: Nama masih kosong
