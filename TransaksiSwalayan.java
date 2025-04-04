import java.util.Scanner; // Mengimpor kelas Scanner untuk input dari pengguna

// Kelas utama untuk menjalankan program
public class TransaksiSwalayan {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Membuat objek Scanner untuk input
        System.out.print("Masukkan nama pelanggan: ");
        String nama = scanner.nextLine(); // Input nama pelanggan
        System.out.print("Masukkan nomor pelanggan (10 digit): ");
        String nomorPelanggan = scanner.nextLine(); // Input nomor pelanggan
        System.out.print("Masukkan saldo awal: ");
        int saldo = scanner.nextInt(); // Input saldo awal
        System.out.print("Masukkan PIN: ");
        String pin = scanner.next(); // Input PIN

        // Validasi nomor pelanggan
        if (nomorPelanggan.length() != 10 || 
            !(nomorPelanggan.startsWith("38") || nomorPelanggan.startsWith("56") || nomorPelanggan.startsWith("74"))) {
            System.out.println("Nomor pelanggan tidak valid!"); // Tampilkan pesan jika nomor tidak valid
            scanner.close(); 
            return; // Keluar dari program
        }

        Pelanggan pelanggan = new Pelanggan(nama, nomorPelanggan, saldo, pin); // Membuat objek pelanggan
        System.out.println("Status pelanggan: " + pelanggan.getStatus()); // Tampilkan status pelanggan

        // Menu transaksi
        while (true) {
            System.out.println("==============================");
            System.out.println("       Menu Transaksi         ");
            System.out.println("==============================");
            System.out.println("1. Top-Up");
            System.out.println("2. Pembelian");
            System.out.println("3. Keluar");
            System.out.print("Pilih menu (1-3): ");
            int pilihan = scanner.nextInt(); // Input pilihan menu

            switch (pilihan) {
                case 1: // Top-Up
                    System.out.print("Masukkan jumlah top-up: ");
                    int jumlahTopUp = scanner.nextInt(); // Input jumlah top-up
                    pelanggan.topUp(jumlahTopUp); // Panggil metode top-up
                    break;
                case 2: // Pembelian
                    System.out.print("Masukkan jumlah pembelian: ");
                    int jumlahBeli = scanner.nextInt(); // Input jumlah pembelian
                    System.out.print("Masukkan PIN: ");
                    String pinInput = scanner.next(); // Input PIN untuk autentikasi
                    if (pelanggan.autentikasi(pinInput)) { // Cek autentikasi
                        pelanggan.beli(jumlahBeli); // Panggil metode beli
                    }
                    break;
                case 3: // Keluar
                    System.out.println("Terima kasih telah menggunakan layanan kami!"); // Tampilkan pesan terima kasih
                    scanner.close(); // Tutup scanner
                    return; // Keluar dari program
                default:
                    System.out.println("Pilihan tidak valid!"); // Tampilkan pesan jika pilihan tidak valid
            }
        }
    }
}