package ec.edu.epn;

/*Es la clase que sirve de modelo para los morfemas.
 Como atributos tiene su traducción a español e ingles.*/
public class Morfema {

    private String sanscrito;
    private String ingles;
    private String español;


    public Morfema(String sanscrito, String ingles, String español) {
        this.sanscrito = sanscrito;
        this.ingles = ingles;
        this.español = español;
    }


    //Este método es empleado para mostrar la traducción en las interfaces.
    @Override
    public String toString() {
        return "\nTraducción del MORFEMA: " + sanscrito + "." +
                "\nEn Inglés es: " + ingles + "." +
                "\nEn Español es: " + español + ".";
    }


    /*------------Getters & Setters--------------*/

    public String getSanscrito() {return sanscrito;}

    public void setSanscrito(String sanscrito) {this.sanscrito = sanscrito;}

    public String getIngles() {return ingles;}

    public void setIngles(String ingles) {this.ingles = ingles;}

    public String getEspañol() {return español;}

    public void setEspañol(String español) {this.español = español;}

}
