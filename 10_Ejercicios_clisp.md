# Reporte: Taller de Prácticas en Common Lisp (CLISP)

## Introducción
Common Lisp es un lenguaje del paradigma funcional caracterizado por su sintaxis basada en Notación Polaca (prefija) y expresiones delimitadas por paréntesis `(función arg1 arg2...)`[cite: 23]. En esta serie de ejercicios exploramos el control de flujo y la toma de decisiones estructurada mediante el uso de formas especiales y macros como `if`, `when`, `unless`, `case`, `cond` y `typecase`, aplicando además estructuras de datos personalizadas (`defstruct`) para construir módulos de validación robustos y seguros.

## Resumen de qué se va a lograr con esta práctica
El objetivo de este taller es resolver 10 problemas progresivos de lógica y validación de datos en CLISP, aplicando la mejor herramienta condicional para cada escenario particular:
1. **Validación de edad (`if`):** Clasificación por categorías con `if` anidados.
2. **Alertas de seguridad (`when`):** Emisión de avisos no bloqueantes utilizando expresiones `when` independientes.
3. **Control de datos incompletos (`unless`):** Aplicación de patrón de guardianes para abortar de forma limpia con `return-from`.
4. **Control de roles (`case`):** Evaluación discreta sobre símbolos.
5. **Evaluación de rangos (`cond`):** Manejo de promedios académicos organizados en cláusulas en orden jerárquico.
6. **Lógica compuesta (`and` / `or` / `not` + `if`):** Registro de usuario combinando condiciones lógicas en una sola expresión.
7. **Clasificación por tipos (`typecase`):** Sistema de triage médico que reacciona según la naturaleza del dato recibido.
8. **Cotizador de envíos (`case` + `when`):** Integración de tablas de servicio con recargos opcionales.
9. **Validación de formulario completo:** Combinación limpia de `if`, `when`, `unless` y `case` en un flujo unificado.
10. **Mini sistema de crédito escolar:** Módulo integrado basado en `defstruct` para evaluar solicitudes estudiantiles.

---

## Código Fuente

### Ejercicio 1 — `if`: Edad para cine
**Descripción:** Valida la edad ingresada clasificándola en categorías cinematográficas (AA, B, B15/C) utilizando exclusivamente condicionales `if` anidados (sin `cond` ni `case`). Retorna `T` para entradas válidas y `NIL` ante errores.

```lisp
(defun validar-edad-cine (edad)
  (if (not (integerp edad))
      (progn
        (format t "error: La edad debe ser un entero~%")
        nil)
      (if (< edad 0)
          (progn
            (format t "error: Edad negativa~%")
            nil)
          (if (> edad 120)
              (progn
                (format t "error: Edad no realista~%")
                nil)
              (if (< edad 13)
                  (progn
                    (format t "ok: AA (infantil)~%")
                    t)
                  (if (< edad 18)
                      (progn
                        (format t "ok: B (adolescentes)~%")
                        t)
                      (progn
                        (format t "ok: B15/C (adultos)~%")
                        t)))))))
```

---

### Ejercicio 2 — `when`: Avisos, no bloqueos
**Descripción:** Revisa una contraseña evaluando tres riesgos independientes (longitud, claves comunes y ausencia de mayúsculas) utilizando expresiones `when` independientes sin rama `else`. La función siempre retorna `T`.

```lisp
(defun avisar-password (clave)
  ;; 1. Evaluación de longitud mínima
  (when (< (length clave) 8)
    (format t "Aviso: clave corta (< 8)~%"))
  
  ;; 2. Evaluación de contraseñas comunes
  (when (or (string= clave "12345678") 
            (string= clave "password"))
    (format t "Aviso: clave demasiado común~%"))
  
  ;; 3. Evaluación de mayúsculas
  (when (and (string= clave (string-downcase clave)) 
             (> (length clave) 0))
    (format t "Aviso: no hay mayúsculas~%"))
  
  ;; Siempre retorna T
  t)
```

---

### Ejercicio 3 — `unless`: No procesar si faltan datos
**Descripción:** Implementa la técnica de "guardianes" mediante `unless` y `return-from` para validar una solicitud web, evitando anidamientos innecesarios y manteniendo el flujo de éxito al final.

