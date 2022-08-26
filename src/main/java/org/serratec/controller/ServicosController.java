package org.serratec.controller;

import java.util.List;

import javax.validation.Valid;

import org.serratec.exception.ServicosException;
import org.serratec.exception.CustomNoContentException;
import org.serratec.exception.CustomNotFoundException;
import org.serratec.model.Servicos;
import org.serratec.service.ServicosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/servico")
public class ServicosController {

    @Autowired
    private ServicosService servicoService;
    
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retornado com sucesso"),
        @ApiResponse(code = 204, message = "Sem conteúdo"),
        @ApiResponse(code = 401 , message = "Não autorizado"),
        @ApiResponse(code = 403, message = "Proibido acesso"),
        @ApiResponse(code = 500, message = "Erro no servidor")
    })  
    @GetMapping
    @ApiOperation(value = "Lista todos as servicos", notes = "Listagem de servicos")
    public ResponseEntity<Object> listar() throws CustomNoContentException{
        List<Servicos> servicos = servicoService.listar();
        return ResponseEntity.ok(servicos);
    }

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retornado com sucesso"),
        @ApiResponse(code = 401 , message = "Não autorizado"),
        @ApiResponse(code = 403, message = "Proibido acesso"),
        @ApiResponse(code = 404 , message = "Não encontrado"),
        @ApiResponse(code = 500, message = "Erro no servidor")
    })
    @GetMapping("/id/{id}")
    @ApiOperation(value = "Lista servico por id", notes = "Listagem de servicos por id")
    public ResponseEntity<Object> buscarPorId(@PathVariable Long id) throws CustomNotFoundException{
        Servicos servico = servicoService.buscarPorId(id);
        return ResponseEntity.ok(servico);

    }

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retornado com sucesso"),
        @ApiResponse(code = 202, message = "Criado com sucesso"),
        @ApiResponse(code = 401 , message = "Não autorizado"),
        @ApiResponse(code = 403, message = "Proibido acesso"),
        @ApiResponse(code = 500, message = "Erro no servidor")
    })
    @PostMapping
    @ApiOperation(value = "Inserir servico", notes = "Insere uma servico")
    public ResponseEntity<Object> inserir(@Valid @RequestBody Servicos servico)throws ServicosException{
        servico = servicoService.inserir(servico);
        return ResponseEntity.status(HttpStatus.CREATED).body(servico);

    }

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retornado com sucesso"),
        @ApiResponse(code = 202, message = "Criado com sucesso"),
        @ApiResponse(code = 401 , message = "Não autorizado"),
        @ApiResponse(code = 404 , message = "Não Encontrado"),
        @ApiResponse(code = 403, message = "Proibido acesso"),
        @ApiResponse(code = 500, message = "Erro no servidor")
    })
    @PutMapping("/{id}")
    @ApiOperation(value = "Atualizar servico por id", notes = "Atualizar uma servico por id")
    public ResponseEntity<Object> atualizar(@Valid @RequestBody Servicos servico, @PathVariable Long id)
    throws CustomNotFoundException{
        
        servico = servicoService.atualizar(servico, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(servico);
    }

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retornado com sucesso"),
        @ApiResponse(code = 401 , message = "Não autorizado"),
        @ApiResponse(code = 404 , message = "Não Encontrado"),
        @ApiResponse(code = 403, message = "Proibido acesso"),
        @ApiResponse(code = 500, message = "Erro no servidor")
    })
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletar servico por id", notes = "Deleta uma servico por id")
    public ResponseEntity<Object> deletar(@PathVariable Long id) throws CustomNotFoundException{
        servicoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
