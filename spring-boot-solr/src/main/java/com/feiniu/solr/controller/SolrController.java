package com.feiniu.solr.controller;

import org.apache.solr.client.solrj.SolrClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.feiniu.solr.model.CourseVideoSolr;
import com.feiniu.solr.repository.CourseVideoRepository;

@RestController
@RequestMapping("/solr")
public class SolrController {

	@Autowired
	private SolrClient client;

	@Autowired
	private CourseVideoRepository repository;

	/**
	 * 
	 * @param id
	 * @param song
	 * @param singer
	 * @param lyric
	 */
	@RequestMapping("/add")
	public String addIndex(@RequestParam Integer id, @RequestParam String song, @RequestParam String singer, @RequestParam String lyric) {
		CourseVideoSolr courseVideo = new CourseVideoSolr();
		courseVideo.setId(id);
		courseVideo.setSong(song);
		courseVideo.setSinger(singer);
		courseVideo.setLyric(lyric);
		repository.save(courseVideo);
		return "success";
	}

	/**
	 * 查询所有内容
	 */
	@RequestMapping("/findAll")
	public Iterable<CourseVideoSolr> findAll() {
		Iterable<CourseVideoSolr> list = repository.findAll();
		return list;
	}

	/**
	 * 根据歌曲、歌手、歌词搜索，并高亮歌词
	 * 
	 * @param lyric
	 * @param page 从0开始
	 * @param pageSize 
	 * @return
	 */
	@RequestMapping("/findByRemark")
	public HighlightPage<CourseVideoSolr> findByLyric(@RequestParam String lyric, @RequestParam int page, @RequestParam int pageSize) {
		Sort sort = new Sort(Sort.Direction.ASC, "id");
		HighlightPage<CourseVideoSolr> result = repository.findByRemark(lyric, new PageRequest(page, pageSize, sort));
		return result;
	}
}
