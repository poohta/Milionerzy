import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

public class Okno extends JFrame {
	Main main = new Main();

	public Okno() {
		initComponents();
	}

	public void initComponents() {

		button1 = new JButton();
		button2 = new JButton();
		button3 = new JButton();
		button4 = new JButton();
		button5 = new JButton();

		/*
		 * JLabel llWynik2=new JLabel(); JLabel llAktualnePytanie2=new JLabel();
		 * JLabel lWynik2 = new JLabel(); JLabel lAktualnePytanie2 = new
		 * JLabel();
		 */
		JLabel Witaj;
		// ======== this ========
		setTitle("Kasa za Pytanie");
		setSize(618, 247);// 390
		setLayout(null);
		// ---- button1 ----
		button1.setText("Reset");
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.reset();
				JOptionPane.showMessageDialog(null, "Wynik zosta³ zresetowany", "Reset",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

		// ---- button2 ----
		button2.setText("Zadaj pytanie");
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.losujPytanie();
				Main.sprawdzWynik();
			}
		});

		// ---- button3 ----
		button3.setText("Zapisz wynik");
		button3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.zapisWyniku();
				Main.reset();
			}
		});
		// ---- button4 ----
		button4.setText("Zasady gry");
		button4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"Witaj w grze 'Kasa za Pytanie'!\nZasady tej gry s¹ nastêpuj¹ce:\nGrê zaczynasz klikaj¹c na przycisk 'Zadaj pytanie'.\nPo klikniêciu tego przycisku zadawane jest Ci pytanie,\nna które mo¿esz odpowiedzeæ poprzez wybranie 1 z 4 przycisków z odpowiedziami.\nZa ka¿d¹ poprawn¹ odpowiedz zwiêkszy Ci siê wynik.\nPo udzieleniu z³ej odpowiedzi gra siê koñczy.\nPo udzieleniu poprawnej odpowiedzi mo¿esz klikn¹æ na przycisk 'Zadaj pytanie' w celu zadania kolejnego pytania.\nPo udzieleniu 3 poprawnych odpowiedzi zyskujesz nagrodê gwarantowan¹ w wysokoœci 2000z³.\nPo udzieleniu 7 poprawnych odpowiedzi nagroda gwarantowana wynosi 32000z³.\nPo udzieleniu 12 poprawnych odpowiedzi, gra koñczy siê, a Ty zostajesz MILIONEREM.\nPrzycisk 'Reset' powoduje zresetowanie twojego wyniku.\nPrzycisk 'Zapisz wynik' zapisuje twój wynik, ale jednoczeœnie resetuje grê.\nPrzycisk 'Tabela wyników' wyœwietla wyniki z poprzednich zapisanych rozgrywek.\n\nIloœæ poprawnych odpowiedzi:    Nagroda:\n1                                                          500\n2                                                          1 000\n3                                                          2 000 - nagroda gwarantowana\n4                                                          4 000\n5                                                          8 000\n6                                                          16 000\n7                                                          32 000 - nagroda gwarantowana\n8                                                          64 000\n9                                                          125 000\n10                                                        250 000\n11                                                        500 000\n12                                                        1 000 000",
						"Zasady gry", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		// ---- button5 ----
		button5.setText("Tabela wyników");
		button5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Main.odczytWyniku();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		// ---- wynik Label ----
		Witaj = new JLabel("Witaj w menu g³ównym gry 'Kasa za Pytanie'");
		Witaj.setBounds(0, 0, 600, 100);
		Witaj.setFont(new Font("SansSerif", Font.PLAIN, 30));
		add(Witaj);
		/*
		 * llWynik2=new JLabel("Wynik:"); llWynik2.setBounds(300, 0, 300, 100);
		 * llWynik2.setFont(new Font("SansSerif",Font.PLAIN,30)); add(llWynik2);
		 * 
		 * lWynik2=new JLabel(""+Main.w); lWynik2.setBounds(394, 36, 100, 30);
		 * lWynik2.setFont(new Font("SansSerif",Font.PLAIN,30)); add(lWynik2);
		 * //---- aktualne pytanie Label ---- llAktualnePytanie2=new
		 * JLabel("Aktualne Pytanie:"); llAktualnePytanie2.setBounds(0, 0, 300,
		 * 100); llAktualnePytanie2.setFont(new
		 * Font("SansSerif",Font.PLAIN,30)); add(llAktualnePytanie2);
		 * 
		 * lAktualnePytanie2=new JLabel(""+Main.p);
		 * lAktualnePytanie2.setBounds(233, 25, 50, 50);
		 * lAktualnePytanie2.setFont(new Font("SansSerif",Font.PLAIN,30));
		 * add(lAktualnePytanie2);
		 */

		JPanel przyciski = new JPanel();
		przyciski.setLayout(new GridLayout(1, 3, 0, 0));
		button1.setBounds(0, 150, 200, 50);
		button2.setBounds(200, 100, 200, 100);
		button3.setBounds(400, 100, 200, 50);
		button4.setBounds(0, 100, 200, 50);
		button5.setBounds(400, 150, 200, 50);
		add(button1);
		add(button2);
		add(button3);
		add(button4);
		add(button5);

		/*
		 * kontenerPanel.add(cyfryPanel, BorderLayout.WEST); ramka = new
		 * JFrame(); ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 * ramka.getContentPane().setLayout(new BorderLayout(0, 0)); JPanel
		 * funkcjePanel = new JPanel(); kontenerPanel.add(funkcjePanel,
		 * BorderLayout.EAST);
		 */

	}

	public JButton button1;
	public JButton button2;
	public JButton button3;
	public JButton button4;
	public JButton button5;
}