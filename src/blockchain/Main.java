package blockchain;

import blockchain.clients.Miner;
import blockchain.clients.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class Main {
    volatile static ArrayList<String> messages = new ArrayList<>(List.of(
            "Tom: Hey, I'm first!",
            "Sarah: It's not fair!",
            "Sarah: You always will be first because it is your blockchain!",
            "Sarah: Anyway, thank you for this amazing chat.",
            "Tom: You're welcome :)",
            "Nick: Hey Tom, nice chat"
    ));

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
        List<Miner> miners = IntStream.rangeClosed(1, 20)
                .mapToObj(x -> new Miner(finalBlockchain, x))
                .collect(Collectors.toList());

        List<User> users = IntStream.rangeClosed(1, messages.size())
                .mapToObj(x -> new User(finalBlockchain, messages))
                .collect(Collectors.toList());

//        List<Thread> threads = Stream.concat(miners.stream().map(Thread::new), users.stream().map(Thread::new)).collect(Collectors.toList());
//        threads.forEach(Thread::start);

        ExecutorService minersExecutor = Executors.newFixedThreadPool(20);
        ExecutorService usersExecutor = Executors.newFixedThreadPool(6);


        users.forEach(minersExecutor::submit);
        miners.forEach(minersExecutor::submit);

//        minersExecutor.shutdown();
//        try {
//            int id = 1;
//            while (id < 5) {
//                id = blockchain.getLast() == null ? 1 : blockchain.getLast().getId();
//                TimeUnit.MILLISECONDS.sleep(10);
//            }
//            System.out.println("\n\n" + id + "\n\n");
//            minersExecutor.shutdownNow();
//            minersExecutor.awaitTermination(1, TimeUnit.MILLISECONDS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        try {
//            SerializationUtil.serialize(blockchain, filename);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println(finalBlockchain.getLast());
    }

}