package fr.eql.al35.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eql.al35.entity.Photo;
import fr.eql.al35.iservice.PhotoIService;
import fr.eql.al35.repository.PhotoIRepository;


@Service
@Transactional
public class PhotoService implements PhotoIService {
	@Autowired
	PhotoIRepository photoRepo;

	@Override
	public Photo upDatePhoto(Integer id, Photo photo) {
		photo.setId(id);
		return photoRepo.save(photo);
		
	}



}
