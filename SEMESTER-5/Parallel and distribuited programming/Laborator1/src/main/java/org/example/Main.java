package org.example;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static int[] generator(int n, int max) {
        int[] vector = new int[n];
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            vector[i] = rand.nextInt(max);
        }
        return vector;
    }

    public static void main(String[] args) {
        int n = 100000, max = 50000;
        int[] A = generator(n, max);
        int[] B = generator(n, max);
        int[] C = new int[n];
        long start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            C[i] = A[i] + B[i];
        }
        long end = System.currentTimeMillis();
        System.out.print("C: ");
        System.out.println(end - start);
        int[] C2 = new int[n];
        int p = 31;
        newThread[] threads = new newThread[p];
        long startThread = System.currentTimeMillis();
        for (int i = 0; i < p; i++) {
            newThread thread = new newThread(i, p, n, A, B, C2);
            threads[i] = thread;
            thread.start();
        }
        for (int i = 0; i < p; i++) {
            try {
                threads[i].join();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        long endThread = System.currentTimeMillis();
        System.out.print("C2: ");
        System.out.println(endThread - startThread);

        int[] C3 = new int[n];
        sequentialThead[] sequentialThreads = new sequentialThead[p];
        start = System.currentTimeMillis();
        for (int i = 0; i < p - 1; i++) {
            sequentialThead seqThread = new sequentialThead(i * n / p, (i + 1) * n / p, A, B, C3);
            sequentialThreads[i] = seqThread;
            seqThread.start();
        }
        int stop = p - 1;
        if(n%p != 0) {
            sequentialThead seqThread = new sequentialThead((p - 1) * n / p, n, A, B, C3);
            sequentialThreads[p - 1] = seqThread;
            seqThread.start();
            stop = p;
        }
        for (int i = 0; i < stop; i++) {
            try {
                sequentialThreads[i].join();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
//        System.out.print("Rezultat: " + Arrays.toString(C3));
        end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public static class sequentialThead extends Thread {
        private int i, j;
        private int[] A, B, C;

        public sequentialThead(int i, int j, int[] a, int[] b, int[] c) {
            this.i = i;
            this.j = j;
            A = a;
            B = b;
            C = c;
        }

        public void run() {
            for (int k = i; k < j; k++) {
                C[k] = A[k] + B[k];
            }
        }
    }

    public static class newThread extends Thread {
        private int id, p, n;
        private int[] A, B, C;

        public newThread(int id, int p, int n, int[] a, int[] b, int[] c) {
            this.id = id;
            this.p = p;
            this.n = n;
            A = a;
            B = b;
            C = c;
        }

        public void run() {
            for (int i = id; i < n; i += p) {
                C[i] = A[i] + B[i];
            }
        }
    }
}