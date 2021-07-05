package blockchain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Blockchain {
    private List<Block> blocks;

    public Blockchain() {
        blocks = new ArrayList<>();
    }

    public Block newBlock() {
        Block block;
        if (blocks.size() == 0) {
            block = new Block();
        } else {
            block = new Block(blocks.get(blocks.size()-1));
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
