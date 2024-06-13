import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuConsultorio {
    public static void main(String[] args) throws IOException, FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        Consultorio consultorio = new Consultorio();
        String path = "ArquivoGeral.txt";
        File file = new File(path);
        FileWriter fw = new FileWriter(file);
        try{
            Scanner sc = new Scanner(file);
        }catch(FileNotFoundException e){
            file.createNewFile();
        }
        int escolha;
        do {
            
            System.out.println("Menu:");
            System.out.println("1. Adicionar Psicólogo");
            System.out.println("2. Remover Psicólogo");
            System.out.println("3. Listar Psicólogos");
            System.out.println("4. Adicionar Paciente");
            System.out.println("5. Remover Paciente");
            System.out.println("6. Listar Pacientes");
            System.out.println("7. Marcar Consulta");
            System.out.println("8. Desmarcar Consulta");
            System.out.println("9. Listar Consultas");
            System.out.println("11.Salvar dados Log");
            System.out.println("12. Salvar dados Psicólogo");
            System.out.println("13. Salvar dados Paciente");
            System.out.println("14. Salvar dados Consulta");
            System.out.println("10. Sair");

            System.out.print("Escolha uma opção: ");
    
            escolha = scanner.nextInt();
            scanner.nextLine(); 
    
            switch (escolha) {
                case 1:
                    adicionarPsicologo(scanner, consultorio);
                    break;
                case 2:
                    removerPsicologo(scanner, consultorio);
                    break;
                case 3:
                    listarPsicologos(consultorio);
                    break;
                case 4:
                    adicionarPaciente(scanner, consultorio);
                    break;
                case 5:
                    removerPaciente(scanner, consultorio);
                    break;
                case 6:
                    listarPacientes(consultorio);
                    break;
                    case 7:
                    marcarConsulta(scanner, listarArrayPsicologos(scanner, consultorio), listarArrayPacientes(scanner, consultorio), escolha);
                    break;
                    case 8:
                    desmarcarConsulta(scanner, listarArrayPsicologos(scanner, consultorio), listarArrayPacientes(scanner, consultorio), escolha);
                    break;
                    case 9:
                    listarConsultas(listarArrayPsicologos(scanner, consultorio));
                    break;
                    case 10:
                    System.out.println("Saindo...");
                    break;
                    //log
                    case 11:
                    ArquivoLog arquivoLog = new ArquivoLog("log.txt");
                    // Escrever no arquivo de log
                    arquivoLog.escreverLog("Entrada de dados: Cadastro realizado com sucesso.");
                    arquivoLog.escreverLog("Saída de dados: Consulta realizada.");
                    arquivoLog.lerLog();// Ler o arquivo de log
                    break;
                    case 12:
                    System.out.print("Salvar em qual arquivo?:");
                    System.out.println("arquivoGeralPsi");
                    String arquivoGeralPsi = scanner.nextLine();
                    consultorio.salvarPsicologos(arquivoGeralPsi);
                    break;
                    case 13:
                    System.out.print("Salvar em qual arquivo? \n(arquivoGeralPac): ");
                    String arquivoGeralPac = scanner.nextLine();
                    consultorio.salvarPacientes(arquivoGeralPac);
                    break;
                    case 14:
                    int valor;
                    System.out.println("Salvar em qual arquivo?");
                    System.out.println("1. arquivo do Psicologo Clínico");
                    System.out.println("2. arquivo do Psicologo Infantil");
                    System.out.println("3. arquivo do Psicologo Infanto-Juvenil");
                    valor = scanner.nextInt();
                    scanner.nextLine();
                    switch (valor) {
                        case 1:
                        System.out.print("Nome do arquivo:(arquivoPsicoClin) ");
                        String arquivoPsicoClin = scanner.nextLine() ;
                        listarArrayPsicologos(scanner, consultorio).salvarConsultas(arquivoPsicoClin);
                        System.out.println("Dados do psicólogo clínico salvos com sucesso.");
                        break;
                        case 2:
                        System.out.print("Nome do arquivo:(arquivoPsicInf) ");
                        String arquivoPsiclInf = scanner.nextLine();
                        listarArrayPsicologos(scanner, consultorio).salvarConsultas(arquivoPsiclInf);
                        System.out.println("Dados do psicólogo infantil salvos com sucesso.");
                        break;
                        case 3:
                        System.out.print("Nome do arquivo:(arquivoPsiInfJuv) ");
                        String arquivoPsiInfJuv =scanner.nextLine();
                        listarArrayPsicologos(scanner, consultorio).salvarConsultas(arquivoPsiInfJuv);
                        System.out.println("Dados do psicólogo infanto-juvenil salvos com sucesso.");
                        break;
                    default:
                    System.out.println("Opção inválida.");
                    break;
                    }

                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        } while (escolha != 10);
          
        scanner.close();
    }
    
    public static void adicionarPsicologo(Scanner scanner, Consultorio consultorio) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Idade: ");
        int idade = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Cargo: ");
        String cargo = scanner.nextLine();
        System.out.print("Registro: ");
        String registro = scanner.nextLine();
        System.out.print("Tipo (1 - Clínico, 2 - Infantil, 3 - Infanto-Juvenil): ");
        int tipoPsicologo = scanner.nextInt();
        scanner.nextLine(); 
        
        Psicologo psicologo = null;
        switch (tipoPsicologo) {
            case 1:
                psicologo = new PsicologoClinico(nome, idade, cpf, cargo, registro, new ArrayList<>());
                consultorio.adicionarPsicologo(psicologo);
                System.out.println("Psicólogo Clínico adicionado com sucesso!");
                break;
            case 2:
                System.out.print("Idade Máxima de Pacientes: ");
                int idadeMaxPacientes = scanner.nextInt();
                scanner.nextLine(); 
                psicologo = new PsicologoInfantil(nome, idade, cpf, cargo, registro, new ArrayList<>(), idadeMaxPacientes);    
                consultorio.adicionarPsicologo(psicologo);
                System.out.println("Psicólogo Infantil adicionado com sucesso!");
                break;
            case 3:
                System.out.print("Idade Mínima de Pacientes: ");
                int idadeMinPacientes = scanner.nextInt();
                scanner.nextLine();
                psicologo = new PsicologoInfantoJuvenil(nome, idade, cpf, cargo, registro, new ArrayList<>(), idadeMinPacientes);
                consultorio.adicionarPsicologo(psicologo);
                System.out.println("Psicólogo Infanto-Juvenil adicionado com sucesso!");
                break;
            default:
                System.out.println("Tipo de psicólogo inválido.");
                break;
        }
    
    }


    public static void removerPsicologo(Scanner scanner, Consultorio consultorio) {
        System.out.print("Registro do psicólogo que deseja remover: ");
        String registro = scanner.nextLine();
        consultorio.removerPsicologo(registro);
    }

    public static void listarPsicologos(Consultorio consultorio) {
        System.out.println("Lista de Psicólogos:");
        for (Psicologo psicologo : consultorio.listarPsicologos()) { 
            System.out.println(psicologo);
        }
    }

    public static void adicionarPaciente(Scanner scanner, Consultorio consultorio) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Idade: ");
        int idade = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Contato: ");
        String contato = scanner.nextLine();

        consultorio.adicionarPaciente(nome, idade, cpf, contato);
        System.out.println("Paciente adicionado com sucesso!");
    }

    public static void removerPaciente(Scanner scanner, Consultorio consultorio) {
        System.out.print("CPF do paciente que deseja remover: ");
        String cpf = scanner.nextLine();
        consultorio.removerPaciente(cpf);
    }

    public static void listarPacientes(Consultorio consultorio) {
        System.out.println("Lista de Pacientes:");
        for (Paciente paciente : consultorio.listarPacientes()) {
            System.out.println(paciente);
        }
    }


    public static void marcarConsulta(Scanner scanner, Psicologo psicologo, Paciente paciente, int tipoPsicologo) throws DateTimeParseException{
        LocalDate dataConsulta = null;
        while (dataConsulta == null) {
            try {
                System.out.println("Data que deseja marcar Consulta: ");
                scanner.nextLine();
                String dataConsultaInput = scanner.nextLine();
                if (dataConsultaInput.isEmpty()) {
                    System.out.println("Data não pode estar vazia. Tente novamente.");
                    continue;
                }
                dataConsulta = LocalDate.parse(dataConsultaInput, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            } catch (DateTimeParseException e) {
                System.out.println("Formato de data inválido. Tente novamente.");
            }
        }

        LocalTime horarioConsulta=null;
        while (horarioConsulta == null) {
            try {
                System.out.println("Horário desejado para consulta: ");
                String horarioConsultaInput = scanner.nextLine();
                if (horarioConsultaInput.isEmpty()) { //se o horario estiver vazio informa
                    System.out.println("Horário não pode ficar vazio. Tente de novo");
                    continue; //solicita dnv
                }
                horarioConsulta = LocalTime.parse(horarioConsultaInput, DateTimeFormatter.ofPattern("HH:mm"));
            } catch (DateTimeParseException e) {
                System.out.println("Formato de horário inválido. Tente novamente.");
            }
        }

        System.out.println("Observações da Consulta: ");
        String observacoesConsulta = scanner.nextLine();

        int tipoPsicologoInput = -1; //começa com -1 pra garantir que o loop seja executado pq ta fora do intervalo de 1 e 3
        while (tipoPsicologoInput < 1 || tipoPsicologoInput > 3) {
            try {
                System.out.print("Tipo (1 - Clínico, 2 - Infanto-Juvenil, 3 - Infantil): ");
                tipoPsicologoInput = scanner.nextInt();
                scanner.nextLine(); // limpa o buffer
            } catch (Exception e) {
                System.out.println("Entrada inválida. Digite um número entre 1 e 3.");
                scanner.nextLine(); 
            }
        }

        tipoPsicologo = tipoPsicologoInput;

        switch (tipoPsicologo) {
            case 1:
                if (psicologo instanceof PsicologoClinico) { //se for instancia 
                    ((PsicologoClinico) psicologo).marcarConsultas(dataConsulta, horarioConsulta, paciente, observacoesConsulta);
                    System.out.println("Consulta Marcada com psicólogo clínico");
                } else {
                    System.out.println("Tipo de psicólogo incorreto");
                }
                break;
            case 2:
                if (psicologo instanceof PsicologoInfantoJuvenil) {
                    ((PsicologoInfantoJuvenil) psicologo).marcarConsultas(dataConsulta, horarioConsulta, paciente, observacoesConsulta);
                    System.out.println("Consulta Marcada com psicólogo Infanto-Juvenil");
                } else {
                    System.out.println("Tipo de psicólogo incorreto fornecido.");
                }
                break;
            case 3:
                if (psicologo instanceof PsicologoInfantil) {
                    ((PsicologoInfantil) psicologo).marcarConsultas(dataConsulta, horarioConsulta, paciente, observacoesConsulta);
                    System.out.println("Consulta Marcada com psicólogo Infantil");
                } else {
                    System.out.println("Tipo de psicólogo incorreto");
                }
                break;
            default:
                System.out.println("Tipo de psicólogo  é inválido.");
                break;
        }
    }


public static void desmarcarConsulta(Scanner scanner, Psicologo psicologo, Paciente paciente, int tipoPsicologo) throws DateTimeParseException{
    try {
        System.out.println("Data da consulta que deseja desmarcar: ");
        LocalDate dataConsulta = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        System.out.println("Horário da consulta que deseja desmarcar: ");
        LocalTime horarioConsulta = LocalTime.parse(scanner.nextLine());

        switch (tipoPsicologo) {
            case 1:
                ((PsicologoClinico)psicologo).desmarcarConsulta(dataConsulta, horarioConsulta, paciente);
                break;
            case 2:
                ((PsicologoInfantoJuvenil) psicologo).desmarcarConsulta(dataConsulta, horarioConsulta, paciente);
                break;
            case 3:
                ((PsicologoInfantil) psicologo).desmarcarConsulta(dataConsulta, horarioConsulta, paciente);
                break;
            default:
                System.out.println("Nenhuma Consulta encontrada");
                break;
        }
    } catch (DateTimeParseException e) { //excessão caso o formato da data e hora esteja errado
        System.out.println("Formato de data ou hora inválido.");
    }
}

public static void listarConsultas(Psicologo psicologo) {
    System.out.println("Lista de consultas:");
    for (Consulta consulta : psicologo.listarConsultas()) {
        System.out.println(consulta);
    }

}

public static Psicologo listarArrayPsicologos(Scanner scanner, Consultorio consultorio) {
    System.out.println("Escolha o psicólogo"); //retorna o array/to String de psicologos
    ArrayList<Psicologo> psicologos = consultorio.listarPsicologos();
    int i = 0;
    for (Psicologo p : psicologos) {
        System.out.println(i + ")" + p);
        i++;
    }
   
    
    int indicePsicologos = scanner.nextInt();

    return psicologos.get(indicePsicologos);
}

public static Paciente listarArrayPacientes(Scanner scanner, Consultorio consultorio) {
    System.out.println("Escolha o paciente");//retorna o array/to String de pacientes
    ArrayList<Paciente> pacientes = consultorio.listarPacientes();
    int i = 0;
    for (Paciente p : pacientes) {
        System.out.println(i + ")" + p);
        i++;
    }
 
    
    int indicePacientes = scanner.nextInt();
    
    return pacientes.get(indicePacientes);
}
    }


