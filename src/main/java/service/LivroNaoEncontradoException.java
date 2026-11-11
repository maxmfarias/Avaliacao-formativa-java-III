package service;

public class LivroNaoEncontradoException extends Exception {
    public LivroNaoEncontradoException(String codigo) {
        super("Nenhum livro foi encontrado com o código: " + codigo);
    }
}
