package in.akhilesh.jobms.job.service;

import in.akhilesh.jobms.job.entity.Job;

import java.util.List;

public interface JobService {
    List<Job> findAll();
    String create(Job job);
    Job getJobById(Long id);
    boolean deleteJobById(Long id);
    boolean updateJob(Long id,Job updatedjob);
}
