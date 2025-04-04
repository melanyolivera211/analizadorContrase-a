package analyzer;
import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class BreachedPasswordChecker {
    private Set<String> breachedPasswords;

    public BreachedPasswordChecker(String filePath) {
        breachedPasswords = new HashSet<>();
        loadBreachedPasswords(filePath);
    }

    private void loadBreachedPasswords(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                breachedPasswords.add(line.trim());
            }
        } catch (IOException e) {
            System.err.println("Error cargando las contraseñas vulneradas: " + e.getMessage());
        }
    }

    public boolean isBreached(String password) {
        return breachedPasswords.contains(password);
    }
}