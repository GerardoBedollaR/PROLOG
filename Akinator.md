# Juego Akinator con Árbol Trinario en CLISP

## Introducción
Common Lisp (CLISP) es un lenguaje de programación del paradigma funcional en el que las estructuras de datos y el código comparten el mismo formato basado en listas y expresiones delimitadas por paréntesis. En este proyecto se utiliza una estructura de datos jerárquica llamada **árbol trinario**. A diferencia de un árbol binario tradicional (que se divide únicamente en dos ramas: Sí/No), cada nodo interno de un árbol trinario posee exactamente tres subárboles o ramas de salida (opciones `A`, `B` y `C`), lo que permite realizar clasificaciones más amplias y complejas en cada nivel de decisión.

## Resumen de la Práctica
El objetivo de este programa es construir un juego interactivo al estilo "Akinator" que adivine cuál de las tres marcas populares de teléfonos inteligentes (**Samsung**, **Apple** o **Huawei**) tiene en mente el usuario. 

Para lograrlo:
1. Se construyó un árbol trinario de decisión con una profundidad estricta de **3 niveles de preguntas**.
2. Cada pregunta presenta 3 opciones cómicas y descriptivas (`[A]`, `[B]`, `[C]`), combinando características de las tres marcas en cada nivel para despistar al jugador.
3. El programa navega de forma recursiva por las ramas elegidas hasta llegar a una "hoja" del árbol, donde identifica y despliega la marca correcta.

---

## Código Fuente
A continuación se presenta el código completo en CLISP, comentado paso a paso:

