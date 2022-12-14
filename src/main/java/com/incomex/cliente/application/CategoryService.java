package com.incomex.cliente.application;

import com.incomex.cliente.application.dto.in.CategoryDto;
import com.incomex.cliente.application.dto.out.CategoryOut;
import com.incomex.cliente.application.port.input.repository.cache.ICache;
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

    @Autowired
    private ICache<CategoryDto> categoryDtoICache;

    /**
     * @param categoryDto Recibe un objeto categoria. Solo es requerido el nombre. La foto se maneja como base 64.
     * @return un id de categoria en una entidad para facilitar su lectura
     */
    @Override
    public CategoryOut Create(CategoryDto categoryDto) {

        validateName(categoryDto);
        CategoryDomain categoryDomain = mapToCategoryDomain(categoryDto);

        return new CategoryOut(categoryDb.create(categoryDomain));
    }

    private CategoryDomain mapToCategoryDomain(CategoryDto categoryDto) {
        CategoryDomain categoryDomain = new CategoryDomain(0, categoryDto.getName(), categoryDto.getDescription(), categoryDto.getPictureBase64());
        return categoryDomain;
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
     */
    @Override
    public CategoryDto getById(int id) {
        CategoryDto categoryDto = categoryDtoICache.get(id, CategoryDto.class.getName(), CategoryDto.class);
        if (categoryDto == null) {
            CategoryDomain categoryLocal = categoryDb.getById(id);
            if (categoryLocal == null) {
                throw new ApplicationException(ErrorType.INFO_CATEGORY_ID_INVALID);
            }
            categoryDto = mapToCategoryDto(categoryLocal);
            categoryDtoICache.save(id, CategoryDto.class.getName(), categoryDto);

        }
        return categoryDto;
    }

    private CategoryDto mapToCategoryDto(CategoryDomain categoryLocal) {
        return new CategoryDto(categoryLocal.getName(),
                categoryLocal.getDescription(), categoryLocal.getPictureBase64());
    }
}
