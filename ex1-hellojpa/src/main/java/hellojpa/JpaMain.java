package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member = new Member();
            member.setId(1L);
            member.setName("A");
            member.setRoleType(RoleType.USER);

            em.persist(member);

            /* detach
            실행 flow
            1. 영속성 컨텍스트가 1차 캐시에 150L member를 캐싱한다.
            2. 영속성 컨텍스트가 관리하는 member객체의 이름을 변경한다.
            3. 영속상태의 member를 준영속 상태로 만든다.
            4. 그 결과, select query만 날라간다. detach가 없으면 update쿼리 까지 날라감

            Member member = em.find(Member.class, 150L);
            member.setName("AAAA");

            em.detach(member);
            System.out.println("========");
            */
            /* flush
            Member member1 = new Member(200L, "member200");
            em.persist(member1);

            em.flush();
            System.out.println("========");
             */

            /*
            // 비영속 상태
            Member member = new Member();
            member.setId(101L);
            member.setName("HelloJPA");

            // 영속 상태
            System.out.println("=== BEFORE ===");
            em.persist(member);
            System.out.println("=== AFTER ===");

            Member findMember = em.find(Member.class, 101L);
            System.out.println("findMember.id = " + findMember.getId());
            System.out.println("findMember.name = " + findMember.getName());
            */
            /* 리스트 조회
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .getResultList();

            for (Member member : result) {
                System.out.println("member.name = " + member.getName());
            }
            */

            /* 수정
            Member findMember = em.find(Member.class, 1L);
            findMember.setName("helloJPA");
             */

            /* 조회
            Member findMember = em.find(Member.class, 1L);
            System.out.println("findMember.id = " + findMember.getId());
            System.out.println("findMember.name = " + findMember.getName());
            */

            /* 저장
            Member member = new Member();
            member.setId(2L);
            member.setName("helloA");
            em.persist(member);
             */

            tx.commit();
        } catch(Exception e) {
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }

    }
}
