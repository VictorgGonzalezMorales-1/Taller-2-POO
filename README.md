# Taller 2 - Simulador Pokémon en Consola

## Descripción del proyecto

Este proyecto corresponde al **Taller 2 de Programación Orientada a Objetos**, desarrollado durante el **I Semestre de 2026** para la carrera de **Ingeniería Civil Industrial (ICI)**.

El proyecto consiste en la creación de un **juego interactivo por consola inspirado en Pokémon**, implementado en lenguaje **Java**. El sistema permite al jugador crear o continuar una partida, capturar Pokémon en distintos hábitats, administrar su equipo, enfrentar líderes de gimnasio, curar Pokémon debilitados, guardar el progreso y desbloquear el desafío contra el Alto Mando.

Toda la información del juego se carga y conserva mediante archivos de texto, permitiendo persistencia de datos entre ejecuciones. Para ello se utilizan archivos como `Pokedex.txt`, `Habitats.txt`, `Gimnasios.txt`, `Alto Mando.txt` y `Registros.txt`.

El sistema aplica principios de **Programación Orientada a Objetos**, distribuyendo responsabilidades entre distintas clases del dominio del juego, tales como jugador, Pokémon, Pokédex, hábitats, gimnasios, Alto Mando, batallas, memoria temporal y administración general.

---

## Funcionalidades principales

El programa permite:

- Crear una nueva partida.
- Continuar una partida guardada.
- Cargar información desde archivos `.txt`.
- Capturar Pokémon en diferentes hábitats.
- Evitar que el jugador capture Pokémon repetidos.
- Revisar el equipo actual.
- Intercambiar Pokémon mediante el PC.
- Retar gimnasios en orden progresivo.
- Combatir utilizando estadísticas totales.
- Aplicar efectividad de tipos mediante una matriz.
- Cambiar de Pokémon durante una batalla.
- Rendirse durante un combate.
- Curar Pokémon debilitados.
- Guardar la partida.
- Guardar y salir del juego.
- Desafiar al Alto Mando después de derrotar los ocho gimnasios.

---

## Integrantes

| Nombre | Carrera | RUT | Usuario GitHub |
|---|---|---|---|
| Victor Manuel Gonzalez Morales | Ingeniería Civil Industrial | 22.061.552-9 | VictorgGonzalezMorales-1 |
| Joaquin Esteban Torres Flores | Ingeniería Civil Industrial | 21.547.370-8 | JoaquinTFdev1 |

---

## Estructura general del proyecto

El proyecto se encuentra organizado en el paquete principal:

```txt
Taller2
```

La estructura general del proyecto es la siguiente:

```txt
Taller2/
│
├── Main.java
├── Administrador.java
├── Memoria.java
├── Jugador.java
├── Pokemon.java
├── Pokedex.java
├── Habitat.java
├── Gimnasios.java
├── AltosMandos.java
├── Batalla.java
└── TablaTipos.java

Archivos de datos:
│
├── Pokedex.txt
├── Habitats.txt
├── Gimnasios.txt
├── Alto Mando.txt
└── Registros.txt

Entregables:
│
├── DiagramaDeClases.pdf
├── ModeloDeDominio.pdf
└── README.md
```

---

## Clases principales

### `Main`

Clase principal del programa. Contiene el método `main()` y controla el flujo general del juego.

Responsabilidades principales:

- Cargar los archivos base del juego.
- Mostrar el menú inicial.
- Permitir continuar o crear una nueva partida.
- Mostrar el menú principal del jugador.
- Recibir opciones por consola.
- Coordinar captura, PC, gimnasios, Alto Mando, curación y guardado.

Flujo general:

```txt
Main inicia el programa
Main carga archivos .txt
Main muestra los menús
Main recibe la opción del usuario
Main solicita acciones a Administrador
```

---

### `Administrador`

Clase intermediaria entre `Main` y las demás clases del sistema.

Responsabilidades principales:

- Crear jugadores.
- Cargar información desde archivos.
- Crear objetos a partir de líneas de texto.
- Guardar información en archivos.
- Buscar Pokémon en la Pokédex.
- Crear hábitats.
- Crear gimnasios.
- Crear miembros del Alto Mando.
- Generar Pokémon aleatorios por hábitat.
- Registrar capturas.
- Evitar Pokémon repetidos.
- Gestionar intercambios en el PC.
- Solicitar permisos para acceder al Alto Mando.

