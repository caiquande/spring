package com.galaxy.libra.dom.biz.repo;

import com.galaxy.libra.dom.biz.vo.orcl.ResumeVo;
import com.galaxy.libra.interfaces.dto.GetFour;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @author caesar
 * @title
 * @description
 * @package com.galaxy.libra.dom.repo
 * @date 2019-09-07
 * @time 20:15
 * @p_name bigdata-platform-etl
 */
@Repository
public interface ResumeRepo extends CrudRepository<ResumeVo, Integer> {
    @Query(value = "select POLNO,CONTNO,INSUREDNO,RISKCODE from LCPOL where ROWNUM<:rn", nativeQuery = true)
    ArrayList<GetFour> findBySpecialRownum(@Param("rn") int rownum);
}

