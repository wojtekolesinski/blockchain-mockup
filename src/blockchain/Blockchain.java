package blockchain;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Blockchain implements Serializable {
    private static final long serialVersionUID = 1L;
    private int noOfZeros;
    private List<Block> blocks;

    public Blockchain() {
        noOfZeros = 0;
        blocks = new ArrayList<>();
    }

    /*public Block newBlock() {
        int id = blocks.size() + 1;
        String previousHash = id == 1 ? null : blocks.get(blocks.size()-1).getHash();
        Block block = null;

        boolean correct = false;

        while (!correct) {
            block = new Block(id, previousHash);
            correct = true;
            for (int i = 0; correct && i < noOfZeros; i++) {
                if (block.getHash().charAt(i) != '0') {
                    correct = false;
                }
            }
        }
        blocks.add(block);
        return block;
    }*/

    public synchronized boolean addBlock(Block block) {
        if (!validate(block)) return false;
        long generationTime = getLast() == null ? 0 : (block.getTimeStamp() - getLast().getTimeStamp()) / 1000;
        System.out.println(block);
        System.out.println("Block was generating for " + generationTime + " seconds");

        if (generationTime >= 60) {
            noOfZeros--;
            System.out.println("N was decreased to " + noOfZeros);
        } else if (generationTime < 15) {
            noOfZeros++;
            System.out.println("N was increased to " + noOfZeros);
        } else {
            System.out.println("N stays the same");
        }
        System.out.println();
        blocks.add(block);
        return true;
    }

    public boolean validate(Block block) {
        if (blocks.size() == 0) return true;
        for (int i = 0; i < blocks.size(); i++){
            if (block.getHash().equals(blocks.get(i).getHash())) {
                if (i == 0) return true;
                else if (block.getPreviousHash().equals(blocks.get(i-1).getHash())) return true;
            }
        }
        return block.getId() == blocks.size() + 1
                && block.getPreviousHash().equals(getLast().getHash())
                && block.getHash().startsWith(new String(new char[noOfZeros]).replace("\0", "0"));

    }

    public Block getLast() {
        return blocks.size() == 0 ? null : blocks.get(blocks.size()-1);
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException, InvalidBlockException {
        ois.defaultReadObject();
        for (Block block: blocks) {
            if(!validate(block)) {
                throw new InvalidBlockException();
            }
        }
    }
}
