
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import Excecoes.DataIndisponivelException;
import Excecoes.IdadeMaximaException;
import Excecoes.NaoCadastradoException;

public class PsicologoInfantil extends Psicologo   {
private int idadeMaxPacientes;



    public PsicologoInfantil(String nome, int idade, String cpf, String cargo, String registro,
        ArrayList<Consulta> consultas, int idadeMaxPacientes) {
    super(nome, idade, cpf, cargo, registro, consultas);
    this.idadeMaxPacientes = idadeMaxPacientes;
}

    public int getIdadeMaxPacientes() {
    return idadeMaxPacientes;
}

public void setIdadeMaxPacientes(int idadeMaxPacientes) {
    this.idadeMaxPacientes = idadeMaxPacientes;
}


@Override
public void marcarConsultas(LocalDate data, LocalTime horario, Paciente paciente, String observacoes) throws IdadeMaximaException, DataIndisponivelException{
    try {
        
        if (paciente.getIdade() > getIdadeMaxPacientes()) {
            throw new IdadeMaximaException("A idade do paciente é maior que a idade máxima atendida por essa especialidade!");
        }
        
        boolean consultaDisponivel = true;
        for (int i = 0; i < consultas.size(); i++) {
            if (consultas.get(i).getData().equals(data) && consultas.get(i).getHorario().equals(horario)) {
                consultaDisponivel = false;
                throw new DataIndisponivelException("Data com o Psicólogo Infantil não disponível!");
            }
        }
        
        if (consultaDisponivel) {
            consultas.add(new Consulta(this, paciente, data, horario, observacoes));
        }
    } catch (IdadeMaximaException | DataIndisponivelException e) {
        System.err.println(e.getMessage());
    }
    
}

@Override
public void desmarcarConsulta(LocalDate data, LocalTime horario, Paciente paciente) throws NaoCadastradoException {
    super.desmarcarConsulta(data, horario, paciente);
}

@Override
public ArrayList<Consulta> listarConsultas() {
    return consultas;
}

//To String
@Override
public String toString() {
    return "PsicologoInfantil [nome=" + getNome() + ", idade=" + getNome() + ", cpf=" + getCpf() + ", idadeMaxPacientes="
            + idadeMaxPacientes + ", registro=" + registro + ", consultas=" + consultas + "]";
}

@Override
public void salvarConsultas(String arquivoPsicologoInfant){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoPsicologoInfant))) { //cria um obj tipo BufferedWriter 
            //q é usado pra escrever no arquivo e abre um novo arquivo com o FileWriter
            for (Consulta consulta : consultas) {
                writer.write(consulta.getPaciente().toString());
                writer.newLine();
            }
            System.out.println("Dados da consulta foram salvos.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados d: " + e.getMessage());
        }
    }

}





