package com.alexdev.ecommercebackend.service;

import com.alexdev.ecommercebackend.model.dto.OptionDTO;

import java.util.List;

public interface OptionService {

    List<OptionDTO> getOptions();

    OptionDTO save(OptionDTO optionDTO);

    OptionDTO update(int optionId, OptionDTO optionDTO);

    OptionDTO getOption(int optionDTOId);

    OptionDTO delete(int optionDTOId);

    boolean existsById(int id);
}
