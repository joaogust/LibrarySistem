package ui.app;
import domain.model.emprestimo.EmprestimoRepository;
import domain.model.livro.LivroRepository;
import domain.model.usuario.Membro;
import domain.model.usuario.MembroRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SystemUI {

    EmprestimoRepository emprestimos = new EmprestimoRepository();
    LivroRepository livros = new LivroRepository();
    MembroRepository membros = new MembroRepository();

    public void login() {
        JFrame janela = new JFrame();
        janela.setLayout(null);

        // Janela
        janela.setBounds(150,100,1200,700);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setVisible(true);

        // Labels
        JLabel usuario = new JLabel("Usuário: ");
        usuario.setBounds(500, 200, 80, 20);

        JLabel senha = new JLabel("Senha: ");
        senha.setBounds(500, 230, 80, 20);

        // TextField
        JTextField campoUsuario = new JTextField();
        campoUsuario.setBounds(570, 200, 120, 20);

        JPasswordField campoSenha = new JPasswordField();
        campoSenha.setBounds(570, 230, 120, 20);

        // Botão "Entrar"
        JButton botaoLogar = new JButton("Entrar");
        botaoLogar.setBounds(490,400,200,30);

        botaoLogar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String usuario = "admin";
                String senha = "123456";
                String usuarioDigitado = campoUsuario.getText().toLowerCase();
                String senhaDigitada = new String(campoSenha.getPassword());

                if (usuario.equals(usuarioDigitado) && senha.equals(senhaDigitada)) {
                    janela.dispose();
                    menu();
                } else {
                    JLabel loginIncorreto = new JLabel("Usuario ou senha incorreto!");
                    loginIncorreto.setBounds(510, 300, 200, 20);
                    loginIncorreto.setForeground(Color.RED);
                    loginIncorreto.setFont(new Font("Arial", Font.BOLD, 13));
                    janela.add(loginIncorreto);
                    janela.repaint();
                    campoUsuario.setText("");
                    campoSenha.setText("");
                }
            }
        });

        // Adicionar
        janela.add(botaoLogar);
        janela.add(usuario);
        janela.add(senha);
        janela.add(campoUsuario);
        janela.add(campoSenha);

        janela.repaint();
    }

    public void menu() {
        JFrame janela = new JFrame();
        janela.setLayout(null);

        // Janela
        janela.setBounds(150,100,1200,700);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setVisible(true);

        // label
        JLabel usuario = new JLabel("Usuário: Admin");
        usuario.setBounds(10, 10, 200,10);

        // Botões
        JButton opcao1 = new JButton("Lista de Clientes");
        opcao1.setBounds(490, 60,200,30);
        opcao1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarClientes();
            }
        });

        JButton opcao2 = new JButton("Lista de Livros");
        opcao2.setBounds(490, 100, 200,30);
        opcao2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarLivros();
            }
        });

        JButton opcao3 = new JButton("Lista de Empréstimos");
        opcao3.setBounds(490, 140, 200,30);
        opcao3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarEmprestimos();
            }
        });

        JButton opcao4 = new JButton("Cadastrar Cliente");
        opcao4.setBounds(490, 180, 200,30);
        opcao4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarCliente();
            }
        });

        JButton opcao5 = new JButton("Cadastrar Livro");
        opcao5.setBounds(490, 220, 200,30);
        opcao5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarLivro();
            }
        });

        JButton opcao6 = new JButton("Cadastrar Empréstimo");
        opcao6.setBounds(490, 260, 200,30);
        opcao6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarEmprestimo();
            }
        });

        JButton sair = new JButton("Sair");
        sair.setBounds(490, 300, 200,30);
        sair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        // Adicionar
        janela.add(usuario);
        janela.add(opcao1);
        janela.add(opcao2);
        janela.add(opcao3);
        janela.add(opcao4);
        janela.add(opcao5);
        janela.add(opcao6);
        janela.add(sair);

        janela.repaint();

    }

    public void listarClientes() {
        JFrame janela = new JFrame();
        janela.setLayout(null);

        // Janela
        janela.setBounds(150,100,1200,700);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setVisible(true);

        // Label
        JLabel titulo = new JLabel("Lista de Clientes");
        titulo.setBounds(470,20,300,50);
        titulo.setFont(new Font("Arial", Font.BOLD, 30));

        // Tabela
        String[] colunas = {"ID", "NOME", "TELEFONE", "CPF", "ENDEREÇO"};
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0);

        for (Membro m : membros.getMembros()) {
            String[] linha = {
                    String.valueOf(m.getId()),
                    m.getNome(),
                    m.getTelefone(),
                    m.getCpf(),
                    m.getEndereco().toString()
            };
            modelo.addRow(linha);
        }

        JTable tabela = new JTable(modelo);
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabela.getColumnModel().getColumn(0).setPreferredWidth(37);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(150);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(130);
        tabela.getColumnModel().getColumn(3).setPreferredWidth(130);
        tabela.getColumnModel().getColumn(4).setPreferredWidth(350);

        JScrollPane painel = new JScrollPane(tabela);
        painel.setBounds(190, 100, 800,250);

        // Botões
        JButton opcao1 = new JButton("Adicionar Cliente");
        opcao1.setBounds(490, 370, 200,30);
        opcao1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        JButton opcao2 = new JButton("Alterar Cliente");
        opcao2.setBounds(490, 410, 200,30);
        opcao2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        JButton opcao3 = new JButton("Menu");
        opcao3.setBounds(490, 450, 200,30);
        opcao3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu();
            }
        });


        // Adicionar
        janela.add(titulo);
        janela.add(painel, BorderLayout.CENTER);
        janela.add(opcao1);
        janela.add(opcao2);
        janela.add(opcao3);
        janela.repaint();
    }

    public void listarLivros() {

    }

    public void listarEmprestimos() {

    }

    public void cadastrarCliente() {

    }

    public void cadastrarLivro() {

    }

    public void cadastrarEmprestimo() {

    }



    /*
    1 - Listar Clientes
    2 - listar livros
    3 - cadastrar cliente
    4 - cadastrar livro -> cadastrar autor
    5 - cadastrar emprestimo
    6 - relatar devolução
    7 - listar emprestimo
     */
}
