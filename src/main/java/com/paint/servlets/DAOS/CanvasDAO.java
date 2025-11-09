package com.paint.servlets.DAOS;

import com.paint.servlets.DTO.DibuixDTO; // Has usado DibuixDTO
import com.paint.servlets.models.Canvas;
import com.paint.servlets.models.User;

import java.util.*;

public class CanvasDAO {

    private final static Map<User, Map<Integer, Canvas>> drawsDatabase = new HashMap();
    private static int idGenerate = 0;

    private static int generarID() {
        int id = idGenerate;
        idGenerate++;
        return id;
    }
    private static String generarName(int id, String name) {
        if (name.isEmpty()){
            name = "Dibuix" + id;
        }
        return name;
    }

    public static void guardarDibuix(User username, String name, String contingutJson) {
        int id = generarID();
        name = generarName(id, name);

        // El constructor de Canvas se encarga del ID y la Fecha
        Canvas canvas = new Canvas(id, name, contingutJson);

        Map<Integer, Canvas> personalDrawings = drawsDatabase.get(username);

        if (personalDrawings == null){
            personalDrawings = new HashMap<>();
            drawsDatabase.put(username, personalDrawings);
        }
        personalDrawings.put(id, canvas);

        System.out.println(
                        "Data:" + canvas.getCreatedAt() +
                        "Username: " + username.getUser() +
                        " ID: " +  canvas.getId() +
                        " Nom: " + canvas.getName() +
                        " Dibuix: " + canvas.getContent());
    }

    public static List<DibuixDTO> getDrawingsByUser(User user){
        List<DibuixDTO> llistaDTO = new ArrayList<>();
        Map<Integer, Canvas> personalDrawings = drawsDatabase.get(user);

        if (personalDrawings != null){
            for (Canvas canvas : personalDrawings.values()) {
                // Convertir Canvas y User en un DTO
                DibuixDTO dto = new DibuixDTO(
                        canvas.getId(),
                        canvas.getName(),
                        user.getUser(),
                        canvas.getCreatedAt()
                );
                llistaDTO.add(dto);
            }
        }
        return llistaDTO; // Devuelve la lista (vacía o llena)
    }

    public static List<DibuixDTO> getAllDrawings(){
        List<DibuixDTO> llistaDTO = new ArrayList<>();
        // Hay que iterar sobre el "entrySet" para saber quién es el propietario (la clave)
        for (Map.Entry<User, Map<Integer, Canvas>> entry : drawsDatabase.entrySet()) {
            User user = entry.getKey(); // El propietario
            Map<Integer, Canvas> personalMap = entry.getValue(); // Sus dibujos

            for (Canvas canvas : personalMap.values()) {
                // Convierte el Canvas completo a un DTO
                DibuixDTO dto = new DibuixDTO(
                        canvas.getId(),
                        canvas.getName(),
                        user.getUser(),
                        canvas.getCreatedAt()
                );
                llistaDTO.add(dto);
            }
        }
        return llistaDTO;
    }

    public static boolean esborrarDibuix(User user, int dibuixId){
        Map<Integer, Canvas> personalDrawings = drawsDatabase.get(user);

        if (personalDrawings != null){
            Canvas dibuixEsborrat = personalDrawings.remove(dibuixId);
            return dibuixEsborrat != null;
        }
        return false;
    }

    public static Canvas getDrawingById(int dibuixId) {
        for (Map<Integer, Canvas> personalMap : drawsDatabase.values()) {
            if (personalMap.containsKey(dibuixId)) {
                return personalMap.get(dibuixId);
            }
        }
        return null;
    }
}