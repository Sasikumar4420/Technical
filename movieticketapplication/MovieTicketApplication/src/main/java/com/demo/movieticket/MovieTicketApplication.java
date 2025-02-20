package com.demo.movieticket;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.demo.movieticket.entity.Seats;
import com.demo.movieticket.entity.Shows;
import com.demo.movieticket.entity.Users;
import com.demo.movieticket.repository.SeatsRepository;
import com.demo.movieticket.repository.ShowsReposiotry;
import com.demo.movieticket.repository.UserRepository;
@EnableScheduling
@SpringBootApplication
public class MovieTicketApplication implements CommandLineRunner {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ShowsReposiotry showsReposiotry;
	@Autowired
	private SeatsRepository seatsRepository;

	public static void main(String[] args) {
		SpringApplication.run(MovieTicketApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		createUser();
		createShows();
		createSeats();

	}

	private void createSeats() {
		List<Seats> seats = new ArrayList<>();
		LocalDateTime showDateTime1 = LocalDateTime.of(2024, 04, 04, 12, 0);
		LocalDateTime showDateTime2 = LocalDateTime.of(2024, 04, 04, 3, 0);
		LocalDateTime showDateTime3 = LocalDateTime.of(2024, 04, 04, 6, 0);
		LocalDateTime showDateTime4 = LocalDateTime.of(2024, 04, 04, 9, 0);

		seats.add(new Seats(1l, "A1", new Shows(1l, "Kanchana", showDateTime1)));
		seats.add(new Seats(2l, "A2", new Shows(1l, "Kanchana", showDateTime1)));
		seats.add(new Seats(3l, "A3", new Shows(1l, "Kanchana", showDateTime1)));
		seats.add(new Seats(4l, "A4", new Shows(1l, "Kanchana", showDateTime1)));
		seats.add(new Seats(5l, "A5", new Shows(1l, "Kanchana", showDateTime1)));
		seats.add(new Seats(6l, "A6", new Shows(1l, "Kanchana", showDateTime1)));
		seats.add(new Seats(7l, "A7", new Shows(1l, "Kanchana", showDateTime1)));
		seats.add(new Seats(8l, "A8", new Shows(1l, "Kanchana", showDateTime1)));
		seats.add(new Seats(9l, "A9", new Shows(1l, "Kanchana", showDateTime1)));
		seats.add(new Seats(10l, "A10",new Shows(1l,"Kanchana", showDateTime1)));

		seats.add(new Seats(11l, "A1", new Shows(2l, "Marvel", showDateTime2)));
		seats.add(new Seats(12l, "A2", new Shows(2l, "Marvel", showDateTime2)));
		seats.add(new Seats(13l, "A3", new Shows(2l, "Marvel", showDateTime2)));
		seats.add(new Seats(14l, "A4", new Shows(2l, "Marvel", showDateTime2)));
		seats.add(new Seats(15l, "A5", new Shows(2l, "Marvel", showDateTime2)));
		seats.add(new Seats(16l, "A6", new Shows(2l, "Marvel", showDateTime2)));
		seats.add(new Seats(17l, "A7", new Shows(2l, "Marvel", showDateTime2)));
		seats.add(new Seats(18l, "A8", new Shows(2l, "Marvel", showDateTime2)));
		seats.add(new Seats(19l, "A9", new Shows(2l, "Marvel", showDateTime2)));
		seats.add(new Seats(20l, "A10", new Shows(2l, "Marvel",showDateTime2)));

		seats.add(new Seats(21l, "A1", new Shows(3l, "IronMan", showDateTime3)));
		seats.add(new Seats(22l, "A2", new Shows(3l, "IronMan", showDateTime3)));
		seats.add(new Seats(23l, "A3", new Shows(3l, "IronMan", showDateTime3)));
		seats.add(new Seats(24l, "A4", new Shows(3l, "IronMan", showDateTime3)));
		seats.add(new Seats(25l, "A5", new Shows(3l, "IronMan", showDateTime3)));
		seats.add(new Seats(26l, "A6", new Shows(3l, "IronMan", showDateTime3)));
		seats.add(new Seats(27l, "A7", new Shows(3l, "IronMan", showDateTime3)));
		seats.add(new Seats(28l, "A8", new Shows(3l, "IronMan", showDateTime3)));
		seats.add(new Seats(29l, "A9", new Shows(3l, "IronMan", showDateTime3)));
		seats.add(new Seats(30l, "A10", new Shows(3l,"IronMan", showDateTime3)));

		seats.add(new Seats(31l, "A1", new Shows(4l, "Xman", showDateTime4)));
		seats.add(new Seats(32l, "A2", new Shows(4l, "Xman", showDateTime4)));
		seats.add(new Seats(33l, "A3", new Shows(4l, "Xman", showDateTime4)));
		seats.add(new Seats(34l, "A4", new Shows(4l, "Xman", showDateTime4)));
		seats.add(new Seats(35l, "A5", new Shows(4l, "Xman", showDateTime4)));
		seats.add(new Seats(36l, "A6", new Shows(4l, "Xman", showDateTime4)));
		seats.add(new Seats(37l, "A7", new Shows(4l, "Xman", showDateTime4)));
		seats.add(new Seats(38l, "A8", new Shows(4l, "Xman", showDateTime4)));
		seats.add(new Seats(39l, "A9", new Shows(4l, "Xman", showDateTime4)));
		seats.add(new Seats(40l, "A10", new Shows(4l,"Xman", showDateTime4)));

		LocalDateTime showDateTime5 = LocalDateTime.of(2024, 04, 05, 12, 0);
		LocalDateTime showDateTime6 = LocalDateTime.of(2024, 04, 05, 3, 0);
		LocalDateTime showDateTime7 = LocalDateTime.of(2024, 04, 05, 6, 0);
		LocalDateTime showDateTime8 = LocalDateTime.of(2024, 04, 05, 9, 0);

		seats.add(new Seats(41l, "A1", new Shows(5l, "Kanchana", showDateTime5)));
		seats.add(new Seats(42l, "A2", new Shows(5l, "Kanchana", showDateTime5)));
		seats.add(new Seats(43l, "A3", new Shows(5l, "Kanchana", showDateTime5)));
		seats.add(new Seats(44l, "A4", new Shows(5l, "Kanchana", showDateTime5)));
		seats.add(new Seats(45l, "A5", new Shows(5l, "Kanchana", showDateTime5)));
		seats.add(new Seats(46l, "A6", new Shows(5l, "Kanchana", showDateTime5)));
		seats.add(new Seats(47l, "A7", new Shows(5l, "Kanchana", showDateTime5)));
		seats.add(new Seats(48l, "A8", new Shows(5l, "Kanchana", showDateTime5)));
		seats.add(new Seats(49l, "A9", new Shows(5l, "Kanchana", showDateTime5)));
		seats.add(new Seats(50l, "A10", new Shows(5l,"Kanchana", showDateTime5)));

		seats.add(new Seats(51l, "A1", new Shows(6l, "Marvel", showDateTime6)));
		seats.add(new Seats(52l, "A2", new Shows(6l, "Marvel", showDateTime6)));
		seats.add(new Seats(53l, "A3", new Shows(6l, "Marvel", showDateTime6)));
		seats.add(new Seats(54l, "A4", new Shows(6l, "Marvel", showDateTime6)));
		seats.add(new Seats(55l, "A5", new Shows(6l, "Marvel", showDateTime6)));
		seats.add(new Seats(56l, "A6", new Shows(6l, "Marvel", showDateTime6)));
		seats.add(new Seats(57l, "A7", new Shows(6l, "Marvel", showDateTime6)));
		seats.add(new Seats(58l, "A8", new Shows(6l, "Marvel", showDateTime6)));
		seats.add(new Seats(59l, "A9", new Shows(6l, "Marvel", showDateTime6)));
		seats.add(new Seats(60l, "A10", new Shows(6l,"Marvel", showDateTime6)));

		seats.add(new Seats(61l, "A1", new Shows(7l, "IronMan", showDateTime7)));
		seats.add(new Seats(62l, "A2", new Shows(7l, "IronMan", showDateTime7)));
		seats.add(new Seats(63l, "A3", new Shows(7l, "IronMan", showDateTime7)));
		seats.add(new Seats(64l, "A4", new Shows(7l, "IronMan", showDateTime7)));
		seats.add(new Seats(65l, "A5", new Shows(7l, "IronMan", showDateTime7)));
		seats.add(new Seats(66l, "A6", new Shows(7l, "IronMan", showDateTime7)));
		seats.add(new Seats(67l, "A7", new Shows(7l, "IronMan", showDateTime7)));
		seats.add(new Seats(68l, "A8", new Shows(7l, "IronMan", showDateTime7)));
		seats.add(new Seats(69l, "A9", new Shows(7l, "IronMan", showDateTime7)));
		seats.add(new Seats(70l, "A10", new Shows(7l,"IronMan", showDateTime7)));

		seats.add(new Seats(71l, "A1", new Shows(8l, "Xman", showDateTime8)));
		seats.add(new Seats(72l, "A2", new Shows(8l, "Xman", showDateTime8)));
		seats.add(new Seats(73l, "A3", new Shows(8l, "Xman", showDateTime8)));
		seats.add(new Seats(74l, "A4", new Shows(8l, "Xman", showDateTime8)));
		seats.add(new Seats(75l, "A5", new Shows(8l, "Xman", showDateTime8)));
		seats.add(new Seats(76l, "A6", new Shows(8l, "Xman", showDateTime8)));
		seats.add(new Seats(77l, "A7", new Shows(8l, "Xman", showDateTime8)));
		seats.add(new Seats(78l, "A8", new Shows(8l, "Xman", showDateTime8)));
		seats.add(new Seats(79l, "A9", new Shows(8l, "Xman", showDateTime8)));
		seats.add(new Seats(80l, "A10", new Shows(8l,"Xman", showDateTime8)));

		seatsRepository.saveAll(seats);

	}

	private void createShows() {
		List<Shows> shows = new ArrayList<>();

		LocalDateTime showDateTime1 = LocalDateTime.of(2024, 04, 04, 12, 0);
		LocalDateTime showDateTime2 = LocalDateTime.of(2024, 04, 04, 3, 0);
		LocalDateTime showDateTime3 = LocalDateTime.of(2024, 04, 04, 6, 0);
		LocalDateTime showDateTime4 = LocalDateTime.of(2024, 04, 04, 9, 0);

		shows.add(new Shows(1l, "Kanchana", showDateTime1));
		shows.add(new Shows(2l, "Marvel", showDateTime2));
		shows.add(new Shows(3l, "IronMan", showDateTime3));
		shows.add(new Shows(4l, "Xman", showDateTime4));

		LocalDateTime showDateTime5 = LocalDateTime.of(2024, 04, 05, 12, 0);
		LocalDateTime showDateTime6 = LocalDateTime.of(2024, 04, 05, 3, 0);
		LocalDateTime showDateTime7 = LocalDateTime.of(2024, 04, 05, 6, 0);
		LocalDateTime showDateTime8 = LocalDateTime.of(2024, 04, 05, 9, 0);

		shows.add(new Shows(5l, "Kanchana", showDateTime5));
		shows.add(new Shows(6l, "Marvel", showDateTime6));
		shows.add(new Shows(7l, "IronMan", showDateTime7));
		shows.add(new Shows(8l, "Xman", showDateTime8));
		showsReposiotry.saveAll(shows);
	}

	private void createUser() {
		List<Users> users = new ArrayList<>();
		users.add(new Users(1l, "suresh", "suresh@gmail.com", 987654l));
		users.add(new Users(2l, "vijay", "vijay@gmail.com", 98765l));
		users.add(new Users(3l, "ram", "ram@gmail.com", 9845765l));
		userRepository.saveAll(users);
	}

}
