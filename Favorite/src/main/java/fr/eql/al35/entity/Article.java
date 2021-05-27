package fr.eql.al35.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
public class Article implements Serializable {

	private static final long serialVersionUID = 1L;
	
	

	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Integer id;
	private Integer quantity;
	private Double price;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	@OneToMany(mappedBy = "article", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Custom> customs;
	
	@ManyToOne
	@JoinColumn(name = "size_label")
	private Size size;
	
	@ManyToOne
	@JoinColumn(name = "command_id")
	private Command command;

	@Override
	public String toString() {
		return "Article [id=" + id + ", quantity=" + quantity + ", price=" + price + ", size=" + size + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
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
		Article other = (Article) obj;
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
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		return true;
	}


	public Article(Integer quantity, Double price, Product product, Set<Custom> customs, Size size, Command command) {
		super();
		this.quantity = quantity;
		this.price = price;
		this.product = product;
		this.customs = customs;
		this.size = size;
		this.command = command;
	}

	
	
}
