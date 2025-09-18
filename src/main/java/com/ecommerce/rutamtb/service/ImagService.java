package com.ecommerce.rutamtb.service;

import com.ecommerce.rutamtb.model.Imag;
import com.ecommerce.rutamtb.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImagService implements IImagService {

    private IImagService imagService;

    @Autowired
    public ImagService(IImagService imagService) {
        this.imagService = imagService;
    }


    @Override
    public List<Imag> getAllImags() {
        return imagService.getAllImags();
    }

    @Override
    public Imag getImagById(Long id) {
        return imagService.getImagById(id);
    }

    @Override
    public Imag saveImag(Imag imag) {
        return imagService.saveImag(imag);
    }

    @Override
    public void deleteImag(Imag imag) {
        imagService.deleteImag(imag);
    }
}
