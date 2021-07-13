package blockchain;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter how many zeros the hash must start with: ");
        int zeros = scanner.nextInt();

        Blockchain blockchain = new Blockchain(zeros);

        for (int i = 0; i < 5; i++) {

            long start = System.currentTimeMillis();
            System.out.println(blockchain.newBlock());
            long end = System.currentTimeMillis();
            System.out.printf("Block was generating for %d seconds\n\n",(int) ((end - start) / 1000));
        }

    }
}




