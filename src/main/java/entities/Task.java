package entities;

import enums.Status;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    private String name;

    private String description;

    private Date startDate;

    private Date endDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    public Task(String name, String description, Date startDate, Date endDate, Status status) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
