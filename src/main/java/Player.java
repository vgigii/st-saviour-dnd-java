// The Player class stores all character information.
// This includes name, role, stats, and custom roll methods.

public class Player {

    // Basic information
    public String name;
    public String role;

    // Stats
    public int dexterity;
    public int charisma;
    public int constitution;

    // Constructor sets the player's name and role
    public Player(String name, String role) {
        this.name = name;
        this.role = role;
    }

    // Basic attack roll using dexterity
    public int attackRoll() {
        int roll = (int)(Math.random() * 20) + 1; // d20 roll
        int modifier = dexterity / 2; // simple beginner-friendly modifier
        System.out.println(this.name + " the " + this.role + " makes an attack roll!");
        return roll + modifier;
    }

    // Advantage: roll two dice, take the highest
    public int rollWithAdvantage() {
        int r1 = (int)(Math.random() * 20) + 1;
        int r2 = (int)(Math.random() * 20) + 1;
        System.out.println("Rolling with --Advantage--: " + r1 + " and " + r2);
        return Math.max(r1, r2);
    }

    // Disadvantage: roll two dice, take the lowest
    public int rollWithDisadvantage() {
        int r1 = (int)(Math.random() * 20) + 1;
        int r2 = (int)(Math.random() * 20) + 1;
        System.out.println("Rolling with --Disadvantage--: " + r1 + " and " + r2);
        return Math.min(r1, r2);
    }

    // Guidance: adds an extra d4 to a roll
    public int applyGuidance(int roll) {
        int d4 = (int)(Math.random() * 4) + 1;
        System.out.println("Guidance adds +" + d4 + "!");
        return roll + d4;
    }

    // Constitution save for resisting effects
    public int constitutionSave() {
        int roll = (int)(Math.random() * 20) + 1; // d20 roll
        int modifier = constitution / 2; 
        System.out.println(this.name + " rolls a Constitution save!");
        return roll + modifier;
    }
}