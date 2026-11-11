package app;

import java.util.List;
import model.Livro;
import service.BibliotecaService;
import service.LivroNaoEncontradoException;

public class BibliotecaServiceTest {
    public static void main(String[] args) throws Exception {
        BibliotecaService biblioteca = new BibliotecaService();
        biblioteca.cadastrar(new Livro("001", "Dom Casmurro", "Machado de Assis"));
        biblioteca.cadastrar(new Livro("002", "O Cortiço", "Aluísio Azevedo"));
        biblioteca.cadastrar(new Livro("003", "Iracema", "José de Alencar"));

        verificar(biblioteca.listar().size() == 3, "A listagem deve conter os três livros.");
        verificar("Dom Casmurro".equals(biblioteca.buscarPorCodigo("001").getTitulo()),
                "A busca deve localizar o livro pelo código.");

        biblioteca.emprestar("001");
        verificar(biblioteca.buscarPorCodigo("001").isEmprestado(),
                "O livro deve ficar emprestado após a operação.");
        verificarFalha(IllegalStateException.class, () -> biblioteca.emprestar("001"));

        biblioteca.devolver("001");
        verificar(!biblioteca.buscarPorCodigo("001").isEmprestado(),
                "O livro deve ficar disponível após a devolução.");
        verificarFalha(LivroNaoEncontradoException.class,
                () -> biblioteca.buscarPorCodigo("999"));

        List<Livro> copia = biblioteca.listar();
        copia.clear();
        verificar(biblioteca.listar().size() == 3, "Alterar a listagem não deve alterar o catálogo.");

        System.out.println("Todos os testes passaram.");
    }

    private static void verificar(boolean condicao, String mensagem) {
        if (!condicao) {
            throw new AssertionError(mensagem);
        }
    }

    private static void verificarFalha(
            Class<? extends Exception> tipoEsperado, AcaoDeTeste acao) throws Exception {
        try {
            acao.executar();
        } catch (Exception e) {
            if (tipoEsperado.isInstance(e)) {
                return;
            }
            throw new AssertionError(
                    "Esperava " + tipoEsperado.getSimpleName() + ", mas recebeu "
                            + e.getClass().getSimpleName() + ".", e);
        }
        throw new AssertionError("Esperava a exceção " + tipoEsperado.getSimpleName() + ".");
    }

    @FunctionalInterface
    private interface AcaoDeTeste {
        void executar() throws Exception;
    }
}
