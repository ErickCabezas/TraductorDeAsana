package ec.edu.epn;


/*Es la clase que sirve de modelo para las posturas.
 Como atributos tiene su traducción a español e ingles, además de
 contar con sus respectivas palabras base.
 También posee un atributo que indica cual es el nombre de su imagen que
 será mostrada en el programa.*/
public class PosturaAsana {
    private String sanscrito;
    private String ingles;
    private String español;
    private String palabrasBase;
    private String nombreImagen;

    public PosturaAsana(String sanscrito, String ingles, String español, String palabrasBase, String nombreImagen) {
        this.sanscrito = sanscrito;
        this.ingles = ingles;
        this.español = español;
        this.palabrasBase = palabrasBase;
        this.nombreImagen = nombreImagen;
    }

    /*Este método se encarga de separar las palabras base, de traducirlas,y de
    almacenarlas en un String.*/
    public String presentarTraducionDeMorfemas(String palabrasBase) {
        String traduccionMorfemas = "";
        String[] morfemas = palabrasBase.split(";");
        Diccionario diccionario = new Diccionario();
        int i = 0;
        for (i = 0; i < morfemas.length; i++) {
            traduccionMorfemas += diccionario.buscarMorfemaSanscrito(morfemas[i]);
        }
        return traduccionMorfemas;
    }

    //Es el método empleado para presentar la traducción de la postura, junto a sus morfemas.
    @Override
    public String toString() {
        return "Traducción de la POSTURA: " + sanscrito + "." +
                "\nEn Inglés es: " + ingles + "." +
                "\nEn Español es: " + español + "." +
                "\n" + presentarTraducionDeMorfemas(this.palabrasBase);
    }

    /*------------Getters & Setters--------------*/
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

    public String getNombreImagen() {return nombreImagen;}

    public void setNombreImagen(String nombreImagen) {this.nombreImagen = nombreImagen;}
}
