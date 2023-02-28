package com.xprod.spring.xprod.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table(name="PRODUIT")
public class XprodImages {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IMAGE_ID")
	private Long idImage;
	@Column(name = "DESCRIPTIF_IMAGE")
	private String descriptifImage;
	@Column(name = "URL_IMAGE")
	private String url_Image;
	
	public Long getIdImage() {
		return idImage;
	}
	public String getDescriptifImage() {
		return descriptifImage;
	}
	public String getUrl_Image() {
		return url_Image;
	}
	public void setIdImage(Long idImageLong) {
		this.idImage = idImageLong;
	}
	public void setDescriptifImage(String descriptifImage) {
		this.descriptifImage = descriptifImage;
	}
	public void setUrl_Image(String url_Image) {
		this.url_Image = url_Image;
	}
	public XprodImages(Long idImage, String descriptifImage, String url_Image) {
		super();
		this.idImage = idImage;
		this.descriptifImage = descriptifImage;
		this.url_Image = url_Image;
	}
	public XprodImages() {
		super();
	}
	
	
	

}
