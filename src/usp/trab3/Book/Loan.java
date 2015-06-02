package usp.trab3.Book;

import usp.trab3.User.User;

import java.util.Date;

/**
 * Objeto que vai representar o Empréstimo no sistema
 */
public class Loan {
    Book LoanBook; // livro do emprestimo
    User LoanUser; // usuario do emprestimo
    Date DateI;    // data inicial do emprestimo
    Date DateF;    // data final do emprestimo
    int DelayDay; // em caso de atraso, quantos dias o usuário desse livro tem
    //
    // 1 para emprestado: fica no arquivo Loans.csv
    // 2 para devolvido: fica  no arquivo Loans.csv porém não é impresso
    // 3 para atrasado: vai para o o arquivo LoansLate.csv
    // 4 para desativo: estava em atraso
    //

    int Status;    // Status do emprestimo


    // construtor
    public Loan (Book b, User u, Date dateI, Date dateF, int Delay) {
        LoanBook = b;
        LoanUser = u;
        DateI = dateI;
        DateF = dateF;
        DelayDay = Delay;
    }


    //
    //getters e settes dos atributos
    //
    public void setDelayDay(int d) {
        DelayDay = d;
    }

    public int getDelayDay(int d) {
        return DelayDay;
    }
}
