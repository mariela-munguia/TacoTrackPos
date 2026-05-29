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
    private static final String RED = "\u001B[31m";
    private static final String WHITE = "\u001B[37m";
    private static final String GREEN = "\u001B[32m";
    private static final String RESET = "\u001B[0m";

    private Scanner scanner = new Scanner(System.in);

    public void display() {
        boolean running = true;

        while (running) {
            printLogo();

            System.out.println("1) View Menu");
            System.out.println("2) New Order");
            System.out.println("0) Exit");

            int choice = readInt("Choose an option: ");

            switch (choice) {
                case 1:
                    showMenu();
                    if (readYesNo("Ready to start an order? (y/n): ")) {
                        orderScreen();
                    }
                    break;
                case 2:
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

    private void printLogo() {
        System.out.println();
        System.out.println(RED + "████████╗ █████╗  ██████╗ ██████╗" + RESET);
        System.out.println(WHITE + "╚══██╔══╝██╔══██╗██╔════╝██╔═══██╗" + RESET);
        System.out.println(GREEN + "   ██║   ███████║██║     ██║   ██║" + RESET);
        System.out.println(RED + "   ██║   ██╔══██║██║     ██║   ██║" + RESET);
        System.out.println(WHITE + "   ██║   ██║  ██║╚██████╗╚██████╔╝" + RESET);
        System.out.println(GREEN + "   ╚═╝   ╚═╝  ╚═╝ ╚═════╝ ╚═════╝" + RESET);
        System.out.println();
        System.out.println(RED + "        Taco" + WHITE + "Track " + GREEN + "POS" + RESET);
        System.out.println("==================================");
    }

    private void showMenu() {
        System.out.println("\n=== TacoTrack POS Menu ===");

        System.out.println("\nBase Prices:");
        System.out.println("Single Taco: $3.50");
        System.out.println("3-Taco Plate: $9.00");
        System.out.println("Burrito: $8.50");

        System.out.println("\nShell Options:");
        System.out.println("Corn, Flour, Hard Shell, Bowl");

        System.out.println("\nMeats:");
        System.out.println("Carne Asada, Al Pastor, Carnitas, Pollo, Chorizo, Pescado");
        System.out.println("Single: $1.00 | 3-Taco: $2.00 | Burrito: $3.00");
        System.out.println("Extra Meat: $0.50 | $1.00 | $1.50");

        System.out.println("\nCheeses:");
        System.out.println("Queso Fresco, Oaxaca, Cotija, Cheddar");
        System.out.println("Single: $0.75 | 3-Taco: $1.50 | Burrito: $2.25");
        System.out.println("Extra Cheese: $0.30 | $0.60 | $0.90");

        System.out.println("\nIncluded Toppings:");
        System.out.println("Lettuce, Cilantro, Onions, Tomatoes, Jalapenos, Radishes");
        System.out.println("Pico de Gallo, Guacamole, Corn");

        System.out.println("\nIncluded Sauces:");
        System.out.println("Salsa Verde, Salsa Roja, Chipotle, Habanero, Mild, Extra Hot");

        System.out.println("\nOther Products:");
        System.out.println("Small Drink: $2.00");
        System.out.println("Medium Drink: $2.50");
        System.out.println("Large Drink: $3.00");
        System.out.println("Chips & Salsa: $1.50");
        System.out.println();
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
                    ordering = !checkout(order);
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

    private boolean checkout(Order order) {
        System.out.println("\n=== Checkout ===");

        if (order.getItemCount() == 0) {
            System.out.println("You cannot checkout with an empty order.");
            return false;
        }

        System.out.println(order);

        boolean confirm = readYesNo("Confirm order and create receipt? (y/n): ");

        if (confirm) {
            order.saveReceipt();
            System.out.println("Receipt created.");
        } else {
            System.out.println("Order canceled.");
        }

        return true;
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
