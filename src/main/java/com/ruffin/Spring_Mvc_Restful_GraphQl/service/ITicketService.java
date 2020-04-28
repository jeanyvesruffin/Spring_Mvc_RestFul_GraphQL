package com.ruffin.Spring_Mvc_Restful_GraphQl.service;

import com.ruffin.Spring_Mvc_Restful_GraphQl.entity.Ticket;

public interface ITicketService {

	Iterable<Ticket> listTickets();
}
