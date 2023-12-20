package com.mia.xrs.repository;

import com.mia.xrs.entity.Letter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.Optional;

public interface LetterRepository extends JpaRepository<Letter,Integer> {

    @Query("SELECT COALESCE(MAX(l.uniqueId), 0) FROM Letter l")
    Integer findByMaxUniqueId();

    Page<Letter> findByStatus(Boolean status, Pageable pageable);

    Optional<Letter> findByIdAndStatus(Integer id, Boolean status);

    @Query("SELECT l FROM Letter l WHERE l.letterNo = :letterNo AND l.status = :status")
    Letter findByLetterNoAndStatus(@Param("letterNo") Integer letterNo,
                                   @Param("status") Boolean status);

    @Query("SELECT l FROM Letter l WHERE l.date = :date AND l.status = :status")
    Page<Letter> findByDateAndStatus(@Param("date") Date date,
                                     @Param("status") Boolean status, Pageable pageable);

    @Query("SELECT l FROM Letter l " +
            "WHERE l.fromDepartment.name = :name " +
            "AND l.status = :status")
    Page<Letter> findByFromDepartmentAndStatus(@Param("name") String name,
                                               @Param("status") Boolean status, Pageable pageable);

    @Query("SELECT l FROM Letter l " +
            "WHERE l.toDepartment.name = :name " +
            "AND l.status = :status")
    Page<Letter> findByToDepartmentAndStatus(@Param("name") String name,
                                             @Param("status") Boolean status, Pageable pageable);

    @Query("SELECT l FROM Letter l WHERE lower(l.createdBy.userSpecification.lastName) LIKE lower(concat('%', :createdBy, '%')) " +
            "AND l.status = :status")
    Page<Letter> findByCreatedByAndStatus(@Param("createdBy") String createdBy,
                                          @Param("status") Boolean status, Pageable pageable);
}