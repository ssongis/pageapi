package com.example.apibasic.jpabasic.repository;

import com.example.apibasic.jpabasic.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

//JPA로 CRUD Operation을 하려면 JpaRepository 인터페이스 상속
//제너릭타입으로 첫번째로 CRUD할 엔티티클래스의 타입, 두번째로 해당 엔티티의 Id의 타입(기본키)
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
}
