# Práctica: Desglose de Billetes con Recursividad en Java

**Breve Introducción**
En esta práctica exploraremos el paradigma de la programación recursiva en Java, una técnica fundamental donde un método se invoca a sí mismo para resolver un problema dividiéndolo en subproblemas más pequeños y manejables, sirviendo como alternativa a las estructuras de control iterativas (ciclos).

**Resumen de la Práctica**
El objetivo es desarrollar un algoritmo recursivo que determine el desglose óptimo de un monto específico ($385) utilizando distintas denominaciones de billetes. La función evaluará un billete a la vez; si es viable, calculará la cantidad a entregar y se invocará a sí misma con el monto sobrante y el índice de la siguiente denominación. El proceso se detendrá al alcanzar el "caso base", es decir, cuando el monto sea cero o se agoten las denominaciones.

**Código Fuente**
A continuación se presenta el código en Java, estructurado y documentado con especial énfasis en la separación lógica entre el caso base y el caso recursivo:

```java
/*
 * Práctica de desglose de moneda mediante recursividad
 * Clase: Tarea12
 */

/**
 * @author gerar
 */
public class Tarea12 {
    public static void main(String[] args) {
        int cantidad = 385; // Puedes cambiar este número para probar diferentes montos
        int[] denominaciones = {100, 50, 20, 10, 5};
        
        System.out.println("Desglose de $" + cantidad + " usando recursividad:");
        
        // Arrancamos la recursividad enviando la cantidad, las denominaciones y empezando en el índice 0
        calcularCambio(cantidad, denominaciones, 0);
    }
    
    public static void calcularCambio(int cantidad, int[] denominaciones, int indice) {
        // 1. EL CASO BASE: Nos detenemos si ya dimos todo el cambio o si nos quedamos sin denominaciones
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
            
            // La función se llama a sí misma pasando el SOBRANTE y el SIGUIENTE billete (indice + 1)
            calcularCambio(sobrante, denominaciones, indice + 1);
        } else {
            // Si el billete es muy grande, mantenemos la cantidad pero pasamos a la siguiente denominación
            calcularCambio(cantidad, denominaciones, indice + 1);
        }
    }
}