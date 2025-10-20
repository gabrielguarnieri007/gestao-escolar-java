package com.suaescola.service;

import com.suaescola.model.Estudante;
import com.suaescola.model.Matricula;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class HistoricoNotas {

    private Map<Integer, List<Matricula>> historico;

    public HistoricoNotas() {
        this.historico = new HashMap<>();
    }

    public void adicionarMatricula(int idEstudante, String codigoDisciplina, double nota) {
        Matricula novaMatricula = new Matricula(codigoDisciplina, nota);
        this.historico.computeIfAbsent(idEstudante, k -> new ArrayList<>()).add(novaMatricula);
    }

    public List<Matricula> obterMatriculas(int idEstudante) {
        return this.historico.getOrDefault(idEstudante, Collections.emptyList());
    }

    public Optional<Double> obterNota(int idEstudante, String codigoDisciplina) {
        List<Matricula> matriculas = obterMatriculas(idEstudante);
        return matriculas.stream()
                .filter(m -> m.getCodigoDisciplina().equals(codigoDisciplina))
                .map(Matricula::getNota)
                .findFirst();
    }

    public void removerMatricula(int idEstudante, String codigoDisciplina) {
        List<Matricula> matriculas = obterMatriculas(idEstudante);
        if (matriculas != null) {
            matriculas.removeIf(m -> m.getCodigoDisciplina().equals(codigoDisciplina));
        }
    }

    public double mediaDoEstudante(int idEstudante) {
        List<Matricula> matriculas = obterMatriculas(idEstudante);
        return matriculas.stream()
                .mapToDouble(Matricula::getNota)
                .average()
                .orElse(0.0);
    }

    public double mediaDaDisciplina(String codigoDisciplina) {
        return this.historico.values().stream()
                .flatMap(List::stream)
                .filter(m -> m.getCodigoDisciplina().equals(codigoDisciplina))
                .mapToDouble(Matricula::getNota)
                .average()
                .orElse(0.0);
    }

    public List<Estudante> topNEstudantesPorMedia(int n, List<Estudante> todosEstudantes) {
        return todosEstudantes.stream()
                .sorted(Comparator.comparingDouble((Estudante e) -> mediaDoEstudante(e.getId())).reversed())
                .limit(n)
                .collect(Collectors.toList());
    }

    public Map<Integer, List<Matricula>> getHistoricoCompleto() {
        return this.historico;
    }
}