# Operación Lambda — Informe
**Agente:** [Nombre / Matrícula]

## Misión 1

- Tabla predicción vs CLISP (a–f):
  | Clave | Expresión | Predicción | CLISP |
  | :---: | :--- | :---: | :---: |
  | a | `(car (cdr (car *agentes*)))` | 28 | 28 |
  | b | `(car (car (cdr *agentes*)))` | beto | beto |
  | c | `(cdr (car (cdr (cdr *agentes*))))` | (22 1 morelia 45) | (22 1 morelia 45) |
  | d | `(car (cdr (cdr (cdr (car (cdr (cdr (cdr *agentes*)))))))))` | zamora | zamora |
  | e | `(caddr (cadr *agentes*))` | 5 | 5 |
  | f | `(car (cdr (cdr (car (cdr (cdr (cdr (cdr (cdr *agentes*))))))))))` | 5 | 5 |

- Errores de predicción y por qué:
  Ninguno. Todas las predicciones hechas de adentro hacia afuera coincidieron con las salidas de CLISP.

- Puntos de Elena (expresión + salida):
  Expresión: `(puntos (car (cdr (cdr (cdr (cdr *agentes*))))))`
  Salida: `90`

- `(car (cdr '(ana)))`:
  Devuelve `NIL`. No marca un error de sintaxis en CLISP. Es peligroso porque si un registro viene incompleto, el programa continúa silenciosamente devolviendo `NIL`, lo que provocará fallos en operaciones aritméticas posteriores difíciles de depurar.

## Misión 2

- Salidas de pase-de-lista, nombre-y-nivel, cumpleaños, aplicar-bonos:
  - `(pase-de-lista *agentes*)` -> `(ANA BETO CARLA DIEGO ELENA FAUSTO)`
  - `(nombre-y-nivel *agentes*)` -> `((ANA . 3) (BETO . 5) (CARLA . 1) (DIEGO . 4) (ELENA . 2) (FAUSTO . 5))`
  - `(cumpleaños *agentes*)` -> `((ANA 29) (BETO 36) (CARLA 23) (DIEGO 42) (ELENA 31) (FAUSTO 27))`
  - `(aplicar-bonos *agentes* *bonos*)` -> `(130 340 50 230 105 400)`

- ¿Por qué #'car?:
  Porque Common Lisp es un lenguaje Lisp-2 donde las funciones y las variables residen en diferentes espacios de nombres. El operador `#'` (o `function`) especifica explícitamente que estamos pasando la función `car` como argumento a `mapcar`. Sin este operador, CLISP intentaría evaluar `car` como una variable, generando un error.

## Misión 3

- Mensaje descifrado:
  `((T R A H I D O R) (N H V D L) (C H N C R) (F U D R A) (D E) (L R T D L H A))`

- Versión en una sola expresión:
```lisp
    (mapcar (lambda (palabra)
        (mapcar (lambda (n)
            (nth (mod (- n 3) 26) *alfabeto*))
                    palabra))
            *interceptado*)
```          
* (Extra) comprobación de cifrado:
```Lisp
(defun cifrar-palabra (palabra)
  (mapcar (lambda (letra)
            (mod (+ (position letra *alfabeto*) 3) 26))
          palabra))
```

Al ejecutar (mapcar #'cifrar-palabra (descifrar-mensaje *interceptado*)), el resultado devuelve exactamente la lista *interceptado* original.

## Misión 4

- Sospechoso(s):
  `(FAUSTO)`

- Total de puntos de los leales:
  `805`

- Promedio de edad (racional y decimal):
  Racional: `147/5`
  Decimal: `29.4`

- Deducción de los dos nivel 5:
  Los dos agentes de nivel 5 son Beto y Fausto. La parte del mensaje que descarta a Beto es la base ("morelia"), ya que Beto pertenece a la base Uruapan. Si el filtro solo revisara el nivel, devolvería a dos sospechosos `(BETO FAUSTO)` y no se podría identificar al traidor de forma unívoca.

## Misión 5

| ID | ¿Truena? | Mensaje / resultado | Causa | Corrección |
| :---: | :---: | :--- | :--- | :--- |
| S1 | Sí | `EVAL: variable CAR has no value` | Se omitió `#'` al pasar la función `car` como argumento a `mapcar`. | `(mapcar #'car *agentes*)` |
| S2 | Sí | `EVAL: undefined function NOMBRE` | Se usó `nombre` dentro de lambda sin haber sido definida previamente en el entorno. | `(defun nombre (ag) (car ag))` <br> `(mapcar (lambda (ag) (nombre ag)) *agentes*)` |
| S3 | Sí | `invalid number of arguments given to #<FUNCTION :LAMBDA (AG) (PUNTOS AG)>` | Se enviaron 2 listas a `mapcar` pero la función lambda solo aceptaba 1 parámetro (`ag`). | `(mapcar (lambda (ag bono) (+ (puntos ag) bono)) *agentes* *bonos*)` |
| S4 | NO | `(130 340 50)` | No arroja error pero trunca el resultado porque la segunda lista solo tiene 3 elementos. | `(mapcar (lambda (ag b) (+ (puntos ag) b)) *agentes* *bonos*)` |
| S5 | Sí | `apply: '(LAMBDA (AG) (NOMBRE AG)) is an illegal function name` | Se antepuso una comilla `'` a `(lambda ...)`, convirtiéndola en un dato literal en lugar de una función evaluable. | `(mapcar (lambda (ag) (nombre ag)) *agentes*)` |

- Sabotaje silencioso y regla de mapcar:
  El fragmento S4 es el sabotaje silencioso. Es el más peligroso porque no genera un error visible de ejecución, pero entrega información incompleta (solo los datos de los primeros 3 agentes de 6).
  La regla de `mapcar` establece que cuando procesa múltiples listas simultáneamente, la iteración se detiene tan pronto como se agota la lista de menor longitud, ignorando los elementos sobrantes de las listas más largas.

## Misión 6

- Salidas de los tres filtros:
  - Nivel 4 o más: `((BETO 35 5 URUAPAN 340) (DIEGO 41 4 ZAMORA 210) (FAUSTO 26 5 MORELIA 400))`
  - Morelia: `((ANA 28 3 MORELIA 120) (CARLA 22 1 MORELIA 45) (FAUSTO 26 5 MORELIA 400))`
  - Morelia y nivel 3 o más: `((ANA 28 3 MORELIA 120) (FAUSTO 26 5 MORELIA 400))`

- Salida del ensamble final:
  ```text
  ANA (MORELIA) nivel 3 -> 120 pts
  BETO (URUAPAN) nivel 5 -> 340 pts
  DIEGO (ZAMORA) nivel 4 -> 210 pts
  3

- Ventaja de la fábrica:
    Permite generar filtros personalizados dinámicamente mediante lambdas/clausuras según los parámetros recibidos, evitando la necesidad de declarar explícitamente múltiples funciones rígidas e individuales para cada posible nivel o combinación.