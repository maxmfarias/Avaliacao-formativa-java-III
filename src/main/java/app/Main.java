package app;

import java.util.List;
import java.util.Scanner;
import model.Livro;
import service.BibliotecaService;
import service.LivroNaoEncontradoException;

public class Main {
    private final BibliotecaService biblioteca = new BibliotecaService();
    private final Scanner scanner;

    private Main(Scanner scanner) {
        this.scanner = scanner;
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            new Main(scanner).executar();
        }
    }

    private void executar() {
        boolean continuar = true;
        while (continuar) {
            exibirMenu();
            String opcao = scanner.nextLine().trim();
            try {
                switch (opcao) {
                    case "1" -> cadastrarLivro();
                    case "2" -> listarLivros();
                    case "3" -> buscarLivro();
                    case "4" -> emprestarLivro();
                    case "5" -> devolverLivro();
                    case "0" -> continuar = false;
                    default -> System.out.println("Opção inválida. Escolha uma opção do menu.");
                }
            } catch (LivroNaoEncontradoException | IllegalArgumentException | IllegalStateException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
        System.out.println("Sistema encerrado.");
    }

    private void exibirMenu() {
        System.out.println("""

                === Sistema de Gerenciamento de Biblioteca ===
                1. Cadastrar livro
                2. Listar livros
                3. Buscar livro pelo código
                4. Emprestar livro
                5. Devolver livro
                0. Sair
                Escolha uma opção:""");
    }

    private void cadastrarLivro() {
        System.out.print("Código: ");
        String codigo = scanner.nextLine();
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        biblioteca.cadastrar(new Livro(codigo, titulo, autor));
        System.out.println("Livro cadastrado com sucesso.");
    }

    private void listarLivros() {
        List<Livro> livros = biblioteca.listar();
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
            return;
        }
        livros.forEach(System.out::println);
    }

    private void buscarLivro() throws LivroNaoEncontradoException {
        System.out.print("Código do livro: ");
        System.out.println(biblioteca.buscarPorCodigo(scanner.nextLine()));
    }

    private void emprestarLivro() throws LivroNaoEncontradoException {
        System.out.print("Código do livro: ");
        biblioteca.emprestar(scanner.nextLine());
        System.out.println("Livro emprestado com sucesso.");
    }

    private void devolverLivro() throws LivroNaoEncontradoException {
        System.out.print("Código do livro: ");
        biblioteca.devolver(scanner.nextLine());
        System.out.println("Livro devolvido com sucesso.");
    }
}