```lisp
(defun reportar-error (mensaje)
  (format t "error: ~a~%" mensaje)
  nil)

(defun procesar-solicitud (nombre correo edad acepta-terminos)
  (declare (ignore edad))

  ;; Guardian 1: Nombre válido
  (unless (and (stringp nombre) (> (length nombre) 0))
    (return-from procesar-solicitud 
      (reportar-error "Falta nombre")))

  ;; Guardian 2: Correo válido con '@'
  (unless (and (stringp correo) (search "@" correo))
    (return-from procesar-solicitud 
      (reportar-error "Correo inválido")))

  ;; Guardian 3: Términos aceptados
  (unless acepta-terminos
    (return-from procesar-solicitud 
      (reportar-error "Debe aceptar términos")))

  ;; Camino feliz
  (format t "ok: Solicitud de ~a procesada~%" nombre)
  t)
  ```
  
  ---

### Ejercicio 4 — `case`: Roles de un sistema escolar
**Descripción:** Determina permisos de usuario evaluando símbolos discretos mediante `case` (comparación con `eql`). Entradas de tipo string son canalizadas al caso `otherwise`.  

```lisp
(defun permiso-por-rol (rol)
  ;; Nota: Los strings ingresados como "admin" caen en 'otherwise' porque eql no iguala símbolos con cadenas.
  (case rol
    (admin
     (format t "Puede crear usuarios y materias~%")
     t)
    (docente
     (format t "Puede calificar y pasar lista~%")
     t)
    (alumno
     (format t "Puede consultar calificaciones~%")
     t)
    (padre
     (format t "Puede ver boleta de sus hijos~%")
     t)
    (otherwise
     (format t "Rol no reconocido~%")
     nil)))
```

---

### Ejercicio 5 — `cond`: Semáforo de promedio
**Descripción:** Evalúa un promedio académico numérico dentro del rango $[0, 100]$ utilizando `unless` para la validación previa y `cond` para clasificar en categorías según el nivel alcanzado.

```lisp
(defun semaforo-promedio (p)
  ;; Validación de tipo
  (unless (numberp p)
    (format t "error: El promedio debe ser un número~%")
    (return-from semaforo-promedio nil))

  ;; Validación de rango
  (unless (and (>= p 0) (<= p 100))
    (format t "error: Promedio fuera de [0, 100]~%")
    (return-from semaforo-promedio nil))

  ;; Evaluación de rangos
  (cond
    ((< p 60) (format t "ROJO: Reprobado~%"))
    ((< p 80) (format t "AMARILLO: Regular~%"))
    ((< p 90) (format t "VERDE: Bien~%"))
    (t        (format t "ORO: Excelencia~%")))

  t)
```

---

### Ejercicio 6 — `and` / `or` / `not` + `if`: Alta de usuario
**Descripción:** Procesa el registro de un usuario evaluando cinco restricciones simultáneas en una única expresión lógica `and` dentro de un sólo `if` principal.

```lisp
(defun reportar-ok (mensaje)
  (format t "ok: ~a~%" mensaje)
  t)

(defun reportar-error-alta (mensaje)
  (format t "error: ~a~%" mensaje)
  nil)

(defun alta-usuario (user pass edad pais bloqueado)
  (if (and (stringp user) (> (length user) 4)
           (stringp pass) (> (length pass) 8)
           (numberp edad) (> edad 13)
           (or (string= pais "MX") 
               (string= pais "CO") 
               (string= pais "AR"))
           (not bloqueado))
      (reportar-ok "Usuario dado de alta")
      (reportar-error-alta "Alta rechazada")))
```

---

### Ejercicio 7 — `typecase`: Triage según el tipo del dato
**Descripción:** Sistema de clasificación médica que inspecciona el tipo de dato recibido (`number`, `string` o `list`) en el nivel superior mediante `typecase` para ejecutar su respectivo diagnóstico.

