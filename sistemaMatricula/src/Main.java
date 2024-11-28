public class Main {
    public static void main(String[] args) {
        GestaoAlunos gestao = new GestaoAlunos();

        try {
            Aluno aluno1 = new Aluno("Maria", "123", "Engenharia");
            Aluno aluno2 = new Aluno("João", "124", "Medicina");
            gestao.adicionarAluno(aluno1);
            gestao.adicionarAluno(aluno2);

            Turma turma1 = new Turma("001", "Matemática", 1);
            gestao.matricularEmTurma(aluno1, turma1);

            gestao.matricularEmTurma(aluno2, turma1);
        } catch (AlunoInvalidoException | SemVagasException e) {
            System.err.println("Erro: " + e.getMessage());
        }

        gestao.salvarAlunosEmArquivo("alunos.dat");
        gestao.carregarAlunosDeArquivo("alunos.dat");
    }
}
