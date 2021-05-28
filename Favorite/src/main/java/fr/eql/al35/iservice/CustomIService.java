package fr.eql.al35.iservice;

import java.util.List;

import fr.eql.al35.entity.Custom;

public interface CustomIService {

	public void addCustom(List<Custom> customs, Integer idDesign, Integer idLocation);
}
