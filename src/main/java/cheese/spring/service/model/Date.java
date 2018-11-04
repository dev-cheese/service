package cheese.spring.service.model;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
@Getter
public class Date {

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Column(name = "update_at")
    private LocalDateTime updateAt;

    private Date() {
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }

    public static Date now() {
        return new Date();
    }

}