Flujo general:

```txt
Main solicita una acción
Administrador procesa la solicitud
Administrador consulta o modifica Memoria
Memoria entrega datos
Administrador devuelve resultados a Main
```

---

### `Memoria`

Clase encargada de almacenar temporalmente los objetos principales durante la ejecución.

Almacena:

- Jugador actual.
- Lista de registros de la Pokédex.
- Lista de hábitats.
- Lista de gimnasios.
- Lista de miembros del Alto Mando.
- Texto preparado para guardar archivos.

Esta clase funciona como una memoria central del programa mientras el juego está abierto.

---

### `Jugador`

Representa al usuario dentro de la partida.

Contiene:

- Nombre o apodo del jugador.
- Gimnasios derrotados.
- Lista de Pokémon capturados.

Consideración importante: los primeros seis Pokémon capturados corresponden al **equipo principal** utilizado en batallas. Los Pokémon restantes pueden reorganizarse mediante el acceso al PC.

---

### `Pokemon`

Representa a cada Pokémon individual dentro del juego.

Atributos principales:

- Nombre.
- Vida.
- Vida máxima.
- Ataque.
- Defensa.
- Ataque especial.
- Defensa especial.
- Velocidad.
- Tipo.

También permite:

- Calcular estadísticas totales.
- Determinar si está vivo o debilitado.
- Curar Pokémon.
- Debilitar Pokémon.
- Crear copias independientes de Pokémon para no modificar directamente los registros base de la Pokédex.

La estadística total utilizada en combate se calcula como:

```txt
vida + ataque + defensa + ataqueEspecial + defensaEspecial + velocidad
```

---

### `Pokedex`

Representa los datos base de cada Pokémon cargado desde `Pokedex.txt`.

Contiene:

- Hábitat del Pokémon.
- Probabilidad de aparición.
- Objeto `Pokemon` con sus estadísticas base.

Esta clase permite construir Pokémon para:

- El jugador.
- Los gimnasios.
- Los miembros del Alto Mando.
- Los hábitats de captura.

---

### `Habitat`

Representa una zona donde pueden aparecer Pokémon.

Ejemplos de hábitats:

```txt
Lago
Cueva
Montaña
Bosque
Prado
Mar
```

Cada hábitat contiene Pokémon según su porcentaje de aparición. Para representar las probabilidades, se utiliza un arreglo de 100 posiciones, donde cada Pokémon se repite proporcionalmente a su probabilidad.

Ejemplo:

```txt
0.20 de aparición = 20 espacios en el arreglo
```

---

### `Gimnasios`

Representa a los líderes de gimnasio.

Cada gimnasio contiene:

- Número de gimnasio.
- Nombre del líder.
- Estado del gimnasio.
- Lista de Pokémon del líder.

Estados posibles:

```txt
Sin derrotar
Derrotado
```

El jugador debe derrotar los gimnasios en orden. No puede retar a un gimnasio posterior sin haber derrotado previamente los anteriores.

---

### `AltosMandos`

Representa a cada miembro del Alto Mando.

Cada miembro posee:

- Número identificador.
- Nombre.
- Lista de seis Pokémon.

El Alto Mando solo puede desafiarse si el jugador derrotó los ocho gimnasios. Los combates se realizan de forma consecutiva, sin regresar al menú entre miembros.

---

### `Batalla`

Clase encargada de administrar el estado de los combates.

Responsabilidades principales:

- Guardar al jugador participante.
- Guardar al oponente.
- Seleccionar el primer Pokémon vivo del jugador.
- Seleccionar el primer Pokémon vivo del rival.
- Cambiar el Pokémon activo del jugador.
- Actualizar Pokémon activos cuando uno queda debilitado.
- Verificar si quedan Pokémon vivos.
- Finalizar el combate cuando corresponde.

Controla combates contra:

- Gimnasios.
- Alto Mando.

También incorpora validaciones para evitar iniciar o continuar batallas si el jugador no tiene Pokémon vivos en el equipo principal.

---

### `TablaTipos`

Implementa la matriz de efectividad de tipos Pokémon.

Permite determinar el multiplicador de daño según el tipo del Pokémon del jugador y el tipo del Pokémon rival.

Multiplicadores posibles:

```txt
2.0  -> súper eficaz
1.0  -> daño normal
0.5  -> poco eficaz
0.0  -> sin efecto
```

---

## Archivos utilizados por el programa

