
//abstrata
    public  class Paciente extends Pessoa {
        private String contato;
        public Paciente(String nome, int idade, String cpf, String contato) {
            super(nome, idade, cpf);
            this.contato = contato;
        }
//To String
        @Override
        public String toString() {
            return super.toString() + " Paciente : " + getNome() + " Idade: " +getIdade() + " Contato: " + contato + "CPF: " + getCpf() ;
        }

        public String getContato() {
            return contato;
        }

        public void setContato(String contato) {
            this.contato = contato;
        }
        
        
    }





