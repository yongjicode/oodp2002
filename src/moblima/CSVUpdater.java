package moblima;

import java.awt.print.Book;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import account.*;
import com.opencsv.CSVWriter;
import moblima.booking.Booking;
import moblima.cineplex.Cineplex;
import moblima.cineplex.cinema.Cinema;
import moblima.movie.Movie;
import moblima.show.Show;
import moblima.SilverVillage;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CSVUpdater {

    //CineplexDB
    public void updateCineplex(String filePath) throws IOException{
        File cineplexDb = new File(filePath);
        FileWriter outputFile = new FileWriter(cineplexDb);
        CSVWriter writer = new CSVWriter(outputFile);
        int count=0;
        try {
            while (SilverVillage.getCineplexList().getCineplexByIndex(count) != null){
                String[] input = new String[2];
                input[0] = SilverVillage.getCineplexList().getCineplexByIndex(count).getBranchName();
                input[1] = SilverVillage.getCineplexList().getCineplexByIndex(count).getBranchAddress();
                writer.writeNext(input);
            }
            writer.close();
        }
        catch (IOException e){
            System.out.println("An error occurred");
        }
    }


    //CinemaDB
    public void updateCinema(String filePath) throws IOException{
        File cinemaDb = new File(filePath);
        FileWriter outputFile = new FileWriter(cinemaDb);
        CSVWriter writer = new CSVWriter(outputFile);
        int count=0;
        try {
            while (SilverVillage.getCineplexList().getCineplexByIndex(count) != null){
                for (Cinema cinema: SilverVillage.getCineplexList().getCineplexByIndex(count).getCinemas()){
                    String[] input = new String[2];
                    input[0] = cinema.convertCinemaCodeToCurrentCode();
                    input[1] = cinema.getClassLevel().toString();
                    writer.writeNext(input);
                }
            }
            writer.close();
        }
        catch (IOException e){
            System.out.println("An error occurred");
        }
    }


    //BookingDB
    public void updateBooking(String filePath) throws IOException{
        File bookingDb = new File(filePath);
        FileWriter outputFile = new FileWriter(bookingDb);
        CSVWriter writer = new CSVWriter(outputFile);
        int count=0;
        try {
            while (SilverVillage.getBookingHistory().getBookingById(count) != null){
                Booking booking = SilverVillage.getBookingHistory().getBookingById(count);
                String[] input = new String[6];
                input[0] = booking.convertTicketsToString();
                input[1] = booking.getCustomerName();
                input[2] = booking.getMobileNumber();
                input[3] = booking.getEmailAddress();
                input[4] = booking.getTransactionId();
                input[5] = Double.toString(booking.getTotalPrice());
                writer.writeNext(input);
            }
            writer.close();
        }
        catch (IOException e){
            System.out.println("An error occurred");
        }
    }


    // AccountsDB
    public static void updateAccounts(String filePath) throws IOException {
        File accountsFile = new File(filePath);
        FileWriter outputFile = new FileWriter(accountsFile);
        CSVWriter writer = new CSVWriter(outputFile);

        try {
            for (Account account: arrayName){
                String[] input = new String[5];
                input[0] = Integer.toString(account);
                input[1] = account.getLoginId();
                input[2] = account.getPassword();
                input[3] = "NA";
                input[4] = "NA";
                writer.writeNext(input);
            }
            writer.close();
        }
        catch (IOException e){
            System.out.println("An error occurred");
        }
    }
    // MoviesDB
    public static void updateMovies(String filePath, ArrayList<Movie> arrayName) throws IOException {
        File movieFile = new File(filePath);
        FileWriter outputFile = new FileWriter(movieFile);
        CSVWriter writer = new CSVWriter(outputFile);

        try {
            for (Movie movie: arrayName){
                String[] input = new String[8];
                input[0] = movie.getTitle();
                input[1] = Integer.toString(movie.getMovieId());
                input[2] = movie.getTitle();
                input[3] = movie.getStatus();
                input[4] = movie.getSynopsis();
                input[5] = movie.getDirector();
                input[6] = movie.getCast();
                input[7] = movie.getReviews().toString();
                writer.writeNext(input);
            }
            writer.close();
        }
        catch (IOException e){
            System.out.println("An error occurred");
        }
    }
    // ShowDB
    public static void updateShows(String filePath, ArrayList<Show> arrayName) throws IOException {
        File showFile = new File(filePath);
        FileWriter outputFile = new FileWriter(showFile);
        CSVWriter writer = new CSVWriter(outputFile);

        try {
            for (Show show: arrayName){
                String[] input = new String[6];
                input[0] = Integer.toString(show.getShowId());
                input[1] = Integer.toString(show.getShowId());
                input[2] = show.getCinema().toString();
                input[3] = show.getMovie().toString();
                input[4] = show.getShowTime().toString();
                input[5] = show.getSeating().toString();
                writer.writeNext(input);
            }
            writer.close();
        }
        catch (IOException e){
            System.out.println("An error occurred");
        }
    }
    // CompanyDB
    public static void updateCompany(String filePath, ArrayList<Cineplex> arrayName1, ArrayList<Cinema> arrayName2, ArrayList<Booking> arrayName3) throws IOException {
        File companyFile = new File(filePath);
        FileWriter outputFile = new FileWriter(companyFile);
        CSVWriter writer = new CSVWriter(outputFile);

        int numberCinema = arrayName2.size();

        try {
            for (Cineplex cineplex : arrayName1) {
                for (int j = 0; j < numberCinema; j++) {
                    String[] input = new String[5];
                    input[0] = "golden_village";
                    input[1] = cineplex.getLocation(); //use name or use location?
                    input[2] = arrayName2.get(j).getCinemaCode();
                    input[3] = arrayName3.toString();
                    input[4] = Integer.toString(cineplex.getShows().get(j).getShowId());
                    writer.writeNext(input);
                }
            }
            writer.close();
        }
        catch (IOException e){
            System.out.println("An error occurred");
        }
    }
    // Tickets
    public static void updateTickets(String filePath, ArrayList<Booking> arrayName) throws IOException {
        File ticketFile = new File(filePath);
        FileWriter outputFile = new FileWriter(ticketFile);
        CSVWriter writer = new CSVWriter(outputFile);
        try {
            for (Booking booking: arrayName){
                for (int i=0; i<booking.getTickets().size(); i++){
                    String[] input = new String[6];
                    input[0] = booking.getTransactionId();
                    input[1] = booking.getCustomerName();
                    input[2] = booking.getTickets().get(i).getSeatId();
                    input[3] = Integer.toString(booking.getTickets().get(i).getShow().getShowId());
                    input[4] = Double.toString(booking.getTickets().get(i).getPrice());
                    input[5] = booking.getTickets().get(i).getAge();
                    writer.writeNext(input);
                }
            }
            writer.close();
        }
        catch (IOException e){
            System.out.println("An error occurred");
        }
    }

    //ReviewList
    public static void updateReviewList(String filePath, ArrayList<Movie> arrayName) throws IOException {
        File reviewListFile = new File(filePath);
        FileWriter outputFile = new FileWriter(reviewListFile);
        CSVWriter writer = new CSVWriter(outputFile);
        try {
            for (Movie movie: arrayName){
                String[] input = new String[5];
                //Concatenating All reviewDescriptions into 1 string
                String review = "";
                for (int i=0; i<movie.getReviews().getReviews().size(); i++){
                    if (i==0) review += movie.getReviews().getReviews().get(i).getReviewDescription();
                    review += ";";
                    review += movie.getReviews().getReviews().get(i).getReviewDescription();
                }
                //Concatenating all rating into 1 String
                String rating = "";
                for (int j=0; j<movie.getReviews().getReviews().size(); j++){
                    if (j==0) rating += Integer.toString(movie.getReviews().getReviews().get(j).getRating());
                    rating += ";";
                    rating += Integer.toString(movie.getReviews().getReviews().get(j).getRating());
                }
                input[0] = movie.getReviews().toString();
                input[1] = "NA";
                input[2] = rating;
                input[3] = review;
                input[4] = Integer.toString(movie.getRating());
                writer.writeNext(input);
            }
            writer.close();
        }
        catch (IOException e){
            System.out.println("An error occurred");
        }
    }

}
