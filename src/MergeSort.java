import Classes.EventProfiler;
import Classes.Sorters.ParallelMergeSorter;
import Classes.Sorters.SerialMergeSorter;
import Classes.Transaction;
import Classes.Utils;

import java.util.List;


public class MergeSort {
  public static void main(String[] args) throws InterruptedException {
    EventProfiler profiler = new EventProfiler(true);
    profiler.start();

    List<Transaction> transactions = Transaction.generateList(10000000);
    profiler.log("Filling array with " + transactions.size() + " transactions");
    System.out.println();


    /** Serial merge sorting. **/
    profiler.start();

    Transaction[] TransactionArray = transactions.toArray(new Transaction[transactions.size()]);
    SerialMergeSorter.serialMergeSort(TransactionArray);

    if (Utils.isFilledArray(TransactionArray)) {
      profiler.log("Serial MergeSort Done");
      Integer size = TransactionArray.length;

      if (size >= 2) {
        profiler.log("First bank in the list is: " + TransactionArray[0].getBank());
        profiler.log("Last bank in the list is: " + TransactionArray[size - 1].getBank());
      }
    } else {
      profiler.log("Serial MergeSort failed");
    }

    System.out.println();

    /** Parallel merge sorting **/
    Transaction[] arrayForOwnParallelSort = transactions.toArray(new Transaction[transactions.size()]);

    profiler.start();
    ParallelMergeSorter.parallelMergeSort(arrayForOwnParallelSort);

    if (Utils.isFilledArray(arrayForOwnParallelSort)) {
      profiler.log("Parallel MergeSort Done");
      Integer size = arrayForOwnParallelSort.length;

      if (size >= 2) {
        profiler.log("First bank in the list is: " + arrayForOwnParallelSort[0].getBank());
        profiler.log("Last bank in the list is: " + arrayForOwnParallelSort[size - 1].getBank());
      }
    } else {
      profiler.log("Parallel MergeSort failed");
    }
  }
}

