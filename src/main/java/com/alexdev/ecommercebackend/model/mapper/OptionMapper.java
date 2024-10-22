package com.alexdev.ecommercebackend.model.mapper;

import com.alexdev.ecommercebackend.model.dto.OptionDTO;
import com.alexdev.ecommercebackend.model.entity.Option;

import java.util.List;

public interface OptionMapper {

    Option toOption(OptionDTO optionDTO);

    OptionDTO toOptionDto(Option option);

    List<OptionDTO> toOptionDtos(List<Option> options);

    void updateOption(Option option, OptionDTO optionDTO);
}
