package ro.droptable.labproblems.web.converter;

import ro.droptable.labproblems.core.model.BaseEntity;
import ro.droptable.labproblems.web.dto.BaseDto;

/**
 * Created by vlad on 01.05.2017.
 */
public interface Converter<Model extends BaseEntity<Long>, Dto extends BaseDto> {

    Model convertDtoToModel(Dto dto);

    Dto convertModelToDto(Model model);
}

