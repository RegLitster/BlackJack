package com.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Deck deck = new Deck();

        homeScreen();
        deck.shuffle();

        ArrayList<String> playerNames = new ArrayList<>();
        ArrayList<Hand> playerHands = new ArrayList<>();


        System.out.println("Please Enter Number of Players: ");
        int playerCount = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < playerCount; i++) {
            System.out.println("Please Enter Player Name: ");
            String playerName = scanner.nextLine();
            playerNames.add(playerName);
            playerHands.add(new Hand());
        }

        for (int i = 0; i < 2; i++) {
            for (Hand hand : playerHands) {
                hand.deal(deck.deal());
            }
        }

        ArrayList<Integer> handValue = new ArrayList<>();
        for (int i = 0; i < playerCount; i++) {
            int value = playerHands.get(i).getValue();
            handValue.add(value);
            System.out.println(playerNames.get(i) + " Hand Is Worth: " + value+"\n");
        }
        hit(playerNames, playerHands, deck);
        scoring(playerNames, playerHands);

    }

    public static void homeScreen() {
        System.out.println("==== Welcome To BlackJack ====");
    }

    public static void scoring(ArrayList<String> playerNames, ArrayList<Hand> playerHands) {
        int highestScore = 0;
        ArrayList<String> winningPlayerNames = new ArrayList<>();

        for (int i = 0; i < playerNames.size(); i++) {
            int value = playerHands.get(i).getValue();

            if (value > 21) { //this skips a player with a score over 21
                continue;
            }

            if (value > highestScore) {
                highestScore = value;
                winningPlayerNames.clear();
                winningPlayerNames.add(playerNames.get(i));
            } else if (value == highestScore) {
                winningPlayerNames.add(playerNames.get(i));
            }
        }
        if (winningPlayerNames.size() == 1) {
            System.out.println(winningPlayerNames.get(0) + " Wins!");
        } else {
            System.out.println("It's a Push");
        }


    }

    public static void hit(ArrayList<String> playerNames, ArrayList<Hand> playerHands, Deck deck) {
        for (int i = 0; i < playerNames.size(); i++) {
            System.out.println(playerNames.get(i) + " Your Hand Is Worth "+playerHands.get(i).getValue()+ " Would you Like to Hit? Y/N");
            String playerChoice = scanner.nextLine().toUpperCase().trim();

            while (true) {
                if (playerChoice.equals("N")) {
                    System.out.println(playerNames.get(i) + " Hand Is Still Worth: " + playerHands.get(i).getValue());
                    break;

                } else if (playerChoice.equals("Y")) {
                    playerHands.get(i).deal(deck.deal());
                    int value = playerHands.get(i).getValue();
                    System.out.println(playerNames.get(i) + " Hand Is Now Worth: " + value);
                    if (value > 21) {
                        System.out.println("Bust!");
                        break;
                    }
                }
            }


        }


    }

}


