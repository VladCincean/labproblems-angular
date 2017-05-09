package ro.droptable.labproblems.web.converter;

/**
 * Created by stefana on 5/9/2017.
 */
public interface ConverterGeneric<Model, Dto> {
    Model convertDtoToModel(Dto dto);

    Dto convertModelToDto(Model model);
}