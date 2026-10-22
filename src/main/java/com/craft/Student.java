package com.craft;

import jakarta.persistence.*;
import lombok.Data;
import jakarta.persistence.*;
import java.util.List;
@Data
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String tech;
}
