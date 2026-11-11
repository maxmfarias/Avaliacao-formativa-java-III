# Sistema de Gerenciamento de Biblioteca

Aplicação de console em Java para cadastrar, listar, buscar, emprestar e devolver livros.
Os livros são mantidos em memória enquanto o programa está em execução.

## Requisitos

- JDK 15 ou superior (o menu usa blocos de texto e switch expressions).

## Compilar e executar

Na raiz do projeto:

```sh
mkdir -p out
javac -d out $(find src/main/java -name '*.java')
java -cp out app.Main
```

O menu interativo permite cadastrar livros, listar o catálogo, buscar pelo código,
emprestar e devolver. Códigos não podem ser repetidos; operações inválidas exibem
uma mensagem de erro sem encerrar o programa.

## Executar os testes

Os testes automatizados não dependem de bibliotecas externas:

```sh
mkdir -p out
javac -d out $(find src/main/java src/test/java -name '*.java')
java -cp out app.BibliotecaServiceTest
```

Os testes cadastram três livros, verificam listagem e busca, emprestam e tentam
emprestar novamente o mesmo livro, devolvem e tratam a busca por um código
inexistente.
