package com.advik.fantasyt20.Model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayersRepository extends JpaRepository<Players, Long>{
    List<Players> findBySquadId(Long squadId);
    // Players findByRole(String role);
}
