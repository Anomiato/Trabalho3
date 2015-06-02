

package usp.trab3.User;

import java.util.List;

/**
 *
 */
public class User {

    String Name; // nome do usuário
    String Address; // endereço do usuário
    String Cellphone;  // celular/telefone do usuário
    int UserType; // tipo de usuário (professor, aluno, comunidade)
    int BookLimit; // limite de livro
    int DateLimit; // dias maximos para cada emprestimo

    int BookLoan; // quantidade de livros pedidos
    /**
     * Construtor da classe User
     * @param name nome do usuário
     * @param address endereço do usuáiro
     * @param cellphone telefone/celular do usuário
     * @param usertype tipo de usuário (1 para PROFESSOR, 2 para ALUNO, 3 para COMUNIDADE)
     */
    public User(String name, String address, String cellphone, int usertype, int bookLoan) {

        Name = name;
        Address = address;
        Cellphone = cellphone;
        UserType = usertype;

        if (UserType == 1) { //professor limite seis livoros, 60 dias
            BookLimit = 6;
            DateLimit = 60;

        } else if (UserType == 2) { //  aluno, limite 4 livros, quinze dias
            BookLimit = 4;
            DateLimit = 15;

        } else if (UserType == 3) { // comunidade limite 2 livros,  quinze dias
            BookLimit = 2;
            DateLimit = 15;

        }
        BookLoan = bookLoan;
    }

    /**
     * Método que imprimir as informações do usuário
     */
    public void PrintInfo() {
        System.out.println("" +
                        "\nNome do usuário: " + Name +
                        "\nEndereço: " + Address +
                        "\nTelefone/Celular: " + Cellphone +
                        "\nTipo de Usuário(1 para PROFESSOR, 2 para ALUNO, 3 para COMUNIDADE): " + UserType +
                        "\n" + "Quantidade de livros em emprestimo: " + BookLoan
                );
    }

    /**
     * Método que transforma os atributos numa string no formato CSV
     * @return StringBuilder no formato CSV
     */
    public StringBuilder toCSV() {
        StringBuilder result = new StringBuilder();

        result.append(Name);
        result.append(",");
        result.append(Address);
        result.append(",");
        result.append(Cellphone);
        result.append(",");
        result.append(UserType);
        result.append(",");
        result.append(BookLimit);
        result.append(",");
        result.append(DateLimit);
        result.append(",");
        result.append(BookLoan);
        result.append("\n");

        return result;
    }


    public static void printUserList(List<User> userList) {
        for(User u : userList) {
            System.out.print("\\n---------------------------------------------------");
            u.PrintInfo();
            System.out.print("\n---------------------------------------------------");

            // esperamos um pouco para continuar tudo
            try {
                Thread.sleep(2000);
            } catch(Exception e) {
                e.printStackTrace();
            }

        }

    }

    //
    // gettets and setters
    //

    public String getName() {
        return Name;
    }
}
