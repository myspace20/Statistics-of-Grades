import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static final int StatsLength = 5;

    public static int getMaxScore(int[] scores){
        if(scores.length == 0){
            System.out.println("Scores must contain at least one value");
        }
        if(scores.length == 1){
            return  scores[0];
        }
        return  Arrays.stream(scores).parallel().max().orElse(0);
    }

    public  static  int getMinScore(int[] scores){
        if(scores.length == 0){
            System.out.println("Scores must contain at least one value");
        }
        if(scores.length == 1) {
            return scores[0];
        }
        return  Arrays.stream(scores).parallel().min().orElse(0);
    }

    public static  double calculateAverageScore(int[] scores){
        if(scores.length == 0){
            System.out.println("Scores must contain at least one value");
        }
        if(scores.length == 1){
            return  scores[0];
        }
        return Arrays.stream(scores).parallel().sum()/(double)scores.length;
    }

    public static  int findMaxBarHeight(int [] stats){
        int maxValue = 0;
        for (int i = 0; i < stats.length; i++) {
            if (stats[i] > maxValue){
                maxValue = stats[i];
            }
        }
        return  maxValue;
    }

    public static  String[][] fillBarChartMatrix(int maximumBarHeight, int[] stats){
        String[][] result = new String[maximumBarHeight][stats.length];

        for (int row = 0; row < maximumBarHeight; row++) {
            for (int col = 0; col < stats.length; col++) {
                if (stats[col] >= maximumBarHeight - row) {
                    result[row][col] = "  ####### ";
                } else {
                    result[row][col] = "          ";
                }
            }
        }
        return result;
    }

    public static int[] fillStatsArray(int[] scores, int statsSize){
        int[] stats = new int[statsSize];

        for (int i = 0; i < scores.length; i++) {
            if (scores[i] > 80) {
                stats[4]++;
            } else if (scores[i] >= 61 && scores[i] <= 80) {
                stats[3]++;
            } else if (scores[i] >= 41 && scores[i] <= 60) {
                stats[2]++;
            } else if (scores[i] >= 21 && scores[i] <= 40) {
                stats[1]++;
            } else {
                stats[0]++;
            }
        }

        return stats;
    }


    public static void displayBarChart(String[][] barChartMatrix, int maximumBarHeight,int[] stats){
        for (int i = 0; i < maximumBarHeight; i++) {
            int label = maximumBarHeight - i;
            System.out.printf("%2d  > ", label);
            for (int j = 0; j < stats.length; j++) {
                System.out.printf("%-10s", barChartMatrix[i][j]);
            }
            System.out.println();
        }

        System.out.println("    +-----------+---------+---------+---------+---------+");
        System.out.println("    I   0-20    I  21-40  I  41-60  I  61-80  I  81-100 I");
    }


    public static  int[] collectScores(Scanner inputScanner){
        System.out.println("Please enter a list of student scores as a list of whitespace separated values");


        String[] scannedScores = inputScanner.nextLine().split(" ");

        int[] result = new int[scannedScores.length];


        for (int i = 0; i < scannedScores.length; i++) {
            result[i] = Integer.parseInt(scannedScores[i]);
        }

        return result;
    }




    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int [] scores = collectScores(scanner);

        int[] stats ;

        stats = fillStatsArray(scores,StatsLength);

        int maximumBarHeight = findMaxBarHeight(stats);

        String[][] barChartMatrix = fillBarChartMatrix(maximumBarHeight, stats);

        System.out.println("Values");
        System.out.print("\n".repeat(2));


        System.out.println("The maximum grade is " + getMaxScore(scores));
        System.out.println("The minimum grade is " + getMinScore(scores));
        System.out.printf("Average: %.2f%n",calculateAverageScore(scores));

        System.out.print("\n".repeat(3));

        System.out.println("Graph:");
        System.out.print("\n".repeat(4));
        displayBarChart(barChartMatrix, maximumBarHeight, stats);

    }









}