package ui;

import java.util.Scanner;
import model.Controller;

public class Executable {

    private Scanner keyboardReader;
    private Controller controller;

    public static void main(String[] args) {

        System.out.println("Welcome to Surtimerca");
        Executable exe = new Executable();
        exe.showMenu();

    }

    public Executable() {
        keyboardReader = new Scanner(System.in);
        controller = new Controller();
    }

    public void showMenu() {

        int option = 0;

        do {

            System.out.println("MAIN MENU");
            System.out.println(
                    "Please pick an option\n"
                    + "(1) Register a product\n"
                    + "(2) Show product list \n"
                    + "(3) Save products \n"
                    + "(4) Read products \n"
                    + "(5) generate reports \n"
                    + "(0) To close the application");
            option = keyboardReader.nextInt();

            switch (option) {
                case 0:
                    System.out.println("Thanks for using our services!");
                    break;
                case 1:
                    registerProduct();
                    break;
                case 2:
                    System.out.println(controller.showProductList());
                    break;
                case 3:
                    System.out.println(controller.saveProductsToFile());
                    System.out.println("El listado de productos ha sido guardado");
                    break;
                case 4:
                    System.out.println(controller.loadProductsFromFile());
                    System.out.println("Productos cargados exitosamente");
                    break;
                case 5:
                    System.out.println(controller.generateReports());
                    
                    break;
                default:
                    System.out.println("Error, type a valid option");

            }

        } while (option != 0);

    }

    public void registerProduct() {

        keyboardReader.nextLine();

        System.out.println("REGISTRO DE UN NUEVO PRODUCTO");

        System.out.println("Digite el nombre del producto");
        String name = keyboardReader.nextLine();

        System.out.println("Digite el precio del producto");
        double price = keyboardReader.nextDouble();

        System.out.println("Digite las unidades disponibles del producto");
        int units = keyboardReader.nextInt();

        if (controller.registerProduct(name, price, units)) {
            System.out.println("Producto registrado exitosamente");
        } else {
            System.out.println("Error, el producto no se pudo registrar");
        }

    }

}
