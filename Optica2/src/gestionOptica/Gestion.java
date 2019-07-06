package gestionOptica;

import empresa.Venta;
import cliente.Cliente;
import empresa.ProductoAlmacen;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import static productos.Color.*;
import productos.Producto;

/**
 * @author Alberto
 */
public class Gestion {

    private ArrayList<Cliente> listaClientes;//declaración de un array de clientes
    private ArrayList<ProductoAlmacen> productosEnStock;//declaracion de un array de productos en Almacen
    private ArrayList<Venta> productosVendidos;//declaración de un array de ventas realizadas

    /**
     * Constructor clase gestion, inicializa 3 Arrays, el de clientes, el de
     * ventas y el de productos vendidos
     */
    public Gestion() {

        listaClientes = new ArrayList<Cliente>(); //inicialización del array de clientes 
        productosEnStock = new ArrayList<ProductoAlmacen>();//icicialización del array de productos en Almacen
        productosVendidos = new ArrayList<Venta>();//inicialización del array de ventas realizadas
    }

    //metodos get y set
    /**
     * @return
     */
    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }

    /**
     * @param listaClientes
     */
    public void setListaClientes(ArrayList<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    /**
     * @return
     */
    public ArrayList<ProductoAlmacen> getProductosEnStock() {
        return productosEnStock;
    }

    /**
     * @param productosEnStock
     */
    public void setProductosEnStock(ArrayList<ProductoAlmacen> productosEnStock) {
        this.productosEnStock = productosEnStock;
    }

    /**
     * @return
     */
    public ArrayList<Venta> getProductosVendidos() {
        return productosVendidos;
    }

    /**
     * @param productosVendidos
     */
    public void setProductosVendidos(ArrayList<Venta> productosVendidos) {
        this.productosVendidos = productosVendidos;
    }

    /**
     * muestra los clientes que hay en el array de clientes
     */
    public void mostrarClientes() {

        if (listaClientes.isEmpty()) {
            System.out.println("no hay clientes");
        } else {
            for (Cliente c : listaClientes) {
                System.out.println(c.toString());
            }
        }
    }

    /**
     * añade clientes al array de client
     * @param c
     */
    public void añadirCliente(Cliente c) {
        listaClientes.add(c);
    }

    /**
     * busca el cliente por dni, si lo encuentra lo devuelve
     */
    private Cliente buscarCliente(String DNI) {

        Cliente k = null;
        boolean encontrado = false;
        if (listaClientes.isEmpty()) {
            System.out.println("no hay clientes");
        } else {
            Iterator it = listaClientes.iterator();
            while (it.hasNext() && !encontrado) {
                Cliente x = (Cliente) it.next();
                if (x.getDNI().equalsIgnoreCase(DNI)) {
                    encontrado = true;
                    System.out.println("cliente encontrado");
                    System.out.println(x.toString());
                    k = x;
                }
            }
        }
        return k;
    }

    /**
     * añade productos a array productosEnStoc
     * @param palmacen
     */
    public void añadirProductosAlmacen(ProductoAlmacen palmacen) {

        productosEnStock.add(palmacen);
    }

    /**
     * muestra los productos disponibles en almacén
     */
    public void mostrarProductosDisponibles() {
        if (productosEnStock.isEmpty()) {
            System.out.println("no hay productos almacenados");
        } else {
            for (ProductoAlmacen palmacen : productosEnStock) {
                if (palmacen.getStock() >= 0) {
                    System.out.println(palmacen.toString());
                }
            }
        }
    }

    /**
     * muestra stock por debajo de 2 unidades y permite aumentarlo
     */
    public void mostrarStockProductosInferiores() {
        Scanner teclado = new Scanner(System.in);
        boolean inferiorAdos = false;
        boolean repetir;
        int opcion = 0;
        for (ProductoAlmacen palmacen : productosEnStock) {
            if (palmacen.getStock() < 2) {
                inferiorAdos = true;
                System.out.println(palmacen.toString());
                do {
                    try {
                        repetir = false;
                        System.out.println("este producto tiene menos de 2 unidades, ¿quiere reponer stock?");
                        System.out.println("1 si 2 no");
                        opcion = teclado.nextInt();
                        switch (opcion) {
                            case 1:
                                int reposicion;//cantidad que vamos a reponer
                                System.out.println("introduzca las unidades que quiere adquirir");
                                reposicion = teclado.nextInt();
                                int disponible = palmacen.getStock();
                                disponible += reposicion;
                                palmacen.setStock(disponible);
                                System.out.println("producto repuesto");
                                System.out.println(palmacen.toString());
                                break;
                            case 2:
                                System.out.println("fin");
                                break;
                            default:
                                System.out.println("introduzca opción correcta");
                                break;
                        }
                    } catch (InputMismatchException e) {
                        repetir = true;
                        teclado.nextLine();
                        System.out.println("introduzca paráetro correcto");
                    }
                } while ((opcion < 1) || (opcion > 3) || (repetir == true));
            }
        }
        if (inferiorAdos == false) {
            System.out.println("no hay productos por debajo de 2 unidades");
        }
    }

    /**
     * muestra los productos de almacen por 4 atributos filtrados
     */
    public void mostrarProductosPorTipo() {
        Scanner teclado = new Scanner(System.in);
        String opcion1;
        String opcion2;
        boolean opciongraduacion = false;
        String opcion3;
        String proteccionSolar3;//segun sea si o no, su valor será el del producto almacenado.
        String opcion4;
        System.out.println("buscador de productos");
        System.out.println("diga si busca lentillas o gafas");
        opcion1 = teclado.nextLine();
        System.out.println("¿busca productos con graduación visual?,escriba si o no");
        opcion2 = teclado.nextLine();
        if (opcion2.equalsIgnoreCase("si")) {
            opciongraduacion = true;
        }
        System.out.println("¿busca lentes con tratamiento solar?,escriba si o no");
        opcion3 = teclado.nextLine();
        if (opcion3.equalsIgnoreCase("si")) {
            proteccionSolar3 = "con proteccion";
        } else {
            proteccionSolar3 = "sin proteccion";
        }
        System.out.println("especifique el tintado de lente: rojo,verde,azul,naranja o sin color");
        opcion4 = teclado.nextLine();
        boolean encontrado = false;
        Iterator it = productosEnStock.iterator();
        while ((it.hasNext()) && !encontrado) {
            ProductoAlmacen p = (ProductoAlmacen) it.next();
            if (p.getProducto().getTipo().equalsIgnoreCase(opcion1)
                    && (p.getProducto().isGraduacion() == opciongraduacion)
                    && (p.getProducto().getDeSol().equalsIgnoreCase(proteccionSolar3))
                    && (p.getProducto().getColor().getColor().equalsIgnoreCase(opcion4))) {
                encontrado = true;
                System.out.println("producto/s encontrado/s:");
                System.out.println(p.toString());
            }
        }
        if (!encontrado) {
            System.out.println("no hay productos con esas características");
        }
    }

    /**
     * solo se usa cuando no existe fichero datosCliente
     */
    public void cargaInicialClientes() {

        Cliente c1 = new Cliente("Juan Fernandez Fernandez", "28369750D", 641548798, "juan@gmail.com", "Calle nuevo horizonte");
        listaClientes.add(c1);
        Cliente c2 = new Cliente("Pedro García García", "12784578T", 600104212, "pg2@hotmail.com", "Calle San jeronimo");
        listaClientes.add(c2);
        Cliente c3 = new Cliente("Maria Pulido Cervantes", "24235357X", 674518574, "maria.cervantes@gmail.com", "Avda Contitucion");
        listaClientes.add(c3);
        Cliente c4 = new Cliente("Eloisa Chamorro Perez", "23451263H", 603343537, "Eloisa.22@gmail.com", "Calle Sierpes");
        listaClientes.add(c4);
        Cliente c5 = new Cliente("David García Peña", "23265785J", 657541287, "david22@outlook.com", "Calle Leon XIII");
        listaClientes.add(c5);
    }

    /**
     * solo se usa cuando no existen ficheros datosVentas ni ProductoAlmacen
     */
    public void cargaInicialProductos() {

        Producto p1 = new Producto("lentillas", 140, "Softlens 59", true, "miopia", "sin proteccion", Cb);
        Producto p2 = new Producto("gafas", 210, "Gauthier", true, "miopia&astigmatismo", "con proteccion", Cr);
        Producto p3 = new Producto("lentillas", 150, "Bioinfinity", true, "astigmatismo", "con proteccion", Cb);
        Producto p4 = new Producto("gafas", 130, "Charlotte", true, "miopia", "sin proteccion", Cv);
        Producto p5 = new Producto("gafas", 100, "Charlotte", true, "astigmatismo", "sin proteccion", Ca);

        ProductoAlmacen palmacen1 = new ProductoAlmacen(p1, 4);
        ProductoAlmacen palmacen2 = new ProductoAlmacen(p2, 30);
        ProductoAlmacen palmacen3 = new ProductoAlmacen(p3, 5);
        ProductoAlmacen palmacen4 = new ProductoAlmacen(p4, 1);//2
        ProductoAlmacen palmacen5 = new ProductoAlmacen(p5, 1);//6

        productosEnStock.add(palmacen1);
        productosEnStock.add(palmacen2);
        productosEnStock.add(palmacen3);
        productosEnStock.add(palmacen4);
        productosEnStock.add(palmacen5);

        String fechaCompra1 = "10/04/2018";
        String fechaCompra2 = "09/04/2018";
        String fechaCompra3 = "08/02/2018";
        String fechaCompra4 = "07/04/2018";
        String fechaCompra5 = "10/04/2018";
        DateTimeFormatter fechaFormateada = DateTimeFormatter.ofPattern("dd/LL/yyyy");
        LocalDate fc1 = LocalDate.parse(fechaCompra1, fechaFormateada);
        LocalDate fc2 = LocalDate.parse(fechaCompra2, fechaFormateada);
        LocalDate fc3 = LocalDate.parse(fechaCompra3, fechaFormateada);
        LocalDate fc4 = LocalDate.parse(fechaCompra4, fechaFormateada);
        LocalDate fc5 = LocalDate.parse(fechaCompra5, fechaFormateada);

        Venta venta1 = new Venta(p1, 1, fc1, "28369750D");
        Venta venta2 = new Venta(p1, 2, fc2, "12784578T");
        Venta venta3 = new Venta(p2, 1, fc3, "24235357X");
        Venta venta4 = new Venta(p3, 1, fc4, "23451263H");
        Venta venta5 = new Venta(p4, 2, fc5, "28369750D");

        productosVendidos.add(venta1);
        productosVendidos.add(venta2);
        productosVendidos.add(venta3);
        productosVendidos.add(venta4);
        productosVendidos.add(venta5);
    }

    /**
     * metodo privado necesario para determinar el atributo marca
     */
    private String especificarMarca() {

        Scanner teclado = new Scanner(System.in);
        int opcion = 0;
        String marca = "";
        do {
            try {
                System.out.println("especifique una marca");
                System.out.println("esto no aumentará el precio");
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
            } catch (InputMismatchException e) {
                System.out.println("introduzca una opción correcta");
                teclado.nextLine();
            }
        } while ((opcion < 1) || (opcion > 5));
        System.out.println("la marca es " + marca);
        return marca;
    }

    /**
     * metodo privado necesario para determinarl el defecto visual
     */
    private String definirDefectoVisual() {

        Scanner teclado = new Scanner(System.in);
        int defectoVisual = 0;
        String patologia = "";
        do {
            try {
                System.out.println("especifique patologia");
                System.out.println("1: miopia");
                System.out.println("2: astigmatismo");
                System.out.println("3: miopia y astigmatismo");
                defectoVisual = teclado.nextInt();
                switch (defectoVisual) {
                    case 1:
                        patologia = "miopia";
                        System.out.println("el precio incrementa en 80€");
                        break;
                    case 2:
                        patologia = "astigmatismo";
                        System.out.println("el precio incrementa en 50€");
                        break;
                    case 3:
                        patologia = "miopia&astigmatismo";
                        System.out.println("el precio incrementa en 120€");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("introduzca una opción correcta");
                teclado.nextLine();
            }
        } while ((defectoVisual < 1) || (defectoVisual > 3));
        System.out.println("el defecto visual es " + patologia);
        return patologia;
    }

    /*establece la fecha de compra*/
    private LocalDate fechaCompra() {
        boolean correcto = false;
        String fechaCompra;
        Scanner teclado = new Scanner(System.in);
        LocalDate fc = null;
        do {
            try {
                System.out.println("introduzca fecha de compra");
                System.out.println("formato: DD/MM/AAAA");
                fechaCompra = teclado.next();
                DateTimeFormatter fechaFormateada = DateTimeFormatter.ofPattern("dd/LL/yyyy");
                // LocalDate fc;
                fc = LocalDate.parse(fechaCompra, fechaFormateada);
                correcto = true;
            } catch (Exception e) {
                System.out.println("la fecha no es valida");
            }
        } while (!correcto);
        return fc;
    }

    /**
     * hace la venta y va informando sobre los precios de las opciones
     */
    public void hacerVenta() {

        //busco el producto en el almacen
        Scanner teclado = new Scanner(System.in);
        String tipoProducto;
        String graduacionVis;
        String tratamientoLente;
        String datoProteccionSolar;//segun se conteste si o no, su valor será el del producto almacenado.
        String colorLente;
        String datoMarca;
        int unidades;//variable para almacenar el numero de unidades a comprar
        String defectoVisual = "";
        boolean opciongraduacion = false;
        do {
            System.out.println("¿Desea comprar lentillas o gafas?");
            System.out.println("las lentillas tienen un precio base de 60€ y las gafas de 40€");
            tipoProducto = teclado.nextLine();
            if ((!tipoProducto.equals("lentillas")) && (!tipoProducto.equals("gafas"))) {
                System.out.println("introduzca correctamente el tipo de producto en minusculas");
            }
        } while ((!tipoProducto.equals("lentillas")) && (!tipoProducto.equals("gafas")));

        do {
            System.out.println("¿busca productos con graduación visual?, esto aumentará el precio: escriba si o no");
            graduacionVis = teclado.nextLine();
            if ((!graduacionVis.equalsIgnoreCase("si")) && (!graduacionVis.equalsIgnoreCase("no"))) {
                System.out.println("conteste si o no");
            }
        } while ((!graduacionVis.equalsIgnoreCase("si")) && (!graduacionVis.equalsIgnoreCase("no")));
        if (graduacionVis.equalsIgnoreCase("si")) {
            opciongraduacion = true;
            defectoVisual = definirDefectoVisual();
        }
        do {
            System.out.println("¿busca lentes con tratamiento solar?,aumentará el precio en 40€,escriba si o no");
            tratamientoLente = teclado.nextLine();
            if ((!tratamientoLente.equalsIgnoreCase("si")) && (!tratamientoLente.equalsIgnoreCase("no"))) {
                System.out.println("conteste si o no");
            }
        } while ((!tratamientoLente.equalsIgnoreCase("si")) && (!tratamientoLente.equalsIgnoreCase("no")));
        if (tratamientoLente.equalsIgnoreCase("si")) {
            datoProteccionSolar = "con proteccion";
        } else {
            datoProteccionSolar = "sin proteccion";
        }
        System.out.println("especifique el tintado de lente: rojo,verde,azul,naranja o sin color");
        System.out.println("si elige un color aumentará el precio en 10€");
        colorLente = teclado.nextLine();
        datoMarca = especificarMarca();
        System.out.println("diga cuantas unidades desea");
        unidades = teclado.nextInt();
        boolean encontrado = false;
        Iterator it = productosEnStock.iterator();
        while ((it.hasNext()) && (!encontrado)) {
            ProductoAlmacen p = (ProductoAlmacen) it.next();
            if (p.getProducto().getTipo().equalsIgnoreCase(tipoProducto)
                    && (p.getProducto().isGraduacion() == opciongraduacion)
                    && (p.getProducto().getPatologia().equalsIgnoreCase(defectoVisual))
                    && (p.getProducto().getMarca().equalsIgnoreCase(datoMarca))
                    && (p.getProducto().getDeSol().equalsIgnoreCase(datoProteccionSolar))
                    && (p.getProducto().getColor().getColor().equalsIgnoreCase(colorLente))
                    && (p.getStock() >= unidades)) {
                encontrado = true;
                System.out.println("producto/s encontrado/s:");
                //creamos un producto con los datos encontrados
                Producto p1 = new Producto(tipoProducto, p.getProducto().getPrecio(), datoMarca, opciongraduacion,
                        defectoVisual, datoProteccionSolar, p.getProducto().getColor());
                System.out.println(p1.toString() + "stock: " + p.getStock());
                System.out.println(".........");
                System.out.println(p1.toString());
                confirmarVenta(unidades, p, p1);
            } else if (p.getProducto().getTipo().equalsIgnoreCase(tipoProducto)
                    && (p.getProducto().isGraduacion() == opciongraduacion)
                    && (p.getProducto().getPatologia().equalsIgnoreCase(defectoVisual))
                    && (p.getProducto().getMarca().equalsIgnoreCase(datoMarca))
                    && (p.getProducto().getDeSol().equalsIgnoreCase(datoProteccionSolar))
                    && (p.getProducto().getColor().getColor().equalsIgnoreCase(colorLente))
                    && (p.getStock() < unidades)) {
                encontrado = true;
                System.out.println("producto encontrado pero en cantidad insuficiente");
            }
        }
        if (!encontrado) {
            System.out.println("el producto no existe");
        }
    }

    /**
     * metodo privado, confirma la venta que quiere hacerse
     */
    private void confirmarVenta(int unidades, ProductoAlmacen p, Producto p1) {

        int stockRestante = ((p.getStock()) - unidades);
        Venta venta = null;
        int opcion = 0;
        String dni = "";
        Scanner teclado = new Scanner(System.in);
        LocalDate fecha = fechaCompra();
        System.out.println("introduzca su dni");
        dni = teclado.nextLine();
        Cliente c = buscarCliente(dni);

        do {
            try {
                System.out.println("¿desea realizar la compra?");
                System.out.println("pulse 1 para Si y 2 para No");
                opcion = teclado.nextInt();
                switch (opcion) {
                    case 1:
                        if (c == null) {
                            String nombre, email, direccion = "";
                            int tfno = 0;
                            System.out.println("el cliente no existe");
                            System.out.println("va a crearse uno");
                            System.out.println("introduzca nombre");
                            teclado.nextLine();
                            nombre = teclado.nextLine();
                            System.out.println("itntroduzca tfno");
                            tfno = teclado.nextInt();
                            System.out.println("introduzca email");
                            email = teclado.next();
                            System.out.println("introduzca direccion");
                            teclado.nextLine();
                            direccion = teclado.nextLine();
                            c = new Cliente();
                            c.setNombre(nombre);
                            c.setDNI(dni);
                            c.setTfno(tfno);
                            c.setEmail(email);
                            c.setDireccion(direccion);
                            listaClientes.add(c);
                        }
                        String dniCliente = c.getDNI();
                        p.setStock(stockRestante);
                        venta = new Venta(p1, unidades, fecha, dniCliente);
                        productosVendidos.add(venta);
                        System.out.println("compra realizada correctamente");
                        break;
                    case 2:
                        System.out.println("compra cancelada");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("introduzca una opción correcta");
                teclado.nextLine();
            }
        } while ((opcion < 1) || (opcion > 2));
    }

    /**
     * muestra las ventas realizadas
     */
    public void mostrarVentasRealizadas() {

        for (Venta lventas : productosVendidos) {
            System.out.println(lventas.toString());
        }
    }

    /**
     * devuelve un boolean y muestra por pantalla las ventas de ese cliente
     * usando un array temporal que se muestra cuando la variable encontrado
     * cambia a true.
     *
     * el return ventaExiste permite controlar el resto del metodo de mostrar
     * Facturas
     *
     * @param dni
     * @return
     */
    private ArrayList<Venta> mostrarVentasPorDNI(String dni) {

        ArrayList<Venta> VentasEncontradas = new ArrayList<Venta>();//array temporal para almacenar las ventas

        for (Venta lventas : productosVendidos) {
            if (lventas.getDni().equalsIgnoreCase(dni)) {
                VentasEncontradas.add(lventas);
            }
        }
        if (VentasEncontradas.isEmpty()) {
            System.out.println("no hay ventas para ese cliente");
        } else {
            System.out.println("existen ventas para ese Cliente, estos son los datos");
        }
        for (Venta VentasEncontrada : VentasEncontradas) {
            System.out.println(VentasEncontrada.toString());
        }
        return VentasEncontradas;
    }

    /**
     * devuelve el nº de operaciones del ultimo mes y el gasto mayor del ultimo
     * mes
     */
    public void contarOperaciones() {

        //declaracion e inicializacion de un array de ventas realizadas en el ultimo mes 
        ArrayList<Venta> prodsVendUltimoMes = new ArrayList<Venta>();
        //declaracion de variables    
        int anioactual = LocalDate.now().getYear();
        //el MonthValue me devuelve un entero, si se llama a getMonth, devolvería un objeto tipo Month
        int mesAnterior = LocalDate.now().minusMonths(1).getMonthValue();
        //recorro el bucle de ventas en busca de cuantos clientes hay con esa fecha
        for (Venta lventa : productosVendidos) {
            if (lventa.getFechaVenta().getYear() == anioactual
                    && (lventa.getFechaVenta().getMonthValue()) == mesAnterior) {
                {
                    prodsVendUltimoMes.add(lventa);
                }
            }
        }
        System.out.println("ventas extraidas");
        System.out.println(prodsVendUltimoMes.size());
        if (prodsVendUltimoMes.size() > 0) {
            //declaración de variables
            int maximo = 0;//nº de veces que un dni se ha repetido más veces
            String dniMaximo = "";//el dni resultante del máximo numero de repeticiones
            int gastoMaximo = 0;//cantidad de dinero por el cliente que mas ha gastado
            String dniGastoMaximo = "";//el dni resultante del cliente que mas ha gastado

            for (Cliente cli : listaClientes) {
                int contador = 0;//contador de coincidencias de DNI
                int gasto = 0;//contador de gasto de dinero por cada DNI
                for (Venta vta : prodsVendUltimoMes) {
                    if (cli.getDNI().equalsIgnoreCase(vta.getDni())) {
                        contador++;
                        if (maximo <= contador) {
                            maximo = contador;
                            dniMaximo = cli.getDNI();//aqui obtengo el cliente que más operaciones ha hecho
                        }
                        gasto += (vta.getProducto().getPrecio() * vta.getUnidades());
                        if (gastoMaximo <= gasto) {
                            gastoMaximo = gasto;
                            dniGastoMaximo = cli.getDNI();//aquí obtengo el cliente que mas gasto ha tenido
                        }
                    }
                }
            }
            Cliente cl1 = encontrarCliente(dniMaximo);
            Cliente cl2 = encontrarCliente(dniGastoMaximo);
            //if else para mostrar quien gasta más y quien compra más veces, si coinciden se muestra un solo mensaje
            if (cl1.getDNI().equalsIgnoreCase(cl2.getDNI())) {
                System.out.println("");
                System.out.println("el cliente que ha gastado más y ha hecho más operaciones en el último mes es");
                System.out.println(cl1.getNombre() + " con DNI " + cl1.getDNI() + " y con gasto de " + gastoMaximo + " € y " + maximo + " operacion/es");
                System.out.println("");
            } else {
                System.out.println("");
                System.out.println("Cliente que ha hecho más operaciones en el último mes");
                System.out.println(cl1.getNombre() + " con DNI " + cl1.getDNI() + " y " + maximo + " operacion/es");
                System.out.println("");
                System.out.println("Cliente que ha hecho más gasto en el último mes");
                System.out.println(cl2.getNombre() + " con DNI " + cl2.getDNI() + " y " + gastoMaximo + " €");
                System.out.println("");
            }
        } else {
            System.out.println("No hay compras en el ultimo mes");
        }
    }

    /**
     * metodo privado que encuentra el cliente por dni y lo devuelve
     */
    private Cliente encontrarCliente(String dni) {
        Cliente cl = null;
        boolean coincidencia = false;//coincidencia del dni con más operacciones y el DNI de cliente
        int i = 0;
        while (!coincidencia && listaClientes.size() > i) {
            if (listaClientes.get(i).getDNI().equalsIgnoreCase(dni)) {
                coincidencia = true;
                cl = listaClientes.get(i);
            } else {
                i++;
            }
        }
        return cl;
    }
    /**
     *muestra Factura en una fecha concreta
     */
    public void obtenerFactura() {

        Cliente clienteAFacturar;//array temporal que almacena ventas de ese cliente en ese mes
        ArrayList<Venta> listadoVentasAFacturar = new ArrayList<Venta>();
        Scanner teclado = new Scanner(System.in);
        if (productosVendidos.isEmpty()) {
            System.out.println("no pueden emitirse facturas, no existen ventas");
        }//primera condición, si no existen ventas no se pueden hacer facturas
        else {
            System.out.println("");
            System.out.println("estas son todas las ventas que existen, decida una para emitir factura");
            System.out.println("");
            mostrarVentasRealizadas();//se muestran todas las ventas para elegirlas

            String dni, fechaCompra;
      
            System.out.println("");
            System.out.println("introduzca dni de cliente");
            dni = teclado.nextLine();
            clienteAFacturar = encontrarCliente(dni);
            if (clienteAFacturar == null) {
                System.out.println("no hay facturas que podamos mostrar");
            } else {
                ArrayList<Venta> VentasEncontradas2 = mostrarVentasPorDNI(dni);

                ///----bucle de control de fecha----
                boolean validacion = false;
                LocalDate fcFactura = null;
                do {
                    try {
                        System.out.println("introduzca fecha de venta");
                        System.out.println("formato: DD/MM/AAAA");

                        fechaCompra = teclado.next();
                        DateTimeFormatter fechaFormateadaFactura = DateTimeFormatter.ofPattern("dd/LL/yyyy");

                        fcFactura = LocalDate.parse(fechaCompra, fechaFormateadaFactura);
                        validacion = true;
                    } catch (Exception e) {
                        System.out.println("introduzca fecha correcta");
                    }

                } while (!validacion);
                for (Venta ventaFacturar : VentasEncontradas2) {
                    if (ventaFacturar.getFechaVenta().equals(fcFactura)) {
                        listadoVentasAFacturar.add(ventaFacturar);
                    }
                }

                if (listadoVentasAFacturar.isEmpty()) {
                    System.out.println("en esa fecha no hay facturas de ese cliente");
                } else {
                    crearFactura(listadoVentasAFacturar, clienteAFacturar);//aqui llamo al metodo crearFactura
                }
            }
        }
    }

    /**
     * se muestra la factura para el cliente con una fecha concreta y una venta
     */
    private void crearFactura(ArrayList<Venta> listadoVentasAFacturar, Cliente cliente) {

        DateTimeFormatter fechaFormateada = DateTimeFormatter.ofPattern("dd/LL/yyyy");
        //declaracion de variables para imprimir una factura
        String dniCliente = cliente.getDNI();
        String nombreCliente = cliente.getNombre();//dato de facturacion del cliente
        String direccionCliente = cliente.getDireccion();//dato de facturacion del cliente
        String fechaFactura = listadoVentasAFacturar.get(0).getFechaVenta().format(fechaFormateada);
        String tipoProducto1, marcaProducto1 = "";
        double precioUdProducto1 = 0;
        int udsProducto1 = 0;
        double total = 0;
        double sumatorio = 0;
        double IVA = 0.21;
        double ivaNeto = 0;
        double totalFactura = 0;

        System.out.println("---------------------------------------------------------------------------");
        System.out.println("-" + "Optica RosaVision                                                        " + "-");
        System.out.println("-" + "C/San Luis,3                                                             " + "-");
        System.out.println("-" + "CIF:F57155792                                                            " + "-");
        System.out.println("-" + "CP: 41003 - SEVILLA                                                      " + "-");
        System.out.println("-" + "                                                                         " + "-");
        System.out.println("-" + "                                                                         " + "-");
        System.out.println("-" + "Fecha de Factura: " + fechaFactura + "                                             " + "-");
        System.out.println("-" + "Cliente: " + nombreCliente + " dni: " + dniCliente + "                         " + "-");
        System.out.println("-" + "Direccion: " + direccionCliente + "                                         " + "-");
        System.out.println("-" + "                                                                         " + "-");
        System.out.println("-" + "       TIPO        MARCA      PRECIO    UDS  TOTAL                       " + "-");

        for (int i = 0; i < listadoVentasAFacturar.size(); i++) {
            String espacio = "";
            tipoProducto1 = listadoVentasAFacturar.get(i).getProducto().getTipo();
            marcaProducto1 = listadoVentasAFacturar.get(i).getProducto().getMarca();
            precioUdProducto1 = listadoVentasAFacturar.get(i).getProducto().getPrecio();
            udsProducto1 = listadoVentasAFacturar.get(i).getUnidades();
            total = precioUdProducto1 * udsProducto1;
            sumatorio += total;
            ivaNeto = Math.round(((sumatorio * IVA) * 100d) / 100d);
            totalFactura = Math.round(((sumatorio * (1 + IVA)) * 100d) / 100d);
            if (tipoProducto1.equalsIgnoreCase("gafas")) {
                espacio = "      ";
            }
            System.out.println("     " + tipoProducto1 + espacio + "    " + marcaProducto1 + "    " + precioUdProducto1 + "    " + udsProducto1 + "   " + total);
        }
        System.out.println("-" + "                                                                         " + "-");
        System.out.println("-                                        " + " TOTAL SIN IVA: " + sumatorio + "            -");
        System.out.println("-                                        " + " IVA del " + (IVA * 100) + " % : " + ivaNeto + "          -");
        System.out.println("-                                        " + " TOTAL CON IVA: " + totalFactura + "            -");
        System.out.println("---------------------------------------------------------------------------");

    }

    /**
     * lee array de clientes y lo escribe en archivo datosCliente
     *
     * @param ficheroClientes
     */
    public void grabarFicheroClientes(File ficheroClientes) {
        Scanner teclado = new Scanner(System.in);

        try {
            if (ficheroClientes.exists()) {
                System.out.println("el fichero existe,¿quiere sobreescribir?");
                System.out.println("1 si, 2 no");
                int opcion = teclado.nextInt();
                switch (opcion) {
                    case 1:
                        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ficheroClientes));
                        for (int i = 0; i < listaClientes.size(); i++) {
                            oos.writeObject(listaClientes.get(i));
                        }
                        oos.close();
                        break;
                    case 2:
                        System.out.println(" ");
                        break;
                }
            } else {
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ficheroClientes))) {
                    for (int i = 0; i < listaClientes.size(); i++) {
                        oos.writeObject(listaClientes.get(i));
                    }
                }
                System.out.println("fichero creado");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado" + e);
        } catch (IOException e) {
            System.out.println("ERROR E/S" + e);
        }
    }

    /**
     * vuelca el fichero datosCliente en el array de clientes
     * @param ficheroClientes
     */
    public void volcarFicheroEnArray(File ficheroClientes) {

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ficheroClientes));
            while (true) {
                Cliente cliente = (Cliente) ois.readObject();
                listaClientes.add(cliente);
            }
        } catch (EOFException e) {
            System.out.println("Fin de archivo");
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado" + e);
        } catch (IOException e) {
            System.out.println("ERROR E/S" + e);
        } catch (ClassNotFoundException e) {
            System.out.println("Clase no encontrada" + e);
        }
    }

    /**
     * graba array de productosAlmacen en fichero ProductoAlmacen
     * @param ficheroProductoAlmacen
     */
    public void grabarFicheroProductoAlmacen(File ficheroProductoAlmacen) {
        Scanner teclado = new Scanner(System.in);

        try {
            if (ficheroProductoAlmacen.exists()) {
                //cargaRealizada=true;
                System.out.println("el fichero existe,¿quiere sobreescribir?");
                System.out.println("1 si, 2 no");
                int opcion = teclado.nextInt();
                switch (opcion) {
                    case 1:

                        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ficheroProductoAlmacen));
                        for (int i = 0; i < productosEnStock.size(); i++) {
                            oos.writeObject(productosEnStock.get(i));
                        }
                        oos.close();
                        break;
                    case 2:
                        System.out.println(" ");
                        break;
                }
            } else {
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ficheroProductoAlmacen))) {
                    for (int i = 0; i < productosEnStock.size(); i++) {
                        oos.writeObject(productosEnStock.get(i));
                    }
                }
                System.out.println("fichero creado");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado" + e);
        } catch (IOException e) {
            System.out.println("ERROR E/S" + e);
        }
    }

    /**
     * vuelca el contenido de fichero productoAlmacen en el array
     * productosEnStock
     *
     * @param ficheroProductoAlmacen
     */
    public void volcarFicheroProductoClienteEnArray(File ficheroProductoAlmacen) {

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ficheroProductoAlmacen));
            while (true) {
                ProductoAlmacen palmacen = (ProductoAlmacen) ois.readObject();
                productosEnStock.add(palmacen);
            }
        } catch (EOFException e) {
            System.out.println("Fin de archivo");
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado" + e);
        } catch (IOException e) {
            System.out.println("ERROR E/S" + e);
        } catch (ClassNotFoundException e) {
            System.out.println("Clase no encontrada" + e);
        }
    }

    /**
     * graba array de productosVendidos en fichero Ventas
     *
     * @param ficheroVentas
     */
    public void grabarFicheroVentas(File ficheroVentas) {

        Scanner teclado = new Scanner(System.in);
        try {
            if (ficheroVentas.exists()) {
                System.out.println("el fichero existe,¿quiere sobreescribir?");
                System.out.println("1 si, 2 no");
                int opcion = teclado.nextInt();
                switch (opcion) {
                    case 1:
                        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ficheroVentas));
                        for (int i = 0; i < productosVendidos.size(); i++) {
                            oos.writeObject(productosVendidos.get(i));
                        }
                        oos.close();
                        break;
                    case 2:
                        System.out.println(" ");
                        break;
                }
            } else {
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ficheroVentas))) {
                    for (int i = 0; i < productosVendidos.size(); i++) {
                        oos.writeObject(productosVendidos.get(i));
                    }
                }
                System.out.println("fichero creado");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado" + e);
        } catch (IOException e) {
            System.out.println("ERROR E/S" + e);
        }
    }

    /**
     * graba el ficheroProducoAlmacen en el Array de productosVendidos
     *
     * @param ficheroVentas
     */
    public void volcarFicheroVentasAlmacenEnArray(File ficheroVentas) {

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ficheroVentas));
            while (true) {
                Venta venta = (Venta) ois.readObject();
                productosVendidos.add(venta);
            }
        } catch (EOFException e) {
            System.out.println("Fin de archivo");
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado" + e);
        } catch (IOException e) {
            System.out.println("ERROR E/S" + e);
        } catch (ClassNotFoundException e) {
            System.out.println("Clase no encontrada" + e);
        }
    }
}