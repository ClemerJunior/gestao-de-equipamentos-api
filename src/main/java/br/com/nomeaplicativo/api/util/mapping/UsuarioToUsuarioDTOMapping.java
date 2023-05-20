package br.com.nomeaplicativo.api.util.mapping;

import br.com.nomeaplicativo.api.domain.Usuario;
import br.com.nomeaplicativo.api.domain.dtos.UsuarioDTO;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import net.rakugakibox.spring.boot.orika.OrikaMapperFactoryConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsuarioToUsuarioDTOMapping implements OrikaMapperFactoryConfigurer {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void configure(MapperFactory orikaMapperFactory) {
        orikaMapperFactory.classMap(Usuario.class, UsuarioDTO.class)
                .customize(new CustomMapper<Usuario, UsuarioDTO>() {
                    /**
                     * Criptografar a senha para persistir no banco
                     */
                    @Override
                    public void mapBtoA(UsuarioDTO usuarioDTO, Usuario usuario, MappingContext context) {
                        super.mapBtoA(usuarioDTO, usuario, context);
                        var senhaCriptografada = bCryptPasswordEncoder.encode(usuarioDTO.getSenha());
                        usuario.setSenha(senhaCriptografada);
                        usuario.setEmail(usuarioDTO.getEmail().toLowerCase());
                    }
                })
                .byDefault().register();
    }
}
