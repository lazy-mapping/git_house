package com.lth.kaoqinsys.service.impl;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lth.kaoqinsys.dao.TeacherCourseMapper;
import com.lth.kaoqinsys.dao.TeacherMapper;
import com.lth.kaoqinsys.pojo.Teacher;
import com.lth.kaoqinsys.pojo.TeacherCourse;
import com.lth.kaoqinsys.service.ITeacherService;

@Service("teacherService")
public class TeacherService implements ITeacherService {
	@Resource
	private TeacherMapper teacherMapper;
	@Resource
	private TeacherCourseMapper teacherCourseMapper;

	public Teacher getTeacherByNum(String num) {
		return this.teacherMapper.selectTeacherByNum(num);
	}

	public int updateTeacher(Teacher teacher) {
		return this.teacherMapper.updateByPrimaryKey(teacher);
	}

	public ArrayList<TeacherCourse> getTeacherCoursesByTeacherId(int teacher_id) {
		return this.teacherCourseMapper.selectKeBiaoByTeacherId(teacher_id);
	}

	public ArrayList<Teacher> selectAllTeacherWithoutSelf(int selfid) {
		return this.teacherMapper.selectAllTeacherWithoutSelf(selfid);
	}

	public Teacher getTeacherById(int id) {
		return this.teacherMapper.selectByPrimaryKey(id);
	}

	public int insetTeacher(Teacher teacher) {
		return this.teacherMapper.insert(teacher);
	}

}