```lisp
;; ==========================================
;; AKINATOR TRINARIO EN CLISP (3 PREGUNTAS EXACTAS)
;; ==========================================

;; Definición de la estructura del nodo trinario
(defstruct nodo-trinario
  pregunta      ; Texto de la pregunta si es un nodo interno
  opcion-a      ; Subárbol al elegir respuesta A
  opcion-b      ; Subárbol al elegir respuesta B
  opcion-c      ; Subárbol al elegir respuesta C
  personaje)    ; Nombre de la marca si es una hoja del árbol

;; ------------------------------------------
;; CONSTRUCCIÓN DEL ÁRBOL TRINARIO
;; ------------------------------------------
(defun construir-arbol-akinator ()
  (make-nodo-trinario
   ;; --- PREGUNTA 1 ---
   :pregunta "¿Qué es lo primero que notas o recuerdas al pensar en la marca?
  [A] Diseños de lujo, precios altísimos y filas larguísimas afuera de las tiendas
  [B] Cámaras con zooms potentísimos para fotografiar la Luna y problemas con EE. UU.
  [C] Pantallas plegables, comerciales burlones e innovación en hardware"

   ;; =========================================================================
   ;; RAMA A (Elegiste A en Pregunta 1)
   ;; =========================================================================
   :opcion-a
   (make-nodo-trinario
    :pregunta "¿Cómo reacciona la marca ante las críticas de sus usuarios?
  [A] Dice 'HarmonyOS no necesita a nadie' y presume carga rápida de 100W
  [B] Quita el cargador de la caja y dice que es para 'cuidar el medio ambiente'
  [C] Presume sus 200 Megapíxeles y pantallas de 120Hz"
    ;; Pregunta 3 para Rama A
    :opcion-a (make-nodo-trinario
               :pregunta "¿Qué dicen los fans en discusiones de internet?
  [A] 'HarmonyOS es el futuro'
  [B] 'Los plegables mandan'
  [C] 'El ecosistema vale la pena'"
               :opcion-a (make-nodo-trinario :personaje "Huawei")
               :opcion-b (make-nodo-trinario :personaje "Samsung")
               :opcion-c (make-nodo-trinario :personaje "Apple"))
    :opcion-b (make-nodo-trinario
               :pregunta "¿Qué excusa ponen cuando suben los precios?
  [A] 'Es innovación con pantallas flexibles'
  [B] 'El estatus y la experiencia lo valen'
  [C] 'Ofrecemos mejor hardware por tu dinero'"
               :opcion-a (make-nodo-trinario :personaje "Samsung")
               :opcion-b (make-nodo-trinario :personaje "Apple")
               :opcion-c (make-nodo-trinario :personaje "Huawei"))
    :opcion-c (make-nodo-trinario
               :pregunta "¿Cuál es su producto o característica más loca?
  [A] Una funda con la manzana mordida que cuesta $100 dólares
  [B] Un teléfono que se dobla en cuatro como papel
  [C] Un zoom de 100x que detecta la Luna automáticamente"
               :opcion-a (make-nodo-trinario :personaje "Apple")
               :opcion-b (make-nodo-trinario :personaje "Samsung")
               :opcion-c (make-nodo-trinario :personaje "Huawei")))

   ;; =========================================================================
   ;; RAMA B (Elegiste B en Pregunta 1)
   ;; =========================================================================
   :opcion-b
   (make-nodo-trinario
    :pregunta "¿Qué pasa cuando intentas usar aplicaciones tradicionales en el teléfono?
  [A] Te sale un aviso que dice 'No hay servicios de Google Play'
  [B] La pantalla se dobla a la mitad mientras ves el video
  [C] Funciona perfecto pero tuviste que vender un riñón para pagarlo"
    ;; Pregunta 3 para Rama B
    :opcion-a (make-nodo-trinario
               :pregunta "¿Qué es lo que más presume la marca en sus eventos?
  [A] Su propio sistema operativo libre de sanciones
  [B] Que sus cámaras son las mejores del mercado
  [C] Sus nuevos procesadores con la manzana de logo"
               :opcion-a (make-nodo-trinario :personaje "Huawei")
               :opcion-b (make-nodo-trinario :personaje "Samsung")
               :opcion-c (make-nodo-trinario :personaje "Apple"))
    :opcion-b (make-nodo-trinario
               :pregunta "¿Cuál es el mayor temor de un usuario con este dispositivo?
  [A] Que la pantalla flexible se rompa con la uña
  [B] Que se le caiga y se rompa el cristal de $500 dólares
  [C] Que le pongan otro bloqueo comercial"
               :opcion-a (make-nodo-trinario :personaje "Samsung")
               :opcion-b (make-nodo-trinario :personaje "Apple")
               :opcion-c (make-nodo-trinario :personaje "Huawei"))
    :opcion-c (make-nodo-trinario
               :pregunta "¿Qué incluye la caja cuando compras el teléfono?
  [A] El teléfono y un sticker de manzana nada más
 Aquí tienes todo el reporte en un solo bloque dentro de un **bloque de código** (````markdown ... ````). 

De esta manera, la interfaz no procesará las etiquetas HTML ni los formatos visuales de los encabezados, por lo que podrás copiar el contenido íntegro y limpio sin perder ningún símbolo de Markdown (`#`, ```, etc.):

```markdown
# Reporte: Akinator Trinario en Common Lisp (CLISP)

## Breve Introducción
Common Lisp es un lenguaje de programación funcional en el cual el código y las estructuras de datos comparten la misma representación basada en expresiones anidadas entre paréntesis. Un **árbol trinario** es una estructura de datos jerárquica donde cada nodo interno posee exactamente tres ramas o hijos (`opcion-a`, `opcion-b`, `opcion-c`). En esta práctica, utilizamos esta estructura para implementar un juego estilo *Akinator* que adivina marcas comerciales de telefonía (Apple, Samsung y Huawei) evaluando las respuestas seleccionadas por el usuario.

## Resumen de qué se va a lograr con esta práctica
El objetivo de este programa es construir un árbol trinario de decisión con una profundidad fija de 3 preguntas consecutivas. A través del juego, el sistema presenta opciones de respuesta variadas (`A`, `B` o `C`) y navega de forma recursiva por las ramas del árbol en función de las elecciones del usuario. Al alcanzar la profundidad máxima (un nodo hoja), el programa determina la marca elegida descartando las opciones incorrectas.

---

## Código Fuente

```lisp
;; ==========================================
;; AKINATOR TRINARIO EN CLISP (3 PREGUNTAS EXACTAS)
;; ==========================================

