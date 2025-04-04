## 1.1 Protocolo de Cliente
La clase Cliente tiene el siguiente protocolo. ¿Cómo puede mejorarlo? 

```java {linenos=true}
1. /** 
2.  * Retorna el límite de crédito del cliente
3.  */
4. public double lmtCrdt() {...
5. 
6. /** 
7.  * Retorna el monto facturado al cliente desde la fecha f1 a la fecha f2
8.  */
9. protected double mtFcE(LocalDate f1, LocalDate f2) {...
10. 
11. /** 
12.  * Retorna el monto cobrado al cliente desde la fecha f1 a la fecha f2
13.  */
14. private double mtCbE(LocalDate f1, LocalDate f2) {...

```

En la línea 9 se observa un método getter con privacidad protected. Esto podría ser un indicio de **Inappropiate Intimacy**, pues un método cuya funcionalidad es retornar un valor, probablemente requiera ser público. Una situación similar pero más agravada sucede en la línea 14, donde el método es privado.
También debe considerarse que tanto los métodos como los parámetros poseen nombres poco explicativos, que no indican su función o propósito.

Deberían cambiarse las privacidades de los métodos y los nombres de métodos y parámetros