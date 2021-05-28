package fr.eql.al35.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eql.al35.entity.Photo;
import fr.eql.al35.entity.Product;
import fr.eql.al35.iservice.PhotoIService;
import fr.eql.al35.repository.PhotoIRepository;
import fr.eql.al35.repository.ProductIRepository;


@Service
@Transactional
public class PhotoService implements PhotoIService {
	@Autowired
	PhotoIRepository photoRepo;

	@Autowired
	ProductIRepository productRepo;

	@Override
	public void upDatePhoto(Integer id, String pathPhoto, String descriptionPhoto, Integer ProductId, Integer index) {
		
		Photo photo = new Photo(id, pathPhoto, descriptionPhoto, null, null);
		System.out.println("================================= photo" +photo.toString());
		Product product = productRepo.findById(ProductId).get();
		System.out.println("================================= product" +product.toString());
		Set<Photo> photos = product.getPhotos();
		List<Photo> listPhoto = new ArrayList<Photo>(photos);
		for(int i = 0; i < listPhoto.size(); i++) {
			System.out.println(listPhoto.toString() + index);
			if(i ==index) {
				listPhoto.set(index, photo);
				System.out.println("photo changée à l'index : "+ index);
			}
		}
		photos = new HashSet<Photo>(listPhoto);
		product.setPhotos(photos);
		productRepo.save(product);



	}

}
