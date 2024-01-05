package com.example.vaccination.repository;

import com.example.vaccination.model.entity.InjectionResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface InjectionResultRepository extends JpaRepository<InjectionResult,Integer> {

    InjectionResult findByInjectionResultID(int id);
    List<InjectionResult> findAllByOrderByInjectionResultIDDesc();

    @Query("SELECT ir FROM InjectionResult ir " +
            "INNER JOIN ir.vaccine_r v " +
            "INNER JOIN v.vaccineType vt " +
            "WHERE (:startDate IS NULL OR ir.injectionDate >= :startDate) " +
            "AND (:endDate IS NULL OR ir.injectionDate <= :endDate)"+
            "AND (:vaccineTypeName IS NULL OR vt.vaccineTypeName Like %:vaccineTypeName%) " +
            "AND (:prevention IS NULL OR ir.prevention Like %:prevention%)")
    List<InjectionResult> findResults(    @Param("startDate") Date startDate,
                                          @Param("endDate") Date endDate,
                                          @Param("vaccineTypeName") String vaccineTypeName,
                                          @Param("prevention") String prevention);

    //Find data to generate Chart by injectionDate
    @Query(value="SELECT MONTHNAME(ir.injectionDate), SUM(ir.numberOfInjection) AS totalNumberOfInjection FROM InjectionResult ir " +
            "WHERE YEAR(ir.injectionDate) = :yearSelect GROUP BY MONTHNAME(ir.injectionDate)")
    List<String> CountInjectionResult(String yearSelect);

    @Query(value="SELECT MONTHNAME(ir.injectionDate), SUM(ir.numberOfInjection) AS totalNumberOfInjection FROM InjectionResult ir " +
            "WHERE YEAR(ir.injectionDate) = YEAR(CURDATE()) GROUP BY MONTHNAME(ir.injectionDate)")
    List<String> CountInjectionResultByYear();

}
