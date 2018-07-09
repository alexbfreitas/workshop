package com.workshop.exemplo.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name="item_order")
public class OrderItems {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
		
	@ManyToOne
	@JoinColumn(name="id_order", referencedColumnName="id")
	@JsonBackReference
	private Order order;
	
	@ManyToOne
	@JoinColumn(name="id_product")
	private Product product;
	
	private BigDecimal price;
	private Integer quantity;
	private BigDecimal total;
	
	
	
}

//package com.workshop.exemplo.model;
//
//import java.math.BigDecimal;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonManagedReference;
//
//import lombok.AllArgsConstructor;
//import lombok.EqualsAndHashCode;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.ToString;
//
//@Getter
//@Setter
//@ToString
//@AllArgsConstructor
//@NoArgsConstructor
//@EqualsAndHashCode
//@Entity
//@Table(name="item_order")
//public class OrderItems {
//	
//	@Id
//	@GeneratedValue( strategy = GenerationType.IDENTITY)
//	private Long id;
//	
//	@ManyToOne
//	@JoinColumn(name="id_order", referencedColumnName="id" )
//	@JsonBackReference
//	private Order order;
//	
//	@ManyToOne
//	@JoinColumn(name="id_product")
//	private Product product;
//	
//	private BigDecimal price;
//	private Integer quantity;
//	private BigDecimal total;
//	
//}