package com.ruffin.Spring_Mvc_Restful_GraphQl.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ruffin.Spring_Mvc_Restful_GraphQl.entity.Application;
import com.ruffin.Spring_Mvc_Restful_GraphQl.entity.Ticket;
import com.ruffin.Spring_Mvc_Restful_GraphQl.exception.ApplicationNotFoundException;
import com.ruffin.Spring_Mvc_Restful_GraphQl.service.ApplicationServiceImpl;
import com.ruffin.Spring_Mvc_Restful_GraphQl.service.TicketServiceImpl;

	////////////////////////////////////////
	////VERSION 1 CLASSIQUE CONTROLER///////
	//Permet de tester notre view tymeleaf//
	////////////////////////////////////////

/*
@Controller
public class TzaController {
	
	// Declaration attributs membres aux classes Services
	
	private ApplicationServiceImpl applicationService;
	private ReleaseServiceImpl releaseService;
	private TicketServiceImpl ticketService;
	
	@Autowired
	public void setApplicationService(ApplicationServiceImpl applicationService) {
		this.applicationService = applicationService;
	}
	@Autowired
	public void setReleaseService(ReleaseServiceImpl releaseService) {
		this.releaseService = releaseService;
	}
	@Autowired
	public void setTicketService(TicketServiceImpl ticketService) {
		this.ticketService = ticketService;
	}

	@GetMapping("/applications")
	public String retrieveApplication(Model model) {
		model.addAttribute("applications", applicationService.listApplications());
		return "applications";		
	}
	@GetMapping("/releases")
	public String retrieveRelease(Model model) {
		model.addAttribute("releases", releaseService.listReleases());
		return "releases";		
	}
	@GetMapping("/tickets")
	public String retrieveTicket(Model model) {
		model.addAttribute("tickets", ticketService.listTickets());
		return "tickets";		
	}
*/

	//////////////////////////////////////////////
	///////////VERSION 2 REST CONTROLER///////////
	////PERMET DE TESTER NOTRE API DANS POSTMAN///
	//////////////////////////////////////////////


@RestController
@RequestMapping("/tza")
public class TzaController {
	private ApplicationServiceImpl applicationService;
	private TicketServiceImpl ticketService;
	
	@Autowired
	public void setApplicationService(ApplicationServiceImpl applicationService) {
		this.applicationService = applicationService;
	}
	@Autowired
	public void setTicketService(TicketServiceImpl ticketService) {
		this.ticketService = ticketService;
	}
	
	@GetMapping("/tickets")
	public ResponseEntity<List<Ticket>> getAllTickets(){
		List<Ticket> list = ticketService.listTickets();		
		return new ResponseEntity<List<Ticket>>(list, HttpStatus.OK);
	}
	@GetMapping("/applications")
	public ResponseEntity<List<Application>> getAllApplications(){
		List<Application> list = applicationService.listApplications();		
		return new ResponseEntity<List<Application>>(list, HttpStatus.OK);
	}
	@GetMapping("/applications/{id}")
    public ResponseEntity<Application> getApplication(@PathVariable("id") long id) {
        try {
            return new ResponseEntity<Application>(applicationService.findApplication(id),
                    HttpStatus.OK);
        } catch (ApplicationNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Application Not Found");
        }
    }
	
	
	
	
	
	
	
}
