# 1.3 Cálculos 

Analice el código que se muestra a continuación. Indique qué code smells encuentra y cómo pueden corregirse.

```java
1. public void imprimirValores() {
2.     int totalEdades = 0;
3.     double promedioEdades = 0;
4.     double totalSalarios = 0;
5.     
6.     for (Empleado empleado : personal) {
7.         totalEdades = totalEdades + empleado.getEdad();
8.         totalSalarios = totalSalarios + empleado.getSalario();
9.     }
10.    promedioEdades = totalEdades / personal.size();
11.        
12.    String message = String.format("El promedio de las edades es %s y el total de salarios es %s", promedioEdades, totalSalarios);
13.    
14.    System.out.println(message);
15. }
```
### Bad Smell: Reinventando la rueda
Bucle for de las líneas 6 a 9.

### Refactoring: Replace Loop with Pipeline

```java
1. public void imprimirValores() {
2.    int totalEdades = this.personal.stream().mapToInt(p -> p.getEdad()).sum();
3.    double promedioEdades = 0;
4.    double totalSalarios = this.personal.stream().mapToDouble(p -> p.getSalario()).sum();
5.     
6.    promedioEdades = totalEdades / personal.size();
7.        
8.    String message = String.format("El promedio de las edades es %s y el total de salarios es %s", promedioEdades, totalSalarios);
9.    
10.   System.out.println(message);
11. }
```

### Bad Smell: nombre de método poco explicativo
Nombre de método `imprimirValores()` no explica su función de forma explícita.

### Refactoring: Rename Method

```java
1. public void imprimirEdadYTotalSalarioEmpleados() {
2.    int totalEdades = this.personal.stream().mapToInt(p -> p.getEdad()).sum();
3.    double promedioEdades = 0;
4.    double totalSalarios = this.personal.stream().mapToDouble(p -> p.getSalario()).sum();
5.     
6.    promedioEdades = totalEdades / personal.size();
7.        
8.    String message = String.format("El promedio de las edades es %s y el total de salarios es %s", promedioEdades, totalSalarios);
9.    
10.   System.out.println(message);
11. }
```

### Bad Smell: variable innecesaria
Variable `totalEdades` solo es utilizada para el cálculo almacenado en `promedioEdades`

### Refactoring: Eliminar variable innecesaria y agrupar funcionalidad

```java
1. public void imprimirEdadYTotalSalarioEmpleados() {
2.    double promedioEdades = this.personal.stream().mapToInt(p -> p.getEdad()).average().orElse(0);
3.    double totalSalarios = this.personal.stream().mapToDouble(p -> p.getSalario()).sum();
4.     
5.        
6.    String message = String.format("El promedio de las edades es %s y el total de salarios es %s", promedioEdades, totalSalarios);
7.    
8.    System.out.println(message);
9. }
```

### Bad Smell: Long Method

### Refactoring: Replace Temp with Query
Las variables temporales `promedioEdades` y `totalSalarios` pueden ser reemplazadas por métodos. Todas sus referencias se reemplazan con la nueva expresión

```java
1.  public void imprimirEdadYTotalSalarioEmpleados() {
2.      String message = String.format("El promedio de las edades es %s y el total de salarios es %s", calcularPromedioEdadEmpleados(), calcularTotalSalarioEmpleados());
3.    
4.      System.out.println(message);
5. }
6.
7.  private int calcularPromedioEdadEmpleados() {
8.      return this.personal.stream().mapToInt(p -> p.getEdad()).average().orElse(0);
9. }
10. 
11. private int calcularTotalSalarioEmpleados() {
12.     return this.personal.stream().mapToDouble(p -> p.getSalario()).sum();
13. }
```