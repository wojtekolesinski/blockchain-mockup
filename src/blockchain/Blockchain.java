package blockchain;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Blockchain implements Serializable {
    private static final long serialVersionUID = 1L;
    private int noOfZeros;
    private List<Block> blocks;

    public Blockchain(int zeros) {
        noOfZeros = zeros;
        blocks = new ArrayList<>();
    }

    public Block newBlock() {
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
    }

    public boolean validate(Block block) {
        for (int i = 0; i < blocks.size(); i++){
            if (block.getHash().equals(blocks.get(i).getHash())) {
                if (i == 0) return true;
                else if (block.getPreviousHash().equals(blocks.get(i-1).getHash())) return true;
            }
        }
        return false;

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
