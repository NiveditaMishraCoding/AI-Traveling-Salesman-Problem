public class Location {
    private int locationName;
    private  double[] distanceWithOtherCities;

    public Location(int locationName, double[][] cityMatrix){
        this.locationName = locationName;
        this.distanceWithOtherCities = new double[cityMatrix.length];

        for(int i =0; i<cityMatrix.length; ++i){
            distanceWithOtherCities[i] = cityMatrix[i][locationName];
        }
    }

    public void setDistanceWithOtherCities(double[] distanceList) {
        this.distanceWithOtherCities = distanceList;
    }

    public double getDistanceWithOtherCities(int index) {
        return this.distanceWithOtherCities[index];
    }

    public int getLocationName() {
        return locationName;
    }

    public void setLocationName(int locationName) {
        this.locationName = locationName;
    }

}