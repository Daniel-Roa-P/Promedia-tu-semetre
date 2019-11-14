package com.example.calculatusemestre;

import java.io.Serializable;

public class Contenedor implements Serializable{

    private static final long serialVersionUID = -5435670920302756945L;
    private String nota = "";
    private String porcentaje = "";
    private String texto1 = "holi";
    private String texto2 = "no holi";

    public String getNota() { return nota; }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(String porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getTexto1() { return texto1; }

    public void setTexto1(String texto1) { this.texto1 = texto1; }

    public String getTexto2() { return texto2; }

    public void setTexto2(String texto2) { this.texto2 = texto2; }
}
