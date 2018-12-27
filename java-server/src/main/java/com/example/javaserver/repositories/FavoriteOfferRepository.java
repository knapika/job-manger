package com.example.javaserver.repositories;

import com.example.javaserver.entities.FavoriteOffer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface FavoriteOfferRepository extends CrudRepository<FavoriteOffer, Long> {
    List<FavoriteOffer> findByUserID(Integer userID);

    @Transactional
    Long deleteByUserIDAndOfferID(@Param("userID") Integer userID, @Param("offerID") Integer offerID);
}
