import javax.persistence.*;
import java.util.*;

public class MenuRestaurantUtils {
    static EntityManagerFactory emf;
    static EntityManager em;

    public static void openEm(){
        emf = Persistence.createEntityManagerFactory("JPA");
        em = emf.createEntityManager();
    }
    public static void closeEm(){
        em.close();
        emf.close();
    }

    public static void addDish(){
        Scanner sc = new Scanner(System.in);
        openEm();
        System.out.println("Input name: " );
        String name = sc.nextLine();
        System.out.println("Input price: ");
        String priceStr = sc.nextLine();
        Double price = Double.parseDouble(priceStr);
        System.out.println("Input weight (only in gramms): ");
        String weightStr = sc.nextLine();
        Double weight = Double.parseDouble(weightStr);
        System.out.println("Input discount Y or N: ");
        String discountStr = sc.nextLine();
        String discountS;
        if (discountStr.equals("Y".toLowerCase())){
            discountS = "true";
        }else{
            discountS = "false";
        }
        boolean isDiscountAvailible = Boolean.parseBoolean(discountS);

        em.getTransaction().begin();
        try{
            Dish d = new Dish(name,price,weight,isDiscountAvailible);
            em.persist(d);
            em.getTransaction().commit();
            System.out.println("Dish id is: " + d.getId());
        }catch(Exception ex){
            em.getTransaction().rollback();
        }
    }
    public static void byPrice(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Min price in $: ");
        String minPriceStr = sc.nextLine();
        Double minPrice = Double.parseDouble(minPriceStr);
        System.out.println("Max price in $: ");
        String maxPriceStr = sc.nextLine();
        Double maxPrice = Double.parseDouble(maxPriceStr);

        openEm();
        TypedQuery query = em.createQuery("SELECT d FROM Dish WHERE d.price > minPrice AND d.price < maxPrice", Dish.class);
        query.setParameter("minPrice",minPrice);
        query.setParameter("maxPrice",maxPrice);

        List<Dish> ListA = query.getResultList();

        for (Dish d:ListA) {
        System.out.println(d);
        }
    }
    public static void priceWithDiscount() {
        openEm();
        TypedQuery query = em.createQuery("SELECT d FROM Dish d WHERE d.isDiscountAvailible = true", Dish.class);
        List<Dish> ListA = query.getResultList();

        for (Dish d : ListA) {
            System.out.println(d);
        }
    }
        public static void lessThanKg() {
            final int maxWeight = 1000;

            openEm();
            TypedQuery query = em.createQuery("SELECT d FROM Dish d", Dish.class);
            List<Dish> list = query.getResultList();
            Set<Dish> menuSet = new HashSet<>();
            Random rdm = new Random();
            double weight;
            double totalWeight = 0;
            System.out.println("The menu of meal less then 1 kg: ");
            while (totalWeight <= maxWeight) {
                Dish randomDish = list.get(rdm.nextInt(list.size()));
                weight = randomDish.getWeight();
                if ((totalWeight + weight) < maxWeight) {
                    menuSet.add(randomDish);
                }
                totalWeight += weight;

            }
            totalWeight = 0;
            for (Dish d : menuSet) {
                totalWeight += d.getWeight();
            }
            for (Dish d : list) {
                if (!menuSet.contains(d) && d.getWeight() < (1000 - totalWeight)) {
                    menuSet.add(d);
                    totalWeight += d.getWeight();
                }
            }
            for (Dish d : menuSet) {
                System.out.println(d);
                System.out.println("Total weight is " + totalWeight + "g");

            }
        }

    }


