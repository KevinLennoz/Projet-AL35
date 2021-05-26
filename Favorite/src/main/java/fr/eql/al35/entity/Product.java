package fr.eql.al35.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	private String description;
	private Double price;
	private Integer quantity;
	
	@OneToMany(mappedBy = "product")
	private Set<Stock> stocks;
	
	@ManyToOne
	@JoinColumn(name = "product_type_name")
	private ProductType productType;
	
	@OneToMany(mappedBy = "product")
	private Set<Article> articles;
	
	@ManyToMany(mappedBy = "products", fetch = FetchType.EAGER)
	private Set<Photo> photos;

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", reference=" + reference + ", refCreationDate="
				+ refCreationDate + ", refDeletionDate=" + refDeletionDate + ", description=" + description + ", price="
				+ price + ", quantity=" + quantity + ", productType=" + productType
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((articles == null) ? 0 : articles.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((photos == null) ? 0 : photos.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((productType == null) ? 0 : productType.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result + ((refCreationDate == null) ? 0 : refCreationDate.hashCode());
		result = prime * result + ((refDeletionDate == null) ? 0 : refDeletionDate.hashCode());
		result = prime * result + ((reference == null) ? 0 : reference.hashCode());
		result = prime * result + ((stocks == null) ? 0 : stocks.hashCode());
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
		if (articles == null) {
			if (other.articles != null)
				return false;
		} else if (!articles.equals(other.articles))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
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
		if (photos == null) {
			if (other.photos != null)
				return false;
		} else if (!photos.equals(other.photos))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (productType == null) {
			if (other.productType != null)
				return false;
		} else if (!productType.equals(other.productType))
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
		if (stocks == null) {
			if (other.stocks != null)
				return false;
		} else if (!stocks.equals(other.stocks))
			return false;
		return true;
	}

}
