package org.serratec.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.serratec.dto.UsuarioDTO;
import org.serratec.dto.UsuarioInserirDTO;
import org.serratec.exception.CpfException;
import org.serratec.exception.CustomNoContentException;
import org.serratec.exception.CustomNotFoundException;
import org.serratec.exception.EmailException;
import org.serratec.model.Endereco;
import org.serratec.model.Usuario;
import org.serratec.model.UsuarioPerfil;
import org.serratec.repository.CepRepository;
import org.serratec.repository.EnderecoRepository;
import org.serratec.repository.UsuarioPerfilRepository;
import org.serratec.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CepService cepService;

    @Autowired
    private CepRepository cepRepository;
    
    @Autowired
    private EnderecoRepository enderecoRepository;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    private PerfilService perfilService;

    @Autowired
    private UsuarioPerfilRepository usuarioPerfilRepository;

    //LISTAR TODOS OS CLIENTES CADASTRADOS
    public List<UsuarioDTO> listar(){
        if(usuarioRepository.findAll().isEmpty()){
            throw new CustomNoContentException(null);
        }
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(usuario -> new UsuarioDTO(usuario)).collect(Collectors.toList());
    }

	 //LISTAR CLIENTE POR ID 	 
	 public Optional<Usuario> buscarPorId(Long id) {
	        if(usuarioRepository.findById(id) != null){
	            return usuarioRepository.findById(id);
	        }
	        else{
	            throw new CustomNotFoundException("Usuario com email '" + id + "' não foi encontrado");
	        }
	 }
    
    //LISTAR CLIENTE POR EMAIL
    public Usuario buscarPorEmail(String email){    
        if(usuarioRepository.findByEmail(email) != null){
            return usuarioRepository.findByEmail(email);
        }
        else{
            throw new CustomNotFoundException("Usuario com email '" + email + "' não foi encontrado");
        }
    }
    
    //LISTAR CLIENTE POR CPF
    public Usuario buscarPorCpf(String cpf){     
        if(usuarioRepository.findByCpf(cpf) != null){
            return usuarioRepository.findByCpf(cpf);
        }
        else{
            throw new CustomNotFoundException("Usuario com cpf '" + cpf + "' não foi encontrado");
        }
    }
    
    //CADASTRAR CLIENTE
    public UsuarioDTO inserir(UsuarioInserirDTO usuarioInserirDTO){
        if(usuarioRepository.findByEmail(usuarioInserirDTO.getEmail()) != null){
            throw new EmailException("Email já cadastrado!");
        }
        else if(usuarioRepository.findByCpf(usuarioInserirDTO.getCpf()) != null){
            throw new CpfException("Cpf já cadastrado!");
        }
        
        Endereco endereco = new Endereco();
        cepService.buscar(usuarioInserirDTO.getCep());
        endereco.setCep(cepRepository.findByCep(usuarioInserirDTO.getCep()));
        endereco.setComplemento(usuarioInserirDTO.getComplemento());
        endereco.setNumero(usuarioInserirDTO.getNumero());
        enderecoRepository.save(endereco);
        
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioInserirDTO.getNome());
        usuario.setCpf(usuarioInserirDTO.getCpf());
        usuario.setEmail(usuarioInserirDTO.getEmail());
        usuario.setPassword(bCryptPasswordEncoder.encode(usuarioInserirDTO.getPassword()));
        usuario.setEndereco(endereco);
        usuario.setNascimento(usuarioInserirDTO.getNascimento());
        usuario.setTelefone(usuarioInserirDTO.getTelefone());
        usuario = usuarioRepository.save(usuario);
        
        for (UsuarioPerfil usuarioPerfil : usuarioInserirDTO.getUsuarioPerfis()) {
			usuarioPerfil.setUsuario(usuario);
			usuarioPerfil.setPerfil(perfilService.buscar(usuarioPerfil.getPerfil().getId()));
			usuarioPerfil.setDataCriacao(LocalDate.now());
		}
		usuarioPerfilRepository.saveAll(usuarioInserirDTO.getUsuarioPerfis());
		System.out.println(usuario);
        return new UsuarioDTO(usuario);
    }

    
    
    //ATUALIZAR CLIENTE POR CPF
    public UsuarioDTO atualizar(UsuarioInserirDTO usuarioInserirDTO, String cpf){
        if(usuarioRepository.findByCpf(cpf) != null){        	
            Endereco endereco = usuarioRepository.findByCpf(cpf).getEndereco();
            
            if (usuarioInserirDTO.getCep() != null) {
            	cepService.buscar(usuarioInserirDTO.getCep());
            	endereco.setCep(cepRepository.findByCep(usuarioInserirDTO.getCep()));
            }
            
            if (usuarioInserirDTO.getComplemento() != null) {
            	endereco.setComplemento(usuarioInserirDTO.getComplemento());
            }
            
            if (usuarioInserirDTO.getNumero() != null) {
            	endereco.setNumero(usuarioInserirDTO.getNumero());
            }
            
            enderecoRepository.save(endereco);
            
            Usuario usuario = usuarioRepository.findByCpf(cpf); 
            
            if (usuarioInserirDTO.getNome() != null) {
            	usuario.setNome(usuarioInserirDTO.getNome());
            }
            
            if (usuarioInserirDTO.getCpf() != null) {
            	usuario.setCpf(usuarioInserirDTO.getCpf());
            }
            
            if (usuarioInserirDTO.getEmail() != null) {
            	usuario.setEmail(usuarioInserirDTO.getEmail());
            }
            
            if (usuarioInserirDTO.getPassword() != null) {
            	usuario.setPassword(usuarioInserirDTO.getPassword());
            }

            usuario.setEndereco(endereco); 
            
            if (usuarioInserirDTO.getNascimento() != null) {
            	usuario.setNascimento(usuarioInserirDTO.getNascimento());
            } 
            
            if (usuarioInserirDTO.getTelefone() != null) {
            	usuario.setTelefone(usuarioInserirDTO.getTelefone());
            }
            
            usuarioRepository.save(usuario);
            return new UsuarioDTO(usuario);        
        }   
                   
	 	throw new CustomNotFoundException("Usuario com cpf '" + cpf + "' não foi encontrado");
    }

    //DELETAR CLIENTE POR CPF
    public void deletar(String cpf){
        if(usuarioRepository.findByCpf(cpf) != null){
            usuarioRepository.deleteByCpf(cpf);
        }else{
            throw new CustomNotFoundException("Usuario com cpf '" + cpf + "' não foi encontrado");
        }
    }
    
    //FILTRAR DENTISTAS
//    public List<Usuario> FiltrarDentistas(){    
//        if(usuarioRepository.filterByDentista() != null){
//            return usuarioRepository.filterByDentista();
//        }
//        else{
//            throw new CustomNotFoundException("Nenhum dentista foi encontrado");
//        }
//    }
    
    //FILTRAR CLIENTES
//    public List<Usuario> FiltrarClientes(){    
//        if(usuarioRepository.filterByCliente() != null){
//            return usuarioRepository.filterByCliente();
//        }
//        else{
//            throw new CustomNotFoundException("Nenhum cliente foi encontrado");
//        }
//    }
    
}
