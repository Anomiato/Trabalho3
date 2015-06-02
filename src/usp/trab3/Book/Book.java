package usp.trab3.Book;

import java.util.List;

/**
 * Classe que ira representar o livro dentro do programa.
 */
public class Book {

    String Title;//titulo do livro
    String Author; // autor do livro
    String Publisher; // editora do livro
    int Pages; // qtd paginas do livro
    int BookType; // tipo do livro 1 (TEXTO apenas aluno e professor) ou 2 (GERAL to-do mundo pode pegar)

    // flag que indica qual é o status do livro
    // 0 para em estoque
    // 1 para emprestado
    // 2 para devolvido
    // 3 para atrasado
    int StatusLoan;

    public Book(String title, String author, String publisher, int pages, int typebook) {
        Title = title;
        Author = author;
        Publisher = publisher;
        Pages = pages;
        BookType = typebook;
        StatusLoan = 0;
    }

    /**
     *  Função que imprimi o conteudo do livro na tela do terminal
     */
    public void PrintInfo() {
        System.out.println("Título: " + Title +
                "\nAutor: " + Author +
                "\nEditora: " + Publisher +
                "\nPáginas: " + Pages +
                "\nTipo: " + BookType);
        if (StatusLoan == 0) {
            System.out.println("Status: Estoque");
        } else if (StatusLoan == 1) {
            System.out.println("Status: Emprestado");
        } else if (StatusLoan == 2) {
            System.out.println("Status: Devolvido");
        } else if (StatusLoan == 3){
            System.out.println("Status: Atrasado");
        }
    }

    /**
     * Método que retorna as informações do objeto no formato CSV
     * @return StringBuilder no formato CSV
     */
    public StringBuilder toCSV() {

        StringBuilder result = new StringBuilder();

        result.append(Title);
        result.append(",");
        result.append(Author);
        result.append(",");
        result.append(Publisher);
        result.append(",");
        result.append(Pages);
        result.append(",");
        result.append(BookType);

        return result;
    }

    /**
     * Método static que recebe uma lista de livros e imprimi
     * @param bookList
     */
    public static void printBookList(List<Book> bookList) {
        for(Book b : bookList) {
            System.out.println("\\n---------------------------------------------------");
            b.PrintInfo();
            System.out.println("\\n---------------------------------------------------");
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    //
    // getters and setters
    //
    public String getTitle() {
        return Title;
    }

    /**
     * Metodo que verifica se o livro esta disponivel para emprestimo
     */
    public boolean loanStatus() {
        if(StatusLoan==0)
            return true;
        else
            return false;
    }
}
