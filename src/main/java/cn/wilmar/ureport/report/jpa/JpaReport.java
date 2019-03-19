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
 * 数据库存储报表对象
 * @author yinguowei 2017/11/9.
 */
@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class JpaReport {

    @Id @NonNull private String fileName;

    @CreatedDate @Column(nullable = false)
    private Date createdDate;

    /**
     * 内容字段，里面是 xml 格式报表文本
     */
    @NotNull @Lob private String fileContent;

    @LastModifiedDate @Column(nullable = false)
    private Instant lastModifiedDate = Instant.now();
}
