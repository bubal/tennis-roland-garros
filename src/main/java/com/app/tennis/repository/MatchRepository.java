package com.app.tennis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.tennis.data.Match;

public interface MatchRepository extends JpaRepository<Match, Integer> {

}
