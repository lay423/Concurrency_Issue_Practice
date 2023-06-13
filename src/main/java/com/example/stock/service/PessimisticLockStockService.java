package com.example.stock.service;

import com.example.stock.domain.Stock;
import com.example.stock.repository.StockRepository;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class PessimisticLockStockService {

  private StockRepository stockRepository;

  public PessimisticLockStockService(StockRepository stockRepository) {
    this.stockRepository = stockRepository;
  }

  @Transactional
  public void decrease(Long id, Long quantity) {
    Stock stock = stockRepository.findByIdWithPessimisticLock(id);
    stock.decrease(quantity);
    stockRepository.saveAndFlush(stock);
  }

}
