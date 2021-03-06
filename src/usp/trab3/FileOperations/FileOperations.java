package usp.trab3.FileOperations;

import usp.trab3.Book.Book;
import usp.trab3.Book.Loan;
import usp.trab3.User.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Classe responsável pelas operações com os arquivos CVS do sistema
 *  */
public class FileOperations {
    static boolean DEBUG = false;


    /**
     * Método que salva uma StringBuilder no arquivo chamado filename, a flag indica se é gravação num arquivo novo ou naõ
     * @param result string CSV
     * @param filename arquivo CSV
     * @param Flag flag que indica se anexamos ou criamos um arquivo novo (1 para anexo, 0 para novo)
     */
    public static void SaveCSV(StringBuilder result, String filename, int Flag) {


        if (DEBUG) System.out.println("[DEBUG] conteudo do objeto ficou: " + result);

        //
        // agora eu gravo ele no arquivo CSV
        //
        FileWriter fw = null;
        try {
            //abrimos arquivo e anexamos (append)
            if (Flag == 1) {
                fw = new FileWriter(filename, true);
            } else {
                fw = new FileWriter(filename);
            }
            fw.append("\n");
            fw.append(result);

            //tratamos o IOException
        } catch (IOException e) {//
            System.out.println("Erro com o filewrite no CSV");
            e.printStackTrace();
        } finally {
            //fechamos o arquivo e cuidamos do possivel exception
            try {
                fw.flush();
                fw.close();
            }catch (IOException e) {
                System.out.println("Erro com o filewrite no CSV");
                e.printStackTrace();
            }
        }

    }

    /**
     * Método que cria uma lista de usuários vindasd o arquivo CSV
     * @param filenameUser
     * @return
     */
    public static List<User> ReadUserCSV(String filenameUser) {

        User temp; //usuário temporario


        List<User> userList = new ArrayList<User>();//arraylist que vamos usar para guardar os usuários

        //para IO
        FileReader fr = null;
        BufferedReader br = null;
        String stringCsv;

        // tratamento e abertura dos arquivos
        try {

            fr = new FileReader(filenameUser);
            br = new BufferedReader(fr);
            stringCsv = br.readLine();

            while (stringCsv != null && stringCsv.length() > 3) {

                //quebramos a string em tokens
                StringTokenizer tempToken = new StringTokenizer(stringCsv, ",");

                // parsing em tokens
                String Name;
                Name = tempToken.nextToken();
                String Address = tempToken.nextToken();
                String Cellphone = tempToken.nextToken();
                int UserType = Integer.parseInt(tempToken.nextToken());
                int BookLimit = Integer.parseInt(tempToken.nextToken());
                int BookLoan = Integer.parseInt(tempToken.nextToken());
                int DateLimit = Integer.parseInt(tempToken.nextToken());

                //lemos a proxima linha
                // e adicionamos
                stringCsv = br.readLine();
                User tempuser = new User(Name, Address,Cellphone, UserType, BookLoan);
                userList.add(tempuser);

                if (DEBUG) {
                    tempuser.PrintInfo();
                }
            }

        } catch(IOException e) {
            System.out.println("Erro ao carregar o arquivo de usuários");
            e.printStackTrace();

        } finally {
            try {

            } catch (NullPointerException e) {
                System.out.println("Erro ao carregar o arquivo de usuários");
                e.printStackTrace();
            }
        }
        return userList;
    }

    /**
     * Método que cria uma lista de usuários vindasd o arquivo CSV
     * @param filenameBook
     * @return
     */
    public static List<Book> ReadBookCSV(String filenameBook) {

        Book  temp; //usuário temporario


        List<Book> BookList = new ArrayList<Book>();//arraylist que vamos usar para guardar os usuários

        //para IO
        FileReader fr = null;
        BufferedReader br = null;
        String stringCsv;

        // tratamento e abertura dos arquivos
        try {

            fr = new FileReader(filenameBook);
            br = new BufferedReader(fr);
            stringCsv = br.readLine();

            while (stringCsv != null && stringCsv.length() > 5) {

                //quebramos a string em tokens
                StringTokenizer tempToken = new StringTokenizer(stringCsv, ",");

                // parsing em tokens
                String Title = tempToken.nextToken();
                String Author = tempToken.nextToken();
                String Publisher = tempToken.nextToken();
                int Pages = Integer.parseInt(tempToken.nextToken());
                int BookType = Integer.parseInt(tempToken.nextToken());
                //lemos a proxima linha
                // e adicionamos
                stringCsv = br.readLine();
                Book tempBook = new Book(Title, Author, Publisher, Pages, BookType);
                BookList.add(tempBook);

                if (DEBUG) {
                    tempBook.PrintInfo();
                }
            }

        } catch(IOException e) {
            System.out.println("Erro ao carregar o arquivo de usuários");
            e.printStackTrace();

        } finally {
            try {
                br.close();
            } catch (Exception e) {
                System.out.println("Erro ao carregar o arquivo de usuários");
                e.printStackTrace();
            }
        }
        return BookList;
    }
    /**
     * Meetodo static que carrega uma lista de emprestismos/loan vinda de um arquivo CSV
     * @param filenameLoan nome do arquivo
     * @return List<Loan>
     */
    public static List<Loan> ReadLoanCSV(String filenameLoan) {

        List<Loan> listLoan = new ArrayList<Loan>();

        FileReader fr = null;
        BufferedReader br = null;
        String stringCSV;

        try {
            fr = new FileReader(filenameLoan);
            br = new BufferedReader(fr);
            stringCSV = br.readLine();

            while(stringCSV != null && stringCSV.length() > 10) {
                StringTokenizer tempToken = new StringTokenizer(stringCSV, ",");


                //
                // pegamos livro
                //
                String Title = tempToken.nextToken();
                String Author = tempToken.nextToken();
                String Publisher = tempToken.nextToken();
                int Pages = Integer.parseInt(tempToken.nextToken());
                int BookType = Integer.parseInt(tempToken.nextToken());
                //lemos a proxima linha
                // e adicionamos
                Book tempBook = new Book(Title, Author, Publisher, Pages, BookType);

                //
                // pegamos usuario
                //
                String Name = tempToken.nextToken();
                String Address = tempToken.nextToken();
                String Cellphone = tempToken.nextToken();
                int UserType = Integer.parseInt(tempToken.nextToken());
                int BookLimit = Integer.parseInt(tempToken.nextToken());
                int BookLoan = Integer.parseInt(tempToken.nextToken());
                int DateLimit = Integer.parseInt(tempToken.nextToken());
                User tempuser = new User(Name, Address,Cellphone, UserType, BookLoan);


                String SDateI = tempToken.nextToken();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date DateI = simpleDateFormat.parse(SDateI);

                String SDateF = tempToken.nextToken();
                SimpleDateFormat simpleDateFormatF = new SimpleDateFormat("dd/MM/yyyy");
                Date DateF = simpleDateFormatF.parse(SDateF);

                int DelayDay = Integer.parseInt(tempToken.nextToken());
                int Status = Integer.parseInt(tempToken.nextToken());
                Loan loan = new Loan(tempBook, tempuser, DateI, DateF, DelayDay, Status);

                listLoan.add(loan);
            }

        } catch (Exception e) {

            System.out.println("erro na escrita do arquivo de emprestismos");
            e.printStackTrace();
        } finally {
            try {

            } catch (Exception e) {
                System.out.println("Erro ao carregar o arquivo de usuários");
                e.printStackTrace();
            }
        }

        return listLoan;
    }
}



















