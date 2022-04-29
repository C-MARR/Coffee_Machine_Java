package machine;

import java.util.Scanner;

enum Inventory {

    WATER(400),
    MILK(540),
    BEANS(120),
    CUPS(9),
    MONEY(550);

    int quantity;

    Inventory(int quantity) {
        this.quantity = quantity;
    }
}

enum Drinks {

    ESPRESSO(250, 0, 16,4, 1),
    LATTE(350, 75, 20, 7, 1),
    CAPPUCCINO(200, 100, 12,6, 1);

    final int waterUsed;
    final int milkUsed;
    final int beansUsed;
    final int cost;
    final int cupsUsed = 1;

    Drinks(int waterUsed, int milkUsed, int beansUsed, int cost, int cupsUsed) {
        this.waterUsed = waterUsed;
        this.milkUsed = milkUsed;
        this.beansUsed = beansUsed;
        this.cost = cost;
    }
}

public class CoffeeMachine {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("Write action (buy, fill, take, remaining, exit): ");
            String input = scanner.next();
            if ("exit".equals(input)) {
                break;
            }
            switch (input) {
                case "buy":
                    while (true) {
                        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, " +
                                "3 - cappuccino, back - to main menu: ");
                        String option = scanner.next();
                        if ("1".equals(option)) {
                            buy(Drinks.ESPRESSO);
                            break;
                        } else if ("2".equals(option)) {
                            buy(Drinks.LATTE);
                            break;
                        } else if ("3".equals(option)) {
                            buy(Drinks.CAPPUCCINO);
                            break;
                        } else if ("back".equals(option)) {
                            break;
                        } else if ("exit".equals(option)) {
                            break;
                        } else {
                            System.out.println("Wrong Input!");
                        }
                    }
                    break;
                case "fill":
                    fill();
                    break;
                case "take":
                    System.out.printf("\nI gave you $%d\n", Inventory.MONEY.quantity);
                    Inventory.MONEY.quantity = 0;
                    break;
                case "remaining":
                    displayInventory();
                    break;
            }
        }
    }

    public static void buy(Drinks drink) {
        if (Inventory.WATER.quantity >= drink.waterUsed && Inventory.MILK.quantity >= drink.milkUsed &&
                Inventory.BEANS.quantity >= drink.beansUsed && Inventory.CUPS.quantity >= drink.cupsUsed) {
            System.out.println("I have enough resources, making you a coffee!");
            Inventory.WATER.quantity -= drink.waterUsed;
            Inventory.MILK.quantity -= drink.milkUsed;
            Inventory.BEANS.quantity -= drink.beansUsed;
            Inventory.MONEY.quantity += drink.cost;
            Inventory.CUPS.quantity -= drink.cupsUsed;
        } else {
            String missing;
            if (Inventory.WATER.quantity < drink.waterUsed) {
                missing = "water";
            } else if (Inventory.MILK.quantity < drink.milkUsed) {
                missing = "milk";
            } else if (Inventory.BEANS.quantity < drink.beansUsed) {
                missing = "beans";
            } else {
                missing = "cups";
            }
            System.out.printf("Sorry, not enough %s!\n\n", missing);
        }
    }

    public static void fill() {
        System.out.println("Write how many ml of water you want to add: ");
        Inventory.WATER.quantity += scanner.nextInt();
        System.out.println("Write how many ml of milk you want to add: ");
        Inventory.MILK.quantity += scanner.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add: ");
        Inventory.BEANS.quantity += scanner.nextInt();
        System.out.println("Write how many disposable cups of coffee you want to add: ");
        Inventory.CUPS.quantity += scanner.nextInt();
    }

    public static void displayInventory() {
        System.out.printf("\nThe coffee machine has:\n" +
                "%d ml of water\n" +
                "%d ml of milk\n" +
                "%d g of coffee beans\n" +
                "%d disposable cups\n" +
                "$%d of money\n\n",
                Inventory.WATER.quantity, Inventory.MILK.quantity, Inventory.BEANS.quantity,
                Inventory.CUPS.quantity, Inventory.MONEY.quantity);
    }

}