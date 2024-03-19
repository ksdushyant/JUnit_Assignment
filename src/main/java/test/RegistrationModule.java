package test;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationModule {
    public static final Map<String, String> registeredUsers = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Forgot Password");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    registerUser(scanner);
                    break;
                case 2:
                    loginUser(scanner);
                    break;
                case 3:
                    forgotPassword(scanner);
                    break;
                case 4:
                    System.out.println("Exiting program. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    public static void registerUser(Scanner scanner) {
        System.out.print("Enter your email address: ");
        String email = scanner.nextLine();

        if (!isValidEmail(email)) {
            System.out.println("Invalid email address format. Registration failed.");
            return;
        }

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        registeredUsers.put(email, password);
        System.out.println("Registration successful.");
    }

    public static void loginUser(Scanner scanner) {
        System.out.print("Enter your email address: ");
        String email = scanner.nextLine();

        if (!registeredUsers.containsKey(email)) {
            System.out.println("Email not registered. You can register or choose 'Forgot Password' option.");
            return;
        }

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        if (registeredUsers.get(email).equals(password)) {
            System.out.println("Login successful.");
        } else {
            System.out.println("Login unsuccessful. Invalid email or password.");
        }
    }

    public static void forgotPassword(Scanner scanner) {
        System.out.print("Enter your registered email address: ");
        String email = scanner.nextLine();

        if (!registeredUsers.containsKey(email)) {
            System.out.println("Email not registered. Please register first.");
            return;
        }

        System.out.println("An email with instructions for resetting your password has been sent to " + email);
    }

    public static boolean isValidEmail(String email) {
        String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
