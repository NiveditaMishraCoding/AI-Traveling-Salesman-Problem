import java.util.ArrayList;
import java.util.Collections;

public class Trip {
    private ArrayList<Location> tripList = new ArrayList<Location>();
    private double distance = 0;

    public Trip() {
        for (int i = 0; i < TripHelper.numberOfLocations(); i++) {
            tripList.add(null);
        }
    }

    @SuppressWarnings("unchecked")
    public Trip(ArrayList<Location> Trip) {
        this.tripList = (ArrayList<Location>) Trip.clone();
    }

    public ArrayList<Location> getTripList() {
        return tripList;
    }

    public void generateRandomTrip() {
        // Loop through all our destination cities and add them to our Trip
        for (int locationIndex = 0; locationIndex < TripHelper.numberOfLocations(); locationIndex++) {
            setLocation(locationIndex, TripHelper.getLocation(locationIndex));
        }
        // Randomly reorder the Trip
        Collections.shuffle(tripList);
    }

    public Location getLocation(int index) {
        return tripList.get(index);
    }

    public void setLocation(int index, Location location) {
        tripList.set(index, location);
        // If the Trip has been altered we need to reset the fitness and distance
        distance = 0;
    }

    public double getTotalDistance() {
        if (distance == 0) {
            double TripDistance = 0;

            for (int locationIndex = 0; locationIndex < tripSize(); locationIndex++) {

                Location fromLocation = getLocation(locationIndex);

                int destinationLocation;

                if (locationIndex + 1 < tripSize()) {
                    destinationLocation = locationIndex + 1;
                } else {
                    destinationLocation = 0;
                }

                TripDistance += TripUtility.distance(fromLocation, destinationLocation);
            }
            distance = TripDistance;
        }
        return distance;
    }

    public int tripSize() {
        return tripList.size();
    }

    @Override
    public String toString() {
        String s = String.valueOf(getLocation(0).getLocationName());
        for (int i = 1; i < tripSize(); i++) {
            s += " -> " + getLocation(i).getLocationName();
        }
        return s + " ->" + getLocation(0).getLocationName();
    }
}
    