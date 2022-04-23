package com.dev.model;

import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Entity
@Data
@TypeDef(name = "json", typeClass = JsonType.class)
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;

    private String suite;

    private String city;

    private String zipcode;

    @Type(type = "json")
    @Column(name = "geo", columnDefinition = "json")
    private Location geo;

    @Data
    private static class Location {
        private Double lat;

        private Double lng;
    }
}
