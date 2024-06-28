import br.edu.up.Aluno;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        List<Aluno> alunos = new ArrayList<>();
        int quantidade = 0;
        int aprovados = 0;
        int reprovados = 0;
        double menorNota = Double.MAX_VALUE;
        double maiorNota = Double.MIN_VALUE;
        double somaNotas = 0.0;

        
        try (BufferedReader br = new BufferedReader(new FileReader("alunos.csv"))) {
            String linha;
            br.readLine();
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                String matricula = dados[0];
                String nome = dados[1];
                double nota = Double.parseDouble(dados[2].replace(",", "."));
                Aluno aluno = new Aluno(matricula, nome, nota);
                alunos.add(aluno);

                quantidade++;
                somaNotas += nota;
                if (nota >= 6.0) {
                    aprovados++;
                } else {
                    reprovados++;
                }
                if (nota < menorNota) {
                    menorNota = nota;
                }
                if (nota > maiorNota) {
                    maiorNota = nota;
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        double media = quantidade > 0 ? somaNotas / quantidade : 0.0;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("resumo.csv"))) {
            bw.write("Quantidade de alunos: " + quantidade);
            bw.newLine();
            bw.write("Aprovados: " + aprovados);
            bw.newLine();
            bw.write("Reprovados: " + reprovados);
            bw.newLine();
            bw.write("Menor nota: " + menorNota);
            bw.newLine();
            bw.write("Maior nota: " + maiorNota);
            bw.newLine();
            bw.write("Média geral: " + media);
        } catch (IOException e) {
            System.err.println("Erro ao escrever o arquivo: " + e.getMessage());
        }
    }
}
