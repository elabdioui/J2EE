package com.example.tp6.Entitie;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppRole {
    @Id@GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;
    private String roleName;
    private String description;
    @ManyToMany(mappedBy = "roles")
    private List<AppUser> appUser;

}
