public class Evento {

    String nome;
    String endereco;
    String categoria;
    String horario;
    String descricao;

    public Evento(String nome, String endereco, String categoria, String horario, String descricao) {
        this.nome = nome;
        this.endereco = endereco;
        this.categoria = categoria;
        this.horario = horario;
        this.descricao = descricao;
    }

    public void mostrarEvento() {
        System.out.println("Nome: " + nome);
        System.out.println("Endereço: " + endereco);
        System.out.println("Categoria: " + categoria);
        System.out.println("Horário: " + horario);
        System.out.println("Descrição: " + descricao);
        System.out.println("---------------------------");
    }
}
