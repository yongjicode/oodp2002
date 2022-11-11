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
import moblima.booking.ticket.MovieTicket;
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
                for (Cinema cinema: SilverVillage.getCineplexList().getCineplexByIndex(count++).getCinemas()){
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
            while (SilverVillage.getBookingHistory().getBookingByIndex(count) != null){
                Booking booking = SilverVillage.getBookingHistory().getBookingByIndex(count);
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


    // AccountsDB TODO UpdateAccounts
//    public static void updateAccounts(String filePath) throws IOException {
//        File accountsFile = new File(filePath);
//        FileWriter outputFile = new FileWriter(accountsFile);
//        CSVWriter writer = new CSVWriter(outputFile);
//
//        try {
//            for (Account account: arrayName){
//                String[] input = new String[5];
//                input[0] = Integer.toString(account);
//                input[1] = account.getLoginId();
//                input[2] = account.getPassword();
//                input[3] = "NA";
//                input[4] = "NA";
//                writer.writeNext(input);
//            }
//            writer.close();
//        }
//        catch (IOException e){
//            System.out.println("An error occurred");
//        }
//    }
    // MoviesDB
    public static void updateMovies(String filePath) throws IOException {
        File movieFile = new File(filePath);
        FileWriter outputFile = new FileWriter(movieFile);
        CSVWriter writer = new CSVWriter(outputFile);
        int count=0;
        try {
            while (SilverVillage.getMovieList().getMovieByIndex(count) != null){
                Movie movie = SilverVillage.getMovieList().getMovieByIndex(count++);
                String[] input = new String[10];
                input[0] = Integer.toString(movie.getMovieId());
                input[1] = movie.getTitle();
                input[2] = movie.getSynopsis();
                input[3] = movie.getDirector();
                input[4] = "NA"; // TODO movie.getCast();
                input[5] = movie.getReviews().toString();
                input[6] = movie.convertMovieStatusToString(movie.getStatus());
                input[7] = movie.getExpiryDate().toString();
                input[8] = Integer.toString(movie.getTicketSold());
                input[9] = Integer.toString(movie.getRating());
                writer.writeNext(input);
            }
            writer.close();
        }
        catch (IOException e){
            System.out.println("An error occurred");
        }
    }


    // ShowDB
    public static void updateShows(String filePath) throws IOException {
        File showFile = new File(filePath);
        FileWriter outputFile = new FileWriter(showFile);
        CSVWriter writer = new CSVWriter(outputFile);
        int count=0;
        try {
            for (Show show: SilverVillage.getCineplexList().getCineplexByIndex(count).getShowList().getShows()){
                String[] input = new String[5];
                input[0] = Integer.toString(show.getShowId());
                input[1] = show.getShowTime().toString();
                input[2] = show.getCinema().getCinemaCode();
                input[3] = Integer.toString(show.getMovie().getMovieId());
                input[4] = "NA"; // TODO getSeating()
                writer.writeNext(input);
            }
            writer.close();
        }
        catch (IOException e){
            System.out.println("An error occurred");
        }
    }
    // TODO
    // Tickets
    public static void updateTickets(String filePath) throws IOException {
        File ticketFile = new File(filePath);
        FileWriter outputFile = new FileWriter(ticketFile);
        CSVWriter writer = new CSVWriter(outputFile);
        int count=0;
        try {
            while (SilverVillage.getBookingHistory().getBookingByIndex(count) != null){
                Booking booking = SilverVillage.getBookingHistory().getBookingByIndex(count++);
                int ticketCount=0;
                while (booking.getTicket(ticketCount) != null){
                    MovieTicket ticket = booking.getTicket(ticketCount++);
                    String[] input = new String[5];
                    input[0] = Integer.toString(ticket.getShow().getShowId());
                    input[1] = ticket.getSeatId();
                    input[2] = Integer.toString(ticket.getTicketID());
                    input[3] = Double.toString(ticket.getPrice());
                    input[4] = MovieTicket.convertCustomerAgeToString(ticket.getAge());
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
    public static void updateReviewList(String filePath) throws IOException {
        File reviewListFile = new File(filePath);
        FileWriter outputFile = new FileWriter(reviewListFile);
        CSVWriter writer = new CSVWriter(outputFile);
        try {
            int count=0;
            while (SilverVillage.getMovieList().getMovieByIndex(count) != null){
                Movie movie = SilverVillage.getMovieList().getMovieByIndex(count++);
                String[] input = new String[5];
                input[0] = Integer.toString(movie.getMovieId()); // TODO remove ticketID from database
                input[2] = movie.getReviews().convertRatingsToString();
                input[3] = movie.getReviews().convertDescriptionToString();
                input[4] = Integer.toString(movie.getReviews().showAverageRating());
                writer.writeNext(input);
            }
            writer.close();
        }
        catch (IOException e){
            System.out.println("An error occurred");
        }
    }

}
