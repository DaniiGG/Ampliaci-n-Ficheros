package ejercicio2;

public class Main {
    public static void main(String[] args) {
        ConversorCSVaXML conversor = new ConversorCSVaXML();
        conversor.convertirCSVtoXML("estudiantes.csv", "estudiantes.xml");
    }
}
