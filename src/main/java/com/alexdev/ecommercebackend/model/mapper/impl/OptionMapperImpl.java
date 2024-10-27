package com.alexdev.ecommercebackend.model.mapper.impl;

import com.alexdev.ecommercebackend.model.dto.OptionDTO;
import com.alexdev.ecommercebackend.model.entity.Option;
import com.alexdev.ecommercebackend.model.mapper.OptionMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OptionMapperImpl implements OptionMapper {

    @Override
    public Option toOption(OptionDTO optionDTO) {
        if (optionDTO == null) {
            return null;
        }
        return Option.builder()
                .id(optionDTO.getId())
                .name(optionDTO.getName())
                .creationDate(optionDTO.getCreationDate())
                .products(optionDTO.getProducts())
                .build();
    }

    @Override
    public OptionDTO toOptionDto(Option option) {
        if (option == null) {
            return null;
        }
        return OptionDTO.builder()
                .id(option.getId())
                .name(option.getName())
                .creationDate(option.getCreationDate())
                .products(option.getProducts())
                .build();
    }

    @Override
    public List<OptionDTO> toOptionDtos(List<Option> options) {
        if (options == null) {
            return null;
        }
        List<OptionDTO> optionDTOS = new ArrayList<>(options.size());
        for (Option option : options) {
            optionDTOS.add(toOptionDto(option));
        }
        return optionDTOS;
    }

    @Override
    public void updateOption(Option option, OptionDTO optionDTO) {
        if (optionDTO == null) {
            return;
        }
        option.setId(optionDTO.getId());
        option.setName(optionDTO.getName());
        option.setProducts(optionDTO.getProducts());
    }
}
