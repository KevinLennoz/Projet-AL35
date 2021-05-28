package fr.eql.al35.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eql.al35.entity.Custom;
import fr.eql.al35.iservice.CustomIService;
import fr.eql.al35.repository.DesignIRepository;
import fr.eql.al35.repository.ProductTypeLocationIRepository;

@Service
public class CustomService implements CustomIService {
	
	@Autowired
	private ProductTypeLocationIRepository productTypeLocationRepo;
	@Autowired
	private DesignIRepository designRepo;

	@Override
	public void addCustom(List<Custom> customs, Integer idDesign, Integer idLocation) {
		Custom custom = new Custom();
		custom.setProductTypeLocation(productTypeLocationRepo.findById(idLocation).get());
		custom.setDesign(designRepo.findById(idDesign).get());
		custom.setPrice(designRepo.findById(idDesign).get().getPrice());
		customs.add(custom);
	}
}
