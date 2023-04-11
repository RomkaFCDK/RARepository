import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        try{
            while(true){
                System.out.println("1: add your dish: ");
                System.out.println("2: filter dishes menu by price: ");
                System.out.println("3: show all dishes with discount: ");
                System.out.println("4: choose random menu less than 1 kg: ");
                System.out.print(">>>");

                String str = sc.nextLine();
                switch (str){
                    case "1":
                        MenuRestaurantUtils.addDish();
                        break;
                    case "2":
                        MenuRestaurantUtils.byPrice();
                        break;
                    case "3":
                        MenuRestaurantUtils.priceWithDiscount();
                        break;
                    case "4":
                        MenuRestaurantUtils.lessThanKg();
                        break;
                    default:
                        return;


                }
            }
        }finally {
            sc.close();
            MenuRestaurantUtils.closeEm();
        }
    }
}
