package com.learnspringbootinmay.practisingselfaboutspboot01.Controller;


import com.learnspringbootinmay.practisingselfaboutspboot01.Repository.EmployeeRepository;
import com.learnspringbootinmay.practisingselfaboutspboot01.entity.Employee;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/emp")
public class EmployeeController {

   @Autowired
   private EmployeeRepository employeeRepository;
//    get  post  put   delete operations

    @GetMapping("/get")
    public ResponseEntity<Object> getAllEmployee(){
        return ResponseEntity.status(HttpStatus.FOUND).body(employeeRepository.findAll());
     }


    @PostMapping("/cre")
    public ResponseEntity<Object> createaccount(@RequestBody  @Valid Employee employee) {
        employeeRepository.save(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body("Employee created successfully");
    }

//    update
    @PutMapping("/resubmit/{id}")
    public ResponseEntity<Object> updatemp(@PathVariable Long id, @RequestBody Employee employee){
        Optional<Employee> emp=employeeRepository.findById(id);
        if(emp.isPresent()){
            Employee existingemp=emp.get();
            existingemp.setName(employee.getName());
            existingemp.setBalance(existingemp.getBalance());
            employeeRepository.save(existingemp);
            return ResponseEntity.status(HttpStatus.OK).body("Employee updated successfull");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee NOT FOUND !!! ");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Long id){
        Optional<Employee> emp=employeeRepository.findById(id);
        if(emp.isPresent()){
            employeeRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Employee account deleted sucessfully");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee Not exist ");
        }
    }
}
