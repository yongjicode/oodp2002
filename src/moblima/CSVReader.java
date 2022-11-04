package moblima;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CSVReader{

    public static ArrayList<Booking> readBookingsFromCSV(String fileName) {
        
        ArrayList<Booking> bookings = new ArrayList<>(); 
        Path pathToFile = Paths.get(fileName);
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) { 
            
            String line = br.readLine();
            
            while (line != null) { 
                
                String[] attributes = line.split(","); 
                String transactionId = attributes[0];
                String customerName = attributes[1];
                String mobileNumber = attributes[2];
                String emailAddress = attributes[3];

                // transactionId, customerName, mobileNumber, emailAddress, totalPrice
                Booking booking = new Booking(customerName, mobileNumber, emailAddress, transactionId);
                bookings.add(booking); 
                line = br.readLine(); 
            } 
        } catch (IOException ioe) { 
            ioe.printStackTrace(); 
        } 
        return bookings; 
    }

    public static ArrayList<Movie> readMoviesFromCSV(String fileName) {
        
        ArrayList<Movie> movies = new ArrayList<>(); 
        Path pathToFile = Paths.get(fileName);
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) { 
            
            String line = br.readLine();
            
            while (line != null) { 
                
                String[] attributes = line.split(",");
                String title = attributes[2];
                String status = attributes[3];
                String synopsis = attributes[4];
                String director = attributes[5];
                String cast = attributes[6];

                Movie movie = new Movie(title, status, synopsis, director, cast);
                movies.add(movie); 
                line = br.readLine(); 
            } 
        } catch (IOException ioe) { 
            ioe.printStackTrace(); 
        } 
        return movies; 
    }

    // TODO: reading lists in csv unsupported (commas in lists)
    public static ArrayList<ReviewList> readReviewsFromCSV(String fileName) {
        
        ArrayList<ReviewList> reviewLists = new ArrayList<>(); 
        Path pathToFile = Paths.get(fileName);
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) { 
            
            String line = br.readLine();
            
            while (line != null) { 
                
                ReviewList reviewList = new ReviewList();

                String[] attributes = line.split(","); 
                List<Integer> rating = (Arrays.asList(Integer.parseInt(attributes[2])));
                List<String> reviewDescription = Arrays.asList(attributes[3]);
                int totalRating = Integer.parseInt(attributes[4]);

                int numberOfReviews = rating.size();

                for (int i=0; i<numberOfReviews; i++) {
                    Review review = new Review(rating.get(i), reviewDescription.get(i));
                    reviewList.add(review); 
                }

                reviewLists.add(reviewList);

                line = br.readLine(); 

            } 
        } catch (IOException ioe) { 
            ioe.printStackTrace(); 
        } 
        return reviewLists; 
    }

}