### `Pokedex.txt`

Contiene la información base de todos los Pokémon disponibles.

Formato:

```txt
pokemon;habitat;porcentajeAparicion;vida;ataque;defensa;ataqueEspecial;defensaEspecial;velocidad;Tipo
```

Ejemplo:

```txt
Mawile;Cueva;0.2;50;85;85;55;55;50;Acero
```

---

### `Habitats.txt`

Contiene las zonas disponibles para explorar.

Formato:

```txt
Lago
Cueva
Montaña
Bosque
Prado
Mar
```

Cada línea representa una zona de captura.

---

### `Gimnasios.txt`

Contiene la información de los gimnasios, líderes, estados y Pokémon.

Formato:

```txt
N°Gimnasio;Lider;Estado;cantPokemons;Pokemon1;Pokemon2;...
```

Ejemplo:

```txt
1;EmmaLaArdillaRabiosa;Sin derrotar;3;Minun;Plusle;Emolga
```

---

### `Alto Mando.txt`

Contiene la información de los miembros del Alto Mando.

Formato:

```txt
N°AltoMando;Nombre;Pokemon1;Pokemon2;Pokemon3;Pokemon4;Pokemon5;Pokemon6
```

Ejemplo:

```txt
1;MartinGalactico;Magikarp;Noivern;Excadrill;Steelix;Lucario;Scizor
```

---

### `Registros.txt`

Archivo utilizado para guardar y cargar la partida del jugador.

Formato:

```txt
nombreCuenta;medallas
pokemon1;Estado
pokemon2;Estado
pokemon3;Estado
```

Ejemplo de partida sin medallas:

```txt
Clapt;none
Mawile;Vivo
Groudon;Vivo
```

Ejemplo de partida con un gimnasio derrotado:

```txt
Clapt;EmmaLaArdillaRabiosa
Mawile;Vivo
Groudon;Debilitado
```

---

## Flujo general de ejecución

```txt
1. Se ejecuta Main.java.
2. Main carga Pokedex.txt, Habitats.txt, Gimnasios.txt y Alto Mando.txt.
3. Administrador procesa los archivos y crea los objetos correspondientes.
4. Memoria almacena temporalmente los objetos creados.
5. Main muestra el menú inicial.
6. El jugador elige entre continuar, nueva partida o salir.
7. Si selecciona continuar, se carga Registros.txt.
8. Si selecciona nueva partida, se solicita un apodo y se inicializa el jugador.
9. Main muestra el menú principal.
10. Según la opción seleccionada, se ejecuta captura, PC, batalla, curación o guardado.
11. Si se guarda la partida, Administrador sobrescribe Registros.txt.
12. Si se guarda y sale, se actualizan los archivos y finaliza el programa.
```

---

## Menú inicial

Al ejecutar el programa se muestra:

```txt
1) Continuar.
2) Nueva Partida.
3) Salir.
```

### Opción 1: Continuar

Carga la partida guardada desde `Registros.txt`.

### Opción 2: Nueva Partida

Solicita el apodo del jugador, crea una nueva partida y reinicia los gimnasios.

### Opción 3: Salir

Finaliza el programa.

---

## Menú principal

Después de continuar o crear una nueva partida, se muestra:

```txt
1) Revisar equipo.
2) Salir a capturar.
3) Acceso al PC (cambiar Pokémon del equipo).
4) Retar un gimnasio.
5) Desafío al Alto Mando.
6) Curar Pokémon.
7) Guardar.
8) Guardar y Salir.
```

### 1) Revisar equipo

Muestra los primeros seis Pokémon del jugador, indicando nombre, tipo y estadísticas totales.

### 2) Salir a capturar

Muestra los hábitats disponibles. Al seleccionar una zona, aparece un Pokémon aleatorio según su porcentaje de aparición. El jugador puede capturarlo o huir.

### 3) Acceso al PC

Permite intercambiar posiciones entre Pokémon capturados. Esto permite modificar los primeros seis Pokémon del equipo principal.

Formato de intercambio:

```txt
1,3
```

### 4) Retar un gimnasio

Muestra los gimnasios disponibles y su estado. El jugador solo puede retar gimnasios en orden progresivo.

### 5) Desafío al Alto Mando

Disponible únicamente cuando los ocho gimnasios están derrotados. Los combates son consecutivos y no se guarda progreso parcial dentro del desafío.

### 6) Curar Pokémon

