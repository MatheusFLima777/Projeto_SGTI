package com.matheus.HelpDesk.Resources;

import com.matheus.HelpDesk.Services.AdminService;
import com.matheus.HelpDesk.domain.Admin;
import com.matheus.HelpDesk.domain.DTOS.AdminDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/admins")
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
    @PostMapping
    public ResponseEntity<AdminDTO> create(@RequestBody AdminDTO objAdminDTO) {
        Admin newObj = adminService.create(objAdminDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(newObj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
