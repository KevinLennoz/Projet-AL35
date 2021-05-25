package fr.eql.al35.service;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fr.eql.al35.entity.ProductTypeLocation;
import fr.eql.al35.repository.ProductTypeLocationIRepository;


@Service
@Transactional
public class ProductTypeLocationService implements ProductTypeLocationIService {

    @Autowired
    private ProductTypeLocationIRepository productTypeLocationIRepository; 

    @Override
    public List<ProductTypeLocation> displayAllProductTypeLocation() {
        // TODO Auto-generated method stub
        return (List<ProductTypeLocation>) productTypeLocationIRepository.findAll();
    }







}