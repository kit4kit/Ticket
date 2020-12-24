package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Ticket;
import ru.netology.repository.TicketRepository;

import static org.junit.jupiter.api.Assertions.*;

class TicketManagerTest {
    private TicketRepository repository = new TicketRepository();
    private TicketManager manager = new TicketManager(repository);

    @Test
    void shouldAirportSearch() {

        Ticket first = new Ticket(1, 2000, "VLG", "MSK", 500);
        Ticket second = new Ticket(2, 2000, "KZN", "MSK", 500);
        Ticket third = new Ticket(3, 2000, "VLG", "KZN", 500);

        repository.save(first);
        repository.save(second);
        repository.save(third);

        String to = "KZN";
        String from = "VLG";

        Ticket[] actual = manager.airportSearch(to, from);
        Ticket[] expected = new Ticket[]{third};

        assertArrayEquals(expected, actual);
    }
    @Test
    void noTicketNeeded() {

        Ticket first = new Ticket(1, 2000, "VLG", "MSK", 500);
        Ticket second = new Ticket(2, 2000, "KZN", "MSK", 500);
        Ticket third = new Ticket(3, 2000, "MSK", "KZN", 500);

        repository.save(first);
        repository.save(second);
        repository.save(third);

        String to = "KZN";
        String from = "VLG";

        Ticket[] actual = manager.airportSearch(to, from);
        Ticket[] expected = new Ticket[]{};

        assertArrayEquals(expected, actual);
    }
    @Test
    void shouldSortTicket() {

        Ticket first = new Ticket(1, 2000, "VLG", "MSK", 500);
        Ticket second = new Ticket(2, 2000, "KZN", "MSK", 500);
        Ticket third = new Ticket(3, 2000, "VLG", "KZN", 500);
        Ticket fourth = new Ticket(3, 5000, "VLG", "KZN", 500);
        Ticket fifth = new Ticket(3, 1000, "VLG", "KZN", 500);

        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(fourth);
        repository.save(fifth);

        String to = "KZN";
        String from = "VLG";

        Ticket[] actual = manager.airportSearch(to, from);
        Ticket[] expected = new Ticket[]{fifth, third, fourth};

        assertArrayEquals(expected, actual);
    }
}