package com.codigomestre.cliente.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Properties;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import com.codigomestre.cliente.Cliente;
import com.codigomestre.servidor.Servidor;
import com.codigomestre.servidor.ServidorSuporte;

public class HomeDireitaView extends JPanel {

	private UsuariosListView usuariosView;
	private SalasListView salasView;

	private Cliente c;

	private Socket skt;
	private Servidor s;
	
	public HomeDireitaView(Janela janela) {
		
		try {
			s = new Servidor();
			s.start();
			skt = new Socket("localhost", Servidor.PORTA);
			c = new Cliente(skt.getInputStream());
			c.start();
			// LOGIN NECESSÁRIO PARA REALIZAR OS TESTES
			Properties p = new Properties();
			p.put("codigo", ServidorSuporte.CODIGO_LOGAR);
			p.put("nu", "Adriane Galisteu");
			p.put("e", "adriane@gmail.com");
			p.put("s", "123");
			
			OutputStream os = skt.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject(p);
			
			salasView = new SalasListView(oos);
		} catch (Exception e) {
			JOptionPane
					.showMessageDialog(null,
							"Não foi possível conectar-se ao servidor. A aplicação será encerrada.");
			System.exit(0);
		}
		
		setLayout(new GridLayout(2, 1));

		setBorder(new LineBorder(new Color(0, 0, 0), 4, true));

		setPreferredSize(Constantes.HOMEDIREITAVIEW_PREFERRED_SIZE);
		setMinimumSize(Constantes.HOMEDIREITAVIEW_MINIMUM_SIZE);
		setMaximumSize(Constantes.HOMEDIREITAVIEW_MAXIMUM_SIZE);

		usuariosView = new UsuariosListView(janela);
		
		add(usuariosView);
		add(salasView);
	}
}
