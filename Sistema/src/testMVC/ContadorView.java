package testMVC;

import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ContadorView extends JPanel implements Observer {

	private JLabel[] label = new JLabel[ (ContadorPOJO.valor + 1) ];

	public ContadorView() {
		super();
		setLayout(new FlowLayout());
		for (int x=0; x<(ContadorPOJO.valor+1); x++) {
			label[x]= new JLabel("01 label");
			add(label[x]);
		}
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		ContadorPOJO observador = (ContadorPOJO) arg0;
		label[observador.getIndice()].setText(String.valueOf(observador.getIncremento()));
		System.out.println( observador.getIndice() + " ATUALIZADO " + observador.getIncremento() + " incrementado!");
	}
}
