Sistema de Gestão Escolar em Java

1. Integrantes do Grupo

    João Ínacio

    Guilherme Morais

    Gabriel Guarnieri

2. Descrição do Projeto

Este projeto é um sistema de gestão para uma escola de ensino médio, desenvolvido em Java. O programa gerencia o cadastro de estudantes, o controle de disciplinas e o lançamento de notas, utilizando as coleções Java (List, Set e Map) para manipular os dados de forma eficiente. O sistema é capaz de gerar relatórios como a lista de alunos ordenada, médias por disciplina, alunos com melhor desempenho, entre outros, e salva o resultado final em um arquivo output.txt.

3. Justificativa das Escolhas de Coleções

A seleção das coleções foi um ponto central do projeto, baseada nos requisitos de cada parte do sistema:

    List (usando ArrayList) para Estudantes:

        Motivo: A classe ListaEstudantes precisa de uma estrutura que permita acesso rápido aos elementos por seu índice (com o método obterEstudantePorIndice) e que seja eficiente para leitura e ordenação. O ArrayList é ideal para isso, pois armazena os elementos em um array contíguo, oferecendo performance excelente (O(1)) para acesso a qualquer posição. Embora a remoção seja mais lenta (O(n)), no contexto do nosso sistema, as operações de leitura e ordenação são mais frequentes.

    Set (usando LinkedHashSet) para Disciplinas:

        Motivo: O principal requisito para o cadastro de disciplinas era não permitir duplicatas e, ao mesmo tempo, manter a ordem de inserção para os relatórios. O LinkedHashSet foi a escolha perfeita, pois combina a eficiência de um HashSet (que garante elementos únicos através dos métodos equals() e hashCode()) com uma lista encadeada que preserva a ordem em que os elementos foram adicionados.

    Map (usando HashMap) para o Histórico de Notas:

        Motivo: A HistoricoNotas precisava de uma forma eficiente para associar um estudante (identificado por sua ID, um Integer) a uma lista de suas matrículas (List<Matricula>). O HashMap é a implementação mais performática para essa associação chave-valor. Ele oferece um tempo de busca, inserção e remoção praticamente constante (O(1)), o que é ideal para adicionar novas notas e buscar rapidamente o histórico de um aluno específico para calcular sua média.

4. Como Executar o Programa

Para compilar e executar o projeto e gerar o arquivo output.txt, siga os passos:

    Pré-requisitos: Certifique-se de ter um JDK (Java Development Kit) versão 8 ou superior instalado.

    Clone o Repositório: Baixe ou clone este repositório para o seu computador.

    Abra o Projeto: Abra a pasta do projeto em uma IDE como VS Code ou IntelliJ.

    Execute a Classe Principal: Encontre e execute o arquivo Main.java localizado em src/main/java/com/suaescola/Main.java.

    Verifique a Saída: Após a execução, um arquivo chamado output.txt será criado na pasta raiz do projeto. Este arquivo conterá todos os relatórios gerados pelo programa.

5. Desafio Encontrado

Um dos principais desafios durante o desenvolvimento foi garantir o funcionamento correto da coleção Set com a nossa classe customizada Disciplina. Inicialmente, o Set não estava impedindo a inserção de disciplinas com o mesmo código. Descobrimos que, para uma coleção baseada em hash (como HashSet ou LinkedHashSet) diferenciar objetos, não basta que eles tenham atributos diferentes; é fundamental sobrescrever os métodos equals() e hashCode() na classe do objeto. Ao implementarmos esses métodos na classe Disciplina, baseando a lógica de igualdade no atributo codigo, o Set passou a funcionar como esperado, ignorando corretamente as duplicatas.