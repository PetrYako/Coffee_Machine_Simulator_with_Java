package machine;

import java.util.Arrays;
import java.util.Scanner;

public class CoffeeMachine {
    private int water;
    private int milk;
    private int beans;
    private int cups;
    private int money;

    public CoffeeMachine() {
        this.water = 400;
        this.milk = 540;
        this.beans = 120;
        this.cups = 9;
        this.money = 550;
    }

    public enum CoffeeType {
        ESPRESSO(1, 250, 0, 16, 4),
        LATTE(2, 350, 75, 20, 7),
        CAPPUCCINO(3, 200, 100, 12, 6);

        private final int choice;
        private final int waterNeeded;
        private final int milkNeeded;
        private final int beansNeeded;
        private final int price;

        CoffeeType(int choice, int waterNeeded, int milkNeeded, int beansNeeded, int price) {
            this.choice = choice;
            this.waterNeeded = waterNeeded;
            this.milkNeeded = milkNeeded;
            this.beansNeeded = beansNeeded;
            this.price = price;
        }

        public static CoffeeType getTypeByChoice(int choice) {
            return Arrays.stream(values()).filter(type -> type.choice == choice).findFirst().orElseThrow();
        }
    }

    public void buy(CoffeeType coffeeType) {
        switch (coffeeType) {
            case ESPRESSO:
                if (canMakeCoffee(coffeeType)) {
                    water -= coffeeType.waterNeeded;
                    beans -= coffeeType.beansNeeded;
                    cups--;
                    money += coffeeType.price;
                }
                break;
            case LATTE, CAPPUCCINO:
                if (canMakeCoffee(coffeeType)) {
                    water -= coffeeType.waterNeeded;
                    milk -= coffeeType.milkNeeded;
                    beans -= coffeeType.beansNeeded;
                    cups--;
                    money += coffeeType.price;
                }
                break;
            default:
                System.out.println("Invalid coffee type");
        }
    }

    private boolean canMakeCoffee(CoffeeType coffeeType) {
        if (water < coffeeType.waterNeeded) {
            System.out.println("Sorry, not enough water!");
            return false;
        }
        if (milk < coffeeType.milkNeeded) {
            System.out.println("Sorry, not enough milk!");
            return false;
        }
        if (beans < coffeeType.beansNeeded) {
            System.out.println("Sorry, not enough coffee beans!");
            return false;
        }
        if (cups < 1) {
            System.out.println("Sorry, not enough disposable cups!");
            return false;
        }
        return true;
    }

    public void fill(int addWater, int addMilk, int addBeans, int addCups) {
        water += addWater;
        milk += addMilk;
        beans += addBeans;
        cups += addCups;
    }

    public void take() {
        System.out.println("I gave you $" + money);
        money = 0;
    }

    public void printState() {
        System.out.println("\nThe coffee machine has:");
        System.out.println(water + " ml of water");
        System.out.println(milk + " ml of milk");
        System.out.println(beans + " g of coffee beans");
        System.out.println(cups + " disposable cups");
        System.out.println("$" + money + " of money\n");
    }

    public enum Action {
        BUY,
        FILL,
        TAKE;

        public static Action getActionByName(String name) {
            return Arrays.stream(values()).filter(action -> action.name().equalsIgnoreCase(name)).findFirst().orElseThrow();
        }
    }

    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        Scanner scanner = new Scanner(System.in);

        coffeeMachine.printState();
        System.out.println("Write action (buy, fill, take):");
        Action action = Action.getActionByName(scanner.nextLine());

        switch (action) {
            case BUY:
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                String choice = scanner.nextLine();
                if (!choice.equals("back")) {
                    coffeeMachine.buy(CoffeeType.getTypeByChoice(Integer.parseInt(choice)));
                }
                break;
            case FILL:
                System.out.println("Write how many ml of water you want to add:");
                int addWater = Integer.parseInt(scanner.nextLine());
                System.out.println("Write how many ml of milk you want to add:");
                int addMilk = Integer.parseInt(scanner.nextLine());
                System.out.println("Write how many grams of coffee beans you want to add:");
                int addBeans = Integer.parseInt(scanner.nextLine());
                System.out.println("Write how many disposable cups you want to add:");
                int addCups = Integer.parseInt(scanner.nextLine());
                coffeeMachine.fill(addWater, addMilk, addBeans, addCups);
                break;
            case TAKE:
                coffeeMachine.take();
                break;
            default:
                System.out.println("Invalid action");
        }

        coffeeMachine.printState();
    }
}
