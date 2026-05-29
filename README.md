<div align="center">

# TacoTrack POS 

###  A Java Point-of-Sale Ordering System for a Taco Shop 🌮

<p>
  <span style="color:red;"><strong></strong></span> •
  <span style="color:white;"><strong></strong></span> •
  <span style="color:green;"><strong></strong></span>
</p>

```text
████████╗ █████╗  ██████╗ ██████╗
╚══██╔══╝██╔══██╗██╔════╝██╔═══██╗
   ██║   ███████║██║     ██║   ██║
   ██║   ██╔══██║██║     ██║   ██║
   ██║   ██║  ██║╚██████╗╚██████╔╝
   ╚═╝   ╚═╝  ╚═╝ ╚═════╝ ╚═════╝
        T A C O T R A C K   P O S



# TacoTrack POS

TacoTrack POS is a Java console-based point of sale application for a custom taco truck/shop. The application allows customers to view the menu, create a taco/burrito order, add drinks, add chips and salsa, remove items, checkout, and save a receipt.

## Project Description
This shop currently takes orders on paper. This application helps automate that process by allowing customers to build custom taco orders through a menu-driven Java program.

Customers can order:

- Single tacos
- 3-taco plates
- Burritos
- Drinks
- Chips & salsa

Customers can also choose tortillas, toppings, sauces, premium toppings, extra toppings, and whether the item is specialized.

## Features

- View menu before ordering
- Start a new order
- Add tacos or burritos
- Choose shell/tortilla
- Choose taco size
- Add meats
- Add cheese
- Add regular toppings
- Add sauces
- Add drinks
- Add chips & salsa
- Remove items before checkout
- View order total
- Confirm or cancel checkout
- Save receipt files in a `receipts` folder

## Application Screens

### Home Screen

```text
1) View Menu
2) New Order
0) Exit
```

### Order Screen

```text
1) Add Taco/Burrito
2) Add Drink
3) Add Chips & Salsa
4) Checkout
5) Remove Item
0) Cancel Order
```

### Add Item Screen

The add item screen asks the user to choose:

- Shell/tortilla
- Item size
- Meat toppings
- Premium toppings
- Other toppings
- Sauces
- Whether the item is specialized

### Checkout Screen

The checkout screen displays:

- All ordered items
- Item prices
- Total price
- Confirm or cancel option

## Folder Structure

```text
TacoTrackPos/
│
├── src/
│   └── main/
│       └── java/
│           │
│           ├── com/
│           │   └── TacoTrackPos/
│           │       └── Main.java
│           │
│           ├── ui/
│           │   └── UserInterface.java
│           │
│           ├── models/
│           │   ├── Order.java
│           │   ├── Tacos.java
│           │   ├── Drink.java
│           │   └── ChipsAndSalsa.java
│           │
│           └── enums/
│               ├── TacoSize.java
│               ├── Tortilla.java
│               ├── Toppings.java
│               ├── ToppingsCategory.java
│               └── DrinkSize.java
│
├── receipts/
│
└── README.md
```

## Folder Setup Diagram

```mermaid
flowchart TD
    A["TacoTrackPos"] --> B["src"]
    B --> C["main"]
    C --> D["java"]

    D --> E["com.TacoTrackPos"]
    E --> F["Main.java"]

    D --> G["ui"]
    G --> H["UserInterface.java"]

    D --> I["models"]
    I --> J["Order.java"]
    I --> K["Tacos.java"]
    I --> L["Drink.java"]
    I --> M["ChipsAndSalsa.java"]

    D --> N["enums"]
    N --> O["TacoSize.java"]
    N --> P["Tortilla.java"]
    N --> Q["Toppings.java"]
    N --> R["ToppingsCategory.java"]
    N --> S["DrinkSize.java"]

    A --> T["receipts"]
    A --> U["README.md"]
