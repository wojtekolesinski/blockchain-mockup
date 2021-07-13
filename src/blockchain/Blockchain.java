package blockchain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Blockchain implements Serializable {
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
        return blocks.stream()
                .filter(b -> b == block)
                .collect(Collectors.toList())
                .size() == 1;
    }
}
