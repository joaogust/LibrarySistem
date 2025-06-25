package ui.app;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SystemUI {

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
        botaoLogar.setBounds(490,500,200,30);

        botaoLogar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String usuario = "admin";
                String senha = "123456";
                String usuarioDigitado = campoUsuario.getText().toLowerCase();
                String senhaDigitada = new String(campoSenha.getPassword());

                if (usuario.equals(usuarioDigitado) && senha.equals(senhaDigitada)) {
                    janela.dispose();
                    sistema();
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

    public void sistema() {
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
        JButton opcao1 = new JButton("Listar Clientes");
        opcao1.setBounds(100, 70,200,20);

        JButton opcao2 = new JButton("Listar Livros");
        opcao2.setBounds(100, 100, 200,20);

        JButton opcao3 = new JButton("Listar Empréstimos");
        opcao3.setBounds(100, 130, 200,20);

        JButton opcao4 = new JButton("Cadastrar Cliente");
        opcao4.setBounds(100, 160, 200,20);

        JButton opcao5 = new JButton("Cadastrar Livro");
        opcao5.setBounds(100, 190, 200,20);

        JButton opcao6 = new JButton("Cadastrar Empréstimo");
        opcao6.setBounds(100, 220, 200,20);

        JButton opcao7 = new JButton("Registrar Devolução");
        opcao7.setBounds(100, 250, 200,20);

        JButton sair = new JButton("sair");
        sair.setBounds(100, 280, 200,20);
        sair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
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
        janela.add(opcao7);
        janela.add(sair);

        janela.repaint();

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
