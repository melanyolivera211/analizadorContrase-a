/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package resources;
import interfaz.MainUI;
import javax.swing.SwingUtilities;

/**
 *
 * @author User
 */
public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainUI());
    }
}
