import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import enums.*;
import services.*;

public class Mastermind {
    // Variablen welche über das ganze Programm benötigt werden
    private BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
    private char[] masterCode = new char[4];
    private char[] eingegebenerCode = new char[4];

    private CodeService _CodeService = new CodeService();

    public static void main(String[] args) throws IOException {
        Mastermind m = new Mastermind();
        m.einleitung();
        System.out.println();
        m.masterCode = m._CodeService.generiereCode();

        boolean ergebnis = m.rateVersuche();
        if (ergebnis) {
            System.out.println("Glückwunsch, du hast den Code erraten!");
        } else {
            System.out.println("Du hast den Code leider nicht erraten. Der Master Code wäre dieser gewesen: ");
            for (char c : m.masterCode) {
                System.out.print(c);
            }
        }
    }

    // Methoden

    private void einleitung() {
        System.out.println("Willkommen zu Mastermind. Erstellt von Ruben Nauer und Gion Rubitschung");
        System.out.println("Diese Farben stehen Ihnen zur Verfügung: ");
        for (int i = 0; i < Colors.colors.length; i++) {
            System.out.print(Colors.colors[i] + ": steht für " + Colors.colorsWritten[i]);
            System.out.println();
        }
        System.out.println(
                "Das Zeichen \"/\" steht dafür, dass Sie eine Farbe richtig haben, diese jedoch nicht an der richtigen Position steht.");
        System.out.println(
                "Das Zeichen \"X\" steht dafür, dass Sie eine Farbe richtig haben und diese an der richtigen Position steht.");
        System.out.println("Du hast acht Versuche. Viel Glück!");
    }

    private boolean rateVersuche() throws IOException {
        int versuche = 8;
        for (int i = 0; i < versuche; i++) {
            boolean validierteEingabe = false;
            do {
                System.out.print((i + 1) + ". Versuch: ");
                String code = console.readLine();
                eingegebenerCode = code.toCharArray();
                if (_CodeService.validiereEingabe(eingegebenerCode)) {
                    validierteEingabe = true;
                    boolean proovenCode = _CodeService.checkCode(eingegebenerCode, masterCode);
                    if (proovenCode) {
                        return true;
                    }
                } else {
                    System.out.println(
                            "Deine Eingabe enthält ungültige Zeichen oder deine Eingabe ist zu kurz oder zu lang! Versuche es nochmals!");
                }
            } while (!validierteEingabe);
        }
        return false;
    }

}
