package com.ruffin.Spring_Mvc_Restful_GraphQl.repository;

import org.springframework.data.repository.CrudRepository;

import com.ruffin.Spring_Mvc_Restful_GraphQl.entity.Ticket;


public interface ITicketRepository extends CrudRepository<Ticket, Long>{

}
