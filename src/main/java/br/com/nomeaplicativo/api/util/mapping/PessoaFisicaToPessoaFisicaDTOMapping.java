package br.com.nomeaplicativo.api.util.mapping;

import br.com.nomeaplicativo.api.domain.PessoaFisica;
import br.com.nomeaplicativo.api.domain.dtos.PessoaFisicaDTO;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFactory;
import net.rakugakibox.spring.boot.orika.OrikaMapperFactoryConfigurer;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PessoaFisicaToPessoaFisicaDTOMapping implements OrikaMapperFactoryConfigurer {


    @Override
    public void configure(MapperFactory orikaMapperFactory) {
        orikaMapperFactory.classMap(PessoaFisica.class, PessoaFisicaDTO.class)
                .fieldMap("id", "id").mapNullsInReverse(false).mapNulls(false).add()
                .byDefault().register();
    }
}
