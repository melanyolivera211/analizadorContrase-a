/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package analyzer;

import java.util.List;
import java.util.Map;

public class PasswordReport {
    private int length;
    private double lengthScore;
    private boolean hasUppercase;
    private boolean hasLowercase;
    private boolean hasDigits;
    private boolean hasSpecialChars;
    private double entropy;
    private String bruteForceTime;
    private boolean breached;
    private List<String> weakPatterns;
    private double overallScore;
    private PasswordStrength strength;
    
    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public double getLengthScore() {
        return lengthScore;
    }

    public void setLengthScore(double lengthScore) {
        this.lengthScore = lengthScore;
    }

    public boolean isHasUppercase() {
        return hasUppercase;
    }

    public void setHasUppercase(boolean hasUppercase) {
        this.hasUppercase = hasUppercase;
    }

    public boolean isHasLowercase() {
        return hasLowercase;
    }

    public void setHasLowercase(boolean hasLowercase) {
        this.hasLowercase = hasLowercase;
    }

    public boolean isHasDigits() {
        return hasDigits;
    }

    public void setHasDigits(boolean hasDigits) {
        this.hasDigits = hasDigits;
    }

    public boolean isHasSpecialChars() {
        return hasSpecialChars;
    }

    public void setHasSpecialChars(boolean hasSpecialChars) {
        this.hasSpecialChars = hasSpecialChars;
    }

    public double getEntropy() {
        return entropy;
    }

    public void setEntropy(double entropy) {
        this.entropy = entropy;
    }

    public String getBruteForceTime() {
        return bruteForceTime;
    }

    public void setBruteForceTime(String bruteForceTime) {
        this.bruteForceTime = bruteForceTime;
    }

    public boolean isBreached() {
        return breached;
    }

    public void setBreached(boolean breached) {
        this.breached = breached;
    }

    public List<String> getWeakPatterns() {
        return weakPatterns;
    }

    public void setWeakPatterns(List<String> weakPatterns) {
        this.weakPatterns = weakPatterns;
    }

    public double getOverallScore() {
        return overallScore;
    }

    public void setOverallScore(double overallScore) {
        this.overallScore = overallScore;
    }

    public PasswordStrength getStrength() {
        return strength;
    }

    public void setStrength(PasswordStrength strength) {
        this.strength = strength;
    }

    

}
