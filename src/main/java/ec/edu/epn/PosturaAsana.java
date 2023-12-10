package ec.edu.epn;


/*Es la clase que sirve de modelo para las posturas.
 Como atributos tiene su traducción a español e ingles, además de
 contar con sus respectivas palabras base.*/
public class PosturaAsana {
    private String sanscrito;
    private String ingles;
    private String español;
    private String palabrasBase;

    public PosturaAsana(String sanscrito, String ingles, String español, String palabrasBase) {
        this.sanscrito = sanscrito;
        this.ingles = ingles;
        this.español = español;
        this.palabrasBase = palabrasBase;
    }

    /*Este método se encarga de separar las palabras base, de traducirlas,y de
    almacenarlas en un String.*/
    public String presentarTraducionDeMorfemas(String palabrasBase) {
        String traduccionMorfemas = "";
        String[] morfemas = palabrasBase.split(";");
        Diccionario diccionario = new Diccionario();
        int i = 0;
        for (i = 0; i < morfemas.length; i++) {
            traduccionMorfemas += diccionario.buscarMorfema(morfemas[i]);
        }
        return traduccionMorfemas;
    }

    //Es el método empleado para presentar la traducción de la postura, junto a sus morfemas.
    @Override
    public String toString() {
        return "Traducción de la POSTURA: " + sanscrito +
                "\n==> Inglés: " + ingles +
                "\n==> Español: " + español +
                "\n" + presentarTraducionDeMorfemas(this.palabrasBase);
    }


    public String getSanscrito() {
        return sanscrito;
    }

    public void setSanscrito(String sanscrito) {
        this.sanscrito = sanscrito;
    }

    public String getIngles() {
        return ingles;
    }

    public void setIngles(String ingles) {
        this.ingles = ingles;
    }

    public String getEspañol() {
        return español;
    }

    public void setEspañol(String español) {
        this.español = español;
    }

    public String getPalabrasBase() {
        return palabrasBase;
    }

    public void setPalabrasBase(String palabrasBase) {
        this.palabrasBase = palabrasBase;
    }
}
