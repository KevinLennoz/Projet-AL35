package fr.eql.al35.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String reference;
	private LocalDateTime refCreationDate;
	private LocalDateTime refDeletionDate;
	private Double price;
	private Integer quantity;
	
	@OneToMany(mappedBy = "product")
	private Set<Stock> stocks;
	
	@ManyToOne
	@JoinColumn(name = "product_type_name")
	private ProductType productType;
	
	@OneToMany(mappedBy = "product")
	private Set<Article> articles;
	
	@ManyToMany(mappedBy = "products")
	private Set<Photo> photos;

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", reference=" + reference + ", refCreationDate="
				+ refCreationDate + ", refDeletionDate=" + refDeletionDate + ", price=" + price + ", quantity="
				+ quantity + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result + ((refCreationDate == null) ? 0 : refCreationDate.hashCode());
		result = prime * result + ((refDeletionDate == null) ? 0 : refDeletionDate.hashCode());
		result = prime * result + ((reference == null) ? 0 : reference.hashCode());
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
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		if (refCreationDate == null) {
			if (other.refCreationDate != null)
				return false;
		} else if (!refCreationDate.equals(other.refCreationDate))
			return false;
		if (refDeletionDate == null) {
			if (other.refDeletionDate != null)
				return false;
		} else if (!refDeletionDate.equals(other.refDeletionDate))
			return false;
		if (reference == null) {
			if (other.reference != null)
				return false;
		} else if (!reference.equals(other.reference))
			return false;
		return true;
	}

}
