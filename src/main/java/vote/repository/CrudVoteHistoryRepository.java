package vote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import vote.model.VoteHistory;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudVoteHistoryRepository extends JpaRepository<VoteHistory, Integer>{
    @Transactional
    @Modifying
    @Query("DELETE FROM VoteHistory h WHERE h.userId=:id AND h.date=:date")
    int delete(@Param("id") int id, @Param("date") LocalDate date);

    @Override
    @Transactional
    VoteHistory save(VoteHistory voteHistory);

    @Modifying
    @Query("SELECT h FROM VoteHistory h WHERE h.userId=:id ORDER BY h.date ASC")
    List<VoteHistory> findAll(@Param("id") int id);
}
