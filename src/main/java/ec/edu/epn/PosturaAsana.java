package ec.edu.epn;

public class PosturaAsana {
    private String sanscrito;
    private String ingles;

    private String español;
    private String palabrasBase;

    public PosturaAsana(String sanscrito, String ingles, String español, String palabrasBase) {
        this.sanscrito=sanscrito;
        this.palabrasBase=palabrasBase;
        this.ingles=ingles;
        this.español = español;
    }

    public String getSanscrito() {
        return sanscrito;
    }

    public void setSanscrito(String sanscrito) {
        this.sanscrito = sanscrito;
    }

    @Override
    public String toString() {
        return "Postura:\n" +
                "Sanscrito='" + sanscrito +"\n"
                + "Ingles='" + ingles +"\n"
                + "Español='" + español +"\n"
                + "PalabrasBase: \n"+
                presentarTraducionPalabrasBase(this.palabrasBase);
    }
    public String presentarTraducionPalabrasBase(String palabrasBase){
        String resultado="";
        String[] traducion = palabrasBase.split(";");
        int i=0;
        for(i=0;i<traducion.length;i++){
            resultado+=traducion[i]+"\n";
        }
        return  resultado;
    }
}
