package com.ecommerce.rutamtb.service;

import com.ecommerce.rutamtb.model.Imag;
import com.ecommerce.rutamtb.repository.IImagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImagService implements IImagService {

    private IImagRepository imagRepository;

    @Autowired
    public ImagService(IImagRepository imagRepository) {
        this.imagRepository = imagRepository;
    }

    @Override
    public List<Imag> getAllImags() {
        return imagRepository.findAll();
    }

    @Override
    public Imag getImagById(Long id) {
        return imagRepository.findById(id).orElse(null);
    }

    @Override
    public Imag saveImag(Imag imag) {
        return imagRepository.save(imag);
    }

    @Override
    public void deleteImag(Imag imag) {
        imagRepository.delete(imag);
    }
}
