/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tarea.pkg1.pkg1;

/**
 *
 * @author gerar
 */
public class Tarea11 {

    public static void main(String[] args) {
        int cantidad = 385; // Puedes cambiar este número para probar
        int[] denominaciones = {100, 50, 20, 10, 5};
        
        System.out.println("Desglose de $" + cantidad + " usando ciclos:");
        
        // Recorremos el arreglo de denominaciones
        for (int i = 0; i < denominaciones.length; i++) {
            int billeteActual = denominaciones[i];
            
            // Si la cantidad es mayor o igual al billete, podemos dar cambio con él
            if (cantidad >= billeteActual) {
                int cuantosBilletes = cantidad / billeteActual; // Cuántos billetes caben
                cantidad = cantidad % billeteActual;            // Cuánto sobra (el residuo)
                
                System.out.println(cuantosBilletes + " billete(s) de $" + billeteActual);
            }
        }
        
        // Por si queda un sobrante menor a 5
        if (cantidad > 0) {
            System.out.println("Sobra: $" + cantidad + " (No hay denominaciones para esto)");
        }
    }
    
}
