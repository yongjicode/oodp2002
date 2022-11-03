public class userListMovies implements Command{
	private Cineplex cineplex;
	public userListMovies(Cineplex cineplex) {
		this.cineplex = cineplex;
	}
	
	public void execute() {
		this.cineplex.listMovies();
	}
}
