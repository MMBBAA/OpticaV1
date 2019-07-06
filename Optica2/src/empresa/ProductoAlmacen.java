package empresa;

import java.io.Serializable;
import productos.Producto;

/**
 * @author Alberto
 */
public class ProductoAlmacen implements Serializable{

    private static final long serialVersionUID = -5370389506626121572L;
   
    private Producto producto;
    private int stock;

    /**constructor por parametros de ProductoAlmace
     * @param producto
     * @param stock*/
    public ProductoAlmacen(Producto producto, int stock){
    
       this.producto=producto;
       this.stock=stock;
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
    public int getStock() {
        return stock;
    }

    /**
     *
     * @param stock
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return producto + "\033[34m,Stock: \033[0m" + stock;
    }
}