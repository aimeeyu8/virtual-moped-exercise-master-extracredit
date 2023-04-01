package edu.nyu.cs;

import java.util.Arrays;
import java.util.Random;

/**
 * A virtual moped, roaming the streets of New York.
 * The signatures of a few methods are given and must be completed and used as
 * indicated.
 * Create as many additional properties or methods as you want, as long as the
 * given methods behave as indicated in the instructions.
 * Follow good object-oriented design, especially the principles of abstraction
 * (i.e. the black box metaphor) and encapsulation (i.e. methods and properties
 * belonging to specific objects), as we have learned them.
 * The rest is up to you.
 */
public class Moped {
    private String mopedName;
    private int street;
    private int avenue;
    private int gas;
    private String orientation;
    private boolean drunk = false;

    public Moped(String name, boolean isDrunk) {
        this.mopedName = name;
        this.drunk = isDrunk;
        this.street = 10;
        this.avenue = 5;
        this.gas = 100;
        this.orientation = "South";
    }

    public Moped(String name) {
        this(name, false);
    }
    public boolean isDrunk() {
        return this.drunk;
    }
    public String getName() {
        return this.mopedName;
    }
    /**
     * Sets the orientation of the moped to a particular cardinal direction.
     * 
     * @param orientation A string representing which cardinal direction at which to
     *                    set the orientation of the moped. E.g. "north", "south",
     *                    "east", or "west".
     */
    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    /**
     * Returns the current orientation of the moped, as a lowercase String.
     * E.g. "north", "south", "east", or "west".
     * 
     * @return The current orientation of the moped, as a lowercase String.
     */
    public String getOrientation() {
        return this.orientation.toLowerCase();
    }

    /**
     * Prints the current location, by default exactly following the format:
     * Now at 12th St. and 5th Ave, facing South.
     *
     * If the current location is associated with location-based advertising, this
     * method should print exactly following format:
     * Now at 12th St. and 4th Ave, facing West. Did you know The Strand has 18
     * Miles of new, used and rare books, and has been in business since 1927?
     * 
     * Note that the suffixes for the numbers must be correct: i.e. the "st" in
     * "1st", "nd" in "2nd", "rd" in "3rd", "th" in "4th", etc, must be correct.
     */
    public void printLocation() {
        StringBuilder builder = new StringBuilder();
        builder.append(mopedName).append(" is now at ");
        builder.append(street).append(getSuffix(street)).append(" St. and ");
        builder.append(avenue).append(getSuffix(avenue)).append(" Ave, facing ").append(orientation).append(".");
        builder.append(" ").append(getAds());
        System.out.println(builder.toString());
    }

    public void drunkDrive() {
        if (!drunk) {
            return;
        }
        int move = new Random().nextInt(4) + 1;
        if (gas < 5) {
            fillGas();
        }
        switch (move) {
            case 1:
                goLeft();
                break;
            case 2:
                goRight();
                break;
            case 3:
                goStraight();
                break;
            case 4:
                goBackwards();
                break;
        }
    }
    public void checkCrash(Moped drunkMoped) {
        if (drunk || !drunkMoped.isDrunk()) {
            return;
        }
        int[] drunkLocation = drunkMoped.getLocation();
        if (street == drunkLocation[0] && avenue == drunkLocation[1]) {
            System.out.println("You crashed with " + drunkMoped.getName());
            System.exit(1);
        } 
    }
    private String getAds() {
        if (street == 12 && avenue == 4) {
            return "Did you know The Strand has 18 Miles of new, used and rare books, and has been in business since 1927?";
        } else if (street == 56 && avenue == 3) {
            return "At Tina’s Cuban, we believe that great cooking starts with a simple philosophy: slow cooking served fast and fresh!";
        } else if (street == 74 && avenue == 1) {
            return "The people of Memorial Sloan Kettering Cancer Center (MSK) are united by a singular mission: ending cancer for life.";
        } else if (street == 79 && avenue == 8) {
            return "The American Museum of Natural History is one of the world’s preeminent scientific and cultural institutions!";
        } else if (street == 15 && avenue == 8) {
            return "We have reached Xi'an Famous Foods.  Enjoy your noodles.";
        } else {
            return "";
        }
    }

