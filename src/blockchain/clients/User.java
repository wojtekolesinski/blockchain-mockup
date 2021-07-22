package blockchain.clients;

import blockchain.Blockchain;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class User implements Runnable {

    private Blockchain blockchain;
    private ArrayList<String> messages;

    public User(Blockchain blockchain, ArrayList<String> messages) {
        this.blockchain = blockchain;
        this.messages = messages;
    }

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep((long)(Math.random() * 10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (messages.isEmpty()) return;
        System.out.println("====================================================================");
        blockchain.addMessage(messages.remove(0));
    }
}
