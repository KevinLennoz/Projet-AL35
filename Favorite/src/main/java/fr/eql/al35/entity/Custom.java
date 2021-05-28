package fr.eql.al35.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
public class Custom implements Serializable {

	private static final long serialVersionUID = 1L;
	
	

	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private Double price;
	
	@ManyToOne
	@JoinColumn(name = "product_type_location_id")
	private ProductTypeLocation productTypeLocation;
	
	@ManyToOne
	@JoinColumn(name = "design_id")
	private Design design;
	
	@ManyToOne
	@JoinColumn(name = "article_id")
	private Article article;

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Custom other = (Custom) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		return true;
	}

	public Custom(Double price, ProductTypeLocation productTypeLocation, Design design) {
		super();
		this.price = price;
		this.productTypeLocation = productTypeLocation;
		this.design = design;
	}

	@Override
	public String toString() {
		return "Custom [id=" + id + ", price=" + price + ", productTypeLocation=" + productTypeLocation + ", design="
				+ design + "]";
	}

	
	
}
