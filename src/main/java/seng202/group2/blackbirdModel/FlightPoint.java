package seng202.group2.blackbirdModel;

/**
 * A subclass of dataPoint that stores information about a flight point
 *
 * @author Team2
 * @version 2.0
 * @since 19/9/2016
 */
public class FlightPoint extends DataPoint {

    private String localeType;
    private String localeID;
    private float altitude;
    private float latitude;
    private float longitude;
    private int correctEntry = 0;
    private int flightIDNum;

    /**
     * Creates a FlightPoint with values given
     *
     * @param type      the locale type
     * @param localeID  the locale ID
     * @param altitude  the altitude
     * @param latitude  the latitude
     * @param longitude the longitude
     */
    public FlightPoint(String type, String localeID, float altitude, float latitude, float longitude) {
        this.localeType = type;
        this.localeID = localeID;
        this.altitude = altitude;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Attempts to create a FlightPoint from a list of strings of length 5.
     * If successful it creates an FlightPoint with values from list and correctEntry as 0.
     * If unsuccessful it creates am FlightPoint with localeType "I AM INCORRECT".
     *
     * @param currentLine The list of strings holding the information for the FlightPoint in index of:
     *                    0 localeType,
     *                    1 localeID,
     *                    2 altitude,
     *                    3 latitude,
     *                    4 longitude,
     */
    public FlightPoint(String[] currentLine) {
        super();
        try {
            if (currentLine.length == 5) {
                this.localeType = currentLine[0];
                this.localeID = currentLine[1];
                this.altitude = Float.parseFloat(currentLine[2]);
                this.latitude = Float.parseFloat(currentLine[3]);
                this.longitude = Float.parseFloat(currentLine[4]);
            }
            //FOR GRABBING OLD FLIGHTPOINTS FROM DATABASE FOR RECREATION
            if (currentLine.length == 7) {
                this.localeType = currentLine[2];
                this.localeID = currentLine[1];
                this.altitude = Float.parseFloat(currentLine[3]);
                this.latitude = Float.parseFloat(currentLine[4]);
                this.longitude = Float.parseFloat(currentLine[5]);
                this.flightIDNum = Integer.parseInt(currentLine[6]);
            }
        } catch (NumberFormatException e) {
            this.localeType = "I AM INCORRECT";
            correctEntry = 1;
        }

    }

    /**
     * Returns the FlightPoint in the form of a string
     *
     * @return localeType, localeID, altitude, latitude, longitude
     */
    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s,%s",
                localeType, localeID, altitude, latitude, longitude);
    }

    int getFlightIDNum() {
        return flightIDNum;
    }

    public String getLocaleType() {
        return localeType;
    }

    public void setLocaleType(String type) {
        this.localeType = type;
    }

    public String getLocaleID() {
        return localeID;
    }

    public void setLocaleID(String localeID) {
        this.localeID = localeID;
    }

    public float getAltitude() {
        return altitude;
    }

    public void setAltitude(float altitude) {
        this.altitude = altitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    int getCorrectEntry() {
        return correctEntry;
    }

    public void setCorrectEntry(int correctEntry) {
        this.correctEntry = correctEntry;
    }

}

