package machine;

import java.util.Scanner;

public class CoffeeMachine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Write how many cups of coffee you will need:");
        int cups = scanner.nextInt();
        
        int water = cups * 200;
        int milk = cups * 50;
        int beans = cups * 15;
        
        System.out.println("For " + cups + " cups of coffee you will need:");
        System.out.println(water + " ml of water");
        System.out.println(milk + " ml of milk");
        System.out.println(beans + " g of coffee beans");
    }
}
