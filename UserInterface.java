
import java.util.Scanner;

public class UserInterface {
    public static void tampilkanMenu (){
        System.out.println();
        System.out.println("+================+");
        System.out.println("| Pilih Menu : |");
        System.out.println("+----------------+");
        System.out.println("| [C] : Create |");
        System.out.println("| [R] : Read   |");
        System.out.println("| [U] : Update |");
        System.out.println("| [D] : Delate |");
        System.out.println("| [X] : Exit   |");
        System.out.println("+===============+");
        
    }
    public static void main(String[] args) {
        Database db = new Database();
        System.out.println("APLIKASI SIMPEL CRUD TEKS DATABASE PUTU");
        while(true){
            tampilkanMenu();
            Scanner sc = new Scanner(System.in);
            System.out.print("Pilih : ");
            String pilihan = sc.nextLine();
            pilihan = pilihan.toUpperCase();
            
            switch (pilihan){
                case "C":
                    System.out.println("INFO : Anda masuk menu Create");
                    System.out.println("---------------------------------------");
                    System.out.println("MENU INPUT DATA BARU");
                    
                    System.out.println("NIM                :");
                    String nim = sc.nextLine();
                    
                    System.out.println("NAMA MAHASISWA     :");
                    String nama = sc.nextLine();
                    
                    System.out.println("ALAMAT             :");
                    String alamat = sc.nextLine();
                    
                    System.out.println("SEMESTER           :");
                    int semester = sc.nextInt();
                    
                    System.out.println("SKS                :");
                    int sks = sc.nextInt();
                    
                    System.out.println("IPK                :");
                    double ipk = sc.nextDouble();
                    sc.nextLine();
                    
                    System.out.println("---------------------------------------");
                    boolean status = db.insert(nim,nama,alamat,semester,sks,ipk);
                    if (status==true) {
                        System.out.println("DATA BARU BERHASIL DI TAMBAHKAN");                       
                    }else{
                        System.out.println("NIM"+nim+"Nim sudah ada di dalam Batabase");
                        System.out.println("GAGAL MENAMBAHKAN DATA BARU");
                    }
                    System.out.println("---------------------------------------");
                    break;
                    
                    
                case "R":
                    System.out.println("INFO : Anda masuk menu Read");
                    db.view();
                    break;
                    
                    
                case "U":
                    System.out.println("INFO : Anda masuk menu Update");
                    db.view();
                    System.out.println("Input Key (Nim Mahasiswa yang akan anda Update):");
                    String key = sc.nextLine();
                    int index = db.search(key);
                    if (index >= 0) {
                        System.out.println("Anda akan menguodate data " + db.getData().get(index));
                        System.out.println("---------------------------------------");
                        System.out.println("MENU INPUT DATA BARU");

                        System.out.println("NIM                :");
                        nim = sc.nextLine();

                        System.out.println("NAMA MAHASISWA     :");
                        nama = sc.nextLine();

                        System.out.println("ALAMAT             :");
                        alamat = sc.nextLine();

                        System.out.println("SEMESTER           :");
                        semester = sc.nextInt();

                        System.out.println("SKS                :");
                        sks = sc.nextInt();

                        System.out.println("IPK                :");
                        ipk = sc.nextDouble();
                        sc.nextLine();

                        System.out.println("---------------------------------------");
                        status = db.update(index, nim, nama, alamat, semester, sks, ipk);
                        if (status==true) {
                            System.out.println("DATA ANDA BERHASIL DI PERBAHARUI");                            
                        }else{
                            System.err.println("DATA ANDA GAGAL DI PERBAHARUI");
                        }
                        System.out.println("---------------------------------------");
                    }else{
                        System.err.println("Mahasiswa dengan NIM : " + key + "Tidak ada di dalam Database Putu");
                    }
                    break;
                    
                    
                case "D":
                    System.out.println("INFO : Anda masuk menu Delate");
                    db.view();
                    System.out.println("Input Key (NIM Mahasiswa yang akan anda hapus) :");
                    key = sc.nextLine();
                    index = db.search(key);
                    if (index >= 0) {
                        System.out.println("ANDA YAKIN AKAN MENGHAPUS DATA" + db.getData().get(index));
                        System.out.println("Pilih : ");
                        pilihan = sc.nextLine();
                        if (pilihan.equalsIgnoreCase("Y")) {
                            status = db.deletee(index);
                            if (status==true) {
                                System.out.println("DATA BRHASIL DIHAPUS");                                
                            }else{
                                System.out.println("DATA GAGAL DIHAPUS");
                            }
                        }
                    }else{
                        System.err.println("Mahasiswa Dengan NIM :" + key + "Tidak ada didalam Database Putu");
                    }
                    break;
                    
                    
                case "X":
                    System.out.println("INFO : Anda memilih Menu EXIT?");
                    System.out.println("APAKAH ANDA YAKIN KELUAR DARI PROGRAM INI? Y/N");
                    System.out.println("Pilih : ");
                    pilihan = sc.nextLine();
                    if (pilihan.equalsIgnoreCase("Y")){
                        System.exit(0);
                    }
                    break;
            }
        }
    
}
}
