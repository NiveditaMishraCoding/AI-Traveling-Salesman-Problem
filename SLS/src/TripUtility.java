import java.util.Random;

public class TripUtility {
    public static double distance(Location location1, int location2) {
        return location1.getDistanceWithOtherCities(location2);
    }
    public static double acceptanceProbability(double currentDistance, double newDistance, double temperature) {

        if (newDistance < currentDistance) {
            return 1.0;
        }
        return Math.exp((currentDistance - newDistance) / temperature);
    }
    static double generateRandomDouble() {
        Random r = new Random();
        return r.nextInt(1000) / 1000.0;
    }
    public static int generateRandomInt(int min, int max) {
        Random r = new Random();
        double d = min + r.nextDouble() * (max - min);
        return (int) d;
    }
}