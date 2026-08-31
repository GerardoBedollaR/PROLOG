# Práctica: Validación de Paréntesis con Pilas en Python

## Introducción
En esta práctica implementaremos una estructura de datos fundamental: la Pila (Stack). Esta estructura opera bajo el principio LIFO (Last In, First Out), un concepto que utilizan los compiladores de código para analizar la sintaxis de los programas y asegurar que no falte cerrar ningún bloque o ecuación[cite: 1].

## Resumen de la práctica
El objetivo es desarrollar un algoritmo que valide si una secuencia de paréntesis (como las resultantes de recorrer un árbol binario en preorden) está estructurada de forma correcta[cite: 1]. Para lograrlo, el ciclo recorrerá la cadena de texto guardando ("apilando") cada paréntesis de apertura y sacándolo ("desapilando") al encontrar uno de cierre[cite: 1]. Si la pila intenta sacar un elemento cuando está vacía, o si al final del proceso quedan elementos dentro, la sintaxis será declarada como incorrecta[cite: 1].

## Código Fuente
A continuación se presenta el algoritmo en Python, estructurado y comentado:

```python
def validar_parentesis(expresion):
    pila = []
    
    for caracter in expresion:
        if caracter == '(':
            # Si encontramos un paréntesis de apertura, lo metemos a la pila (Push)
            pila.append(caracter)
        elif caracter == ')':
            # Si encontramos uno de cierre, verificamos si la pila está vacía
            if len(pila) == 0:
                return False # Hay un cierre sin su apertura correspondiente
            # Sacamos el último paréntesis de apertura de la pila (Pop)
            pila.pop()
            
    # Si al final la pila está vacía, todos los paréntesis se cerraron correctamente
    return len(pila) == 0

# Pruebas con diferentes escenarios
print("Prueba '(( ))':", validar_parentesis("(( ))"))          # Esperado: True
print("Prueba ')) ((':", validar_parentesis(")) (("))          # Esperado: False
print("Prueba '()()':", validar_parentesis("()()"))             # Esperado: True
print("Prueba '()()()()':", validar_parentesis("()()()()"))     # Esperado: True
print("Prueba '()(':", validar_parentesis("()("))               # Esperado: False