package PF;

import java.util.Arrays;
import java.util.Scanner;

public class Pedidos {
    static final String title = "Almacen - OEFA !";
    static final String[] PRODUCTOS = {"P1", "P2", "P3", "P4", "P5", "P6", "P7", "P8", "P9", "P10"};
    static int[] STOCKS = {12, 25, 55, 4, 3, 8, 9, 5, 32, 22};

    static String[] PEDIDOS = new String[0];
    static String[] DETALLES = new String[0];

    static String[] AUX_DET_PROD = new String[0];
    static int[] AUX_DET_CANT = new int[0];
    static int[] AUX_PROD_INDEX = new int[0];

    public static void main(String[] args) {

        String ope = "";

        do {

            if (ope.equalsIgnoreCase("P")) {
                ope = IngresePedido();

            } else if (ope.equalsIgnoreCase("R")) {
                ope = ReportePedido();

            } else if (ope.equalsIgnoreCase("K")) {
                ope = MovimientosStock();

            } else {
                ope = Menu();
            }

        } while (!ope.equalsIgnoreCase("S"));


        System.out.println("Aplicacion terminada!");
    }

    public static String Menu() {
        Scanner sc = new Scanner(System.in);
        String res;

        System.out.println(title);
        System.out.println("================================================================");
        System.out.println("Stock (K)\t|Nuevo pedido (P)\t|Reporte Pedidos (R)\t|Salir (S)");
        System.out.print("Seleccione una opcion: ");

        res = sc.nextLine();

        return res;
    }

    public static String MovimientosStock() {
        System.out.println(title);
        System.out.println("Movimientos - Stock");
        System.out.println("================================================================");
        System.out.println("Codigo\t\t|Stock");
        for (int i = 0; i < PRODUCTOS.length; i++) {
            System.out.printf("%s  \t\t|%d \n", PRODUCTOS[i], STOCKS[i]);
        }

        System.out.println();

        return "M";
    }


    public static String IngresePedido() {
        Scanner sc = new Scanner(System.in);
        String res = "M";

        String codigo;
        int cantidad;

        System.out.println(title);
        System.out.print("Ingrese producto : ");
        codigo = sc.nextLine().toUpperCase();

        System.out.print("Ingrese cantidad : ");
        cantidad = sc.nextInt();

        int index = -1;
        boolean productoExiste = false;
        boolean stockSuficiente = false;

        for (int i = 0; i < PRODUCTOS.length; i++) {
            if (PRODUCTOS[i].equalsIgnoreCase(codigo)) {
                index = i;
                productoExiste = true;
                stockSuficiente = true;
                break;
            }
        }

        if (!productoExiste) {
            System.out.println("Codigo no registrado!");
            System.out.print("Desea ingresar otro codigo Si (S) o Finalizar (F): ");
            res = sc.next();
        } else if (STOCKS[index] < cantidad) {
            stockSuficiente = false;
            System.out.println("Stocks insuficiente!");
            System.out.print("Desea ingresar otro codigo Si (S) o Finalizar (F): ");
            res = sc.next();
        }

        if (productoExiste && stockSuficiente) {
            res = AgregarItem(codigo, cantidad, index);
        }

        if (res.equalsIgnoreCase("F")) res = FinalizarPedido();
        else if (res.equalsIgnoreCase("S")) res = "P";
        else res = "M";

        return res;
    }

    public static String AgregarItem(String codigo, int cantidad, int index) {
        int _n = AUX_DET_PROD.length;

        AUX_DET_PROD = Arrays.copyOf(AUX_DET_PROD, _n + 1);
        AUX_DET_CANT = Arrays.copyOf(AUX_DET_CANT, _n + 1);
        AUX_PROD_INDEX = Arrays.copyOf(AUX_PROD_INDEX, _n + 1);

        AUX_DET_PROD[_n] = codigo;
        AUX_DET_CANT[_n] = cantidad;
        AUX_PROD_INDEX[_n] = index;

        Scanner scn = new Scanner(System.in);
        System.out.println("Producto agregado a la lista.");
        System.out.print("Desea agregar mas productos? Si (S) o Finalizar (F): ");

        return scn.next();
    }

    public static String FinalizarPedido() {
        if (AUX_DET_PROD.length > 0) {
            int _n = PEDIDOS.length;
            PEDIDOS = Arrays.copyOf(PEDIDOS, _n + 1);
            DETALLES = Arrays.copyOf(DETALLES, _n + 1);

            StringBuilder sBuilder = new StringBuilder();

            PEDIDOS[_n] = String.format("P%05d\t|%d", (_n + 1), AUX_DET_PROD.length);

            for (int i = 0; i < AUX_DET_PROD.length; i++) {
                sBuilder.append(String.format("%d\t |%s\t\t|%d", i + 1, AUX_DET_PROD[i], AUX_DET_CANT[i]));

                if (i + 1 < AUX_DET_PROD.length)
                    sBuilder.append("@");

                STOCKS[AUX_PROD_INDEX[i]] = STOCKS[AUX_PROD_INDEX[i]] - AUX_DET_CANT[i];
            }

            DETALLES[_n] = sBuilder.toString();

            System.out.println("Solicitud registrado !");
            System.out.println();

            NotificarAlAlmacen();
        }

        AUX_DET_PROD = new String[0];
        AUX_DET_CANT = new int[0];
        AUX_PROD_INDEX = new int[0];

        return "M";
    }

    public static void NotificarAlAlmacen() {
        System.out.println("Notificando al almacen...");
        System.out.println();
    }

    public static String ReportePedido() {
        System.out.println(title);
        System.out.println("Reporte de pedidos!");
        System.out.println("================================================================");

        if (PEDIDOS.length > 0) {
            for (int i = 0; i < PEDIDOS.length; i++) {
                String _pedido = PEDIDOS[i];
                String _detalle = DETALLES[i];

                System.out.println("Nro \t|Total Item");
                System.out.println(_pedido);

                System.out.println("Item |Codigo\t|Cantidad");
                for (String p : _detalle.split("@")) {
                    System.out.println(p);
                }

                System.out.println();
            }
        } else {
            System.out.println("Sin Registro de pedidos.");

            System.out.println();
        }

        return "M";
    }
}