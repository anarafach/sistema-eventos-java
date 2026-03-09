import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Main {

    static ArrayList<Evento> eventos = new ArrayList<>();
    static ArrayList<Evento> meusEventos = new ArrayList<>();
    static Usuario usuario;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        carregarEventos();

        cadastrarUsuario(sc);

        int opcao;

        do {

            System.out.println("\n===== SISTEMA DE EVENTOS =====");
            System.out.println("1 - Cadastrar evento");
            System.out.println("2 - Listar eventos");
            System.out.println("3 - Participar de evento");
            System.out.println("4 - Ver meus eventos");
            System.out.println("5 - Cancelar participação");
            System.out.println("6 - Mostrar eventos que já aconteceram");
            System.out.println("7 - Sair");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {

                case 1:
                    cadastrarEvento(sc);
                    salvarEventos();
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

                case 6:
                    mostrarEventosPassados();
                    break;

            }

        } while (opcao != 7);

        System.out.println("Sistema encerrado.");
    }

    public static void cadastrarUsuario(Scanner sc) {

        System.out.println("=== Cadastro de Usuário ===");

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Idade: ");
        int idade = sc.nextInt();
        sc.nextLine();

        usuario = new Usuario(nome, email, idade);

        System.out.println("Usuário cadastrado com sucesso!");
    }

    public static void cadastrarEvento(Scanner sc) {

        System.out.println("Nome do evento:");
        String nome = sc.nextLine();

        System.out.println("Endereço:");
        String endereco = sc.nextLine();

        System.out.println("Categoria:");
        String categoria = sc.nextLine();

        System.out.println("Horário (ex: 18):");
        String horario = sc.nextLine();

        System.out.println("Descrição:");
        String descricao = sc.nextLine();

        Evento evento = new Evento(nome, endereco, categoria, horario, descricao);

        eventos.add(evento);

        System.out.println("Evento cadastrado!");
    }

    public static void listarEventos() {

        if (eventos.size() == 0) {
            System.out.println("Nenhum evento cadastrado.");
            return;
        }

        for (int i = 0; i < eventos.size(); i++) {
            System.out.println("\nEvento " + (i + 1));
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

    public static void mostrarEventosPassados() {

        int horaAtual = java.time.LocalTime.now().getHour();

        for (Evento e : eventos) {

            try {

                int horaEvento = Integer.parseInt(e.horario);

                if (horaEvento < horaAtual) {
                    System.out.println("\nEvento que já ocorreu:");
                    e.mostrarEvento();
                }

            } catch (Exception ex) {

            }
        }
    }

    public static void salvarEventos() {

        try {

            BufferedWriter writer = new BufferedWriter(new FileWriter("events.data"));

            for (Evento e : eventos) {

                writer.write(e.nome + ";" + e.endereco + ";" + e.categoria + ";" + e.horario + ";" + e.descricao);
                writer.newLine();

            }

            writer.close();

        } catch (Exception e) {

            System.out.println("Erro ao salvar eventos.");

        }
    }

    public static void carregarEventos() {

        try {

            BufferedReader reader = new BufferedReader(new FileReader("events.data"));

            String linha;

            while ((linha = reader.readLine()) != null) {

                String[] dados = linha.split(";");

                Evento evento = new Evento(
                        dados[0],
                        dados[1],
                        dados[2],
                        dados[3],
                        dados[4]);

                eventos.add(evento);

            }

            reader.close();

        } catch (Exception e) {

        }
    }
}
