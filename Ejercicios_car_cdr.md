# Ejercicios de Manipulación de Listas con `car` y `cdr` en Common Lisp

## Introducción
En Common Lisp, la estructura de datos fundamental es la lista enlazada, la cual se compone de pares ordenados conocidos como celdas *cons*. Para manipular y navegar a través de estas estructuras, se utilizan dos primitivas esenciales: `car`, que extrae el primer elemento (la cabeza) de una lista, y `cdr`, que devuelve el resto de la lista (la cola) omitiendo el primer elemento. Además, la función `cons` permite construir nuevas celdas uniendo elementos con sublistas existentes.

## Resumen de esta práctica
El objetivo de este taller es dominar el acceso, descomposición y construcción de listas en Lisp a través de 10 problemas prácticos. Mediante combinaciones de `car`, `cdr` y `cons`, se resuelven tareas comunes de extracción de elementos específicos, segmentación de sublistas, verificación de longitud, operaciones aritméticas sobre elementos y construcción de estructuras anidadas sin recurrir a funciones avanzadas de alto nivel.

---

## Código Fuente por Ejercicio

### Problema 1: Acceder al cuarto elemento de una lista
**Descripción:** Dada la lista `(a b c d e f g)`, se requiere extraer el cuarto elemento (`d`). Se logra encadenando tres aplicaciones de `cdr` para desplazar el puntero al cuarto nodo y aplicando `car` al final.

```lisp
(car (cdr (cdr (cdr '(a b c d e f g)))))
;; Resultado: d
```

---

### Problema 2: Obtener los tres últimos elementos de una lista
**Descripción:** Dada la lista `(1 2 3 4 5 6)`, se extrae la sublista conformada por sus últimos tres elementos `(4 5 6)` descartando los primeros tres valores mediante la aplicación consecutiva de `cdr`.

```lisp
(cdr (cdr (cdr '(1 2 3 4 5 6))))
;; Resultado: (4 5 6)
```

---

### Problema 3: Construir una lista a partir de los tres primeros elementos
**Descripción:** Dada la lista `(a b c d e)`, se extraen sus primeros tres elementos `(a, b, c)` usando `car`/`cdr` y se construye una nueva lista plana `(a b c)` mediante llamadas anidadas a cons finalizando en la lista vacía '().

```lisp
(cons (car '(a b c d e))
      (cons (car (cdr '(a b c d e)))
            (cons (car (cdr (cdr '(a b c d e))))
                  '())))
;; Resultado: (a b c)
```

---

### Problema 4: Eliminar el primer y último elemento de una lista
**Descripción:** Dada la lista `(x y z w)`, se eliminan el primer elemento (`x`) y el último (`w`), reteniendo únicamente `(y z)`. Se logra accediendo a `y` y `z` con `car`/`cdr` y reconstruyendo la lista resultante.

```lisp
(cons (car (cdr '(x y z w)))
      (cons (car (cdr (cdr '(x y z w))))
            '()))
;; Resultado: (y z)
```

---

### Problema 5: Verificar si una lista tiene al menos tres elementos
**Descripción:** Se define la función `al-menos-tres-p` que evalúa si una lista dada contiene por lo menos tres elementos. La función verifica que ni la lista original ni sus dos primeras colas (`cdr`) sean nulas.

(defun al-menos-tres-p (lista)
  (and (not (null lista))
       (not (null (cdr lista)))
       (not (null (cdr (cdr lista))))))

```lisp
;; Pruebas:
;; (al-menos-tres-p '(a b c)) => T
;; (al-menos-tres-p '(a b))   => NIL
```

---

### Problema 6: Sumar los primeros tres números de una lista
**Descripción:** Dada la lista de números `(10 20 30 40)`, se extraen los tres primeros valores mediante combinaciones de `car` y `cdr` y se calcula su suma acumulada mediante el operador `+`.

```lisp
(+ (car '(10 20 30 40))
   (car (cdr '(10 20 30 40)))
   (car (cdr (cdr '(10 20 30 40)))))
;; Resultado: 60
```

---

### Problema 7: Acceder al penúltimo elemento de una lista
**Descripción:** Dada la lista `(p q r s t)`, se extrae el penúltimo elemento (`s`). En una lista de 5 elementos, el penúltimo corresponde a la cuarta posición, accesible desplazándonos tres posiciones con `cdr` y tomando su `car`.

```lisp
(car (cdr (cdr (cdr '(p q r s t)))))
;; Resultado: s
```

---

### Problema 8: Crear una lista anidada
**Descripción:** Dada la lista `(1 2 3)`, se extrae cada entero individualmente y se construyen sublistas individuales de un solo elemento `(1)`, `(2)` y `(3)` para combinarlas finalmente en la lista anidada `((1) (2) (3))`.

```lisp
(cons (cons (car '(1 2 3)) '())
      (cons (cons (car (cdr '(1 2 3))) '())
            (cons (cons (car (cdr (cdr '(1 2 3)))) '())
                  '())))
;; Resultado: ((1) (2) (3))
```

---

### Problema 9: Fusionar dos listas
**Descripción:** Dadas las listas `(a b c)` y `(x y z)`, se extraen secuencialmente los elementos de la primera lista usando `car`/`cdr` y se insertan al frente de la segunda lista utilizando `cons` para obtener `(a b c x y z)`.

```lisp
(cons (car '(a b c))
      (cons (car (cdr '(a b c)))
            (cons (car (cdr (cdr '(a b c))))
                  '(x y z))))
;; Resultado: (a b c x y z)
```

---

### Problema 10: Invertir los dos primeros pares de elementos de una lista
**Descripción:** Dada la lista `(1 2 3 4 5 6)`, se invierte el orden del primer par `(1 2)` a `2 1` y del segundo par `(3 4)` a `4 3`, manteniendo intacto el resto de la lista `(5 6)` para producir `(2 1 4 3 5 6)`.

```lisp
(cons (car (cdr '(1 2 3 4 5 6)))                ; 2
      (cons (car '(1 2 3 4 5 6))                 ; 1
            (cons (car (cdr (cdr (cdr '(1 2 3 4 5 6)))))       ; 4
                  (cons (car (cdr (cdr '(1 2 3 4 5 6))))        ; 3
                        (cdr (cdr (cdr (cdr '(1 2 3 4 5 6))))))))) ; (5 6)
;; Resultado: (2 1 4 3 5 6)
```

---

## Conclusión
El desarrollo de estos diez ejercicios refuerza la comprensión de cómo Common Lisp representa y gestiona la memoria a través de celdas cons. La combinación estratégica de `car` y `cdr` permite un control de acceso preciso a cualquier posición dentro de una estructura jerárquica. Asimismo, el uso de `cons` demuestra la naturaleza inmutable de la construcción de listas, donde las nuevas estructuras se forman vinculando nuevos elementos a listas ya existentes sin alterar las referencias originales.