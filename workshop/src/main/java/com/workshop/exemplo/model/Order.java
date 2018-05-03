package com.workshop.exemplo.model;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.workshop.exemplo.converter.LocalDateTimeConverter;

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
@Table(name="orders")
public class Order  {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="order_date")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date date;
	
	@NotNull
	private String deliveryaddress;
	
	@NotNull
	private String contact;
	
	@NotNull
	private String status;
	
	@ManyToOne
	@JoinColumn(name="id_customer")
	@JsonManagedReference
	private Customer customer;
	
	private Double total;
	
	@Convert(converter = LocalDateTimeConverter.class)	
	@JsonFormat(pattern = "dd/MM/yyyy hh:mm")
	@Column(name = "last_update")
	private LocalDateTime lastUpdate;

}
