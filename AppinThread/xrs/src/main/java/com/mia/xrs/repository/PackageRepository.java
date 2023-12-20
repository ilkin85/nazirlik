package com.mia.xrs.repository;

import com.mia.xrs.entity.Letter;
import com.mia.xrs.entity.Package;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.Optional;

public interface PackageRepository extends JpaRepository<Package,Integer> {

    @Query("SELECT COALESCE(MAX(p.uniqueId), 0) FROM Package p")
    Integer findByMaxUniqueId();

    Page<Package> findByStatus(Boolean status, Pageable pageable);

    Optional<Package> findByIdAndStatus(Integer id, Boolean status);

    @Query("SELECT p FROM Package p WHERE p.packageNo = :packageNo AND p.status = :status")
    Package findByPackageNoAndStatus(@Param("packageNo") Integer packageNo,
                                   @Param("status") Boolean status);

    @Query("SELECT p FROM Package p WHERE p.sentDate = :sentDate AND p.status = :status")
    Page<Package> findBySentDateAndStatus(@Param("sentDate") Date sentDate,
                                     @Param("status") Boolean status, Pageable pageable);

    @Query("SELECT p FROM Package p JOIN p.letters l " +
            "WHERE l.fromDepartment.name = :name " +
            "AND p.status = :status")
    Page<Package> findByFromDepartmentAndStatus(@Param("name") String name,
                                                @Param("status") Boolean status, Pageable pageable);


    @Query("SELECT p FROM Package p JOIN p.letters l " +
            "WHERE l.toDepartment.name = :name " +
            "AND p.status = :status")
    Page<Package> findByToDepartmentAndStatus(@Param("name") String name,
                                                @Param("status") Boolean status, Pageable pageable);


    @Query("SELECT p FROM Package p WHERE lower(p.createdBy.userSpecification.lastName) LIKE lower(concat('%', :createdBy, '%')) " +
            "AND p.status = :status")
    Page<Package> findByCreatedByAndStatus(@Param("createdBy") String createdBy,
                                          @Param("status") Boolean status, Pageable pageable);

    @Query("SELECT COUNT(l) FROM Package p JOIN p.letters l WHERE p.packageNo = :packageNo AND p.status = :status")
    Integer countByLetterAndPackageStatus(@Param("packageNo") Integer packageNo, @Param("status") Boolean status);

    @Query("SELECT COUNT(l) FROM Package p JOIN p.letters l WHERE p.packageNo = :packageNo AND l.status = :status")
    Integer countByLetterAndStatus(@Param("packageNo") Integer packageNo, @Param("status") Boolean status);
}
