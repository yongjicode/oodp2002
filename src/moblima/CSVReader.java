package moblima;

import account.Account;
import account.CineplexAdminAccount;
import account.CompanyAdminAccount;
import account.UserAccount;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static moblima.Cinema.convertToCinemaClass;
import static moblima.Movie.convertToMovieStatus;
import static moblima.MovieTicket.checkCustomerAge;
public class CSVReader{

    public static ArrayList<Account> readAccountsFromCSV(String fileName, ArrayList<Cineplex> arrayCineplex) {

        ArrayList<Account> accounts = new ArrayList<Account>();
        Path pathToFile = Paths.get(fileName);
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {

            String line = br.readLine();

            while (line != null) {

                String[] attributes = line.split(",");
                int privilege = Integer.parseInt(attributes[0]);
                String loginId = attributes[1];
                String password = attributes[2];
                String mobileNumber = attributes[3];
                String email = attributes[4];
                String outlet = attributes[5];

                if (privilege == 0) { // user
                    // String loginId, String password, int privilege
                    UserAccount userAccount = new UserAccount(loginId, password, privilege, email, mobileNumber, loginId);
                    accounts.add(userAccount);
                } else if (privilege == 1) { // cineplex admin
                    // String loginId, String password, int privilege,String mobileNumber, String outlet
                    for (Cineplex cineplex: arrayCineplex){
                        if (cineplex.getLocation() == outlet){
                            CineplexAdminAccount cineplexAdminAccount = new CineplexAdminAccount(loginId, password, privilege, cineplex, email, mobileNumber, loginId );
                            accounts.add(cineplexAdminAccount);
                        }
                    }
                } else { // company admin
                    // String loginId, String password, int privilege,String mobileNumber
                    CompanyAdminAccount companyAdminAccount =  new CompanyAdminAccount(loginId, password, privilege, email, mobileNumber, loginId);
                    accounts.add(companyAdminAccount);
                }

                line = br.readLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return accounts;
    }

    public static ArrayList<Cineplex> readCineplexFromCSV(String fileName, ArrayList<Show> arrayName){
        Path pathToFile = Paths.get(fileName);
        ArrayList<Cineplex> cineplexes = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {

            int count=0;
            Cineplex cineplex = null;
            String line = br.readLine();
            while (line != null) {

                // company,cineplexLocation,cinemaCode,classLevel,booking,showId
                String[] attributes = line.split(",");
//                String company = attributes[0];
                String cineplexLocation = attributes[1];
//                String cinemaCode = attributes[2];
                String classLevel = attributes[3];
                int showId = Integer.parseInt(attributes[5]);

                if (count==0) {
                    cineplex = new Cineplex(cineplexLocation, cineplexLocation);
                    Cinema cinema = new Cinema(convertToCinemaClass(classLevel));
                    cineplex.addCinema(cinema);
                }
                else if (cineplexLocation != cineplexes.get(count).getLocation()){
                    count++;
                    cineplexes.add(cineplex);
                    cineplex = new Cineplex(cineplexLocation, cineplexLocation);
                }
                else{
                    Cinema cinema = new Cinema(convertToCinemaClass(classLevel));
                    cineplex.addCinema(cinema);
                }
                line = br.readLine();
            }
            for (Cineplex cine: cineplexes){
                for (Show show: arrayName){
                    cine.addShow(show);
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return cineplexes;
    }

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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
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
                // TODO add expiry date attribute
                // String expiryDate = attributes[7];
                String expiryDate = "2022-12-25 00:00";
                Movie movie = new Movie(title, convertToMovieStatus(status), synopsis, director, cast, LocalDateTime.parse(expiryDate, formatter));
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

                Cinema cinema = new Cinema(convertToCinemaClass(classLevel));
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
        return null;
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
        Movie movie = null;    // no movie found
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
        return null;
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
                MovieTicket movieTicket = new MovieTicket(seatId, show, checkCustomerAge(age));
                movieTickets.add(movieTicket);
                line = br.readLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return movieTickets;
    }

}