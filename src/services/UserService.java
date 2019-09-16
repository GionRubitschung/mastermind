package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import enums.Colors;

public class UserService {
    private BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

    private CodeService _CodeService = new CodeService();

    public void einleitung() {
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

    public boolean rateVersuche(char[] masterCode) throws IOException {
        int versuche = 8;
        for (int i = 0; i < versuche; i++) {
            boolean validierteEingabe = false;
            do {
                System.out.print((i + 1) + ". Versuch: ");
                String code = console.readLine();
                char[] eingegebenerCode = code.toCharArray();
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

    public void ergebnis(boolean ergebnis, char[] masterCode){
        if (ergebnis) {
            System.out.println("Glückwunsch, du hast den Code erraten!");
        } else {
            System.out.println("Du hast den Code leider nicht erraten. Der Master Code wäre dieser gewesen: ");
            for (char c : masterCode) {
                System.out.print(c);
            }
        }
    }
}