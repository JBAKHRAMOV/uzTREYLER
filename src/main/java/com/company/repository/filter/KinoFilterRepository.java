package com.company.repository.filter;

import com.company.dto.kino.KinoSearchDTO;
import com.company.mapper.KinoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class KinoFilterRepository {

    private final EntityManager entityManager;


    public List<KinoMapper> filter(KinoSearchDTO dto) {

        StringBuilder sql = new StringBuilder(
                "select new com.company.mapper.KinoMapper (k.id as id, " +
                        "      k.createdDate as createdDate," +
                        "      k.name  as name," +
                        "      k.videoLink  as videoLink," +
                        "      k.previewAttachLink as previewAttachLink," +
                        "      k.country as country," +
                        "      k.translationLanguage  as translationLanguage," +
                        "      k.duration  as duration," +
                        "      k.type  as type," +
                        "      k.categoryId as categoryId," +
                        "      k.publishedDate as publishedDate )  " +
                        " from KinoEntity as k" +
                        " left join KinoJanrEntity j on j.kinoId = k.id" +
                        " where k.deletedDate is null ");

        Map<String, Object> params = new HashMap<>();

        if (Optional.ofNullable(dto.getName()).isPresent()) {
            sql.append("and k.name = :name ");
            params.put("name", dto.getName());
        }

        if (Optional.ofNullable(dto.getJanrId()).isPresent()) {
            sql.append(" and j.id = :janrId ");
            params.put("janrId", dto.getJanrId());
        }

        if (Optional.ofNullable(dto.getCategoryId()).isPresent()) {
            sql.append(" and  k.categoryId = :categoryId ");
            params.put("categoryId", dto.getCategoryId());
        }

        if (Optional.ofNullable(dto.getType()).isPresent()) {
            sql.append(" and k.type = :type  ");
            params.put("type", dto.getType());
        }

        if (Optional.ofNullable(dto.getYear()).isPresent()) {
            sql.append(" and k.year = :year ");
            params.put("year", dto.getYear());
        }


        Query query = entityManager.createQuery(sql.toString(), KinoMapper.class);

        params.forEach(query::setParameter);

        return query.getResultList();
    }
}
