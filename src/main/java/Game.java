

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

// Main class, runs the game
public class Game {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Collect character name and role
        System.out.print("Enter your character name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your role (Wizard, Knight, etc.): ");
        String role = scanner.nextLine();

        // Create a player
        Player player = new Player(name, role);

        // Collect stats
        System.out.print("Enter Dexterity (1-20): ");
        player.dexterity = scanner.nextInt();

        System.out.print("Enter Charisma (1-20): ");
        player.charisma = scanner.nextInt();

        System.out.print("Enter Constitution (1-20): ");
        player.constitution = scanner.nextInt();

        scanner.nextLine(); 

        // Character sheet
        System.out.println("\n===== CHARACTER SHEET =====");
        System.out.println("Name: " + player.name);
        System.out.println("Role: " + player.role);
        System.out.println("Dexterity: " + player.dexterity);
        System.out.println("Charisma: " + player.charisma);
        System.out.println("Constitution: " + player.constitution);
        System.out.println("============================\n");

        // Start adventure
        printDramaticText("Our adventure begins in a shady tavern ...");

        // Generate a monster
        int requiredRoll = generateMonster();

        System.out.println("\nPress ENTER to attack!!");
        scanner.nextLine();

        // Player attack roll
        int attack = player.attackRoll();

        // Ask for Guidance bonus: player gets to choose
        System.out.println("\nDo you want to use Guidance? (y/n)");
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("y")) {
            attack = player.applyGuidance(attack);
        }

        System.out.println("Your total attack roll: " + attack);

        // Win or Lose
        if (attack >= requiredRoll) {
            printDramaticText("Your strike is true!! The monster falls.");
        } else {
            printDramaticText("Your attack misses...");
            printDramaticText("The creature lunges toward you!");

            int conSave = player.constitutionSave();
            System.out.println("You attempt a Constitution save: " + conSave);

            if (conSave > 12) {
                printDramaticText("You resist the blow and escape!");
            } else {
                printDramaticText("You stumble... the darkness takes you...");
            }
        }

        printDramaticText("\nTHE END.");

        scanner.close();
    }

    // Prints text slowly for dramatic effect
    public static void printDramaticText(String text) {
        int delay = 50; // milliseconds per character
        for (char c : text.toCharArray()) {
            System.out.print(c);
            try {
                TimeUnit.MILLISECONDS.sleep(delay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();
    }

    // Generates a monster and returns the roll required to defeat it
    public static int generateMonster() {
        int r = (int)(Math.random() * 6) + 1;

        if (r <= 3) {
            String zombie = Character.toString(0x1F9DF);
            System.out.println("++++++++ " + zombie + " A HORDE OF ZOMBIES " + zombie + " ++++++++");
            System.out.println("+                                        +");
            System.out.println("+           roll required:  8            +");
            System.out.println("+                                        +");
            System.out.println("++++++++++++++++++++++++++++++++++++++++++");
            return 8;
        } else if (r <= 5) {
            String mask = Character.toString(0x1F3AD);
            System.out.println("++++++++++ " + mask + " DISGUISED MIMIC " + mask + " +++++++++");
            System.out.println("+                                        +");
            System.out.println("+           roll required:  12           +");
            System.out.println("+                                        +");
            System.out.println("++++++++++++++++++++++++++++++++++++++++++");
            return 12;
        } else {
            String eye = Character.toString(0x1F441);
            System.out.println("+++++++++++ " + eye + " EVIL BEHOLDER " + eye + " ++++++++++++");
            System.out.println("+                                        +");
            System.out.println("+           roll required:  18           +");
            System.out.println("+                                        +");
            System.out.println("++++++++++++++++++++++++++++++++++++++++++");
            return 18;
        }
    }
}
