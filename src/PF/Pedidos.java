package PF;

import java.util.Scanner;

public class Pedidos {
    static final String title = "Almacen - OEFA !";

    public static void main(String[] args) {

        Menu();

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


}