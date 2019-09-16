import java.io.IOException;
import services.*;

public class Mastermind {
    private char[] masterCode = new char[4];

    private CodeService _CodeService = new CodeService();
    private OutputService _OutputService = new OutputService();

    public static void main(String[] args) throws IOException {
        Mastermind m = new Mastermind();
        m.masterCode = m._CodeService.generiereCode();
        m._OutputService.einleitung();
        System.out.println();
        m._OutputService.ergebnis(
            m._OutputService.rateVersuche(m.masterCode), 
            m.masterCode
            );
    }
}
