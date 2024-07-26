package appSoft.project.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import appSoft.project.model.Transport;
import appSoft.project.service.TransportService;

@RestController
public class TransportRestController {

	@Autowired   
	private TransportService ts;  
	


	
	@GetMapping("/api/transport")
	public List<Transport> getAll() {
		return ts.getAllTransport();
	}
	@GetMapping("/api/transport/{id}")
	public Transport getOneEmp(@PathVariable int id) {
		return ts.getTransportById(id);
	}
	@PostMapping("/api/transport")
	public String add(@RequestBody Transport transport) {
		ts.addTransport(transport);
		return "success";
	}
	@DeleteMapping("/api/transport/{id}")
	public String delete(@PathVariable int id) {
		ts.deleteTransportById(id);
		return "success";
	}
	@PutMapping("/api/transport")
	public String update(@RequestBody Transport transport) {
		ts.updateTransport(transport);
		return "Success";
	}
	
}
