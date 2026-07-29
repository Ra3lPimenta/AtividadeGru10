package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controller.ClienteController;
public class TelaCadastro extends JFrame {
	private static final long serialVersionUID = 1L;
	public JLabel lblID;
	public JLabel lblTitulo;
	public JLabel lblAutor;
	public JLabel lblGenero;
	public JLabel lblIdioma;
	public JLabel lblQuantidade;
	public JLabel lblPreco;

	
	public JTextField txtID;
	public JTextField txtTitulo;
	public JTextField txtAutor;
	public JTextField txtGenero;
	public JTextField txtIdioma;
	public JTextField txtQuantidade;
	public JTextField txtPreco;


	public JButton btnInserir;
	public JButton btnAtualizar;
	public JButton btnExcluir;
	
	private JTable tabela_Pimenta_e_Andrade;
	private DefaultTableModel modeloTabelinha;
	private ClienteController Controller;
	
	public TelaCadastro () {
		setTitle("Cad_Cli");
		setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        criarComponentes();

        Controller = new ClienteController(this);
        configurarEventos();
        Controller.carregarTabela();
	}
    private void criarComponentes() {
        JPanel painelFormulario = new JPanel(new GridLayout(3, 2, 10, 10));
        painelFormulario.setBorder(BorderFactory.createTitledBorder("Dados do Cliente"));

        lblID = new JLabel("ID:");
        txtID = new JTextField();
        txtID.setEditable(false);

        lblTitulo = new JLabel("Título:");
        txtTitulo = new JTextField();

        lblAutor = new JLabel("Autor:");
        txtAutor = new JTextField();
        
        lblGenero = new JLabel("Gênero:");
        txtGenero = new JTextField();
        
        lblIdioma = new JLabel("Idioma:");
        txtIdioma = new JTextField();
        
        lblQuantidade = new JLabel("Quantidade:");
        txtQuantidade = new JTextField();
        
        lblPreco = new JLabel("Preço");
        txtPreco = new JTextField();

        painelFormulario.add(lblID);
        painelFormulario.add(txtID);
        painelFormulario.add(lblTitulo);
        painelFormulario.add(txtTitulo);
        painelFormulario.add(lblAutor);
        painelFormulario.add(txtAutor);
        painelFormulario.add(lblGenero);
        painelFormulario.add(txtGenero);
        painelFormulario.add(lblIdioma);
        painelFormulario.add(txtIdioma);
        painelFormulario.add(lblQuantidade);
        painelFormulario.add(txtQuantidade);
        painelFormulario.add(lblPreco);
        painelFormulario.add(txtPreco);
      
        add(painelFormulario, BorderLayout.NORTH);

        modeloTabelinha = new DefaultTableModel(new Object[] { "ID", "Título", "Autor", "Gênero", "Idioma", "Quantidade", "Preço", }, 0) {
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabela_Pimenta_e_Andrade = new JTable(modeloTabelinha);
        JScrollPane scrollPane = new JScrollPane(tabela_Pimenta_e_Andrade);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Lista de Livros"));
        add(scrollPane, BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));

        btnInserir = new JButton("Inserir");;
        btnAtualizar = new JButton("Atualizar");
        btnExcluir = new JButton("Excluir");

        painelBotoes.add(btnInserir);
        painelBotoes.add(btnAtualizar);
        painelBotoes.add(btnExcluir);

        add(painelBotoes, BorderLayout.SOUTH);
    }

    private void configurarEventos() {
        btnInserir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                Controller.salvar();
            }
        });
        btnAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                Controller.salvar();
            }
        });

        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                Controller.excluir();
            }
        });

        tabela_Pimenta_e_Andrade.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Controller.preencherFormulario();
            }
        });
    }
    public JTable getTabela_Pimenta_e_Andrade() {
        return tabela_Pimenta_e_Andrade;
    }
	public JTextField getTxtID() {
		return txtID;
	}
	public void setTxtID(JTextField txtID) {
		this.txtID = txtID;
	}
	public void setTxtTitulo(JTextField txtTitulo) {
		this.txtTitulo = txtTitulo;
	}
	
	public JTextField getTxtTitulo() {
		return txtTitulo;
	}
	public JTextField getTxtAutor() {
		return txtAutor;
	}
	public void setTxtAutor(JTextField txtAutor) {
		this.txtAutor = txtAutor;
	}
	public JTextField getTxtGenero() {
		return txtGenero;
	}
	public void setTxtGenero(JTextField txtGenero) {
		this.txtGenero = txtGenero;
	}
	public JTextField getTxtIdioma() {
		return txtIdioma;
	}
	public void setTxtIdioma(JTextField txtIdioma) {
		this.txtIdioma = txtIdioma;
	}
	public void setTxtQuantidade(JTextField txtQuantidade) {
		this.txtQuantidade = txtQuantidade;
	}
	public JTextField getTxtQuantidade() {
		return txtQuantidade;
	}

	public void setTxtPreco(JTextField txtPreco) {
		this.txtPreco = txtPreco;
	}
	public JTextField getTxtPreco() {
		return txtPreco;
	}
}
