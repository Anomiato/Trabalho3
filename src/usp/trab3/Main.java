package usp.trab3;

import usp.trab3.Book.Book;
import usp.trab3.FileOperations.FileOperations;
import usp.trab3.User.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Main {

    // Nome dos arquivos que será utilizados para guardar as informações
    static String filenameUser = "Users.csv";
    static String filenameBook = "Books.csv";
    static String filenameLoan = "Loans.csv";

    static Date DataAtual = null; // data atual escolhida pelo usuário na hora que inicia o programa
    // necessária para gerar os atrasos

    public static void main(String[] args) {
        String SDataAtual = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println("Digite a data atual que será usada no programa[dd/MM/yyyy]: ");
            SDataAtual = br.readLine();

            SimpleDateFormat sp = new SimpleDateFormat("dd/MM/yyyy");
            DataAtual = sp.parse(SDataAtual);

            System.out.println("A data digitada foi: " + DataAtual.toString() );

            //
            // chamo a função que gera  arquivo de atrasos
            //

            Main.Loop(); // loop do programa principal/

        } catch (Exception e) {
            System.out.println("Um erro ocorreu no Loop principal");

        }finally {
           System.out.println("Finalizando...");
        }

    }

    /**
     * Função que vai ter o loop do programa junto com o menu inicial
     * @throws Exception
     */
    private static void Loop() throws Exception {

        while(true) {
            System.out.print("\n\n\n\n\n" +
                            "Menu de opções - Digite uma das opções[1-4]"
                            + "\n"
                            + "\n"
                            + "1) Cadastros de Usuário\n"
                            + "2) Cadastros de Livros\n"
                            + "3) Empréstimos\n"
                            + "4) Devoluções\n"
                            + "5) Listagens\n"
                            + "6) Sair\n"


            );
            System.out.println("Digite a Opção desejada: ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int op = Integer.parseInt(br.readLine());

            switch(op) {
                case 1: // cadastro de usuário
                    System.out.println("Opção escolhida: " + op);
                    CadastroUsuario(0);
                    break;
                case 2:  // cadastro de livro
                    System.out.println("Opção escolhida: " + op);
                    CadastroLivro(0);
                    break;
                case 3: //emprestimos
                    System.out.println("Opção escolhida: " + op);
                    Emprestimo();
                    break;
                case 4: // devoluções
                    System.out.println("Opção escolhida: " + op);
                    Devolucao();
                    break;
                case 5: // Listagens
                    System.out.println("Opção escolhida: " + op);
                    Listagem();
                    break;
                case 6:// saindo
                    System.out.println("Opção escolhida: " + op);
                    System.exit(0);
                default:
                    System.out.println("Opção escolhida: " + op);
                    System.out.println("Opção errada, tente novamente");
            }
        }
    }


    /**
     * Método que cadastra um usuário, retorna um usuário se a flag N estiver como 1, 0 ele grava o usuaŕio no arquivo
     * @param n flag que indica se gravamos o usuaŕio novo no arquivo ou se retornamos ele
     * @return retorna o usuário
     */
    private static User CadastroUsuario(int n) {
        String Name = null;
        String Address = null;
        String Cellphone = null;
        int UserType = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));

        System.out.println("Digite as informações do usuário: ");

        System.out.print("Nome do usuário: ");
        try { Name = br.readLine(); } catch(IOException e) { System.out.println("Erro no input do nome");}

        System.out.print("\nEndereço do usuário: ");
        try { Address = br.readLine(); } catch(IOException e) { System.out.println("Erro no input do endereço");}

        System.out.print("\nTelefone/Celular do usuário: ");
        try { Cellphone = br.readLine(); } catch(IOException e) { System.out.println("Erro no input do telefone");}

        System.out.print("\nTipo do usuário(1 para PROFESSOR, 2 para ALUNO, 3 para COMUNIDADE: ");
        try { UserType = Integer.parseInt(br.readLine()); } catch(IOException e) { System.out.println("Erro no input do tipo de usuario");}


        //
        // crio um novo objeto usuário e chamo a funçaõ de IO para adicionar no arquivo
        //
        User usuario = new User(Name, Address, Cellphone, UserType, 0);

        System.out.println("As informações cadastradas foram: ");
        usuario.PrintInfo();

        //
        // chamo função que adiciona o novo usuário no 'banco de dados', no caso, o arquivo csv dele
        //
        if (n == 1) {
            FileOperations.SaveCSV(usuario.toCSV(), filenameUser, 1);
            return null;
        } else { // caso a flag seja zero ou diferente de 1, retornamos o objeto que queremos
            return usuario;
        }

    }

    /**
     * Método que cadastra um livro, pode ou naõ retornar o livro cadastrado, depende de flag
     * @param n  flag que indica se retorna ou um livro ou NULL (0 para retornar o livro)
     * @return o livro cadastrado
     */
    private static Book CadastroLivro(int n) {
        String Title = null;
        String Author = null;
        String Publisher = null;
        int Pages = 0;
        int BookType = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));

        System.out.println("Digite as informações do Livro: ");

        System.out.print("Título do Livro: ");
        try { Title = br.readLine(); } catch(IOException e) { System.out.println("Erro no input do titulo");}

        System.out.print("\nAutor:  ");
        try { Author = br.readLine(); } catch(IOException e) { System.out.println("Erro no input do Autor");}

        System.out.print("\nEditora: ");
        try { Publisher = br.readLine(); } catch(IOException e) { System.out.println("Erro no input da editora");}

        System.out.print("\nQuantidade de páginas: ");
        try { Pages = Integer.parseInt(br.readLine());
        } catch(IOException e) { System.out.println("Erro no input nas quantidades de páginas");}

        System.out.print("\nTipo de livro (1 para TEXTO, 2 para GERAL): ");
        try { BookType = Integer.parseInt(br.readLine()); } catch(IOException e) { System.out.println("Erro no input do tipo de livro");}

        // crio um objeto livro das informaçõe do input
        Book b = new Book(Title, Author, Publisher, Pages, BookType);
        System.out.println("As informações cadastradas foram: ");
        b.PrintInfo();

        //
        // chamo o método de salva o livro no arquivo
        //
        if (n == 1) {
            FileOperations.SaveCSV(b.toCSV(), filenameBook, 1);
            return null;
        } else { // flag ativada, retornamos o objeto criado
            return b;
        }
    }

    //
    // método que chama o empréstimo
    //
    private static void Emprestimo() {

        //
        // construo dois arraylist, um vai conter os usuaŕios e o outro, os livros
        //
        List<Book> bookList = FileOperations.ReadBookCSV(filenameBook);
        List<User> userList = FileOperations.ReadUserCSV(filenameUser);

        Book tempBook = null; // book temporario
        Book tempUser = null; // user temporario

        String s = null; // opção para mudar data atual
        String Title = null;// titulo do livro
        String UserName = null; // nome do usuário

        Date DateI = null; // data inicial
        String SDateI = null;

        Date DateD = null; // data de devolução
        String SDateD = null; // string da data de devolução

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Digite as informações de empréstimo: ");

        // input do titulo
        while(true) {
            System.out.println("Digite o título do livro: ");
            try { Title = br.readLine(); } catch(IOException e) { System.out.println("Erro no input do titulo"); continue; }

            //
            // verifico se o usuaŕio está dentro da lista, se sim, pego/remove ele
            //

            Boolean flag = false;
            // ando na lista verificando se ele existe
            for(Book e: bookList) {
                if (e.getTitle().equalsIgnoreCase(Title)) { // encontrei
                    flag = true;
                    tempBook = e; // guardo o objeto
                    bookList.remove(e); // remove ele
                    break; // sai do loop., já encontrei o que eu queria
                }
            }

            if (flag) {// encontrei as informações
                System.out.println("Título encontrado: ");
                tempBook.PrintInfo();
                break;
            } else {
                System.out.println("Título Não encontrado, digite novamente");
                continue;
            }
        }


        //input do nome do usuário
        System.out.println("Digite o nome do usuário:");
        try { UserName = br.readLine(); } catch(IOException e) { System.out.println("Erro no input do nome");}

        // data da devolução (pode ser configurada no inicio do programa ou aqui)
        System.out.println("A Data Atual digitada no inicio do programa é:  " + DataAtual.toString());

        System.out.println("Deseja manter a data (Y/N)?");
        try { s = br.readLine(); } catch (IOException e) { System.out.println("Erro no input de opções");}

        // mudamos a data (caso o usuário digite N)
        assert s != null; //garrantimos que não é nula para evitar erors
        if (s.compareToIgnoreCase("N") == 0) {
            try {
                System.out.println("Digite a data inicial do emprestimo(dd/MM/yyyy): ");
                SDateI = br.readLine();

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                DateI = simpleDateFormat.parse(SDateI);
                System.out.println("Nova data: " + DateI.toString());

            } catch(Exception e) {
                System.out.println("Erro na mudança da data atual..");
            }
        } else {
            DateI = DataAtual;
        }

        //
        // chamamos a função que faz os empréstimos(grava em arquivo)
        //
    }

    //
    // método que chama a devoluçaõ
    //
    private static void Devolucao() {
        String Title = null; // nome do titulo do livro
        String UserName = null; //nome do usuário
        String SDateD = null; // string da data de devoluçaõ
        Date DateD = null; // data de devolução

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Digite as informações de devolução: ");
        while(true) {

            System.out.println("Digite o título:  ");
            try { Title = br.readLine(); } catch(IOException e) { System.out.println("Erro no input do titulo");}



        }

        System.out.println("Digite o nome do usuário :  ");
        try { UserName = br.readLine(); } catch(IOException e) { System.out.println("Erro no input do nome");
        }

        System.out.println("Digite a data de devolução[dd/MM/yyyy]: ");
        try { SDateD = br.readLine(); } catch(IOException e) { System.out.println("Erro no input da data");}

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            DateD = simpleDateFormat.parse(SDateD);
        } catch(Exception e) {
            System.out.println("Erro na data de devolução..");
        }

        //
        //
        // chama o método que adiciona essa devoluçaõ ao sistema
        //
        //
    }

    /**
     * Método que mostra tela de listagem de todas as informações dos sistema
     */
    private static void Listagem() {
        while (true) {
            System.out.print("" +
                            "Menu de Listagem - Digite uma das opções[1-4]"
                            + "\n"
                            + "\n"
                            + "1) Listagem de Usuário\n"
                            + "2) Listagem de Livros\n"
                            + "3) Listagem de Empréstimos\n"
                            + "4) Listagem de Devoluções\n"
                            + "5) Listagem de Atrasos\n"
                            + "6) Voltar\n"
            );
            System.out.println("Digite a Opção desejada: ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int op = 0;
            try {
                op = Integer.parseInt(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Erro na opção de listagem");
            }

            switch (op) {
                case 1: // listagem  de usuário
                    System.out.println("Opção escolhida: " + op);
                    ListagemUsuario();
                    break;
                case 2:  // listagem  de livros
                    System.out.println("Opção escolhida: " + op);
                    break;
                case 3: //listagem de emprestimos
                    System.out.println("Opção escolhida: " + op);
                    break;
                case 4: // listagem de devoluções
                    System.out.println("Opção escolhida: " + op);
                    break;
                case 5: // listagem de atravsos
                    System.out.println("Opção escolhida: " + op);
                    break;
                case 6:// saindo
                    System.out.println("Opção escolhida: " + op);
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção escolhida: " + op);
                    System.out.println("Opção errada, tente novamente");
            }

        }
    }

    /**
     * Método que mostra uma listagem de todos os usuários no sistema
     */
    private static void ListagemUsuario() {
        System.out.println("Lista de usuários cadastrados no sistema: ");

        // chamamos uma função static para cuidar de uma lista que vem do arquivo CSV chamado filenameUser
        User.printUserList(FileOperations.ReadUserCSV(filenameUser));
    }

    /**
     * Método que mostra uma listagem de todos os livros no sistema
     */
    private static void ListagemLivros() {
        System.out.println("Lista de Livros cadastrados no sistema: ");

        // chamamos uma função static para cuidar de uma lista que vem do arquivo CSV chamado filenameUser
        Book.printBookList(FileOperations.ReadBookCSV(filenameBook));
    }



}
