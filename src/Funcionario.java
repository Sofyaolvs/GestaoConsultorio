//abstrata
public abstract class Funcionario extends Pessoa {
private String cargo;

public Funcionario(String nome, int idade, String cpf, String cargo) {
    super(nome, idade, cpf);
    this.cargo = cargo;
}

public String getCargo() {
    return cargo;
}
public void setCargo(String cargo) {
    this.cargo = cargo;
}

@Override
public String toString() {
    return "Funcionario [cargo=" + cargo + "]";
}

}