;; Definición de la estructura del nodo trinario
(defstruct nodo-trinario
  pregunta      ; Texto de la pregunta si es un nodo interno
  opcion-a      ; Subárbol al elegir respuesta A
  opcion-b      ; Subárbol al elegir respuesta B
  opcion-c      ; Subárbol al elegir respuesta C
  personaje)    ; Nombre de la marca si es una hoja del árbol

;; ------------------------------------------
;; CONSTRUCCIÓN DEL ÁRBOL TRINARIO
;; ------------------------------------------
(defun construir-arbol-akinator ()
  (make-nodo-trinario
   ;; --- PREGUNTA 1 ---
   :pregunta "¿Qué es lo primero que notas o recuerdas al pensar en la marca?
  [A] Diseños de lujo, precios altísimos y filas larguísimas afuera de las tiendas
  [B] Cámaras con zooms potentísimos para fotografiar la Luna y problemas con EE. UU.
  [C] Pantallas plegables, comerciales burlones e innovación en hardware"

   ;; =========================================================================
   ;; RAMA A (Elegiste A en Pregunta 1)
   ;; =========================================================================
   :opcion-a
   (make-nodo-trinario
    :pregunta "¿Cómo reacciona la marca ante las críticas de sus usuarios?
  [A] Dice 'HarmonyOS no necesita a nadie' y presume carga rápida de 100W
  [B] Quita el cargador de la caja y dice que es para 'cuidar el medio ambiente'
  [C] Presume sus 200 Megapíxeles y pantallas de 120Hz"
    :opcion-a (make-nodo-trinario
               :pregunta "¿Qué dicen los fans en discusiones de internet?
  [A] 'HarmonyOS es el futuro'
  [B] 'Los plegables mandan'
  [C] 'El ecosistema vale la pena'"
               :opcion-a (make-nodo-trinario :personaje "Huawei")
               :opcion-b (make-nodo-trinario :personaje "Samsung")
               :opcion-c (make-nodo-trinario :personaje "Apple"))
    :opcion-b (make-nodo-trinario
               :pregunta "¿Qué excusa ponen cuando suben los precios?
  [A] 'Es innovación con pantallas flexibles'
  [B] 'El estatus y la experiencia lo valen'
  [C] 'Ofrecemos mejor hardware por tu dinero'"
               :opcion-a (make-nodo-trinario :personaje "Samsung")
               :opcion-b (make-nodo-trinario :personaje "Apple")
               :opcion-c (make-nodo-trinario :personaje "Huawei"))
    :opcion-c (make-nodo-trinario
               :pregunta "¿Cuál es su producto o característica más loca?
  [A] Una funda con la manzana mordida que cuesta $100 dólares
  [B] Un teléfono que se dobla en cuatro como papel
  [C] Un zoom de 100x que detecta la Luna automáticamente"
               :opcion-a (make-nodo-trinario :personaje "Apple")
               :opcion-b (make-nodo-trinario :personaje "Samsung")
               :opcion-c (make-nodo-trinario :personaje "Huawei")))

   ;; =========================================================================
   ;; RAMA B (Elegiste B en Pregunta 1)
   ;; =========================================================================
   :opcion-b
   (make-nodo-trinario
    :pregunta "¿Qué pasa cuando intentas usar aplicaciones tradicionales en el teléfono?
  [A] Te sale un aviso que dice 'No hay servicios de Google Play'
  [B] La pantalla se dobla a la mitad mientras ves el video
  [C] Funciona perfecto pero tuviste que vender un riñón para pagarlo"
    :opcion-a (make-nodo-trinario
               :pregunta "¿Qué es lo que más presume la marca en sus eventos?
  [A] Su propio sistema operativo libre de sanciones
  [B] Que sus cámaras son las mejores del mercado
  [C] Sus nuevos procesadores con la manzana de logo"
               :opcion-a (make-nodo-trinario :personaje "Huawei")
               :opcion-b (make-nodo-trinario :personaje "Samsung")
               :opcion-c (make-nodo-trinario :personaje "Apple"))
    :opcion-b (make-nodo-trinario
               :pregunta "¿Cuál es el mayor temor de un usuario con este dispositivo?
  [A] Que la pantalla flexible se rompa con la uña
  [B] Que se le caiga y se rompa el cristal de $500 dólares
  [C] Que le pongan otro bloqueo comercial"
               :opcion-a (make-nodo-trinario :personaje "Samsung")
               :opcion-b (make-nodo-trinario :personaje "Apple")
               :opcion-c (make-nodo-trinario :personaje "Huawei"))
    :opcion-c (make-nodo-trinario
               :pregunta "¿Qué incluye la caja cuando compras el teléfono?
  [A] El teléfono y un sticker de manzana nada más
  [B] El teléfono y un cargador súper rápido de 100W
  [C] El teléfono, funda, pero sin la Play Store"
               :opcion-a (make-nodo-trinario :personaje "Apple")
               :opcion-b (make-nodo-trinario :personaje "Samsung")
               :opcion-c (make-nodo-trinario :personaje "Huawei")))

   ;; =========================================================================
   ;; RAMA C (Elegiste C en Pregunta 1)
   ;; =========================================================================
   :opcion-c
   (make-nodo-trinario
    :pregunta "¿Además de teléfonos inteligentes, qué más fabrica o vende esta empresa?
  [A] Relojes de lujo, audífonos finos y suscripciones de almacenamiento
  [B] Lavadoras, refrigeradores, televisores y hasta tanques militares
  [C] Antenas de redes 5G y equipo de telecomunicaciones global"
    :opcion-a (make-nodo-trinario
               :pregunta "¿Cuál es la frase típica de sus usuarios?
  [A] 'Si no tienes uno, no entiendes el ecosistema'
  [B] 'Mi pantalla AMOLED se ve mejor que tu LCD'
  [C] 'Mi batería se carga de 0 a 100% en 15 minutos'"
               :opcion-a (make-nodo-trinario :personaje "Apple")
               :opcion-b (make-nodo-trinario :personaje "Samsung")
               :opcion-c (make-nodo-trinario :personaje "Huawei"))
    :opcion-b (make-nodo-trinario
               :pregunta "¿Cuál es su serie de teléfonos más famosa?
  [A] La serie Galaxy S / Z Flip
  [B] La serie iPhone Pro Max
  [C] La serie P / Mate"
               :opcion-a (make-nodo-trinario :personaje "Samsung")
               :opcion-b (make-nodo-trinario :personaje "Apple")
               :opcion-c (make-nodo-trinario :personaje "Huawei"))
    :opcion-c (make-nodo-trinario
               :pregunta "¿Cuál es el color o diseño más representativo de la marca?
  [A] Rojo con dorado y estética de tecnología asiática avanzada
  [B] Blanco minimalista con logo de fruta
  [C] Diseños extravagantes en tonalidades pastel y pantallas curvas"
               :opcion-a (make-nodo-trinario :personaje "Huawei")
               :opcion-b (make-nodo-trinario :personaje "Apple")
               :opcion-c (make-nodo-trinario :personaje "Samsung")))))

