package com.planner.project.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    public List<Event> findAllByOrderByDateAscTimeAsc();

//
//    @Query("select e from Event p where year(e.date) = ?1 and month(e.date) = ?2")
    public List<Event> findAllByDateBetween(LocalDate date1, LocalDate date2);

}