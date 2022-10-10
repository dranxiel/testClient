package com.incomex.cliente.application;

import com.incomex.cliente.application.dto.in.CategoryDto;
import com.incomex.cliente.application.port.input.repository.db.ICategoryDb;
import com.incomex.cliente.application.port.input.service.ICategoryService;
import com.incomex.cliente.domain.CategoryDomain;
import com.incomex.cliente.domain.ErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private ICategoryDb categoryDb;

    /**
     * @param categoryDto Recibe un objeto categoria. Solo es requerido el nombre. La foto se maneja como base 64.
     */
    @Override
    public int Create(CategoryDto categoryDto) {

        validateName(categoryDto);
        CategoryDomain categoryDomain = new CategoryDomain(0, categoryDto.getName(), categoryDto.getDescription(), categoryDto.getPictureBase64());

        return categoryDb.create(categoryDomain);
    }

    /**
     * @param categoryDto Recibe un objeto categoria. Y retorna un error si existe la categoria.
     */
    private void validateName(CategoryDto categoryDto) {
        CategoryDomain categoryLocal = categoryDb.getByName(categoryDto.getName());
        if (categoryLocal != null) {
            throw new ApplicationException(ErrorType.INFO_CATEGORY_NAME_IN_USED);
        }
    }

    /**
     * @param id recibe un id de categoria, si existe la retorna, si no retorna una excepcion
     *           se deja abierto  el retorno para futura reutilizacion.
     */
    @Override
    public CategoryDomain getById(int id) {
        CategoryDomain categoryLocal = categoryDb.getById(id);
        if (categoryLocal == null) {
            throw new ApplicationException(ErrorType.INFO_CATEGORY_ID_INVALID);
        }
        return categoryLocal;
    }
}
