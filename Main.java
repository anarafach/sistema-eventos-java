import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<Evento> eventos = new ArrayList<>();
    static ArrayList<Evento> meusEventos = new ArrayList<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int opcao;

        do {

            System.out.println("===== SISTEMA DE EVENTOS =====");
            System.out.println("1 - Cadastrar evento");
            System.out.println("2 - Listar eventos");
            System.out.println("3 - Participar de evento");
            System.out.println("4 - Ver meus eventos");
            System.out.println("5 - Cancelar participação");
            System.out.println("6 - Sair");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {

                case 1:
                    cadastrarEvento(sc);
                    break;

                case 2:
                    listarEventos();
                    break;

                case 3:
                    participarEvento(sc);
                    break;

                case 4:
                    verMeusEventos();
                    break;

                case 5:
                    cancelarEvento(sc);
                    break;
            }

        } while (opcao != 6);

        System.out.println("Sistema encerrado.");
    }

    public static void cadastrarEvento(Scanner sc) {

        System.out.println("Nome do evento:");
        String nome = sc.nextLine();

        System.out.println("Endereço:");
        String endereco = sc.nextLine();

        System.out.println("Categoria (Festa, Show, Esporte):");
        String categoria = sc.nextLine();

        System.out.println("Horário:");
        String horario = sc.nextLine();

        System.out.println("Descrição:");
        String descricao = sc.nextLine();

        Evento evento = new Evento(nome, endereco, categoria, horario, descricao);
        eventos.add(evento);

        System.out.println("Evento cadastrado com sucesso!");
    }

    public static void listarEventos() {

        if (eventos.size() == 0) {
            System.out.println("Nenhum evento cadastrado.");
            return;
        }

        for (int i = 0; i < eventos.size(); i++) {
            System.out.println("Evento " + (i + 1));
            eventos.get(i).mostrarEvento();
        }
    }

    public static void participarEvento(Scanner sc) {

        listarEventos();

        System.out.println("Escolha o número do evento:");
        int escolha = sc.nextInt();

        Evento evento = eventos.get(escolha - 1);
        meusEventos.add(evento);

        System.out.println("Participação confirmada!");
    }

    public static void verMeusEventos() {

        if (meusEventos.size() == 0) {
            System.out.println("Você não confirmou presença em nenhum evento.");
            return;
        }

        for (Evento e : meusEventos) {
            e.mostrarEvento();
        }
    }

    public static void cancelarEvento(Scanner sc) {

        verMeusEventos();

        System.out.println("Escolha o número do evento para cancelar:");
        int escolha = sc.nextInt();

        meusEventos.remove(escolha - 1);

        System.out.println("Participação cancelada.");
    }
}
