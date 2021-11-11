package com.atyeti.tradingApp.repository;

import com.atyeti.tradingApp.models.HistoryModel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<HistoryModel, Integer> {
}
