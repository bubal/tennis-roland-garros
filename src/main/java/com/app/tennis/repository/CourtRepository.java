package com.app.tennis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.tennis.data.Court;

public interface CourtRepository extends JpaRepository<Court, Integer> {

}
