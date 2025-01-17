package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.joda.time.DateTime;

import controller.HospedeController;
import controller.ReservaController;
import model.Hospede;
import model.Reserva;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;



@SuppressWarnings("serial")
public class Buscar extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHospedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHospedes;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;

	private ReservaController reservaController;
	private HospedeController hospedeController;

	private int tabelaSelecionada;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Buscar frame = new Buscar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Buscar() {
		this.reservaController = new ReservaController();
		this.hospedeController = new HospedeController();

		setIconImage(Toolkit.getDefaultToolkit().getImage(Buscar.class.getResource("/imagenes/lOGO-50PX.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		
		JLabel lblTitulo = new JLabel("SISTEMA DE BUSCA");
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblTitulo.setBounds(331, 62, 280, 42);
		contentPane.add(lblTitulo);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);
		// aqui declaramos uma nova tabela
		// e sobre escreve o metodo isCellEditable passando quais celulas o usuario podera editar		
		tbReservas = new JTable() {
			@Override
			public boolean isCellEditable(int row, int column) {                
				return column == 1 || column == 2 || column == 3 || column == 4;               
			};
		};
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Data Check In");
		modelo.addColumn("Data Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Buscar.class.getResource("/imagenes/reservado.png")), scroll_table, null);
		scroll_table.setVisible(true);


		tbHospedes = new JTable() {
			@Override
			public boolean isCellEditable(int row, int column) {                
				return column == 1 || column == 2 || column == 3 || column == 4 || column == 5 ;               
			};
		};
		tbHospedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHospedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHospedes = (DefaultTableModel) tbHospedes.getModel();
		modeloHospedes.addColumn("Numero de Hóspede");
		modeloHospedes.addColumn("Nome");
		modeloHospedes.addColumn("Sobrenome");
		modeloHospedes.addColumn("Data de Nascimento");
		modeloHospedes.addColumn("Nacionalidade");
		modeloHospedes.addColumn("Telefone");
		modeloHospedes.addColumn("Numero de Reserva");
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHospedes);
		panel.addTab("Huéspedes", new ImageIcon(Buscar.class.getResource("/imagenes/pessoas.png")), scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Buscar.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { // Quando o usuário passa o mouse sobre o botão, ele muda de cor
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Quando o usuário remove o mouse do botão, ele retornará ao estado original
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);
		
		JPanel btnbuscar = new JPanel();
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel btnEditar = new JPanel();
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);
		
		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		
		JPanel btnDeletar = new JPanel();
		btnDeletar.setLayout(null);
		btnDeletar.setBackground(new Color(12, 138, 199));
		btnDeletar.setBounds(767, 508, 122, 35);
		btnDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnDeletar);
		
		JLabel lblExcluir = new JLabel("DELETAR");
		lblExcluir.setHorizontalAlignment(SwingConstants.CENTER);
		lblExcluir.setForeground(Color.WHITE);
		lblExcluir.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblExcluir.setBounds(0, 0, 122, 35);
		btnDeletar.add(lblExcluir);
		setResizable(false);

		//metodo para saber qual tabela o usuario esta vendo.
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				tabelaSelecionada = panel.getSelectedIndex();
			}
		});

		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				limparTabela();
				//logica para saber qual metodo de qual tabela chamar
				switch (tabelaSelecionada) {
					case 0:
						if (txtBuscar.getText().isEmpty()) {
							preencherTabela(null);
						} else if (txtBuscar.getText().matches("[0-9]*")) {
							preencherTabela(Integer.parseInt(txtBuscar.getText()));
						} else {
							JOptionPane.showMessageDialog(null, "Numero da Reserva deve ser um NUMERO");
						}
						break;
					case 1:
						if (txtBuscar.getText().isEmpty()) {
							preencherTabelaHospede(null);
						} else {
							preencherTabelaHospede(txtBuscar.getText());
						}
						break;
				}
			}
		});

		btnDeletar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {

			switch (tabelaSelecionada) {
				case 0:
					deletarReserva();
					limparTabela();
					preencherTabela(null);
					break;
				case 1:
					deletarHospede();
					limparTabela();
					preencherTabelaHospede(null);
					break;
				}
			}
	
			private void deletarHospede() {
				try {
					Integer idHospede = (Integer) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 0);
					System.out.println(idHospede);
					hospedeController.deletar(idHospede);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Erro inesperado: " + e.getMessage());
					throw new RuntimeException(e);
				}
			}

			private void deletarReserva() {
				try {
					Integer idReserva = (Integer) modelo.getValueAt(tbReservas.getSelectedRow(), 0);
					System.out.println(idReserva);
					reservaController.deletar(idReserva);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Erro inesperado: " + e.getMessage());
					throw new RuntimeException(e);
				}
			}
		});
		
		btnEditar.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e){
			switch (tabelaSelecionada) {
				case 0:
					alterarReserva();
					break;
				case 1:
					alterarHospede();
					break;
				}
			
		}
		});
	}

	private void alterarHospede() {
		SimpleDateFormat padrao = new SimpleDateFormat("yyyy-MM-dd");
		Object objetoDaLinha = (Object) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 0);
		if (objetoDaLinha instanceof Integer) {
			try {
				String nome = (String) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 1);
				
				String sobrenome = (String) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 2);

				DateTime dataNascimentoDATADateTime = new DateTime(modeloHospedes.getValueAt(tbHospedes.getSelectedRow(),3));
				Date dataNascimento = (Date) dataNascimentoDATADateTime.toDate();

				String nacionalidade = (String) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 4);

				String telefone = (String) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 5);

				//Object objNumeroDaReserva = (Object) modelo.getValueAt(tbReservas.getSelectedRow(), 6);
				Integer numeroDaReserva = (Integer) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 6);

				Hospede hospede = new Hospede((Integer)objetoDaLinha, nome, sobrenome, dataNascimento, nacionalidade, telefone, numeroDaReserva);

				try {
					this.hospedeController.alterar(hospede);
					JOptionPane.showMessageDialog(this, "Dados do Hospede Atualizado com sucesso");
					limparTabela();
					preencherTabelaHospede(hospede.getNome());

				} catch (Exception e) {
					JOptionPane.showMessageDialog(this, "Erro ao Alterar Reserva!");
				}

			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Preencher campos corretamente");
			}
			
		} else {
			JOptionPane.showMessageDialog(this, "Por favor, selecionar o ID");
		}
	}

	private void alterarReserva() {
		SimpleDateFormat padrao = new SimpleDateFormat("yyyy-MM-dd");
		Object objetoDaLinha = (Object) modelo.getValueAt(tbReservas.getSelectedRow(), 0);
		if (objetoDaLinha instanceof Integer) {
			try {
				DateTime dataEntradaDATADateTime = new DateTime(modelo.getValueAt(tbReservas.getSelectedRow(), 1));
				Date dataEntrada = (Date) dataEntradaDATADateTime.toDate();

				DateTime dataSaidaDATADateTime = new DateTime(modelo.getValueAt(tbReservas.getSelectedRow(),2));
				Date dataSaida = (Date) dataSaidaDATADateTime.toDate();

				Object objValor = (Object) modelo.getValueAt(tbReservas.getSelectedRow(), 3);
				Double valor = Double.parseDouble(objValor.toString());

				String formaPagamento = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 4);

				Reserva reserva = new Reserva((Integer)objetoDaLinha, dataEntrada, dataSaida, valor, formaPagamento);
				System.out.println(reserva.toString());

				try {
					this.reservaController.alterarReserva(reserva);
					JOptionPane.showMessageDialog(this, "Reserva Alterada Com Sucesso!!");
					limparTabela();
					preencherTabela(reserva.getIdReserva());

				} catch (Exception e) {
					JOptionPane.showMessageDialog(this, "Erro ao Alterar Reserva!");
				}

			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Preencher campos corretamente");
			}

		} else {
			JOptionPane.showMessageDialog(this, "Por favor, selecionar o ID");
		}
	}

	//Código que permite movimentar a janela pela tela seguindo a posição de "x" e "y"	
	private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	}

	private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
	}
	private void limparTabela() {
		modelo.getDataVector().clear();
		modeloHospedes.getDataVector().clear();
	}
	private void preencherTabela(Integer idInteger) {
		//this.reservaController.listarSimples();
			List<Reserva> reservas = listarReserva(idInteger);
			try {
				for(Reserva reserva : reservas){
					modelo.addRow(new Object[] { reserva.getIdReserva(), reserva.getDataEntrada(), reserva.getDataSaida(), reserva.getValor(), reserva.getFormaPagamento()});
				}
			} catch (Exception e) {
				throw e;
			}
		
	}

	private List<Reserva> listarReserva(Integer idReserva) {
		return this.reservaController.listar(idReserva);
	}

	private void preencherTabelaHospede(String nome) {
		List<Hospede> hospedes = listarHospedes(nome);
		try {
			for(Hospede hospede : hospedes){
				modeloHospedes.addRow(new Object[] { hospede.getIdHospede(), hospede.getNome(), hospede.getSobreNome(), hospede.getDataNascimento(), hospede.getNacionalidade(), hospede.getTelefone(), hospede.getNumeroReserva()});
			}
			} catch (Exception e) {
				throw e;
			}

	}

	private List<Hospede> listarHospedes(String nome) {
		return this.hospedeController.listar(nome);
	}

}