```lisp
(defun clasificar-temperatura (temp)
  (cond
    ((< temp 35) "HIPOTERMIA")
    ((< temp 37.5) "NORMAL")
    ((< temp 39) "FIEBRE")
    (t "FIEBRE ALTA")))

(defun clasificar-triage (dato)
  (typecase dato
    (number
     (format t "Resultado: ~a~%" (clasificar-temperatura dato))
     t)
    (string
     (cond
       ((string-equal dato "rojo")    (format t "Resultado: ROJO (Urgencia mayor)~%") t)
       ((string-equal dato "naranja") (format t "Resultado: NARANJA (Urgencia alta)~%") t)
       ((string-equal dato "verde")   (format t "Resultado: VERDE (Atención normal)~%") t)
       (t (format t "error: Código de color no válido~%") nil)))
    (list
     (let ((nombre (first dato))
           (temp (second dato)))
       (if (numberp temp)
           (progn
             (format t "Paciente ~a: ~a~%" nombre (clasificar-temperatura temp))
             t)
           (progn
             (format t "error: Temperatura inválida~%")
             nil))))
    (t
     (format t "error: Tipo no soportado~%")
     nil)))
```

---

### Ejercicio 8 — `case` de códigos + `when` de recargo (Paquetería)
**Descripción:** Determina el costo base de un envío con `case`, valida el límite de peso con `unless`, y calcula recargos por zona de riesgo mediante `when` sin interrumpir la operación.

```lisp
(defun cotizar-envio (codigo peso zona-riesgo)
  (let ((info (case codigo
                (est '(80 20 "Estándar"))
                (exp '(160 10 "Express"))
                (noc '(220 5 "Nocturno"))
                (int '(450 15 "Internacional"))
                (otherwise nil))))
    
    (unless info
      (format t "error: Código de servicio inválido~%")
      (return-from cotizar-envio nil))

    (let ((costo-base (first info))
          (peso-max (second info)))
      
      (unless (<= peso peso-max)
        (format t "error: Excede peso del servicio~%")
        (return-from cotizar-envio nil))

      (let ((costo-final costo-base))
        (when zona-riesgo
          (incf costo-final 40)
          (format t "Recargo zona de riesgo +40~%"))

        (format t "Costo final del envío: $~a~%" costo-final)
        t))))
```

---

### Ejercicio 9 — Formulario completo: Mezclar `if`, `when`, `unless` y `case`
**Descripción:** Integra los cuatro constructores condicionales en un flujo unificado para validar un registro de usuario completo, controlando términos, edades, planes, roles y seguridad de contraseña.

```lisp
(defun validar-registro (usuario correo edad rol plan password acepta-terminos)
  (declare (ignore correo))

  ;; 1. [UNLESS] Términos
  (unless acepta-terminos
    (format t "error: Debe aceptar términos y condiciones~%")
    (return-from validar-registro nil))

  ;; 2. [IF] Edad / Plan
  (if (< edad 13)
      (progn
        (format t "error: Menor de 13 años no permitido~%")
        (return-from validar-registro nil))
      (if (and (>= edad 13) (<= edad 17))
          (unless (eq plan 'campus)
            (format t "error: Entre 13 y 17 años solo se permite plan 'campus'~%")
            (return-from validar-registro nil))))

  ;; 3. [CASE] Rol
  (let ((prefijo (case rol
                   (alumno "a/")
                   (docente "d/")
                   (admin "s/")
                   (otherwise nil))))
    (unless prefijo
      (format t "error: Rol no válido~%")
      (return-from validar-registro nil))

    ;; 4. [CASE] Plan
    (let ((cuota (case plan
                   (libre 0)
                   (pro 99)
                   (campus 0)
                   (otherwise nil))))
      (unless cuota
        (format t "error: Plan no válido~%")
        (return-from validar-registro nil))

      ;; 5. [WHEN] Admin
      (when (eq rol 'admin)
        (format t "Aviso: Admin creado: revisa bitácora de auditoría~%"))

      ;; 6. [WHEN] Password (reutiliza lógica de avisos)
      (avisar-password password)

      ;; 7. Registro exitoso
      (format t "ok: Cuenta creada: ~a~a | Cuota: $~a~%" prefijo usuario cuota)
      t)))
```

