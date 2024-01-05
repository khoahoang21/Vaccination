package com.example.vaccination.repository;

import com.example.vaccination.model.entity.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface VaccineRepository extends JpaRepository<Vaccine, String> {
    List<Vaccine> findAll();
    Optional<Vaccine> findById(String vaccineID);
    Optional<Vaccine> findByVaccineName(String vaccineName);

    //Search all when user input all in search form
    @Query("SELECT v FROM Vaccine v JOIN v.vaccineType vt WHERE " +
            "(:from IS NULL OR Date(v.timeBeginNextInjection) >= :from) AND " +
            "(:to IS NULL OR Date(v.timeEndNextInjection) <= :to) AND " +
            "(:vaccineSelection IS NULL OR vt.vaccineTypeName LIKE %:vaccineSelection%) AND " +
            "(:origin IS NULL OR v.origin LIKE %:origin%)")
    List<Vaccine> findAllbyConditions(@Param("from") Date from,
                                      @Param("to") Date to,
                                      @Param("vaccineSelection") String vaccineSelection,
                                      @Param("origin") String origin);

    //Find Vaccine's data to generate Chart by Number_of_injection / Time_End
    @Query(value="SELECT MONTHNAME(v.time_end_next_injection), SUM(CAST(v.number_of_injection  AS signed)) AS totalNumberOfInjection FROM vaccine v " +
            "WHERE YEAR(v.time_end_next_injection) = :yearSelect GROUP BY MONTHNAME(v.time_end_next_injection);",
            nativeQuery=true)
    List<String> CountVaccine(String yearSelect);
}
