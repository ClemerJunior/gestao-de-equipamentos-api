package br.com.nomeaplicativo.api.util.mapping;

import br.com.nomeaplicativo.api.util.genericrestcrud.PageDTO;
import ma.glasnost.orika.MapperFactory;
import net.rakugakibox.spring.boot.orika.OrikaMapperFactoryConfigurer;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class PageToPageDTOMapping implements OrikaMapperFactoryConfigurer {

    @Override
    public void configure(MapperFactory orikaMapperFactory) {
        orikaMapperFactory.classMap(Page.class, PageDTO.class)
            .field("content", "items")
            .field("pageable.pageNumber", "pageNumber")
            .field("pageable.pageSize", "pageSize")
            .byDefault().register();
    }
}
