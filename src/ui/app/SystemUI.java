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
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setVisible(true);

        // Janela
        janela.setBounds(150,100,1200,700);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setVisible(true);

        // label
        JLabel usuario = new JLabel("Usuário: Admin");
        usuario.setBounds(10, 10, 200,10);

        // Botões
        JButton opcao1 = new JButton("1 - listar Clientes");
        opcao1.setBounds(100, 100, 200,20);
        janela.add(opcao1);

        // Adicionar
        janela.add(usuario);

    }
    /*
    1 - listar Clientes
    2 - listar livros
    3 - cadastrar cliente
    4 - cadastrar livro -> cadastrar autor
    5 - cadastrar emprestimo
    6 - relatar devolução
    7 - listar emprestimo
     */
}
