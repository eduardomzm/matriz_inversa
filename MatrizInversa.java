import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MatrizInversa {

    public static void main(String[] args) throws IOException {
        Files files = new Files();
        BufferedReader bufer = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Nombre del archivo con la matriz: ");
        String inputFile = bufer.readLine();

        // Leemos la matriz original (que suelen ser enteros al principio)
        int[] flatMatrixInt = files.fileToIntArray(inputFile);

        if (flatMatrixInt != null) {
            // Calcular dimensión N
            int n = (int) Math.sqrt(flatMatrixInt.length);
            if (n * n != flatMatrixInt.length) {
                System.out.println("Error: El archivo no es una matriz cuadrada.");
                return;
            }

            double[][] matrix = new double[n][n];
            double[][] inverse = new double[n][n];
            int k = 0;
            
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = (double) flatMatrixInt[k++];
                    if (i == j) inverse[i][j] = 1.0; else inverse[i][j] = 0.0;
                }
            }

            for (int i = 0; i < n; i++) {
                double pivot = matrix[i][i];
                if(pivot == 0) { 
                    System.out.println("El sistema encontró un pivote 0, no se puede invertir con este método simple.");
                    return;
                }
                
                for (int j = 0; j < n; j++) {
                    matrix[i][j] /= pivot;
                    inverse[i][j] /= pivot;
                }
                for (int x = 0; x < n; x++) {
                    if (x != i) {
                        double factor = matrix[x][i];
                        for (int j = 0; j < n; j++) {
                            matrix[x][j] -= factor * matrix[i][j];
                            inverse[x][j] -= factor * inverse[i][j];
                        }
                    }
                }
            }

            double[] resultForFile = new double[n * n];
            k = 0;
            
            System.out.println("Matriz Inversa calculada:");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.printf("%.3f   ", inverse[i][j]);
                    resultForFile[k++] = inverse[i][j];        
                }
                System.out.println();
            }

            System.out.print("Nombre del archivo para guardar: ");
            String outputFile = bufer.readLine();
            
            files.writeDoubleArrayToFile(outputFile, resultForFile);
            
            System.out.println("Archivo guardado correctamente.");
        }
    }
}