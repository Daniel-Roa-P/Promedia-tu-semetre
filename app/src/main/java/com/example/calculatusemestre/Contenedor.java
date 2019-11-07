package com.example.calculatusemestre;

import java.io.Serializable;

public class Contenedor implements Serializable{

    private static final long serialVersionUID = -5435670920302756945L;
    private String nota = "";
    private String porcentaje = "";

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(String porcentaje) {
        this.porcentaje = porcentaje;
    }
}
