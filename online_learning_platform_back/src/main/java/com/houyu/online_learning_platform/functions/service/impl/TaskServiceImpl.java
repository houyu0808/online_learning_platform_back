package com.houyu.online_learning_platform.functions.service.impl;

import com.houyu.online_learning_platform.back_stage_manage.dao.ClassRepository;
import com.houyu.online_learning_platform.back_stage_manage.dao.StudentRepository;
import com.houyu.online_learning_platform.back_stage_manage.entity.Class;
import com.houyu.online_learning_platform.back_stage_manage.entity.Student;
import com.houyu.online_learning_platform.functions.dao.TaskClassRepository;
import com.houyu.online_learning_platform.functions.dao.TaskRepository;
import com.houyu.online_learning_platform.functions.entity.Task;
import com.houyu.online_learning_platform.functions.entity.TaskClass;
import com.houyu.online_learning_platform.functions.service.TaskService;
import com.houyu.online_learning_platform.functions.vo.TaskVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {
    @Value("${oss.local.upload-file-path}")
    private String uploadFilePath;
    @Value("${path.uploadPath}")
    private String uploadPath;

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ClassRepository classRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TaskClassRepository taskClassRepository;

    @Override
    public void addTask(MultipartFile file, TaskVO taskVO) {
        String fileName = file.getOriginalFilename();
        String newName = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."), fileName.length());
        File dest = new File(uploadFilePath + "/taskFile/" + newName);
        taskVO.setFileUrl(uploadPath + "/taskFile/" + newName);
        Task task = new Task();
        Class classx = classRepository.findByClassCode(taskVO.getBelongClassCode());
        taskVO.setBelongClassName(classx.getClassName());
        BeanUtils.copyProperties(taskVO,task);
        try{
            file.transferTo(dest);
            taskRepository.save(task);
        }catch (IOException e){
            throw new Error("文件存储出错！请联系管理员！");
        }
        List<Student> studentList = studentRepository.findByAffiliatedClassCode(taskVO.getBelongClassCode());
        Task task1 = taskRepository.findByName(taskVO.getName());
        for(Student student:studentList){
            TaskClass taskClass = new TaskClass();
            taskClass.setStuName(student.getUsername());
            taskClass.setStuNumber(student.getStuNumber());
            taskClass.setBelongTaskId(task1.getId());
            taskClass.setBelongClassCode(taskVO.getBelongClassCode());
            taskClass.setBelongClassName(classx.getClassName());
            taskClass.setStatus("未完成");
            taskClassRepository.save(taskClass);
        }
    }

    @Override
    public void deleteTask(Integer id) {
        Task task = taskRepository.findById(id).get();
        taskRepository.delete(task);
        try{
            String taskFile = task.getFileUrl().replace(uploadFilePath,uploadPath);
            File exist = new File(taskFile);
            exist.delete();
        }catch (Exception e){
            throw new Error("源文件不存在");
        }
        List<TaskClass> taskClassList = taskClassRepository.findAllByBelongTaskId(id);
        taskClassRepository.deleteAll(taskClassList);
    }

    @Override
    public Page<Task> getTeacherTaskPage(String teacherCode, Pageable pageable) {
        return taskRepository.findByPublisherCode(teacherCode,pageable);
    }

    @Override
    public Page<TaskClass> getTaskClassPage(Integer taskId,Pageable pageable) {
        return taskClassRepository.findByBelongTaskId(taskId,pageable);
    }

    @Override
    public Page<Task> getStudentTask(BigInteger classCode, Pageable pageable) {
        return taskRepository.findAllByBelongClassCode(classCode,pageable);
    }

    @Override
    public void uploadFile(MultipartFile file, String stuNumber, Integer taskId,String uploadTime) {
        String fileName = file.getOriginalFilename();
        String newName = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."), fileName.length());
        File dest = new File(uploadFilePath + "/studentFile/" + newName);
        TaskClass taskClass = taskClassRepository.findByBelongTaskIdAndStuNumber(taskId,stuNumber);
        taskClass.setFileUrl(uploadPath + "/studentFile/" + newName);
        taskClass.setUploadTime(uploadTime);
        try{
            file.transferTo(dest);
            taskClassRepository.save(taskClass);
        }catch (IOException e){
            throw new Error("文件存储出错！请联系管理员！");
        }
    }


}