Cura los Pokémon debilitados del jugador.

### 7) Guardar

Sobrescribe `Registros.txt` con la información actual.

### 8) Guardar y Salir

Guarda la partida y finaliza el programa.

---

## Mecánica de combate

Los combates se resuelven comparando las estadísticas totales de los Pokémon.

Primero se calcula:

```txt
statsTotales = vida + ataque + defensa + ataqueEspecial + defensaEspecial + velocidad
```

Luego se aplica el multiplicador de efectividad de tipo obtenido desde `TablaTipos`.

Ejemplo:

```txt
Stats jugador = 380
Multiplicador = 2.0
Puntaje final jugador = 760
```

El Pokémon con mayor puntaje final gana el enfrentamiento. El Pokémon perdedor queda debilitado.

Durante la batalla, el jugador puede:

```txt
1) Atacar
2) Cambiar de Pokémon
3) Rendirse
```

---

## Reglas y restricciones

- Los primeros seis Pokémon capturados forman el equipo principal.
- Los Pokémon posteriores quedan almacenados y pueden reorganizarse mediante el PC.
- Los Pokémon debilitados no pueden combatir.
- Debe existir al menos un Pokémon vivo en el equipo principal para iniciar una batalla.
- El jugador no puede capturar dos veces el mismo Pokémon.
- Los gimnasios deben ser derrotados en orden.
- Para desafiar al Alto Mando se deben derrotar los ocho gimnasios.
- Si el jugador pierde o se rinde en el Alto Mando, vuelve al menú y debe iniciar el desafío desde el primer miembro.
- Los archivos `.txt` deben mantener exactamente el formato indicado.

---

## Control de errores

El programa incorpora validaciones para evitar caídas durante la ejecución, especialmente en:

- Entradas numéricas inválidas.
- Opciones fuera de rango.
- Líneas incompletas en archivos.
- Pokémon inexistentes en la Pokédex.
- Objetos nulos durante batalla.
- Intentos de combatir sin Pokémon vivos.
- Capturas repetidas.
- Hábitats sin Pokémon disponibles.
- Estados inválidos en gimnasios.

---

## Instrucciones de ejecución

### Requisitos

- Java JDK 8 o superior.
- IDE compatible con Java:
  - Eclipse.
  - IntelliJ IDEA.
  - Visual Studio Code.
  - NetBeans.

---

### Pasos para ejecutar

1. Clonar el repositorio desde GitHub:

```bash
git clone [URL_DEL_REPOSITORIO]
```

2. Abrir el proyecto en el IDE de preferencia.

3. Verificar que las clases se encuentren dentro del paquete:

```txt
Taller2
```

4. Verificar que los archivos `.txt` estén ubicados en la raíz del proyecto:

```txt
Pokedex.txt
Habitats.txt
Gimnasios.txt
Alto Mando.txt
Registros.txt
```

5. Verificar que los archivos PDF solicitados se encuentren en la raíz del repositorio:

```txt
DiagramaDeClases.pdf
ModeloDeDominio.pdf
```

6. Ejecutar la clase principal:

```txt
Main.java
```

7. Navegar mediante números ingresados por teclado.

8. Seguir las instrucciones mostradas en consola.

---

## Consideraciones para ejecución en Eclipse

1. Importar el proyecto como proyecto Java existente.
2. Verificar que el paquete sea `Taller2`.
3. Verificar que `Main.java` tenga el método `main`.
4. Verificar que los archivos `.txt` estén en la raíz del proyecto, no dentro de `src`.
5. Ejecutar con clic derecho sobre `Main.java`:

```txt
Run As → Java Application
```

---

## Tecnologías y conceptos aplicados

Este proyecto utiliza:

- Java.
- Programación Orientada a Objetos.
- Clases y objetos.
- Encapsulamiento.
- Abstracción.
- Colecciones dinámicas con `ArrayList`.
- Arreglos.
- Matrices bidimensionales.
- Lectura de archivos con `Scanner`.
- Escritura de archivos con `BufferedWriter`.
- Generación aleatoria con `Random`.
- Menús interactivos por consola.
- Persistencia de datos mediante archivos `.txt`.
- Manejo de errores con `try/catch`.

---

## Estado del proyecto

Proyecto desarrollado como entrega académica para el Taller 2 de Programación Orientada a Objetos, I Semestre 2026, carrera de Ingeniería Civil Industrial.
