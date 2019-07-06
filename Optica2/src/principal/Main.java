package principal;

import gestionOptica.Gestion;
import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Alberto
 */
public class Main {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        String ruta = "datosCliente.ddr";
        String ruta2 = "datosProductoAlmacen.ddr";
        String ruta3 = "datosVentas.ddr";
        File ficheroClientes = new File(ruta);
        File ficheroProductoAlmacen = new File(ruta2);
        File ficheroVentas = new File(ruta3);
        Gestion g = new Gestion();
        Scanner teclado = new Scanner(System.in);
        int opcion;

        if ((ficheroClientes.exists())
                && (ficheroProductoAlmacen.exists())
                && (ficheroVentas.exists())) {
            g.volcarFicheroEnArray(ficheroClientes);
            System.out.println("Clientes cargados desde fichero");
            g.volcarFicheroProductoClienteEnArray(ficheroProductoAlmacen);
            System.out.println("Stock de productos cargado desde fichero");
            g.volcarFicheroVentasAlmacenEnArray(ficheroVentas);
            System.out.println("Lista de ventas cargadas desde fichero");
        } else {
            System.out.println("Clientes cargados desde Array de Clientes");
            System.out.println("Stock de productos cargado desde Array de Stock de Productos");
            System.out.println("Ventas cargadas desde Array de Ventas");
            g.cargaInicialClientes();
            g.cargaInicialProductos();
        }
        do {
            try {
                System.out.println("introduzca opción");
                System.out.println("1º.Mostrar productos disponibles");
                System.out.println("2º-Mostrar productos por tipo");
                System.out.println("3º-Mostrar productos con Stock menor a 2 unidades");
                System.out.println("4º-Hacer venta");
                System.out.println("5º-Mostrar ventas realizadas");
                System.out.println("6º-Mostrar clientes");
                System.out.println("7º-Usuario con mayor nº de ventas en un mes/Usuario con mayor gasto en un mes");
                System.out.println("8º-Obtener factura de compra de un cliente por fecha");
                System.out.println("9º-Salir");
                opcion = teclado.nextInt();
                switch (opcion) {
                    case 1://ok
                        System.out.println("Productos disponibles en óptica\n");
                        g.mostrarProductosDisponibles();
                        System.out.println("\n");
                        break;
                    case 2://ok
                        System.out.println("Mostrar productos por tipo");
                        g.mostrarProductosPorTipo();
                        break;
                    case 3://ok
                        System.out.println("Productos con menos de 2 unidades\n");
                        g.mostrarStockProductosInferiores();
                        System.out.println("\n");
                        break;
                    case 4://ok
                        g.hacerVenta();
                        break;
                    case 5://ok
                        g.mostrarVentasRealizadas();
                        break;
                    case 6://ok
                        g.mostrarClientes();
                        break;
                    case 7://ok
                        System.out.println("Usuario con mas compras y/o mayor gasto en un mes");
                        g.contarOperaciones();
                        break;
                    case 8://ok
                        System.out.println("Obtener factura de compra de un cliente por fecha");
                        g.obtenerFactura();
                        break;
                    case 9://ok
                        g.grabarFicheroClientes(ficheroClientes);
                        g.grabarFicheroProductoAlmacen(ficheroProductoAlmacen);
                        g.grabarFicheroVentas(ficheroVentas);
                        System.out.println("fin");
                }
            } catch (InputMismatchException e) {
                System.out.println("introduzca número correcto");
                opcion = 0;
                teclado.nextLine();
            }
        } while (opcion != 9);
    }
}