;; ------------------------------------------
;; LÓGICA DE NAVEGACIÓN RECURSIVA DEL ÁRBOL
;; ------------------------------------------
(defun jugar-akinator (nodo)
  ;; CASO BASE: Llegamos a una hoja (nodo con personaje asignado)
  (if (nodo-trinario-personaje nodo)
      (progn
        (format t "~%==========================================~%")
        (format t " ¡JA! ¡Ya sé exactamente en quién piensas! ~%")
        (format t " La marca es: *** ~A ***~%" (nodo-trinario-personaje nodo))
        (format t "==========================================~%"))
      
      ;; CASO RECURSIVO: Hacemos la pregunta y descendemos en el árbol
      (progn
        (format t "~%~A~%" (nodo-trinario-pregunta nodo))
        (format t "Elige tu respuesta (A, B o C): ")
        (finish-output)
        (let ((respuesta (string-upcase (string (read)))))
          (cond
            ((string= respuesta "A")
             (jugar-akinator (nodo-trinario-opcion-a nodo)))
            ((string= respuesta "B")
             (jugar-akinator (nodo-trinario-opcion-b nodo)))
            ((string= respuesta "C")
             (jugar-akinator (nodo-trinario-opcion-c nodo)))
            (t
             (format t "Opción no válida. Por favor escribe A, B o C.~%")
             (jugar-akinator nodo)))))))

