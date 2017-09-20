/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import org.bouncycastle.jcajce.provider.digest.BCMessageDigest;
import org.bouncycastle.jcajce.provider.digest.SHA3;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author KoRiSnIk
 */
public class HashInfo {

    private String name;
    
    private int blockSizeInHex, digestSizeInHex;
    
    private boolean hasIV;
    
    private int blockCells, blockCellRows, blockColumns;
    private int digestCells, digestCellRows, digestColumns;
    
    private BCMessageDigest md;
    
    private String toHexString(byte[] array) {
        return DatatypeConverter.printHexBinary(array);
    }
    
    private byte[] toByteArray(String s) {
        return DatatypeConverter.parseHexBinary(s);
    }

    public HashInfo(String name, int blockSizeInHex, int digestSizeInHex, boolean hasIV, int blockCellCount, int blockCellColumns, int digestCellCount, int digestCellColumns) {
        this.name = name;
        this.blockSizeInHex = blockSizeInHex;
        this.digestSizeInHex = digestSizeInHex;
        this.hasIV = hasIV;
        
        this.blockCells = blockCellCount;
        this.blockColumns = blockCellColumns;
        
        this.digestCells = digestCellCount;
        this.digestColumns = digestCellColumns;
    }
    
    public String getHashValue(String message) {
        switch (name) {
            case "SHA3-224": md = new SHA3.DigestSHA3(224); break;
            case "SHA3-256": md = new SHA3.DigestSHA3(256); break;
            case "SHA3-384": md = new SHA3.DigestSHA3(384); break;
            case "SHA3-512": md = new SHA3.DigestSHA3(512); break;
        }
        md.update(toByteArray(message));
        return toHexString(md.digest());
    }

    public String getName() {
        return name;
    }

    public int getBlockSizeInHex() {
        return blockSizeInHex;
    }

    public int getDigestSizeInHex() {
        return digestSizeInHex;
    }

    public boolean isHasIV() {
        return hasIV;
    }

    public int getBlockCells() {
        return blockCells;
    }

    public void setBlockCells(int blockCells) {
        this.blockCells = blockCells;
    }

    public int getBlockColumns() {
        return blockColumns;
    }

    public void setBlockColumns(int blockColumns) {
        this.blockColumns = blockColumns;
    }

    public int getDigestCells() {
        return digestCells;
    }

    public void setDigestCells(int digestCells) {
        this.digestCells = digestCells;
    }

    public int getDigestColumns() {
        return digestColumns;
    }

    public void setDigestColumns(int digestColumns) {
        this.digestColumns = digestColumns;
    }
    
}
