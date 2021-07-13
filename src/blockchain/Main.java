package blockchain;

import blockchain.util.SerializationUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String filename = "blockchain.data";
        Blockchain blockchain;

        try {
            blockchain = (Blockchain) SerializationUtil.deserialize(filename);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter how many zeros the hash must start with: ");
            int zeros = scanner.nextInt();

            blockchain = new Blockchain(zeros);
        }


        for (int i = 0; i < 5; i++) {

            long start = System.currentTimeMillis();
            System.out.println(blockchain.newBlock());
            long end = System.currentTimeMillis();
            System.out.printf("Block was generating for %d seconds\n\n",(int) ((end - start) / 1000));
        }

        try {
            SerializationUtil.serialize(blockchain, filename);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}