;; ------------------------------------------
;; FUNCIÓN PRINCIPAL
;; ------------------------------------------
(defun iniciar-juego ()
  (format t "==========================================~%")
  (format t "   BIENVENIDO AL AKINATOR TRINARIO LISP   ~%")
  (format t " Piensa en una marca: Samsung, Apple o Huawei ~%")
  (format t "==========================================~%")
  (let ((arbol (construir-arbol-akinator)))
    (jugar-akinator arbol)))
```

---

## Explicación Detallada de Comandos y Funciones de LISP
Para entender cómo funciona el programa sin conocimientos previos de Common Lisp o árboles trinarios, a continuación se desglosan sus componentes principales:

* `defstruct` (Definición de Estructura): Define una estructura personalizada llamada `nodo-trinario`. Permite crear nodos que guardan los campos `pregunta`, tres ramas descendientes (`opcion-a`, `opcion-b`, `opcion-c`) y el campo `personaje`.

* `make-nodo-trinario` (Constructor): Función generada automáticamente por Lisp para crear una instancia del nodo asignando valores a sus miembros clave mediante indicadores como :`pregunta` o :`personaje`.

* `defun` (Definición de Función): Declara y nombra una función personalizada en Lisp junto con sus parámetros formales.

* `if` (Condicional Simple): Evalúa un caso base. Si el nodo contiene un `personaje`, imprime la solución; de lo contrario, continúa ejecutando el bloque recursivo.

* `progn` (Agrupador de Instrucciones): Agrupa múltiples expresiones Lisp dentro de un mismo bloque de ejecución para ejecutarlas secuencialmente.

* `format` (Impresión en Consola): Formatea e imprime cadenas de texto en la terminal. El indicador `~A` sustituye una variable e indica un salto de línea.

* `finish-output` (Limpieza de Buffer): Fuerza la impresión inmediata del texto en la consola antes de esperar la entrada del usuario.

* `let` (Entorno de Variables Locales): Define un bloque temporal para declarar variables (como `respuesta` o `arbol`) que solo existen dentro de esa función.

* `read` (Lectura de Entrada): Lee el símbolo o texto escrito por el usuario en la terminal.

* `string-upcase` y `string`: Convierten el dato ingresado por el usuario a una cadena de texto en mayúsculas (`"A"`, `"B"`, `"C"`) para evitar errores por minúsculas.

* `cond` (Evaluación Múltiple / Switch): Evalúa cuál opción seleccionó el usuario (`A`, `B` o `C`) para determinar hacia cuál subárbol debe desplazarse la ejecución recursiva.

## Conclusión
La implementación de un Akinator con árboles trinarios en Common Lisp demuestra la versatilidad de las estructuras de datos jerárquicas y la elegancia del paradigma funcional. Al vincular cada decisión a una de tres ramas y descender recursivamente a lo largo de 3 niveles de profundidad, el programa descarta escenarios de forma determinista hasta alcanzar un nodo hoja. Este enfoque evita la necesidad de bucles tradicionales (for o while) y garantiza un flujo ordenado y libre de errores sintácticos.