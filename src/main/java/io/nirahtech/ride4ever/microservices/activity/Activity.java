package io.nirahtech.ride4ever.microservices.activity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public final class Activity implements Serializable, Comparable<Activity> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activity_id")
    private int identifier;
    private Timestamp date;
    @Enumerated(EnumType.STRING)
    private EventType event;
    private String pseudo;
    private String detail = null;
    
    public int getIdentifier() {
        return identifier;
    }
    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }
    public Timestamp getDate() {
        return date;
    }
    public void setDate(Timestamp date) {
        this.date = date;
    }
    public EventType getEvent() {
        return event;
    }
    public void setEvent(EventType eventType) {
        this.event = eventType;
    }
    public String getPseudo() {
        return pseudo;
    }
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
    public String getDetail() {
        return detail;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public int compareTo(Activity o) {
        return 0;
    }
    
    public Activity() {

    }
    
    public Activity(Timestamp date, EventType event, String pseudo, String detail) {
        this.date = date;
        this.event = event;
        this.pseudo = pseudo;
        this.detail = detail;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + ((detail == null) ? 0 : detail.hashCode());
        result = prime * result + ((event == null) ? 0 : event.hashCode());
        result = prime * result + identifier;
        result = prime * result + ((pseudo == null) ? 0 : pseudo.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Activity other = (Activity) obj;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        if (detail == null) {
            if (other.detail != null)
                return false;
        } else if (!detail.equals(other.detail))
            return false;
        if (event != other.event)
            return false;
        if (identifier != other.identifier)
            return false;
        if (pseudo == null) {
            if (other.pseudo != null)
                return false;
        } else if (!pseudo.equals(other.pseudo))
            return false;
        return true;
    }

    
}
