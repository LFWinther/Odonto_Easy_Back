package org.serratec.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.serratec.dto.UsuarioDTO;
import org.serratec.dto.UsuarioInserirDTO;
import org.serratec.exception.CpfException;
import org.serratec.exception.CustomNoContentException;
import org.serratec.exception.CustomNotFoundException;
import org.serratec.exception.EmailException;
import org.serratec.exception.UsuarioException;
import org.serratec.model.Usuario;
import org.serratec.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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


//@CrossOrigin(origins = "*", allowedHeaders = "*")

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retornado com sucesso"),
        @ApiResponse(code = 204, message = "Sem conteúdo"),
        @ApiResponse(code = 401 , message = "Não autorizado"),
        @ApiResponse(code = 403, message = "Proibido acesso"),
        @ApiResponse(code = 500, message = "Erro no servidor")
    })
    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "Lista todos os usuarios", notes = "Listagem de usuarios")
    public ResponseEntity<Object> listarTodos() throws CustomNoContentException{
        List<UsuarioDTO> usuarios = usuarioService.listar();
        return ResponseEntity.ok(usuarios);
    }

	 @ApiResponses(value = {
		 @ApiResponse(code = 200, message = "Retornado com sucesso"),
		 @ApiResponse(code = 401 , message = "Não autorizado"),
		 @ApiResponse(code = 403, message = "Proibido acesso"),
		 @ApiResponse(code = 404 , message = "Não encontrado"),
		 @ApiResponse(code = 500, message = "Erro no servidor")
	 })
	 @GetMapping("/id={id}") 
	 @ApiOperation(value = "Lista usuario por id", notes = "Listagem de usuarios por id")
	 public ResponseEntity<Object> buscarPorId(@PathVariable String id)throws CustomNotFoundException{
		 
		 Optional<Usuario> usuario = usuarioService.buscarPorId(Long.parseLong(id)); return
		 ResponseEntity.ok(usuario);
	 }
    
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retornado com sucesso"),
        @ApiResponse(code = 401 , message = "Não autorizado"),
        @ApiResponse(code = 403, message = "Proibido acesso"),
        @ApiResponse(code = 404 , message = "Não encontrado"),
        @ApiResponse(code = 500, message = "Erro no servidor")
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/email={email}")
    @ApiOperation(value = "Lista usuario por email", notes = "Listagem de usuarios por email")
    public ResponseEntity<Object> buscarPorEmail(@PathVariable String email)throws CustomNotFoundException{
        
        Usuario usuario = usuarioService.buscarPorEmail(email);
        return ResponseEntity.ok(usuario);
    }
    
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retornado com sucesso"),
        @ApiResponse(code = 401 , message = "Não autorizado"),
        @ApiResponse(code = 403, message = "Proibido acesso"),
        @ApiResponse(code = 404 , message = "Não encontrado"),
        @ApiResponse(code = 500, message = "Erro no servidor")
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/cpf={cpf}")
    @ApiOperation(value = "Lista usuario por cpf", notes = "Listagem de usuarios por cpf")
    public ResponseEntity<Object> buscarPorCpf(@PathVariable String cpf)throws CustomNotFoundException{
        
        Usuario usuario = usuarioService.buscarPorCpf(cpf);
        return ResponseEntity.ok(new UsuarioDTO(usuario));
    }

    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "Retornado com sucesso"),
    		@ApiResponse(code = 401 , message = "Não autorizado"),
    		@ApiResponse(code = 403, message = "Proibido acesso"),
    		@ApiResponse(code = 404 , message = "Não encontrado"),
    		@ApiResponse(code = 500, message = "Erro no servidor")
    })
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Optional<Usuario>> buscarPorId(@PathVariable Long id) throws UsuarioException {
    	return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }
    
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retornado com sucesso"),
        @ApiResponse(code = 202, message = "Criado com sucesso"),
        @ApiResponse(code = 401 , message = "Não autorizado"),
        @ApiResponse(code = 403, message = "Proibido acesso"),
        @ApiResponse(code = 500, message = "Erro no servidor")
    })
    @PostMapping
    @ApiOperation(value = "Cadastra usuario", notes = "Cadastrar usuarios")
    public ResponseEntity<Object> inserir(@Valid @RequestBody UsuarioInserirDTO usuarioInserirDTO)
    throws EmailException, CpfException, CustomNotFoundException{
        
        UsuarioDTO usuarioDTO = usuarioService.inserir(usuarioInserirDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDTO);
    }

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retornado com sucesso"),
        @ApiResponse(code = 202, message = "Criado com sucesso"),
        @ApiResponse(code = 401 , message = "Não autorizado"),
        @ApiResponse(code = 404 , message = "Não Encontrado"),
        @ApiResponse(code = 403, message = "Proibido acesso"),
        @ApiResponse(code = 500, message = "Erro no servidor")
    })
    @PutMapping("/cpf={cpf}")
    @ApiOperation(value = "Atualizar usuario por cpf", notes = "Atualiza usuario por cpf")
    public ResponseEntity<Object> atualizar(@Valid @RequestBody UsuarioInserirDTO usuarioInserirDTO, 
    @PathVariable String cpf) throws CustomNotFoundException{
        
        UsuarioDTO usuarioDTO = usuarioService.atualizar(usuarioInserirDTO, cpf);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDTO);
    }

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retornado com sucesso"),
        @ApiResponse(code = 401 , message = "Não autorizado"),
        @ApiResponse(code = 404 , message = "Não Encontrado"),
        @ApiResponse(code = 403, message = "Proibido acesso"),
        @ApiResponse(code = 500, message = "Erro no servidor")
    })
    @DeleteMapping("/cpf={cpf}")
    @ApiOperation(value = "Deletar usuario por cpf", notes = "Delta usuario por cpf")
    public ResponseEntity<Object> deletar(@PathVariable String cpf) throws CustomNotFoundException{
        usuarioService.deletar(cpf);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(cpf);
    }
    
//    @ApiResponses(value = {
//    		@ApiResponse(code = 200, message = "Retornado com sucesso"),
//    		@ApiResponse(code = 401 , message = "Não autorizado"),
//    		@ApiResponse(code = 403, message = "Proibido acesso"),
//    		@ApiResponse(code = 404 , message = "Não encontrado"),
//    		@ApiResponse(code = 500, message = "Erro no servidor")
//    })
//    @GetMapping("/filtro/dentista")
//    public ResponseEntity<List<Usuario>> FiltrarDentistas() throws UsuarioException {
//    	return ResponseEntity.ok(usuarioService.FiltrarDentistas());
//    }
//    
//    @ApiResponses(value = {
//    		@ApiResponse(code = 200, message = "Retornado com sucesso"),
//    		@ApiResponse(code = 401 , message = "Não autorizado"),
//    		@ApiResponse(code = 403, message = "Proibido acesso"),
//    		@ApiResponse(code = 404 , message = "Não encontrado"),
//    		@ApiResponse(code = 500, message = "Erro no servidor")
//    })
//    
//    @GetMapping("/filtro/cliente")
//    public ResponseEntity<List<Usuario>> FiltrarClientes() throws UsuarioException {
//    	return ResponseEntity.ok(usuarioService.FiltrarClientes());
//    }
    
}
