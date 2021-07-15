package blockchain;

import java.util.concurrent.Callable;

public class Miner implements Runnable {

    private Blockchain blockchain;
    private int id;

    public Miner(Blockchain blockchain, int id) {
        this.blockchain = blockchain;
        this.id = id;
    }

    public String getName() {
        return "miner # " + id;
    }

    @Override
    public void run() {
        Block block, last;
        boolean added = false;
        int blockId = 0;
        while (blockId <= 5) {
            while (!added && blockId < 5) {
                int counter = 0;
                do {
//                System.out.println("IM HERE " + id);
                    last = blockchain.getLast();
                    block = (last == null ? new Block(1, null, getName()) : new Block(last.getId() + 1, last.getHash(), getName()));
//                System.out.println(blockchain.validate(block));
//                System.out.println(counter++);
                } while (!blockchain.validate(block));
                added = blockchain.addBlock(block);
            }
        }
    }
}
