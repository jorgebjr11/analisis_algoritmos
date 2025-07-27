public class MergeSort {
    public static void merge(int[] A, int p, int q, int r) {
        int n1 = q - p + 1; // longitud de A[p:q]
        int n2 = r - q;     // longitud de A[q+1:r]
        
        int[] L = new int[n1];
        int[] R = new int[n2];
        
        // Copia A[p:p+n1-1] a L[0:n1-1]
        for (int i = 0; i < n1; i++) {
            L[i] = A[p + i];
        }
        
        // Copia A[q+1:r] en R[0:n2-1]
        for (int j = 0; j < n2; j++) {
            R[j] = A[q + j + 1];
        }
        
        int i = 0; // índices el elemento restante más pequeño en L
        int j = 0; // índices el elemento restante más pequeño en R
        int k = p; // índices la ubicación en A para llenar
        
        // Fusiona los arreglos L y R de vuelta en A
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                A[k] = L[i];
                i = i + 1;
            } else {
                A[k] = R[j];
                j = j + 1;
            }
            k = k + 1;
        }
        
        // Copia los elementos restantes de L, si los hay
        while (i < n1) {
            A[k] = L[i];
            i = i + 1;
            k = k + 1;
        }
        
        // Copia los elementos restantes de R, si los hay
        while (j < n2) {
            A[k] = R[j];
            j = j + 1;
            k = k + 1;
        }
    }

    // Ejemplo de uso
    public static void main(String[] args) {
        int[] arr = {5, 2, 8, 1, 9, 3};
        merge(arr, 0, 2, 5);
        for (int value : arr) {
            System.out.print(value + " ");
        }
    }
}