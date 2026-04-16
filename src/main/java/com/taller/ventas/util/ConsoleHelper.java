package com.taller.ventas.util;

import java.util.Scanner;

public final class ConsoleHelper {
    private static final Scanner scanner = new Scanner(System.in);

    private ConsoleHelper() {}

    public static String leerString(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    public static int leerInt(String mensaje) {
        System.out.print(mensaje);
        while (!scanner.hasNextInt()) {
            System.out.print("Ingrese un número entero válido: ");
            scanner.next();
        }
        int valor = scanner.nextInt();
        scanner.nextLine();
        return valor;
    }

    public static double leerDouble(String mensaje) {
        System.out.print(mensaje);
        while (!scanner.hasNextDouble()) {
            System.out.print("Ingrese un número decimal válido: ");
            scanner.next();
        }
        double valor = scanner.nextDouble();
        scanner.nextLine();
        return valor;
    }

    public static boolean leerBoolean(String mensaje) {
        System.out.print(mensaje + " (s/n): ");
        String resp = scanner.nextLine().trim().toLowerCase();
        return resp.equals("s") || resp.equals("si") || resp.equals("y") || resp.equals("yes");
    }
}