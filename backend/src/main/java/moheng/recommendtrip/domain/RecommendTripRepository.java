package moheng.recommendtrip.domain;

import moheng.keyword.dto.RecommendTripResponse;
import moheng.member.domain.Member;
import moheng.trip.domain.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface RecommendTripRepository extends JpaRepository<RecommendTrip, Long> {
    @Query("SELECT new moheng.keyword.dto.RecommendTripResponse(rt.trip.contentId, rt.visitedCount) FROM RecommendTrip rt WHERE rt.member.id = :memberId")
    List<RecommendTripResponse> findVisitedCountAndTripContentIdByMemberId(@Param("memberId") final Long memberId);

    List<RecommendTrip> findAllByMemberId(final Long memberId);

    List<RecommendTrip> findTop10ByMember(Member member);

    boolean existsByMemberAndTrip(final Member member, final Trip trip);

    void deleteByMemberAndRank(final Member member, final long rank);

    boolean existsByMemberAndRank(final Member member, final long rank);

    @Modifying
    @Transactional
    @Query("UPDATE RecommendTrip rt SET rt.rank = rt.rank - 1 WHERE rt IN :recommendTrips")
    void bulkDownRank(@Param("recommendTrips") final List<RecommendTrip> recommendTrips);
}
