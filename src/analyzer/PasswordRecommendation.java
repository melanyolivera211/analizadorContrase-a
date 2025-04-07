/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package analyzer;

import java.util.Random;

/**
 *
 * @author Rafael Barragán Acevedo
 */
public class PasswordRecommendation {

    public static String enhancePassword(String password) {
        // Agregar caracteres especiales y números aleatorios
        String specialChars = "!@#$%^&*()";
        Random random = new Random();
        StringBuilder enhancedPassword = new StringBuilder(password);

        // Agregar un carácter especial al principio y al final
        char specialCharStart = specialChars.charAt(random.nextInt(specialChars.length()));
        char specialCharEnd = specialChars.charAt(random.nextInt(specialChars.length()));
        enhancedPassword.insert(0, specialCharStart);
        enhancedPassword.append(specialCharEnd);

        // Agregar un número aleatorio al medio
        int randomNumber = random.nextInt(100); // Número aleatorio entre 0 y 99
        int middleIndex = enhancedPassword.length() / 2;
        enhancedPassword.insert(middleIndex, randomNumber);

        return enhancedPassword.toString();
    }
}
