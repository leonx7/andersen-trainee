package com.andersen.shop;

import com.andersen.shop.model.Basket;
import com.andersen.shop.model.Catalog;

import java.util.Scanner;

public class ProgramEngine {
    private static Scanner input = new Scanner(System.in);
    private static int selection;
    private static Basket basket = new Basket();
    private static int id;

    public static void run(Catalog catalog) {
        System.out.println("--- Welcome to Online Shopping System ---");

        while (true) {
            System.out.println("\n\n 1) Show products  2) Add to basket  3) Remove from basket " +
                    " 4) Show your order  5) Clean the basket");
            selection = input.nextInt();
            switch (selection) {
                case 1:
                    catalog.display();
                    continue;
                case 2:
                    addToBasket(catalog);
                    continue;
                case 3:
                    deleteFromBasket();
                    continue;
                case 4:
                    basket.display(catalog);
                    continue;
                case 5:
                    basket.clear();
            }
        }
    }

    public static void addToBasket(Catalog catalog) {
        int quantity;

        System.out.print("Product Id: ");
        id = input.nextInt();

        System.out.print("Quantity: ");
        quantity = input.nextInt();

        basket.add(id, quantity);
    }

    public static void deleteFromBasket() {
        System.out.println("Product id: ");
        id = input.nextInt();
        basket.delete(id);
    }
}
