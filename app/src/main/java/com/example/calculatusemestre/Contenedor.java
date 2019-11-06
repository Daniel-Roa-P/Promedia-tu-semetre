package com.example.calculatusemestre;

import java.io.Serializable;

public class Contenedor implements Serializable{

    private static final long serialVersionUID = -5435670920302756945L;
    private int nota = 0;
    private int porcentaje = 0;

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }
}
