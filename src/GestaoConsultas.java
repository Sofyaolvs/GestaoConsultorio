import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public interface GestaoConsultas {
public void marcarConsultas(LocalDate data, LocalTime horario, Paciente paciente, String observacoes);
public void desmarcarConsulta(LocalDate data, LocalTime horario, Paciente paciente);
public ArrayList<Consulta> listarConsultas();
}
