package productos;

import java.io.Serializable;
import java.util.Scanner;

/**
 * @author Alberto
 */
public class Producto implements Serializable {

    private static final long serialVersionUID = -7246451997399534334L;

    private String tipo,marca,patologia,deSol;
    private double precio;
    private boolean graduacion;
    private boolean correccionDeLente;
    private Color color;

    /**constructor con parámetros de la clase Product
     * @param tipo
     * @param precio
     * @param marca
     * @param graduacion
     * @param patologia
     * @param deSol
     * @param c*/
    public Producto(String tipo, double precio, String marca, boolean graduacion,
            String patologia,String deSol,Color c) {

        this.tipo = tipo;
        this.precio = precio;
        this.marca = marca;
        this.graduacion = graduacion;
        this.patologia = patologia;
        this.deSol = deSol;
        color = c;
    }

    /**construcctor sin parámetros*/
    public Producto() {
        tipo = "";
        precio = 0;
        marca = "";
        graduacion = false;
        patologia = "";
        deSol = "";
        color = color.Cb;
        //cod++;
        //codigo = cod;
        definirProducto();
        definirMarca();
        definirGraduacion();
        definirProteccionSolar();
        definirColor();
    }

    //metodos get y set

    /**
     *
     * @return
     */
    public String getTipo() {
        return tipo;
    }

    /**
     *
     * @param tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     *
     * @return
     */
    public double getPrecio() {
        return precio;
    }

    /**
     *
     * @param precio
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     *
     * @return
     */
    public String getMarca() {
        return marca;
    }

    /**
     *
     * @param marca
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     *
     * @return
     */
    public boolean isGraduacion() {
        return graduacion;
    }

    /**
     *
     * @param graduacion
     */
    public void setGraduacion(boolean graduacion) {
        this.graduacion = graduacion;
    }

    /**
     *
     * @return
     */
    public String getPatologia() {
        return patologia;
    }

    /**
     *
     * @param patologia
     */
    public void setPatologia(String patologia) {
        this.patologia = patologia;
    }

    /**
     *
     * @return
     */
    public boolean isCorreccionDeLente() {
        return correccionDeLente;
    }

    /**
     *
     * @param correccionDeLente
     */
    public void setCorreccionDeLente(boolean correccionDeLente) {
        this.correccionDeLente = correccionDeLente;
    }
    
    /**
     *
     * @return
     */
    public String getDeSol() {
        return deSol;
    }

    /**
     *
     * @param deSol
     */
    public void setDeSol(String deSol) {
        this.deSol = deSol;
    }

    /**
     *
     * @return
     */
    public Color getColor() {
        return color;
    }

    /**
     *
     * @param color
     */
    public void setColor(Color color) {
        this.color = color;
    }
    
    /**definir tipo de producto "Lentillas", "Gafas", con un switch de opciones 1,2*/ 
    private void definirProducto() {
        Scanner teclado = new Scanner(System.in);
        int opcion;
        System.out.println("pulse 1 para lentillas o 2 para gafas");
        opcion = teclado.nextInt();
        switch (opcion) {
            case 1:
                tipo = "Lentillas";
                precio = 60;
                System.out.println("el precio base es: " + precio);
                break;
            case 2:
                tipo = "Gafas";
                precio = 40;
                System.out.println("el precio base es: " + precio);
                break;
        }
    }
 
    /**define atributo graduacion con un if/else, se llama a definirDefectoVisual()*/
    private void definirGraduacion() {
        Scanner teclado = new Scanner(System.in);
        String respuesta;
        System.out.println("¿desea graduación visual?, responda si o no");
        respuesta = teclado.nextLine();
        if (respuesta.equalsIgnoreCase("si")) {
            graduacion = true;
            definirDefectoVisual();
        } else {
            graduacion = false;
        }
    }
    
