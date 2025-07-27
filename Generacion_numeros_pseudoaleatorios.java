import java.util.ArrayList;
import java.util.List;

public class GeneradorPseudoaleatorio {
    // Parámetros del generador de congruencia lineal
    private long semilla; // Semilla inicial
    private final long a = 1664525; // Multiplicador
    private final long c = 1013904223; // Incremento
    private final long m = (long) Math.pow(2, 32); // Módulo (2^32)

    // Constructor
    public GeneradorPseudoaleatorio(long semilla) {
        this.semilla = semilla;
    }

    // Genera el siguiente número pseudoaleatorio
    private long siguienteAleatorio() {
        semilla = (a * semilla + c) % m;
        return semilla;
    }

    // Genera una lista de n números pseudoaleatorios y muestra los pasos
    public List<Long> generarNumerosAleatorios(int n) {
        List<Long> numerosAleatorios = new ArrayList<>();
        System.out.println("\nPRUEBA DE ESCRITORIO - GENERACION DE NUMEROS PSEUDOALEATORIOS (LCG)");
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("Parametros: a = " + a + ", c = " + c + ", m = " + m);
        System.out.println("Semilla inicial: " + semilla);
        System.out.println("\nIteraciones:");

        long semillaActual = semilla;
        for (int i = 0; i < n; i++) {
            System.out.println("\nIteracion " + (i + 1) + ":");
            System.out.println("  X_" + i + " = " + semillaActual);
            long valorSiguiente = (a * semillaActual + c) % m;
            System.out.println("  Calculo: (" + a + " * " + semillaActual + " + " + c + ") mod " + m + " = " + valorSiguiente);
            numerosAleatorios.add(valorSiguiente);
            semillaActual = valorSiguiente;
            System.out.println("  Numero generado: " + valorSiguiente);
        }

        return numerosAleatorios;
    }

    // Método principal para probar el generador
    public static void main(String[] args) {
        // Configuración: semilla inicial y número de valores a generar
        long semilla = 12345; // Semilla inicial
        int n = 10; // Generar 10 números

        GeneradorPseudoaleatorio generador = new GeneradorPseudoaleatorio(semilla);
        List<Long> numeros = generador.generarNumerosAleatorios(n);

        // Mostrar resultados finales
        System.out.println("\nNumeros pseudoaleatorios generados:");
        for (int i = 0; i < numeros.size(); i++) {
            System.out.println("Numero " + (i + 1) + ": " + numeros.get(i));
        }
    }
}
