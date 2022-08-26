package org.serratec.repository;

import java.util.Optional;

import org.serratec.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	public Optional<Usuario> findByNome(String nome);
	
    public Usuario findByEmail(String email);

    @Query(nativeQuery = true, value="select * from usuario u where u.email=:paramEmail")
    public Optional<Usuario> BuscarPorEmail(String paramEmail);
    
    public Usuario findByCpf(String cpf);
    
    public Optional<Usuario> deleteByCpf(String cpf);
    
    public Optional<Usuario> existsByCpf(String cpf);
    
//    @Query(value="FROM Usuario a WHERE a.cargo = 'CLIENTE'")
//    List<Usuario> filterByCliente();
//    
//    @Query(value="FROM Usuario a WHERE a.cargo = 'DENTISTA'")
//    List<Usuario> filterByDentista();
}