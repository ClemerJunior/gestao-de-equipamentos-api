package br.com.nomeaplicativo.api.services;

import br.com.nomeaplicativo.api.domain.Usuario;
import br.com.nomeaplicativo.api.repositories.UsuarioRepository;
import br.com.nomeaplicativo.api.util.Messages;
import br.com.nomeaplicativo.api.util.genericrestcrud.GenericService;
import br.com.nomeaplicativo.api.util.Constants;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UsuarioService extends GenericService<Usuario, Long> implements UserDetailsService {

    private final UsuarioRepository repository;

    private final Messages messages;

    @Override
    public UsuarioRepository getRepository() {
        return repository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Usuario usuario =  repository.findByNomeUsuario(userName)
                .orElseThrow(() -> new UsernameNotFoundException(messages.getMessage(Constants.MSG_ERRO_LOGIN)));
        Hibernate.initialize(usuario.getPerfil().getAcoes());
        usuario.setUltimoAcesso(LocalDateTime.now());

        return repository.save(usuario);
    }

    @Transactional
    public UserDetails loadUserById(Long id) throws UsernameNotFoundException {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(messages.getMessage(Constants.MSG_ERRO_USUARIO_ID_INVALIDO, id.toString())));
        Hibernate.initialize(usuario.getPerfil().getAcoes());

        return usuario;
    }
}
