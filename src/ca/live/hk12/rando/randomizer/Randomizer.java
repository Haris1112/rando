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
	private static final String delimiter = "~";
	private static final String defDelimiter = "_";
	
	private int fQi = 0;
	private ArrayList<String> firstQuestions;
	private ArrayList<String> questions;
	
	private JFrame frame;
	private JLabel questionLabel;
	private JButton randomButton;
	
	public Randomizer(String fileName) {
		this.fileName = fileName;
		firstQuestions = new ArrayList<String>();
		questions = new ArrayList<String>();
		
		readQuestions();
		createWindow("Randomizer", 700, 400);
		
		System.out.println("Author:\tHaris Khan\nDate:\tFeb/2014\nGitHub:\thttp://github.com/Haris1112/");
		System.out.println("Website:\thttp://HappyHaris.com/");
		System.out.println("\n The following data was loaded:");
		System.out.println("First Questions to ask:===");
		for(String s : firstQuestions) {
			System.out.println(s);
		}
		System.out.println("\nOther Questions:===");
		for(String s : questions) {
			System.out.println(s);
		}
	}
	
	public void createWindow(String title, int width, int height) {
		frame = new JFrame(title);
		questionLabel = new JLabel("Tell me about yourself.");
		randomButton = new JButton("Random Question");
		
		frame.setSize(width, height);
		frame.setPreferredSize(new Dimension(width, height));
		frame.setLocationRelativeTo(null);
		frame.setLayout(new GridLayout(2, 1));
		frame.add(questionLabel);
		frame.add(randomButton);
		
		randomButton.addActionListener(this);
		randomButton.setActionCommand("randomize");
		randomButton.setFont(new Font("Serif", Font.PLAIN, 25 ));
		
		questionLabel.setFont(new Font("Serif", Font.PLAIN, 25 ));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void readQuestions() {
		File f = new File(fileName);
		try {
			Scanner sc = new Scanner(f);
			boolean defQs = false;
			while(sc.hasNextLine()) {
				String s = sc.nextLine().trim();
				if(s.equals(defDelimiter))
					defQs = true;
			}
			sc.close();
			sc = new Scanner(f);
			if(defQs) {
				while(sc.hasNextLine()) {
					String s = sc.nextLine().trim();
					if(s.equals(defDelimiter))
						break;
					firstQuestions.add(s);
				}
			}
			while(sc.hasNextLine()) {
				String s = sc.nextLine().trim();
				int num = 1;
				if(s.contains("~")) {
					String[] st = s.split(delimiter);
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
			setNextQuestion();
		}
	}
	
	private void setQuestion(String question) {
		questionLabel.setText("<html>" + question + "</html>");
	}

	private void setNextQuestion() {
		if(fQi < firstQuestions.size()) {
			setQuestion(firstQuestions.get(fQi++));
		} else {
			setQuestion(getRandomQuestion());
		}
	}
	
	private String getRandomQuestion() {
		Random r = new Random();
		int x = r.nextInt(questions.size());
		return questions.get(x);
	}
	
}
