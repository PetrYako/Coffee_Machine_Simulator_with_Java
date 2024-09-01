package machine;

import java.util.Scanner;

public class CoffeeMachine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Write how many ml of water the coffee machine has:");
        int availableWater = scanner.nextInt();
        
        System.out.println("Write how many ml of milk the coffee machine has:");
        int availableMilk = scanner.nextInt();
        
        System.out.println("Write how many grams of coffee beans the coffee machine has:");
        int availableBeans = scanner.nextInt();
        
        System.out.println("Write how many cups of coffee you will need:");
        int requestedCups = scanner.nextInt();
        
        int maxCups = calculateMaxCups(availableWater, availableMilk, availableBeans);
        
        if (maxCups >= requestedCups) {
            int extraCups = maxCups - requestedCups;
            if (extraCups == 0) {
                System.out.println("Yes, I can make that amount of coffee");
            } else {
                System.out.println("Yes, I can make that amount of coffee (and even " + extraCups + " more than that)");
            }
        } else {
            System.out.println("No, I can make only " + maxCups + " cup(s) of coffee");
        }
    }
    
    public static int calculateMaxCups(int water, int milk, int beans) {
        int maxWater = water / 200;
        int maxMilk = milk / 50;
        int maxBeans = beans / 15;
        return Math.min(Math.min(maxWater, maxMilk), maxBeans);
    }
}
