import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Scanner;
public class FlatUse {
    static EntityManagerFactory emf;
    static EntityManager em;

    public static void addFlat(Scanner sc){
        System.out.println("Enter your flat district: ");
        String district = sc.nextLine();
        System.out.println("Enter your flat address: ");
        String address = sc.nextLine();
        System.out.println("Enter your flat square: ");
        int square = Integer.parseInt(sc.nextLine());
        System.out.println("Enter your flat price: ");
        long price = Long.parseLong(sc.nextLine());

        em.getTransaction().begin();
        try{
            Flat f = new Flat(district,address,square,price);
            em.persist(f);
            em.gerTransaction().commit();
            System.out.println(f.getId());
        }catch(Exception ex){
            em.getTransaction().rollback();
        }
    }

    public static void deleteFlat(Scanner sc){
        System.out.println("Enter your flat id: ");
        String s = sc.nextLine();
        long id = Long.parseLong(s);

        Flat f = em.getReference(Flat.class, id);
        if(f == null){
            System.out.println("Such flat no found!");
            return;
        }
        em.getTransaction().begin();
        try {
            em.remove(f);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    public static void showFlats(){
        emf.Persistence.createEntityManagerFactory("");
        em = emf.createEntityManager();
        Query query = em.createQuery("SELECT s FROM FLATS s",Flat.class);
        List<Flat> list = (List<Flat>) query.getResultList();

        for (Flat f:list) {
        System.out.println(f);
        }
        public static void showChosenFlats(){
            System.out.println("Choose your flat parameter: ");
            System.out.println("1: by square");
            System.out.println("2: by price");
            System.out.println(">>>");

            Scanner sc = new Scanner(System.in);
            final String param = sc.nextLine();
            switch(param){
                case "1":
                    bySquare(sc);
                    break;
                case "2":
                    byPrice(sc);
                    break;
            }
        }

        public static void bySquare(Scanner sc){
            System.out.println("Enter your min square: ");
            int minSquare = Integer.parseInt(sc.nextLine());
            System.out.println("Enter your max square: ");
            int maxSquare = Integer.parseInt(sc.nextLine());

            Query query = em.createQuery("FROM FLAT f WHERE f.square>:minSquare AND f.square<:max.Square")
                    .setParameter("minSquare",minSquare);
                    .setParameter("maxSquare", maxSquare);
            List<Flat> list = (List<Flat>) query.getResultList();
            if (list.isEmpty()) {
                System.out.println("There are no such flats!");
            } else {
                System.out.printf("Your flats are with square between " + minSquare + " and " + maxSquare + ":\n");
                for (Flat f : list)
                    System.out.println(f);
            }
        }

        private static void byPrice(Scanner sc){
            System.out.println("Enter your minimal price:");
            final long minPrice = sc.nextLong();
            System.out.println("Enter your maximum price:");
            final long maxPrice = sc.nextLong();

            Query query = em.createQuery("FROM Flat f WHERE f.price>=:minPrice AND f.price<=:maxPrice")
                    .setParameter("minPrice", minPrice)
                    .setParameter("maxPrice", maxPrice);
            List<Flat> list = (List<Flat>) query.getResultList();
            if (list.isEmpty()) {
                System.out.println("Such flats found!");
            } else {
                System.out.printf("Your flats are with price between" + minPrice + "and" + maxPrice);
                for (Flat f : list)
                    System.out.println(f);
        }
    }
}
