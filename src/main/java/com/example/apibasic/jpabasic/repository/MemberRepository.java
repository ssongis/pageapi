package com.example.apibasic.jpabasic.repository;

import com.example.apibasic.jpabasic.entity.Gender;
import com.example.apibasic.jpabasic.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//JPA로 CRUD Operation을 하려면 JpaRepository 인터페이스 상속
//제너릭타입으로 첫번째로 CRUD할 엔티티클래스의 타입, 두번째로 해당 엔티티의 Id의 타입(기본키)
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    //쿼리 메서드 - 이름에 규칙 있음
    List<MemberEntity> findByGender(Gender gender);

    List<MemberEntity> findByAccountAndGender(String account, Gender gender);

    //쿼리 Like
    List<MemberEntity> findByNickNameContaining(String nickName);

    //JPQL 사용 - 이름 규칙 없음
    //select 별칭 from 엔티티클래스명 as 별칭 where 별칭.필드명
    //native-sql : select m.user_code from tbl_member as m
    //jpql : select m.userId from MemberEntity as m
    //계정명으로 회원 조회
    @Query("select m from MemberEntity as m where m.account=?1")
    MemberEntity getMemberByAccount(String account);

    // 닉네임과 성별 동시만족 조건으로 회원 조회
//    @Query("select m from MemberEntity m where m.nickName=?1 and m.gender=?2")
//    List<MemberEntity> getMembersByNickAndGender(String nickName, Gender gender);
    @Query("select m from MemberEntity m where m.nickName=:nick and m.gender=:gen")
    List<MemberEntity> getMembersByNickAndGender(String nick, Gender gen);

    // 검색
    @Query("select m from MemberEntity m where m.nickName like %:nick%")
    List<MemberEntity> getMembersByNickName(String nick);
}
