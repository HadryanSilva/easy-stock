package br.com.hadryan.stock.repository;

import br.com.hadryan.stock.model.StockOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockOrderRepository extends JpaRepository<StockOrder, Long> {

}
