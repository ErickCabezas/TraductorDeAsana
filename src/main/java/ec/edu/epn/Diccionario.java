package ec.edu.epn;

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;

public class Diccionario {
    private ArrayList<PosturaAsana> posturas;
    private Gson gson;

    public Diccionario() {
        gson = new Gson();
        posturas = new ArrayList<>();
        obtenerPosturas();
    }

    public void obtenerPosturas(){
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/posturasAsana.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                PosturaAsana posturaAsana = gson.fromJson(linea, PosturaAsana.class);
                this.posturas.add(posturaAsana);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void escribirPostura(PosturaAsana postura){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/java/posturasAsana.txt", true))) {
            String json = gson.toJson(postura);
            bw.write(json);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //forma de ingresar palabras base: ardha=mitad=half; chandra=luna=moon.....
    public boolean agregarPostura(String sanscrito, String ingles, String español, String palabrasBase) {
        PosturaAsana posturaAsana=new PosturaAsana(sanscrito, ingles, español, palabrasBase);
        escribirPostura(posturaAsana);
        return this.posturas.add(posturaAsana);
    }

    public String buscarPostura(String sanskrit){
        for (PosturaAsana postura : posturas) {
            if (postura.getSanscrito().equalsIgnoreCase(sanskrit)) {
                return postura.toString();
            }
        }
        return "Postura no encontrada";
    }

}
