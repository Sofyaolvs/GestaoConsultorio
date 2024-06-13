import java.time.LocalDate;
import java.time.LocalTime;

public class Consulta {
private Psicologo psicologo;
private Paciente paciente;
private LocalDate data;
private  LocalTime horario;
private String observacoes;
public Consulta(Psicologo psicologo, Paciente paciente, LocalDate data, LocalTime horario, String observacoes) {
    this.psicologo = psicologo;
    this.paciente = paciente;
    this.data = data;
    this.horario = horario;
    this.observacoes = observacoes;
}


//get e set 
public Psicologo getPsicologo() {
    return psicologo;
}
public void setPsicologo(Psicologo psicologo) {
    this.psicologo = psicologo;
}
public Paciente getPaciente() {
    return paciente;
}
public void setPaciente(Paciente paciente) {
    this.paciente = paciente;
}

public String getObservacoes() {
    return observacoes;
}
public void setObservacoes(String observacoes) {
    this.observacoes = observacoes;
}

public LocalDate getData() {
    return data;
}

public void setData(LocalDate data) {
    this.data = data;
}

public LocalTime getHorario() {
    return horario;
}

public void setHorario(LocalTime horario) {
    this.horario = horario;
}



// to string 
@Override
public String toString() {
    return "Psicologo: " + getPsicologo().getNome() + " Paciente: " + getPaciente().getNome() + " Data: "
            + getData() + " Horario: " + getHorario() + " Observações: " + getObservacoes();
}









}
