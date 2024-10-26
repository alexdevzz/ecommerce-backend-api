package com.alexdev.ecommercebackend.service;

import com.alexdev.ecommercebackend.model.dto.OptionDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OptionService {

    List<OptionDTO> getOptions(Pageable pageable);

    OptionDTO save(OptionDTO optionDTO);

    OptionDTO update(int optionId, OptionDTO optionDTO);

    OptionDTO getOption(int optionDTOId);

    OptionDTO delete(int optionDTOId);

    boolean existsById(int id);

    int count();
}
