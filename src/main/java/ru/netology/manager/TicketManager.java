package ru.netology.manager;

import ru.netology.domain.Ticket;
import ru.netology.repository.TicketRepository;

import java.util.Arrays;


public class TicketManager {
    public TicketRepository repository;
    public TicketManager(TicketRepository repository) {
        this.repository = repository;
    }


    public Ticket[] airportSearch(String toAirport, String fromAirport) {
        Ticket[] result = new Ticket[0];
        for (Ticket ticket : repository.findAll()){
            if(ticket.getToAirport().equalsIgnoreCase(toAirport) && ticket.getFromAirport().equalsIgnoreCase(fromAirport)){
                Ticket[] tmp = new Ticket[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = ticket;
                result = tmp;
            }
        }
        Arrays.sort(result);
        return result;
    }
}