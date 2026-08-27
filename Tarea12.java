/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author gerar
 */
public class Tarea12 {
    public static void main(String[] args) {
        int cantidad = 385; // Puedes cambiar este número para probar
        int[] denominaciones = {100, 50, 20, 10, 5};
        
        System.out.println("Desglose de $" + cantidad + " usando recursividad:");
        
        // Arrancamos la recursividad enviando la cantidad, las denominaciones y empezando en el índice 0
        calcularCambio(cantidad, denominaciones, 0);
    }
    
    public static void calcularCambio(int cantidad, int[] denominaciones, int indice) {
        // 1. EL CASO BASE: Nos detenemos si ya dimos todo el cambio o si ya nos quedamos sin denominaciones que revisar
        if (cantidad == 0 || indice >= denominaciones.length) {
            if (cantidad > 0) {
                System.out.println("Sobra: $" + cantidad + " (No hay denominaciones para esto)");
            }
            return; // Aquí termina la recursividad
        }
        
        // 2. EL CASO RECURSIVO
        int billeteActual = denominaciones[indice];
        
        if (cantidad >= billeteActual) {
            int cuantosBilletes = cantidad / billeteActual; // Calculamos cuántos billetes dar
            int sobrante = cantidad % billeteActual;        // Calculamos cuánto dinero sobra
            
            System.out.println(cuantosBilletes + " billete(s) de $" + billeteActual);
            
            // ¡La función se llama a sí misma! Le pasamos el SOBRANTE y el SIGUIENTE billete (indice + 1)
            calcularCambio(sobrante, denominaciones, indice + 1);
        } else {
            // Si el billete es muy grande y no nos sirve, nos llamamos a nosotros mismos
            // con la misma cantidad, pero pasamos al siguiente billete (indice + 1)
            calcularCambio(cantidad, denominaciones, indice + 1);
        }
    }
}
