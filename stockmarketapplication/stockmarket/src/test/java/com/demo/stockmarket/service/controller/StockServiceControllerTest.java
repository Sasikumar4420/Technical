package com.demo.stockmarket.service.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.demo.stockmarket.controller.StockController;
import com.demo.stockmarket.dto.ApiResponse;
import com.demo.stockmarket.dto.StockResponseDTO;
import com.demo.stockmarket.service.StockService;
import com.demo.stockmarket.util.Responses;

@ExtendWith(SpringExtension.class)
class StockServiceControllerTest 
{
	
	@Mock
    private StockService stockService;

    @InjectMocks
    private StockController stockController;

    @Test
    void testGetStock_Success() {
        ApiResponse apiResponse = new ApiResponse(Responses.GET_CURRENT_PRICE_CODE, Responses.GET_CURRENT_PRICE_MESSAGE);
        when(stockService.getStocks(1L)).thenReturn(apiResponse);

        ResponseEntity<ApiResponse> responseEntity = stockController.getStock(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(apiResponse, responseEntity.getBody());
    }
    @Test
    void testGetHistoryStock_Success() {
    	StockResponseDTO stocks=new StockResponseDTO();
    	stocks.setCode(Responses.GET_HISTORY_PRICE_CODE);
    	stocks.setMessage(Responses.GET_HISTORY_PRICE_MESSAGE);
        when(stockService.getStockhistory(anyLong())).thenReturn(stocks);

        ResponseEntity<StockResponseDTO> responseEntity = stockController.getStocksHistory(1l);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        
    }



}
