package com.suaescola.service;

import com.suaescola.model.Estudante;
import java.util.ArrayList;
import java.util.Comparator; 
import java.util.List;
import java.util.stream.Collectors;

public class ListaEstudantes {

    private List<Estudante> estudantes;

    public ListaEstudantes() {
        this.estudantes = new ArrayList<>();
    }

    public void adicionarEstudante(Estudante e) {
        this.estudantes.add(e);
    }

    public void removerEstudantePorld(int id) {
        this.estudantes.removeIf(estudante -> estudante.getId() == id);
    }

    public Estudante obterEstudantePorIndice(int indice) {
        if (indice >= 0 && indice < estudantes.size()) {
            return this.estudantes.get(indice);
        }
        return null;
    }

    public List<Estudante> buscarEstudantesPorNome(String substring) {
        return this.estudantes.stream()
                .filter(e -> e.getNome().toLowerCase().contains(substring.toLowerCase()))
                .collect(Collectors.toList());
    }

    public void ordenarEstudantesPorNome() {
        this.estudantes.sort(Comparator.comparing(Estudante::getNome));
    }

    public List<Estudante> getEstudantes() {
        return this.estudantes;
    }
}