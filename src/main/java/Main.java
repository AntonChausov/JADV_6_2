import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    static final int DEALS_COUNT = 10;
    static final double ACCURACY = 0.001;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Price for 1  (for example 5000):");
        double price = scanner.nextDouble();
        List<double[]> dealsList = generateDeals();
        System.out.println("Deals:");
        printDeals(dealsList);
        List<Boolean> checkResult = check(dealsList, price);
        System.out.println("Correct:");
        printResultList(checkResult, true);
        System.out.println("Incorrect:");
        printResultList(checkResult, false);
    }

    private static void printResultList(List<Boolean> checkResult, boolean type) {
        IntStream.range(0,checkResult.size()).forEach((i) -> {
                if(checkResult.get(i) == type) {
                    System.out.print((i+1) + " ");
                }
        });
        System.out.println();
    }

    private static List<Boolean> check(List<double[]> dealsList, double price) {
        return  dealsList.stream().map((deal) -> {
            double priseInDeal = deal[2] / (deal[0] * deal[1]);
            return (priseInDeal - price) <= ACCURACY;
        }).collect(Collectors.toList());
    }

    private static void printDeals(List<double[]> dealsList) {
        System.out.println("    length / width / price");

        IntStream.range(0,dealsList.size()).forEach((i) -> {
            double[] deal = dealsList.get(i);
            System.out.println(String.format("%2d. %5.2f  / %5.2f / %.2f", (i+1), deal[0], deal[1], + deal[2]));
        });
    }

    private static List<double[]> generateDeals() {
        List<double[]> result = new ArrayList<>();
        for (int i = 0; i < DEALS_COUNT; i++) {
            double[] deal = new double[3];
            deal[0] = Math.random() * 100;      //length
            deal[1] = Math.random() * 100;      //width
            deal[2] = Math.random() * 10000000; //price
            result.add(deal);
        }
        return result;
    }
}
