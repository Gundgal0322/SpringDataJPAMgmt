package com.csi.controller;

import com.csi.exception.RecordNotFoundException;
import com.csi.model.Employee;
import com.csi.service.EmployeeServiceImpl;
import com.csi.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1")
public class EmployeeController {

    @Autowired
    EmployeeServiceImpl employeeServiceImpl;

    @PostMapping("/savedata")
    public ResponseEntity<String> saveData(@RequestBody Employee employee){
        employeeServiceImpl.saveData(employee);
        return ResponseEntity.ok("Data SavedSuccessfully");
    }
    @GetMapping("/getdatabyid/{emmpId}")
    public ResponseEntity<Optional<Employee>> getDataById(@PathVariable int empId){
        return   ResponseEntity.ok(employeeServiceImpl.getDataById(empId));
    }

    @GetMapping("getalldata")
    public  ResponseEntity<Employee> getAllData(@RequestBody Employee employee){
        return ResponseEntity.ok((Employee) employeeServiceImpl.getAllData());
    }

    @PutMapping("/updatedata/{empId}")
    public ResponseEntity<Employee> updateData(@PathVariable int empId, @RequestBody Employee employee){

        Employee employee1 = employeeServiceImpl.getDataById(empId).orElseThrow(() -> new RecordNotFoundException("Id does Not Exist"));

        employee1.setEmpName( employee.getEmpName());
        employee1.setEmpAddress(employee.getEmpAddress());
        employee1.setEmpDOB(employee.getEmpDOB());

        return ResponseEntity.ok(employeeServiceImpl.updateData(employee));

    }

    @DeleteMapping("/deletedatabyid/{empId}")
    public ResponseEntity<String> deleteDataById(@PathVariable int empId){
        employeeServiceImpl.deleteDataById(empId);
        return ResponseEntity.ok("Record Deleted Successfully");
    }

}
