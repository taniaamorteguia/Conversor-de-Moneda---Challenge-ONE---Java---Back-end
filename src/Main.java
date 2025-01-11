package src;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int opcion = 0;
        double monto;

        while (opcion != 1 && opcion != 2 && opcion != 3){
            System.out.println("Seleccione una divisa del menú: ");
            System.out.println("1. COP - Peso colombiano ");
            System.out.println("2. USD - Dólar estadounidense ");
            System.out.println("3. BRL - Real brasileño");

            String input = scanner.nextLine();
            try {
                opcion = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingresa un número válido para la opción.");
                continue;
            }

            if (opcion <= 0 || opcion > 3) {
                System.out.println("Por favor seleccione una de las opciones válidas.");
            } else {
                System.out.println("Digite el valor que desea convertir: ");
                // Leer el monto como un número decimal
                while (true) {
                    try {
                        monto = Double.parseDouble(scanner.nextLine());
                        break;  // Salir del bucle si la entrada es válida
                    } catch (NumberFormatException e) {
                        System.out.println("Por favor ingresa un número decimal válido para el monto.");
                    }
                }
                ConsultaDivisa api = new ConsultaDivisa();

                try {
                    switch (opcion){
                        case 1:
                            api.actualizarResponse("COP");
                            System.out.println("El resultado de la conversión en USD es: "+ (monto * api.consultarCambio("USD")));
                            System.out.println("El resultado de la conversión en BRL es: "+ (monto * api.consultarCambio("BRL")));
                            break;
                        case 2:
                            api.actualizarResponse("USD");
                            System.out.println("El resultado de la conversión en COP es: "+ (monto * api.consultarCambio("COP")));
                            System.out.println("El resultado de la conversión en BRL es: "+ (monto * api.consultarCambio("BRL")));
                            break;
                        case 3:
                            api.actualizarResponse("BRL");
                            System.out.println("El resultado de la conversión en COP es: "+ (monto * api.consultarCambio("COP")));
                            System.out.println("El resultado de la conversión en USD es: "+ (monto * api.consultarCambio("USD")));
                            break;
                    }

                } catch (Exception e) {
                    System.out.println("Ocurrio un error al consultar el Api ");
                    opcion = 0;
                    throw new RuntimeException(e);
                }

                System.out.println("Desea realizar otra operacion?");
                System.out.println("1. Si");
                System.out.println("2. No");
                input = scanner.nextLine();
                try {
                    opcion = Integer.parseInt(input);
                    if (opcion == 1) {
                        opcion = 0;
                    } else {
                        System.out.println("Gracias por usar mi programa");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Gracias por usar mi programa");
                    continue;
                }
            }
        }
    }
}
