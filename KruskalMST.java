import java.util.*;

class KruskalMST {
    // Clase para representar una arista
    static class Arista implements Comparable<Arista> {
        int origen, destino, peso;

        Arista(int origen, int destino, int peso) {
            this.origen = origen;
            this.destino = destino;
            this.peso = peso;
        }

        // Para ordenar las aristas por peso
        @Override
        public int compareTo(Arista otro) {
            return this.peso - otro.peso;
        }
    }

    // Clase para representar un subconjunto para Union-Find
    static class Subconjunto {
        int padre, rango;

        Subconjunto(int padre, int rango) {
            this.padre = padre;
            this.rango = rango;
        }
    }

    // Número de vértices en el grafo
    private int V;
    
    // Lista de aristas
    private List<Arista> aristas;

    KruskalMST(int vertices) {
        V = vertices;
        aristas = new ArrayList<>();
    }

    // Añadir una arista al grafo
    void AñadirArista(int origen, int destino, int peso) {
        aristas.add(new Arista(origen, destino, peso));
    }

    // Encontrar el padre de un elemento (con compresión de caminos)
    int find(Subconjunto[] subconjuntos, int i) {
        if (subconjuntos[i].padre != i) {
            subconjuntos[i].padre = find(subconjuntos, subconjuntos[i].padre);
        }
        return subconjuntos[i].padre;
    }

    // Unir dos subconjuntos
    void union(Subconjunto[] subconjuntos, int x, int y) {
        int xraiz = find(subconjuntos, x);
        int yraiz = find(subconjuntos, y);

        if (subconjuntos[xraiz].rango < subconjuntos[yraiz].rango) {
            subconjuntos[xraiz].padre = yraiz;
        } else if (subconjuntos[xraiz].rango > subconjuntos[yraiz].rango) {
            subconjuntos[yraiz].padre = xraiz;
        } else {
            subconjuntos[yraiz].padre = xraiz;
            subconjuntos[xraiz].rango++;
        }
    }

    // Implementación del algoritmo de Kruskal
    void AlgoritmoKruskal() {
        // Lista para almacenar el MST
        List<Arista> resultado = new ArrayList<>();
        int e = 0; // Contador de aristas en el MST
        int i = 0; // Contador para las aristas ordenadas

        // Ordenar todas las aristas por peso
        Collections.sort(aristas);
        System.out.println("\nPRUEBA DE ESCRITORIO - ALGORITMO DE KRUSKAL");
        System.out.println("1. Aristas ordenadas por peso:");
        for (Arista arista : aristas) {
            System.out.println("Arista: " + arista.origen + " - " + arista.destino + ", Peso: " + arista.peso);
        }

        // Crear subconjuntos para cada vértice
        Subconjunto[] subconjuntos = new Subconjunto[V];
        for (i = 0; i < V; i++) {
            subconjuntos[i] = new Subconjunto(i, 0);
        }

        System.out.println("\n2. Proceso de seleccion de aristas:");
        i = 0;
        while (e < V - 1 && i < aristas.size()) {
            Arista siguienteArista = aristas.get(i);
            int x = find(subconjuntos, siguienteArista.origen);
            int y = find(subconjuntos, siguienteArista.destino);

            System.out.println("\nAnalizando arista " + siguienteArista.origen + " - " + siguienteArista.destino + " (Peso: " + siguienteArista.peso + ")");
            System.out.println("Conjunto de " + siguienteArista.origen + ": " + x + ", Conjunto de " + siguienteArista.destino + ": " + y);

            if (x != y) {
                resultado.add(siguienteArista);
                union(subconjuntos, x, y);
                e++;
                System.out.println("Arista incluida en el MST");
                System.out.println("Union de conjuntos: " + x + " y " + y);
            } else {
                System.out.println("Arista descartada (forma un ciclo)");
            }
            i++;
        }

        // Imprimir el MST
        System.out.println("\n3. Arbol de recubrimiento minimo resultante:");
        int PesoTotal = 0;
        for (Arista arista : resultado) {
            System.out.println("Arista: " + arista.origen + " - " + arista.destino + ", Peso: " + arista.peso);
            PesoTotal += arista.peso;
        }
        System.out.println("Peso total del MST: " + PesoTotal);
    }

    public static void main(String[] args) {

        // Ejemplo de grafo con 4 vértices y 5 aristas
        int V = 4;
        KruskalMST grafico = new KruskalMST(V);

        // Añadir aristas: (origen, destino, peso)
        grafico.AñadirArista(0, 1, 10);
        grafico.AñadirArista(0, 2, 6);
        grafico.AñadirArista(0, 3, 5);
        grafico.AñadirArista(1, 3, 15);
        grafico.AñadirArista(2, 3, 4);

        // Ejecutar el algoritmo de Kruskal
        grafico.AlgoritmoKruskal();

    }
}