    /**define atributo patologia "miopia","astigmatismo","miopia&astigmatismo" con un switch de 3 opciones*/
    private void definirDefectoVisual() {

        Scanner teclado = new Scanner(System.in);
        int defectoVisual;
        System.out.println("seleccione el defecto visual corregido");
        System.out.println("1: miopia, aumenta el precio en 80€");
        System.out.println("2: astigmatismo, aumenta el precio en 50€");
        System.out.println("3: miopia&astigmatismo, aumenta el precio en 120€ ");
        defectoVisual = teclado.nextInt();
        switch (defectoVisual) {
            case 1:
                patologia = "miopia";
                precio += 80;
                System.out.println("el precio con esta corrección visual es de " + precio);
                break;
            case 2:
                patologia = "astigmatismo";
                precio += 50;
                System.out.println("el precio con esta correccion visual es de " + precio);
                break;
            case 3:
                patologia = "miopia&astigmatismo";
                precio += 120;
                System.out.println("el precio con esta correccion visual es de " + precio);
                break;
        }
    }

    /**define el atributo deSol "con proteccion", "sin proteccion" con un switch de opciones si,no*/
    private void definirProteccionSolar() {

        Scanner teclado = new Scanner(System.in);
        String LentesDeSol;
        System.out.println("¿desea lentes que protegan del sol?");
        System.out.println("el precio aumentará 40 €");
        System.out.println("conteste si quiere o no esta protección");
        LentesDeSol = teclado.nextLine();
        switch (LentesDeSol) {

            case "si":
                deSol = "con proteccion";
                precio += 40;
                System.out.println("el precio del producto con tratamiento antisol es de " + precio);
                break;
            case "no":
                deSol = "sin proteccion";
                break;
        }
    }

    /**define color con un switch de 5 opsciones, usando los enum*/
    private void definirColor() {
        Scanner teclado = new Scanner(System.in);
        int opcion;
        System.out.println("seleccione el color de las lentes");
        System.out.println("el tintar una lente aumenta el precio 10 €");
        System.out.println("1-tinte rojo");
        System.out.println("2-tinte verde");
        System.out.println("3-tinte azul");
        System.out.println("4-tinte naranja");
        System.out.println("5-sin color");
        opcion = teclado.nextInt();
        if ((opcion >= 1) || (opcion <= 4)) {
            System.out.println("el precio aumentará 10 euros");
        } else {
            System.out.println("el precio de las lentes no ha variado");
        }
        switch (opcion) {

            case 1:
                color = Color.Cr;
                precio += 10;
                break;
            case 2:
                color = Color.Cv;
                precio += 10;
                break;
            case 3:
                color = Color.Ca;
                precio += 10;
                break;
            case 4:
                color = Color.Cn;
                precio += 10;
                break;
            case 5:
                color = Color.Cb;
                break;
        }
    }
    
    /**permite definir el atributo marca del producto con un switch con las marcas disponibles*/
    private void definirMarca() {
        Scanner teclado = new Scanner(System.in);
        int opcion;
        System.out.println("diga una marca de producto");
        System.out.println("1-Gauthier");
        System.out.println("2-Charlotte");
        System.out.println("3-Boiler");
        System.out.println("4-Bioinfinity");
        System.out.println("5-SoftLens 59");
        opcion = teclado.nextInt();
        switch (opcion) {

            case 1:
                marca = "Gauthier";
                break;
            case 2:
                marca = "Charlotte";
                break;
            case 3:
                marca = "Boiler";
                break;
            case 4:
                marca = "Bioinfinity";
                break;
            case 5:
                marca = "SoftLens 59";
                break;
        }
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        System.out.println("\033[34m------------------------------------------------------------------------"
                + "--------------------------------------------------------------------\033[0m");
        return " \033[34mTipo: \033[0m" + tipo + "\033[34m,Precio €: \033[0m" + precio 
                + "\033[34m,Marca: \033[0m" + marca + "\033[34m,Patol: \033[0m" + patologia +
                "\033[34m,Trat. solar: \033[0m" + deSol+ "\033[34m,Col: \033[0m" + this.color;
    }
}