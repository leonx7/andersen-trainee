package com.andersen.shop;

import com.andersen.shop.annotations.ExpiryDate;
import com.andersen.shop.model.Basket;
import com.andersen.shop.model.Catalog;
import com.andersen.shop.model.Product;
import com.andersen.shop.model.ProductWithExpiryDate;
import com.andersen.shop.model.User;
import com.andersen.shop.model.Warehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;

public class ProgramEngine {
    private User user = new User();
    private Basket basket = new Basket(user.getUuid());
    private static Scanner input = new Scanner(System.in);
    private static Warehouse warehouse = new Warehouse();

    private static final Logger logger = LogManager.getLogger(ProgramEngine.class);

    public void run(Catalog catalog) throws IOException {
        loadBasket();
        logger.info("Program started");
        System.out.println("--- Welcome to Online Shopping System ---");
        System.out.println("\n\n 1) Go to shop  2) Go to warehouse");
        int selection = input.nextInt();
        switch (selection) {
            case 1:
                purchaseProcessing(catalog);
                break;
            case 2:
                warehouseProcessing(catalog);
                break;
        }
    }

    private void purchaseProcessing(Catalog catalog) throws IOException {
        while (true) {
            System.out.println("\n\n 1) Show products  2) Add to basket  3) Remove from basket " +
                    " 4) Show your order  5) Clean the basket  6) Exit");
            int selection = input.nextInt();
            switch (selection) {
                case 1:
                    displayProductsFromCatalog(catalog);
                    continue;
                case 2:
                    addToBasket();
                    continue;
                case 3:
                    deleteFromBasket();
                    continue;
                case 4:
                    basket.display(catalog);
                    continue;
                case 5:
                    basket.clear();
                    continue;
                case 6:
                    serializeBasket();
                    exit();
            }
        }
    }

    private void warehouseProcessing(Catalog catalog) {
        while (true) {
            System.out.println("\n\n 1) Add product 2) Display products");
            int selection = input.nextInt();
            switch (selection) {
                case 1:
                    addToWarehouse(catalog);
                    break;
                case 2:
                    displayProductsFromWarehouse(catalog);
                    break;
            }
        }
    }

    private void displayProductsFromCatalog(Catalog catalog) {
        Map<Integer, Product> products = catalog.getProducts();
        for (Integer integer : products.keySet()) {
            Product product = products.get(integer);
            System.out.printf("%-4s %-10s %-6s %-3s%n", product.getId(), product.getName(),
                    countPrice(product.getPrice(), product.getCurrency().getCode()), "rub");
        }
    }

    private void displayProductsFromWarehouse(Catalog catalog) {
        for (Map.Entry<Integer, Integer> entry : warehouse.getItems().entrySet()) {
            Product product = catalog.getProduct(entry.getKey());
            if (product instanceof ProductWithExpiryDate) {
                System.out.printf("%-4s %-10s %-3s %-6s %-20s%n", product.getId(), product.getName(), entry.getValue(), "pcs", ((ProductWithExpiryDate) product).getExpiryDate());
            } else {
                System.out.printf("%-4s %-10s %-3s %-6s%n", product.getId(), product.getName(), entry.getValue(), "pcs");
            }
        }
    }

    private void addToBasket() {
        int quantity;
        int productId;
        System.out.print("Product Id: ");
        productId = input.nextInt();
        System.out.print("Quantity: ");
        quantity = input.nextInt();
        basket.add(productId, quantity);
    }

    private void addToWarehouse(Catalog catalog) {
        int quantity;
        int productId;
        System.out.print("Product Id: ");
        productId = input.nextInt();
        System.out.print("Quantity: ");
        quantity = input.nextInt();
        Product product = catalog.getProduct(productId);
        if (product instanceof ProductWithExpiryDate) {
            ProductWithExpiryDate p = (ProductWithExpiryDate) product;
            int year, month, day;
            System.out.print("Year: ");
            year = input.nextInt();
            System.out.print("Month: ");
            month = input.nextInt();
            System.out.print("Day: ");
            day = input.nextInt();
            addExpiryDate(p, year, month, day);
        }
        warehouse.add(productId, quantity);
    }

    private void addExpiryDate(ProductWithExpiryDate product, int year, int month, int day) {
        for (Field field : product.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(ExpiryDate.class)) {
                product.setExpiryDate(LocalDate.of(year, month, day));
            }
        }
    }

    private void deleteFromBasket() {
        int productId;
        System.out.println("Product id: ");
        productId = input.nextInt();
        basket.delete(productId);
    }

    private void serializeBasket() throws IOException {
        try (FileOutputStream outputStream = new FileOutputStream("C:\\Users\\Lenovo\\Desktop\\save.ser");
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
            objectOutputStream.writeObject(basket);
        }
    }

    private void deserializeBasket() throws IOException, ClassNotFoundException {
        try (FileInputStream fileInputStream = new FileInputStream("C:\\Users\\Lenovo\\Desktop\\save.ser");
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            basket = (Basket) objectInputStream.readObject();
        }
    }

    //Load the basket data saved in the file system
    private void loadBasket() {
        try {
            deserializeBasket();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Compute a sale price based on purchase price and purchase currency
    private Double countPrice(Double price, String currencyCode) {
        Double salePrice = null;
        switch (currencyCode) {
            case "BYN":
                salePrice = price * 1.2;
                break;
            case "USD":
                salePrice = price * 1.4;
                break;
        }
        return salePrice;
    }

    private void exit() {
        logger.info("Program finished");
        System.exit(0);
    }
}
