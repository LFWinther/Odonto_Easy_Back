package org.serratec.service;

import java.util.List;

import org.serratec.exception.CustomNoContentException;
import org.serratec.exception.CustomNotFoundException;
import org.serratec.model.Endereco;
import org.serratec.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    //LISTA TODAS OS ENDERECOS CADASTRADOS
    public List<Endereco> listar(){
        if(enderecoRepository.findAll().isEmpty()){
            throw new CustomNoContentException("");
        }
        return enderecoRepository.findAll();
    }
    
    //BUSCA ENDERECO POR ID
    public Endereco buscarPorId(Long id){
        return enderecoRepository.findById(id)
            .orElseThrow(() -> new CustomNotFoundException("Endereço com id '" + id + "' não foi encontrada"));
    }
}
