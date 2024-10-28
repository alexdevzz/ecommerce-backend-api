package com.alexdev.ecommercebackend.service.impl;

import com.alexdev.ecommercebackend.model.dto.OptionDTO;
import com.alexdev.ecommercebackend.model.entity.Option;
import com.alexdev.ecommercebackend.model.mapper.OptionMapper;
import com.alexdev.ecommercebackend.repository.OptionRepository;
import com.alexdev.ecommercebackend.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OptionServiceImpl implements OptionService {

    @Autowired
    private OptionRepository optionRepository;
    @Autowired
    private OptionMapper optionMapper;


    @Override
    public List<OptionDTO> getOptionsDTO(Pageable pageable) {
        return optionMapper.toOptionDtos(optionRepository.findAll(pageable).getContent());
    }

    @Override
    public OptionDTO create(OptionDTO optionDTO) {
        optionDTO.setId(0);
        optionDTO.setCreationDate(new Date());
        optionDTO.setProducts(new ArrayList<>());
        Option option = optionMapper.toOption(optionDTO);
        return optionMapper.toOptionDto(optionRepository.save(option));
    }

    @Override
    public OptionDTO update(int optionId, OptionDTO optionDTO) {
        Option option = optionRepository.findById(optionId).get();
        optionDTO.setId(optionId);
        optionMapper.updateOption(option, optionDTO);
        return optionMapper.toOptionDto(optionRepository.save(option));
    }

    @Override
    public OptionDTO getOptionDTO(int optionDTOId) {
        Option option = optionRepository.findById(optionDTOId).get();
        return optionMapper.toOptionDto(option);
    }

    @Override
    public OptionDTO getOptionDTOByName(String name) {
        return optionMapper.toOptionDto(optionRepository.getByNameIgnoreCase(name));
    }

    @Override
    public OptionDTO delete(int optionDTOId) {
        Option option = optionRepository.findById(optionDTOId).get();
        OptionDTO optionDTO = optionMapper.toOptionDto(option);
        optionRepository.delete(option);
        return optionDTO;
    }

    @Override
    public boolean existsById(int id) {
        return optionRepository.existsById(id);
    }

    @Override
    public boolean existsByNameIgnoreCase(String name) {
        return optionRepository.existsByNameIgnoreCase(name.strip());
    }

    @Override
    public int count() {
        return (int) optionRepository.count();
    }
}
