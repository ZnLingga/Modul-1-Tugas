import java.util.Scanner;

class Main {
    public static String[] idBuku = new String[] {"388c-e681-9152", "ed90-be30-5cdb", "d95e-0c4a-9523"};
    public static String[] namaBuku = new String[] {"title", "title", "title"};
    public static String[] author = new String[] {"author", "author", "author"};
    public static String[] category = new String[] {"Sejarah", "Sejarah", "Sejarah"};
    public static int[] stock = new int[] {4, 0, 2};
    public static String[] nim = new String[10];
    void Menu() {
        System.out.println("===== Library System =====");
        System.out.println("1. Login as Student");
        System.out.println("2. Login as Admin");
        System.out.println("3. Exit");
        System.out.print("Choose option (1-3): ");
    }

    void menuAdmin() {
        System.out.println("===== Admin Menu =====");
        System.out.println("1. Add Student");
        System.out.println("2. Display Registered Students");
        System.out.println("3. Logout");
        System.out.print("Choose option (1-3): ");
    }

    void menuStudent() {
        System.out.println("==== Student Menu ====");
        System.out.println("1. Buku terpinjam");
        System.out.println("2. Pinjam buku");
        System.out.println("3. Logout");
        System.out.print("Choose option (1-3): ");
    }

    public static void main(String[] args) {
        Student student = new Student();
        Admin admin = new Admin();
        Main main = new Main();
        mainMenu:
        while (true) {
            main.Menu();
            Scanner scanInt = new Scanner(System.in);
            int pilih = scanInt.nextInt();
            if (pilih == 1) {
                if (Student.checkStudent() == 0) {
                    continue mainMenu;
                }
                int j = 0;
                while (true) {
                    main.menuStudent();
                    pilih = scanInt.nextInt();
                    if (pilih == 1){
                        student.bukuTerpinjam();
                    } else if (pilih == 2){
                        student.pinjamBuku(j);
                        j++;
                    } else {
                        break;
                    }
                }
            } else if (pilih == 2) {
                if (admin.checkAdmin() == 0) {
                    continue mainMenu;
                }
                while (true) {
                    main.menuAdmin();
                    pilih = scanInt.nextInt();
                    int i = 0;
                    if (pilih == 1) {
                        admin.addStudent(i);
                        i++;
                    } else if (pilih == 2) {
                        admin.displayStudent(i);
                    } else {
                        break;
                    }
                }
            } else {
                break;
            }
        }
    }
}

class Student {
    public static String[] name = new String[10];
    public static String[] faculty = new String[10];
    public static String[] nim = new String[10];
    public static String[] programStudi = new String[10];
    public static String[] bukuTerpinjam = new String[10];

    static int checkStudent(){
        Scanner scanString = new Scanner (System.in);
        System.out.print("Enter your NIM: ");
        String input = scanString.nextLine();
        for (int i = 0; i < nim.length; i++) {
            if (input.equals(nim[i])) {
                System.out.println("Successful Login as Student\n");
                return 1;
            }
        }
        System.out.println("NIM Not Found.\n");
        return 0;
    }

    void logout() {
        System.out.println("System logout...");
    }

    void displayBooks() {
        System.out.println("====================================================================================================");
        System.out.println("|| No. || Id Buku\t\t\t || Nama Buku\t\t\t || Author\t\t || Category\t\t || Stock\t || ");
        for (int i = 0; true; i++) {
            Object[] idBuku = new Object[0];
            System.out.printf("|| %d   || %s\t\t || %s\t\t\t || %s\t\t || %s\t\t || %d\t\t || \n", i+1, idBuku[i], Main.namaBuku[i], Main.author[i], Main.category[i], Main.stock[i]);
            System.out.println("====================================================================================================");
        }

    }

    void pinjamBuku(int j){
        Main main = new Main();
        Scanner scanString = new Scanner (System.in);
        System.out.println("Input Id buku yang ingin dipinjam (input 99 untuk back)");
        System.out.print("Input: ");
        String idBuku = scanString.nextLine();
        for (int i = 0; i < main.idBuku.length; i++) {
            if (idBuku.equals(main.idBuku[i])) {
                System.out.println("Buku id " + idBuku + " berhasil dipinjam");
                main.stock[i] -= 0;
            } else if (idBuku.equals("99")) {
                System.out.println("Kembali ke menu awal...");
                break;
            }
        }
        bukuTerpinjam[j] = idBuku;
    }

    void bukuTerpinjam(){
        System.out.println("Buku terpinjam: ");
        for (int i = 0; i < bukuTerpinjam.length; i++) {
            if (bukuTerpinjam[i] == null) {
                break;
            } else {
                System.out.printf("%d. %s", i+1, bukuTerpinjam[i]);
            }
        }
    }
}

class Admin {
    Student student = new Student();
    public static String ADMIN_USERNAME = "admin";
    public static String ADMIN_PASSWORD = "123";

    int checkAdmin() {
        Scanner scanString = new Scanner(System.in);
        System.out.print("Enter your username (admin): ");
        String user = scanString.nextLine();
        System.out.print("Enter your password (admin): ");
        String pass = scanString.nextLine();
        if (user.equals(ADMIN_USERNAME) && pass.equals(ADMIN_PASSWORD)) {
            System.out.println("Successful Login as Admin\n");
            return 1;
        }
        else {
            System.out.println("Admin User Not Found!!\n");
            return 0;
        }
    }

    void displayStudent(int i){
        System.out.println("Data mahasiswa ke " + (i+1));
        System.out.println("Nama: " + Student.name[i]);
        System.out.println("Fakultas: " + Student.faculty[i]);
        System.out.println("nim: " + Student.nim[i]);
        System.out.println("prodi: " + Student.programStudi[i]);
    }

    void addStudent(int i){
        Scanner scanString = new Scanner(System.in);
        System.out.print("Enter student name: ");
        Student.name[i] = scanString.nextLine();

        System.out.print("Enter student faculty: ");
        Student.faculty[i] = scanString.nextLine();

        System.out.print("Enter student NIM: ");
        Student.nim[i] = scanString.nextLine();
        while(true){
            if (String.valueOf(Student.nim[i]).length() != 15 ) {
                System.out.print("NIM must be 15 digits!!!\n");
                System.out.print("Enter student NIM: ");
                student.nim[i] = scanString.nextLine();
            } else {
                break;
            }
        }

        System.out.print("Enter student program studi: ");
        Student.programStudi[i] = scanString.nextLine();

        System.out.print("Student data successfully added.\n");
    }
}