    /**
     * returns suffix to match number
     * 
     * @param number
     * @return
     */
    private String getSuffix(int number) {
        int ones = number % 10;
        if (number == 11 || number == 12 || number == 13) {
            return "th";
        } else if (ones == 1) {
            return "st";
        } else if (ones == 2) {
            return "nd";
        } else if (ones == 3) {
            return "rd";
        } else {
            return "th";
        }
    }

    /**
     * Handles the command, `go left`.
     * Moves the moped one block to the left, and causes the moped to face the
     * appropriate new cardinal direction.
     * Consumes gas with each block moved, and doesn't move or turn unless there is
     * sufficient gas, as according to the instructions.
     * If attempting to drive off the map, the moped will turn but not move a block.
     * Turns-only consume no gas.
     * This method must not print anything.
     */
    public void goLeft() {
        if (this.gas >= 5) {

            if (getOrientation().equals("south")) {
                if (avenue > 1) {
                    avenue--;
                    gas -= 5;
                }
                orientation = "East";
            } else if (getOrientation().equals("east")) {
                if (street < 200) {
                    street++;
                    gas -= 5;
                }
                orientation = "North";
            } else if (getOrientation().equals("north")) {
                if (avenue < 10) {
                    avenue++;
                    gas -= 5;
                }
                orientation = "West";
            } else if (getOrientation().equals("west")) {
                if (street > 1) {
                    street--;
                    gas -= 5;
                }
                orientation = "South";
            }
        }
    }

    /**
     * Handles the command, `go right`.
     * Moves the moped one block to the right, and causes the moped to face the
     * appropriate new cardinal direction.
     * Consumes gas with each block moved, and doesn't move or turn unless there is
     * sufficient gas, as according to the instructions.
     * If attempting to drive off the map, the moped will turn but not move a block.
     * Turns-only consume no gas.
     * This method must not print anything.
     */
    public void goRight() {
        if (this.gas >= 5) {

            if (getOrientation().equals("south")) {
                if (avenue < 10) {
                    avenue++;
                    gas -= 5;
                }
                orientation = "West";
            } else if (getOrientation().equals("west")) {
                if (street < 200) {
                    street++;
                    gas -= 5;
                }
                orientation = "North";
            } else if (getOrientation().equals("north")) {
                if (avenue > 1) {
                    avenue--;
                    gas -= 5;
                }
                orientation = "East";
            } else if (getOrientation().equals("east")) {
                if (street > 1) {
                    street--;
                    gas -= 5;
                }
                orientation = "South";
            }
        }
    }

    /**
     * Handles the command,`straight on`.
     * Moves the moped one block straight ahead.
     * Consumes gas with each block moved, and doesn't move unless there is
     * sufficient gas, as according to the instructions.
     * This method must not print anything.
     */
    public void goStraight() {
        if (this.gas >= 5) {

            if (getOrientation().equals("south")) {
                if (street > 1) {
                    street--;
                    gas -= 5;
                }
            } else if (getOrientation().equals("west")) {
                if (avenue < 10) {
                    avenue++;
                    gas -= 5;
                }
            } else if (getOrientation().equals("north")) {
                if (street < 200) {
                    street++;
                    gas -= 5;
                }
            } else if (getOrientation().equals("east")) {
                if (avenue > 1) {
                    avenue--;
                    gas -= 5;
                }
            }
        }
    }

