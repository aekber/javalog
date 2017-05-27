package org.ekber.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ekber.dao.interfaces.ITagDao;
import org.ekber.domain.Article;
import org.ekber.domain.Tag;
import org.ekber.domain.TagDTO;
import org.ekber.service.interfaces.ITagService;
import org.springframework.transaction.annotation.Transactional;


public class TagServiceImpl implements ITagService {

	private ITagDao tagDao;

	public void setTagDao(ITagDao tagDao) {
		this.tagDao = tagDao;
	}

	@Override
	@Transactional
	public List<TagDTO> getTags() {
		List<Tag> list = tagDao.getTags();
		List<TagDTO> dtoList = new ArrayList<TagDTO>();
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		for(Tag tag : list){
			if(map.containsKey(tag.getTagName())){
				map.put(tag.getTagName(), map.get(tag.getTagName()) + 1);
			} else{
				map.put(tag.getTagName(), 1);
			}
		}
		
		for(Map.Entry<String, Integer> entry : map.entrySet()){
			TagDTO dto = new TagDTO();
			dto.setTagName(entry.getKey());
			dto.setTagFrequency(entry.getValue() * 10);
			dtoList.add(dto);
		}
		
		return dtoList;
	}

	@Override
	@Transactional
	public List<Article> getArticlesByTag(String tagName) {
		return tagDao.findByTag(tagName);
	}
	
}
