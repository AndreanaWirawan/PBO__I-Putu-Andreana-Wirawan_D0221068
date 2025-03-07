import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {
    private ArrayList<Mahasiswa> data = new ArrayList<>();
    private final String filename = "src/data.csv";
    private final Path path = Path.of(filename);
    
    public void open() {
        try {
            List<String> lines = Files.readAllLines(path);
            data = new ArrayList<>();

            if (lines.isEmpty()) {
                System.out.println("File CSV kosong!");
                return;
            }

            for (int i = 1; i < lines.size(); i++) { // Melewati header (baris pertama)
                String line = lines.get(i).trim();

                if (line.isEmpty()) {
                    System.out.println("Baris kosong ditemukan, dilewati.");
                    continue;
                }

                // Gunakan titik koma sebagai pemisah!
                String[] element = line.split(";");

                if (element.length < 6) { 
                    System.out.println("Format data salah di baris " + (i + 1) + ": " + line);
                    continue;
                }

                try {
                    String nim = element[0].trim();
                    String nama = element[1].trim();
                    String alamat = element[2].trim();
                    int semester = Integer.parseInt(element[3].trim());
                    int sks = Integer.parseInt(element[4].trim());
                    double ipk = Double.parseDouble(element[5].trim());

                    Mahasiswa mhs = new Mahasiswa(nim, nama, alamat, semester, sks, ipk);
                    data.add(mhs);
                } catch (NumberFormatException e) {
                    System.out.println("Kesalahan format angka di baris " + (i + 1) + ": " + line);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void save(){
        StringBuilder sb = new StringBuilder();
        sb.append("NIM,NAMA,ALAMAT,SEMESTER,SKS,IPK\n");
        if (!data.isEmpty()){
            for (int i = 0; i <data.size(); i++) {
                Mahasiswa mhs = data.get(i);
                String line = mhs.getNim() + "," + mhs.getNama() + "," + mhs.getAlamat() + "," + mhs.getSemester() + "," + mhs.getSks() + "," + mhs.getSks() + "," + mhs.getIpk() + "\n";
                sb.append(line);
            }
        }
        try{
            Files.writeString(path,sb.toString());
        } catch (IOException e){
            throw new RuntimeException(e);
        }
        
    }
    public void view(){
        System.out.println("================================================================================");
        System.out.printf("| %-8.8S |", "NIM");
        System.out.printf(" %-20.20S |", "NAMA");
        System.out.printf(" %-20.20S |", "ALAMAT");
        System.out.printf(" %8.8S", "SEMESTER");
        System.out.printf("| %3.3S", "SKS");
        System.out.printf("| %4.4S |", "IPK");
        System.out.println();
        System.out.println("--------------------------------------------------------------------------------");
        for (Mahasiswa mhs : data){ 
            System.out.printf("| %-8S |", mhs.getNim());
            System.out.printf(" %-20.20S |", mhs.getNama());
            System.out.printf(" %-20.20S |", mhs.getAlamat());
            System.out.printf(" %8.8S", mhs.getSemester());
            System.out.printf("| %3.3S", mhs.getSks());
            System.out.printf("| %4.4S |", mhs.getIpk());
            System.out.println();
        }
        System.out.println("--------------------------------------------------------------------------------");
    }
    
    
   
    boolean insert(String nim, String nama, String alamat, int semester, int sks, double ipk){
        boolean status = true;
        if (!data.isEmpty()) {
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i).getNim().equalsIgnoreCase(nim)) {
                    status = false;
                    break;
                }
            }
            
        }
        if (status == true) {
            Mahasiswa mhs = new Mahasiswa(nim,nama,alamat,semester,sks,ipk);
            data.add(mhs);
            save();            
        }        
        return status;
    }
    
    
    
    public int search(String nim){
        int index = -1;
        if (!data.isEmpty()) {
            for (int i = 0; i <data.size(); i++) {
                if (data.get(i).getNim().equalsIgnoreCase(nim)) {
                    index = i;
                    break;                    
                }
                
            }
            
        }
        return index;
        
        
        
    }
    public boolean update (int index, String nim, String nama, String alamat, int semester, int sks, double ipk){
        boolean status = false;
        if (!data.isEmpty()) {
            if (index >= 0 && index < data.size()) {
                Mahasiswa mhs = new Mahasiswa (nim, nama, alamat, semester, sks, ipk);
                data.set(index, mhs);
                save();
                status = true;
        
        return status;
    }
            
        
    }
        return false;
}
    public boolean deletee(int index) {
      boolean status = false;
      if (!data.isEmpty()){
          data.remove(index);
          save(); 
          status = true;
      }

        return status;
}
}


