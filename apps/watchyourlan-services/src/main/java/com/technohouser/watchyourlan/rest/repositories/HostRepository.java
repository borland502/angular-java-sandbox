package com.technohouser.watchyourlan.rest.repositories;

import com.technohouser.watchyourlan.entities.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Repository interface for Host entities. Provides CRUD operations and custom query methods for
 * Host entities.
 */
@RepositoryRestResource(path = "hosts")
public interface HostRepository extends JpaRepository<Host, Integer> {}
