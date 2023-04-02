import javax.persistence.*;
import java.util.Scanner;

public class Main {
    static EntityManagerFactory emf;
    static EntityManager em;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            while (true) {
                System.out.println("1: add your flat");
                System.out.println("2: delete your flat");
                System.out.println("3: view all flats");
                System.out.println("4: view flats by parameters");
                System.out.print(">>>");

                String s = sc.nextLine();
                switch (s) {
                    case "1" -> addFlat(sc);
                    case "2" -> deleteFlat(sc);
                    case "3" -> showFlats();
                    case "4" -> showChosenFlats();
                    default -> {
                        return;
                    }
                }
            }
        } finally {
            sc.close();
            em.close();
            emf.close();
        }
    } catch(Exception ex)
    {ex.printStackTrace();
    }
}



