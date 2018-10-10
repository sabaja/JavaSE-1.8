package com.expressionLambda;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ActionListenerLambdaExpression {

	public static void main(String[] args) {
		ButtonNoLambda m = new ButtonNoLambda();
		ButtonLambda n = new ButtonLambda();
	}

}

class ButtonNoLambda{
	
	public ButtonNoLambda(){
		super();
		JButton button = new JButton("Click");
		JFrame frame = new JFrame("JButton No Lambda");
		JPanel panel = new JPanel();

		panel.add(button);
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Prima di java 8");
				
			}
		});
	}
}

class ButtonLambda{

	public ButtonLambda() {
		super();
		JButton button = new JButton("Click");
		JFrame frame = new JFrame("JButton Lambda");
		JPanel panel = new JPanel();

		panel.add(button);
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);
		button.addActionListener((e) -> System.out.println("Lambda con Java 8"));
	
	}
	 
	
}