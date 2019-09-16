package services;

import java.util.Arrays;
import java.util.Random;

import enums.*;

public class CodeService{
    Colors mastermind_colors = new Colors();

    public char[] generiereCode(){
        char[] masterCode = new char[4];
		for (int i = 0; i < masterCode.length; i++) {
			int rnd = new Random().nextInt(6) + 1;
			masterCode[i] = mastermind_colors.colors[rnd - 1];
        }
        return masterCode;
    }
    
    public boolean validiereEingabe(char[] code) {
        boolean validStatus = false;
        if (code.length == code.length) {
            for (char c : code) {
                boolean enthaelt = new String(mastermind_colors.colors).contains(String.valueOf(c));
                if (!enthaelt) {
                    validStatus = false;
                    break;
                } else {
                    validStatus = true;
                }
            }
        }
        return validStatus;
    }

    public boolean checkCode(char[] code, char[] masterCode) {
        StringBuilder check = new StringBuilder();
        for (int i = 0; i < code.length; i++) {
            boolean enthaelt = Arrays.toString(masterCode).contains(String.valueOf(code[i]));
            if (enthaelt) {
                if (String.valueOf(masterCode[i]).equals(String.valueOf(code[i]))) {
                    check.append("X");
                } else {
                    check.append("/");
                }
            }
        }
        for (char c : code) {
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