package ec.edu.epn;

public class PosturaAsana {
    private String sanscrito;
    private String ingles;
    private String español;
    private String palabrasBase;

    public PosturaAsana(String sanscrito, String ingles, String español, String palabrasBase) {
        this.sanscrito = sanscrito;
        this.ingles = ingles;
        this.español = español;
        this.palabrasBase=palabrasBase;
    }

    public String getSanscrito() {
        return sanscrito;
    }

    public void setSanscrito(String sanscrito) {
        this.sanscrito = sanscrito;
    }

    @Override
    public String toString() {
        return "Traducción de la postura: " + sanscrito +
                "\n==> Inglés: " + ingles +
                "\n==> Español: " + español+
                "\n"+presentarTraducionDeMorfemas(this.palabrasBase);
    }
    public String presentarTraducionDeMorfemas(String palabrasBase){
        String traduccionMorfemas="";
        String[] morfemas = palabrasBase.split(";");
        Diccionario diccionario=new Diccionario();
        int i=0;
        for(i=0;i<morfemas.length;i++){
            traduccionMorfemas+=diccionario.buscarMorfema(morfemas[i]);
        }
        return  traduccionMorfemas;
    }
}