```

## Class Diagram

```mermaid
classDiagram
    class Main {
        +main(String[] args)
    }

    class UserInterface {
        -Scanner scanner
        +display()
        -showMenu()
        -orderScreen()
        -addTaco() Tacos
        -addDrink() Drink
        -addChipsAndSalsa() ChipsAndSalsa
        -removeItem(Order order)
        -checkout(Order order) boolean
        -chooseTacoSize() TacoSize
        -chooseTortilla() Tortilla
        -chooseDrinkSize() DrinkSize
        -readInt(String prompt) int
        -readString(String prompt) String
        -readYesNo(String prompt) boolean
    }

    class Order {
        -ArrayList~Object~ items
        +addItem(Object item)
        +getItemCount() int
        +removeItem(int index) boolean
        +getTotal() double
        +saveReceipt()
        +toString() String
    }

    class Tacos {
        -TacoSize size
        -Tortilla tortilla
        -ArrayList~Toppings~ toppings
        -ArrayList~Boolean~ extras
        -boolean specialized
        +addTopping(Toppings topping, boolean extra)
        +setSpecialized(boolean specialized)
        +getPrice() double
        +toString() String
    }

    class Drink {
        -DrinkSize size
        -String flavor
        +getPrice() double
        +toString() String
    }

    class ChipsAndSalsa {
        -String salsaType
        +getPrice() double
        +toString() String
    }

    class TacoSize {
        <<enum>>
        SINGLE
        THREE_TACO_PLATE
        BURRITO
    }

    class Tortilla {
        <<enum>>
        CORN
        FLOUR
        HARD_SHELL
        BOWL
    }

    class Toppings {
        <<enum>>
        CARNE_ASADA
        AL_PASTOR
        CARNITAS
        POLLO
        CHORIZO
        PESCADO
        QUESO_FRESCO
        OAXACA
        COTIJA
        CHEDDAR
        LETTUCE
        CILANTRO
        ONIONS
        TOMATOES
        JALAPENOS
        RADISHES
        PICO_DE_GALLO
        GUACAMOLE
        CORN
        SALSA_VERDE
        SALSA_ROJA
        CHIPOTLE
        HABANERO
        MILD
        EXTRA_HOT
    }

    class ToppingsCategory {
        <<enum>>
        MEAT
        CHEESE
        REGULAR
        SAUCE
    }

    class DrinkSize {
        <<enum>>
        SMALL
        MEDIUM
        LARGE
    }

    Main --> UserInterface
    UserInterface --> Order
    UserInterface --> Tacos
    UserInterface --> Drink
    UserInterface --> ChipsAndSalsa
    Order --> Tacos
    Order --> Drink
    Order --> ChipsAndSalsa
    Tacos --> TacoSize
    Tacos --> Tortilla
    Tacos --> Toppings
    Toppings --> ToppingsCategory
    Drink --> DrinkSize
```

## How To Run

1. Open the project in IntelliJ.
2. Make sure the project folder is opened at the `TacoTrackPos` root.
3. Go to:

```text
src/main/java/com/TacoTrackPos/Main.java
```

4. Run the `Main` class.

## Receipt Files

When a customer confirms an order, the application creates a receipt file inside the `receipts` folder.

Receipt files are named using the date and time:

```text
yyyyMMdd-HHmmss.txt
```

Example:

```text
20260529-091530.txt
```

## Pricing

### Base Prices

| Item | Price |
|---|---:|
| Single Taco | $3.50 |
| 3-Taco Plate | $9.00 |
| Burrito | $8.50 |

### Premium Toppings

| Topping Type | Single | 3-Taco | Burrito |
|---|---:|---:|---:|
| Meat | $1.00 | $2.00 | $3.00 |
| Extra Meat | $0.50 | $1.00 | $1.50 |
| Cheese | $0.75 | $1.50 | $2.25 |
| Extra Cheese | $0.30 | $0.60 | $0.90 |

### Other Products

| Item | Price |
|---|---:|
| Small Drink | $2.00 |
| Medium Drink | $2.50 |
| Large Drink | $3.00 |
| Chips & Salsa | $1.50 |

## OOP Concepts Used

This project uses object-oriented programming concepts:

- Classes to represent order items
- Enums to store fixed menu options
- Encapsulation with private fields and public methods
- Methods to separate user interface logic, pricing logic, and receipt logic
- Multiple objects working together to build a complete order
