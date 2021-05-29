package fr.eql.al35.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eql.al35.entity.Custom;
import fr.eql.al35.entity.Design;
import fr.eql.al35.entity.ProductTypeLocation;
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
		Optional<ProductTypeLocation> productType = productTypeLocationRepo.findById(idLocation);
		custom.setProductTypeLocation(productType.isPresent() ? productType.get() : null);
		Optional<Design> design = designRepo.findById(idDesign);
		if(design.isPresent()) {
			custom.setDesign(design.get());
			custom.setPrice(design.get().getPrice());
		}
		customs.add(custom);
	}
}
