package ec.edu.epn;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Diccionario diccionario = new Diccionario();

        /*System.out.println(diccionario.agregarPostura(
                "Parivrtta Trikonasana",
                "Revolved Triangle Pose",
                "Postura del Triángulo Invertido"));

        System.out.println(diccionario.agregarMorfema(
                "Asana",
                "posture",
                "postura"));*/

        System.out.println(diccionario.buscarPostura("lso"));
        System.out.println(diccionario.buscarMorfema("hasta"));
    }
}