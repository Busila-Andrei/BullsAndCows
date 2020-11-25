package org.example;

public class GenerateCode {
    private int lengthCode;
    private int possibleSymbols;
    private String code;


    public String generateCode(int n, int x){
        String AlphaNumericString ="0123456789"
                + "abcdefghijklmnopqrstuvxyz";


        String substring = AlphaNumericString.substring(0,x-1);

        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            int index = (int)(substring.length() * Math.random());


            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }

    public boolean hasDuplicateCharacter(int[] v){
        for (int i = 0; i < v.length; i++ ) {
            for (int j = 0; j < v.length; j++) {
                if (j != i & v[i] == v[j])
                    return true;
            }
        }


        return false;
    }

    public static int[] convertCodeToBite (String code){
        char[] ch = code.toCharArray();
        int[] bit = new int[ch.length];
        for (int i = 0; i < ch.length; i++)
            bit[i] = ch[i];
        return bit;
    }

    public void setLengthCode(int lengthCode) {
        this.lengthCode = lengthCode;
    }

    public void setPossibleSymbols(int possibleSymbols) {
        this.possibleSymbols = possibleSymbols;
    }

    public int getLengthCode() {
        return lengthCode;
    }

    public int getPossibleSymbols() {
        return possibleSymbols;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        while (hasDuplicateCharacter(convertCodeToBite(code))) {
            code = generateCode(getLengthCode(), getPossibleSymbols());
        }
        this.code = code;
    }
}
