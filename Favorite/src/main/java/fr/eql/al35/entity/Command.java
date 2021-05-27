package fr.eql.al35.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
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
public class Command implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Integer id;
	private String reference;
	private LocalDateTime creationDate;
	private LocalDateTime shippingDate;
	private LocalDateTime deliveryDate;
	private LocalDateTime cancelDate;
	private LocalDateTime returnDate;
	private LocalDateTime deliveryReturnDate;
	private Double taxInPrice;
	private Double taxOutPrice;
	
	@ManyToOne
	@JoinColumn(name = "status_id")
	private Status status;
	
	@ManyToOne
	@JoinColumn(name = "pay_mode_id")
	private PayMode payMode;
	
	@ManyToOne
	@JoinColumn(name = "vat_id")
	private Vat vat;
	
	@ManyToOne 
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne (cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="delivery_address_id")
	private Address deliveryAddress;
	
	@ManyToOne (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="facturation_address_id")
	private Address facturationAddress;
	
	@OneToMany(mappedBy = "command")
	private Set<Article> articles;

	@Override
	public String toString() {
		return "Command [id=" + id + ", reference=" + reference + ", creationDate=" + creationDate + ", shippingDate="
				+ shippingDate + ", deliveryDate=" + deliveryDate + ", cancelDate=" + cancelDate + ", returnDate="
				+ returnDate + ", deliveryReturnDate=" + deliveryReturnDate + ", taxInPrice=" + taxInPrice
				+ ", taxOutPrice=" + taxOutPrice + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cancelDate == null) ? 0 : cancelDate.hashCode());
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((deliveryAddress == null) ? 0 : deliveryAddress.hashCode());
		result = prime * result + ((deliveryDate == null) ? 0 : deliveryDate.hashCode());
		result = prime * result + ((deliveryReturnDate == null) ? 0 : deliveryReturnDate.hashCode());
		result = prime * result + ((facturationAddress == null) ? 0 : facturationAddress.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((reference == null) ? 0 : reference.hashCode());
		result = prime * result + ((returnDate == null) ? 0 : returnDate.hashCode());
		result = prime * result + ((shippingDate == null) ? 0 : shippingDate.hashCode());
		result = prime * result + ((taxInPrice == null) ? 0 : taxInPrice.hashCode());
		result = prime * result + ((taxOutPrice == null) ? 0 : taxOutPrice.hashCode());
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
		Command other = (Command) obj;
		if (cancelDate == null) {
			if (other.cancelDate != null)
				return false;
		} else if (!cancelDate.equals(other.cancelDate))
			return false;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (deliveryAddress == null) {
			if (other.deliveryAddress != null)
				return false;
		} else if (!deliveryAddress.equals(other.deliveryAddress))
			return false;
		if (deliveryDate == null) {
			if (other.deliveryDate != null)
				return false;
		} else if (!deliveryDate.equals(other.deliveryDate))
			return false;
		if (deliveryReturnDate == null) {
			if (other.deliveryReturnDate != null)
				return false;
		} else if (!deliveryReturnDate.equals(other.deliveryReturnDate))
			return false;
		if (facturationAddress == null) {
			if (other.facturationAddress != null)
				return false;
		} else if (!facturationAddress.equals(other.facturationAddress))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (reference == null) {
			if (other.reference != null)
				return false;
		} else if (!reference.equals(other.reference))
			return false;
		if (returnDate == null) {
			if (other.returnDate != null)
				return false;
		} else if (!returnDate.equals(other.returnDate))
			return false;
		if (shippingDate == null) {
			if (other.shippingDate != null)
				return false;
		} else if (!shippingDate.equals(other.shippingDate))
			return false;
		if (taxInPrice == null) {
			if (other.taxInPrice != null)
				return false;
		} else if (!taxInPrice.equals(other.taxInPrice))
			return false;
		if (taxOutPrice == null) {
			if (other.taxOutPrice != null)
				return false;
		} else if (!taxOutPrice.equals(other.taxOutPrice))
			return false;
		return true;
	}

	
	
}
