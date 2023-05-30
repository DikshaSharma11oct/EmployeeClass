package com.app.rest;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Employee;
import com.app.service.IEmpService;

@RestController //to recognize rest api
@RequestMapping("/r") //this is root to make differentiate between same url firing("/all") for get mapping of 
                         //controller and rest controller by server
public class EmpRestController {
	@Autowired
	private IEmpService empService;
	
	@GetMapping("/all") //to retreive data from db,by this url Angular api will identify the server location(path) of all employee method
	public ResponseEntity<?> getAllemps() //Response entity is return type of rest controller
	{ 
		ResponseEntity<?> response =null; //Response entity is null as we want to send data on try and catch basis
		
		try {
		   List<Employee> list= empService.getAllEmployees(); 
		   response= new ResponseEntity<List<Employee>>(list,HttpStatus.OK);//to get services from emp interface
		}
		catch(Exception e)
		{
			response= new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);// exception message return string
		}
	 return response;
	}
      
	
	@PostMapping("/save")//to save data in db
    public ResponseEntity<String> createEmployee(@RequestBody Employee employee)
    { 
		ResponseEntity<String> response =null;
    	try
    	{
    		boolean saved = empService.saveEmployee(employee);
    		response= new ResponseEntity<String>("success",HttpStatus.CREATED);
    		
    	}
    	catch(Exception e)
    	{
    		response= new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    	 return response;
    }
	
	@GetMapping("/oneEmp/{id}") //to get one emp data
	public ResponseEntity<?> getOneEmp(@PathVariable("id") Integer id)
	{ 
		ResponseEntity<?> response =null; 
		
		try {
		   Employee employee= empService.getOneEmp(id); 
		   response= new ResponseEntity<Employee>(employee,HttpStatus.OK);
		}
		catch(Exception e)
		{
			response= new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);// exception message return string
		}
	 return response;
	}
	
	@PutMapping("/update/{id}")//to save data in db
    public ResponseEntity<String> updateEmployee(@RequestBody Employee employee,@PathVariable("id") Integer id)
    { 
		ResponseEntity<String> response =null;
    	try
    	{
    		Employee emp = empService.getOneEmp(id);
    		BeanUtils.copyProperties(employee, emp);//employee gets copied in to emp
    		empService.updateEmployee(emp);
    		response= new ResponseEntity<String>("updated emp",HttpStatus.OK);
    		
    	}
    	catch(Exception e)
    	{
    		response= new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    	 return response;
    }
	@GetMapping("/delete/{id}") //to get one emp data
	public ResponseEntity<?> deleteEmp(@PathVariable("id") Integer id)
	{ 
		ResponseEntity<?> response =null; 
		
		try {
		   empService.deleteEmp(id); 
		   response= new ResponseEntity<String>("delete emp",HttpStatus.OK);
		}
		catch(Exception e)
		{
			response= new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);// exception message return string
		}
	 return response;
	}
	
}
    
	/*asynchronous is to call multiple component without taking too much burden on client side 
 here in api every operation solely need to create like update and edit,on component in edit case post mapping internally work 
  to load page on particular id,same page gets reloaded 
 but here on edit a ,request will go to server and another coponent of angular api gets generated here you will add data on same id and click on update
 then postmapping method will carry body and id with itself.
 edit,delete,update ,post -.provide url

 on controller data send by browser so data sent in url in the form of key and value but in api data is sent only in url
 simply dont make things complicated.one application interacts with other application but in web application case request sent by browser

in case of web application put ,patch,trace ,delete method is not allowed only get and post works}*/
	
