package org.serratec.controller;

import java.util.List;

import org.serratec.dto.CepDTO;
import org.serratec.exception.CustomNoContentException;
import org.serratec.exception.CustomNotFoundException;
import org.serratec.model.Cep;
import org.serratec.service.CepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/cep")
public class CepController {

    @Autowired
    private CepService enderecoService;
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retornado com sucesso"),
        @ApiResponse(code = 400, message = "Erro de requisição"),
        @ApiResponse(code = 401 , message = "Não autorizado"),
        @ApiResponse(code = 403, message = "Proibido acesso"),
        @ApiResponse(code = 404, message = "Não encontrado"),
        @ApiResponse(code = 500, message = "Erro no servidor")
    })
    @GetMapping("/api/{cep}")
    @ApiOperation(value = "Cadastrar cep Viacep", notes = "Valida o cep no ViaCep e registra no banco")
    public ResponseEntity<CepDTO> buscar(@PathVariable String cep){
        CepDTO enderecoDTO =  enderecoService.buscar(cep);
        if(enderecoDTO == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(enderecoDTO);
    }
    
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retornado com sucesso"),
        @ApiResponse(code = 204, message = "Sem conteúdo"),
        @ApiResponse(code = 401 , message = "Não autorizado"),
        @ApiResponse(code = 403, message = "Proibido acesso"),
        @ApiResponse(code = 500, message = "Erro no servidor")
    })  
    @GetMapping("/listar")
    @ApiOperation(value = "Lista todos os ceps ", notes = "Listagem de ceps no Banco")
    public ResponseEntity<Object> listar() throws CustomNoContentException{
        List<Cep> enderecos = enderecoService.listar();
        return ResponseEntity.ok(enderecos);
    }
    
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retornado com sucesso"),
        @ApiResponse(code = 401 , message = "Não autorizado"),
        @ApiResponse(code = 403, message = "Proibido acesso"),
        @ApiResponse(code = 404 , message = "Não encontrado"),
        @ApiResponse(code = 500, message = "Erro no servidor")
    })
    @GetMapping("/id={id}")
    @ApiOperation(value = "Buscar ceps por id", notes = "Buscar de ceps por id")
    public ResponseEntity<Object> buscarPorId(@PathVariable Long id) throws CustomNotFoundException{
    	Cep enderecos = enderecoService.buscarPorId(id);
        return ResponseEntity.ok(enderecos);

    }
    
}
