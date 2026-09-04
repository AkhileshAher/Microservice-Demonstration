package in.akhilesh.jobms.job.service;

import in.akhilesh.jobms.job.entity.Job;
import in.akhilesh.jobms.job.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public String create(Job job) {
        jobRepository.save(job);
        return "Job added Successfully";
    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJobById(Long id) {
        jobRepository.deleteById(id);
        return true;

    }

    @Override
    public boolean updateJob(Long id,Job updatedJob) {
        Job job = jobRepository.findById(id).orElse(null);
        if(job==null)
            return false;
        job.setTitle(updatedJob.getTitle());
        job.setDescription(updatedJob.getDescription());
        job.setLocation(updatedJob.getLocation());
        job.setMinSalary(updatedJob.getMinSalary());
        job.setMaxSalary(updatedJob.getMaxSalary());
        jobRepository.save(updatedJob);
        return true;
    }

}
