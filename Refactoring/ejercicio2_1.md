# 2.1 Empleados 

```java
1.  public class EmpleadoTemporario {
2.      public String nombre;
3.      public String apellido;
4.      public double sueldoBasico = 0;
5.      public double horasTrabajadas = 0;
6.      public int cantidadHijos = 0;
7.      // ......
8.      
9.      public double sueldo() {
10.         return this.sueldoBasico 
11.             + (this.horasTrabajadas * 500) 
12.             + (this.cantidadHijos * 1000) 
13.             + (this.sueldoBasico * 0.13);
14.     }
15. }
16. 
17. public class EmpleadoPlanta {
18.     public String nombre;
19.     public String apellido;
20.     public double sueldoBasico = 0;
21.     public int cantidadHijos = 0;
22.     // ......
23. 
24.     public double sueldo() {
25.         return this.sueldoBasico 
26.             + (this.cantidadHijos * 2000)
27.             - (this.sueldoBasico * 0.13);
28.     }
29. }
30. 
31. public class EmpleadoPasante {
32.     public String nombre;
33.     public String apellido;
34.     public double sueldoBasico = 0;
35.     // ......
36. 
37.     public double sueldo() {
38.         return this.sueldoBasico - (this.sueldoBasico * 0.13);
39.     }
40. }
```

### Bad Smell: se rompe el encapsulamiento
Las variables de instancia de ambas clases, al tener visibilidad pública, rompen el encapsulamiento

### Refactoring: Encapsulate Field

```java
1.  public class EmpleadoTemporario {
2.      private String nombre;
3.      private String apellido;
4.      private double sueldoBasico = 0;
5.      private double horasTrabajadas = 0;
6.      private int cantidadHijos = 0;
7.      // ......
8.      
9.      public double sueldo() {
10.         return this.sueldoBasico 
11.             + (this.horasTrabajadas * 500) 
12.             + (this.cantidadHijos * 1000) 
13.             + (this.sueldoBasico * 0.13);
14.     }
15. }
16. 
17. public class EmpleadoPlanta {
18.     private String nombre;
19.     private String apellido;
20.     private double sueldoBasico = 0;
21.     private int cantidadHijos = 0;
22.     // ......
23. 
24.     public double sueldo() {
25.         return this.sueldoBasico 
26.             + (this.cantidadHijos * 2000)
27.             - (this.sueldoBasico * 0.13);
28.     }
29. }
30. 
31. public class EmpleadoPasante {
32.     private String nombre;
33.     private String apellido;
34.     private double sueldoBasico = 0;
35.     // ......
36. 
37.     public double sueldo() {
38.         return this.sueldoBasico - (this.sueldoBasico * 0.13);
39.     }
40. }
```

### Bad Smell: Duplicate Code
Las variables de instancia de las tres clases se repiten

### Refactoring: Extract Superclass
nota: `Extract Superclass` ya implica `Pull Up Method`, no es necesario mencionarlo 

```java
1.  public abstract class Empleado {
2.      private String nombre;
3.      private String apellido;
4.      private double sueldoBasico = 0;
5.  
6.      public String getNombre() {
7.          return nombre;
8.      }
9.  
10.     public String getApellido() {
11.         return apellido;
12.     }
13. 
14.     public double getSueldoBasico() {
15.         return sueldoBasico;
16.     }
17. }
18. 
19. public class EmpleadoTemporario extends Empleado{
20.     private double horasTrabajadas = 0;
21.     private int cantidadHijos = 0;
22.     // ......
23.     
24.     public double sueldo() {
25.         return this.getSueldoBasico() 
26.             + (this.horasTrabajadas * 500) 
27.             + (this.cantidadHijos * 1000) 
28.             + (this.sueldoBasico * 0.13);
29.     }
30. }
31. 
32. public class EmpleadoPlanta extends Empleado{
33.     private int cantidadHijos = 0;
34.     // ......
35. 
36.     public double sueldo() {
37.         return this.getSueldoBasico() 
38.             + (this.cantidadHijos * 2000)
39.             - (this.sueldoBasico * 0.13);
40.     }
41. }
42. 
43. public class EmpleadoPasante extends Empleado{
44.     // ......
45. 
46.     public double sueldo() {
47.         return this.getSueldoBasico() - (this.sueldoBasico * 0.13);
48.     }
49. }
```

### Bad Smell: Duplicate Code
Los métodos `sueldo` de cada subclase poseen lógica que puede ser agrupada en otros métodos

