package models;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Order {
    private ArrayList<OrderItem> items;

    public Order() {
        items = new ArrayList<>();
    }

    public void addItem(OrderItem item) {
        items.add(0, item); // newest first
    }

    public double getTotal() {
        double total = 0;

        for (OrderItem item : items) {
            total += item.getPrice();
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
