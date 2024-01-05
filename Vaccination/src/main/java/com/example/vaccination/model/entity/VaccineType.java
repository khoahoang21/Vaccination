package com.example.vaccination.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vaccine_types")
public class VaccineType {

    @Id
    @Column(name = "vaccine_type_id", length = 36)
    @Pattern(regexp = "^VT[0-9]{5}$", message = "The Form Of Vaccine Type Code: VTxxxxx")
    private String vaccineTypeID;

    @Column(name = "description", length = 200)
    private String description;

    @Column(name = "vaccine_type_name", length = 50)
    @NotEmpty(message = "Vaccine Type Name is required")
    private String vaccineTypeName;

    @Column(name = "status")
    @NotNull
    private boolean status = true ;

    @Column(name = "image", length = Integer.MAX_VALUE)
    private String image;

    @OneToMany(mappedBy = "vaccineType", cascade = CascadeType.ALL)
    private List<Vaccine> vaccineList;

}
