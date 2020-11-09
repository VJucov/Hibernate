package entities;

import lombok.*;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "user_table")
@Setter
@Getter
@ToString
@NoArgsConstructor
@SQLDelete(sql = "UPDATE user_table SET firstName = 'deleted', lastName = 'deleted',email = 'deleted',userName = 'deleted',enabled = false WHERE id =?")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    private String firstName;

    private String lastName;

    private String email;

    @Column(unique = true)
    private String userName;

    private Date createdAt;

    private boolean enabled;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_role",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name ="role_id")}
    )
    @ToString.Exclude
    private Set<Role> roles;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private Set<Task> tasks;

    @JoinColumn(name = "discipline_id", referencedColumnName = "id")
    @Enumerated(EnumType.STRING)
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Discipline discipline;


    public User(String firstName, String lastName, String email, String userName, Date createdAt, boolean enabled, Set<Role> roles, Set<Task> tasks, Discipline discipline) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userName = userName;
        this.createdAt = createdAt;
        this.enabled = enabled;
        this.roles = roles;
        this.tasks = tasks;
        this.discipline = discipline;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }
}
