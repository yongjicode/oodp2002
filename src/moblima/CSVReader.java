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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    public static ArrayList<ReviewList> readReviewsFromCSV(String fileName) {
        
        ArrayList<ReviewList> reviewLists = new ArrayList<>(); 
        Path pathToFile = Paths.get(fileName);
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) { 
            
            String line = br.readLine();
            
            while (line != null) { 
                
                ReviewList reviewList = new ReviewList();

                String[] attributes = line.split(",");
                String ticketString = attributes[1];
                String[] ticketList = ticketString.split(";");

                String ratingString = attributes[2];
                String[] ratingList = ratingString.split(";");

                int ratingSize = ratingList.length;
                int[] ratingIntList = new int[ratingSize];

                for (int i=0; i<ratingSize; i++) {
                    ratingIntList[i] = Integer.parseInt(ratingList[i]);
                }
                System.out.println(ratingIntList);

                String reviewDescString = attributes[3];
                String[] reviewDescList = reviewDescString.split(";");

                for (int i=0; i<ratingSize; i++) {
                    Review review = new Review(ratingIntList[i], reviewDescList[i]);
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

    public static ArrayList<Cinema> readCinemasFromCSV(String fileName) {
        
        ArrayList<Cinema> cinemas = new ArrayList<>(); 
        Path pathToFile = Paths.get(fileName);
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) { 
            
            String line = br.readLine();
            
            while (line != null) { 
                
                // company,cineplexLocation,cinemaCode,classLevel,booking,showId
                String[] attributes = line.split(",");
                String company = attributes[0];
                String cineplexLocation = attributes[1];
                String cinemaCode = attributes[2];
                String classLevel = attributes[3];
                //int showId = Integer.parseInt(attributes[5]);

                Cinema cinema = new Cinema(classLevel, cineplexLocation, cinemaCode);
                cinemas.add(cinema); 
                line = br.readLine(); 
            } 
        } catch (IOException ioe) { 
            ioe.printStackTrace(); 
        } 
        return cinemas; 
    }

    public static Cinema filterCinemaFromCSV(String fileName, String cinemaCode) {
        
        ArrayList<Cinema> cinemas = readCinemasFromCSV(fileName);
        int arrSize = cinemas.size();
        for (int i=0; i<arrSize; i++) {
            if (cinemaCode.equals(cinemas.get(i).getCinemaCode())) {
                return cinemas.get(i);
            }
        }
        System.out.println("No existing cinema found!");
        Cinema cinema = new Cinema();    // no cinema found
        return cinema;
    }

    public static Movie filterMovieFromCSV(String fileName, String movieTitle) {
        
        ArrayList<Movie> movies = readMoviesFromCSV(fileName);
        int arrSize = movies.size();
        for (int i=0; i<arrSize; i++) {
            if (movieTitle.equals(movies.get(i).getTitle())) {
                return movies.get(i);
            }
        }
        System.out.println("No existing movie found!");
        Movie movie = new Movie();    // no movie found
        return movie;
    }

    public static ArrayList<Show> readShowsFromCSV(String fileName) {
        
        ArrayList<Show> shows = new ArrayList<>(); 
        Path pathToFile = Paths.get(fileName);
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) { 
            
            String line = br.readLine();
            
            while (line != null) { 
                
                // currentId,showId,cinemaCode,movie,showTime,seating
                String[] attributes = line.split(",");
                //int showId = Integer.parseInt(attributes[1]);
                String cinemaCode = attributes[3];
                String movieTitle = attributes[4];
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime showTime = LocalDateTime.parse(attributes[5],formatter);

                Cinema cinema = filterCinemaFromCSV("src\\database\\companyDB.csv", cinemaCode);
                Movie movie = filterMovieFromCSV("src\\database\\movieDB.csv", movieTitle);

                Show show = new Show(showTime, cinema, movie);
                shows.add(show); 
                line = br.readLine(); 
            } 
        } catch (IOException ioe) { 
            ioe.printStackTrace(); 
        } 
        return shows; 
    }

    public static Show filterShowFromCSV(String fileName, int showId) {
        
        ArrayList<Show> shows = readShowsFromCSV(fileName);
        int arrSize = shows.size();
        for (int i=0; i<arrSize; i++) {
            if (showId==shows.get(i).getShowId()) {
                return shows.get(i);
            }
        }
        System.out.println("No existing show found!");
        Show show = new Show();    // no show found
        return show;
    }

    public static ArrayList<MovieTicket> readTicketsFromCSV(String movieTicketFile) {
        
        ArrayList<MovieTicket> movieTickets = new ArrayList<>(); 
        Path pathToFile = Paths.get(movieTicketFile);
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) { 
            
            String line = br.readLine();
            
            while (line != null) { 
                
                String[] attributes = line.split(",");
                String transactionId = attributes[0];
                String username = attributes[1];
                String seatId = attributes[2];
                int showId = Integer.parseInt(attributes[3]);
                double price = Double.parseDouble(attributes[4]);
                String age = attributes[5];

                Show show = filterShowFromCSV("src\\database\\showDB.csv", showId);

                // String seatId, Show show, double price, String age
                MovieTicket movieTicket = new MovieTicket(seatId, show, price, age);
                movieTickets.add(movieTicket); 
                line = br.readLine(); 
            } 
        } catch (IOException ioe) { 
            ioe.printStackTrace(); 
        } 
        return movieTickets; 
    }

}
