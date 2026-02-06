package com.advik.fantasyt20.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayersPointsRepository extends JpaRepository<PlayersPoints, Long>{
} 
