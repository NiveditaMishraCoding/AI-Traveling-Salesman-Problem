import java.util.ArrayList;
public class TripHelper {
    private static ArrayList<Location> destinationLocations = new ArrayList<Location>();
    public static void addLocation(Location location) {
        destinationLocations.add(location);
    }

    public static Location getLocation(int index){
        return (Location)destinationLocations.get(index);
    }

    public static int numberOfLocations(){
        return destinationLocations.size();
    }

    public static void setDestinationLocations(ArrayList<Location> list){
        destinationLocations = list;
    }
}