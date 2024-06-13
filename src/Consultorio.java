import java.io.*;
import java.util.ArrayList;

import Excecoes.JaCadastradoException;
import Excecoes.NaoCadastradoException;

public class Consultorio implements GestaoPacientes, GestaoPsicologos {
    private ArrayList<Psicologo> psicologos;
    private ArrayList<Paciente> pacientes;

    public Consultorio() {
        this.psicologos = new ArrayList<>();
        this.pacientes = new ArrayList<>();
    }

    @Override
    public void adicionarPaciente(String nome, int idade, String cpf, String contato) throws JaCadastradoException{
        try {
            for (int i = 0; i < pacientes.size(); i++) {
                if (pacientes.get(i).getCpf().equals(cpf)) {
                    throw new JaCadastradoException("Paciente já está cadastrado no sistema!");
                }
            }
            pacientes.add(new Paciente(nome, idade, cpf, contato));
        } catch (JaCadastradoException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public ArrayList<Paciente> listarPacientes() {
        return pacientes;
    }

    @Override
    public void removerPaciente(String cpf) throws NaoCadastradoException{
        try {
            for (int i = 0; i < pacientes.size(); i++) {
                if (pacientes.get(i).getCpf().equals(cpf)) {
                    pacientes.remove(i);
                    System.out.println("Paciente removido com sucesso!");
                    return;
                }
            }
            throw new NaoCadastradoException("Paciente não encontrado!");
        } catch (NaoCadastradoException e) {
            System.err.println(e.getMessage());
        }
    }

    public void adicionarPsicologo(Psicologo psicologo) throws JaCadastradoException{
        try {
            for (int i = 0; i < psicologos.size(); i++) {
                if (psicologos.get(i).getRegistro().equals(psicologo.getRegistro())) {
                    throw new JaCadastradoException("Psicólogo já está cadastrado no sistema!");
                }
            }
            psicologos.add(psicologo);
        } catch (JaCadastradoException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public ArrayList<Psicologo> listarPsicologos() {
        return psicologos;
    }

    @Override
    public void removerPsicologo(String registro) throws NaoCadastradoException{
        try {
            for (int i = 0; i < psicologos.size(); i++) {
                if (psicologos.get(i).getRegistro().equals(registro)) {
                    psicologos.remove(i);
                    System.out.println("Psicólogo removido com sucesso!");
                    return;
                }
            }
            throw new NaoCadastradoException("Psicólogo não encontrado!");
        } catch (NaoCadastradoException e) {
            System.err.println(e.getMessage());
        }
    }


    
    

    public void salvarPsicologos(String arquivoPsicologos) throws IOException{
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoPsicologos))) {
            for (Psicologo psicologo : psicologos) {
                writer.write(psicologo.toString());
                writer.newLine();
            }
            System.out.println("Dados salvos com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }
    
    public void salvarPacientes(String arquivoPacientes) throws IOException{
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoPacientes))) {
            for (Paciente paciente : pacientes) {
                writer.write(paciente.toString());
                writer.newLine();
            }
            System.out.println("Dados do paciente salvos com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados do paciente: " + e.getMessage());
        }
    }

    
@Override
    public String toString() {
        return "Consultorio [Pacientes" + listarPacientes() + ", Psicólogos=" + listarPsicologos() + "]";
    }
    }
    
    


