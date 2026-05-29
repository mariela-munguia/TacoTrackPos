package models;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Order {
    private ArrayList<Object> items;

    public Order() {
        items = new ArrayList<>();
    }

    public void addItem(Object item) {
        items.add(0, item);
    }

    public int getItemCount() {
        return items.size();
    }

    public boolean removeItem(int index) {
        if (index >= 0 && index < items.size()) {
            items.remove(index);
            return true;
        }

        return false;
    }

    public double getTotal() {
        double total = 0;

        for (Object item : items) {
            if (item instanceof Tacos) {
                total += ((Tacos) item).getPrice();
            } else if (item instanceof Drink) {
                total += ((Drink) item).getPrice();
            } else if (item instanceof ChipsAndSalsa) {
                total += ((ChipsAndSalsa) item).getPrice();
            }
        }

        return total;
    }

    public void saveReceipt() {
        try {
            File folder = new File("receipts");

            if (!folder.exists()) {
                folder.mkdirs();
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
            String fileName = LocalDateTime.now().format(formatter) + ".txt";

            File receipt = new File(folder, fileName);
            FileWriter writer = new FileWriter(receipt);

            writer.write(toString());
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving receipt: " + e.getMessage());
        }
    }

    public String toString() {
        String result = "TacoTrack POS Receipt\n";
        result += "=====================\n";

        if (items.isEmpty()) {
            result += "No items ordered.\n";
        } else {
            for (int i = 0; i < items.size(); i++) {
                result += (i + 1) + ") " + items.get(i).toString() + "\n";
            }
        }

        result += "---------------------\n";
        result += "Total: $" + String.format("%.2f", getTotal()) + "\n";

        return result;
    }
}
