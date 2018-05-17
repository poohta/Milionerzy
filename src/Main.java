import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main {
	public static String path = "wyniki.txt";
	// public static String
	// path="D:\\Piechta\\Eclipse\\eclipseneon\\milionerzy\\wyniki.txt";
	public static File plik = new File(path);
	// public static DbParams dbParams = null;
	// public static boolean isDbReady = false;
	public static boolean wygrana = false;
	public static boolean koniecGry = false;
	public static int w = 0; // wynik
	public static int gw = 0; // gwarantowany wynik
	public static int p = 0; // nr pytania
	public static long r = (long) Math.floor(Math.random() * 21); // losowe
																	// pytanie

	public static Okno ekran = new Okno();

	public static void main(String[] args) {

		if (ekran.isVisible() == false)
			ekran.setVisible(true);
		ekran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void sprawdzWynik() { // metoda sprawdza wynik na podstawie
										// aktualnego numeru pytania
		if (koniecGry == false) {
			if (p == 1)
				w = 500;
			if (p == 2)
				w = 1000;
			if (p == 3) { // po 3 pytaniu gwarantowana wygrana=2000
				w = 2000;
				gw = w;
			}
			if (p == 4)
				w = 4000;
			if (p == 5)
				w = 8000;
			if (p == 6)
				w = 16000;
			if (p == 7) { // po 7 pytaniu gwarantowana wygrana=32000
				w = 32000;
				gw = w;
			}
			if (p == 8)
				w = 64000;
			if (p == 9)
				w = 125000;
			if (p == 10)
				w = 250000;
			if (p == 11)
				w = 500000;
			if (p == 12) {
				w = 1000000;
				wygrana = true;
				JOptionPane.showMessageDialog(null, "Gratulacjê, wygra³eœ MILION!!!", "WYGRANA",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} else { // komunikat w przypadku udzielenia b³êdnej odpowiedzi
			w = gw;
			Object[] options = { "Tak", "Nie" };
			int n = JOptionPane.showOptionDialog(null,
					"B³êdna odpowiedŸ. Twój wynik wynosi³: " + gw + ". Czy chcesz zapisaæ swój wynik?", " Koniec gry",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[1]);
			if (n == 0)
				zapisWyniku();
			reset();
		}
	}

	public static void reset() { // metoda resetuj¹ca grê
		p = 0;
		gw = 0;
		w = 0;
		koniecGry = false;
	}

	public static void wyswietlWynik() { // metoda wyœwietlaj¹ca wynik
		JOptionPane.showMessageDialog(null, "Twój obecny wynik to: " + w + "\nLiczba poprawnych odpowiedzi: " + p,
				"Postêp", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void zapisWyniku() { // metoda zapisuj¹ca datê i wynik
		BufferedReader bReader = null;
		try {
			bReader = new BufferedReader(new FileReader(plik));
			String l = bReader.readLine();
			String tekst = "";
			while (l != null) {
				l = bReader.readLine();
				if (l != null)
					tekst = tekst + "\n" + l;
			}
			FileWriter zapis = new FileWriter(path);
			Scanner odczyt = new Scanner(plik);
			zapis.write(tekst + "\n" + "Data: " + new Date() + "                Wygrana: " + w + "z³");
			zapis.close();
		} catch (IOException e) {
			System.out.println("B³¹d zapisu");
			JOptionPane.showMessageDialog(null,
					"B³¹d zapisu! SprawdŸ czy plik .jar jest w tym samym folderze co plik z wynikami.", "B³¹d zapisu!",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		/*
		 * if (isDbReady) { // .getClass(). ResultSet rs = null; Statement stmt
		 * = null; Connection connect = null;
		 * 
		 * try { connect = DriverManager.getConnection(dbParams.getDbUrl(),
		 * dbParams.getDbUser(), dbParams.getDbPassword()); stmt =
		 * connect.createStatement(); if(gw>w) w=gw; String query =
		 * "INSERT INTO WYNIKI (wygrana, data) " + "Values(w, now())";
		 * 
		 * stmt.executeQuery(query); } catch (SQLException e) {
		 * e.printStackTrace(); } }
		 */
	}

	public static void odczytWyniku() throws IOException {
		BufferedReader bReader = null;
		try {
			bReader = new BufferedReader(new FileReader(plik));
			String l = bReader.readLine();
			String tekst = l;
			while (l != null) {
				System.out.println(l);
				l = bReader.readLine();
				if (l != null)
					tekst = tekst + "\n" + l;
			}
			JOptionPane.showMessageDialog(null, tekst, "Tabela wyników", JOptionPane.INFORMATION_MESSAGE);
		} finally {
			if (bReader != null) {
				bReader.close();
			}
		}
	}
		/*
		 * FileReader plikWe=null; String teskt=""; try { plikWe = new
		 * FileReader(plik); System.out.println("Odczyt znak po znaku:\n"); int
		 * c; // odczyt pliku znak po znaku i wyœwietlenie na ekranie monitora
		 * while ((c = plikWe.read()) != -1) { // je¿eli c = -1 to koniec pliku
		 * System.out.print((char)c); char character=(char)c; String
		 * tekst+=(String)char; } } finally { // klauzula finally s³u¿y do
		 * wykonania instrukcji // niezale¿nie od tego kiedy i w jaki sposób
		 * (normalnie lub // przez wyj¹tek) zosta³o zakoñczone wykonywanie bloku
		 * try if (plikWe != null) { plikWe.close(); // zamkniêcie pliku }
		 */
		/*
		 * try { Scanner odczyt = new Scanner(plik); String
		 * tekst=odczyt.nextLine(); JOptionPane.showMessageDialog(null,
		 * tekst,"Tabela wyników",JOptionPane.INFORMATION_MESSAGE);
		 * System.out.println(tekst); } catch (FileNotFoundException e) {
		 * System.out.println("Brak pliku do zapisu");
		 * JOptionPane.showMessageDialog(null, "Brak pliku do zapisu");
		 * e.printStackTrace(); }
		 */

	/*
	 * File plik=new File(plikzwynikami); try{ FileReader odczyt=new
	 * FileReader(plik); BufferedReader bodczyt= new BufferedReader(odczyt);
	 * JOptionPane.showMessageDialog(null, bodczyt); } catch(IOException e){
	 * e.printStackTrace(); }
	 */

	/*
	 * JTable tabela = pobierzDaneDoTabeli(); if (tabela != null)
	 * tabela.setRowHeight(2 * tabela.getRowHeight());
	 * //scrollPane.setViewportView(tabela);
	 * JOptionPane.showMessageDialog(null,tabela); }
	 */

	/*
	 * public static JTable pobierzDaneDoTabeli() { JTable tabela = null; if
	 * (isDbReady) { ResultSet rs = null; Statement stmt = null; Connection
	 * connect = null; try { connect =
	 * DriverManager.getConnection(dbParams.getDbUrl(), dbParams.getDbUser(),
	 * dbParams.getDbPassword()); stmt = connect.createStatement();
	 * 
	 * String query = "SELECT * FROM WYNIKI"; rs = stmt.executeQuery(query); if
	 * (rs != null) { tabela = new JTable(); DefaultTableModel modelTabeli =
	 * (DefaultTableModel) tabela.getModel(); String[] nazwyKolumn = {
	 * "Wygrana", "Data" }; modelTabeli.setColumnIdentifiers(nazwyKolumn);
	 * 
	 * while (rs.next()) { Object[] objects = new Object[2]; objects[0] =
	 * rs.getString("WYGRANA"); objects[1] = rs.getDate("DATA");
	 * modelTabeli.addRow(objects); } }
	 * 
	 * } catch (SQLException e) { System.out.println(e.getMessage() +
	 * ", State: " + e.getSQLState()); e.printStackTrace(); } finally { if (stmt
	 * != null) try { stmt.close(); } catch (SQLException e) {
	 * e.printStackTrace(); } if (connect != null) try { connect.close(); }
	 * catch (SQLException e) { e.printStackTrace(); } } } return tabela; }
	 */

	public static void losujPytanie() { // metoda wywo³uj¹ca kolejne pytanie
		if (r == 21) // pytañ jest 20, wiêc jeœli r przekroczy 20 musi zostaæ
						// zresetowane
			r = -1;
		r++;
		/*
		 * long r = (long) Math.floor(Math.random() * 21); while (r == 21) r =
		 * (long) Math.floor(Math.random() * 21);
		 */
		// r=0; //test
		if (r == 0) {
			Object[] options = { "Tegucigalapa", "Phnom Penh", "Bejrut", "Duszanbe" };
			int n = JOptionPane.showOptionDialog(null, "Które miasto jest stolic¹ Kambod¿y?", "Pytanie nr: " + (p + 1),
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[3]);
			if (n == 1) {
				p++;
				sprawdzWynik();
				System.out.println("Dobra odpowiedz");
				if (!wygrana)
					wyswietlWynik();
			} else
				koniecGry = true;
		}
		if (r == 1) {
			Object[] options = { "Olly Murs", "Josh Dun", "Chester Bennington", "Dominic Cifarelli" };
			int n = JOptionPane.showOptionDialog(null,
					"Jak siê nazywa wokalista prowadz¹cy w zespole muzycznym 'Linkin Park'?", "Pytanie nr: " + (p + 1),
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[3]);
			if (n == 2) {
				p++;
				sprawdzWynik();
				System.out.println("Dobra odpowiedz");
				if (!wygrana)
					wyswietlWynik();
			} else
				koniecGry = true;
		}
		if (r == 2) {
			Object[] options = { "480r p.n.e.", "743r p.n.e.", "515r p.n.e.", "447r p.n.e." };
			int n = JOptionPane.showOptionDialog(null, "W którym roku mia³a miejsce Bitwa pod Termopilami?",
					"Pytanie nr: " + (p + 1), JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
					options, options[3]);
			if (n == 0) {
				p++;
				sprawdzWynik();
				System.out.println("Dobra odpowiedz");
				if (!wygrana)
					wyswietlWynik();
			} else
				koniecGry = true;
		}
		if (r == 3) {
			Object[] options = { "Konrad Wallenrod", "Konfederaci barscy", "Pierwiosnek", "Roz³¹czenie" };
			int n = JOptionPane.showOptionDialog(null,
					"Które z wymienionych dzie³ nie zosta³o napisane przez Adama Mickiewicza?",
					"Pytanie nr: " + (p + 1), JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
					options, options[3]);
			if (n == 3) {
				p++;
				sprawdzWynik();
				System.out.println("Dobra odpowiedz");
				if (!wygrana)
					wyswietlWynik();
			} else
				koniecGry = true;
		}
		if (r == 4) {
			Object[] options = { "W¹troba", "Œledziona", "Mó¿d¿ek", "Trzustka" };
			int n = JOptionPane.showOptionDialog(null, "Najwiêkszym gruczo³em cz³owieka jest...",
					"Pytanie nr: " + (p + 1), JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
					options, options[3]);
			if (n == 0) {
				p++;
				sprawdzWynik();
				System.out.println("Dobra odpowiedz");
				if (!wygrana)
					wyswietlWynik();
			} else
				koniecGry = true;
		}
		if (r == 5) {
			Object[] options = { "Pablo Picasso", "Micha³ Anio³", "Salvador Dali", "Leonardo da Vinci" };
			int n = JOptionPane.showOptionDialog(null, "Kto namalowa³ obraz 'Trwa³oœæ Pamiêci'?",
					"Pytanie nr: " + (p + 1), JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
					options, options[3]);
			if (n == 2) {
				p++;
				sprawdzWynik();
				System.out.println("Dobra odpowiedz");
				if (!wygrana)
					wyswietlWynik();
			} else
				koniecGry = true;
		}
		if (r == 6) {
			Object[] options = { "z Anglii", "z Argentyny", "z Zanzibaru", "ze Stanów Zjednoczonych" };
			int n = JOptionPane.showOptionDialog(null, "Sk¹d pochodzi³ Freddie Mercury, wokalista grupy Queen",
					"Pytanie nr: " + (p + 1), JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
					options, options[3]);
			if (n == 2) {
				p++;
				sprawdzWynik();
				System.out.println("Dobra odpowiedz");
				if (!wygrana)
					wyswietlWynik();
			} else
				koniecGry = true;
		}
		if (r == 7) {
			Object[] options = { "1T (tesla)", "1H (henr)", "1Wb (weber)", "1S (siemens)" };
			int n = JOptionPane.showOptionDialog(null, "Co jest jednostk¹ konduktancji w uk³adzie SI?",
					"Pytanie nr: " + (p + 1), JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
					options, options[3]);
			if (n == 3) {
				p++;
				sprawdzWynik();
				System.out.println("Dobra odpowiedz");
				if (!wygrana)
					wyswietlWynik();
			} else
				koniecGry = true;
		}
		if (r == 8) {
			Object[] options = { "tchnienie wiatru", "pusta rêka", "droga b³yskawicy", "spokojna droga" };
			int n = JOptionPane.showOptionDialog(null, "Co dos³ownie oznacza s³owo 'karate'?", "Pytanie nr: " + (p + 1),
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[3]);
			if (n == 1) {
				p++;
				sprawdzWynik();
				System.out.println("Dobra odpowiedz");
				if (!wygrana)
					wyswietlWynik();
			} else
				koniecGry = true;
		}
		if (r == 9) {
			Object[] options = { "kwazary", "gwiazdy supergiganty", "supernowe", "komety" };
			int n = JOptionPane.showOptionDialog(null, "Które obiekty we wszechœwiecie œwiec¹ najjaœniej?",
					"Pytanie nr: " + (p + 1), JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
					options, options[3]);
			if (n == 0) {
				p++;
				sprawdzWynik();
				System.out.println("Dobra odpowiedz");
				if (!wygrana)
					wyswietlWynik();
			} else
				koniecGry = true;
		}
		if (r == 10) {
			Object[] options = { "Archimedes", "Sokrates", "Pascal", "Kartezjusz" };
			int n = JOptionPane.showOptionDialog(null,
					"Kto jest autorem s³ów Cogito ergo sum(³ac.„Myœlê, wiêc jestem”?", "Pytanie nr: " + (p + 1),
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[3]);
			if (n == 3) {
				p++;
				sprawdzWynik();
				System.out.println("Dobra odpowiedz");
				if (!wygrana)
					wyswietlWynik();
			} else
				koniecGry = true;
		}
		if (r == 11) {
			Object[] options = { "w Ameryce Pó³nocnej", "w Ameryce Po³udniowej", "w Europie", "w Australii" };
			int n = JOptionPane.showOptionDialog(null,
					"Na którym kontynencie znajduje siê najmniej aktywnych wulkanów?", "Pytanie nr: " + (p + 1),
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[3]);
			if (n == 3) {
				p++;
				sprawdzWynik();
				System.out.println("Dobra odpowiedz");
				if (!wygrana)
					wyswietlWynik();
			} else
				koniecGry = true;
		}
		if (r == 12) {
			Object[] options = { "Œniardwy", "Wigry", "Hañcza", "Morskie Oko" };
			int n = JOptionPane.showOptionDialog(null, "Które jezioro jest nag³êbsze w Polsce?",
					"Pytanie nr: " + (p + 1), JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
					options, options[3]);
			if (n == 2) {
				p++;
				sprawdzWynik();
				System.out.println("Dobra odpowiedz");
				if (!wygrana)
					wyswietlWynik();
			} else
				koniecGry = true;
		}
		if (r == 13) {
			Object[] options = { "Wietnam", "Finlandia", "Sri lanka", "Seszele" };
			int n = JOptionPane.showOptionDialog(null, "Który kraj nazywa siê w swoim jêzyku 'Suomi'?",
					"Pytanie nr: " + (p + 1), JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
					options, options[3]);
			if (n == 1) {
				p++;
				sprawdzWynik();
				System.out.println("Dobra odpowiedz");
				if (!wygrana)
					wyswietlWynik();
			} else
				koniecGry = true;
		}
		if (r == 14) {
			Object[] options = { "Francuzi", "Brytyjczycy", "Holendrzy", "Hiszpanie" };
			int n = JOptionPane.showOptionDialog(null, "Kto odkry³ Now¹ Zelandiê?", "Pytanie nr: " + (p + 1),
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[3]);
			if (n == 2) {
				p++;
				sprawdzWynik();
				System.out.println("Dobra odpowiedz");
				if (!wygrana)
					wyswietlWynik();
			} else
				koniecGry = true;
		}
		if (r == 15) {
			Object[] options = { "hydroponika", "hydroformia", "hydrofarming", "hydrokultura" };
			int n = JOptionPane.showOptionDialog(null, "Jak nazywa siê bezglebowa uprawa roœlin na po¿ywkach wodnych?",
					"Pytanie nr: " + (p + 1), JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
					options, options[3]);
			if (n == 0) {
				p++;
				sprawdzWynik();
				System.out.println("Dobra odpowiedz");
				if (!wygrana)
					wyswietlWynik();
			} else
				koniecGry = true;
		}
		if (r == 16) {
			Object[] options = { "Antwerpia", "Wenecja", "Dubrownik", "Rotterdam" };
			int n = JOptionPane.showOptionDialog(null, "W którym mieœcie jest najwiêkszy port w Europie?",
					"Pytanie nr: " + (p + 1), JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
					options, options[3]);
			if (n == 3) {
				p++;
				sprawdzWynik();
				System.out.println("Dobra odpowiedz");
				if (!wygrana)
					wyswietlWynik();
			} else
				koniecGry = true;
		}
		if (r == 17) {
			Object[] options = { "2kg", "5kg", "8kg", "10kg" };
			int n = JOptionPane.showOptionDialog(null, "Ile typowo wa¿y ludzka g³owa?", "Pytanie nr: " + (p + 1),
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[3]);
			if (n == 1) {
				p++;
				sprawdzWynik();
				System.out.println("Dobra odpowiedz");
				if (!wygrana)
					wyswietlWynik();
			} else
				koniecGry = true;
		}
		if (r == 18) {
			Object[] options = { "Na Pacyfiku", "Na Morzu Norweskim", "W trzustce", "w w¹trobie" };
			int n = JOptionPane.showOptionDialog(null, "Gdzie znajduj¹ siê wyspy Langerhansa?",
					"Pytanie nr: " + (p + 1), JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
					options, options[3]);
			if (n == 2) {
				p++;
				sprawdzWynik();
				System.out.println("Dobra odpowiedz");
				if (!wygrana)
					wyswietlWynik();
			} else
				koniecGry = true;
		}
		if (r == 19) {
			Object[] options = { "Pulp Fiction", "Kill Bill I", "Cztery pokoje", "Wœciek³e psy" };
			int n = JOptionPane.showOptionDialog(null,
					"W którym z przez siebie re¿yserowanych filmów NIE zagra³ Quentin Tarantino?",
					"Pytanie nr: " + (p + 1), JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
					options, options[3]);
			if (n == 1) {
				p++;
				sprawdzWynik();
				System.out.println("Dobra odpowiedz");
				if (!wygrana)
					wyswietlWynik();
			} else
				koniecGry = true;
		}
		if (r == 20) {
			Object[] options = { "68", "82", "88", "102" };
			int n = JOptionPane.showOptionDialog(null, "Ile klawiszy ma typowy wspó³czesny fortepian?",
					"Pytanie nr: " + (p + 1), JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
					options, options[3]);
			if (n == 2) {
				p++;
				sprawdzWynik();
				System.out.println("Dobra odpowiedz");
				if (!wygrana)
					wyswietlWynik();
			} else
				koniecGry = true;
		}
	}

}