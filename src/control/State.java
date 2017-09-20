/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import panda_hmac.PANDA_HMAC;

/**
 *
 * @author KoRiSnIk
 */
public class State {
    
    private static State state;
    
    private HashInfo selectedHash;
    
    private String kPlus, message;
    private String ipad, opad;
    private String Si, So;
    private String firstHResult, secondHResult;
    
    private String hmac;
    
    private State() {
        
    }
    
    private String makeIpad() {
        if (ipad == null || "".equals(ipad)) {
            ipad = makePad("36");
        }
        return ipad;
    }
    
    private String makeOpad() {
        if (opad == null || "".equals(opad)) {
            opad = makePad("5C");
        }
        return opad;
    }
    
    private String makePad(String val) {
        StringBuilder pad = new StringBuilder(kPlus.length());
        for (int i = 0; i < kPlus.length(); i += 2) {
            pad.append(val);
        }
        return pad.toString();
    }
    
    private void calcSi() {
        StringBuilder SiB = new StringBuilder(kPlus.length());
        for (int i = 0; i < kPlus.length(); i++) {
            SiB.append(xor(kPlus.charAt(i), ipad.charAt(i)));
        }
        Si = SiB.toString();
    }
    
    private void calcSo() {
        StringBuilder SoB = new StringBuilder(kPlus.length());
        for (int i = 0; i < kPlus.length(); i++) {
            SoB.append(xor(kPlus.charAt(i), opad.charAt(i)));
        }
        So = SoB.toString();
    }
    
    private char xor(char first, char second) {
        return intToChar(charToInt(first) ^ charToInt(second));
    }
    
    private int charToInt(char c) {
        switch (c) {
            case '0': return 0;
            case '1': return 1;
            case '2': return 2;
            case '3': return 3;
            case '4': return 4;
            case '5': return 5;
            case '6': return 6;
            case '7': return 7;
            case '8': return 8;
            case '9': return 9;
            case 'A': return 10;
            case 'B': return 11;
            case 'C': return 12;
            case 'D': return 13;
            case 'E': return 14;
            case 'F': return 15;
        }
        return 0;
    }
    
    private char intToChar(int i) {
        switch (i) {
            case 0: return '0';
            case 1: return '1';
            case 2: return '2';
            case 3: return '3';
            case 4: return '4';
            case 5: return '5';
            case 6: return '6';
            case 7: return '7';
            case 8: return '8';
            case 9: return '9';
            case 10: return 'A';
            case 11: return 'B';
            case 12: return 'C';
            case 13: return 'D';
            case 14: return 'E';
            case 15: return 'F';
        }
        return '0';
    }
    
    public static State getInstance() {
        if (state == null) {
            state = new State();
        }
        return state;
    }

    public HashInfo getSelectedHash() {
        return selectedHash;
    }
    
    public void setSelectedHash(String hashName) {
        this.selectedHash = PANDA_HMAC.hashInfoMap.get(hashName);
    }

    public void setSelectedHash(HashInfo selectedHash) {
        this.selectedHash = selectedHash;
    }

    public String getkPlus() {
        return kPlus;
    }

    public void setkPlus(String kPlus) {
        this.kPlus = kPlus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public void calculateAll() {
        System.out.println("K0 IS: " + kPlus);
        makeIpad();
        System.out.println("IPAD IS: " + ipad);
        makeOpad();
        System.out.println("OPAD IS: " + opad);
        calcSi();
        System.out.println("Si IS: " + Si);
        calcSo();
        System.out.println("So IS: " + So);
        firstHResult = selectedHash.getHashValue(Si + message);
        System.out.println("HASH IS: " + firstHResult);
        secondHResult = selectedHash.getHashValue(So + firstHResult);
        System.out.println("HASH IS: " + secondHResult);
    }

    public String getIpad() {
        return ipad;
    }

    public void setIpad(String ipad) {
        this.ipad = ipad;
    }

    public String getOpad() {
        return opad;
    }

    public void setOpad(String opad) {
        this.opad = opad;
    }

    public String getSi() {
        return Si;
    }

    public void setSi(String Si) {
        this.Si = Si;
    }

    public String getSo() {
        return So;
    }

    public void setSo(String So) {
        this.So = So;
    }

    public String getFirstHResult() {
        return firstHResult;
    }

    public void setFirstHResult(String firstHResult) {
        this.firstHResult = firstHResult;
    }

    public String getSecondHResult() {
        return secondHResult;
    }

    public void setSecondHResult(String secondHResult) {
        this.secondHResult = secondHResult;
    }
    
    
    
}
