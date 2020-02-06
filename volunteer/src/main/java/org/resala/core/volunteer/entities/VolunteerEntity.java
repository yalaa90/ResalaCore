package org.resala.core.volunteer.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Entity
@Getter
@Setter
public class VolunteerEntity {

    public VolunteerEntity() {
    }

    public VolunteerEntity(String name, String joinDate, String notes, String identificationNumber,
                           Boolean miniCamp, Boolean tshirt, GenderEnum gender, String phoneNumber, String birthDate,
                           Long networkTypeId, Long universitySpecializationId,
                           Long volunteerTypeId) {

        this.name = name;
        this.joinDate = joinDate;
        this.notes = notes;
        this.identificationNumber = identificationNumber;
        this.miniCamp = miniCamp;
        this.tshirt = tshirt;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.networkType = new NetworkTypeEntity();
        this.networkType.setId(networkTypeId);
        this.UniversitySpecialization = new UniversitySpecializationEntity();
        this.UniversitySpecialization.setId(universitySpecializationId);
        this.volunteerType = new VolunteerTypeEntity();
        this.volunteerType.setId(volunteerTypeId);
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String code;
    @NotEmpty(message = "Please enter Name")
    private String name;
    private String joinDate;
    private String notes;
    @Size(max = 14, min = 14, message = "{identification Number invalid}")
    @Column(unique = true)
    @NotEmpty(message = "Please enter identification Number")
    private String identificationNumber;
    private Boolean miniCamp;
    private Boolean tshirt;
    private GenderEnum gender;
    @Column(unique = true)
    @NotEmpty(message = "Please enter Phone Number")
    private String phoneNumber;
    private String birthDate;

    @ManyToOne
    @JoinColumn
    private NetworkTypeEntity networkType;
    @ManyToOne
    private UniversitySpecializationEntity UniversitySpecialization;
    @ManyToOne
    private VolunteerTypeEntity volunteerType;

    @ManyToOne
    private  RegionEntity regionEntity;

}