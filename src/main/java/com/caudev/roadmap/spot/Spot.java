package com.caudev.roadmap.spot;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Spot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="spot_id")
    private Long id;

    private String name;

    private double location_x;

    private double location_y;

    private String detail;

    private Long phoneNum;

    private String Image;
}
