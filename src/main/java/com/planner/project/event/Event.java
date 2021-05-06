package com.planner.project.event;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity(name = "Event")
@Table
public class Event {
    @Id
    @SequenceGenerator(name = "event_sequence", sequenceName = "event_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_sequence")
    private Long id;
    private String type;
    private  String privacy;
    private String description;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate date;

    @DateTimeFormat(pattern="HH:mm")
    private LocalTime time;

    public Event() {
    }

    public Event(String type, String privacy, String description, LocalDate date, LocalTime time) {
        this.type = type;
        this.privacy = privacy;
        this.description = description;
        this.date = date;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {return time;}

    public void setTime(LocalTime time) {this.time = time;}

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", privacy='" + privacy + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", time-" + time +
                '}';
    }
}
