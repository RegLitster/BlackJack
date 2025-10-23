package com.pluralsight;

import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        Deck deck = new Deck();
        Hand player1Hand = new Hand();
        Hand player2Hand = new Hand();

        homeScreen();
        deck.shuffle();

        System.out.println("Please Enter Player1 Name");
        String player1 = scanner.nextLine();
        System.out.println("Please Enter Player2 Name");
        String player2 = scanner.nextLine();

        for (int i = 0; i < 2; i++) {

            player1Hand.deal(deck.deal());
            player2Hand.deal(deck.deal());
        }
        int handValue = player1Hand.getValue();
        System.out.println(player1+ " Hand is worth: " + handValue);

    }
    public static void homeScreen() {
        System.out.println("==== Welcome To BlackJack ====");
    }
}