### Refactoring: Extract Method

```java
1.  public abstract class Empleado {
2.      private String nombre;
3.      private String apellido;
4.      private double sueldoBasico = 0;
5.  
6.      public String getNombre() {
7.          return nombre;
8.      }
9.  
10.     public String getApellido() {
11.         return apellido;
12.     }
13. 
14.     public double getSueldoBasico() {
15.         return sueldoBasico;
16.     }
17. }
18. 
19. public class EmpleadoTemporario extends Empleado{
20.     private double horasTrabajadas = 0;
21.     private int cantidadHijos = 0;
22.     // ......
23. 
24.     public double calcularBonificacionHorasTrabajadas() {
25.         return this.horasTrabajadas * 500;
28.     }
29. 
        public double calcularBonificacionPorHijos() {
            return this.cantidadHijos * 1000;
        }

        public calcularSustraccionSegunSueldoBasico() {
            return this.sueldoBasico * 0.13;
        }

30.     public double sueldo() {
31.         return this.getSueldoBasico() 
32.             + this.calcularBonificacionHorasTrabajadas()
                + this.calcularBonificacionPorHijos()
                - this.calcularSustraccionSegunSueldoBasico();
33.     }
34. }
35. 
36. public class EmpleadoPlanta extends Empleado{
37.     private int cantidadHijos = 0;
38.     // ......
39. 
        public double calcularBonificacionPorHijos() {
            return this.cantidadHijos * 1000;
        }

        public calcularSustraccionSegunSueldoBasico() {
            return this.sueldoBasico * 0.13;
        }
44. 
45.     public double sueldo() {
46.         return this.getSueldoBasico() 
47.             + this.calcularBonificacionPorHijos()
                - this.calcularSustraccionSegunSueldoBasico()
48.     }
49. }
50. 
51. public class EmpleadoPasante extends Empleado{
52.     // ......
53. 
54.     public calcularSustraccionSegunSueldoBasico() {
            return this.sueldoBasico * 0.13;
        }
57. 
58.     public double sueldo() {
59.         return this.getSueldoBasico() 
60.             - this.calcularSustraccionSegunSueldoBasico();
61.     }
62. }
```

### Bad Smell: Duplicate Code
Se repiten métodos `sueldo()` y `calcularSustraccionSegunSueldoBasico()` en las subclases. 

### Refactoring: Form Template Method
nota: `Form Template Method` puede implicar `Extract Method`, para luego realizar `Pull Up Method` de los pasos comunes y un override en las subclases para el comportamiento específico.
El cambio de `calcularSustraccionSegunSueldoBasico()` de público a privado (cuando se subió a la superclase) debería haber implicado un refactoring aparte

```java
1.  public abstract class Empleado {
2.      private String nombre;
3.      private String apellido;
4.      private double sueldoBasico = 0;
5.  
6.      public String getNombre() {
7.          return nombre;
8.      }
9.  
10.     public String getApellido() {
11.         return apellido;
12.     }
13. 
14.     public double getSueldoBasico() {
15.         return sueldoBasico;
16.     }
17. 
18.     public double sueldo() {
19.         this.getSueldoBasico() - this.calcularSustraccionSegunSueldoBasico();
20.     }
21. 
22.     private double calcularSustraccionSegunSueldoBasico() {
23.         return this.sueldoBasico * 0.13;
24.     }
25. }
26. 
27. public class EmpleadoTemporario extends Empleado{
28.     private double horasTrabajadas = 0;
29.     private int cantidadHijos = 0;
30.     // ......
31. 
32.     public double calcularBonificacionHorasTrabajadas() {
33.         return this.horasTrabajadas * 500;
34.     }
35. 
36.     public double calcularBonificacionPorHijos() {
37.         return this.cantidadHijos * 1000;
38.     }
39. 
40.     public double sueldo() {
41.         return super.sueldo() 
42.             + this.calcularBonificacionHorasTrabajadas()
43.             + this.calcularBonificacionPorHijos();
44.     }
45. }
46. 
47. public class EmpleadoPlanta extends Empleado{
48.     private int cantidadHijos = 0;
49.     // ......
50. 
51.     public double calcularBonificacionPorHijos() {
52.         return this.cantidadHijos * 1000;
53.     }
54. 
55.     public double sueldo() {
56.         return super.sueldo() 
57.             + this.calcularBonificacionPorHijos();
58.     }
59. }
60. 
61. public class EmpleadoPasante extends Empleado{
62.     // ......
63. }
```

