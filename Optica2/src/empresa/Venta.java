package empresa;

import java.io.Serializable;
import java.time.LocalDate;
import productos.Producto;

/**
 * @author Alberto
 */
public class Venta implements Serializable{

    private static final long serialVersionUID = -4472943088381130387L;
   
    private Producto producto;
    int unidades;
    private LocalDate fechaVenta;
    private String dni;

    /**constructor con parametros de Vent
     * @param producto
     * @param unidades
     * @param fechaVenta
     * @param dni*/
    public Venta(Producto producto, int unidades, LocalDate fechaVenta, String dni) {
        
        this.producto = producto;
        this.unidades = unidades;
        this.fechaVenta = fechaVenta;
        this.dni = dni;
    }

    //metodos get y set

    /**
     *
     * @return
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     *
     * @param producto
     */
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    /**
     *
     * @return
     */
    public int getUnidades() {
        return unidades;
    }

    /**
     *
     * @param unidades
     */
    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    /**
     *
     * @return
     */
    public LocalDate getFechaVenta() {
        return fechaVenta;
    }

    /**
     *
     * @param fechaVenta
     */
    public void setFechaVenta(LocalDate fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    /**
     *
     * @return
     */
    public String getDni() {
        return dni;
    }

    /**
     *
     * @param dni
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "prod: " + producto
                + "\033[31m uds: \033[0m" + unidades
                + "\033[31m f.Venta: \033[0m" + fechaVenta
                + "\033[31m dni: \033[0m" + dni;
    }
}