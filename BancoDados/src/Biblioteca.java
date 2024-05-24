import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Biblioteca {

    private static final String ARQUIVO_LIVROS = "livros.txt";
    private static List<String> livros = new ArrayList<String>();

        public static void main(String[] args) {
          carregarLivrosDoArquivo();
          menuPrincipal();
      }

    private static void menuPrincipal() {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\nMenu de Livros:");
            System.out.println("1. Listar livros");
            System.out.println("2. Adicionar livro");
            System.out.println("3. Editar livro");
            System.out.println("4. Excluir livro");
            System.out.println("5. Sair");
            System.out.print("Digite a opção desejada: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    listarLivros();
                    break;
                case 2:
                    adicionarLivro(scanner);
                    break;
                case 3:
                    editarLivro(scanner);
                    break;
                case 4:
                    excluirLivro(scanner);
                    break;
                case 5:
                    System.out.println("Saindo do programa...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 5);

        scanner.close();
    }

    private static void listarLivros() {
        if (livros.isEmpty()) {
            System.out.println("Não há livros cadastrados.");
            return;
        }

        System.out.println("\nLista de Livros:");
        for (int i = 0; i < livros.size(); i++) {
            System.out.println((i + 1) + ". " + livros.get(i));
        }
    }

    private static void adicionarLivro(Scanner scanner) {
        System.out.print("Digite o nome do livro: ");
        String nomeLivro = scanner.nextLine();

        livros.add(nomeLivro);
        salvarLivrosNoArquivo();
        System.out.println("Livro adicionado com sucesso!");
    }

    private static void editarLivro(Scanner scanner) {
        if (livros.isEmpty()) {
            System.out.println("Não há livros cadastrados.");
            return;
        }

        listarLivros();

        System.out.print("Digite o número do livro a ser editado: ");
        int numeroLivro = scanner.nextInt();
        scanner.nextLine(); 

        if (numeroLivro <= 0 || numeroLivro > livros.size()) {
            System.out.println("Número de livro inválido!");
            return;
        }

        System.out.print("Digite o novo nome do livro: ");
        String novoNomeLivro = scanner.nextLine();
        livros.set(numeroLivro - 1, novoNomeLivro);

        salvarLivrosNoArquivo();
        System.out.println("Livro editado com sucesso!");
    }

    private static void excluirLivro(Scanner scanner) {
        if (livros.isEmpty()) {
            System.out.println("Não há livros cadastrados.");
            return;
        }

        listarLivros();

        System.out.print("Digite o número do livro a ser excluído: ");
        int numeroLivro = scanner.nextInt();
        scanner.nextLine(); 

        if (numeroLivro <= 0 || numeroLivro > livros.size()) {
            System.out.println("Número de livro inválido!");
            return;
        }

        livros.remove(numeroLivro - 1);
        salvarLivrosNoArquivo();
        System.out.println("Livro excluído com sucesso!");
    }

    private static void carregarLivrosDoArquivo() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_LIVROS))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                livros.add(linha);
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar livros: " + e.getMessage());
        }
    }

    private static void salvarLivrosNoArquivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_LIVROS))) {
            for (String livro : livros) {
                writer.append(livro);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar livros: " + e.getMessage());
        }
    }
    
}
