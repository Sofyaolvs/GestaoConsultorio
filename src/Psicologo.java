//abstrata

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import Excecoes.DataIndisponivelException;
import Excecoes.NaoCadastradoException;

public abstract class Psicologo extends Funcionario implements GestaoConsultas {
protected String registro;
protected ArrayList<Consulta> consultas; //arraylista de consultas



public Psicologo(String nome, int idade, String cpf, String cargo, String registro, ArrayList<Consulta> consultas) {
    super(nome, idade, cpf, cargo);
    this.registro = registro;
    this.consultas = consultas;
    
}

//Metodos implementados das Interfaces que vão ser herdados pelos tipos de psicologos
@Override
public void marcarConsultas(LocalDate data, LocalTime horario, Paciente paciente, String observacoes) throws DataIndisponivelException{
    try {
        boolean consultaDisponivel = true;
        for (int i = 0; i < consultas.size(); i++) {
            if (consultas.get(i).getData().equals(data) && consultas.get(i).getHorario().equals(horario)) {
                consultaDisponivel = false;
                throw new DataIndisponivelException("Data com o Psicólogo não disponível!");
            }
        }
        if (consultaDisponivel) {
            consultas.add(new Consulta(this, paciente, data, horario, observacoes));
        }
    } catch (DataIndisponivelException e) {
        System.err.println(e.getMessage());
    }
}

@Override
public void desmarcarConsulta(LocalDate data, LocalTime horario, Paciente paciente) throws NaoCadastradoException{
    try {
        boolean consultaEncontrada = false;
        for (int i = 0; i < consultas.size(); i++) {
            if (consultas.get(i).getData().equals(data) && consultas.get(i).getHorario().equals(horario) && consultas.get(i).getPaciente().equals(paciente)) {
                consultas.remove(i);
                System.out.println("Consulta Desmarcada com Sucesso!");
                consultaEncontrada = true;
                break;
            }
        }
        if (!consultaEncontrada) {
            throw new NaoCadastradoException("Nenhuma Consulta Foi Encontrada Nesse Horário e Data!");
        }
    } catch (NaoCadastradoException e) {
        System.err.println(e.getMessage());
    }
}

@Override
public ArrayList<Consulta> listarConsultas() {
    return consultas;
}

public abstract void salvarConsultas(String arquivoPsicologo);


//To string
@Override
public String toString() {
    return "Psicologo: registro=" + registro + ", Nome=" + getNome() + ", Cargo=" + getCargo();
}

public String getRegistro() {
    return registro;
}
public void setRegistro(String registro) {
    this.registro = registro;
}

public ArrayList<Consulta> getConsultas() {
    return consultas;
}

public void setConsultas(ArrayList<Consulta> consultas) {
    this.consultas = consultas;
}


}
