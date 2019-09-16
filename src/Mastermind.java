import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Mastermind {
    // Variablen welche über das ganze Programm benötigt werden
    private BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
	private Mastermind_Colors mastermind_colors = new Mastermind_Colors();
	private char[] masterCode = new char[4];
	private char[] eingegebenerCode = new char[4];

	public static void main(String[] args) throws IOException {
        Mastermind m = new Mastermind();
        m.einleitung();
        System.out.println();
        m.generiereCode();

        boolean ergebnis = m.rateVersuche();
        if(ergebnis){
            System.out.println("Glückwunsch, du hast den Code erraten!");
        }
        else {
            System.out.println("Du hast den Code leider nicht erraten. Der Master Code wäre dieser gewesen: ");
            for(char c: m.masterCode){
                System.out.print(c);
            }
        }
	}

	// Methoden

	private void einleitung(){
        System.out.println("Willkommen zu Mastermind. Erstellt von Ruben Nauer und Gion Rubitschung");
        System.out.println("Diese Farben stehen Ihnen zur Verfügung: ");
        for(int i = 0; i < mastermind_colors.colors.length; i++){
            System.out.print(mastermind_colors.colors[i] + ": steht für " + mastermind_colors.colorsWritten[i]);
            System.out.println();
        }
        System.out.println("Das Zeichen \"/\" steht dafür, dass Sie eine Farbe richtig haben, diese jedoch nicht an der richtigen Position steht.");
        System.out.println("Das Zeichen \"X\" steht dafür, dass Sie eine Farbe richtig haben und diese an der richtigen Position steht.");
        System.out.println("Du hast acht Versuche. Viel Glück!");
    }

	private void generiereCode(){
		for (int i = 0; i < masterCode.length; i++) {
			int rnd = new Random().nextInt(6) + 1;
			masterCode[i] = mastermind_colors.colors[rnd - 1];
		}
	}

	private boolean rateVersuche() throws IOException {
	    int versuche = 8;
	    for(int i = 0; i < versuche; i++){
            boolean validierteEingabe = false;
	        do {
                System.out.print("Gib deinen Versuch ein: ");
                String code = console.readLine();
                eingegebenerCode = code.toCharArray();
                if(validiereEingabe()){
                    validierteEingabe = true;
                    boolean proovenCode = checkCode();
                    if(proovenCode){
                        return true;
                    }
                }
                else{
                    System.out.println("Deine Eingabe enthält ungültige Zeichen oder deine Eingabe ist zu kurz oder zu lang! Versuche es nochmals!");
                }
            }while (!validierteEingabe);
        }
	    return false;
    }

    private boolean validiereEingabe(){
	    boolean validStatus = false;
	    if(eingegebenerCode.length == masterCode.length){
            for(char c: eingegebenerCode){
                boolean enthaelt = new String(mastermind_colors.colors).contains(String.valueOf(c));
                if(!enthaelt){
                    validStatus = false;
                    break;
                }
                else{
                    validStatus = true;
                }
            }
        }
	    return validStatus;
    }

	private boolean checkCode(){
	    StringBuilder check = new StringBuilder();
        for(int i = 0; i < eingegebenerCode.length; i++){
            boolean enthaelt = Arrays.toString(masterCode).contains(String.valueOf(eingegebenerCode[i]));
            if (enthaelt){
                if (String.valueOf(masterCode[i]).equals(String.valueOf(eingegebenerCode[i]))) {
                    check.append("X");
                } else {
                    check.append("/");
                }
            }
        }
        for(char c: eingegebenerCode){
            System.out.print(c);
        }
        char[] sortedCode = check.toString().toCharArray();
        Arrays.sort(sortedCode);
        String checkedCode = new String(sortedCode);
        System.out.print("      ");
        System.out.println(checkedCode);
        return checkedCode.equals("XXXX");
    }

}
