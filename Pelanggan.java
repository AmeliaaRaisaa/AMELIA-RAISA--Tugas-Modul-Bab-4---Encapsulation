import java.util.Scanner; // Mengimpor kelas Scanner untuk input dari pengguna

// Kelas untuk merepresentasikan pelanggan
class Pelanggan {
    private String nama; // Nama pelanggan
    private String nomorPelanggan; // Nomor pelanggan (10 digit)
    private int saldo; // Saldo pelanggan
    private String pin; // PIN untuk autentikasi
    private int percobaanGagal = 0; // Menghitung jumlah percobaan gagal untuk autentikasi
    private boolean diblokir = false; // Status akun apakah diblokir atau tidak

    // Constructor untuk inisialisasi objek Pelanggan
    public Pelanggan(String nama, String nomorPelanggan, int saldo, String pin) {
        this.nama = nama; // Menginisialisasi nama pelanggan
        this.nomorPelanggan = nomorPelanggan; // Menginisialisasi nomor pelanggan
        this.saldo = saldo; // Menginisialisasi saldo pelanggan
        this.pin = pin; // Menginisialisasi PIN pelanggan
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
    
    public String getNama() {
        return nama;
    }

    // Getter untuk nomor pelanggan
    public String getNomorPelanggan() {
        return nomorPelanggan; // Mengembalikan nomor pelanggan
    }

    // Mengecek apakah akun diblokir
    public boolean isDiblokir() {
        return diblokir; // Mengembalikan status blokir akun
    }

    // Metode untuk autentikasi menggunakan PIN
    public boolean autentikasi(String pinInput) {
        if (diblokir) { // Jika akun diblokir
            System.out.println("Akun Anda telah diblokir."); // Tampilkan pesan
            return false; // Kembalikan false
        }
        if (this.pin.equals(pinInput)) { // Jika PIN yang dimasukkan benar
            percobaanGagal = 0; // Reset percobaan gagal jika PIN benar
            return true; // Kembalikan true
        } else { // Jika PIN salah
            percobaanGagal++; // Tambah jumlah percobaan gagal
            if (percobaanGagal >= 3) { // Jika sudah 3 kali salah
                diblokir = true; // Blokir akun
                System.out.println("Akun Anda telah diblokir karena 3x kesalahan."); // Tampilkan pesan
            } else {
                System.out.println("PIN salah! Percobaan tersisa: " + (3 - percobaanGagal)); // Tampilkan sisa percobaan
            }
            return false; // Kembalikan false
        }
    }

    // Metode untuk melakukan top-up saldo
    public void topUp(int jumlah) {
        saldo += jumlah; // Tambahkan jumlah top-up ke saldo
        System.out.println("Top-up berhasil! Saldo saat ini: Rp" + saldo); // Tampilkan saldo saat ini
    }

    // Metode untuk melakukan pembelian
    public void beli(int jumlah) {
        if (jumlah > saldo) { // Jika jumlah pembelian lebih besar dari saldo
            System.out.println("Saldo tidak cukup!"); // Tampilkan pesan
            return; // Keluar dari metode
        }

        int cashback = hitungCashback(jumlah); // Hitung cashback
        saldo -= jumlah; // Kurangi saldo dengan jumlah pembelian
        saldo += cashback; // Tambahkan cashback ke saldo

        // Cek apakah saldo setelah transaksi kurang dari Rp10.000
        if (saldo < 10000) {
            saldo += jumlah; // Kembalikan saldo jika transaksi gagal
            saldo -= cashback; // Hapus cashback
            System.out.println("Transaksi gagal! Saldo tidak boleh kurang dari Rp10.000"); // Tampilkan pesan
        } else {
            System.out.println("Pembelian berhasil! Cashback: Rp" + cashback + ", Saldo saat ini: Rp" + saldo); // Tampilkan hasil pembelian
        }
    }

    // Metode untuk menghitung cashback berdasarkan jenis pelanggan
    private int hitungCashback(int jumlah) {
        String kodeJenis = nomorPelanggan.substring(0, 2); // Ambil 2 digit pertama dari nomor pelanggan
        int cashback = 0; // Inisialisasi cashback

        // Hitung cashback berdasarkan jenis pelanggan
        if (kodeJenis.equals("38")) { // Silver
            if (jumlah > 1000000) cashback = (int) ( jumlah * 0.05); // 5% cashback untuk pembelian di atas Rp1.000.000
        } else if (kodeJenis.equals("56")) { // Gold
            if (jumlah > 500000) cashback = (int) (jumlah * 0.1); // 10% cashback untuk pembelian di atas Rp500.000
        } else if (kodeJenis.equals("74")) { // Platinum
            cashback = (int) (jumlah * 0.15); // 15% cashback untuk semua pembelian
        }
        return cashback; // Kembalikan nilai cashback
    }
}