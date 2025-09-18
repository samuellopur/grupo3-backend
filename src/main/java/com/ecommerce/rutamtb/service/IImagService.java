package com.ecommerce.rutamtb.service;

import com.ecommerce.rutamtb.model.Imag;
import java.util.List;

public interface IImagService {
    List<Imag> getAllImags();
    Imag getImagById(Long id);
    Imag saveImag(Imag imag);
    void deleteImag(Imag imag);
}
