package in.akhilesh.companyms.company.repository;


import in.akhilesh.companyms.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Long> {
}
