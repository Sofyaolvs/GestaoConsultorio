import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import Excecoes.DataIndisponivelException;
import Excecoes.IdadeMinimaException;
import Excecoes.NaoCadastradoException;

public class PsicologoInfantoJuvenil extends Psicologo {
    private int idadeMinPacientes;
    public PsicologoInfantoJuvenil(String nome, int idade, String cpf, String cargo, String registro,
            ArrayList<Consulta> consultas, int idadeMinPacientes) {
        super(nome, idade, cpf, cargo, registro, consultas);
        this.idadeMinPacientes = idadeMinPacientes;
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
    public void marcarConsultas(LocalDate data, LocalTime horario, Paciente paciente, String observacoes) throws IdadeMinimaException, DataIndisponivelException{
        try {
            
            if (paciente.getIdade() < idadeMinPacientes) {
                throw new IdadeMinimaException("A idade do paciente é menor que a idade mínima atendida pelo Psicólogo Infanto-Juvenil!");
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
        } catch (IdadeMinimaException | DataIndisponivelException e) {
            System.err.println(e.getMessage());
        }
    }
    
//getters setters
    public int getIdadeMinPacientes() {
        return idadeMinPacientes;
    }

    public void setIdadeMinPacientes(int idadeMinPacientes) {
        this.idadeMinPacientes = idadeMinPacientes;
    }


//To String
    @Override
    public String toString() {
        return "PsicologoInfantoJuvenil [nome=" + getNome() + ", idadeMinPacientes=" + idadeMinPacientes + ", registro="
                + registro + ", consultas=" + consultas + "]";
    }
    
  public void salvarDados(String nomeArquivo) throws IOException{
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            outputStream.writeObject(this);
            System.out.println("Dados do Psicólogo Infanto-Juvenil salvos com sucesso no arquivo " + nomeArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados do Psicólogo Infanto-Juvenil no arquivo " + nomeArquivo);
            e.printStackTrace();
        }
    }

    @Override
    public void salvarConsultas(String arquivoPsicologoInfJuv){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoPsicologoInfJuv))) {//cria um obj tipo BufferedWriter 
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
