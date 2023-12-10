package ec.edu.epn;

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;

/*La clase Diccionario tiene todas las posturas, y morfemas, incluidas sus traducciones.*/
public class Diccionario {
    private ArrayList<PosturaAsana> posturas;
    private ArrayList<Morfema> morfemas;
    private Gson gson;

    /*Al momento de contruir el objeto Diccionario, se llama a dos métodos internos
    * que tienen como función leer el archivo txt de posturas y morfemas, con el fin
    * obtener y almacenar esos datos en los ArrayList.*/
    public Diccionario() {
        gson = new Gson();
        posturas = new ArrayList<>();
        morfemas = new ArrayList<>();
        obtenerPosturas();
        obtenerMorfema();
    }

    /*Las posturas son leidas y guardadas en el ArrayList. Este funcionamiento se repite para los morfémas*/
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

    /*Este método permite ingresar nuevas posturas en el archivo txt. Este funcionamiento se repite para los morfémas*/
    public void escribirPostura(PosturaAsana postura) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/java/posturasAsana.txt", true))) {
            String json = gson.toJson(postura);
            bw.write(json);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*Es el método que permite agrear nuevas palabras. Por el momento no es utilizado.*/
    //forma de ingresar palabras base: ardha=mitad=half; chandra=luna=moon.....
    public boolean agregarPostura(String sanscrito, String ingles, String español, String palabrasBase) {
        PosturaAsana posturaAsana = new PosturaAsana(sanscrito, ingles, español, palabrasBase);
        escribirPostura(posturaAsana);
        return this.posturas.add(posturaAsana);
    }

    /*Es el método para buscar una portura en el ArrayList. Este funcionamiento se repite para los morfémas.*/
    public String buscarPostura(String sanskrit) {
        for (PosturaAsana postura : posturas) {
            if (postura.getSanscrito().equalsIgnoreCase(sanskrit)) {
                return postura.toString();
            }
        }
        return "Postura no encontrada";
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

    public void escribirMorfema(Morfema morfema) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/java/morfemas.txt", true))) {
            String json = gson.toJson(morfema);
            bw.write(json);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean agregarMorfema(String sanscrito, String ingles, String español) {
        Morfema morfema = new Morfema(sanscrito, ingles, español);
        escribirMorfema(morfema);
        return this.morfemas.add(morfema);
    }

    public String buscarMorfema(String morfemaEnSanscrito) {
        for (Morfema morfema : morfemas) {
            if (morfema.getSanscrito().equalsIgnoreCase(morfemaEnSanscrito)) {
                return morfema.toString();
            }
        }
        return "No contamos con ese morfema";
    }
}
