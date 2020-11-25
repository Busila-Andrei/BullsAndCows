package org.example;

import org.example.utils.ScannerUtils;

public class Start {

    private final GenerateCode generateCode = new GenerateCode();

    public  void start() {
        if(inputCode())
            if (verifLength())
                findCode();
    }

    public boolean inputCode() {
        System.out.println("Input the length of the secret code: ");
        String codeLengthS = ScannerUtils.nextLine();

        if (isDigit(codeLengthS)) {
            System.out.println("System.out.println(Error: " + codeLengthS + " isn't a valid number.);");
        } else {
            System.out.println("Input the number of possible symbols in the code: ");
            String codeSymbolsS = ScannerUtils.nextLine();
            if (isDigit(codeSymbolsS)) {
                System.out.println("System.out.println(Error: " + codeSymbolsS + " isn't a valid number.);");
            } else {
                generateCode.setLengthCode(Integer.parseInt(codeLengthS));
                generateCode.setPossibleSymbols(Integer.parseInt(codeSymbolsS));
                return true;
            }

        }
        return false;
    }

    public boolean verifLength(){
        if (generateCode.getLengthCode() <= 0)
            System.out.println("Error: it's not possible to generate a code with a length of " + generateCode.getLengthCode());
        else if (generateCode.getLengthCode() > generateCode.getPossibleSymbols())
            System.out.println("Error: it's not possible to generate a code with a length of " + generateCode.getLengthCode() + " with " + generateCode.getPossibleSymbols() + " unique symbols.");
        else if (generateCode.getPossibleSymbols() < 0 || generateCode.getPossibleSymbols() > 36)
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
        else {
            print(generateCode.getLengthCode(), generateCode.getPossibleSymbols());
            generateCode.setCode(generateCode.generateCode(generateCode.getLengthCode(),generateCode.getPossibleSymbols()));
            System.out.println("Okay, let's start a game!");
            return true;
        }
        return false;
    }

    public static  boolean isDigit(String str){
        try {
            Integer.parseInt(str);
            return false;
        } catch(NumberFormatException e){
            return true;
        }
    }


    public static boolean findIdenticNumber(int[] cifNumber, int[] cifCode, int codeLength){
        int cow = 0, bull = 0;
        for (int i = 0; i < cifCode.length;i++) {
            for (int j = 0; j < cifNumber.length; j++) {
                if (i == j & cifCode[i] == cifNumber[j] )
                    bull++;
                else if (cifCode[i] == cifNumber[j])
                    cow++;
            }
        }
        boolean test = false;

        if(bull == codeLength) {
            System.out.println("Grade: " + bull + " bulls");
            System.out.println("Congratulations! You guessed the secret code.");
            test = true;
        } else if (bull > 1 & cow > 1)
            System.out.println("Grade: " + bull + " bulls and " + cow + " cows");
        else if (bull == 1 & cow == 1)
            System.out.println("Grade: " + bull + " bull and " + cow + " cow");
        else if (bull > 1 & cow == 1)
            System.out.println("Grade: " + bull + " bulls and " + cow + " cow");
        else if (bull == 1 & cow > 1)
            System.out.println("Grade: " + bull + " bull and " + cow + " cows");
        else if (bull == 1 & cow == 0)
            System.out.println("Grade: " + bull + " bull");
        else if (bull == 0 & cow == 1)
            System.out.println("Grade: " + cow + " cow");
        else if(bull == 0 & cow > 1)
            System.out.println("Grade: " + cow + " cows");
        else if(bull > 1 & cow == 0)
            System.out.println("Grade: " + bull + " bulls");
        else
            System.out.println("Grade: None");

        return test;

    }

    public static int[] convertCodeToBite (String code){
        char[] ch = code.toCharArray();
        int[] bit = new int[ch.length];
        for (int i = 0; i < ch.length; i++)
            bit[i] = ch[i];
        return bit;
    }

    public static void print(int codeLength,int lengthSymbol){
        String a = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder code = new StringBuilder();
        code.append("*".repeat(Math.max(0, codeLength)));
        StringBuilder range = new StringBuilder();

        if (lengthSymbol -10  == 1)
            range.append(", a");
        else if(lengthSymbol -10  == 0)
            range.append("");
        else
            range.append(", a-").append(a.charAt(lengthSymbol - 10 - 1));

        System.out.println("The secret is prepared: " + code.toString() +" (0-9" + range.toString() + ").");

    }

    public void findCode(){
        long turn = 1;
        int[] cifCode = convertCodeToBite(generateCode.getCode());
        int[] cifNumber;
        do {
            System.out.println("Turn " + turn + ":");
            String input = ScannerUtils.nextLine();
            cifNumber = convertCodeToBite(input);
            turn++;
        } while (!findIdenticNumber(cifNumber, cifCode, generateCode.getLengthCode()));
    }
}
