import java.io.*;
import java.time.LocalDateTime;

public class ArquivoLog {
    private String nomeArquivo;

    public ArquivoLog(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public void escreverLog(String mensagem) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo, true))) {
            LocalDateTime timestamp = LocalDateTime.now();
            writer.write(timestamp + ": " + mensagem);
            writer.newLine();
            System.out.println("Log registrado com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo de log: " + e.getMessage());
        }
    }

    public void lerLog() {
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            System.out.println("Conteúdo do arquivo de log:");
            while ((linha = reader.readLine()) != null) {
                System.out.println(linha);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo de log: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "ArquivoLog [nomeArquivo=" + nomeArquivo + "]";
    }
    
}

