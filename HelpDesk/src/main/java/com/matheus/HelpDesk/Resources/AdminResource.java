package com.matheus.HelpDesk.Resources;

import com.matheus.HelpDesk.Services.AdminService;
import com.matheus.HelpDesk.domain.Admin;
import com.matheus.HelpDesk.domain.DTOS.AdminDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/admin")
public class AdminResource {

    private AdminService adminService;
    public AdminResource(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AdminDTO> findById(@PathVariable Integer id){
        Admin obj = this.adminService.findById(id);
        return ResponseEntity.ok().body(new AdminDTO(obj));
    }

    @GetMapping
    public  ResponseEntity<List<AdminDTO>> findAll(){
        List<Admin> list = adminService.findAll();
        List<AdminDTO> listDTO = list.stream().map(obj -> new AdminDTO(obj)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDTO);
    }
}
