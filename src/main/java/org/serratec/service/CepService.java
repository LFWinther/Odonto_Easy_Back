package org.serratec.service;

import java.util.List;
import java.util.Optional;

import org.serratec.dto.CepDTO;
import org.serratec.exception.CustomNoContentException;
import org.serratec.exception.CustomNotFoundException;
import org.serratec.model.Cep;
import org.serratec.repository.CepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class CepService {

    @Autowired
    private CepRepository cepRepository;

    //BUSCA CEP NO VIACEP
    public CepDTO buscar(String cep){
        Cep cep_endereco = cepRepository.findByCep(cep);
        if(cep_endereco != null){
            return new CepDTO(cep_endereco);
        }else{
            RestTemplate rs = new RestTemplate();
            String url = "https://viacep.com.br/ws/" + cep + "/json/";
            Optional<Cep> enderecoViaCep = Optional.ofNullable(rs.getForObject(url, Cep.class));
            if(!enderecoViaCep.get().getCep().isEmpty()){
                String cepSemTraco = enderecoViaCep.get().getCep().replaceAll("-", "");
                enderecoViaCep.get().setCep(cepSemTraco);
                return inserir(enderecoViaCep.get());
            }
            else{
                return null;
            }
        }       
    }

    //LISTA TODAS OS ENDERECOS CADASTRADOS
    public List<Cep> listar(){
        if(cepRepository.findAll().isEmpty()){
            throw new CustomNoContentException("");
        }
        return cepRepository.findAll();
    }
    
    //BUSCA ENDERECO POR ID
    public Cep buscarPorId(Long id){
        return cepRepository.findById(id)
            .orElseThrow(() -> new CustomNotFoundException("Endereço com id '" + id + "' não foi encontrada"));
    }
    
    
    //CADASTRA CEP NO BANCO
    private CepDTO inserir(Cep endereco) {
        endereco = cepRepository.save(endereco);
        return new CepDTO(endereco);
    }
}
