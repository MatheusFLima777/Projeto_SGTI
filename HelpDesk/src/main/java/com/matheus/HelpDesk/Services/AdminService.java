package com.matheus.HelpDesk.Services;

import com.matheus.HelpDesk.Repository.AdminRepository;
import com.matheus.HelpDesk.Repository.PessoaRepository;
import com.matheus.HelpDesk.Resources.execption.DataIntegrityViolationException;
import com.matheus.HelpDesk.Resources.execption.ObjNotFoundException;
import com.matheus.HelpDesk.domain.Admin;
import com.matheus.HelpDesk.domain.DTOS.AdminDTO;
import com.matheus.HelpDesk.domain.Pessoa;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    private final PessoaRepository pessoaRepository;
    private final AdminRepository adminRepository;

    public AdminService(PessoaRepository pessoaRepository, AdminRepository adminRepository) {
        this.pessoaRepository = pessoaRepository;
        this.adminRepository = adminRepository;
    }


    public Admin findById(Integer id) {
        Optional<Admin> obj = adminRepository.findById(id);
        return obj.orElseThrow(() -> new ObjNotFoundException("Admin não encontrado com ID: " + id));
    }

    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    public Admin create(AdminDTO objDTO) {
        objDTO.setId(null);
        validaCPFeEmail(objDTO);
        Admin newObj = new Admin(objDTO);
        return adminRepository.save(newObj);
    }

    private void validaCPFeEmail(AdminDTO objDTO) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
        }

        obj = pessoaRepository.findByEmail(objDTO.getEmail());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
            throw new DataIntegrityViolationException("E-mail já cadastrado no sistema");
        }

    }

    public Admin update(Integer id, AdminDTO objDTO) {
        objDTO.setId(id);
        Admin oldObj = findById(id);
        validaCPFeEmail(objDTO);
        oldObj = new Admin(objDTO);
        return pessoaRepository.save(oldObj);


    }

    public void  delete(Integer id) {
        Admin obj = findById(id);
        if (obj.getChamados().size() > 0) {
            throw new DataIntegrityViolationException("Admin possui chamados e não pode ser deletado!");
        }
        adminRepository.deleteById(id);


    }
}
