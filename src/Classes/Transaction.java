package Classes;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.SplittableRandom;


public class Transaction {
  private Integer _id;
  private String _bank;
  private BigInteger _number;

  private static final String[] listOfBanks = { "ABN", "Triodos", "ING", "SNS", "ASR", "ASN", "Rabobank", "NIBC" };

  public Transaction() { /* for now not needed, but needs to be defined. */ }

  public String getBank() {
    return _bank;
  }

  public static List<Transaction> generateList(Integer amount) {
    List<Transaction> transactionsList = new ArrayList<Transaction>();

    for (Integer i = 0; i < amount; i++) {
      Transaction transaction = new Transaction();
      transaction._id = (i + 1);
      transaction._bank = listOfBanks[getRandomNumber()];
      transaction._number = getRandomBigInteger();

      transactionsList.add(transaction);
    }

    return transactionsList;
  }

  public int compareTo(Transaction transaction) {
    return getBank().compareTo(transaction.getBank());
  }

  private static Integer getRandomNumber() {
    int number = new SplittableRandom().nextInt(0, 8);
    return number;
  }

  private static BigInteger getRandomBigInteger() {
    Random rand = new Random();
    BigInteger result = new BigInteger(4, rand);
    return result;
  }
}
