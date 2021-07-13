package blockchain;

import blockchain.util.StringUtil;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Block implements Serializable {
    private int id;
    private long timeStamp;
    private String hash;
    private String previousHash;
    private int magicNumber;


    public Block(int id, String previousHash) {
        this.id = id;
        this.previousHash = previousHash;
        this.magicNumber = (int) (Math.random() * 100_000_000);
        this.timeStamp = new Date().getTime();
        this.hash = computeHashCode();
    }

    public int getMagicNumber() {
        return magicNumber;
    }

    public int getId() {
        return id;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public String getHash() {
        return hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Block block = (Block) o;
        return getId() == block.getId() && getTimeStamp() == block.getTimeStamp() && getHash().equals(block.getHash()) && Objects.equals(getPreviousHash(), block.getPreviousHash());
    }

    private String computeHashCode() {
        return StringUtil.applySha256(""+getId()+getTimeStamp()+getMagicNumber()+getPreviousHash());
    }

    @Override
    public String toString() {
        return "Block:\n" +
                "Id: " + getId() + "\n" +
                "Timestamp: " + getTimeStamp() + "\n" +
                "Magic number: " + getMagicNumber() + "\n" +
                "Hash of the previous block:\n" + (getPreviousHash() == null ? "0" : getPreviousHash()) + "\n" +
                "Hash of the block:\n" + getHash() + "";
    }
}
