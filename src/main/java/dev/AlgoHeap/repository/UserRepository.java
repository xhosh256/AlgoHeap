package dev.AlgoHeap.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.AlgoHeap.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByNameAndPassword(String name, String password);
}