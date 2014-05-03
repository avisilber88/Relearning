import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

public class LessonC extends JPanel implements ActionListener {

	Timer timer;
	int time;
	JTextArea tf;
	JTextArea av;
	JFrame frame;
Container window;
	public static void main(String args[]) {
		System.out.println("hi");
		new LessonC();
	}

	public LessonC() {
		// Timer timer = new Timer ();
		time = 0;
		timer = new Timer(100, this);
		timer.start();

		// this.paint(shape);
		frame = new JFrame();

		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Rectangle shape = new Rectangle(5, 5, 5, 5);
		frame.setContentPane(this);
		window = frame.getContentPane();
	
		tf = new JTextArea();
		window.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		tf.setRows(1);
		tf.setColumns(1);

		av = new JTextArea();
		c.gridx = 1;
		c.gridy = 2;
		c.insets = new Insets(0, 0, 100, 0); // (space out up, space out left,
												// space out down, space out
												// right)
		window.add(tf, c);
//		c.gridx = 2;
//		c.gridy = 0;
//		window.add(new MyPanel(), c);
		c.gridx = 1;
		c.gridy = 3;
		window.add(av, c);
		// tf.setAlignmentY(250); f.add(new MyPanel());
//		this.pack();
		frame.setVisible(true);
		makeStaff();

	}

	// this.getContentPane().paint(g)
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graphics = (Graphics2D)(g);
//		super.paintComponent(g);
//graphics.
		graphics.drawString("hello", time, 70);
		// Draw Text
//		g.drawString("This is my custom Panel!", 10, 20);
	}
	public void makeStaff() {
		System.out.println("I am making a staff");
		// this.
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		time++;
		if (canCountSecond()) {
			tf.setText("" + (int) howManySeconds());
			av.setText("brought to you by Silber Solutions");
//			System.out.println(time / 10);
//			paintComponents(this.getGraphics());
			repaint();
		}
		// TODO Auto-generated method stub

	}

	public double howManySeconds() {
		double doubleNumber = (double) time / 10;
		return doubleNumber;

	}

	public boolean canCountSecond() {
		return (howManySeconds() == (double) (time / 10));
	}
}

class MyPanel extends JPanel {

	public MyPanel() {
//		setBorder(BorderFactory.createLineBorder(Color.black));
	}

	public Dimension getPreferredSize() {
		return new Dimension(1000, 200);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Draw Text
		g.drawString("This is my custom Panel!", 10, 20);
	}
}
