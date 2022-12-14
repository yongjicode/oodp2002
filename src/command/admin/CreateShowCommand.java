package command.admin;

import command.Command;
import exceptions.moblimaExceptions.invalidInputException;
import moblima.SilverVillage;
import moblima.cineplex.cinema.Cinema;
import moblima.cineplex.Cineplex;
import moblima.movie.Movie;
import moblima.show.Show;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Represents a command for Cineplex Admin to create a Show Object
 */
public class CreateShowCommand implements Command{
    private Cineplex cineplex;
    /**
     * Creates a CreateShowCommand object with the given Cineplex
     * @param cineplex Cineplex object which stores list of Shows available at Cineplex
     */
    public CreateShowCommand(Cineplex cineplex){
        this.cineplex = cineplex;
    }

    /**
     * Gets input from user to create a new Show Object that will be stored in the list of Shows in cineplex
     */
    public void execute(){
        Scanner scanner = new Scanner(System.in);
        SilverVillage.getMovieList().listMoviesForAdmin();
        //error handling req for index
        System.out.println();
        System.out.print("Please enter the Movie ID: ");

        while(true) {
            try {
                if(scanner.hasNextInt() == false) {
                    throw new invalidInputException("Movie ID");
                }



                int movieId = scanner.nextInt();
                scanner.nextLine();
                Movie movie = SilverVillage.getMovieList().searchMovieById(movieId);
                if (movie == null){
                    //handle error
                    System.out.println();
                    System.out.println("Movie ID \"" + movieId + "\" does not exist. No showtime created.");
                    return;
                }
                else if(movie != (Movie)movie) {
                    throw new invalidInputException("Movie ID");
                }

                else {
                	System.out.println();
                    System.out.print("Please enter the Date (YYYY-MM-DD HH:MM): ");
                    //scanner.nextLine();
                    String str = scanner.nextLine();

                    //Error handling: when datetime is in the wrong format
                    while(true) {
                        try {

                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                            LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
                            System.out.println();

                            System.out.println("============ List of cinemas ============");
                            cineplex.listCinemas();
                            System.out.print("Please enter the Cinema Code: ");


                            //Error handling: cinema code in wrong format or doesn't exist
                            while(true) {
                                try {

                                    String cinemaCode = scanner.nextLine();
                                    //scanner.nextLine();
                                    Cinema cinema = cineplex.searchCinemaById(cinemaCode);


                                    if (cinema == null) {
                                        System.out.println();
                                        System.out.println("Cinema Code \"" + cinemaCode + "\" does not exist. No new showtime created.");
                                        return;
                                    }
                                    else if(cinema != (Cinema)cinema) {
                                        throw new invalidInputException("Cinema Code");
                                    }

                                    else {
                                        cineplex.getShowList().addShow(new Show(dateTime,cinema,movie));
                                        System.out.println();
                                        System.out.println("New showtime sucessfully added.");
                                        cineplex.getShowList().listShows();
                                    }
                                    break;
                                }
                                catch (invalidInputException e) {
                                    System.out.println(e.getMessage());

                                }
                                System.out.println();
                                System.out.print("Please enter the Cinema Code again: ");
                                scanner.next();
                                continue;
                            }
                            break;
                        }
                        catch (DateTimeParseException e) {
                            System.out.println("Invalid format for Date and Time.");
                            System.out.println();
                            System.out.print("Please enter the Date (YYYY-MM-DD HH:MM) again: ");
                            str = scanner.nextLine();
                            continue;
                        }

                    }

                    break;
                }
            }
            catch (invalidInputException e) {
                System.out.println(e.getMessage());
                System.out.println();
                System.out.print("Please enter the Movie ID again: ");
                scanner.next();
                continue;
            }

        }

    }
}

