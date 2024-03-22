import java.security.SecureRandom; import java.util.HashMap; import java.util.Map;  public class PasswordManager {     private Map<String, String> passwords;     private SecureRandom random;      public PasswordManager() {         passwords = new HashMap<>();         random = new SecureRandom();     }      // Add a new password     public void addPassword(String website, String password) {         passwords.put(website, password);     }      // Retrieve password for a given website     public String getPassword(String website) {         return passwords.get(website);     }      // Update an existing password     public void updatePassword(String website, String newPassword) {         if (passwords.containsKey(website)) {             passwords.put(website, newPassword);         } else {             System.out.println("Website not found.");         }     }      // Remove password for a given website     public void removePassword(String website) {         if (passwords.containsKey(website)) {             passwords.remove(website);         } else {             System.out.println("Website not found.");         }     }      // Display all stored passwords     public void displayPasswords() {         if (passwords.isEmpty()) {             System.out.println("No passwords stored.");         } else {             System.out.println("Stored Passwords:");             for (Map.Entry<String, String> entry : passwords.entrySet()) {                 System.out.println("Website: " + entry.getKey() + ", Password: " + entry.getValue());             }         }     }      // Generate a strong password     public String generateStrongPassword(int length) {         String uppercaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";         String lowercaseLetters = "abcdefghijklmnopqrstuvwxyz";         String numbers = "0123456789";         String specialCharacters = "!@#$%^&*()-_+=";          String allCharacters = uppercaseLetters + lowercaseLetters + numbers + specialCharacters;          StringBuilder password = new StringBuilder();         for (int i = 0; i < length; i++) {             int index = random.nextInt(allCharacters.length());             password.append(allCharacters.charAt(index));         }          return password.toString();     }      public static void main(String[] args) {         PasswordManager passwordManager = new PasswordManager();          // Example usage         passwordManager.addPassword("example.com", "password123");         passwordManager.addPassword("google.com", "securePassword");         passwordManager.displayPasswords();          passwordManager.updatePassword("example.com", "newPassword123");         passwordManager.displayPasswords();          passwordManager.removePassword("google.com");         passwordManager.displayPasswords();          // Generate a strong password         String strongPassword = passwordManager.generateStrongPassword(12);         System.out.println("Generated strong password: " + strongPassword);     } }