package view;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import model.Professor;
import model.Disciplina;
import model.Alocacao;
import model.Horario;
import repository.Repositorio;
import service.GradeManager;

public class MainFrame extends JFrame {
    private Repositorio repositorio;
    private GradeManager gradeManager;
    private List<Professor> listaProfessores;
    private List<Disciplina> listaDisciplinas;

    // Componentes principais
    private DefaultTableModel modeloTabela;
    private JTable tabelaGrade;
    private DefaultListModel<String> modelListaProfessores;
    private DefaultListModel<String> modelListaDisciplinas;

    private JComboBox<Professor> comboBoxProfessores;
    private JComboBox<Disciplina> comboBoxDisciplinas;
    private JComboBox<Horario.DiaSemana> comboDias;
    private JComboBox<Horario.Turno> comboTurnos;
    private JComboBox<Horario.Bloco> comboBlocos;

    // Variáveis de controle para saber se estamos editando
    private Professor professorSendoEditado = null;
    private Disciplina disciplinaSendoEditada = null;

    private CardLayout cardLayout;
    private JPanel painelCentral;

    private final Color ROXO_PRINCIPAL = new Color(123, 63, 222);
    private final Color ROXO_CLARO = new Color(243, 239, 255);
    private final Color TEXTO_MENU = Color.WHITE;

    public MainFrame() {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            UIManager.put("Button.arc", 12);
            UIManager.put("Component.arc", 12);
        } catch (Exception e) {
            System.err.println("Não foi possível carregar o tema.");
        }

        this.repositorio = new Repositorio();
        this.gradeManager = new GradeManager();
        this.listaProfessores = repositorio.carregarProfessores();
        this.listaDisciplinas = repositorio.carregarDisciplinas();

        setTitle("Gerenciador de Grade Acadêmica");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painelPrincipal = new JPanel(new BorderLayout());

        JPanel menuLateral = new JPanel();
        menuLateral.setBackground(ROXO_PRINCIPAL);
        menuLateral.setPreferredSize(new Dimension(230, 700));
        menuLateral.setLayout(new BoxLayout(menuLateral, BoxLayout.Y_AXIS));
        menuLateral.setBorder(new EmptyBorder(30, 15, 30, 15));

        JLabel lblLogo = new JLabel("Portal Grade Acadêmica");
        lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblLogo.setForeground(Color.WHITE);
        lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuLateral.add(lblLogo);
        menuLateral.add(Box.createRigidArea(new Dimension(0, 40)));

        JButton btnMenuGeral = criarBotaoMenu("Painel Geral");
        JButton btnMenuProfessores = criarBotaoMenu("Cadastrar Professores");
        JButton btnMenuDisciplinas = criarBotaoMenu("Cadastrar Disciplinas");
        JButton btnMenuMatch = criarBotaoMenu("Realizar Match");

        menuLateral.add(btnMenuGeral);
        menuLateral.add(Box.createRigidArea(new Dimension(0, 10)));
        menuLateral.add(btnMenuProfessores);
        menuLateral.add(Box.createRigidArea(new Dimension(0, 10)));
        menuLateral.add(btnMenuDisciplinas);
        menuLateral.add(Box.createRigidArea(new Dimension(0, 10)));
        menuLateral.add(btnMenuMatch);

        painelPrincipal.add(menuLateral, BorderLayout.WEST);

        cardLayout = new CardLayout();
        painelCentral = new JPanel(cardLayout);
        painelCentral.setBackground(Color.WHITE);

        painelCentral.add(criarAbaGeral(), "Geral");
        painelCentral.add(criarAbaCadastroProfessor(), "Professor");
        painelCentral.add(criarAbaCadastroDisciplina(), "Disciplina");
        painelCentral.add(criarAbaMatch(), "Match");

        painelPrincipal.add(painelCentral, BorderLayout.CENTER);
        add(painelPrincipal);

        btnMenuGeral.addActionListener(e -> cardLayout.show(painelCentral, "Geral"));
        btnMenuProfessores.addActionListener(e -> cardLayout.show(painelCentral, "Professor"));
        btnMenuDisciplinas.addActionListener(e -> cardLayout.show(painelCentral, "Disciplina"));
        btnMenuMatch.addActionListener(e -> cardLayout.show(painelCentral, "Match"));

