import java.util.ArrayList;

public interface GestaoPacientes {
public void adicionarPaciente(String nome, int idade, String cpf, String contato);
public void removerPaciente(String cpf);
public ArrayList<Paciente> listarPacientes();
}
