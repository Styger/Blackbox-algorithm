
public class main {
	public static int dimension = 9;
	public static int[][] array1 = new int[dimension + 1][dimension + 1];
	public static int farbindex = 4;
	public static int zaehler = 0;
	
    // Berechnung des Laserstrahls inklusive Ablenkungen 
    
	public static void rechne(int x, int y) {

		// Startkoordinaten sind in X und Y gespeichert
		
		//aktuell X und Y beschreibt den aktuellen Standort des Lichtstrahls (zu Beginn identisch mit Startkoordinaten)
		int aktuellX = x; 
		int aktuellY = y;

		// Variable für das nächste Feld in Richtung vorne geradeaus des Laserstrahls von aktuell X, Y
		int ri_mitteX;
		int ri_mitteY;

		// Variable für das nächste Feld in Richtung vorne Rechts des Laserstrahls von aktuell X, Y
		int ri_rechtsX;
		int ri_rechtsY;

		// Variable für das nächste Feld in Richtung vorne Links des Laserstrahls von aktuell X, Y
		int ri_linksX;
		int ri_linksY;

		// Festlegen der Richtung der drei zu überprüfenden Felder in Abhängigkeit der Startkoordinaten
		if (x == 0) { // Fall Osten
			ri_mitteX = +1;
			ri_mitteY = +0;
			ri_rechtsX=+1;
			ri_rechtsY=+1;
			ri_linksX=+1;
			ri_linksY=-1;
		} else if (y == 0) { // Fall Süden
			ri_mitteX = +0;
			ri_mitteY = +1;
			ri_rechtsX=-1;
			ri_rechtsY=+1;
			ri_linksX=+1;
			ri_linksY=+1;
		} else if (x == dimension) { // Fall Westen
			ri_mitteX = -1;
			ri_mitteY = +0;
			ri_rechtsX=-1;
			ri_rechtsY=-1;
			ri_linksX=-1;
			ri_linksY=+1;
		} else { // Fall Norden
			ri_mitteX = +0;
			ri_mitteY = -1;
			ri_rechtsX=+1;
			ri_rechtsY=-1;
			ri_linksX=-1;
			ri_linksY=-1;
		}
	
		// Testung der ersten drei Felder auf Atom (spezialfälle zu Beginn)
		// Felder die ein Atom beinhalten haben den Wert -1 (freie Felder sind 0)
		// Felder des Spielfeldrandes haben den Wert 1
		if (array1[aktuellX + ri_mitteX][aktuellY + ri_mitteY] == -1) {

			array1[x][y] = 2; //2 heisst schwarz
		} else if (array1[aktuellX + ri_rechtsX][aktuellY + ri_rechtsY] == -1
				|| array1[aktuellX + ri_linksX][aktuellY + ri_linksY] == -1) {
			array1[x][y] = 3; //3 heisst weiss
		}
		
		
		
		while (array1[x][y] == 1) {
			// Schritt vorwärts in Richtung des Laserstrahls...
			aktuellX += ri_mitteX;
			aktuellY += ri_mitteY;

			if (array1[aktuellX + ri_mitteX][aktuellY + ri_mitteY] == -1) {
				array1[x][y] = 2; //2 heisst schwarz
			} else if (array1[aktuellX + ri_rechtsX][aktuellY + ri_rechtsY] == -1
					&& array1[aktuellX + ri_linksX][aktuellY + ri_linksY] == -1) {
				array1[x][y] = 3; //3 heisst weiss
			// Testen, ob links ein Atom ist und falls ja: Richtung nach rechts drehen
			} else if (array1[aktuellX + ri_linksX][aktuellY + ri_linksY] == -1) {
				if (ri_mitteX == 0) {
					ri_mitteX = -ri_mitteY;
					ri_mitteY = 0;
					ri_rechtsX = ri_mitteX;
					ri_linksX = ri_mitteX;
					if (ri_mitteX > 0) {
						ri_rechtsY = +1;
						ri_linksY = -1;
					} else {
						ri_rechtsY = -1;
						ri_linksY = +1;
					}
				} else {
					ri_mitteY = +ri_mitteX;
					ri_mitteX = 0;
					ri_rechtsY = ri_mitteY;
					ri_linksY = ri_mitteY;
					if (ri_mitteY > 0) {
						ri_rechtsX = -1;
						ri_linksX = +1;
					} else {
						ri_rechtsX = +1;
						ri_linksX = -1;
					}
				}
			// Testen, ob rechts ein Atom ist und falls ja: Richtung nach links drehen	
			} else if (array1[aktuellX + ri_rechtsX][aktuellY + ri_rechtsY] == -1) {
				if (ri_mitteX == 0) {
					ri_mitteX = +ri_mitteY;
					ri_mitteY = 0;
					ri_rechtsX = ri_mitteX;
					ri_linksX = ri_mitteX;
					if (ri_mitteX > 0) {
						ri_rechtsY = +1;
						ri_linksY = -1;
					} else {
						ri_rechtsY = -1;
						ri_linksY = +1;
					}
				} else {
					ri_mitteY = -ri_mitteX;
					ri_mitteX = 0;
					ri_rechtsY = ri_mitteY;
					ri_linksY = ri_mitteY;
					if (ri_mitteY > 0) {
						ri_rechtsX = -1;
						ri_linksX = +1;
					} else {
						ri_rechtsX = +1;
						ri_linksX = -1;
					}
				}
			} 
			// Testen, ob geradeaus in der Mitte ein Rand ist. In diesem Fall muss der Lichtstrahl austreten und die Routine ist beendet.
			if (array1[aktuellX + ri_mitteX][aktuellY + ri_mitteY] >= 1) {
				//Einfärbung des Start- und Endfeldes mit der Farbe des Farbindexes
				array1[x][y] = farbindex;// somit wird der Wert 1 überschrieben (Abbruchkriterium)
				array1[aktuellX + ri_mitteX][aktuellY + ri_mitteY] = farbindex;
				farbindex++;
			}
			
			
		}

	}
	
	
	public static void buttonssetzen(){
		
		for(int i=0;i<=dimension;i++) {
			array1[i][0] = 1;
			array1[0][i] = 1;
			array1[i][dimension] = 1;
			array1[dimension][i] = 1;
		}
		
	}
	
	public static void darstellen(){
		int x = 0;
		int y = 0;
		for (int i = 1; i <= 10; i++) {
			System.out.println("|" + array1[x][y] + "|" + array1[x + 1][y] + "|" + array1[x + 2][y] + "|"
					+ array1[x + 3][y] + "|" + array1[x + 4][y] + "|" + array1[x + 5][y] + "|" + array1[x + 6][y] + "|"
					+ array1[x + 7][y] + "|" + array1[x + 8][y] + "|" + array1[x + 9][y] + "|");
			y++;
		}
	}

	public static void main(String[] args) {

		

		buttonssetzen();

		
		array1[4][2] = -1;
		array1[8][4] = -1;
		array1[6][7] = -1;

		
		rechne(6,0);
		rechne(0,6);
		rechne(4,9);
		rechne(9,6);
		
		darstellen();

		

	}

}
