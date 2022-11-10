package moblima;

import account.*;
import moblima.booking.Booking;
import moblima.cineplex.cinema.Cinema;
import moblima.cineplex.cinema.CinemaClass;
import moblima.cineplex.Cineplex;
import moblima.movie.Movie;
import moblima.movie.MovieStatus;
import moblima.movie.review.Review;
import moblima.movie.review.ReviewList;
import moblima.show.Show;
import moblima.show.ShowList;
import moblima.booking.ticket.MovieTicket;


import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

//import static moblima.cineplex.Cinema.convertToCinemaClass;
import static moblima.movie.Movie.convertToMovieStatus;
//import static moblima.show.ticket.MovieTicket.checkCustomerAge;
public class CSVReader{

public static ArrayList<Account> readAccountsFromCSV(String fileName, ArrayList<Cineplex> arrayCineplex) {

    ArrayList<Account> accounts = new ArrayList<Account>();
    Path pathToFile = Paths.get(fileName);
    try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
    
        br.readLine();
        String line = br.readLine();

        while (line != null) {

            String[] attributes = line.split(",");
            Privilege privilege = Account.convertIntToPrivilege(Integer.parseInt(attributes[0]));
            String loginId = attributes[1];
            String password = attributes[2];
            String email = attributes[3];
            String mobile = attributes[4];
            String name = attributes[5];
            String location = attributes[6];

            if (privilege == Privilege.User) { // user
                // String loginId, String password, Privilege privilege,String emailAddress, String phoneNo, String name
                UserAccount userAccount = new UserAccount(loginId, password, privilege, email, mobile, name);
                accounts.add(userAccount);
            } else if (privilege == Privilege.CineplexAdmin) { // cineplex admin
                for (Cineplex cineplex: arrayCineplex){
                    if (cineplex.getLocation() == location){
                        //String loginId, String password, Privilege privilege, Cineplex cineplex, String emailAddress, String phoneNo, String name
                        CineplexAdminAccount cineplexAdminAccount = new CineplexAdminAccount(loginId, password, privilege, cineplex, email, mobile, name );
                        accounts.add(cineplexAdminAccount);
                    }
                }
            } else { // company admin
                // String loginId, String password, Privilege privilege, String emailAddress, String phoneNo, String name
                CompanyAdminAccount companyAdminAccount =  new CompanyAdminAccount(loginId, password, privilege, email, mobile, name);
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
        br.readLine();
        String line = br.readLine();
        while (line != null) {
            //cineplexName,cineplexLocation,classLevel,cinemaCode
            String[] attributes = line.split(",");
            String cineplexName = attributes[0];
            String cineplexLocation = attributes[1];
            String classLevel = attributes[2];
            //String cinemaCode = attributes[3];

            if (count==0) {
                cineplex = new Cineplex(cineplexName, cineplexLocation);
                Cinema cinema = new Cinema(Cinema.convertToCinemaClass(classLevel));
                cineplex.addCinema(cinema);
            }
            else if (cineplexLocation != cineplexes.get(count).getLocation()){
                count++;
                cineplexes.add(cineplex);
                cineplex = new Cineplex(cineplexLocation, cineplexLocation);
            }
            else{
                Cinema cinema = new Cinema(Cinema.convertToCinemaClass(classLevel));
                cineplex.addCinema(cinema);
            }
            line = br.readLine();
        }
        for (Cineplex cine: cineplexes){
            for (Show show: arrayName){
                cine.getShowList().addShow(show);
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

        br.readLine();
        String line = br.readLine();

        while (line != null) {
            // ticketId, customerName, mobileNumber, emailAddress, totalPrice
            String[] attributes = line.split(",");
            //String[] ticketIdList = attributes[0].split(";");
            String customerName = attributes[1];
            String mobileNumber = attributes[2];
            String emailAddress = attributes[3];
            //double totalPrice = Double.parseDouble(attributes[4]);

            Booking booking = new Booking(customerName, mobileNumber, emailAddress);
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

        br.readLine();
        String line = br.readLine();

        while (line != null) {
            // movieId, title, synopsis, director, casts, reviews, status, expiryDate, ticketSold, rating
            String[] attributes = line.split(",");
            //String movieId = attributes[0];
            String title = attributes[1];
            String synopsis = attributes[2];
            String director = attributes[3];
            String castString = attributes[4]; // to change to list
            String[] casts = castString.split(";");
            MovieStatus status = Movie.convertToMovieStatus(attributes[6]);
            LocalDateTime expiryDate = LocalDateTime.parse(attributes[7],formatter);

            // String title,MovieStatus status,String synopsis,String director,String cast,LocalDateTime expiryDate
            Movie movie = new Movie(title, status, synopsis, director, castString, expiryDate);
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

        br.readLine();
        String line = br.readLine();

        while (line != null) {

            ReviewList reviewList = new ReviewList();

            String[] attributes = line.split(",");
            //String movieId = attributes[0];
            //String[] ticketIdList = attributes[1].split(";");
            String[] ratingList = attributes[2].split(";");
            String[] reviewDescList = attributes[3].split(";");
            //int totalRating = Integer.parseInt(attributes[4]);

            int ratingSize = ratingList.length;
            int[] ratingIntList = new int[ratingSize];

            for (int i=0; i<ratingSize; i++) {
                ratingIntList[i] = Integer.parseInt(ratingList[i]);
            }

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

        br.readLine();
        String line = br.readLine();

        while (line != null) {

            // cinemaCode,classLevel
            String[] attributes = line.split(",");
            //String cinemaCode = attributes[0];
            String classLevel = attributes[1];

            Cinema cinema = new Cinema(Cinema.convertToCinemaClass(classLevel));
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

public static Movie filterMovieFromCSV(String fileName, int movieId) {

    ArrayList<Movie> movies = readMoviesFromCSV(fileName);
    int arrSize = movies.size();
    for (int i=0; i<arrSize; i++) {
        if (movieId == movies.get(i).getMovieId()) {
            return movies.get(i);
        }
    }
    System.out.println("No existing movie found!");
    Movie movie = null;    // no movie found
    return movie;
}

public static ShowList readShowsFromCSV(String fileName) {

    ShowList showlist = new ShowList();
    Path pathToFile = Paths.get(fileName);
    try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {

        br.readLine();
        String line = br.readLine();

        while (line != null) {

            // currentId,showId,cinemaCode,movie,showTime,seating
            String[] attributes = line.split(",");
            //int showId = Integer.parseInt(attributes[0]);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime showTime = LocalDateTime.parse(attributes[1],formatter);
            String cinemaCode = attributes[2];
            int movieId = Integer.parseInt(attributes[3]);
            
            Cinema cinema = filterCinemaFromCSV("src\\database\\CinemaDB.csv", cinemaCode);
            Movie movie = filterMovieFromCSV("src\\database\\MovieDB.csv", movieId);

            Show show = new Show(showTime, cinema, movie);
            showlist.addShow(show);
            line = br.readLine();
        }
    } catch (IOException ioe) {
        ioe.printStackTrace();
    }
    return showlist;
}

public static Show filterShowFromCSV(String fileName, int showId) {

    ArrayList<Show> shows = readShowsFromCSV(fileName).getShows();
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

        br.readLine();
        String line = br.readLine();

        while (line != null) {

            String[] attributes = line.split(",");
            int showId = Integer.parseInt(attributes[0]);
            String seatId = attributes[1];
            //int ticketId = Integer.parseInt(attributes[2]);
            //double price = Double.parseDouble(attributes[3]);
            String age = attributes[4];

            Show show = filterShowFromCSV("src\\database\\ShowDB.csv", showId);

            // String seatId, Show show, double price, String age
            MovieTicket movieTicket = new MovieTicket(seatId, show, MovieTicket.checkCustomerAge(age));
            movieTickets.add(movieTicket);
            line = br.readLine();
        }
    } catch (IOException ioe) {
        ioe.printStackTrace();
    }
    return movieTickets;
}

}