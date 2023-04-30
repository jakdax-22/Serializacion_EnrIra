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
public class Serializacion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException {
        Administrador jefe = new Administrador ("Juan",8000,2005,12,15);
        jefe.setIncentivo(5000);
        
        Empleado [] personal = new Empleado [3];
        personal [0]=jefe;
        personal [1]=new Empleado ("Ana",25000,2008,10,1);
        personal [2]=new Empleado ("Luis",18000,2012,9,15);
        
        try {
            File destino = new File ("./src/serializacion/empleado.txt");
            if (!destino.exists()){
                destino.createNewFile();
            }
            /*ObjectOutputStream escribiendo_fichero = new ObjectOutputStream (new FileOutputStream("./src/serializacion/empleado.txt"));
            escribiendo_fichero.writeObject(personal);
            escribiendo_fichero.close();*/
            ObjectInputStream recuperando_fichero = new ObjectInputStream (new FileInputStream("./src/serializacion/empleado.txt"));
            Empleado [] personal_recuperado = (Empleado[])recuperando_fichero.readObject();
            recuperando_fichero.close();
            for (Empleado e  : personal_recuperado){
                System.out.println("Nombre: "+e.getNombre()+"\nSueldo: "+e.getSueldo()+"\nFecha Contrato: "+e.getFechaContrato()
                +"\n-----------------------------------------------");
            }
        }
        catch (FileNotFoundException fnfe){fnfe.printStackTrace();}
        catch (EOFException eofe){eofe.printStackTrace();}
        catch (IOException ioe){ioe.printStackTrace();}        
    }
    
public static class Empleado implements Serializable{
    private static final long serialVersionUID = 2L;
    private String nombre;
    private double sueldos;
    private  Date fechaContrato;
    public Empleado(String n, double s, int agno, int mes, int dia){
     nombre = n;
     sueldos = s;
     GregorianCalendar calendario = new GregorianCalendar(agno, mes-1, dia);
     fechaContrato = calendario.getTime();
    }
    public String getNombre() {
     return nombre;
    }
    public void setNombre(String nombre) {
     this.nombre = nombre;
    }
    public double getSueldo() {
     return sueldos;
    }
    public void setSueldo(double sueldo) {
     this.sueldos = sueldo;
    }
    public Date getFechaContrato() {
     return fechaContrato;
    }
    public void setFechaContrato(Date fechaContrato) {
     this.fechaContrato = fechaContrato;
    }
    public void SubirSueldo(double porcentaje){
     double aumento = sueldos*porcentaje/100;
     sueldos+=aumento;
    }
    public String toString(){
     return "Noombre = " + nombre + " ,sueldo = " + sueldos + " , fecha de contrato: " + fechaContrato;
    }
 
}
public static class Administrador extends Empleado{
    private static final long serialVersionUID = 2L;    
    private double incentivo;
    public Administrador(String n, double s, int agno, int mes, int dia){
     super(n,s,agno,mes,dia);
     incentivo = 0;
    }
    public double getSueldo() {
     double sueldoBase = super.getSueldo();
     return sueldoBase+incentivo;
    }
    public void setIncentivo(double incentivo) {
     this.incentivo = incentivo;
    }
    public String toString(){
     return super.toString()+ " Incentivo = " + incentivo;
    }


   }
}