### Bad Smell: Lazy Class
Clase `EmpleadoPasante` es anémica, pero se decidió dejar para no alterar la expresividad y comportamiento del programa

### Bad Smell: nombre de método poco explicativo
Nombre de método `sueldo` es poco explicativo

### Refactoring: Rename Method

```java
1.  public abstract class Empleado {
2.      private String nombre;
3.      private String apellido;
4.      private double sueldoBasico = 0;
5.  
6.      public String getNombre() {
7.          return nombre;
8.      }
9.  
10.     public String getApellido() {
11.         return apellido;
12.     }
13. 
14.     public double getSueldoBasico() {
15.         return sueldoBasico;
16.     }
17. 
18.     public double calcularSueldoNeto() {
19.         this.getSueldoBasico() - this.calcularSustraccionSegunSueldoBasico();
20.     }
21. 
22.     private double calcularSustraccionSegunSueldoBasico() {
23.         return this.sueldoBasico * 0.13;
24.     }
25. }
26. 
27. public class EmpleadoTemporario extends Empleado{
28.     private double horasTrabajadas = 0;
29.     private int cantidadHijos = 0;
30.     // ......
31. 
32.     public double calcularBonificacionHorasTrabajadas() {
33.         return this.horasTrabajadas * 500;
34.     }
35. 
36.     public double calcularBonificacionPorHijos() {
37.         return this.cantidadHijos * 1000;
38.     }
39. 
40.     public double calcularSueldoNeto() {
41.         return super.calcularSueldoNeto() 
42.             + this.calcularBonificacionHorasTrabajadas()
43.             + this.calcularBonificacionPorHijos();
44.     }
45. }
46. 
47. public class EmpleadoPlanta extends Empleado{
48.     private int cantidadHijos = 0;
49.     // ......
50. 
51.     public double calcularBonificacionPorHijos() {
52.         return this.cantidadHijos * 1000;
53.     }
54. 
55.     public double calcularSueldoNeto() {
56.         return super.calcularSueldoNeto() 
57.             + this.calcularBonificacionPorHijos();
58.     }
59. }
60. 
61. public class EmpleadoPasante extends Empleado{
62.     // ......
63. }
```

### Bad Smell: Duplicate Code
Las clases `EmpleadoPlanta` y `EmpleadoTemporario` tienen ambas un método `calcularBonificacionPorHijos()` idéntico

### Refactoring: Extract Superclass
El refactoring implica **Pull Up Method** y **Pull Up Field**

```java
1.  public abstract class Empleado {
2.      private String nombre;
3.      private String apellido;
4.      private double sueldoBasico = 0;
5.  
6.      public String getNombre() {
7.          return nombre;
8.      }
9.  
10.     public String getApellido() {
11.         return apellido;
12.     }
13. 
14.     public double getSueldoBasico() {
15.         return sueldoBasico;
16.     }
17. 
18.     public double calcularSueldoNeto() {
19.         this.getSueldoBasico() - this.calcularSustraccionSegunSueldoBasico();
20.     }
21. 
22.     private double calcularSustraccionSegunSueldoBasico() {
23.         return this.sueldoBasico * 0.13;
24.     }
25. }
26. 
27. public abstract class EmpleadoContratado extends Empleado {
28.     private int cantidadHijos = 0;
29. 
30.     public double calcularBonificacionPorHijos() {
31.         return this.cantidadHijos * 1000;
32.     }
33. 
34.     public double calcularSueldoNeto() {
35.         return super.calcularSueldoNeto() 
36.             + this.calcularBonificacionPorHijos();
37.     }
38. }
39. 
40. public class EmpleadoTemporario extends EmpleadoContratado {
41.     private double horasTrabajadas = 0;
42.     // ......
43. 
44.     public double calcularBonificacionHorasTrabajadas() {
45.         return this.horasTrabajadas * 500;
46.     }
47. 
48.     public double calcularSueldoNeto() {
49.         return super.calcularSueldoNeto() 
50.             + this.calcularBonificacionHorasTrabajadas();
51.     }
52. }
53. 
54. public class EmpleadoPlanta extends EmpleadoContratado {
55.     // ......
56. }
57. 
58. public class EmpleadoPasante extends Empleado {
59.     // ......
60. }
```

### Bad Smell: Lazy Class
Clase `EmpleadoPlanta` es anémica. Se decide dejar para mantener expresividad y comportamiento.