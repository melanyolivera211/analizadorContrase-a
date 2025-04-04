/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package analyzer;

public enum PasswordStrength {
    VERY_WEAK("Muy débil", "Rojo"),
    WEAK("Débil", "Naranja"),
    MODERATE("Moderada", "Amarillo"),
    STRONG("Fuerte", "Verde claro"),
    VERY_STRONG("Muy fuerte", "Verde oscuro");
    
    private final String description;
    private final String color;
    
    PasswordStrength(String description, String color) {
        this.description = description;
        this.color = color;
    }
    
    public String getDescription() { return description; }
    public String getColor() { return color; }
}
