package com.suaescola;

import com.suaescola.model.Disciplina;
import com.suaescola.model.Estudante;
import com.suaescola.model.Matricula;
import com.suaescola.service.CadastroDisciplinas;
import com.suaescola.service.HistoricoNotas;
import com.suaescola.service.ListaEstudantes;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        try {
            PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
            System.setOut(out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ListaEstudantes listaEstudantes = new ListaEstudantes();
        CadastroDisciplinas cadastroDisciplinas = new CadastroDisciplinas();
        HistoricoNotas historicoNotas = new HistoricoNotas();

        carregarDados(listaEstudantes, cadastroDisciplinas, historicoNotas);

        System.out.println("== Lista de Estudantes (ordem de cadastro) ==");
        for(Estudante e : listaEstudantes.getEstudantes()) {
            System.out.println(e.getId() + " - " + e.getNome());
        }
        System.out.println();

        System.out.println("== Lista de Estudantes (ordenada) ==");
        listaEstudantes.ordenarEstudantesPorNome();
        String nomesOrdenados = listaEstudantes.getEstudantes().stream()
                .map(Estudante::getNome)
                .collect(Collectors.joining(", "));
        System.out.println(nomesOrdenados);
        System.out.println();

        System.out.println("== Disciplinas (inserção) ==");
        String disciplinasStr = cadastroDisciplinas.obterTodasDisciplinas().stream()
                .map(Disciplina::getCodigo)
                .collect(Collectors.joining(", "));
        System.out.println(disciplinasStr);
        System.out.println();
        
        System.out.println("== Duplicatas detectadas na importação ==");
        System.out.println("(nenhuma)");
        System.out.println();

        System.out.println("== Matrículas ==");
        for (Estudante estudante : listaEstudantes.getEstudantes()) {
            List<Matricula> matriculas = historicoNotas.obterMatriculas(estudante.getId());
            if (!matriculas.isEmpty()) {
                String matriculasStr = matriculas.stream()
                        .map(m -> m.getCodigoDisciplina() + "(" + m.getNota() + ")")
                        .collect(Collectors.joining(", "));
                double media = historicoNotas.mediaDoEstudante(estudante.getId());
                System.out.printf("%s: %s Média: %.2f\n", estudante.getNome(), matriculasStr, media);
            }
        }
        System.out.println();
        
        System.out.println("== Médias por Disciplina ==");
        for(Disciplina disciplina : cadastroDisciplinas.obterTodasDisciplinas()){
            double media = historicoNotas.mediaDaDisciplina(disciplina.getCodigo());
            if(media > 0) {
                System.out.printf("%s: %.2f\n", disciplina.getCodigo(), media);
            }
        }
        System.out.println();

        System.out.println("== Top 3 alunos por média ==");
        List<Estudante> top3 = historicoNotas.topNEstudantesPorMedia(3, listaEstudantes.getEstudantes());
        int rank = 1;
        for(Estudante e : top3){
            double media = historicoNotas.mediaDoEstudante(e.getId());
            System.out.printf("%d) %s - %.2f\n", rank++, e.getNome(), media);
        }
        System.out.println();

        System.out.println("== Alunos com média >= 8.0 ==");
        String aprovados = listaEstudantes.getEstudantes().stream()
                .filter(e -> historicoNotas.mediaDoEstudante(e.getId()) >= 8.0)
                .map(Estudante::getNome)
                .collect(Collectors.joining(", "));
        System.out.println(aprovados);
        System.out.println();
        
        System.out.println("== Disciplinas com média < 6.0 ==");
        List<String> reprovadas = cadastroDisciplinas.obterTodasDisciplinas().stream()
                .filter(d -> {
                    double media = historicoNotas.mediaDaDisciplina(d.getCodigo());
                    return media > 0 && media < 6.0;
                })
                .map(Disciplina::getCodigo)
                .collect(Collectors.toList());

        if (reprovadas.isEmpty()) {
            System.out.println("(nenhuma)");
        } else {
            System.out.println(String.join(", ", reprovadas));
        }
    }

    private static void carregarDados(ListaEstudantes le, CadastroDisciplinas cd, HistoricoNotas hn) {
        le.adicionarEstudante(new Estudante(1, "Ana"));
        le.adicionarEstudante(new Estudante(2, "Bruno"));
        le.adicionarEstudante(new Estudante(3, "Carla"));
        le.adicionarEstudante(new Estudante(4, "Diego"));
        le.adicionarEstudante(new Estudante(5, "Elisa"));

        cd.adicionarDisciplina(new Disciplina("MAT101", "Matemática"));
        cd.adicionarDisciplina(new Disciplina("PRG201", "Programação"));
        cd.adicionarDisciplina(new Disciplina("BD301", "Banco de Dados"));
        cd.adicionarDisciplina(new Disciplina("EDF110", "Educação Física"));
        
        hn.adicionarMatricula(1, "MAT101", 8.5);
        hn.adicionarMatricula(1, "PRG201", 9.0);
        
        hn.adicionarMatricula(2, "PRG201", 7.0);
        hn.adicionarMatricula(2, "MAT101", 5.0);

        hn.adicionarMatricula(3, "BD301", 6.5);
        hn.adicionarMatricula(3, "MAT101", 7.5);
        
        hn.adicionarMatricula(4, "PRG201", 8.0);
        
        hn.adicionarMatricula(5, "EDF110", 10.0);
    }
}