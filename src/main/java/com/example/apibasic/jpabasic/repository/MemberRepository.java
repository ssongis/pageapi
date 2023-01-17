package com.example.apibasic.jpabasic.repository;

import com.example.apibasic.jpabasic.entity.Gender;
import com.example.apibasic.jpabasic.entity.MemberEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// JPA로 CRUD Operation을 하려면 JpaRepository 인터페이스를 상속
// 제너릭타입으로 첫번째로 CRUD할 엔터티클래스의 타입, 두번째로 해당 엔터티의 Id의 타입
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    // 쿼리 메서드 사용
    List<MemberEntity> findByGender(Gender gender);

    List<MemberEntity> findByAccountAndGender(String account, Gender gender);

    List<MemberEntity> findByNickNameContaining(String nickName);

    // JPQL 사용
    // select 별칭 from 엔터티클래스명 as 별칭 where 별칭.필드명
    // native-sql :  select m.user_code  from tbl_member as m
    // jpql :  select m.userId  from MemberEntity as m

    // 계정명으로 회원 조회
//    @Query("select m from MemberEntity as m where m.account=?1")
    @Query("select m from MemberEntity as m where m.account=:acc")
    MemberEntity getMemberByAccount(@Param("acc") String acc);

    // 닉네임과 성별 동시만족 조건으로 회원 조회
//    @Query("select m from MemberEntity m where m.nickName=?1 and m.gender=?2")
    @Query("select m from MemberEntity m where m.nickName=:nick and m.gender=:gen")
    List<MemberEntity> getMembersByNickAndGender(@Param("nick") String nick,@Param("gen") Gender gen);

    @Query("select m from MemberEntity m where m.nickName like %:nick%")
    List<MemberEntity> getMembersByNickName(@Param("nick") String nick);


    @Modifying // 수정, 삭제할 때 붙이기
    @Query("delete from MemberEntity m where m.nickName=:nick")
    void deleteByNickName(@Param("nick") String nick);

    

}






