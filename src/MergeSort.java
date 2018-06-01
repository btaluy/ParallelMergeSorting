import Classes.EventProfiler;
import Classes.Sorters.ParallelMergeSorter;
import Classes.Sorters.SerialMergeSorter;
import Classes.Transaction;
import Classes.Utils;

import java.util.List;


public class MergeSort {
  public static void main(String[] args) {
    EventProfiler profiler = new EventProfiler(true);

    // Generates a list of 20 mil transactions
    List<Transaction> transactions = Transaction.generateList(20000000);
    benchmark(transactions, profiler);


    // Generates a list of 10 mil transactions
    transactions = Transaction.generateList(10000000);
    benchmark(transactions, profiler);


    // Generates a list of 1 mil transactions
    transactions = Transaction.generateList(1000000);
    benchmark(transactions, profiler);


    // Generates a list of 100k transactions
    transactions = Transaction.generateList(100000);
    benchmark(transactions, profiler);


    // Generates a list of 10k transactions
    transactions = Transaction.generateList(10000);
    benchmark(transactions, profiler);
  }

  private static void benchmark(List<Transaction> transactions, EventProfiler profiler) {
    profiler.start();
    generateText(profiler, transactions.size());

    System.out.println("-----------------------------------------------------------------");
    serialBenchmark(transactions, profiler);
    // Uses two threads for the parallel mergesort.
    parallelBenchmark(transactions, 2, profiler);

    // Uses four threads for the parallel mergesort.
    parallelBenchmark(transactions, 4, profiler);

    // Uses six threads for the parallel mergesort.
    parallelBenchmark(transactions, 6, profiler);

    // Uses eight threads for the parallel mergesort.
    parallelBenchmark(transactions, 8, profiler);

    System.out.println("-----------------------------------------------------------------\n\n");
  }


  private static void serialBenchmark(List<Transaction> transactions, EventProfiler profiler) {
    /** Serial merge sorting. **/
    profiler.start();

    Transaction[] TransactionArray = transactions.toArray(new Transaction[transactions.size()]);
    SerialMergeSorter.serialMergeSort(TransactionArray);

    if (Utils.isFilledArray(TransactionArray)) {
      profiler.log("Serial MergeSort Done");
    } else {
      profiler.log("Serial MergeSort failed");
    }
  }

  private static void parallelBenchmark(List<Transaction> transactions, Integer threadsAmount, EventProfiler profiler) {
    /** Parallel merge sorting **/
    Transaction[] arrayForOwnParallelSort = transactions.toArray(new Transaction[transactions.size()]);

    profiler.start();
    ParallelMergeSorter.parallelMergeSort(arrayForOwnParallelSort, threadsAmount);

    if (Utils.isFilledArray(arrayForOwnParallelSort)) {
      profiler.log("Parallel MergeSort Done while using " + threadsAmount + " threads");
    } else {
      profiler.log("Parallel MergeSort failed");
    }
  }

  private static void generateText(EventProfiler profiler, Integer size) {
    System.out.println("////////////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");
    profiler.log("Filling array with " + size + " transactions");
    System.out.println("\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\///////////////////////////");
    System.out.println();
  }
}

