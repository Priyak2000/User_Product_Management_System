package com.enitity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {


@Id	
@GeneratedValue(strategy = GenerationType.IDENTITY)

private Integer prodId;
private String prodName;
private String prodPrice;
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "user_id")
private User user;
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
public Integer getProdId() {
	return prodId;
}
public void setProdId(Integer prodId) {
	this.prodId = prodId;
}
public String getProdName() {
	return prodName;
}
public void setProdName(String prodName) {
	this.prodName = prodName;
}
public String getProdPrice() {
	return prodPrice;
}
public void setProdPrice(String prodPrice) {
	this.prodPrice = prodPrice;
}

}
