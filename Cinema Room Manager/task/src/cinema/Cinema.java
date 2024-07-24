package cinema;

import java.util.Arrays;
import java.util.Scanner;

public class Cinema {
    static int count = 0;
    static int money = 0;
    static int result = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();

        int[][] seatInfo = new int[rows][seats];


        int price;
        if (rows * seats < 60) {
            price = 10;
            result = rows * seats * price;
        } else if (rows % 2 == 0) {
            price = 9;
            result = rows * seats * price;
        } else {
            result = rows / 2 * seats * 10;
            result += (rows + 1) / 2 * seats * 8;
        }

        boolean exit = false;
        while(!exit){
            System.out.println();
            System.out.printf(
                    """
                    1. Show the seats
                    2. Buy a ticket
                    3. Statistics
                    0. Exit
                    """
            );

            int which = scanner.nextInt();
            switch (which){
                case 1:
                    cinema(rows, seats, seatInfo);
                    break;
                case 2:
                    buyTicket(rows, seats, seatInfo);
                    break;
                case 3:
                    statistics(rows, seats);
                    break;
                case 0:
                    exit = true;
            }
        }
    }
    public static void cinema (int rows, int seats, int[][] seatInfo){
        System.out.println();
        System.out.println("Cinema: ");
        System.out.print("  ");
        for (int i = 1; i <= seats; i++){
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 1; i <= rows; i++){
            System.out.print(i + " ");
            for (int j = 1; j <= seats; j++){
                if (seatInfo[i - 1][j - 1] == 1){
                    System.out.print("B ");
                } else{
                    System.out.print("S ");
                }
            }
            System.out.println();
        }

    }
    public static void buyTicket(int rows, int seats, int[][] seatInfo) {
        Scanner scanner = new Scanner(System.in);

        int price;
        int tempRow = 0;
        int tempSeat = 0;
        boolean valid = false;

        while (!valid) {
            System.out.println();
            System.out.println("Enter a row number:");
            tempRow = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            tempSeat = scanner.nextInt();

            if (tempRow > seatInfo.length || tempRow < 1) {
                System.out.println("Wrong input!");
            } else if (tempSeat > seatInfo[tempRow - 1].length || tempSeat < 1) {
                System.out.println("Wrong input!");
            } else {
                if (seatInfo[tempRow - 1][tempSeat - 1] == 1) {
                    System.out.println("That ticket has already been purchased!");
                } else {
                    valid = true;
                }

            }
        }

            seatInfo[tempRow - 1][tempSeat - 1] = 1;
            System.out.println();

            if (rows * seats < 60) {
                price = 10;
            } else if (rows % 2 == 0) {
                price = 9;
            } else {
                if (tempRow > (rows / 2)) {
                    price = 8;
                } else {
                    price = 10;
                }
            }
            System.out.print("Ticket price: $");
            System.out.println(price);
            money += price;
            count++;

    }
    public static void statistics(int rows, int seats){
        System.out.println();
        System.out.println("Number of purchased tickets: " + count);
        double percentage = (double) count / (rows * seats) * 100;
        System.out.printf("Percentage: %.2f%%", percentage);
        System.out.println();
        System.out.println("Current income: $" + money);
        System.out.println("Total income: $" + result);
    }
}