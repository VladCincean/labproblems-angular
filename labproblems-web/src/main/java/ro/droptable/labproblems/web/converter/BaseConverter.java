package ro.droptable.labproblems.web.converter;

import ro.droptable.labproblems.core.model.BaseEntity;
import ro.droptable.labproblems.web.dto.BaseDto;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by vlad on 01.05.2017.
 */
public abstract class BaseConverter<Model extends BaseEntity<Long>, Dto extends BaseDto> implements Converter<Model, Dto> {

    @Override
    public Model convertDtoToModel(Dto dto) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public Dto convertModelToDto(Model model) {
        throw new RuntimeException("not implemented");
    }

    public Set<Long> convertModelsToIDs(Set<Model> models) {
        return models.stream()
                .map(model -> model.getId())
                .collect(Collectors.toSet());
    }

    public Set<Long> convertDTOsToIDs(Set<Dto> dtos) {
        return dtos.stream()
                .map(dto -> dto.getId())
                .collect(Collectors.toSet());
    }

    public Set<Dto> convertModelsToDtos(Collection<Model> models) {
        return models.stream()
                .map(model -> convertModelToDto(model))
                .collect(Collectors.toSet());
    }
}