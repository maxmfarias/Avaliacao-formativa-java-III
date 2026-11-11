package service;

import java.util.ArrayList;
import java.util.List;
import model.Livro;

public class BibliotecaService {
    private final ArrayList<Livro> livros = new ArrayList<>();

    public void cadastrar(Livro livro) {
        if (livro == null) {
            throw new IllegalArgumentException("O livro não pode ser nulo.");
        }
        for (Livro cadastrado : livros) {
            if (cadastrado.getCodigo().equalsIgnoreCase(livro.getCodigo())) {
                throw new IllegalArgumentException(
                        "Já existe um livro cadastrado com o código " + livro.getCodigo() + ".");
            }
        }
        livros.add(livro);
    }

    public List<Livro> listar() {
        return new ArrayList<>(livros);
    }

    public Livro buscarPorCodigo(String codigo) throws LivroNaoEncontradoException {
        if (codigo != null) {
            for (Livro livro : livros) {
                if (livro.getCodigo().equalsIgnoreCase(codigo.trim())) {
                    return livro;
                }
            }
        }
        throw new LivroNaoEncontradoException(codigo == null ? "null" : codigo);
    }

    public void emprestar(String codigo) throws LivroNaoEncontradoException {
        buscarPorCodigo(codigo).emprestar();
    }

    public void devolver(String codigo) throws LivroNaoEncontradoException {
        buscarPorCodigo(codigo).devolver();
    }
}
