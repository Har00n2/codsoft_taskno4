import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.*;
import java.awt.event.*;

public class QuizApplication {

	public static void main(String[] args) {
		new QuizApp();
	}

}
class QuizApp extends JFrame implements ActionListener{
	String questions[][] = new String[5][5];
	String answers[][] = new String[5][1];
	String userInput[][] = new String[5][1];
	
	int correctAnswers = 0;
	int count = 0;
	public static int totalTime = 15;
	public static int answered = 0;
	public static int score = 0;
	
	JLabel qNumber, question, header;
	JRadioButton option1, option2, option3, option4;
	JButton submit, next;
	ButtonGroup buttonGroup;
	
	QuizApp(){
		getContentPane().setBackground(new Color(198, 231, 255));
		setLayout(null);
		setBounds(50,50,1440,700);
		
		header = new JLabel("Quiz App");
		header.setBounds(450,1,900,180);
		header.setForeground(Color.BLACK);
		header.setFont(new Font("Tahoma", Font.BOLD, 100));
		add(header);
		
		
		
		ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("images/watch2.png"));
		JLabel image = new JLabel(img);
		image.setBounds(980,120,500,500);
		add(image);
		
		qNumber = new JLabel();
		qNumber.setBounds(100, 200, 50, 30);
		qNumber.setFont(new Font("Tahoma", Font.BOLD, 20));
		add(qNumber);
		
		question = new JLabel();
		question.setBounds(130, 200, 600, 30);
		question.setFont(new Font("Tahoma", Font.BOLD, 20));
		add(question);
		
		questions[0][0] = "What is the capital of France?" ;		
		questions[0][1] = "Madrid";
		questions[0][2] = "Berlin";
		questions[0][3] = "Paris";
		questions[0][4] = "Rome";
		
		questions[1][0] = "Which planet is known as the Red Planet?";
		questions[1][1] = "Earth";
		questions[1][2] = "Venus";
		questions[1][3] = "Mars";
		questions[1][4] = "Jupiter";

		questions[2][0] = "Who wrote the play Romeo and Juliet?";
		questions[2][1] = "William Wordsworth";
		questions[2][2] = "William Shakespeare";
		questions[2][3] = "Charles Dickens";
		questions[2][4] = "Mark Twain";

		questions[3][0] = "Which gas do plants primarily use for photosynthesis?";
		questions[3][1] = "Oxygen";
		questions[3][2] = "Nitrogen";
		questions[3][3] = "Carbon Dioxide";
		questions[3][4] = "Hydrogen";

		questions[4][0] = "What is the boiling point of water at sea level?";
		questions[4][1] = "90°C";
		questions[4][2] = "100°C";
		questions[4][3] = "110°C";
		questions[4][4] = "120°C";
		
		answers[0][0] = "Paris";
		answers[1][0] = "Mars";
		answers[2][0] = "William Shakespeare";
		answers[3][0] = "Carbon Dioxide";
		answers[4][0] = "100°C";

		
		option1 = new JRadioButton();
		option1.setBounds(140, 250, 350, 30);
		option1.setFont(new Font("Tahoma", Font.BOLD, 15));
		option1.setBackground(new Color(255, 255, 255));
		add(option1);
		
		option2 = new JRadioButton();
		option2.setBounds(650, 250, 350, 30);
		option2.setFont(new Font("Tahoma", Font.BOLD, 15));
		option2.setBackground(new Color(255, 255, 255));
		add(option2);
		
		option3 = new JRadioButton();
		option3.setBounds(140, 400, 350, 30);
		option3.setFont(new Font("Tahoma", Font.BOLD, 15));
		option3.setBackground(new Color(255, 255, 255));
		add(option3);
		
		option4 = new JRadioButton();
		option4.setBounds(650, 400, 350, 30);
		option4.setFont(new Font("Tahoma", Font.BOLD, 15));
		option4.setBackground(new Color(255, 255, 255));
		add(option4);
		
		buttonGroup = new ButtonGroup();
		buttonGroup.add(option1);
		buttonGroup.add(option2);
		buttonGroup.add(option3);
		buttonGroup.add(option4);
		
		
		next = new JButton("Next");
		next.setBounds(340,550,150,30);
		next.addActionListener(this);
		next.setForeground(Color.WHITE);
		next.setBackground(new Color(0, 0, 0));
		add(next);
		
		submit = new JButton("Submit");
		submit.setBounds(650,550,150,30);
		submit.addActionListener(this);
		submit.setForeground(Color.WHITE);
		submit.setBackground(new Color(0, 0, 0));
		submit.setEnabled(false);
		add(submit);
		
