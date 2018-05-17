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
				JOptionPane.showMessageDialog(null, "Gratulacj�, wygra�e� MILION!!!", "WYGRANA",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} else { // komunikat w przypadku udzielenia b��dnej odpowiedzi
			w = gw;
			Object[] options = { "Tak", "Nie" };
			int n = JOptionPane.showOptionDialog(null,
					"B��dna odpowied�. Tw�j wynik wynosi�: " + gw + ". Czy chcesz zapisa� sw�j wynik?", " Koniec gry",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[1]);
			if (n == 0)
				zapisWyniku();
			reset();
		}
	}

	public static void reset() { // metoda resetuj�ca gr�
		p = 0;
		gw = 0;
		w = 0;
		koniecGry = false;
	}

	public static void wyswietlWynik() { // metoda wy�wietlaj�ca wynik
		JOptionPane.showMessageDialog(null, "Tw�j obecny wynik to: " + w + "\nLiczba poprawnych odpowiedzi: " + p,
				"Post�p", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void zapisWyniku() { // metoda zapisuj�ca dat� i wynik
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
			zapis.write(tekst + "\n" + "Data: " + new Date() + "                Wygrana: " + w + "z�");
			zapis.close();
		} catch (IOException e) {
			System.out.println("B��d zapisu");
			JOptionPane.showMessageDialog(null,
					"B��d zapisu! Sprawd� czy plik .jar jest w tym samym folderze co plik z wynikami.", "B��d zapisu!",
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
			JOptionPane.showMessageDialog(null, tekst, "Tabela wynik�w", JOptionPane.INFORMATION_MESSAGE);
		} finally {
			if (bReader != null) {
				bReader.close();
			}
		}
	}
		/*
		 * FileReader plikWe=null; String teskt=""; try { plikWe = new
		 * FileReader(plik); System.out.println("Odczyt znak po znaku:\n"); int
		 * c; // odczyt pliku znak po znaku i wy�wietlenie na ekranie monitora
		 * while ((c = plikWe.read()) != -1) { // je�eli c = -1 to koniec pliku
		 * System.out.print((char)c); char character=(char)c; String
		 * tekst+=(String)char; } } finally { // klauzula finally s�u�y do
		 * wykonania instrukcji // niezale�nie od tego kiedy i w jaki spos�b
		 * (normalnie lub // przez wyj�tek) zosta�o zako�czone wykonywanie bloku
		 * try if (plikWe != null) { plikWe.close(); // zamkni�cie pliku }
		 */
		/*
		 * try { Scanner odczyt = new Scanner(plik); String
		 * tekst=odczyt.nextLine(); JOptionPane.showMessageDialog(null,
		 * tekst,"Tabela wynik�w",JOptionPane.INFORMATION_MESSAGE);
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

	public static void losujPytanie() { // metoda wywo�uj�ca kolejne pytanie
		if (r == 21) // pyta� jest 20, wi�c je�li r przekroczy 20 musi zosta�
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
			int n = JOptionPane.showOptionDialog(null, "Kt�re miasto jest stolic� Kambod�y?", "Pytanie nr: " + (p + 1),
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
					"Jak si� nazywa wokalista prowadz�cy w zespole muzycznym 'Linkin Park'?", "Pytanie nr: " + (p + 1),
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
			int n = JOptionPane.showOptionDialog(null, "W kt�rym roku mia�a miejsce Bitwa pod Termopilami?",
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
			Object[] options = { "Konrad Wallenrod", "Konfederaci barscy", "Pierwiosnek", "Roz��czenie" };
			int n = JOptionPane.showOptionDialog(null,
					"Kt�re z wymienionych dzie� nie zosta�o napisane przez Adama Mickiewicza?",
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
			Object[] options = { "W�troba", "�ledziona", "M�d�ek", "Trzustka" };
			int n = JOptionPane.showOptionDialog(null, "Najwi�kszym gruczo�em cz�owieka jest...",
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
			Object[] options = { "Pablo Picasso", "Micha� Anio�", "Salvador Dali", "Leonardo da Vinci" };
			int n = JOptionPane.showOptionDialog(null, "Kto namalowa� obraz 'Trwa�o�� Pami�ci'?",
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
			Object[] options = { "z Anglii", "z Argentyny", "z Zanzibaru", "ze Stan�w Zjednoczonych" };
			int n = JOptionPane.showOptionDialog(null, "Sk�d pochodzi� Freddie Mercury, wokalista grupy Queen",
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
			int n = JOptionPane.showOptionDialog(null, "Co jest jednostk� konduktancji w uk�adzie SI?",
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
			Object[] options = { "tchnienie wiatru", "pusta r�ka", "droga b�yskawicy", "spokojna droga" };
			int n = JOptionPane.showOptionDialog(null, "Co dos�ownie oznacza s�owo 'karate'?", "Pytanie nr: " + (p + 1),
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
			int n = JOptionPane.showOptionDialog(null, "Kt�re obiekty we wszech�wiecie �wiec� najja�niej?",
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
					"Kto jest autorem s��w Cogito ergo sum(�ac.�My�l�, wi�c jestem�?", "Pytanie nr: " + (p + 1),
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
			Object[] options = { "w Ameryce P�nocnej", "w Ameryce Po�udniowej", "w Europie", "w Australii" };
			int n = JOptionPane.showOptionDialog(null,
					"Na kt�rym kontynencie znajduje si� najmniej aktywnych wulkan�w?", "Pytanie nr: " + (p + 1),
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
			Object[] options = { "�niardwy", "Wigry", "Ha�cza", "Morskie Oko" };
			int n = JOptionPane.showOptionDialog(null, "Kt�re jezioro jest nag��bsze w Polsce?",
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
			int n = JOptionPane.showOptionDialog(null, "Kt�ry kraj nazywa si� w swoim j�zyku 'Suomi'?",
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
			int n = JOptionPane.showOptionDialog(null, "Kto odkry� Now� Zelandi�?", "Pytanie nr: " + (p + 1),
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
			int n = JOptionPane.showOptionDialog(null, "Jak nazywa si� bezglebowa uprawa ro�lin na po�ywkach wodnych?",
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
			int n = JOptionPane.showOptionDialog(null, "W kt�rym mie�cie jest najwi�kszy port w Europie?",
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
			int n = JOptionPane.showOptionDialog(null, "Ile typowo wa�y ludzka g�owa?", "Pytanie nr: " + (p + 1),
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
			Object[] options = { "Na Pacyfiku", "Na Morzu Norweskim", "W trzustce", "w w�trobie" };
			int n = JOptionPane.showOptionDialog(null, "Gdzie znajduj� si� wyspy Langerhansa?",
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
			Object[] options = { "Pulp Fiction", "Kill Bill I", "Cztery pokoje", "W�ciek�e psy" };
			int n = JOptionPane.showOptionDialog(null,
					"W kt�rym z przez siebie re�yserowanych film�w NIE zagra� Quentin Tarantino?",
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
			int n = JOptionPane.showOptionDialog(null, "Ile klawiszy ma typowy wsp�czesny fortepian?",
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