package br.com.meatapp.domain;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class OrderItem implements Serializable{
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@EmbeddedId
	private OrderItemPK id = new OrderItemPK();
	
	@NotNull
	@Column(name="quantity")
	private Integer quantity;
	
	@NotNull
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	@Column(name="price", columnDefinition = "DECIMAL(7,2) DEFAULT 0.00")
	private Double price;
	
	@ManyToOne
	@JoinColumn(name="menu_item_id")
	private MenuItem menuItem;
	
	public OrderItem() {
		
	}

	public OrderItem(OrderItemPK id, Integer quantity, Double price, MenuItem menuItem) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.price = price;
		this.menuItem = menuItem;
	}
	
	public double getSubTotal() {
		return price * quantity;
	}

	@JsonIgnore
	public Orders getOrders() {
		return id.getOrders();
	}
	
	public void setOrders(Orders orders) {
		id.setOrders(orders);
	}
	
	public Integer getOrderItemId() {
		return id.getOrderItemId();
	}
	
	public void setOrderItemId(Integer orderItemId) {
		this.id.setOrderItemId(orderItemId);
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public MenuItem getMenuItem() {
		return menuItem;
	}

	public void setMenuItem(MenuItem menuItem) {
		this.menuItem = menuItem;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		OrderItem other = (OrderItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
