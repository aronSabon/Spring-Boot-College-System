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


import appSoft.project.model.FeesType;
import appSoft.project.service.FeesTypeService;

@RestController
public class FeesTypeRestController {

	@Autowired   
	private FeesTypeService fts;  
	


	
	@GetMapping("/api/feesType")
	public List<FeesType> getAll() {
		return fts.getAllFeesType();
	}
	@GetMapping("/api/feesType/{id}")
	public FeesType getOneEmp(@PathVariable int id) {
		return fts.getFeesTypeById(id);
	}
	@PostMapping("/api/feesType")
	public String add(@RequestBody FeesType feesType) {
		fts.addFeesType(feesType);
		return "success";
	}
	@DeleteMapping("/api/feesType/{id}")
	public String delete(@PathVariable int id) {
		fts.deleteFeesTypeById(id);
		return "success";
	}
	@PutMapping("/api/feesType")
	public String update(@RequestBody FeesType feesType) {
		fts.updateFeesType(feesType);
		return "Success";
	}
	
}
