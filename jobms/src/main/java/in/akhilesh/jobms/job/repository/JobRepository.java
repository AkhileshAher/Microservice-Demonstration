package in.akhilesh.jobms.job.repository;


import in.akhilesh.jobms.job.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job,Long> {
}