---

### Ejercicio 10 — Mini sistema: Crédito escolar
**Descripción:** Módulo compuesto que utiliza `defstruct` para modelar a los alumnos solicitantes y múltiples funciones auxiliares para validar datos, penalizar historiales y decidir la aprobación del crédito de material.

```lisp
;; Definición de la estructura
(defstruct solicitante
  nombre
  promedio
  materias-reprobadas
  beca
  monto
  historial
  semestre)

;; 1. [UNLESS] Validación de tipo y rangos
(defun validar-solicitante (s)
  (unless (and (numberp (solicitante-promedio s))
               (>= (solicitante-promedio s) 0)
               (<= (solicitante-promedio s) 100))
    (format t "error: Promedio fuera de rango [0, 100]~%")
    (return-from validar-solicitante nil))

  (unless (and (numberp (solicitante-monto s))
               (> (solicitante-monto s) 0))
    (format t "error: Monto debe ser mayor a 0~%")
    (return-from validar-solicitante nil))

  (unless (and (integerp (solicitante-semestre s))
               (>= (solicitante-semestre s) 1)
               (<= (solicitante-semestre s) 10))
    (format t "error: Semestre fuera de rango [1, 10]~%")
    (return-from validar-solicitante nil))

  t)

;; 2. [COND] Categoría de monto
(defun categoria-monto (monto)
  (cond
    ((< monto 2000) 'bajo)
    ((< monto 8000) 'medio)
    (t 'alto)))

;; 3. [CASE] Penalización por historial
(defun penalizacion-historial (historial)
  (case historial
    (limpio 0)
    (atrasos 2)
    (desconocio 1)
    (otherwise 99)))

;; 4. [IF/COND] Veredicto de crédito
(defun decidir-credito (s)
  (unless (validar-solicitante s)
    (return-from decidir-credito nil))

  (let* ((prom (solicitante-promedio s))
         (reprobadas (solicitante-materias-reprobadas s))
         (monto (solicitante-monto s))
         (hist (solicitante-historial s))
         (penalizacion (penalizacion-historial hist))
         (cat-monto (categoria-monto monto))
         (becado (solicitante-beca s))
         (veredicto nil)
         (motivo ""))

    (cond
      ((or (> reprobadas 3) (< prom 70) (>= penalizacion 99))
       (setf veredicto "RECHAZADO")
       (setf motivo "Promedio < 70 o materias reprobadas excesivas"))

      ;; Política: Un historial 'desconocio' nunca equivale a 'limpio',
      ;; por lo que envía al alumno a REVISION aunque posea beca.
      ((and (>= prom 85)
            (eq hist 'limpio)
            (or (eq cat-monto 'bajo) becado))
       (setf veredicto "APROBADO")
       (setf motivo "Alto promedio, limpio, monto bajo o beca"))

      (t
       (setf veredicto "REVISION")
       (setf motivo "Historial o condiciones requieren evaluación manual")))

    ;; 5. [WHEN] Prioridad a becados
    (format t "~a -> ~a (~a)~%" (solicitante-nombre s) veredicto motivo)
    (when (and becado (not (string= veredicto "RECHAZADO")))
      (format t "  -> Prioridad: solicitante becado~%"))

    veredicto))

;; 6. Iteración sobre banco de hechos
(defun ronda-credito (lista-solicitantes)
  (format t "=== RESULTADOS EVALUACION DE CREDITO ===~%")
  (dolist (s lista-solicitantes)
    (decidir-credito s)
    (terpri)))
```

---

## Conclusión
El desarrollo de estos diez ejercicios demuestra el poder de las estructuras condicionales en la programación funcional con Common Lisp. Comprender la diferencia entre macros como `when` (para efectos secundarios sin ramificación `else`), `unless` (para salidas limpias en estilo guardián), `case` (para mapeo rápido de símbolos), y `cond` o `typecase` (para evaluar rangos o tipos dinámicos) permite escribir un código legible, expresivo y libre de construcciones complejas. La integración final mediante estructuras (`defstruct`) ofrece una base sólida para diseñar aplicaciones modulares y mantenibles.