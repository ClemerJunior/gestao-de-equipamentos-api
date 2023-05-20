package br.com.nomeaplicativo.api.services;

import br.com.nomeaplicativo.api.domain.Perfil;
import br.com.nomeaplicativo.api.repositories.PerfilRepository;
import br.com.nomeaplicativo.api.util.genericrestcrud.GenericService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerfilService extends GenericService<Perfil, Long> {

    private final PerfilRepository repository;

    @Override
    protected PerfilRepository getRepository() {
        return repository;
    }
}
