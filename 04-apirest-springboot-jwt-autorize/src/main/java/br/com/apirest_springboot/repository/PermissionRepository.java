package br.com.apirest_springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.apirest_springboot.model.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
	
	List<Permission> findByDescription(String description);
}
