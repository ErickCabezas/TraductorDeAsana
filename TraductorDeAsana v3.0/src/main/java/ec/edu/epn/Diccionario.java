package ec.edu.epn;

import com.google.gson.Gson;

import java.io.*;
import java.text.Normalizer;
import java.util.ArrayList;

/*La clase Diccionario tiene todas las posturas, y morfemas, incluidas sus traducciones.
* En una primera instancia, estas están almacenadas en un archivo de texto. Posteriormente
* se utiliza métodos que extraen los datos de ahí, y los guardan en ArrayLists.*/
public class Diccionario {
    private ArrayList<PosturaAsana> posturas;
    private ArrayList<Morfema> morfemas;
    private Gson gson;

    /*Al momento de construir el objeto Diccionario, automáticamente se llenan los
    * ArrayLists con los datos de los archivos de texto. Esto se hace con los métodos
    * obtenerPosturas y obtenerMorfema */
    public Diccionario() {
        gson = new Gson();
        posturas = new ArrayList<>();
        morfemas = new ArrayList<>();
        obtenerPosturas();
        obtenerMorfema();
    }

    /*Las posturas son leidas y guardadas en el ArrayList. Este algoritmo es igual para los morfemas.*/
    public void obtenerPosturas() {
        posturas.clear();
        try (InputStream is = getClass().getResourceAsStream("/posturasAsana.txt");
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

            String linea;
            while ((linea = br.readLine()) != null) {
                PosturaAsana posturaAsana = gson.fromJson(linea, PosturaAsana.class);
                if (!(posturaAsana.getSanscrito() == null) && !(posturaAsana.getEspañol() == null)
                        && !(posturaAsana.getIngles() == null) && !(posturaAsana.getPalabrasBase() == null)) {
                    this.posturas.add(posturaAsana);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de texto: " + e.getMessage());
        }
    }


    public void obtenerMorfema() {
        morfemas.clear();
        try (InputStream is = getClass().getResourceAsStream("/morfemas.txt");
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

            String linea;
            while ((linea = br.readLine()) != null) {
                Morfema morfema = gson.fromJson(linea, Morfema.class);
                if (!(morfema.getSanscrito() == null) && !(morfema.getEspañol() == null)
                        && !(morfema.getIngles() == null)) {
                    this.morfemas.add(morfema);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de texto: " + e.getMessage());
        }
    }

    /*El método recive como parametro una postura que sera transformado a json para despues
    * escribirla en el archivo posturasAsana.txt*/
    public boolean escribirPostura(PosturaAsana postura) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("TraductorDeAsana v3.0/src/main/resources/posturasAsana.txt",true))) {
            String json = gson.toJson(postura);
            bw.write(json + "\n");
            obtenerPosturas();
            return true;
        } catch (IOException e) {
            System.out.println("Error al ingresar postura: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /*En este método se crea un objeto tipo postura para despues mandarlo a escribir en el archivo
     * posturasAsana.txt con el metodo escribirPostura*/
    //forma de ingresar palabras base: ardha; chandra.....
    public boolean agregarPostura(String sanscrito, String ingles, String español, String palabrasBase) {
        if(buscarPosturaSanscrito(sanscrito)==null){
            PosturaAsana posturaAsana = new PosturaAsana(sanscrito, ingles, español, palabrasBase,"newImagen");
            System.out.println(posturaAsana.getEspañol());
            return escribirPostura(posturaAsana) && this.posturas.add(posturaAsana);
        }else{
            return false;
        }
    }


    /*Es el método para buscar una postura en el ArrayList. La búsqueda se puede
     hacer por Sánscrito, Español o Ingles. Es por esto que hay 3 métodos similares,
     cada uno está especializado en un lenguaje.
     El funcionamiento es igual para los morfemas*/
    public PosturaAsana buscarPosturaSanscrito(String posturaSanscrito) {
        for (PosturaAsana postura : posturas) {
            if (postura.getSanscrito().equalsIgnoreCase(posturaSanscrito)) {
                return postura;
            }
        }
        return null;
    }

    public PosturaAsana buscarPosturaEspaniol(String posturaEspaniol ) {
        for (PosturaAsana postura : posturas) {
            if (suprimirTildes(postura.getEspañol()).equalsIgnoreCase(suprimirTildes(posturaEspaniol))) {
                return postura;
            }
        }
        return null;
    }

    public PosturaAsana buscarPosturaIngles(String posturaIngles) {
        for (PosturaAsana postura : posturas) {
            if (postura.getIngles().equalsIgnoreCase(posturaIngles)) {
                return postura;
            }
        }
        return null;
    }



    public Morfema buscarMorfemaSanscrito(String morfemaSanscrito) {
        for (Morfema morfema : morfemas) {
            if (morfema.getSanscrito().equalsIgnoreCase(morfemaSanscrito)) {
                return morfema;
            }
        }
        return null;
    }

    public Morfema buscarMorfemaEspaniol(String morfemaEspaniol) {
        for (Morfema morfema : morfemas) {
            if (suprimirTildes(morfema.getEspañol()).equalsIgnoreCase(suprimirTildes(morfemaEspaniol))) {
                return morfema;
            }
        }
        return null;
    }

    public Morfema buscarMorfemaIngles(String morfemaIngles) {
        for (Morfema morfema : morfemas) {
            if (morfema.getIngles().equalsIgnoreCase(morfemaIngles)) {
                return morfema;
            }
        }
        return null;
    }

    /*Es el método que nos ayuda a quitar las tildes en las posturas y morfemas en
     Español. Este permite que el cliente pueda ingresar la palabra con o sin acento
     y hallar de todas maneras el morfema o postura.*/
    private static String suprimirTildes(String cadena) {
        String normalized = Normalizer.normalize(cadena, Normalizer.Form.NFD);
        return normalized.replaceAll("[^\\p{ASCII}]", "");
    }

}
