import java.util.*;

public class BnbDFS {
    static int N;
    static int path_taken[];
    static boolean isVisited[];
    static double result = Double.MAX_VALUE;

    static long startTime;

    static void copyPath(int current_path[]) {
        if (N >= 0) System.arraycopy(current_path, 0, path_taken, 0, N);
        path_taken[N] = current_path[0];
    }

    static double firstMin(Double locations[][], int i) {
        double min = Double.MAX_VALUE;
        for (int k = 0; k < N; k++)
            if (locations[i][k] < min && i != k)
                min = locations[i][k];
        return min;
    }

    static double secondMin(Double locations[][], int i) throws Exception {
        Double min1 = Double.MAX_VALUE, min2 = Double.MAX_VALUE;
        for (int j = 0; j < N; j++) {
            if (i == j)
                continue;

            if (locations[i][j] <= min1) {
                min2 = min1;
                min1 = locations[i][j];
            } else if (locations[i][j] <= min2 &&
                    locations[i][j] != min1)
                min2 = locations[i][j];
        }

        long endTime = System.currentTimeMillis();

        if ((endTime - startTime) / 60000 > 10) {
            throw new Exception("TimeOut !");
        }

        return min2;
    }

    static void TSPBnB(Double locations[][], double current_bound, int current_weight,
                       int level, int current_path[]) throws Exception {

        if (level == N) {

            if (locations[current_path[level - 1]][current_path[0]] != 0) {

                double current_result = current_weight +
                        locations[current_path[level - 1]][current_path[0]];

                if (current_result < result) {
                    copyPath(current_path);
                    result = current_result;
                }
            }
            return;
        }

        for (int i = 0; i < N; i++) {

            if (locations[current_path[level - 1]][i] != 0 && !isVisited[i]) {
                double temp = current_bound;
                current_weight += locations[current_path[level - 1]][i];

                if (level == 1)
                    current_bound -= ((firstMin(locations, current_path[level - 1]) + firstMin(locations, i)) / 2);
                else
                    current_bound -= ((secondMin(locations, current_path[level - 1]) + firstMin(locations, i)) / 2);

                if (current_bound + current_weight < result) {
                    current_path[level] = i;
                    isVisited[i] = true;

                    TSPBnB(locations, current_bound, current_weight, level + 1, current_path);
                }
                current_weight -= locations[current_path[level - 1]][i];
                current_bound = temp;

                Arrays.fill(isVisited, false);
                for (int j = 0; j <= level - 1; j++)
                    isVisited[current_path[j]] = true;
            }
        }
    }

    static void TSP(Double locations[][]) throws Exception {
        int current_path[] = new int[N + 1];

        double current_bound = 0;
        Arrays.fill(current_path, -1);
        Arrays.fill(isVisited, false);

        for (int i = 0; i < N; i++)
            current_bound += (firstMin(locations, i) + secondMin(locations, i));

        current_bound = (current_bound == 1) ? current_bound / 2 + 1 : current_bound / 2;

        isVisited[0] = true;
        current_path[0] = 0;

        TSPBnB(locations, current_bound, 0, 1, current_path);
    }

    public static void main(String[] args) {

        for (String fileName : Constants.fileNames) {
            try {

                ReadFile r = new ReadFile();
                Double[][] locations = r.readFileAndReturnInputForBnbDFS(fileName);
                startTime = System.currentTimeMillis();
                N = locations.length;
                path_taken = new int[N + 1];
                isVisited = new boolean[N];
                result = Double.MAX_VALUE;
                TSP(locations);

                long stopTime = System.currentTimeMillis();
                double time = (stopTime - startTime) / 1000.0;
//                System.out.println("Time taken in seconds: " + time);

//                System.out.println("Minimum cost : " + result);
//                System.out.println("Path Taken : ");
                System.out.printf(result + ", " + time +  ", " + N + ", ");
                for (int i = 0; i < N; i++) {
                    System.out.printf(path_taken[i] + " -> ");
                }
                System.out.printf(path_taken[N] + "");
                System.out.println("");



            } catch (Exception e) {
                int count = 0;
                e.printStackTrace();
                System.out.println("Nodes expanded:");
                for (int i = 0; i < isVisited.length; ++i) {
                    if (isVisited[i]) {
                        System.out.print(" " + i);
                        count++;
                    }
                }
                System.out.println("\nTotal nodes expanded = " + count);
                System.out.println((startTime - System.currentTimeMillis()) / 60000 + " time");
            }
        }

    }
}

