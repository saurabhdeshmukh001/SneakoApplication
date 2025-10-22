package org.genc.app.SneakoAplication.service.api;

import org.genc.app.SneakoAplication.domain.entity.Category;
import org.genc.app.SneakoAplication.dto.CategoryDTO;

public interface CategoryService {
    public CategoryDTO createCategory(CategoryDTO categoryDTO);

   public CategoryDTO findById(Long id);
   public Category findByCategoryEntityByName(String name);
}
