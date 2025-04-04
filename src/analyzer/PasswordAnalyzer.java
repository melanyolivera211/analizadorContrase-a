package analyzer;
import java.util.List;

public class PasswordAnalyzer {
    private static final int MIN_LENGTH = 8;
    private static final int STRONG_LENGTH = 12;
    
    private final BreachedPasswordChecker breachedPasswordChecker;
    private final WeakPatternDetector weakPatternDetector;
 public PasswordAnalyzer() {
        this.breachedPasswordChecker = new BreachedPasswordChecker("breached_passwords.txt"); 
        this.weakPatternDetector = new WeakPatternDetector();
    }
    public PasswordAnalyzer(String breachedPasswordsFile) {
        this.breachedPasswordChecker = new BreachedPasswordChecker(breachedPasswordsFile);
        this.weakPatternDetector = new WeakPatternDetector();
    }

    public PasswordReport analyze(String password) {
        PasswordReport report = new PasswordReport();

        // Longitud
        int length = password.length();
        report.setLength(length);
        report.setLengthScore(calculateLengthScore(length));

        // Diversidad de caracteres
        boolean hasUppercase = !password.equals(password.toLowerCase());
        boolean hasLowercase = !password.equals(password.toUpperCase());
        boolean hasDigits = password.matches(".*\\d.*");
        boolean hasSpecialChars = !password.matches("[A-Za-z0-9]*");

        report.setHasUppercase(hasUppercase);
        report.setHasLowercase(hasLowercase);
        report.setHasDigits(hasDigits);
        report.setHasSpecialChars(hasSpecialChars);

        // Entropía y fuerza bruta
        double entropy = calculateEntropy(password);
        report.setEntropy(entropy);
        report.setBruteForceTime(estimateBruteForceTime(entropy));

        // Contraseñas comprometidas
        boolean breached = breachedPasswordChecker.isBreached(password);
        report.setBreached(breached);

        // Patrones débiles
        List<String> weakPatterns = weakPatternDetector.detectWeakPatterns(password);
        report.setWeakPatterns(weakPatterns);

        // Puntuación general y fuerza
        double overallScore = calculateOverallScore(report);
        report.setOverallScore(overallScore);
        report.setStrength(determineStrength(overallScore));

        return report;
    }

    private double calculateLengthScore(int length) {
        if (length < MIN_LENGTH) return 0;
        if (length >= STRONG_LENGTH) return 1;
        return (double) (length - MIN_LENGTH) / (STRONG_LENGTH - MIN_LENGTH);
    }

    private double calculateEntropy(String password) {
        int poolSize = 0;
        if (password.matches(".*[a-z].*")) poolSize += 26;
        if (password.matches(".*[A-Z].*")) poolSize += 26;
        if (password.matches(".*\\d.*")) poolSize += 10;
        if (password.matches(".*[^a-zA-Z0-9].*")) poolSize += 32; // Aproximado de caracteres especiales
        
        return password.length() * Math.log(poolSize) / Math.log(2);
    }

 private String estimateBruteForceTime(double entropy) {
    // Suponiendo 10^12 intentos por segundo (supercomputadora)
    double seconds = Math.pow(2, entropy) / 1e12;

    if (seconds < 1) return "Menos de 1 segundo";
    
    int[] timeUnits = {60, 60, 24, 30, 12, 100}; // Segundos, Minutos, Horas, Días, Meses, Años
    String[] unitNames = {"segundo(s)", "minuto(s)", "hora(s)", "día(s)", "mes(es)", "año(s)"};

    int i = 0;
    while (seconds >= timeUnits[i] && i < timeUnits.length - 1) {
        seconds /= timeUnits[i];
        i++;
    }

    return String.format("%.2f %s", seconds, unitNames[i]);
}

    private double calculateOverallScore(PasswordReport report) {
        double score = 0;

        // Ponderaciones para la evaluación
        score += report.getLengthScore() * 0.3;
        score += (report.isHasUppercase() ? 0.1 : 0);
        score += (report.isHasLowercase() ? 0.1 : 0);
        score += (report.isHasDigits() ? 0.1 : 0);
        score += (report.isHasSpecialChars() ? 0.15 : 0);
        score += (report.getEntropy() > 60 ? 0.2 : report.getEntropy() / 300);
        score -= (report.isBreached() ? 0.4 : 0);
        score -= (report.getWeakPatterns().size() * 0.05);

        return Math.max(0, Math.min(1, score));
    }

    private PasswordStrength determineStrength(double score) {
        if (score < 0.3) return PasswordStrength.VERY_WEAK;
        if (score < 0.5) return PasswordStrength.WEAK;
        if (score < 0.7) return PasswordStrength.MODERATE;
        if (score < 0.9) return PasswordStrength.STRONG;
        return PasswordStrength.VERY_STRONG;
    }
}
