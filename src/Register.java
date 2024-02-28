import java.util.Scanner;

/**
 * Register is a basic Java program that simulates a simple registration system.
 * It prompts the user for a username, age, and password through a text-based interface,
 * enforcing specific rules for each input. The program performs error checking to ensure
 * the validity of the user-provided information. If any input fails to meet the criteria,
 * the registration process is terminated by using the statement System.exit(0);,
 * and an appropriate error message is displayed.
 *
 * Rules:
 * - Username must be between 4 and 20 characters.
 * - Age must be a valid integer between 18 and 120.
 * - Password must include at least one uppercase letter, one lowercase letter,
 *   and one digit.
 *
 * On successful registration, the program displays a confirmatory message.
 *
 * The main method initialises an instance of FUPRLoginSystem and executes the runApp() method
 * to start the registration verification process.
 */


public class Register {
    private final static int MIN_USER_NAME = 4; // Minimum length of username
    private final static int MAX_USER_NAME = 20; // Minimum length of username
    private final static int MIN_AGE = 18; // Minimum length of username
    private final static int MAX_AGE = 120; // Minimum length of username
    private final static int MIN_PASSWORD = 8; // Minimum length of username
    private Scanner scanner;

    public Register() {
        this.scanner = new Scanner(System.in);
    }

    public void runApp() {
        // Prompt user for username
        String username = promptForUsername();

        // Prompt user for age
        int age = promptForAge();

        // Prompt user for password
        String password = promptForPassword();

        // Display successful registration message
        System.out.printf("User name: %s%nAge: %d%nPassword: %s%n", username, age, getHiddenPassword(password));
    }

    /**
     * TASK ONE: Complete a valid method to check if a value is within a given range.
     * @param value
     * @param lower
     * @param upper
     * @return true if the value is within the range, false otherwise
     */
    private boolean betweenRange(int value, int lower, int upper) {
        return value >= lower && value <= upper;
    }

    /**
     * TASK TWO: Complete the method to check if an int entered using the Scanner object is valid.
     * Use any valid method - use a loop to keep prompting the user until a valid int is entered.
     * If the input is not a valid int, manage the error and display a relevant error message.
     * @return correct input
     */
    private int getValidInt1() {
        Integer input = null;
        while (input == null) {
            try {
                input = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(String.format("%s invalid input. Please enter a valid integer.", e));
            }
        }
        return input;
    }

    /**
     * Complete the method to check if an int entered using the Scanner object is valid.
     * Use any valid method - use a loop to keep prompting the user until a valid int is entered.
     * If the input is not a valid int, manage the error and display a relevant error message.
     * @return correct input
     */
    private int getValidInt2() {
        Integer input = null;
        while (input == null) {
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.printf("%s invalid input. Please enter a valid integer.%n", scanner.nextLine());
            }
        }
        return input;
    }

    /**
     * Complete the method to check if a password meets the required criteria.
     * @param password
     * @return true if the password meets the criteria, false otherwise
     */
    private boolean passwordOK(String password) {
        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasDigit = false;
        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                hasUppercase = true;
            } else if (Character.isLowerCase(ch)) {
                hasLowercase = true;
            } else if (Character.isDigit(ch)) {
                hasDigit = true;
            }
        }
        return  (hasUppercase && hasLowercase && hasDigit);
    }

    /**
     * Complete the method to return a hidden version of the password.
     * @param password
     * @return the hidden password
     */
    private String getHiddenPassword(String password) {
        String hiddenPassword = "";
        for (char ch : password.toCharArray()) {
            hiddenPassword += "*";
        }
        return hiddenPassword;
    }

    /**
     * Using the defined methods and constants appropriately to prompt the user for a username.
     * @return the username entered by the user
     */
    private String promptForUsername() {
        String username = "";
        while (!betweenRange(username.length(), MIN_USER_NAME, MAX_USER_NAME)) {
            System.out.printf("Enter your username (between %s and %s  characters):%n", MIN_USER_NAME, MAX_USER_NAME);
            username= scanner.nextLine();
            if (!betweenRange(username.length(), MIN_USER_NAME, MAX_USER_NAME)) {
                System.out.printf("%s is invalid, username length is %d.%n",
                        username, username.length());

            }
        }
        return username;
    }

    private int promptForAge() {
        int age = 0;
        while (!betweenRange(age, MIN_AGE, MAX_AGE)) {
            System.out.printf("Enter your age (between %s and %s):%n", MIN_AGE, MAX_AGE);
            age = getValidInt1();
            if (!betweenRange(age, MIN_AGE, MAX_AGE))
                System.out.printf("%d Invalid age.", age);
        }
        return age;
    }



    private String promptForPassword() {
        System.out.println("Enter your password (must have at least one uppercase, one lowercase, and one digit):");

        boolean passwordOK = false;
        String password = "";
        while (!passwordOK) {
            password = scanner.nextLine();
            if (password.length() >= MIN_PASSWORD) {
                passwordOK = passwordOK(password);
                if (!passwordOK) {
                    System.out.printf("%s. Invalid password. Registration failed.%n");
                }
            } else {
                System.out.printf("Password too short. Length is %d. It must be at least %d characters long.%n",
                        password.length(), MIN_PASSWORD);
            }
        }
        return password;
    }

    public static void main(String[] args) {
        Register loginSystem = new Register();
        loginSystem.runApp();
    }
}