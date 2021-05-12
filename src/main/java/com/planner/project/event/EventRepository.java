package com.planner.project.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
     List<Event> findAllByOrderByDateAscTimeAsc();
     List<Event> findAllByDateBetweenOrderByDateAscTimeAsc(LocalDate date1, LocalDate date2);
     List<Event> findAllByDateOrderByDateAscTimeAsc(LocalDate date);
     List<Event> findAllByTypeOrderByDateAscTimeAsc(String type);
}