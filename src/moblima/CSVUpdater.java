package moblima;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.opencsv.CSVWriter;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class CSVUpdater {

    // AccountsDB
    public static void updateAccounts(String filePath, ArrayList<Account> arrayName) throws IOException {
        File accountsFile = new File(filePath);
        FileWriter outputFile = new FileWriter(accountsFile);
        CSVWriter writer = new CSVWriter(outputFile);

        try {
            for (Account account: arrayName){
                String[] input = new String[5];
                input[0] = Integer.toString(account.getPrivilege());
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
    // BookingsDB
    public static void updateBookings(String filePath, ArrayList<Booking> arrayName) throws IOException {
        File bookingFile = new File(filePath);
        FileWriter outputFile = new FileWriter(bookingFile);
        CSVWriter writer = new CSVWriter(outputFile);

        try {
            for (Booking booking: arrayName){
                String[] input = new String[5];
                input[0] = booking.getTransactionId();
                input[1] = booking.getCustomerName();
                input[2] = booking.getMobileNumber();
                input[3] = booking.getEmailAddress();
                input[4] = Integer.toString(booking.getTotalPrice());
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
    public static void updateCompany(String filePath, Company company) throws IOException {
        File companyFile = new File(filePath);
        FileWriter outputFile = new FileWriter(companyFile);
        CSVWriter writer = new CSVWriter(outputFile);

        ArrayList<Cineplex> = Company.getCineplexes();



        try {
            for (){
                String[] input = new String[6];

                writer.writeNext(input);
            }
            writer.close();
        }
        catch (IOException e){
            System.out.println("An error occurred");
        }
    }
    // Tickets


}
