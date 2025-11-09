package com.paint.servlets.services;

import com.paint.servlets.DAOS.CanvasDAO;
import com.paint.servlets.DTO.DibuixDTO;
import com.paint.servlets.models.Canvas;
import com.paint.servlets.models.User;

import java.util.List;

public class CanvasService {
    public void guardarDibuix(User username, String name, String contingutJson){
        CanvasDAO.guardarDibuix(username, name, contingutJson);
    }
    public List<DibuixDTO> getAllDrawings(){
        return CanvasDAO.getAllDrawings();
    }
    public List<DibuixDTO> getDrawingsByUser(User user){
        return CanvasDAO.getDrawingsByUser(user);
    }
    public boolean esborrarDibuix(User user, int dibuixId){
        return CanvasDAO.esborrarDibuix(user,dibuixId);
    }
    public Canvas getDrawingById(int dibuixId) {
        return CanvasDAO.getDrawingById(dibuixId);
    }
}
