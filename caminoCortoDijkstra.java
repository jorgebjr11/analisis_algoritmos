import java.util.*;

class caminoCortoDijkstra {
    // Clase para representar una arista
    static class Arista {
        int destino, peso;

        Arista(int destino, int peso) {
            this.destino = destino;
            this.peso = peso;
        }
    }

    // Clase para representar un nodo en la cola de prioridad
    static class Nodo implements Comparable<Nodo> {
        int vertice, distancia;

        Nodo(int vertice, int distancia) {
            this.vertice = vertice;
            this.distancia = distancia;
        }

        @Override
        public int compareTo(Nodo otro) {
            return this.distancia - otro.distancia;
        }
    }

    private int V; // Número de vértices
    private List<List<Arista>> adyacencia; // Lista de adyacencia

    caminoCortoDijkstra(int vertices) {
        V = vertices;
        adyacencia = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adyacencia.add(new ArrayList<>());
        }
    }

    // Añadir una arista al grafo
    void añadirArista(int origen, int destino, int peso) {
        adyacencia.get(origen).add(new Arista(destino, peso));
    }

    // Algoritmo de Dijkstra
    void dijkstra(int origen) {
        // Inicializar distancias, predecesores y visitados
        int[] dist = new int[V];
        int[] predecesores = new int[V];
        boolean[] visitados = new boolean[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(predecesores, -1);
        dist[origen] = 0;

        // Cola de prioridad para seleccionar el vértice con menor distancia
        PriorityQueue<Nodo> pq = new PriorityQueue<>();
        pq.offer(new Nodo(origen, 0));

        // Prueba de escritorio: Mostrar estado inicial
        System.out.println("\nPRUEBA DE ESCRITORIO - ALGORITMO DE DIJKSTRA");
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("Inicializacion desde el vertice fuente " + origen + ":");
        System.out.println("Distancias iniciales: " + Arrays.toString(dist));
        System.out.println("Predecesores iniciales: " + Arrays.toString(predecesores));
        System.out.println("\nIteraciones:");

        // Contador para numerar las iteraciones
        int iteracion = 1;

        while (!pq.isEmpty()) {
            Nodo nodo = pq.poll();
            int u = nodo.vertice;

            // Si el vértice ya fue visitado, continuar
            if (visitados[u]) continue;

            // Marcar el vértice como visitado
            visitados[u] = true;

            // Mostrar el vértice seleccionado
            System.out.println("\nIteracion " + iteracion + ":");
            System.out.println("Seleccionando vertice " + u + " con distancia " + dist[u]);

            // Explorar los vecinos de u
            for (Arista arista : adyacencia.get(u)) {
                int v = arista.destino;
                int peso = arista.peso;

                // Si no ha sido visitado y se puede mejorar la distancia
                if (!visitados[v] && dist[u] + peso < dist[v]) {
                    dist[v] = dist[u] + peso;
                    predecesores[v] = u;
                    pq.offer(new Nodo(v, dist[v]));
                    System.out.println("  Actualizando distancia a " + v + ": " + dist[v] + ", Predecesor: " + u);
                } else {
                    System.out.println("  No se actualiza distancia a " + v + " (ya visitado o distancia no mejorada)");
                }
            }

            // Mostrar estado actual
            System.out.println("Distancias actuales: " + Arrays.toString(dist));
            System.out.println("Predecesores actuales: " + Arrays.toString(predecesores));
            iteracion++;
        }

        // Imprimir los caminos mínimos
        System.out.println("\nResultados finales - Caminos minimos desde el vertice " + origen + ":");
        for (int i = 0; i < V; i++) {
            System.out.print("Vertice " + i + ": Distancia = " + (dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]));
            System.out.print(", Camino: ");
            imprimirRuta(predecesores, i);
            System.out.println();
        }
    }

    // Método para imprimir el camino desde la fuente hasta el vértice destino
    private void imprimirRuta(int[] predecesores, int destino) {
        if (destino == -1) {
            System.out.print("Ninguno");
            return;
        }
        List<Integer> ruta = new ArrayList<>();
        for (int at = destino; at != -1; at = predecesores[at]) {
            ruta.add(at);
        }
        Collections.reverse(ruta);
        if (ruta.size() == 1 && ruta.get(0) == destino) {
            System.out.print(destino);
        } else {
            for (int i = 0; i < ruta.size(); i++) {
                System.out.print(ruta.get(i));
                if (i < ruta.size() - 1) System.out.print(" -> ");
            }
        }
    }

    public static void main(String[] args) {
        // Ejemplo de grafo con 5 vértices
        int V = 5;
        caminoCortoDijkstra grafico = new caminoCortoDijkstra(V);

        // Añadir aristas: (origen, destino, peso)
        grafico.añadirArista(0, 1, 4);
        grafico.añadirArista(0, 2, 8);
        grafico.añadirArista(1, 2, 2);
        grafico.añadirArista(1, 3, 5);
        grafico.añadirArista(2, 3, 5);
        grafico.añadirArista(2, 4, 9);
        grafico.añadirArista(3, 4, 10);

        // Ejecutar Dijkstra desde el vértice 0
        grafico.dijkstra(0);
    }
}