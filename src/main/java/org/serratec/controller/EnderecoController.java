package org.serratec.controller;

import java.util.List;

import org.serratec.exception.CustomNoContentException;
import org.serratec.exception.CustomNotFoundException;
import org.serratec.model.Endereco;
import org.serratec.service.EnderecoService;
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
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;   
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retornado com sucesso"),
        @ApiResponse(code = 204, message = "Sem conteúdo"),
        @ApiResponse(code = 401 , message = "Não autorizado"),
        @ApiResponse(code = 403, message = "Proibido acesso"),
        @ApiResponse(code = 500, message = "Erro no servidor")
    })  
    @GetMapping("/listar")
    @ApiOperation(value = "Lista todos os endereços ", notes = "Listagem de endereços no Banco")
    public ResponseEntity<Object> listar() throws CustomNoContentException{
        List<Endereco> enderecos = enderecoService.listar();
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
    @ApiOperation(value = "Buscar endereços por id", notes = "Buscar de endereços por id")
    public ResponseEntity<Object> buscarPorId(@PathVariable Long id) throws CustomNotFoundException{
    	Endereco enderecos = enderecoService.buscarPorId(id);
        return ResponseEntity.ok(enderecos);

    }
    
}
