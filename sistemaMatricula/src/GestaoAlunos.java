import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestaoAlunos {
    private List<Aluno> alunos;
    private List<Turma> turmas;

    public GestaoAlunos() {
        this.alunos = new ArrayList<>();
        this.turmas = new ArrayList<>();
    }

    public void adicionarAluno(Aluno aluno) throws AlunoInvalidoException {
        if (aluno.getNome().isEmpty() || aluno.getCurso().isEmpty()) {
            throw new AlunoInvalidoException("Nome ou curso do aluno não pode estar vazio.");
        }
        for (Aluno a : alunos) {
            if (a.getMatricula().equals(aluno.getMatricula())) {
                throw new AlunoInvalidoException("A matrícula já está cadastrada.");
            }
        }
        alunos.add(aluno);
    }

    public void salvarAlunosEmArquivo(String nomeArquivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            oos.writeObject(alunos);
        } catch (IOException e) {
            System.err.println("Erro ao salvar alunos no arquivo: " + e.getMessage());
        }
    }

    public void carregarAlunosDeArquivo(String nomeArquivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            alunos = (List<Aluno>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado: " + e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar alunos: " + e.getMessage());
        }
    }

    public void matricularEmTurma(Aluno aluno, Turma turma) throws SemVagasException {
        turma.matricularAluno(aluno);
    }
}
