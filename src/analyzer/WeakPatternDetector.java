/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package analyzer;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class WeakPatternDetector {
    private static final List<Pattern> WEAK_PATTERNS = List.of(
        Pattern.compile("^[0-9]+$"), // Solo números
        Pattern.compile("^[a-zA-Z]+$"), // Solo letras
        Pattern.compile("(.)\\1{3,}"), // Caracteres repetidos
        Pattern.compile("(123|abc|qwerty|password|admin)"), // Patrones comunes
        Pattern.compile("^[a-zA-Z0-9]+$") // Sin caracteres especiales
    );
    
    public List<String> detectWeakPatterns(String password) {
        List<String> detectedPatterns = new ArrayList<>();
        
        for (Pattern pattern : WEAK_PATTERNS) {
            if (pattern.matcher(password).matches()) {
                detectedPatterns.add(pattern.pattern());
            }
        }
        
        return detectedPatterns;
    }
}
