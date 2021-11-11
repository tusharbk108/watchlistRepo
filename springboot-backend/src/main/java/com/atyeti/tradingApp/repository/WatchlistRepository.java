package com.atyeti.tradingApp.repository;

import com.atyeti.tradingApp.models.WatchListModel;
import org.springframework.data.repository.CrudRepository;

public interface WatchlistRepository extends CrudRepository<WatchListModel,Integer> {
}
