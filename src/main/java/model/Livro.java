package model;

import interfaces.Emprestavel;

public class Livro implements Emprestavel {
    private final String codigo;
    private final String titulo;
    private final String autor;
    private boolean emprestado;

    public Livro(String codigo, String titulo, String autor) {
        this.codigo = validarCampo(codigo, "código");
        this.titulo = validarCampo(titulo, "título");
        this.autor = validarCampo(autor, "autor");
    }

    private static String validarCampo(String valor, String nomeCampo) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException("O " + nomeCampo + " não pode ficar vazio.");
        }
        return valor.trim();
    }

    public String getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public boolean isEmprestado() {
        return emprestado;
    }

    @Override
    public void emprestar() {
        if (emprestado) {
            throw new IllegalStateException("O livro já está emprestado.");
        }
        emprestado = true;
    }

    @Override
    public void devolver() {
        if (!emprestado) {
            throw new IllegalStateException("O livro não está emprestado.");
        }
        emprestado = false;
    }

    @Override
    public String toString() {
        String status = emprestado ? "Emprestado" : "Disponível";
        return "Código: " + codigo + " | Título: " + titulo
                + " | Autor: " + autor + " | Status: " + status;
    }
}
