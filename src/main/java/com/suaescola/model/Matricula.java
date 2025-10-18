package com.suaescola.model;

public class Matricula {
    private String codigoDisciplina;
    private double nota;

    
    public Matricula(String codigoDisciplina, double nota) {
        this.codigoDisciplina = codigoDisciplina;
        this.nota = nota;
    }

    
    public String getCodigoDisciplina() {
        return codigoDisciplina;
    }

    public void setCodigoDisciplina(String codigoDisciplina) {
        this.codigoDisciplina = codigoDisciplina;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    
    @Override
    public String toString() {
        return "Matricula{" +
               "codigoDisciplina='" + codigoDisciplina + '\'' +
               ", nota=" + nota +
               '}';
    }
}