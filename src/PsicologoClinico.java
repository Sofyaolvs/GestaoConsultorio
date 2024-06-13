
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import Excecoes.DataIndisponivelException;
import Excecoes.NaoCadastradoException;

public class PsicologoClinico extends Psicologo{

    public PsicologoClinico(String nome, int idade, String cpf, String cargo, String registro,
            ArrayList<Consulta> consultas) {
        super(nome, idade, cpf, cargo, registro, consultas);
        
    }

    @Override
    public void desmarcarConsulta(LocalDate data, LocalTime horario, Paciente paciente) throws NaoCadastradoException{
        super.desmarcarConsulta(data, horario, paciente);
    }

    @Override
    public ArrayList<Consulta> listarConsultas() {
        return super.listarConsultas();
    }

    @Override
    public void marcarConsultas(LocalDate data, LocalTime horario, Paciente paciente, String observacoes) throws DataIndisponivelException{
        super.marcarConsultas(data, horario, paciente, observacoes);
    }

    

    //To String
    @Override
    public String toString() {
        return "PsicologoClinico [nome=" + getNome() + ", consultas=" + consultas + "registro=" + registro + "]";
    }

    @Override
    public void salvarConsultas(String arquivoPsicologoClin) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoPsicologoClin))) {//cria um obj tipo BufferedWriter 
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
