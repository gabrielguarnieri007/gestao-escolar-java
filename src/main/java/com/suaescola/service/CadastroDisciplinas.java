package com.suaescola.service;

import com.suaescola.model.Disciplina;
import java.util.LinkedHashSet;
import java.util.Set;

public class CadastroDisciplinas {

    private Set<Disciplina> disciplinas;

    public CadastroDisciplinas() {
        this.disciplinas = new LinkedHashSet<>();
    }

    public void adicionarDisciplina(Disciplina d) {
        this.disciplinas.add(d);
    }

    public boolean verificarDisciplina(String codigo) {
        return this.disciplinas.stream().anyMatch(d -> d.getCodigo().equals(codigo));
    }

    public void removerDisciplina(String codigo) {
        this.disciplinas.removeIf(d -> d.getCodigo().equals(codigo));
    }

    public Set<Disciplina> obterTodasDisciplinas() {
        return this.disciplinas;
    }
}