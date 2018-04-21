package cn.wilmar.ureport.report.jpa;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Date;

/**
 * @author yinguowei 2017/11/9.
 */
@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EntityListeners(AuditingEntityListener.class)
class JpaReport {

    @Id @NonNull private String fileName;

    @CreatedDate @Column(nullable = false)
    private Date createdDate;

    @NotNull @Lob private String fileContent;

    @LastModifiedDate @Column(nullable = false)
    private Instant lastModifiedDate = Instant.now();
}
