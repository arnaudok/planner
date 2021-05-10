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

    public List<Event> findAllByDateBetween(LocalDate date1, LocalDate date2);
    public List<Event> findAllByDate(LocalDate date);
    List<Event> findAllByType(String type);

}