		start(count);
		
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == next) {
			repaint();			
			answered = 1;
			if(buttonGroup.getSelection() == null) {
				userInput[count][0] = " ";
			}
			else {
				userInput[count][0] = buttonGroup.getSelection().getActionCommand();
			}

			if(count==3) {
				next.setEnabled(false);
				submit.setEnabled(true);
			}
			count++;
			start(count);
		}
		
		else {
			answered = 1;
			if(buttonGroup.getSelection() == null) {
				userInput[count][0] = "";
			}
			else {
				userInput[count][0] = buttonGroup.getSelection().getActionCommand();
			}
			
			for(int i=0; i<userInput.length; i++) {
				if(userInput[i][0].equals(answers[i][0])) {
					score = score + 10;
					correctAnswers++;
				}
			}
			setVisible(false);
			new Result(score, correctAnswers);
		}
	}
	
	
	public void paint(Graphics g) {
		super.paint(g);
		
		
		String timer = "Time Remaining: "; //"Time Remaining: " + totalTime + " Secs"
		String timer1 = Integer.toString(totalTime);
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("Tahoma", Font.BOLD, 30));
		if(totalTime>0) {
			g.drawString(timer, 1140, 250);
			g.drawString(timer1, 1235, 425);
		}
		else {
			g.drawString("Time Out!", 1210, 250);
		}
		
		totalTime--;
		
		try {
			Thread.sleep(1000);
			repaint();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		if(answered == 1) {
			answered = 0;
			totalTime = 15;
		}
		else if(totalTime<0) {
			totalTime = 15;

			if(count==3) {
				next.setEnabled(false);
				submit.setEnabled(true);
			}
			if(count==4) {
				if(buttonGroup.getSelection() == null) {
					userInput[count][0] = "";
				}
				else {
					userInput[count][0] = buttonGroup.getSelection().getActionCommand();
				}
				
				for(int i=0; i<userInput.length; i++) {
					if(userInput[i][0].equals(answers[i][0])) {
						score = score + 10;
						correctAnswers++;
					}
				}
				setVisible(false);
				new Result(score, correctAnswers);
				
			}
			else {
				if(buttonGroup.getSelection() == null) {
					userInput[count][0] = "";
				}
				else {
					userInput[count][0] = buttonGroup.getSelection().getActionCommand();
				}
			
				count++;
				start(count);
			}
		}
		
	}

	public void start(int count) {
		qNumber.setText((count+1)+".");
		question.setText(questions[count][0]);
		
		option1.setText(questions[count][1]);
		option1.setActionCommand(questions[count][1]);
		
		option2.setText(questions[count][2]);
		option2.setActionCommand(questions[count][2]);
		
		option3.setText(questions[count][3]);
		option3.setActionCommand(questions[count][3]);
		
		option4.setText(questions[count][4]);
		option4.setActionCommand(questions[count][4]);
		
		buttonGroup.clearSelection();
	}
	
	
}

class Result extends JFrame{
	JLabel totalScore, totalCorrectAnswers, totalWrongAnswers, header;
	
	Result(int score, int correctAnswers){
		int wrongAnswers = 5 - correctAnswers;
		getContentPane().setBackground(new Color(212, 246, 255));
		setLayout(null);
		setBounds(500,100,500,700);
		
		header = new JLabel("Quiz Stats!");
		header.setForeground(Color.black);
		header.setFont(new Font("Tahoma", Font.PLAIN, 50)); 
		header.setBounds(120, 60, 300, 100);
		add(header);
		
		totalScore = new JLabel("Score: " + score);
		totalScore.setBounds(150, 250, 200, 30);
		totalScore.setFont(new Font("Serif", Font.PLAIN, 25));
		add(totalScore);
		
		totalCorrectAnswers = new JLabel("Correct Answers: " + correctAnswers + " \u2705");
		totalCorrectAnswers.setBounds(150, 320, 350, 30);
		totalCorrectAnswers.setFont(new Font("Serif", Font.PLAIN, 25));
		totalCorrectAnswers.setForeground(Color.GREEN);
		add(totalCorrectAnswers);
		
		totalWrongAnswers = new JLabel("Wrong Answers: " + wrongAnswers + " \u274C");
		totalWrongAnswers.setBounds(150, 400, 350, 30);
		totalWrongAnswers.setFont(new Font("Serif", Font.PLAIN, 25));
		totalWrongAnswers.setForeground(Color.red);
		add(totalWrongAnswers);
		
		setVisible(true);
	}
}