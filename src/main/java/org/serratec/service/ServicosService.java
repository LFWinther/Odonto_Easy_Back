package org.serratec.service;

import java.util.List;

import org.serratec.exception.ServicosException;
import org.serratec.exception.CustomNoContentException;
import org.serratec.exception.CustomNotFoundException;
import org.serratec.model.Servicos;
import org.serratec.repository.ServicosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicosService {
    
    @Autowired
    private ServicosRepository servicoRepository;

    //LISTA TODAS AS servicoS CADASTRADAS
    public List<Servicos> listar(){
        if(servicoRepository.findAll().isEmpty()){
            throw new CustomNoContentException("");
        }
        return servicoRepository.findAll();
    }

    //BUSCA servico POR ID
    public Servicos buscarPorId(Long id){
        return servicoRepository.findById(id)
            .orElseThrow(() -> new CustomNotFoundException("servico com id '" + id + "' não foi encontrada"));
    }

    //CADASTRA servicoS
    public Servicos inserir(Servicos servico){
        if(servicoRepository.findByNome(servico.getNome().toUpperCase()) != null){
            throw new ServicosException("servico '" + servico.getNome() + "' já está cadastrada");
        }
        servico.setNome(servico.getNome().toUpperCase());
        servico.setDescricao(servico.getDescricao());
        servico.setPreco(servico.getPreco());
        servico.setTempoServico(servico.getTempoServico());
        return servicoRepository.save(servico);
    }

    //ATUALIZA servico
    public Servicos atualizar(Servicos servico, Long id){
		if(servicoRepository.existsById(id)){
            servico.setId(id);
            servico.setNome(servico.getNome().toUpperCase());
            servico.setDescricao(servico.getDescricao());
            servico.setPreco(servico.getPreco());
            servico.setTempoServico(servico.getTempoServico());
            return servicoRepository.save(servico);
        }   
		throw new CustomNotFoundException("servico com id '" + id + "' não foi encontrada");
    }

    //DELETA servico
    public void deletar(Long id){    
        if(servicoRepository.existsById(id)){
            servicoRepository.deleteById(id);
        }
        else{
            throw new CustomNotFoundException("servico com id '" + id + "' não foi encontrada");
        }
    }
    
}
