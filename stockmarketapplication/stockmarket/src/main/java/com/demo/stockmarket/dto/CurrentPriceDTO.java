package com.demo.stockmarket.dto;

public class CurrentPriceDTO 
{
	private ApiResponse response;
	private Double currentPrice;
	
	public ApiResponse getResponse() {
		return response;
	}
	public void setResponse(ApiResponse response) {
		this.response = response;
	}
	public Double getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(Double currentPrice) {
		this.currentPrice = currentPrice;
	}
	public CurrentPriceDTO(ApiResponse response, Double currentPrice) {
		super();
		this.response = response;
		this.currentPrice = currentPrice;
	}

}
