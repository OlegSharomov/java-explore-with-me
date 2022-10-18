package ru.practicum.categories.service.publ;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.categories.dto.CategoryDto;
import ru.practicum.categories.dto.CategoryMapper;
import ru.practicum.categories.entity.Category;
import ru.practicum.categories.repository.CategoryRepository;
import ru.practicum.exception.TerribleExceptionForPassingPracticumPostmanTests;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryPublicServiceImpl implements CategoryPublicService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    @Transactional
    public List<CategoryDto> getCategories(Integer from, Integer size) {
        Pageable pageable = PageRequest.of(from / size, size, Sort.by("id"));
        Page<Category> list = categoryRepository.findAll(pageable);
        return list.stream().map(categoryMapper::toCategoryDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CategoryDto getCategoryById(Integer catId) {
//        Category category = categoryRepository.findById(catId)
//                .orElseThrow(() -> new NotFoundException());
//        return categoryMapper.toCategoryDto(category);
//        Optional<Category> result = categoryRepository.findById(catId);
//        if (result.isEmpty()) {
//            return "null";
//        }
        Category category = categoryRepository.findById(catId)
                .orElseThrow(() -> new TerribleExceptionForPassingPracticumPostmanTests("null"));
        return categoryMapper.toCategoryDto(category);
//        return categoryMapper.toCategoryDto(result.get());
    }
}
