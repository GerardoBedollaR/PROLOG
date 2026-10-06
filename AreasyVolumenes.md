# Práctica: Cálculo de Áreas y Volúmenes en Common Lisp (CLISP)

## Introducción
Common Lisp es un lenguaje de programación funcional caracterizado por el uso de la notación prefija (Notación Polaca), donde los operadores y nombres de funciones se colocan antes de sus operandos[cite: 5, 8]. En este entorno, la sintaxis está basada completamente en listas delimitadas por paréntesis `(función arg1 arg2...)`[cite: 2, 5]. En esta práctica abordamos la definición de funciones mediante la primitiva `defun`, aplicando expresiones aritméticas para calcular figuras geométricas de dos y tres dimensiones de manera modular[cite: 2, 5].

## Resumen de esta práctica
El objetivo de este programa es construir un conjunto de 20 funciones independientes en CLISP que permitan calcular de forma rápida y precisa:
1. **10 áreas geométricas:** Cuadrado, rectángulo, triángulo, círculo, trapecio, paralelogramo, rombo, pentágono, hexágono y elipse.
2. **10 volúmenes geométricos:** Cubo, cilindro, esfera, cono, prisma rectangular, pirámide cuadrangular, toroide, elipsoide, prisma triangular y tronco de cono.

A través de esta práctica se consolida el uso de `defun`, la gestión de parámetros sin comas y el anidamiento correcto de operadores aritméticos (`+`, `-`, `*`, `/`) en notación prefija[cite: 2, 7].

# Código Fuente
A continuación se presenta el código completo en CLISP, comentado y estructurado respetando la sintaxis del lenguaje:

```lisp
;; ==========================================
;; CÁLCULO DE 10 ÁREAS
;; ==========================================

;; 1. Área de un Cuadrado (lado * lado)
(defun area-cuadrado (lado)
  (* lado lado)
)

;; 2. Área de un Rectángulo (base * altura)
(defun area-rectangulo (base altura)
  (* base altura)
)

;; 3. Área de un Triángulo (base * altura / 2)
(defun area-triangulo (base altura)
  (/ (* base altura) 2)
)

;; 4. Área de un Círculo (pi * r^2)
(defun area-circulo (radio)
  (* 3.1416 (* radio radio))
)

;; 5. Área de un Trapecio ((base1 + base2) * altura / 2)
(defun area-trapecio (base1 base2 altura)
  (/ (* (+ base1 base2) altura) 2)
)

;; 6. Área de un Paralelogramo (base * altura)
(defun area-paralelogramo (base altura)
  (* base altura)
)

;; 7. Área de un Rombo (diagonal_mayor * diagonal_menor / 2)
(defun area-rombo (diag-mayor diag-menor)
  (/ (* diag-mayor diag-menor) 2)
)

;; 8. Área de un Pentágono Regular (perimetro * apotema / 2)
(defun area-pentagono (perimetro apotema)
  (/ (* perimetro apotema) 2)
)

;; 9. Área de un Hexágono Regular (perimetro * apotema / 2)
(defun area-hexagono (perimetro apotema)
  (/ (* perimetro apotema) 2)
)

;; 10. Área de una Elipse (pi * a * b)
(defun area-elipse (semieje-a semieje-b)
  (* 3.1416 (* semieje-a semieje-b))
)


;; ==========================================
;; CÁLCULO DE 10 VOLÚMENES
;; ==========================================

;; 1. Volumen de un Cubo (lado^3)
(defun volumen-cubo (lado)
  (* lado (* lado lado))
)

;; 2. Volumen de un Cilindro (pi * r^2 * altura)
(defun volumen-cilindro (radio altura)
  (* 3.1416 (* (* radio radio) altura))
)

;; 3. Volumen de una Esfera (4/3 * pi * r^3)
(defun volumen-esfera (radio)
  (* (/ 4.0 3.0) (* 3.1416 (* radio (* radio radio))))
)

;; 4. Volumen de un Cono (1/3 * pi * r^2 * altura)
(defun volumen-cono (radio altura)
  (/ (* 3.1416 (* (* radio radio) altura)) 3)
)

;; 5. Volumen de un Prisma Rectangular (largo * ancho * alto)
(defun volumen-prisma-rectangular (largo ancho alto)
  (* largo (* ancho alto))
)

;; 6. Volumen de una Pirámide Cuadrangular (area_base * altura / 3)
(defun volumen-piramide-cuadrada (lado-base altura)
  (/ (* (* lado-base lado-base) altura) 3)
)

;; 7. Volumen de un Toroide (2 * pi^2 * R * r^2)
(defun volumen-toroide (radio-mayor radio-menor)
  (* 2 (* (* 3.1416 3.1416) (* radio-mayor (* radio-menor radio-menor))))
)

;; 8. Volumen de un Elipsoide (4/3 * pi * a * b * c)
(defun volumen-elipsoide (a b c)
  (* (/ 4.0 3.0) (* 3.1416 (* a (* b c))))
)

;; 9. Volumen de un Prisma Triangular (base * altura_tri * altura_prisma / 2)
(defun volumen-prisma-triangular (base-tri altura-tri altura-prisma)
  (/ (* (* base-tri altura-tri) altura-prisma) 2)
)

;; 10. Volumen de un Tronco de Cono
(defun volumen-tronco-cono (radio1 radio2 altura)
  (* (/ (* 3.1416 altura) 3) (+ (* radio1 radio1) (+ (* radio2 radio2) (* radio1 radio2))))
)

```
## Conclusión
La implementación de estas fórmulas en Common Lisp demuestra la elegancia y simplicidad del paradigma funcional para evaluar cálculos matemáticos. Al eliminar la necesidad de variables temporales o estructuras imperativas complejas, el intérprete evalúa de adentro hacia afuera cada nivel de paréntesis. Además, la declaración explícita de parámetros sin separadores como comas ayuda a comprender el estándar estricto de listas del lenguaje, sirviendo como excelente base para el desarrollo de algoritmos más avanzados.