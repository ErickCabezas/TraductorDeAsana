package ec.edu.epn;

public class Morfema {

    private String sanscrito;
    private String ingles;
    private String español;

    public Morfema(String sanscrito, String ingles, String español) {
        this.sanscrito = sanscrito;
        this.ingles = ingles;
        this.español = español;
    }

    @Override
    public String toString() {
        return "\nTraducción del morfema: " + sanscrito +
                "\n==> Ingles = " + ingles +
                "\n==> Español = " + español;
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
}
