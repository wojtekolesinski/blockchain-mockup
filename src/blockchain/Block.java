package blockchain;

import blockchain.util.StringUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Block implements Serializable {
    private final int id;
    private final long timeStamp;
    private final String hash;
    private final String previousHash;
    private final int magicNumber;
    private final String creator;
    private ArrayList<String> data;


    public Block(int id, String previousHash, String creator) {
        this.id = id;
        this.previousHash = previousHash;
        this.creator = creator;
        this.magicNumber = (int) (Math.random() * 100_000_000);
        this.timeStamp = new Date().getTime();
        this.hash = computeHashCode();
        this.data = new ArrayList<>();
    }

    public int getMagicNumber() {
        return magicNumber;
    }

    public int getId() {
        return id;
    }

    public String getCreator() {
        return creator;
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

    public void setData(ArrayList<String> data) {
        this.data = data;
    }

    public void addMessage(String message) {
        data.add(message);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Block block = (Block) o;
        return getId() == block.getId() && getTimeStamp() == block.getTimeStamp() && getHash().equals(block.getHash()) && Objects.equals(getPreviousHash(), block.getPreviousHash());
    }

    private String printData() {
        StringBuilder builder = new StringBuilder();
        data.forEach(m -> builder.append("\n" + m));
        return builder.toString();
    }

    private String computeHashCode() {
        return StringUtil.applySha256(""+getId()+getTimeStamp()+getMagicNumber()+getPreviousHash()+getCreator());
    }

    @Override
    public String toString() {
        return "Block: \n" +
                "Created by " + getCreator() + "\n" +
                "Id: " + getId() + "\n" +
                "Timestamp: " + getTimeStamp() + "\n" +
                "Magic number: " + getMagicNumber() + "\n" +
                "Hash of the previous block:\n" + (getPreviousHash() == null ? "0" : getPreviousHash()) + "\n" +
                "Hash of the block:\n" + getHash() + "\n" +
                "Block data: " + (data.size() == 0 ? "no messages" : printData());
    }
}
