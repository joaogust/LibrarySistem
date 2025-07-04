package ui.app;
import domain.model.emprestimo.Emprestimo;
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
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        janela.setLocationRelativeTo(null);
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

        // Mensagem de erro
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
        janela.setSize(400, 350);
        janela.setLayout(null);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLocationRelativeTo(null);
        janela.setResizable(false);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = LocalDate.now().format(formatter);
        JLabel dataHoje = new JLabel(dataFormatada);
        dataHoje.setBounds(275, 35, 160, 20);
        dataHoje.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel usuario = new JLabel("Usuário: Admin");
        usuario.setBounds(50, 35, 200, 20);
        usuario.setFont(new Font("Arial", Font.PLAIN, 14));

        int larguraBotao = 300;
        int alturaBotao = 35;
        int xPos = (janela.getWidth() - larguraBotao) / 2;
        int yInicial = 85;
        int espacamento = 50;

        JButton buttonMembros = new JButton("Lista de Membros");
        buttonMembros.setBounds(xPos, yInicial, larguraBotao, alturaBotao);
        buttonMembros.addActionListener(e -> listaDeMembros());

        JButton buttonLivros = new JButton("Lista de Livros");
        buttonLivros.setBounds(xPos, yInicial + espacamento, larguraBotao, alturaBotao);
        buttonLivros.addActionListener(e -> listaDeLivros());

        JButton buttonEmprestimos = new JButton("Lista de Empréstimos");
        buttonEmprestimos.setBounds(xPos, yInicial + espacamento * 2, larguraBotao, alturaBotao);
        buttonEmprestimos.addActionListener(e -> listaDeEmprestimos());

        JButton sair = new JButton("Sair");
        sair.setBounds(xPos, yInicial + espacamento * 3, larguraBotao, alturaBotao);
        sair.addActionListener(e -> {
            janela.dispose();
            login();
        });

        janela.add(dataHoje);
        janela.add(usuario);
        janela.add(buttonMembros);
        janela.add(buttonLivros);
        janela.add(buttonEmprestimos);
        janela.add(sair);
        janela.setVisible(true);
    }

    public void listaDeMembros() {
        JFrame janela = new JFrame("Lista de Membros");
        janela.setSize(1000, 600);
        janela.setLayout(null);
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        janela.setLocationRelativeTo(null);

        JLabel titulo = new JLabel("Lista de Membros");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBounds(390, 20, 300, 30);

        String[] colunas = {"ID", "Nome", "Telefone", "CPF", "Endereço"};
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0);

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

        JButton cadastrar = new JButton("Cadastrar Membro");
        cadastrar.setBounds(180, 400, 200, 30);
        cadastrar.addActionListener(e -> cadastrarMembro());

        JButton alterar = new JButton("Alterar Membro");
        alterar.setBounds(400, 400, 200, 30);
        alterar.addActionListener(e -> {
            int linhaSelecionada = tabela.getSelectedRow();
            if (linhaSelecionada == -1) {
                JOptionPane.showMessageDialog(janela, "Selecione um membro da tabela.");
                return;
            }
            int idSelecionado = Integer.parseInt((String) tabela.getValueAt(linhaSelecionada, 0));
            Membro membro = membros.buscarPorId(idSelecionado);
            if (membro != null) {
                alterarMembro(membro);
            } else {
                JOptionPane.showMessageDialog(janela, "Membro não encontrado.");
            }
        });

        // Botão Voltar ao Menu
        JButton voltar = new JButton("Voltar");
        voltar.setBounds(620, 400, 200, 30);
        voltar.addActionListener(e -> janela.dispose());

        janela.add(cadastrar);
        janela.add(titulo);
        janela.add(painel);
        janela.add(alterar);
        janela.add(voltar);
        janela.setVisible(true);
    }

    public void listaDeLivros() {
        JFrame janela = new JFrame("Lista de Livros");
        janela.setSize(900, 500);
        janela.setLayout(null);
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel titulo = new JLabel("Lista de Livros");
        titulo.setBounds(330, 20, 300, 30);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));

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
        tabela.getColumnModel().getColumn(0).setPreferredWidth(40);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(180);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(150);
        tabela.getColumnModel().getColumn(3).setPreferredWidth(200);
        tabela.getColumnModel().getColumn(4).setPreferredWidth(100);
        tabela.getColumnModel().getColumn(5).setPreferredWidth(100);

        JScrollPane painel = new JScrollPane(tabela);
        painel.setBounds(50, 70, 780, 300);

        JButton cadastrar = new JButton("Cadastrar Livro");
        cadastrar.setBounds(180, 390, 160, 30);
        cadastrar.addActionListener(e -> cadastrarLivro());

        JButton alterar = new JButton("Alterar Livro");
        alterar.setBounds(360, 390, 160, 30);
        alterar.addActionListener(e -> {
            int linha = tabela.getSelectedRow();
            if (linha == -1) {
                JOptionPane.showMessageDialog(janela, "Selecione um livro para alterar.");
                return;
            }

            int idLivro = Integer.parseInt((String) tabela.getValueAt(linha, 0));
            for (domain.model.livro.Livro l : livros.getLivros()) {
                if (l.getId() == idLivro) {
                    alterarLivro(l);
                    break;
                }
            }
        });

        JButton voltar = new JButton("Voltar");
        voltar.setBounds(540, 390, 120, 30);
        voltar.addActionListener(e -> janela.dispose());

        janela.add(cadastrar);
        janela.add(alterar);
        janela.add(titulo);
        janela.add(painel);
        janela.add(voltar);

        janela.setLocationRelativeTo(null);
        janela.setVisible(true);
    }

    public void listaDeEmprestimos() {
        JFrame janela = new JFrame("Lista de Empréstimos");
        janela.setSize(850, 550);
        janela.setLayout(null);
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel titulo = new JLabel("Empréstimos Registrados");
        titulo.setBounds(270, 20, 400, 30);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));

        DefaultTableModel modelo = new DefaultTableModel(
                new String[]{"Membro", "Livros", "Início", "Fim", "Atrasado?", "Finalizado?", "Multa (R$)"}, 0
        );

        for (var e : emprestimos.getEmprestimos()) {
            e.estaAtrasado();

            String livros = e.getCopias().size() + " livro(s)";
            String atrasado = e.getEstado().estaAtrasado() ? "Sim" : "Não";
            String finalizado = e.isFinalizado() ? "Sim" : "Não";
            double multaValor = e.calcularMulta();

            String multaFormatada = String.format("%.2f", multaValor);

            modelo.addRow(new String[]{
                    e.getMembro().getNome(),
                    livros,
                    e.getDataInicial().toString(),
                    e.getDataFinal().toString(),
                    atrasado,
                    finalizado,
                    multaFormatada
            });
        }

        JTable tabela = new JTable(modelo);
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabela.getColumnModel().getColumn(0).setPreferredWidth(150); // Membro
        tabela.getColumnModel().getColumn(1).setPreferredWidth(100); // Livros
        tabela.getColumnModel().getColumn(2).setPreferredWidth(100); // Início
        tabela.getColumnModel().getColumn(3).setPreferredWidth(100); // Fim
        tabela.getColumnModel().getColumn(4).setPreferredWidth(100); // Atrasado
        tabela.getColumnModel().getColumn(5).setPreferredWidth(100); // Finalizado
        tabela.getColumnModel().getColumn(6).setPreferredWidth(100); // Multa

        JScrollPane painel = new JScrollPane(tabela);
        painel.setBounds(50, 70, 730, 300);

        int botaoLargura = 200;
        int botaoAltura = 30;
        int yBotoes = 400;

        JButton cadastrar = new JButton("Cadastrar Empréstimo");
        cadastrar.setBounds(70, yBotoes, botaoLargura, botaoAltura);
        cadastrar.addActionListener(e -> cadastrarEmprestimo());

        JButton finalizar = new JButton("Finalizar Empréstimo");
        finalizar.setBounds(325, yBotoes, botaoLargura, botaoAltura);
        finalizar.addActionListener(e -> {
            int linhaSelecionada = tabela.getSelectedRow();
            if (linhaSelecionada == -1) {
                JOptionPane.showMessageDialog(janela, "Selecione um empréstimo para finalizar.");
                return;
            }

            String nomeMembro = (String) tabela.getValueAt(linhaSelecionada, 0);
            String dataInicioStr = (String) tabela.getValueAt(linhaSelecionada, 2);

            for (Emprestimo emp : emprestimos.getEmprestimos()) {
                if (emp.getMembro().getNome().equals(nomeMembro) &&
                        emp.getDataInicial().toString().equals(dataInicioStr) &&
                        !emp.isFinalizado()) {

                    emp.finalizar();
                    JOptionPane.showMessageDialog(janela, "Empréstimo finalizado com sucesso.");
                    janela.dispose();
                    listaDeEmprestimos(); // reabre atualizada
                    return;
                }
            }

            JOptionPane.showMessageDialog(janela, "Empréstimo já finalizado ou não encontrado.");
        });

        JButton voltar = new JButton("Voltar");
        voltar.setBounds(580, yBotoes, botaoLargura, botaoAltura);
        voltar.addActionListener(e -> janela.dispose());

        janela.add(titulo);
        janela.add(painel);
        janela.add(cadastrar);
        janela.add(finalizar);
        janela.add(voltar);

        janela.setLocationRelativeTo(null);
        janela.setVisible(true);
    }

    public void cadastrarMembro() {
        JFrame janela = new JFrame("Cadastrar Membro");
        janela.setSize(600, 600);
        janela.setLayout(null);
        janela.setLocationRelativeTo(null);
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        janela.setResizable(false);

        JLabel titulo = new JLabel("Cadastro de Membro");
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setBounds(180, 10, 300, 30);

        Font fonteLabel = new Font("Arial", Font.PLAIN, 14);
        int labelX = 40;
        int campoX = 180;
        int largura = 350;
        int altura = 25;
        int y = 60;
        int espacamento = 35;

        // Campos
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
        mensagem.setBounds(40, y + 35, 500, 25);
        mensagem.setFont(new Font("Arial", Font.BOLD, 14));

        JButton botaoCadastrar = new JButton("Cadastrar");
        botaoCadastrar.setBounds(130, y + 70, 140, 30);

        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setBounds(310, y + 70, 140, 30);
        botaoVoltar.addActionListener(e -> janela.dispose());

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

            mensagem.setText("Membro cadastrado com sucesso!");
            mensagem.setForeground(new Color(0, 128, 0));

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

        // Adiciona os componentes
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

    public void alterarMembro(Membro membroSelecionado) {
        JFrame janela = new JFrame("Alterar Membro");
        janela.setSize(500, 500);
        janela.setLayout(null);
        janela.setLocationRelativeTo(null);
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel labelTitulo = new JLabel("Alterar Dados do Membro");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        labelTitulo.setBounds(130, 10, 300, 30);

        int y = 50;
        int espacamento = 35;

        JLabel nomeLabel = new JLabel("Nome:");
        nomeLabel.setBounds(30, y, 150, 25);
        JTextField nomeField = new JTextField(membroSelecionado.getNome());
        nomeField.setBounds(180, y, 250, 25);

        y += espacamento;
        JLabel telefoneLabel = new JLabel("Telefone:");
        telefoneLabel.setBounds(30, y, 150, 25);
        JTextField telefoneField = new JTextField(membroSelecionado.getTelefone());
        telefoneField.setBounds(180, y, 250, 25);

        Endereco end = membroSelecionado.getEndereco();

        y += espacamento;
        JLabel ruaLabel = new JLabel("Rua:");
        ruaLabel.setBounds(30, y, 150, 25);
        JTextField ruaField = new JTextField(end.getRua());
        ruaField.setBounds(180, y, 250, 25);

        y += espacamento;
        JLabel numeroLabel = new JLabel("Número:");
        numeroLabel.setBounds(30, y, 150, 25);
        JTextField numeroField = new JTextField(String.valueOf(end.getNumero()));
        numeroField.setBounds(180, y, 250, 25);

        y += espacamento;
        JLabel bairroLabel = new JLabel("Bairro:");
        bairroLabel.setBounds(30, y, 150, 25);
        JTextField bairroField = new JTextField(end.getBairro());
        bairroField.setBounds(180, y, 250, 25);

        y += espacamento;
        JLabel cidadeLabel = new JLabel("Cidade:");
        cidadeLabel.setBounds(30, y, 150, 25);
        JTextField cidadeField = new JTextField(end.getCidade());
        cidadeField.setBounds(180, y, 250, 25);

        y += espacamento;
        JLabel estadoLabel = new JLabel("Estado:");
        estadoLabel.setBounds(30, y, 150, 25);
        JTextField estadoField = new JTextField(end.getEstado());
        estadoField.setBounds(180, y, 250, 25);

        y += espacamento;
        JLabel cepLabel = new JLabel("CEP:");
        cepLabel.setBounds(30, y, 150, 25);
        JTextField cepField = new JTextField(end.getCEP());
        cepField.setBounds(180, y, 250, 25);

        JButton salvar = new JButton("Salvar Alterações");
        salvar.setBounds(150, y + 50, 200, 30);
        salvar.addActionListener(e -> {
            try {
                String nome = nomeField.getText();
                String telefone = telefoneField.getText();
                String rua = ruaField.getText();
                String bairro = bairroField.getText();
                String cidade = cidadeField.getText();
                String estado = estadoField.getText();
                String cep = cepField.getText();
                int numero = Integer.parseInt(numeroField.getText());

                Endereco novoEndereco = new Endereco(rua, bairro, cidade, estado, numero, cep);
                membroSelecionado.setNome(nome);
                membroSelecionado.setTelefone(telefone);
                membroSelecionado.setEndereco(novoEndereco);

                JOptionPane.showMessageDialog(janela, "Membro alterado com sucesso!");
                janela.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(janela, "Erro ao alterar membro: " + ex.getMessage());
            }
        });

        janela.add(labelTitulo);
        janela.add(nomeLabel); janela.add(nomeField);
        janela.add(telefoneLabel); janela.add(telefoneField);
        janela.add(ruaLabel); janela.add(ruaField);
        janela.add(numeroLabel); janela.add(numeroField);
        janela.add(bairroLabel); janela.add(bairroField);
        janela.add(cidadeLabel); janela.add(cidadeField);
        janela.add(estadoLabel); janela.add(estadoField);
        janela.add(cepLabel); janela.add(cepField);
        janela.add(salvar);

        janela.setVisible(true);
    }

    public void alterarLivro(Livro livroSelecionado) {
        JFrame janela = new JFrame("Alterar Livro");
        janela.setSize(800, 400);
        janela.setLayout(null);
        janela.setLocationRelativeTo(null);
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        janela.setResizable(false);

        JLabel titulo = new JLabel("Alterar Livro");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBounds(300, 20, 250, 30);

        Font fonteLabel = new Font("Arial", Font.PLAIN, 16);
        int labelX = 100;
        int campoX = 270;
        int largura = 400;
        int altura = 30;
        int y = 70;
        int espacamento = 40;

        JLabel labelTitulo = new JLabel("Título:");
        labelTitulo.setBounds(labelX, y, 150, altura);
        labelTitulo.setFont(fonteLabel);
        JTextField campoTitulo = new JTextField(livroSelecionado.getTitulo());
        campoTitulo.setBounds(campoX, y, largura, altura);

        y += espacamento;
        JLabel labelIsbn = new JLabel("ISBN:");
        labelIsbn.setBounds(labelX, y, 150, altura);
        labelIsbn.setFont(fonteLabel);
        JTextField campoIsbn = new JTextField(livroSelecionado.getISBN());
        campoIsbn.setBounds(campoX, y, largura, altura);

        y += espacamento;
        JLabel labelAutores = new JLabel("Autor(es) (separados ','):");
        labelAutores.setBounds(labelX, y, 200, altura);
        labelAutores.setFont(fonteLabel);
        StringBuilder autoresStr = new StringBuilder();
        for (Autor autor : livroSelecionado.getAutores()) {
            autoresStr.append(autor.toString()).append(",");
        }
        JTextField campoAutores = new JTextField(autoresStr.toString());
        campoAutores.setBounds(campoX, y, largura, altura);

        JLabel mensagem = new JLabel("");
        mensagem.setBounds(200, y + 40, 400, 25);
        mensagem.setFont(new Font("Arial", Font.BOLD, 14));
        mensagem.setHorizontalAlignment(SwingConstants.CENTER);

        JButton botaoSalvar = new JButton("Salvar Alterações");
        botaoSalvar.setBounds(200, y + 80, 180, 35);
        botaoSalvar.addActionListener(e -> {
            String tituloLivro = campoTitulo.getText().trim();
            String isbn = campoIsbn.getText().trim();
            String autoresInput = campoAutores.getText().trim();

            if (tituloLivro.isEmpty() || isbn.isEmpty() || autoresInput.isEmpty()) {
                mensagem.setText("Preencha todos os campos.");
                mensagem.setForeground(Color.RED);
                return;
            }

            String[] nomesAutores = autoresInput.split(",");
            ArrayList<Autor> listaAutores = new ArrayList<>();
            for (String nome : nomesAutores) {
                listaAutores.add(new Autor(nome.trim()));
            }

            livroSelecionado.setTitulo(tituloLivro);
            livroSelecionado.setISBN(isbn);
            livroSelecionado.setAutores(listaAutores);

            mensagem.setText("Livro alterado com sucesso!");
            mensagem.setForeground(new Color(0, 128, 0));
        });

        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setBounds(420, y + 80, 180, 35);
        botaoVoltar.addActionListener(e -> janela.dispose());

        janela.add(titulo);
        janela.add(labelTitulo); janela.add(campoTitulo);
        janela.add(labelIsbn); janela.add(campoIsbn);
        janela.add(labelAutores); janela.add(campoAutores);
        janela.add(botaoSalvar); janela.add(botaoVoltar);
        janela.add(mensagem);

        janela.setVisible(true);
    }

    public void cadastrarLivro() {
        JFrame janela = new JFrame("Cadastrar Livro");
        janela.setSize(800, 500); // tamanho reduzido
        janela.setLayout(null);
        janela.setLocationRelativeTo(null);
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        janela.setResizable(false);

        JLabel titulo = new JLabel("Cadastro de Livro");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBounds(270, 20, 400, 30); // centralizado

        Font fonteLabel = new Font("Arial", Font.PLAIN, 16);
        int labelX = 100;
        int campoX = 300;
        int largura = 350;
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
        mensagem.setBounds(200, y + 40, 500, 25);
        mensagem.setFont(new Font("Arial", Font.BOLD, 14));

        JButton botaoCadastrar = new JButton("Cadastrar Livro");
        botaoCadastrar.setBounds(200, y + 80, 180, 35);

        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setBounds(400, y + 80, 180, 35);
        botaoVoltar.addActionListener(e -> janela.dispose());

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
