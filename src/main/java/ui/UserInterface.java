package ui;

import enums.DrinkSize;
import enums.TacoSize;
import enums.Toppings;
import enums.ToppingsCategory;
import enums.Tortilla;
import models.ChipsAndSalsa;
import models.Drink;
import models.Order;
import models.Tacos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Scanner scanner = new Scanner(System.in);

    public void display() {
        boolean running = true;

        while (running) {
            System.out.println("\n=== TacoTrack POS ===");
            System.out.println("1) New Order");
            System.out.println("0) Exit");

            int choice = readInt("Choose an option: ");

            switch (choice) {
                case 1:
                    orderScreen();
                    break;
                case 0:
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private void orderScreen() {
        Order order = new Order();
        boolean ordering = true;

        while (ordering) {
            System.out.println("\n=== Order Screen ===");
            System.out.println(order);
            System.out.println("1) Add Taco/Burrito");
            System.out.println("2) Add Drink");
            System.out.println("3) Add Chips & Salsa");
            System.out.println("4) Checkout");
            System.out.println("5) Remove Item");
            System.out.println("0) Cancel Order");

            int choice = readInt("Choose an option: ");

            switch (choice) {
                case 1:
                    order.addItem(addTaco());
                    break;
                case 2:
                    order.addItem(addDrink());
                    break;
                case 3:
                    order.addItem(addChipsAndSalsa());
                    break;
                case 4:
                    checkout(order);
                    ordering = false;
                    break;
                case 5:
                    removeItem(order);
                    break;
                case 0:
                    System.out.println("Order canceled.");
                    ordering = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private Tacos addTaco() {
        System.out.println("\n=== Add Item Screen ===");

        Tortilla tortilla = chooseTortilla();
        TacoSize size = chooseTacoSize();

        Tacos taco = new Tacos(size, tortilla);

        addToppingsFromCategory(taco, ToppingsCategory.MEAT, "Meat");
        addToppingsFromCategory(taco, ToppingsCategory.CHEESE, "Premium Topping: Cheese");
        addToppingsFromCategory(taco, ToppingsCategory.REGULAR, "Other Toppings");
        addToppingsFromCategory(taco, ToppingsCategory.SAUCE, "Sauces");

        boolean specialized = readYesNo("Would you like the item specialized? (y/n): ");
        taco.setSpecialized(specialized);

        return taco;
    }

    private void addToppingsFromCategory(Tacos taco, ToppingsCategory category, String title) {
        List<Toppings> choices = new ArrayList<>();

        for (Toppings topping : Toppings.values()) {
            if (topping.getCategory() == category) {
                choices.add(topping);
            }
        }

        boolean adding = true;

        while (adding) {
            System.out.println("\n" + title);

            for (int i = 0; i < choices.size(); i++) {
                System.out.println((i + 1) + ") " + choices.get(i).getDisplayName());
            }

            System.out.println("0) Done");

            int choice = readInt("Choose an option: ");

            if (choice == 0) {
                adding = false;
            } else if (choice >= 1 && choice <= choices.size()) {
                Toppings selected = choices.get(choice - 1);

                boolean extra = false;

                if (selected.getCategory() == ToppingsCategory.MEAT
                        || selected.getCategory() == ToppingsCategory.CHEESE) {
                    extra = readYesNo("Make it extra? (y/n): ");
                }

                taco.addTopping(selected, extra);
                System.out.println("Added " + (extra ? "extra " : "") + selected.getDisplayName());
            } else {
                System.out.println("Invalid option.");
            }
        }
    }

    private Drink addDrink() {
        System.out.println("\n=== Add Drink ===");

        DrinkSize size = chooseDrinkSize();
        String flavor = readString("Enter drink flavor: ");

        return new Drink(size, flavor);
    }

    private ChipsAndSalsa addChipsAndSalsa() {
        System.out.println("\n=== Add Chips & Salsa ===");

        String salsaType = readString("Enter salsa type: ");

        return new ChipsAndSalsa(salsaType);
    }

    private void removeItem(Order order) {
        System.out.println("\n=== Remove Item ===");

        if (order.getItemCount() == 0) {
            System.out.println("There are no items to remove.");
            return;
        }

        System.out.println(order);

        int itemNumber = readInt("Enter item number to remove, or 0 to cancel: ");

        if (itemNumber == 0) {
            System.out.println("Remove canceled.");
        } else if (order.removeItem(itemNumber - 1)) {
            System.out.println("Item removed.");
        } else {
            System.out.println("Invalid item number.");
        }
    }

    private void checkout(Order order) {
        System.out.println("\n=== Checkout ===");

        if (order.getItemCount() == 0) {
            System.out.println("You cannot checkout with an empty order.");
            return;
        }

        System.out.println(order);

        boolean confirm = readYesNo("Confirm order and create receipt? (y/n): ");

        if (confirm) {
            order.saveReceipt();
            System.out.println("Receipt created.");
        } else {
            System.out.println("Order canceled.");
        }
    }

    private TacoSize chooseTacoSize() {
        TacoSize[] sizes = TacoSize.values();

        while (true) {
            System.out.println("\nItem size:");
            for (int i = 0; i < sizes.length; i++) {
                System.out.println((i + 1) + ") " + sizes[i].getDisplayName());
            }

            int choice = readInt("Choose size: ");

            if (choice >= 1 && choice <= sizes.length) {
                return sizes[choice - 1];
            }

            System.out.println("Invalid option.");
        }
    }

    private Tortilla chooseTortilla() {
        Tortilla[] tortillas = Tortilla.values();

        while (true) {
            System.out.println("\nSelect your shell:");
            for (int i = 0; i < tortillas.length; i++) {
                System.out.println((i + 1) + ") " + tortillas[i].getDisplayName());
            }

            int choice = readInt("Choose shell: ");

            if (choice >= 1 && choice <= tortillas.length) {
                return tortillas[choice - 1];
            }

            System.out.println("Invalid option.");
        }
    }

    private DrinkSize chooseDrinkSize() {
        DrinkSize[] sizes = DrinkSize.values();

        while (true) {
            System.out.println("\nDrink size:");
            for (int i = 0; i < sizes.length; i++) {
                System.out.println((i + 1) + ") " + sizes[i].getDisplayName());
            }

            int choice = readInt("Choose size: ");

            if (choice >= 1 && choice <= sizes.length) {
                return sizes[choice - 1];
            }

            System.out.println("Invalid option.");
        }
    }

    private int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }

    private String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private boolean readYesNo(String prompt) {
        while (true) {
            System.out.print(prompt);
            String answer = scanner.nextLine().trim().toLowerCase();

            if (answer.equals("y") || answer.equals("yes")) {
                return true;
            } else if (answer.equals("n") || answer.equals("no")) {
                return false;
            }

            System.out.println("Please enter y or n.");
        }
    }
}
