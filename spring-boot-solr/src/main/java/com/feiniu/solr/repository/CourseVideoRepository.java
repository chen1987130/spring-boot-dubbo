package com.feiniu.solr.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.data.solr.repository.Highlight;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

import com.feiniu.solr.model.CourseVideoSolr;

public interface CourseVideoRepository extends SolrCrudRepository<CourseVideoSolr, Integer> {

	/**
	 * ?0 第一个参数
	 * ?1 第二个参数
	 * fragsize 高亮文档内容大小
	 * 
	 * 
	 * @param remark
	 * @param pageable
	 * @return
	 */
	@Query(value = "lyric:?0 or remark:?0")
	@Highlight(prefix = "<font color='red'>", postfix = "</font>", fragsize = 50)
	HighlightPage<CourseVideoSolr> findByRemark(String remark, Pageable pageable);

}
