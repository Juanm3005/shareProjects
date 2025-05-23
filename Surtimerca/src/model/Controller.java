package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.MalformedInputException;
import java.util.ArrayList;

public class Controller {

    private ArrayList<Product> productList;

    public Controller() {
        productList = new ArrayList<Product>();

    }

    private boolean registerProduct(String id, String name, double price, int unitsAvailable) {
        return productList.add(new Product(id, name, price, unitsAvailable));
    }

    public boolean registerProduct(String name, double price, int unitsAvailable) {
        return productList.add(new Product(name, price, unitsAvailable));
    }

    public String showProductList() {

        String list = "Los productos registrados en el sistema son: \n";

        for (int i = 0; i < productList.size(); i++) {

            list += (i + 1) + ". " + productList.get(i).getName() + "\n";

        }

        return list;

    }

    public String saveProductsToFile() {

        File myFile = new File("data\\database.txt");

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(myFile));

            for (Product product : productList) {
                writer.write(product.formatToFile() + "\n");
            }

            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
            return "error, no se guardo el archivo";
        }
        return "el archivo se guardo con exito";
    }

    public ArrayList<Product> lowerAvailability() {
        int avialability = Integer.MAX_VALUE;
        ArrayList<Product> producto = new ArrayList<Product>();
        for (Product product : productList) {
            if (product.getUnitsAvailable() < avialability) {
                avialability = product.getUnitsAvailable();

            }
        }

        for (Product product : productList) {
            if (product.getUnitsAvailable() == avialability) {
                producto.add(product);

            }
        }

        return producto;
    }

    public ArrayList<Product> expensiveProduct() {
        double valor = 0;
        ArrayList<Product> producto = new ArrayList<Product>();
        for (Product product : productList) {
            if (product.getPrice() > valor) {
                valor = product.getPrice();

            }
        }

        for (Product product : productList) {
            if (product.getPrice() == valor) {
                producto.add(product);

            }
        }

        return producto;
    }

    public ArrayList<Product> longestName() {
        int stringSize = 0;
        ArrayList<Product> producto = new ArrayList<Product>();
        for (Product product : productList) {
            if (product.getName().length() > stringSize) {
                stringSize = product.getName().length();
            }
        }

        for (Product product : productList) {
            if (product.getName().length() == stringSize) {
                producto.add(product);
            }
        }

        return producto;
    }

    public String generateReports() {
        File reportExpensive = new File("data\\reportExpensive.txt");
        File reportAvialable = new File("data\\reportAvialable.txt");
        File reportLongest = new File("data\\reportLongest.txt");

        try {

            reportExpensive.createNewFile();
            reportAvialable.createNewFile();
            reportLongest.createNewFile();

            ArrayList<Product> mostExpensive = expensiveProduct();
            ArrayList<Product> lowerUnits = lowerAvailability();
            ArrayList<Product> theLongestName = longestName();

            BufferedWriter expensive = new BufferedWriter(new FileWriter(reportExpensive));
            BufferedWriter avialable = new BufferedWriter(new FileWriter(reportAvialable));
            BufferedWriter name = new BufferedWriter(new FileWriter(reportLongest));

            expensive.write("the most expensive product is: \n");
            avialable.write("The product with the lowest availability is: \n");
            name.write("The product with the longest name is: \n");

            for (Product product : mostExpensive) {
                expensive.write(product.formatToFile() + "\n");
            }

            for (Product product : lowerUnits) {
                avialable.write(product.formatToFile() + "\n");
            }

            for (Product product : theLongestName) {
                name.write(product.formatToFile() + "\n");
            }

            name.flush();
            name.close();

            avialable.flush();
            avialable.close();

            expensive.flush();
            expensive.close();

        } catch (IOException e) {
            e.printStackTrace();
            return "error, no se generaron los reportes";
        }
        return "se generaron los reportes con exito";
    }

    public String loadProductsFromFile() {

        File myFile = new File("data\\database.txt");

        try {
            BufferedReader reader = new BufferedReader(new FileReader(myFile));
            String line = "";
            while ((line = reader.readLine()) != null) {

                String[] lineSplitted = line.split(" - ");

                String id = lineSplitted[0];
                String name = lineSplitted[1];
                Double price = Double.parseDouble(lineSplitted[2]);
                int units = Integer.parseInt(lineSplitted[3]);
                registerProduct(id, name, price, units);
            }

            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
            return "error al leer";

        }
        return "se leyo correctamente";
    }

}
