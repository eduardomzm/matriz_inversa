import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Files{

    // Cuenta el número de lineas que tiene un archivo
    public int countFileLines(String fileName){
        File file;
        FileReader reader;
        BufferedReader bufer;
        int numLines = 0;

        try{
            file = new File("C:\\archivos\\" + fileName);
            reader = new FileReader(file);
            bufer = new BufferedReader(reader);
            while ( (bufer.readLine()) != null ){
                numLines++;
            }
            reader.close();
        }catch( Exception e ) {
            System.out.println("Error al leer el archivo: " + e.toString());
        }
        return numLines;
    }

    // Lee un archivo de texto con datos numéricos y lo convierte en arreglo
    public int[] fileToIntArray(String fileName){
        File file; // Apuntar a un archivo físico del dd
        FileReader reader; //Llave con permiso de sólo lectura
        BufferedReader bufer; // Para recuperar información del archivo
        String linea; // Una línea del archivo
        int[] array = null; // arreglo de números resultante
        int i=0; // indice
        int t; //tamaño del arreglo
        try{
            t = countFileLines(fileName);
            
            
            // Si t es 0, no se intentará crear el arreglo y se devolverá null o un arreglo de tamaño 0.
            if (t > 0) {
                array = new int[t];
            } else {
                return new int[0]; // Devolver un arreglo vacío si no hay líneas
            }
            
            file = new File("C:\\archivos\\" + fileName);
            reader = new FileReader(file); // Abre el archivo en modo de sólo lectura
            bufer = new BufferedReader(reader);
            while ( (linea = bufer.readLine()) != null ){
                if (i < t) { 
                    array[i] = Integer.parseInt(linea);
                    i++;
                } else {
                    break;
                }
            }
            reader.close();
        } catch( Exception e){
            System.out.println("Error al leer el archivo: " + e.toString());
            // En caso de error (e.g., NumberFormatException, File Not Found), 
            // se devuelve null o un arreglo vacío.
            return new int[0];
        }
        return array;
    }

    //Leer un archivo de texto con datos tipo String
    //y armacenarlos en un arreglo
    public String[] fileToStringArray(String fileName){
        File file; // Apuntar a un archivo físico del dd
        FileReader reader; //Llave con permiso de sólo lectura
        BufferedReader bufer; // Para recuperar información del archivo
        String linea; // Una línea del archivo
        String[] array = null; // arreglo de números resultante
        int i=0; // indice
        int t; //tamaño del arreglo
        try{
            t = countFileLines(fileName);
            
            if (t > 0) {
                array = new String[t];
            } else {
                return new String[0];
            }

            file = new File("C:\\archivos\\" + fileName);
            reader = new FileReader(file); // Abre el archivo en modo de sólo lectura
            bufer = new BufferedReader(reader);
            // leer cada línea del archivo y almacenarla en un arreglo de enteros
            while ( (linea = bufer.readLine()) != null ){
                if (i < t) {
                    array[i] = linea;
                    i++;
                } else {
                    break;
                }
            }
            reader.close();
        } catch( Exception e){
            System.out.println("Error al leer el archivo: " + e.toString());
            return new String[0];
        }
        return array;
    }

    // Crear un archivo con información de un arreglo
    public void writeIntArrayToFile(String fileName, int[] array){
        FileWriter file;
        PrintWriter writer;
        try{
            file = new FileWriter("c:\\archivos\\" + fileName);
            writer = new PrintWriter(file);
            for  ( int unDato : array )
                writer.println(unDato);
            file.close();
            System.out.println("Archivo " + fileName + " creado exitosamente.");
        } catch ( Exception e) {
            System.out.println("Error al crear el archivo: " + e.toString());
        }

    }
    
    public boolean sumIntArraysAndWriteToFile(String fileName, int[] array1, int[] array2){
        if (array1.length != array2.length) {
            System.out.println("Error: Los arreglos tienen diferente tamaño (" + array1.length + " vs " + array2.length + ") y no se pueden sumar elemento por elemento.");
            return false;
        }

        int[] sumArray = new int[array1.length];
        for (int i = 0; i < array1.length; i++) {
            sumArray[i] = array1[i] + array2[i];
        }

        try {
            writeIntArrayToFile(fileName, sumArray);
            System.out.println("El arreglo resultante de la suma se ha escrito correctamente en " + fileName);
            return true;
        } catch (Exception e) {
            System.out.println("Error al intentar escribir el arreglo de suma: " + e.toString());
            return false;
        }
    }

    public void writeDoubleArrayToFile(String fileName, double[] array){
        FileWriter file;
        PrintWriter writer;
        try{
            file = new FileWriter("c:\\archivos\\" + fileName);
            writer = new PrintWriter(file);
            for (double unDato : array)
                writer.println(unDato);
            file.close();
        } catch (Exception e) {
            System.out.println("Error al crear el archivo: " + e.toString());
        }
    }
}