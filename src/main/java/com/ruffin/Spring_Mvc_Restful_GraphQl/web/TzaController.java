package com.ruffin.Spring_Mvc_Restful_GraphQl.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ruffin.Spring_Mvc_Restful_GraphQl.service.ApplicationServiceImpl;
import com.ruffin.Spring_Mvc_Restful_GraphQl.service.ReleaseServiceImpl;
import com.ruffin.Spring_Mvc_Restful_GraphQl.service.TicketServiceImpl;



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
		model.addAttribute("applications", applicationService.listApplication());
		return "applications";		
	}
	@GetMapping("/releases")
	public String retrieveRelease(Model model) {
		model.addAttribute("releases", releaseService.listRelease());
		return "releases";		
	}
	@GetMapping("/tickets")
	public String retrieveTicket(Model model) {
		model.addAttribute("tickets", ticketService.listTicket());
		return "tickets";		
	}
	
	
	
	
	

}
