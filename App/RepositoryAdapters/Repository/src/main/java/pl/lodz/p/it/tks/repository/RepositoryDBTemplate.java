package pl.lodz.p.it.tks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepositoryDBTemplate<T> extends JpaRepository<T, String> {
}
