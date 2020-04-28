package com.ruffin.Spring_Mvc_Restful_GraphQl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruffin.Spring_Mvc_Restful_GraphQl.entity.Ticket;
import com.ruffin.Spring_Mvc_Restful_GraphQl.repository.ITicketRepository;

@Service
public class TicketServiceImpl implements ITicketService {

	@Autowired
	private ITicketRepository ticketRepository;
	
	@Override
	public Iterable<Ticket> listTicket() {
		return ticketRepository.findAll();
	}

}
