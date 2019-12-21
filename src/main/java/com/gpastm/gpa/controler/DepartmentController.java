package com.gpastm.gpa.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gpastm.gpa.model.Department;
import com.gpastm.gpa.model.Response;
import com.gpastm.gpa.service.DepartmentService;

@Controller
@RequestMapping("/department")
public class DepartmentController {

	@Autowired
	DepartmentService departmentService; 
	
	@GetMapping("/findAllDepartment")
	public ResponseEntity<List<Department>> findAllDepartment(){
		return new ResponseEntity<List<Department>>(departmentService.findAll(), HttpStatus.OK);
	}

	
	@PostMapping("/addDepartment")
    public ResponseEntity<Response> addDepartment(@RequestBody Department department){
		departmentService.addDepartment(department);
    	return new ResponseEntity<Response>(new Response("add Department") , HttpStatus.OK);
    }
	
	@PostMapping("/deleteDepartment/{departmentId}")
	public ResponseEntity<Response> deleteDepartment(@PathVariable String departmentId){
		departmentService.deleteDepartment(departmentId);
	return new ResponseEntity<Response>(new Response("deleted department"),HttpStatus.OK);
			}
}
