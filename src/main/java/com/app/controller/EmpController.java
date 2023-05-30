package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.app.model.Employee;
import com.app.service.IEmpService;

@Controller
public class EmpController {
	
	@Autowired
	private IEmpService empService;
	
	@GetMapping("/rg")
     public String loadRegisterPage(Model model)
     {
       model.addAttribute("emp",new Employee());
      return "register";
     }
	
	@PostMapping("/save")
     public String handleSubmitBtn(@ModelAttribute("emp") Employee employee,Model model)
     {
		      boolean save = empService.saveEmployee(employee);
		      if(save)
		      {
		        model.addAttribute("sucMsg","successful!");
		      }
		      else
		      {
		    	model.addAttribute("failMsg","failed!");
		      }
		      
		return "register";
    	 
     }
	@GetMapping("/all")
	public String handleHyperLink(Model model)
	{
		List<Employee>list=empService.getAllEmployees();
		{
			model.addAttribute("lists",list);
			return "empData";
			
		}
		
		
	}
	@GetMapping("/edit")
	public String editEmp(@RequestParam("id") Integer id,Model model) {
		Employee employee= empService.getOneEmp(id);//on behalf of id we are fecthing single emp data
		model.addAttribute("emp",employee);//now on behalf of id we want to go register page and to load data there on basis of id from table and we are
		return "register";//returning to html page so model will go there and filled the data in emp as emp is bind as object there
		
	}
	
	@GetMapping("/delete")
	public String deleteEmp(@RequestParam("id") Integer id) {
		 empService.deleteEmp(id);
		
		return "redirect:all";
	} 
}
