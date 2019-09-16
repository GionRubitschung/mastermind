import java.io.IOException;
import services.*;

public class Mastermind {
    private char[] masterCode = new char[4];

    private CodeService _CodeService = new CodeService();
    private UserService _UserService = new UserService();

    public static void main(String[] args) throws IOException {
        Mastermind m = new Mastermind();
        m.masterCode = m._CodeService.generiereCode();
        m._UserService.einleitung();
        System.out.println();
        m._UserService.ergebnis(
            m._UserService.rateVersuche(m.masterCode), 
            m.masterCode
            );
    }
}
