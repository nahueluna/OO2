# 2.2 Juego 

```java
01  public class Juego {
02      // ......
03      public void incrementar(Jugador j) {
04          j.puntuacion = j.puntuacion + 100;
05      }
06 
07      public void decrementar(Jugador j) {
08          j.puntuacion = j.puntuacion - 50;
09      }
10  }
11   
12  public class Jugador {
13      public String nombre;
14      public String apellido;
15      public int puntuacion = 0;
16  }
```

### Bad Smell: Breaking Encapsulation
Variables de instancia de `Jugador` son públicas

### Refactoring: Encapsulate Field

```java
01  public class Juego {
02      // ......
03      public void incrementar(Jugador j) {
04          j.puntuacion = j.puntuacion + 100;
05      }
06 
07      public void decrementar(Jugador j) {
08          j.puntuacion = j.puntuacion - 50;
09      }
10  }
11   
12  public class Jugador {
13      private String nombre;
14      private String apellido;
15      private int puntuacion = 0;
16
17      public String getNombre() {
18          return this.nombre;
19      }
20
21      public String getApellido() {
22          return this.apellido;
23      }
24
25      public int getPuntuacion() {
26          return this.puntuacion;
27      }
28  }
```

### Bad Smell: Feature Envy
`Juego` toma datos de `Jugador` para realizar tarea que no es su responsabilidad

### Refactoring: Move Method
nota: Move Method puede eliminar el método en la clase donde estaba o convertirlo en una delegación

```java
01  public class Juego {
02      // ......
03      public void incrementar(Jugador j) {
04          j.incrementar();
05      }
06 
07      public void decrementar(Jugador j) {
08          j.decrementar();
09      }
10  }
11   
12  public class Jugador {
13      private String nombre;
14      private String apellido;
15      private int puntuacion = 0;
16
17      public String getNombre() {
18          return this.nombre;
19      }
20
21      public String getApellido() {
22          return this.apellido;
23      }
24
25      public int getPuntuacion() {
26          return this.puntuacion;
27      }
28
29      public void incrementar() {
30          this.puntuacion = this.puntuacion + 100;
31      }
32
33      public void decrementar() {
34          this.puntuacion = this.puntuacion - 50;
35      }
36  }
```

### Bad Smell: Nombre de métodos poco explicativos
Métodos `incrementar()` y `decrementar()` tiene nombres poco explicativos

### Refactoring: Rename Method

```java
01  public class Juego {
02      // ......
03      public void incrementarPuntuacionJugador(Jugador j) {
04          j.incrementar();
05      }
06 
07      public void decrementarPuntuacionJugador(Jugador j) {
08          j.decrementar();
09      }
10  }
11   
12  public class Jugador {
13      private String nombre;
14      private String apellido;
15      private int puntuacion = 0;
16
17      public String getNombre() {
18          return this.nombre;
19      }
20
21      public String getApellido() {
22          return this.apellido;
23      }
24
25      public int getPuntuacion() {
26          return this.puntuacion;
27      }
28
29      public void incrementarPuntuacion() {
30          this.puntuacion = this.puntuacion + 100;
31      }
32
33      public void decrementarPuntuacion() {
34          this.puntuacion = this.puntuacion - 50;
35      }
36  }
```