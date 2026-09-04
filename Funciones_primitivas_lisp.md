# Reporte: Funciones Primitivas de Lisp

## introduccion
Lisp es un lenguaje funcional clásico donde el código y los datos comparten la misma estructura de listas enlazadas. Esta característica hace que la evaluación lógica y el recorrido de árboles sean procesos completamente naturales.

## Resumen de esta practica
El objetivo de este reporte es presentar un listado comprensivo de 25 funciones primitivas y base del lenguaje Lisp. El documento sirve como referencia rápida para comprender cómo se manipulan listas, se evalúan expresiones y se controla el flujo lógico en este particular lenguaje funcional.

## Funciones Primitivas 
A continuación se presenta el listado solicitado con su respectiva descripción:

| Función | Descripción |
| :--- | :--- |
| `car` | Devuelve el primer elemento de una lista. |
| `cdr` | Devuelve el resto de la lista (todo el contenido excluyendo el primer elemento). |
| `cons` | Construye una nueva lista uniendo un elemento con otra lista existente. |
| `quote` (o `'`) | Devuelve la expresión de manera literal sin intentar evaluarla como código. |
| `eval` | Toma una expresión o lista de datos y la ejecuta forzosamente como código Lisp. |
| `setq` | Asigna un valor específico a una variable global. |
| `let` | Define un entorno cerrado con variables locales que solo existen dentro de ese bloque. |
| `defun` | Declara y nombra una nueva función personalizada. |
| `lambda` | Crea funciones matemáticas o lógicas anónimas para ser utilizadas en el momento sin definirlas globalmente. |
| `list` | Agrupa los elementos proporcionados creando una nueva lista plana. |
| `append` | Concatena los elementos de dos o más listas fusionándolas en una sola estructura. |
| `length` | Cuenta y devuelve el número total de elementos en el nivel superior de una lista. |
| `null` | Verifica si una lista está vacía o si el valor es nulo (equivalente a falso). |
| `atom` | Determina si el dato evaluado es un átomo (un valor indivisible, no una lista). |
| `listp` | Confirma lógicamente si el argumento analizado pertenece al tipo de dato lista. |
| `eq` | Compara si dos objetos o variables apuntan exactamente a la misma dirección de memoria. |
| `equal` | Compara exhaustivamente si el contenido de dos listas o variables es idéntico, sin importar su memoria. |
| `if` | Ejecuta un bloque de control de flujo simple con base en si la expresión inicial es verdadera o falsa. |
| `cond` | Evalúa múltiples escenarios condicionales en cadena, actuando como un bloque switch-case. |
| `and` | Operador lógico que retorna verdadero únicamente si todas las expresiones evaluadas lo son. |
| `or` | Operador lógico que retorna verdadero en cuanto encuentra la primera expresión que se cumpla. |
| `not` | Invierte lógicamente el valor booleano de la expresión evaluada. |
| `mapcar` | Aplica una función a cada elemento de una lista iterativamente, devolviendo una nueva lista con los resultados. |
| `apply` | Toma una función y una lista de argumentos, desempaquetando la lista para ejecutar la función con ellos. |
| `format` | Formatea cadenas de texto y valores para imprimirlos en la consola o devolverlos como una nueva cadena. |

## Conclusion 
Estas 25 primitivas demuestran la elegancia de Lisp. Al usar una sintaxis basada completamente en listas y paréntesis, permite crear algoritmos y evaluaciones matemáticas de forma muy directa y declarativa. Tal como hemos visto en ejercicios anteriores sobre validación de paréntesis mediante pilas y recorridos de árboles binarios, comprender estas bases facilita enormemente la programación de estructuras lógicas y recursivas.