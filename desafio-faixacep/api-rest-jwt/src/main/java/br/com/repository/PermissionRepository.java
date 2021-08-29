package br.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.model.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long>{

}
