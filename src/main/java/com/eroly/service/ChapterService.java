package com.eroly.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.eroly.mapper.ChapterMapper;
import com.eroly.mapper.NodeMapper;

@Service("ChapterService")
public class ChapterService {
	private static Logger logger = LoggerFactory.getLogger(ChapterService.class);

	@Autowired(required=true)
	@Qualifier("ChapterMapper")
	private ChapterMapper chapterMapper;
	@Autowired(required=true)
	@Qualifier("NodeMapper")
	private NodeMapper nodeMapper;
	/**
	 * 查询课程章节详情
	 * @param courseId
	 * @return
	 */
    public Map<String, Object> findByCourseId(String courseId){
    	logger.info("查询某课程对应的所有章节：{} 课程ID"+courseId);
    	Map<String, Object> resultMap = new HashMap<String, Object>();
    	Map<String, Object> nodeMap = new HashMap<String, Object>();
    	List<Map<String, Object>> courseDetailList = chapterMapper.findByCourseId(courseId);
    	if(courseDetailList!=null && courseDetailList.size()>0) {
    		//章节列表
    		resultMap.put("courseDetailList", courseDetailList);
    		for (Map<String, Object> map : courseDetailList) {
				String chapterId =  map.get("chapterId").toString();
				//查询章节对应的课时列表
				List<Map<String, Object>> nodeList = nodeMapper.findNodeByChapter(chapterId);
				nodeMap.put(chapterId, nodeList);
			}
    		resultMap.put("nodeMap", nodeMap);
    	}
    	return resultMap;
    }
}
