package com.alexdev.ecommercebackend.service;

import com.alexdev.ecommercebackend.model.dto.OptionDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OptionService {

    List<OptionDTO> getOptionsDTO(Pageable pageable);

    OptionDTO create(OptionDTO optionDTO);

    OptionDTO update(int optionId, OptionDTO optionDTO);

    OptionDTO getOptionDTO(int optionDTOId);

    OptionDTO getOptionDTOByName(String name);

    OptionDTO delete(int optionDTOId);

    boolean existsById(int id);

    boolean existsByNameIgnoreCase(String name);

    int count();
}
