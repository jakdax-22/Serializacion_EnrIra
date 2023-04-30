/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package serializacion;
import java.util.*;
import java.io.*;
/**
 *
 * @author enriq
 */
public class FicB6EnrIra {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException {
        Alumno enrique = new Alumno ("78945612C","Enrique Iranzo","Calle la Amargura",46300);
        ObjectInputStream leer_fichero = null;
        ObjectOutputStream escribir_fichero = null;
        
        try {
            File destino = new File ("./src/serializacion/destino.txt");
            
            if (!destino.exists()){
                destino.createNewFile();
            }
            escribir_fichero = new ObjectOutputStream (new FileOutputStream(destino.getPath()));
            escribir_fichero.writeObject(enrique);
            escribir_fichero.close();
            leer_fichero = new ObjectInputStream (new FileInputStream(destino.getPath()));
            Alumno enrique_recuperado = (Alumno)leer_fichero.readObject();
            System.out.println(enrique_recuperado.toString());
        }
        catch (EOFException eofe){eofe.printStackTrace();}
        catch (FileNotFoundException fnfe){fnfe.printStackTrace();}
        catch (IOException ioe){ioe.printStackTrace();}
    }
public static class Alumno implements Serializable {
    private final long serialVersionUID = 1L;
    private String dni,nombre,direccion;
    transient private int codPostal;

        public Alumno(String dni, String nombre, String direccion, int codPostal) {
            this.dni = dni;
            this.nombre = nombre;
            this.direccion = direccion;
            this.codPostal = codPostal;
        }

        public String getDni() {
            return dni;
        }

        public void setDni(String dni) {
            this.dni = dni;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getDireccion() {
            return direccion;
        }

        public void setDireccion(String direccion) {
            this.direccion = direccion;
        }

        public int getCodPostal() {
            return codPostal;
        }

        public void setCodPostal(int codPostal) {
            this.codPostal = codPostal;
        }

        @Override
        public String toString() {
            return "Alumno{" + "dni=" + dni + ", nombre=" + nombre + ", direccion=" + direccion + ", codPostal=" + codPostal + '}';
        }
    
}    
}

