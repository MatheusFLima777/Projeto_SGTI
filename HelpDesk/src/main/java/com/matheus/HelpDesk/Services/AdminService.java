package com.matheus.HelpDesk.Services;

import com.matheus.HelpDesk.Repository.AdminRepository;
import com.matheus.HelpDesk.Repository.PessoaRepository;
import com.matheus.HelpDesk.Resources.execption.ObjNotFoundException;
import com.matheus.HelpDesk.domain.Admin;
import com.matheus.HelpDesk.domain.DTOS.AdminDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private PessoaRepository pessoaRepository;



    public AdminService(AdminRepository adminRepository, PessoaRepository pessoaRepository) {

        this.adminRepository = adminRepository;
        this.pessoaRepository = pessoaRepository;

    }

    public Admin findById(Integer id) {
        Optional<Admin> obj = adminRepository.findById(id);
        return obj.orElseThrow(() -> new ObjNotFoundException("Admin não encontrado com ID: "+ id));

    }
    public List<Admin> findAll() {
        return adminRepository.findAll();
    }
    public Admin create(AdminDTO objDTO){
        objDTO.setId(null);
        Admin newObj = new Admin(objDTO);
        return adminRepository.save(newObj);
    }


    }


