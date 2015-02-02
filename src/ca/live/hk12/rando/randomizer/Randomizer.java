package ca.live.hk12.rando.randomizer;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Randomizer implements ActionListener {
	
	private String fileName;
	
	private ArrayList<String> questions;
	
	private JFrame frame;
	private JLabel questionLabel;
	private JButton randomButton;
	
	public Randomizer(String fileName) {
		this.fileName = fileName;
		questions = new ArrayList<String>();
		
		readQuestions();
		createWindow("Randomizer", 700, 400);
		
		System.out.println("Author:\tHaris Khan\nDate:\tFeb/2014\nGitHub:\thttp://github.com/Haris1112/");
		System.out.println("Website:\thttp://HappyHaris.com/");
		System.out.println("\n The following data was loaded:");
		for(String s : questions) {
			System.out.println(s);
		}
	}
	
	public void createWindow(String title, int width, int height) {
		frame = new JFrame(title);
		questionLabel = new JLabel("Loading...");
		randomButton = new JButton("Random Question");
		
		frame.setSize(width, height);
		frame.setPreferredSize(new Dimension(width, height));
		frame.setLocationRelativeTo(null);
		frame.setLayout(new GridLayout(2, 1));
		frame.add(questionLabel);
		frame.add(randomButton);
		
		randomButton.addActionListener(this);
		randomButton.setActionCommand("randomize");
		
		questionLabel.setFont(new Font("Serif", Font.PLAIN, 25 ));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void readQuestions() {
		File f = new File(fileName);
		try {
			Scanner sc = new Scanner(f);
			while(sc.hasNextLine()) {
				String s = sc.nextLine().trim();
				int num = 1;
				if(s.contains(",")) {
					String[] st = s.split(",");
					num = Integer.parseInt(st[1].trim());
					s = st[0].trim();
				}
				while(num-- > 0) {
					questions.add(s);
				}
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getActionCommand().equals("randomize")) {
			questionLabel.setText("<html>" + getRandomQuestion() + "</html>");
		}
	}

	private String getRandomQuestion() {
		Random r = new Random();
		int x = r.nextInt(questions.size());
		return questions.get(x);
	}
	
}
