package com.ruffin.Spring_Mvc_Restful_GraphQl.service;

import java.util.List;

import com.ruffin.Spring_Mvc_Restful_GraphQl.entity.Ticket;

public interface ITicketService {

	List<Ticket> listTickets();
}
