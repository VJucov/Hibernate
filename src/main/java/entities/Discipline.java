package entities;

import enums.Disciplines;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Discipline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Enumerated(EnumType.STRING)
    private Disciplines name;

    @OneToMany(mappedBy = "discipline", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<User> members;

    @OneToOne
    @ToString.Exclude
    private User headOfDiscipline;

    public Discipline(Disciplines name, Set<User> members) {
        this.name = name;
        this.members = members;
    }

    public User getHeadOfDiscipline() {
        return headOfDiscipline;
    }

    public void setHeadOfDiscipline(User headOfDiscipline) {
        this.headOfDiscipline = headOfDiscipline;
    }

    public Set<User> getMembers() {
        return members;
    }

    public void setMembers(Set<User> members) {
        this.members = members;
    }
}
