package seng202.group2.blackbirdModel;

import com.opencsv.CSVReader;
import seng202.group2.blackbirdControl.ErrorTabController;

import java.io.*;
import java.util.ArrayList;

/**
 * This class handles parsing of data, using an imported library called opencsv and the class CSVReader
 * to read through an inputted file.
 *
 * @author Team2
 * @version 2.0
 * @since 19/9/2016
 */
public class Parser {

    /**
     * Parses a given file using CSVReader to generate a string array. This is then passed to the DataPoint
     * static method to create a new DataPoint, and added to an arraylist of DataPoints.
     *
     * @param file      The input file
     * @param pointType The type of points that we are wanting to create
     * @param errorTab  The instance of the error tab that needs to be updated when a file is parsed in.
     * @return The arraylist of datapoints that have been parsed from the file.
     * @see DataPoint
     * @see CSVReader
     */
    public static ArrayList<DataPoint> parseFile(File file, DataTypes pointType, ErrorTabController errorTab) {
        ArrayList<DataPoint> allDataPoints = new ArrayList<>();
        int count = 0;
        try {
            CSVReader reader = new CSVReader(new FileReader(file), ',', '"', '\0');
            String[] currentLine;
            while ((currentLine = reader.readNext()) != null) {
                count++;
                if (currentLine.length == 1) {  //empty line with only new line char
                    continue;
                }
                String[] formattedLine = formatLine(currentLine);
                DataPoint currentDataPoint = DataPoint.createDataPointFromStringArray(formattedLine, pointType, count, errorTab);
                currentDataPoint.setFileLine(count);
                boolean correctPoint = true;
                switch (pointType) {
                    case AIRLINEPOINT:
                        AirlinePoint myNewAirline = (AirlinePoint) currentDataPoint;
                        if (myNewAirline.getCorrectEntry() == 1) {
                            correctPoint = false;
                        }
                        break;
                    case AIRPORTPOINT:
                        AirportPoint myNewAirport = (AirportPoint) currentDataPoint;
                        if (myNewAirport.getCorrectEntry() == 1) {
                            correctPoint = false;
                        }
                        break;
                    case ROUTEPOINT:
                        RoutePoint myNewRoute = (RoutePoint) currentDataPoint;
                        if (myNewRoute.getCorrectEntry() == 1) {
                            correctPoint = false;
                        }
                        break;
                    case FLIGHTPOINT:
                        FlightPoint myNewFlightPoint = (FlightPoint) currentDataPoint;
                        if (myNewFlightPoint.getCorrectEntry() == 1) {
                            return null;
                        }
                        break;
                    default:
                        break;
                }
                if (correctPoint) {
                    allDataPoints.add(currentDataPoint);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allDataPoints;
    }

    /**
     * Helper method to format each file line; removes all of the \N's in a line replacing them with null
     *
     * @param currentLine The current line, as a string array
     * @return The formatted line, as a string array
     */
    private static String[] formatLine(String[] currentLine) {
        String[] formattedLine = new String[currentLine.length];
        int lineCount = 0;
        for (String line : currentLine) {
            if ("\\N".equals(line)) {
                line = "";
            }
            if (line.matches("([a-zA-Z0-9]+\\\\\\\\'[a-zA-Z0-9]+)+")) {
                line = line.replaceAll("\\\\", "");
            }
            formattedLine[lineCount] = line;
            lineCount += 1;
        }
        return formattedLine;
    }

}

