package br.com.nomeaplicativo.api.util.mapping;

import br.com.nomeaplicativo.api.domain.PessoaFisica;
import br.com.nomeaplicativo.api.domain.dtos.CadastroDTO;
import ma.glasnost.orika.MapperFactory;
import net.rakugakibox.spring.boot.orika.OrikaMapperFactoryConfigurer;
import org.springframework.stereotype.Component;

@Component
public class PessoaFisicaToCadastroDTOMapping implements OrikaMapperFactoryConfigurer {

    @Override
    public void configure(MapperFactory orikaMapperFactory) {
        orikaMapperFactory.classMap(PessoaFisica.class, CadastroDTO.class)
                .byDefault()
                .register();
    }
}
