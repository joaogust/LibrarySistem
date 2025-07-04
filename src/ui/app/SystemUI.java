package ui.app;
import domain.model.emprestimo.EmprestimoRepository;
import domain.model.livro.Autor;
import domain.model.livro.Livro;
import domain.model.livro.LivroRepository;
import domain.model.usuario.Endereco;
import domain.model.usuario.Membro;
import domain.model.usuario.MembroRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SystemUI {
    EmprestimoRepository emprestimos = new EmprestimoRepository();
    LivroRepository livros = new LivroRepository();
    MembroRepository membros = new MembroRepository();

    public void login() {
        JFrame janela = new JFrame("Login - Sistema Bibliotecário");
        janela.setSize(400, 300);
        janela.setLayout(null);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLocationRelativeTo(null); // Centraliza a janela
        janela.setResizable(false);

        // Título
        JLabel titulo = new JLabel("Bem-vindo!");
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setBounds(135, 20, 200, 30);

        // Labels
        JLabel usuarioLabel = new JLabel("Usuário:");
        usuarioLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        usuarioLabel.setBounds(60, 70, 80, 25);

        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        senhaLabel.setBounds(60, 110, 80, 25);

        // Campos de texto
        JTextField campoUsuario = new JTextField();
        campoUsuario.setBounds(140, 70, 180, 25);

        JPasswordField campoSenha = new JPasswordField();
        campoSenha.setBounds(140, 110, 180, 25);

        // Mensagem de erro (visível apenas se falhar)
        JLabel mensagemErro = new JLabel("");
        mensagemErro.setBounds(60, 150, 280, 25);
        mensagemErro.setFont(new Font("Arial", Font.BOLD, 12));
        mensagemErro.setForeground(Color.RED);

        // Botão Entrar
        JButton botaoLogar = new JButton("Entrar");
        botaoLogar.setBounds(140, 190, 120, 30);
        botaoLogar.setFont(new Font("Arial", Font.BOLD, 14));

        botaoLogar.addActionListener(e -> {
            String usuario = "admin";
            String senha = "123456";
            String usuarioDigitado = campoUsuario.getText().toLowerCase();
            String senhaDigitada = new String(campoSenha.getPassword());

            if (usuario.equals(usuarioDigitado) && senha.equals(senhaDigitada)) {
                janela.dispose();
                menu();
            } else {
                mensagemErro.setText("Usuário ou senha incorretos!");
                campoUsuario.setText("");
                campoSenha.setText("");
            }
        });

        // Adiciona os componentes
        janela.add(titulo);
        janela.add(usuarioLabel);
        janela.add(campoUsuario);
        janela.add(senhaLabel);
        janela.add(campoSenha);
        janela.add(botaoLogar);
        janela.add(mensagemErro);

        janela.setVisible(true);
    }

    public void menu() {
        JFrame janela = new JFrame("Menu Principal");
        janela.setSize(400, 450);
        janela.setLayout(null);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLocationRelativeTo(null); // Centraliza a janela na tela
        janela.setResizable(false);

        // Label do usuário
        JLabel usuario = new JLabel("Usuário: Admin");
        usuario.setBounds(20, 10, 200, 20);
        usuario.setFont(new Font("Arial", Font.PLAIN, 14));

        // Criar botões com tamanhos e espaçamento consistentes
        int larguraBotao = 300;
        int alturaBotao = 35;
        int xPos = (janela.getWidth() - larguraBotao) / 2;
        int yInicial = 50;
        int espacamento = 45;

        JButton opcao1 = new JButton("Lista de Clientes");
        opcao1.setBounds(xPos, yInicial, larguraBotao, alturaBotao);
        opcao1.addActionListener(e -> listarClientes());

        JButton opcao2 = new JButton("Lista de Livros");
        opcao2.setBounds(xPos, yInicial + espacamento, larguraBotao, alturaBotao);
        opcao2.addActionListener(e -> listarLivros());

        JButton opcao3 = new JButton("Lista de Empréstimos");
        opcao3.setBounds(xPos, yInicial + espacamento * 2, larguraBotao, alturaBotao);
        opcao3.addActionListener(e -> listarEmprestimos());

        JButton opcao4 = new JButton("Cadastrar Cliente");
        opcao4.setBounds(xPos, yInicial + espacamento * 3, larguraBotao, alturaBotao);
        opcao4.addActionListener(e -> cadastrarCliente());

        JButton opcao5 = new JButton("Cadastrar Livro");
        opcao5.setBounds(xPos, yInicial + espacamento * 4, larguraBotao, alturaBotao);
        opcao5.addActionListener(e -> cadastrarLivro());

        JButton opcao6 = new JButton("Cadastrar Empréstimo");
        opcao6.setBounds(xPos, yInicial + espacamento * 5, larguraBotao, alturaBotao);
        opcao6.addActionListener(e -> cadastrarEmprestimo());

        JButton sair = new JButton("Sair");
        sair.setBounds(xPos, yInicial + espacamento * 6, larguraBotao, alturaBotao);
        sair.addActionListener(e -> {
            janela.dispose();
            login();
        });

        // Adiciona componentes na janela
        janela.add(usuario);
        janela.add(opcao1);
        janela.add(opcao2);
        janela.add(opcao3);
        janela.add(opcao4);
        janela.add(opcao5);
        janela.add(opcao6);
        janela.add(sair);

        janela.setVisible(true);
    }

    public void listarClientes() {
        JFrame janela = new JFrame("Lista de Membros");
        janela.setSize(1000, 600);
        janela.setLayout(null);
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        janela.setLocationRelativeTo(null);

        JLabel titulo = new JLabel("Lista de Membros");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBounds(390, 20, 300, 30);

        // Cabeçalho da tabela
        String[] colunas = {"ID", "Nome", "Telefone", "CPF", "Endereço"};
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0);

        // Preenchimento da tabela com os membros
        for (Membro m : membros.getMembros()) {
            modelo.addRow(new String[]{
                    String.valueOf(m.getId()),
                    m.getNome(),
                    m.getTelefone(),
                    m.getCpf(),
                    m.getEndereco().toString()
            });
        }

        JTable tabela = new JTable(modelo);
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabela.getColumnModel().getColumn(0).setPreferredWidth(50);   // ID
        tabela.getColumnModel().getColumn(1).setPreferredWidth(150);  // Nome
        tabela.getColumnModel().getColumn(2).setPreferredWidth(120);  // Telefone
        tabela.getColumnModel().getColumn(3).setPreferredWidth(130);  // CPF
        tabela.getColumnModel().getColumn(4).setPreferredWidth(500);  // Endereço

        JScrollPane painel = new JScrollPane(tabela);
        painel.setBounds(50, 80, 900, 300);

        // Botão Voltar ao Menu
        JButton voltar = new JButton("Voltar");
        voltar.setBounds(400, 400, 200, 30);
        voltar.addActionListener(e -> janela.dispose());

        janela.add(titulo);
        janela.add(painel);
        janela.add(voltar);
        janela.setVisible(true);
    }

    public void listarLivros() {
        JFrame janela = new JFrame("Lista de Livros");
        janela.setSize(900, 500);
        janela.setLayout(null);
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel titulo = new JLabel("Lista de Livros");
        titulo.setBounds(330, 20, 300, 30);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));

        // Define colunas da tabela
        String[] colunas = {"ID", "Título", "ISBN", "Autor(es)", "Total de Cópias", "Disponíveis"};
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0);

        for (domain.model.livro.Livro l : livros.getLivros()) {
            StringBuilder autores = new StringBuilder();
            for (domain.model.livro.Autor a : l.getAutores()) {
                autores.append(a.toString()).append("; ");
            }

            String[] linha = {
                    String.valueOf(l.getId()),
                    l.getTitulo(),
                    l.getISBN(),
                    autores.toString(),
                    String.valueOf(l.getCopias().size()),
                    String.valueOf(l.totalDisponiveis())
            };

            modelo.addRow(linha);
        }

        JTable tabela = new JTable(modelo);
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabela.getColumnModel().getColumn(0).setPreferredWidth(40);  // ID
        tabela.getColumnModel().getColumn(1).setPreferredWidth(180); // Título
        tabela.getColumnModel().getColumn(2).setPreferredWidth(150); // ISBN
        tabela.getColumnModel().getColumn(3).setPreferredWidth(200); // Autor(es)
        tabela.getColumnModel().getColumn(4).setPreferredWidth(100); // Total de Cópias
        tabela.getColumnModel().getColumn(5).setPreferredWidth(100); // Disponíveis

        JScrollPane painel = new JScrollPane(tabela);
        painel.setBounds(50, 70, 780, 300);

        JButton voltar = new JButton("Voltar");
        voltar.setBounds(380, 390, 120, 30);
        voltar.addActionListener(e -> janela.dispose());

        janela.add(titulo);
        janela.add(painel);
        janela.add(voltar);

        janela.setLocationRelativeTo(null);
        janela.setVisible(true);
    }

    public void listarEmprestimos() {
        JFrame janela = new JFrame("Lista de Empréstimos");
        janela.setSize(800, 500);
        janela.setLayout(null);
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel titulo = new JLabel("Empréstimos Registrados");
        titulo.setBounds(250, 20, 400, 30);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));

        DefaultTableModel modelo = new DefaultTableModel(new String[]{"Membro", "Livros", "Início", "Fim"}, 0);
        for (var e : emprestimos.getEmprestimos()) {
            String livros = e.getCopias().size() + " livro(s)";
            modelo.addRow(new String[]{
                    e.getMembro().getNome(),
                    livros,
                    e.getDataInicial().toString(),
                    e.getDataFinal().toString()
            });
        }

        JTable tabela = new JTable(modelo);
        JScrollPane painel = new JScrollPane(tabela);
        painel.setBounds(50, 70, 700, 300);

        JButton voltar = new JButton("Voltar");
        voltar.setBounds(350, 390, 100, 30);
        voltar.addActionListener(e -> janela.dispose());

        janela.add(titulo);
        janela.add(painel);
        janela.add(voltar);
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);
    }

    public void cadastrarCliente() {
        JFrame janela = new JFrame("Cadastrar Cliente");
        janela.setSize(1200, 700);
        janela.setLayout(null);
        janela.setLocationRelativeTo(null);
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        janela.setResizable(false);

        JLabel titulo = new JLabel("Cadastro de Cliente");
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        titulo.setBounds(430, 20, 400, 30);

        Font fonteLabel = new Font("Arial", Font.PLAIN, 16);
        int labelX = 320;
        int campoX = 500;
        int largura = 300;
        int altura = 30;
        int y = 80;
        int espacamento = 45;

        JLabel labelNome = new JLabel("Nome:");
        labelNome.setBounds(labelX, y, 150, altura);
        labelNome.setFont(fonteLabel);
        JTextField campoNome = new JTextField();
        campoNome.setBounds(campoX, y, largura, altura);

        y += espacamento;
        JLabel labelCpf = new JLabel("CPF:");
        labelCpf.setBounds(labelX, y, 150, altura);
        labelCpf.setFont(fonteLabel);
        JTextField campoCpf = new JTextField();
        campoCpf.setBounds(campoX, y, largura, altura);

        y += espacamento;
        JLabel labelTelefone = new JLabel("Telefone:");
        labelTelefone.setBounds(labelX, y, 150, altura);
        labelTelefone.setFont(fonteLabel);
        JTextField campoTelefone = new JTextField();
        campoTelefone.setBounds(campoX, y, largura, altura);

        y += espacamento;
        JLabel labelRua = new JLabel("Rua:");
        labelRua.setBounds(labelX, y, 150, altura);
        labelRua.setFont(fonteLabel);
        JTextField campoRua = new JTextField();
        campoRua.setBounds(campoX, y, largura, altura);

        y += espacamento;
        JLabel labelNumero = new JLabel("Número:");
        labelNumero.setBounds(labelX, y, 150, altura);
        labelNumero.setFont(fonteLabel);
        JTextField campoNumero = new JTextField();
        campoNumero.setBounds(campoX, y, largura, altura);

        y += espacamento;
        JLabel labelBairro = new JLabel("Bairro:");
        labelBairro.setBounds(labelX, y, 150, altura);
        labelBairro.setFont(fonteLabel);
        JTextField campoBairro = new JTextField();
        campoBairro.setBounds(campoX, y, largura, altura);

        y += espacamento;
        JLabel labelCidade = new JLabel("Cidade:");
        labelCidade.setBounds(labelX, y, 150, altura);
        labelCidade.setFont(fonteLabel);
        JTextField campoCidade = new JTextField();
        campoCidade.setBounds(campoX, y, largura, altura);

        y += espacamento;
        JLabel labelEstado = new JLabel("Estado:");
        labelEstado.setBounds(labelX, y, 150, altura);
        labelEstado.setFont(fonteLabel);
        JTextField campoEstado = new JTextField();
        campoEstado.setBounds(campoX, y, largura, altura);

        y += espacamento;
        JLabel labelCep = new JLabel("CEP:");
        labelCep.setBounds(labelX, y, 150, altura);
        labelCep.setFont(fonteLabel);
        JTextField campoCep = new JTextField();
        campoCep.setBounds(campoX, y, largura, altura);

        JLabel mensagem = new JLabel("");
        mensagem.setBounds(400, y + 40, 500, 25);
        mensagem.setFont(new Font("Arial", Font.BOLD, 14));

        JButton botaoCadastrar = new JButton("Cadastrar Cliente");
        botaoCadastrar.setBounds(400, y + 70, 200, 35);

        JButton botaoVoltar = new JButton("Voltar ao Menu");
        botaoVoltar.setBounds(620, y + 70, 200, 35);
        botaoVoltar.addActionListener(e -> {
            janela.dispose();
            menu();
        });

        botaoCadastrar.addActionListener(e -> {
            String nome = campoNome.getText().trim();
            String cpf = campoCpf.getText().trim();
            String telefone = campoTelefone.getText().trim();
            String rua = campoRua.getText().trim();
            String numeroStr = campoNumero.getText().trim();
            String bairro = campoBairro.getText().trim();
            String cidade = campoCidade.getText().trim();
            String estado = campoEstado.getText().trim();
            String cep = campoCep.getText().trim();

            if (nome.isEmpty() || cpf.isEmpty() || telefone.isEmpty() ||
                    rua.isEmpty() || numeroStr.isEmpty() || bairro.isEmpty() ||
                    cidade.isEmpty() || estado.isEmpty() || cep.isEmpty()) {
                mensagem.setText("Por favor, preencha todos os campos.");
                mensagem.setForeground(Color.RED);
                return;
            }

            int numero;
            try {
                numero = Integer.parseInt(numeroStr);
            } catch (NumberFormatException ex) {
                mensagem.setText("Número do endereço inválido.");
                mensagem.setForeground(Color.RED);
                return;
            }

            Endereco endereco = new Endereco(rua, bairro, cidade, estado, numero, cep);
            Membro novoMembro = new Membro(nome, cpf, endereco, telefone);

            membros.adicionarMembroOrdenado(novoMembro);
            mensagem.setText("Cliente cadastrado com sucesso!");
            mensagem.setForeground(new Color(0, 128, 0));

            // Limpa os campos
            campoNome.setText("");
            campoCpf.setText("");
            campoTelefone.setText("");
            campoRua.setText("");
            campoNumero.setText("");
            campoBairro.setText("");
            campoCidade.setText("");
            campoEstado.setText("");
            campoCep.setText("");
        });

        // Adiciona todos os componentes
        janela.add(titulo);
        janela.add(labelNome); janela.add(campoNome);
        janela.add(labelCpf); janela.add(campoCpf);
        janela.add(labelTelefone); janela.add(campoTelefone);
        janela.add(labelRua); janela.add(campoRua);
        janela.add(labelNumero); janela.add(campoNumero);
        janela.add(labelBairro); janela.add(campoBairro);
        janela.add(labelCidade); janela.add(campoCidade);
        janela.add(labelEstado); janela.add(campoEstado);
        janela.add(labelCep); janela.add(campoCep);
        janela.add(botaoCadastrar);
        janela.add(botaoVoltar);
        janela.add(mensagem);

        janela.setVisible(true);
    }

    public void cadastrarLivro() {
        JFrame janela = new JFrame("Cadastrar Livro");
        janela.setSize(1200, 700);
        janela.setLayout(null);
        janela.setLocationRelativeTo(null);
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        janela.setResizable(false);

        JLabel titulo = new JLabel("Cadastro de Livro");
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        titulo.setBounds(430, 20, 400, 30);

        Font fonteLabel = new Font("Arial", Font.PLAIN, 16);
        int labelX = 320;
        int campoX = 500;
        int largura = 300;
        int altura = 30;
        int y = 80;
        int espacamento = 45;

        JLabel labelTitulo = new JLabel("Título:");
        labelTitulo.setBounds(labelX, y, 150, altura);
        labelTitulo.setFont(fonteLabel);
        JTextField campoTitulo = new JTextField();
        campoTitulo.setBounds(campoX, y, largura, altura);

        y += espacamento;
        JLabel labelIsbn = new JLabel("ISBN:");
        labelIsbn.setBounds(labelX, y, 150, altura);
        labelIsbn.setFont(fonteLabel);
        JTextField campoIsbn = new JTextField();
        campoIsbn.setBounds(campoX, y, largura, altura);

        y += espacamento;
        JLabel labelAutores = new JLabel("Autor(es) (separados ','):");
        labelAutores.setBounds(labelX, y, 250, altura);
        labelAutores.setFont(fonteLabel);
        JTextField campoAutores = new JTextField();
        campoAutores.setBounds(campoX, y, largura, altura);

        y += espacamento;
        JLabel labelQtd = new JLabel("Quantidade de Cópias:");
        labelQtd.setBounds(labelX, y, 200, altura);
        labelQtd.setFont(fonteLabel);
        JTextField campoQtd = new JTextField();
        campoQtd.setBounds(campoX, y, largura, altura);

        JLabel mensagem = new JLabel("");
        mensagem.setBounds(400, y + 40, 500, 25);
        mensagem.setFont(new Font("Arial", Font.BOLD, 14));

        JButton botaoCadastrar = new JButton("Cadastrar Livro");
        botaoCadastrar.setBounds(400, y + 70, 200, 35);

        JButton botaoVoltar = new JButton("Voltar ao Menu");
        botaoVoltar.setBounds(620, y + 70, 200, 35);
        botaoVoltar.addActionListener(e -> {
            janela.dispose();
            menu();
        });

        botaoCadastrar.addActionListener(e -> {
            String tituloLivro = campoTitulo.getText().trim();
            String isbn = campoIsbn.getText().trim();
            String autoresStr = campoAutores.getText().trim();
            String qtdStr = campoQtd.getText().trim();

            if (tituloLivro.isEmpty() || isbn.isEmpty() || autoresStr.isEmpty() || qtdStr.isEmpty()) {
                mensagem.setText("Por favor, preencha todos os campos.");
                mensagem.setForeground(Color.RED);
                return;
            }

            int qtdCopias;
            try {
                qtdCopias = Integer.parseInt(qtdStr);
                if (qtdCopias <= 0) throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                mensagem.setText("Quantidade de cópias inválida.");
                mensagem.setForeground(Color.RED);
                return;
            }

            String[] nomesAutores = autoresStr.split(",");
            List<Autor> listaAutores = new ArrayList<>();
            for (String nome : nomesAutores) {
                listaAutores.add(new Autor(nome.trim()));
            }

            Livro novoLivro = new Livro(tituloLivro, isbn, qtdCopias, listaAutores.toArray(new Autor[0]));
            livros.salvar(novoLivro);

            mensagem.setText("Livro cadastrado com sucesso!");
            mensagem.setForeground(new Color(0, 128, 0));

            // Limpa os campos
            campoTitulo.setText("");
            campoIsbn.setText("");
            campoAutores.setText("");
            campoQtd.setText("");
        });

        // Adiciona componentes
        janela.add(titulo);
        janela.add(labelTitulo); janela.add(campoTitulo);
        janela.add(labelIsbn); janela.add(campoIsbn);
        janela.add(labelAutores); janela.add(campoAutores);
        janela.add(labelQtd); janela.add(campoQtd);
        janela.add(botaoCadastrar);
        janela.add(botaoVoltar);
        janela.add(mensagem);

        janela.setVisible(true);
    }

    public void cadastrarEmprestimo() {
        JFrame janela = new JFrame("Cadastro de Empréstimos");
        janela.setSize(600, 600);
        janela.setLayout(null);
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel labelMembro = new JLabel("Selecione o Membro:");
        labelMembro.setBounds(50, 30, 200, 25);

        JComboBox<Membro> comboMembros = new JComboBox<>();
        for (Membro m : membros.getMembros()) {
            comboMembros.addItem(m);
        }
        comboMembros.setBounds(50, 60, 300, 25);

        JLabel labelLivros = new JLabel("Selecione o(s) Livro(s):");
        labelLivros.setBounds(50, 100, 200, 25);

        DefaultListModel<domain.model.livro.Livro> modelLivros = new DefaultListModel<>();
        for (domain.model.livro.Livro l : livros.getLivros()) {
            modelLivros.addElement(l);
        }

        JList<domain.model.livro.Livro> listaLivros = new JList<>(modelLivros);
        listaLivros.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollLivros = new JScrollPane(listaLivros);
        scrollLivros.setBounds(50, 130, 400, 200);

        JLabel labelDataInicial = new JLabel("Data Inicial (dd-MM-yyyy):");
        labelDataInicial.setBounds(50, 350, 200, 25);
        JTextField campoDataInicial = new JTextField();
        campoDataInicial.setBounds(250, 350, 100, 25);

        JLabel labelDataFinal = new JLabel("Data Final (dd-MM-yyyy):");
        labelDataFinal.setBounds(50, 380, 200, 25);
        JTextField campoDataFinal = new JTextField();
        campoDataFinal.setBounds(250, 380, 100, 25);

        JButton botaoConfirmar = new JButton("Confirmar Empréstimo");
        botaoConfirmar.setBounds(50, 420, 200, 30);

        JButton botaoSair = new JButton("Sair");
        botaoSair.setBounds(270, 420, 100, 30);
        botaoSair.addActionListener(e -> janela.dispose());

        JLabel mensagem = new JLabel("");
        mensagem.setBounds(50, 460, 500, 25);
        mensagem.setForeground(Color.BLUE);

        botaoConfirmar.addActionListener(e -> {
            Membro membroSelecionado = (Membro) comboMembros.getSelectedItem();
            java.util.List<domain.model.livro.Livro> livrosSelecionados = listaLivros.getSelectedValuesList();

            if (membroSelecionado == null) {
                mensagem.setText("Por favor, selecione um membro.");
                mensagem.setForeground(Color.RED);
                return;
            }

            if (livrosSelecionados.isEmpty()) {
                mensagem.setText("Por favor, selecione pelo menos um livro.");
                mensagem.setForeground(Color.RED);
                return;
            }

            String dataInicialStr = campoDataInicial.getText();
            String dataFinalStr = campoDataFinal.getText();

            java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy");

            try {
                java.time.LocalDate dataInicial = java.time.LocalDate.parse(dataInicialStr, formatter);
                java.time.LocalDate dataFinal = java.time.LocalDate.parse(dataFinalStr, formatter);

                if (dataFinal.isBefore(dataInicial)) {
                    mensagem.setText("A data final deve ser posterior à data inicial.");
                    mensagem.setForeground(Color.RED);
                    return;
                }

                for (domain.model.livro.Livro livro : livrosSelecionados) {
                    if (livro.totalDisponiveis() == 0) {
                        mensagem.setText("O livro \"" + livro.getTitulo() + "\" não possui cópias disponíveis.");
                        mensagem.setForeground(Color.RED);
                        return;
                    }
                }


                // Criação do empréstimo usando a Factory
                domain.model.emprestimo.Emprestimo novoEmprestimo =
                        domain.model.emprestimo.EmprestimoFactory.criarEmprestimo(
                                membroSelecionado, livrosSelecionados, dataInicial, dataFinal
                        );

                // Salvar no repositório
                emprestimos.salvar(novoEmprestimo);

                mensagem.setText("Empréstimo cadastrado com sucesso de " + dataInicialStr + " até " + dataFinalStr + "!");
                mensagem.setForeground(new Color(0, 128, 0));

            } catch (Exception ex) {
                mensagem.setText("Erro: " + ex.getMessage());
                mensagem.setForeground(Color.RED);
            }
        });

        // Adiciona os elementos à janela
        janela.add(labelMembro);
        janela.add(comboMembros);
        janela.add(labelLivros);
        janela.add(scrollLivros);
        janela.add(labelDataInicial);
        janela.add(campoDataInicial);
        janela.add(labelDataFinal);
        janela.add(campoDataFinal);
        janela.add(botaoConfirmar);
        janela.add(botaoSair);
        janela.add(mensagem);

        janela.setLocationRelativeTo(null);
        janela.setVisible(true);
    }
}
