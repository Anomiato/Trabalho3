package usp.trab3.Book;

import usp.trab3.FileOperations.FileOperations;
import usp.trab3.User.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Objeto que vai representar o Empréstimo no sistema
 */
public class Loan {
    Book LoanBook; // livro do emprestimo
    User LoanUser; // usuario do emprestimo
    Date DateI;    // data inicial do emprestimo
    Date DateF;    // data final do emprestimo
    int DelayDay; // em caso de atraso, quantos dias o usuário desse livro tem
    static String filenameLoan = "Loans.csv";


    //
    // 1 para emprestado: fica no arquivo Loans.csv
    // 2 para devolvido: fica  no arquivo Loans.csv porém não é impresso
    // 3 para atrasado: vai para o o arquivo LoansLate.csv
    // 4 para desativo: estava em atraso
    //

    int Status;    // Status do emprestimo


    // construtor
    public Loan (Book b, User u, Date dateI, Date dateF, int Delay, int Status) {
        LoanBook = b;
        LoanUser = u;
        DateI = dateI;
        DateF = dateF;
        DelayDay = Delay;
        this.Status = Status;
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

    public Date getDateF() { return DateF; }

    public StringBuilder toCSV() {
        StringBuilder result = new StringBuilder();

        result.append(LoanBook.toCSV());
        result.append(",");
        result.append(LoanUser.toCSV());
        result.append(",");
        result.append(DateI.toString());
        result.append(",");
        result.append(DateF.toString());
        result.append(",");
        result.append(DelayDay);
        result.append(",");
        result.append(Status);
        return result;
    }

    public static void testeAtraso(Date date) {
        List<Loan> loanList = FileOperations.ReadLoanCSV(filenameLoan);
        List<Loan> atrasos = new ArrayList<Loan>();
        for(Loan l : loanList)
            if(l.getDateF().before(date))
                if(l.LoanBook.StatusLoan!=0)
                    atrasos.add(l);
        for(Loan l:atrasos)
            FileOperations.SaveCSV(l.toCSV(), "arrears.csv", 1);
    }
}
