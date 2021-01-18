package com.ruchi.app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import com.ruchi.models.RecommendedProduct;
import com.ruchi.utils.URLUtils;

public class CommandManager {

    // Method to print the main menu    
    void printMenu() {
        System.out.println("\n\nProduct Recommendation");
        System.out.println("\nMenu\n");
        System.out.println("1. Query about the product");
        System.out.println("2. Quit");
        System.out.print("Please select an option(enter a number) : ");
    }


    // Method to read command from command line 
    public String getUserInput() {
        String input = null;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            input = br.readLine();
        } catch (Exception e) {
            System.out.println("Something went wrong with the system input!!   Please try again.");
        }
        return input;
    }

    public int parseCommand(String strCommand) {
        int command = -1;
        if (strCommand == null || strCommand.length() == 0) {
            return command;
        }
        try {
            command = Integer.parseInt(strCommand);
        } catch (NumberFormatException nfe) {
            command = 0;
        }
        return command;
    }

    void startSystem() {
        URLUtils urlUtils = new URLUtils();
        RecommendationEngine re = new RecommendationEngine(urlUtils);
        while (true) {
            this.printMenu();
            String userInput = this.getUserInput();
            int command = this.parseCommand(userInput);
            if (command == 2) {
                System.exit(0);
            } else if (command == 1) {
                System.out.print("Please input the product to search : ");
                String userQuery = this.getUserInput();
                System.out.println("Getting recommended products...");
                List<RecommendedProduct> recommendedProducts = re.getRecommendedProducts(userQuery);
                printResults(recommendedProducts);
            } else {
                System.out.println("\n\tInvalid Option! Please try again.\n");
            }
        }
    }

    void printResults(List<RecommendedProduct> recommendedProducts) {
        if (recommendedProducts.size() == 0) {
            System.out.println("\n**No recommended products found. Please try with different product!!\n");
            return;
        }
        System.out.println("------------------------------------------------------------------------");
        System.out.println(" R e c o m m e n d e d   P r o d u c t s ");
        System.out.println("------------------------------------------------------------------------");
        System.out.format("No.  %-10s\t%-100s\n", "Item ID", "Item Name");
        System.out.println("------------------------------------------------------------------------");
        int cnt = 1;
        for (RecommendedProduct rp : recommendedProducts) {
            System.out.format("%3d%10d\t%-100s\n", cnt++, rp.getItemId(), rp.getItemName());
        }
        System.out.println("------------------------------------------------------------------------");
    }
}
