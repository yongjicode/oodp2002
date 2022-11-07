package moblima;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

    // Accounts
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
    // Bookings
    public static void updateBookings(String filePath, ArrayList<Booking> arrayName){
        File bookingFile = new File(filePath);
        FileWriter outputFile = new FileWriter(bookingFile);
        CSVWriter writer = new CSVWriter(outputFile);

        try {
            for (Booking booking: arrayName){
                String[] input = new String[5]
                input[0] = booking.getTransactionId();
                input[1] = booking.getCustomerName();
                input[2] = booking.getMobileNumber();
                input[3] = booking.getEmailAddress();
                input[4] = Integer.toString(booking.getTotalPrice());
                writer.writeNext(input);
            }
            writer.close();
        }
        catch (IOException){
            System.out.println("An error occurred");
        }
    }
    // Movies
    public static void updateMovies(String filePath, ArrayList<Movie> arrayName){
        File movieFile = new File(filePath);
        FileWriter outputFile = new FileWriter(movieFile);
        CSVWriter writer = new CSVWriter(outputFile);

        try {
            for (Movie movie: arrayName){
                String[] input = new String[5]

                writer.writeNext(input);
            }
            writer.close();
        }
        catch (IOException){
            System.out.println("An error occurred");
        }


    }
    // Cinemas
    // Shows
    // Tickets


}
