package command.adminModule;

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

public class createShowCommand implements Command{
    private Cineplex cineplex;
    public createShowCommand(Cineplex cineplex){
        this.cineplex = cineplex;
    }
    public void execute(){
        Scanner scanner = new Scanner(System.in);
        SilverVillage.getMovieList().listMovies(1);
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
                    System.out.println("Movie with movie ID " + movieId + " does not exist");
                    return;
                }
                else if(movie != (Movie)movie) {
                    throw new invalidInputException("Movie ID");
                }

                else {
                    System.out.print("Please enter the Time (YYYY-MM-DD HH:MM): ");
                    //scanner.nextLine();
                    String str = scanner.nextLine();

                    //Error handling: when datetime is in the wrong format
                    while(true) {
                        try {

                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                            LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
                            System.out.println();

                            System.out.println("============ List of cinemas ============");
                            cineplex.listCinema();
                            System.out.print("Please enter the Cinema Code: ");


                            //Error handling: cinema code in wrong format or doesn't exist
                            while(true) {
                                try {

                                    String cinemaCode = scanner.nextLine();
                                    //scanner.nextLine();
                                    Cinema cinema = cineplex.searchCinema(cinemaCode);


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
                            System.out.println("Invalid format for Date and Time");
                            System.out.println();
                            System.out.print("Please enter the Time (YYYY-MM-DD HH:MM) again: ");
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
                System.out.print("Please enter the movie's Movie ID again: ");
                scanner.next();
                continue;
            }

        }

    }
}

