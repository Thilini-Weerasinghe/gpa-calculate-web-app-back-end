package com.gpastm.gpa.controler;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.NotAcceptableStatusException;

import com.gpastm.gpa.model.Response;
import com.gpastm.gpa.model.Student;

import com.gpastm.gpa.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	StudentService studentService; 
	
	@GetMapping("/findAllstudent")
	public ResponseEntity<List<Student>> findAllStudent(){
		return new ResponseEntity<List<Student>>(studentService.findAll(), HttpStatus.OK);
	}

	
	@PostMapping("/addStudent")
    public ResponseEntity<Map<String, Object>> addStudent(@RequestBody Student student){
		Map<String, Object> map = new HashMap<>();
		if(student.edit) {
			if(studentService.findUnique(student.studentName, student.id)) {
				throw new NotAcceptableStatusException("student is exsit");
				
			}else {
				
				Student editStudent = studentService.findByid(student.id);
				
				student.ai = editStudent.ai;
				Student savedStudent =  studentService.addStudent(student);		
				map.put("action", new String("saved"));
				map.put("student",savedStudent);	
				return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
				
			}
	
			
		}else {
			if(studentService.findExsit(student.epNumber)){
				throw new NotAcceptableStatusException("that ep number is exsit");
			}else {				
				Student savedStudent =  studentService.addStudent(student);				
				map.put("action", new String("saved"));
				map.put("student", savedStudent );				
				return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
			}

			
		}
		

    	
    }
	
	@PostMapping("/deleteStudent/{studentId}")
	public ResponseEntity<Response> deleteStudent(@PathVariable String studentId){
		studentService.deleteStudent(studentId);
	return new ResponseEntity<Response>(new Response("deleted Course"),HttpStatus.OK);
			}

	@PostMapping(value = "/saveCsvFile", consumes = "multipart/form-data")
	public ResponseEntity<List<Student>> addStudentByCsvFile (
			@RequestParam("file") MultipartFile file,
			@RequestParam("departmentId") String departmentId)  throws IOException {
		
		return new ResponseEntity<List<Student>>(studentService.addstudentBycsvFile(file,departmentId),HttpStatus.OK);
	}
}