        atualizarListasInterface();
    }

    private JButton criarBotaoMenu(String texto) {
        JButton btn = new JButton(texto);
        btn.setMaximumSize(new Dimension(200, 45));
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setForeground(TEXTO_MENU);
        btn.setBackground(ROXO_PRINCIPAL);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private JPanel criarAbaGeral() {
        JPanel painel = new JPanel(new BorderLayout(20, 20));
        painel.setBackground(Color.WHITE);
        painel.setBorder(new EmptyBorder(30, 30, 30, 30));

        JLabel titulo = new JLabel("Grade Horário Semestral");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titulo.setForeground(new Color(50, 50, 50));
        painel.add(titulo, BorderLayout.NORTH);

        String[] colunas = {"Horário / Dia", "Segunda", "Terça", "Quarta", "Quinta", "Sexta"};
        modeloTabela = new DefaultTableModel(null, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        modeloTabela.addRow(new Object[]{"08:30 - 10:20", "", "", "", "", ""});
        modeloTabela.addRow(new Object[]{"10:30 - 12:20", "", "", "", "", ""});
        modeloTabela.addRow(new Object[]{"13:30 - 15:20", "", "", "", "", ""});
        modeloTabela.addRow(new Object[]{"15:30 - 17:20", "", "", "", "", ""});
        modeloTabela.addRow(new Object[]{"18:30 - 20:20", "", "", "", "", ""});
        modeloTabela.addRow(new Object[]{"20:30 - 22:20", "", "", "", "", ""});

        tabelaGrade = new JTable(modeloTabela);
        tabelaGrade.setRowHeight(60); 
        tabelaGrade.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tabelaGrade.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tabelaGrade.getTableHeader().setBackground(ROXO_CLARO);
        tabelaGrade.setGridColor(new Color(235, 235, 235));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tabelaGrade.getColumnCount(); i++) {
            tabelaGrade.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scroll = new JScrollPane(tabelaGrade);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230), 1));
        painel.add(scroll, BorderLayout.CENTER);

        JButton btnAtualizar = new JButton("Atualizar");
        btnAtualizar.setBackground(ROXO_PRINCIPAL);
        btnAtualizar.setForeground(Color.WHITE);
        btnAtualizar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnAtualizar.setPreferredSize(new Dimension(200, 45));
        
        btnAtualizar.addActionListener(e -> recarregarMatrizGradeVisual());

        JPanel painelBotao = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        painelBotao.setBackground(Color.WHITE);
        painelBotao.add(btnAtualizar);
        painel.add(painelBotao, BorderLayout.SOUTH);

        return painel;
    }

    private void recarregarMatrizGradeVisual() {
        for (int row = 0; row < modeloTabela.getRowCount(); row++) {
            for (int col = 1; col < modeloTabela.getColumnCount(); col++) {
                modeloTabela.setValueAt("", row, col);
            }
        }

        List<Alocacao> alocacoesAtuais = gradeManager.getGradeAlocada();
        
        for (Alocacao aloc : alocacoesAtuais) {
            int colunaDia = obterColunaPorDia(aloc.getHorario().getDiaSemana());
            int linhaTurno = obterLinhaPorTurno(aloc.getHorario().getTurno(), aloc.getHorario().getBloco());
            
            if (colunaDia != -1 && linhaTurno != -1) {
                String conteudo = "<html><center><b>" + aloc.getDisciplina().getNome() + "</b><br><font color='#7B3FFE'>" + aloc.getProfessor().getNome() + "</font></center></html>";
                modeloTabela.setValueAt(conteudo, linhaTurno, colunaDia);
            }
        }
    }

    private int obterColunaPorDia(Horario.DiaSemana dia) {
        switch (dia) {
            case SEGUNDA: return 1;
            case TERCA: return 2;
            case QUARTA: return 3;
            case QUINTA: return 4;
            case SEXTA: return 5;
            default: return -1;
        }
    }

    private int obterLinhaPorTurno(Horario.Turno turno, Horario.Bloco bloco) {
        switch (turno) {
            case MANHA: 
                return (bloco == Horario.Bloco.PRIMEIRO) ? 0 : 1; 
            case TARDE: 
                return (bloco == Horario.Bloco.PRIMEIRO) ? 2 : 3;
            case NOITE: 
                return (bloco == Horario.Bloco.PRIMEIRO) ? 4 : 5;
            default: return -1;
        }
    }

    // --- ABA 2: CADASTRO PROFESSOR ---
    private JPanel criarAbaCadastroProfessor() {
        JPanel painel = new JPanel(new BorderLayout(20, 20));
        painel.setBackground(Color.WHITE);
        painel.setBorder(new EmptyBorder(30, 30, 30, 30));

        JPanel form = new JPanel(new GridLayout(2, 2, 10, 10));
        form.setBackground(Color.WHITE);
        form.add(new JLabel("Nome do Professor:"));
        JTextField txtNome = new JTextField();
        form.add(txtNome);
        form.add(new JLabel("Área de Atuação (separadas por vírgula):"));
        JTextField txtArea = new JTextField();
        form.add(txtArea);
        painel.add(form, BorderLayout.NORTH);

        modelListaProfessores = new DefaultListModel<>();
        JList<String> listaVisual = new JList<>(modelListaProfessores);
        listaVisual.setSelectionBackground(ROXO_CLARO);
        painel.add(new JScrollPane(listaVisual), BorderLayout.CENTER);

        // Painel inferior para abrigar os botões de Salvar e Editar
        JPanel painelAcoes = new JPanel(new GridLayout(1, 2, 10, 0));
        painelAcoes.setBackground(Color.WHITE);
        painelAcoes.setPreferredSize(new Dimension(0, 45));

        JButton btnEditar = new JButton("✏️ Editar Selecionado");
        btnEditar.setBackground(new Color(240, 240, 240));
        btnEditar.setForeground(Color.BLACK);

        JButton btnSalvar = new JButton("Salvar Professor");
        btnSalvar.setBackground(ROXO_PRINCIPAL);
        btnSalvar.setForeground(Color.WHITE);

        painelAcoes.add(btnEditar);
        painelAcoes.add(btnSalvar);
        painel.add(painelAcoes, BorderLayout.SOUTH);

        // Evento do botão Editar
        btnEditar.addActionListener(e -> {
            int index = listaVisual.getSelectedIndex();
            if (index != -1) {
                professorSendoEditado = listaProfessores.get(index);
                txtNome.setText(professorSendoEditado.getNome());
                txtArea.setText(String.join(",", professorSendoEditado.getAreasConhecimento()));
                btnSalvar.setText("💾 Confirmar Alterações");
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um professor na lista para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });

        // Evento do botão Salvar (Modificado para suportar Update)
        btnSalvar.addActionListener(e -> {
            try {
                String nome = txtNome.getText();
                String area = txtArea.getText();
                
                Professor novoProf = new Professor(nome, Arrays.asList(area.split(",")));
                
                if (professorSendoEditado != null) {
                    // Substitui o professor antigo na lista pelo novo editado
                    int index = listaProfessores.indexOf(professorSendoEditado);
                    listaProfessores.set(index, novoProf);
                    professorSendoEditado = null; // Limpa o estado de edição
                    btnSalvar.setText("Salvar Professor");
                    JOptionPane.showMessageDialog(this, "Professor atualizado com sucesso!");
                } else {
                    listaProfessores.add(novoProf);
                    JOptionPane.showMessageDialog(this, "Professor cadastrado com sucesso!");
                }
                
                repositorio.alvarProfessores(listaProfessores);
                atualizarListasInterface();
                
                txtNome.setText("");
                txtArea.setText("");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.WARNING_MESSAGE);
            }
        });

        return painel;
    }

    // --- ABA 3: CADASTRO DISCIPLINA ---
    private JPanel criarAbaCadastroDisciplina() {
        JPanel painel = new JPanel(new BorderLayout(20, 20));
        painel.setBackground(Color.WHITE);
        painel.setBorder(new EmptyBorder(30, 30, 30, 30));

        JPanel form = new JPanel(new GridLayout(4, 2, 10, 10));
        form.setBackground(Color.WHITE);
        form.add(new JLabel("Código:"));
        JTextField txtCodigo = new JTextField();
        form.add(txtCodigo);
        form.add(new JLabel("Nome:"));
        JTextField txtNome = new JTextField();
        form.add(txtNome);
        form.add(new JLabel("Área Curricular:"));
        JTextField txtArea = new JTextField();
        form.add(txtArea);
        form.add(new JLabel("Carga Horária (h):"));
        JTextField txtCarga = new JTextField();
        form.add(txtCarga);
        painel.add(form, BorderLayout.NORTH);

        modelListaDisciplinas = new DefaultListModel<>();
        JList<String> listaVisual = new JList<>(modelListaDisciplinas);
        listaVisual.setSelectionBackground(ROXO_CLARO);
        painel.add(new JScrollPane(listaVisual), BorderLayout.CENTER);

        // Painel inferior de ações para Disciplinas
        JPanel painelAcoes = new JPanel(new GridLayout(1, 2, 10, 0));
        painelAcoes.setBackground(Color.WHITE);
        painelAcoes.setPreferredSize(new Dimension(0, 45));

        JButton btnEditar = new JButton("Editar Selecionada");
        btnEditar.setBackground(new Color(240, 240, 240));
        btnEditar.setForeground(Color.BLACK);

        JButton btnSalvar = new JButton("Salvar Disciplina");
        btnSalvar.setBackground(ROXO_PRINCIPAL);
        btnSalvar.setForeground(Color.WHITE);

        painelAcoes.add(btnEditar);
        painelAcoes.add(btnSalvar);
        painel.add(painelAcoes, BorderLayout.SOUTH);

        // Evento do botão Editar Disciplina
        btnEditar.addActionListener(e -> {
            int index = listaVisual.getSelectedIndex();
            if (index != -1) {
                disciplinaSendoEditada = listaDisciplinas.get(index);
                txtCodigo.setText(disciplinaSendoEditada.getCodigo());
                txtNome.setText(disciplinaSendoEditada.getNome());
                txtArea.setText(disciplinaSendoEditada.getArea());
                txtCarga.setText(String.valueOf(disciplinaSendoEditada.getCargaHoraria()));
                btnSalvar.setText("Confirmar Alterações");
            } else {
                JOptionPane.showMessageDialog(this, "Selecione uma disciplina na lista para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });

        // Evento do botão Salvar Disciplina (Modificado para suportar Update)
        btnSalvar.addActionListener(e -> {
            try {
                String codigo = txtCodigo.getText();
                String nome = txtNome.getText();
                String area = txtArea.getText();
                int carga = Integer.parseInt(txtCarga.getText().trim());

                Disciplina novaDisc = new Disciplina(codigo, nome, area, carga);
                
                if (disciplinaSendoEditada != null) {
                    int index = listaDisciplinas.indexOf(disciplinaSendoEditada);
                    listaDisciplinas.set(index, novaDisc);
                    disciplinaSendoEditada = null; // Limpa o estado de edição
                    btnSalvar.setText("Salvar Disciplina");
                    JOptionPane.showMessageDialog(this, "Disciplina atualizada com sucesso!");
                } else {
                    listaDisciplinas.add(novaDisc);
                    JOptionPane.showMessageDialog(this, "Disciplina cadastrada com sucesso");
                }
                
                repositorio.salvarDisciplinas(listaDisciplinas);
                atualizarListasInterface();
                
                txtCodigo.setText("");
                txtNome.setText("");
                txtArea.setText("");
                txtCarga.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro nos dados informados.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        return painel;
    }

    private JPanel criarAbaMatch() {
        JPanel painel = new JPanel(new GridLayout(11, 1, 6, 6)); 
        painel.setBackground(Color.WHITE);
        painel.setBorder(new EmptyBorder(30, 60, 30, 60));

        painel.add(new JLabel("<b>Selecione o Professor:</b>"));
        comboBoxProfessores = new JComboBox<>(); 
        painel.add(comboBoxProfessores);

        painel.add(new JLabel("<b>Selecione a Disciplina:</b>"));
        comboBoxDisciplinas = new JComboBox<>(); 
        painel.add(comboBoxDisciplinas);

        painel.add(new JLabel("<b>Selecione o Dia da Semana:</b>"));
        comboDias = new JComboBox<>(Horario.DiaSemana.values());
        painel.add(comboDias);

        painel.add(new JLabel("<b>Selecione o Turno:</b>"));
        comboTurnos = new JComboBox<>(Horario.Turno.values());
        painel.add(comboTurnos);

        painel.add(new JLabel("<b>Selecione o Horário do Turno:</b>"));
        comboBlocos = new JComboBox<>(Horario.Bloco.values());
        painel.add(comboBlocos);

        JButton btnAlocar = new JButton("🤝 Confirmar Match e Alocação");
        btnAlocar.setBackground(ROXO_PRINCIPAL);
        btnAlocar.setForeground(Color.WHITE);
        btnAlocar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        painel.add(btnAlocar);

        btnAlocar.addActionListener(e -> {
            Professor p = (Professor) comboBoxProfessores.getSelectedItem(); 
            Disciplina d = (Disciplina) comboBoxDisciplinas.getSelectedItem(); 
            Horario.DiaSemana dia = (Horario.DiaSemana) comboDias.getSelectedItem();
            Horario.Turno turno = (Horario.Turno) comboTurnos.getSelectedItem();
            Horario.Bloco bloco = (Horario.Bloco) comboBlocos.getSelectedItem();

            if (p != null && d != null) {
                try {
                    Horario h = new Horario(dia, turno, bloco);
                    gradeManager.tentarAlocar(p, d, h); 
                    JOptionPane.showMessageDialog(this, "🤝 Match registrado com sucesso!\nVá ao Painel Geral e clique em Atualizar.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                } catch (RuntimeException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Alerta de Validação", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        return painel;
    }

    private void atualizarListasInterface() {
        if (modelListaProfessores != null) {
            modelListaProfessores.clear();
            for (Professor p : listaProfessores) {
                modelListaProfessores.addElement(p.getNome() + " - Áreas: " + p.getAreasConhecimento());
            }
        }
        if (modelListaDisciplinas != null) {
            modelListaDisciplinas.clear();
            for (Disciplina d : listaDisciplinas) {
                modelListaDisciplinas.addElement(d.toString());
            }
        }
        if (comboBoxProfessores != null && comboBoxDisciplinas != null) { 
            comboBoxProfessores.removeAllItems();
            comboBoxDisciplinas.removeAllItems();
            for (Professor p : listaProfessores) comboBoxProfessores.addItem(p);
            for (Disciplina d : listaDisciplinas) comboBoxDisciplinas.addItem(d);
        }
    }
}