    /**
     * Handles the command,`back up`.
     * Moves the moped one block backwards, but does not change the cardinal
     * direction the moped is facing.
     * Consumes gas with each block moved, and doesn't move unless there is
     * sufficient gas, as according to the instructions.
     * This method must not print anything.
     */
    public void goBackwards() {
        if (this.gas >= 5) {

            if (getOrientation().equals("south")) {
                if (street < 200) {
                    street++;
                    gas -= 5;
                }
            } else if (getOrientation().equals("west")) {
                if (avenue > 1) {
                    avenue--;
                    gas -= 5;
                }
            } else if (getOrientation().equals("north")) {
                if (street > 1) {
                    street--;
                    gas -= 5;
                }
            } else if (getOrientation().equals("east")) {
                if (avenue < 10) {
                    avenue++;
                    gas -= 5;
                }
            }
        }
    }

    /**
     * Handles the command,`how we doin'?`.
     * This method must not print anything.
     * 
     * @return The current gas level, as an integer from 0 to 100.
     */
    public int getGasLevel() {
        return this.gas;
    }

    /**
     * Prints the current gas level, by default exactly following the format:
     * The gas tank is currently 85% full.
     *
     * If the moped is out of gas, this method should print exactly following
     * format:
     * We have run out of gas. Bye bye!
     */
    public void printGasLevel() {
        if (this.gas == 0) {
            System.out.println("We have run out of gas.  Bye bye!");
        } else {
            System.out.println("The gas tank is currently " + this.gas + "% full.");
        }
    }

    /**
     * Handles the command, `fill it up`.
     * This method must not print anything.
     * Fills the gas level to the maximum.
     */
    public void fillGas() {
        this.gas = 100;
    }

    /**
     * Handles the command, `park`.
     * This causes the program to quit.
     * You can use System.exit(0); to cause a program to quit with status code 0,
     * which indicates a normal graceful exit.
     * (In case you were wondering, status code 1 represents quitting as a result of
     * an error of some kind).
     */
    public void park() {
        System.exit(0);
    }

    /**
     * Handles the command, `go to Xi'an Famous Foods`
     * Causes the moped to self-drive, block-by-block, to 8th Ave. and 15th St.
     * Consumes gas with each block, and doesn't move unless there is sufficient
     * gas, as according to the instructions.
     */
    public void goToXianFamousFoods() {
        int avenueDiff = 8 - avenue;
        int streetDiff = 15 - street;
        if (avenueDiff > 0) {
            setOrientation("West");
            for (int i = 0; i < avenueDiff; i++) {
                if (gas < 5) {
                    fillGas();
                }
                goStraight();
            }
        } else if (avenueDiff < 0) {
            setOrientation("East");
            for (int i = 0; i < -avenueDiff; i++) {
                if (gas < 5) {
                    fillGas();
                }
                goStraight();
            }
        }
        if (streetDiff > 0) {
            setOrientation("North");
            for (int i = 0; i < streetDiff; i++) {
                if (gas < 5) {
                    fillGas();
                }
                goStraight();
            }
        } else if (streetDiff < 0) {
            setOrientation("South");
            for (int i = 0; i < -streetDiff; i++) {
                if (gas < 5) {
                    fillGas();
                }
                goStraight();
            }
        }
    }

    /**
     * Generates a string, containing a list of all the user commands that the
     * program understands.
     * 
     * @return String containing commands that the user can type to control the
     *         moped.
     */
    public String getHelp() {
        return "go left, go right, straight on, back up, how we doin'?, fill it up, park, go to Xi'an Famous Foods, help";
    }

    /**
     * Sets the current location of the moped.
     * 
     * @param location an int array containing the new location at which to place
     *                 the moped, in the order {street, avenue}.
     */
    public void setLocation(int[] location) {
        this.street = location[0];
        this.avenue = location[1];
    }

    /**
     * Gets the current location of the moped.
     * 
     * @return The current location of the moped, as an int array in the order
     *         {street, avenue}.
     */
    public int[] getLocation() {
        int[] location = { this.street, this.avenue };
        return location;
    }

}
