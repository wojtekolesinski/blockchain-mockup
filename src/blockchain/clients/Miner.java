package blockchain.clients;

import blockchain.Block;
import blockchain.Blockchain;

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
        while (!added) {

            do {
                last = blockchain.getLast();
                block = (last == null ? new Block(1, null, getName()) : new Block(last.getId() + 1, last.getHash(), getName()));
            } while (!blockchain.validate(block));
            added = blockchain.addBlock(block);
        }
    }
}
