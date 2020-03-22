package com.company;

/*

1627 Let's play

Three people play the game. Each player (Gamer) is characterized by two parameters: the name (name) and the number of actions per second (rating).
You need to bring the game in the console and determine the winner and losers.
So ...
1. Understand what the program does.
1.1. List <String> steps stores the sequence of actions that each player performs from 0 to the last.
1.2. isWinnerFound shows whether a winner is found or not.
1.3. The sleep method throws InterruptedException and takes a parameter of type long.
1.4. Players play independently.
2. Implement the logic of the run method so that for each player:
2.1. At regular intervals (1000ms / rating), the steps described in the steps were displayed in the console.
2.2. Any text should start with the player's last name (getName () method), followed by a colon, and then the text itself.
Example: [Ivanov: Start of game].
2.3. When a player completes all steps, he is considered the winner. Output [getName () + ": winner!"].
2.4. When a winner is found, the game stops and the other players are considered defeated. Print for them [getName () + ": lost"].

Requirements:
1. The run method of the Gamer class at equal intervals of time (1000ms / rating) should output to the console the player's surname (method getName ()), then a colon, and then text from OnlineGame.steps. Example: [Ivanov: Start of game].
2. When all game steps are completed and the winner is not yet found, the run method must set the OnlineGame.isWinnerFound flag to true (tell the others that it is the winner).
3. If the player wins, his run method should display [getName () +: winner!]. For example: [Sidorov: winner!].
4. Run methods of all players who did not win (were interrupted) should display [getName () +: lost]. For example: [Petrov: lost]
5. The run method should not throw an exception when interrupted.


 */



import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws InterruptedException {
        OnlineGame onlineGame = new OnlineGame();
        onlineGame.start();
    }

    public static class OnlineGame extends Thread {
        public static volatile boolean isWinnerFound = false;

        public static List<String> steps = new ArrayList<String>();

        static {
            steps.add("Начало игры");
            steps.add("Сбор ресурсов");
            steps.add("Рост экономики");
            steps.add("Убийство врагов");
        }

        protected Gamer gamer1 = new Gamer("Ivanov", 3);//3
        protected Gamer gamer2 = new Gamer("Petrov", 1);//1
        protected Gamer gamer3 = new Gamer("Sidorov", 5);//5

        public void run() {
            gamer1.start();
            gamer2.start();
            gamer3.start();

            while (!isWinnerFound) {
            }
            gamer1.interrupt();
            gamer2.interrupt();
            gamer3.interrupt();
        }
    }

    public static class Gamer extends Thread {
        private int rating;

        public Gamer(String name, int rating) {
            super(name);
            this.rating = rating;
        }

        @Override
        public void run() {
            //Add your code here - добавь код тут
            try {
                for (String action : OnlineGame.steps) {
                    System.out.println(getName() + ":" + action);
                    Thread.sleep(1000 / rating);
                }

                if (!OnlineGame.isWinnerFound)
                    System.out.println(getName() + ":победитель!");
                OnlineGame.isWinnerFound = true;

            } catch (InterruptedException e) {
                System.out.println(getName() + ":проиграл");
                return;
            }
        }
    }
}




