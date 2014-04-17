package testMVC;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;


public class ContadorControlador {

	private List<ContadorPOJO> listadepojo = new ArrayList<ContadorPOJO>();
	private ContadorView view;
	private JFrame jframe;
	
	public ContadorControlador() throws Exception {
		jframe = new JFrame();
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		for (int x=0; x<12; x++) {
			listadepojo.add(new ContadorPOJO());
		}
		
		view = new ContadorView();
		for (int x=0; x<12; x++) {
			listadepojo.get(x).addObserver(view);
			listadepojo.get(x).IniciarThread();
		}
		jframe.setContentPane(view);
		jframe.pack();
		jframe.setLocationRelativeTo(null);
		jframe.setVisible(true);
		
		
	}
	
}
