package PF;

import java.util.Scanner;

public class Pedidos {
    static final String title = "Almacen - OEFA !";
    static final String[] PRODUCTOS = {"P1", "P2", "P3", "P4", "P5", "P6", "P7", "P8", "P9", "P10"};
    static int[] STOCKS = {12, 25, 55, 4, 3, 8, 9, 5, 32, 22};
    public static void main(String[] args) {

        //Menu();
        MovimientosStock();

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

}