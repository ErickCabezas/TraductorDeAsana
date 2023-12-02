package ec.edu.epn;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Diccionario diccionario=new Diccionario();
        System.out.println(diccionario.agregarPostura("Parivrtta Trikonasana", "Revolved Triangle Pose", "Postura del Triángulo Invertido",
                "Parivrtta=invertido=invested; Trikona=triángulo=triangle; Asana=postura=posture"));
        System.out.println(diccionario.buscarPostura("Parivrtta Janu Sirsasana"));
    }
}