package ec.edu.epn;

public class PosturaAsana {
    private String sanscrito;
    private String ingles;
    private String español;
    private String palabrasBase;

    public PosturaAsana(String sanscrito, String ingles, String español) {
        this.sanscrito = sanscrito;
        this.ingles = ingles;
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
        return "Traducción de la postura: " + sanscrito +
                "\n==> Inglés: " + ingles +
                "\n==> Español: " + español;
    }
}
