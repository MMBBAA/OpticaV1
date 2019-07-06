package cliente;

import java.io.Serializable;

/**
 * @author Alberto
 */
public class Cliente implements Serializable{

    private static final long serialVersionUID = 7365345385824948464L;

    private String nombre,DNI,email,direccion;
    private int tfno;

    /**constructor con parámetros de la clase Clien
     * @param nombre
     * @param DNI
     * @param tfno
     * @param email
     * @param direccion*/
    public Cliente(String nombre, String DNI, int tfno, String email, String direccion) {

        this.nombre = nombre;
        this.DNI = DNI;
        this.tfno = tfno;
        this.email = email;
        this.direccion = direccion;
    }

    /**constructor por defecto*/
    public Cliente() {

        nombre = "";
        DNI = "";
        tfno = 0;
        email = "";
        direccion = "";

    }
    
    //metodos get y set

    /**
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     *
     * @return
     */
    public String getDNI() {
        return DNI;
    }

    /**
     *
     * @param DNI
     */
    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    /**
     *
     * @return
     */
    public int getTfno() {
        return tfno;
    }

    /**
     *
     * @param tfno
     */
    public void setTfno(int tfno) {
        this.tfno = tfno;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     *
     * @param direccion
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        System.out.println("\033[31mDatos de cliente\033[0m");
        return "\033[31mnombre: \033[0m" + nombre
                + "\033[31m,DNI: \033[0m" + DNI
                + "\033[31m,tfno: \033[0m" + tfno
                + "\033[31m,email: \033[0m" + email
                + "\033[31m,direcc: \033[0m" + direccion;
    }
}