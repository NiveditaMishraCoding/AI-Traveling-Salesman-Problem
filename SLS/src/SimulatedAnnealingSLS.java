import java.util.ArrayList;

public class SimulatedAnnealingSLS {

    public static void main(String[] args) {

        for (String fileName : Constants.fileNames) {
            ReadFileSls r = new ReadFileSls();
            double[][] locationMatrix = r.readFileAndReturnInput(fileName);
            if (locationMatrix == null)
                continue;
//            System.out.println("File Name : " + fileName);
            TripHelper.setDestinationLocations(new ArrayList<>());
            SLS(locationMatrix);
//            System.out.println("\n");
        }


    }

    public static void SLS(double[][] locationMatrix) {

        for (int i = 0; i < locationMatrix.length; ++i) {

            Location city = new Location(i, locationMatrix);
            TripHelper.addLocation(city);
        }
        long startTime = System.currentTimeMillis();
        double temp = 100000;

        double coolingRate = 0.003;

        Trip currentSolution = new Trip();
        currentSolution.generateRandomTrip();

//        System.out.println("Total distance of initial solution: " + currentSolution.getTotalDistance());
//        System.out.println("Trip: " + currentSolution);

        Trip best = new Trip(currentSolution.getTripList());
        double bestDistance = best.getTotalDistance();
        int iterations = 0;

        while (temp > 1) {

            iterations++;
            Trip newSolution = new Trip(currentSolution.getTripList());

            int tripPos1 = TripUtility.generateRandomInt(0, newSolution.tripSize());
            int tripPos2 = TripUtility.generateRandomInt(0, newSolution.tripSize());

            while (tripPos1 == tripPos2) {
                tripPos2 = TripUtility.generateRandomInt(0, newSolution.tripSize());
            }

            Location citySwap1 = newSolution.getLocation(tripPos1);
            Location citySwap2 = newSolution.getLocation(tripPos2);

            newSolution.setLocation(tripPos2, citySwap1);
            newSolution.setLocation(tripPos1, citySwap2);

            double currentDistance = currentSolution.getTotalDistance();
            double neighbourDistance = newSolution.getTotalDistance();

            double rand = TripUtility.generateRandomDouble();
            if (TripUtility.acceptanceProbability(currentDistance, neighbourDistance, temp) > rand) {
                currentSolution = new Trip(newSolution.getTripList());
            }

            if (currentSolution.getTotalDistance() < best.getTotalDistance()) {
                best = new Trip(currentSolution.getTripList());
                bestDistance = best.getTotalDistance();
            }

            temp *= 1 - coolingRate;
        }

//        System.out.println("Final solution distance: " + Double.valueOf(best.getTotalDistance()).toString());
//        System.out.println("Trip: " + best);

        long stopTime = System.currentTimeMillis();
        double time = (stopTime - startTime) / 1000.0;
//        System.out.println("Time taken in seconds: " + time);

        System.out.println(bestDistance + ", " + time + ", " + iterations + ", " + best);
    }
}