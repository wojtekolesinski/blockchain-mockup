package blockchain;

import blockchain.util.SerializationUtil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Main {
    public static void main(String[] args) {
        String filename = "blockchain.data";
        Blockchain blockchain = new Blockchain();

//        try {
//            blockchain = (Blockchain) SerializationUtil.deserialize(filename);
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//            blockchain = new Blockchain();
//        }

        Blockchain finalBlockchain = blockchain;
        List<Miner> miners = IntStream.range(1, 50)
                .mapToObj(x -> new Miner(finalBlockchain, x))
                .collect(Collectors.toList());

//        List<Thread> threads = miners.stream().map(m -> new Thread(m)).collect(Collectors.toList());
//        threads.forEach(t -> t.start());

        ExecutorService executor = Executors.newFixedThreadPool(5);
        miners.forEach(executor::submit);

        executor.shutdown();
        try {
            int id = 1;
            while (id < 5) {
                id = blockchain.getLast() == null ? 1 : blockchain.getLast().getId();
                TimeUnit.MILLISECONDS.sleep(10);
            }
            System.out.println("\n\n" + id + "\n\n");
            executor.shutdownNow();
            executor.awaitTermination(1, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        try {
//            SerializationUtil.serialize(blockchain, filename);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println(finalBlockchain.getLast());
    }

}