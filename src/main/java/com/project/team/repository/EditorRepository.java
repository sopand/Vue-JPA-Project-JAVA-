package com.project.team.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.team.entity.EditorUpload;

public interface EditorRepository extends JpaRepository<EditorUpload, Long>{

}
