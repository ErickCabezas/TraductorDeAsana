package ec.edu.epn;

import com.google.gson.Gson;

import java.io.*;
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
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/posturasAsana.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                PosturaAsana posturaAsana = gson.fromJson(linea, PosturaAsana.class);
                if(!(posturaAsana.getSanscrito()==null)&&!(posturaAsana.getEspañol()==null)&&!(posturaAsana.getIngles()==null)&&!(posturaAsana.getPalabrasBase()==null)){
                    this.posturas.add(posturaAsana);
                }
            }
        } catch (IOException e) {
            System.out.println("Archivo no encontrado");
        }
    }


    public void obtenerMorfema() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/morfemas.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                Morfema morfemas = gson.fromJson(linea, Morfema.class);
                if(!(morfemas.getSanscrito()==null)&&!(morfemas.getEspañol()==null)&&!(morfemas.getIngles()==null)){
                    this.morfemas.add(morfemas);
                }
            }
        } catch (IOException e) {
            System.out.println("Archivo no encontrado");
        }
    }


    /*Es el método para buscar una postura en el ArrayList. El funcionamiento es igual para los morfemas*/
    public PosturaAsana buscarPostura(String sanskrit) {
        for (PosturaAsana postura : posturas) {
            if (postura.getSanscrito().equalsIgnoreCase(sanskrit)) {
                return postura;
            }
        }
        return null;
    }

    public Morfema buscarMorfema(String morfemaEnSanscrito) {
        for (Morfema morfema : morfemas) {
            if (morfema.getSanscrito().equalsIgnoreCase(morfemaEnSanscrito)) {
                return morfema;
            }
        }
        return null;
    }
}
