package blockchain;

import java.util.Date;
import java.util.Objects;

public class Block {
    private int id;
    private long timeStamp;
    private String hash;
    private Block previous;

    public Block() {
        this.id = 1;
        this.timeStamp = new Date().getTime();
        this.hash = StringUtil.applySha256(""+getId()+getTimeStamp());
        this.previous = null;
    }

    public Block(Block previous) {
        this.previous = previous;
        this.timeStamp = new Date().getTime();
        this.id = previous.getId() + 1;
        this.hash = computeHashCode();
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

    public Block getPrevious() {
        return previous;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Block block = (Block) o;
        return getId() == block.getId() && getTimeStamp() == block.getTimeStamp() && getHash().equals(block.getHash()) && Objects.equals(getPrevious(), block.getPrevious());
    }

    private String computeHashCode() {
        return StringUtil.applySha256(""+getId()+getTimeStamp()+getPrevious().hashCode());
    }

    @Override
    public String toString() {
        return "Block:\n" +
                "Id: " + getId() + "\n" +
                "Timestamp: " + getTimeStamp() + "\n" +
                "Hash of the previous block:\n" + (getPrevious() == null ? "0" : getPrevious().getHash()) + "\n" +
                "Hash of the block:\n" + getHash() + "\n";
    }
}
