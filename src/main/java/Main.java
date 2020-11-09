import dao.DaoImplementation;
import entities.Discipline;
import entities.Role;
import entities.Task;
import entities.User;
import enums.Disciplines;
import enums.Status;

import java.util.*;

public class Main {

    private static DaoImplementation dao = new DaoImplementation();

    public static void main(String[] args) {
        List<Role> roles = rolesFactory();
        dao.toDatabase(roles);
        List<Task> tasks = tasksFactory();
        dao.toDatabase(tasks);
        List<Discipline> disciplines = disciplineFactory();
        dao.toDatabase(disciplines);
        List<User> users = usersFactory(roles, tasks, disciplines);
        dao.toDatabase(users);

        printSeparator();
        Discipline discipline1 = disciplines.get(0);
        discipline1.setHeadOfDiscipline(users.get(4));
        dao.updateEntity(new ArrayList<Discipline>() {{
            add(discipline1);
        }});

        discipline1.setMembers(new HashSet<>(Arrays.asList(users.get(0), users.get(1), users.get(2), users.get(4))));
        dao.updateEntity(new ArrayList<Discipline>() {{
            add(discipline1);
        }});

        Discipline discipline2 = disciplines.get(1);
        discipline2.setMembers(new HashSet<>(Arrays.asList(users.get(3))));
        dao.updateEntity(new ArrayList<Discipline>() {{
            add(discipline2);
        }});

        Task task1 = tasks.get(0);
        task1.setUser(users.get(0));
        dao.updateEntity(new ArrayList<Task>() {{
            add(task1);
        }});

        Task task2 = tasks.get(1);
        task2.setUser(users.get(3));
        dao.updateEntity(new ArrayList<Task>() {{
            add(task2);
        }});

        Task task3 = tasks.get(2);
        task3.setUser(users.get(2));
        dao.updateEntity(new ArrayList<Task>() {{
            add(task3);
        }});

        Task task4 = tasks.get(3);
        task4.setUser(users.get(1));
        dao.updateEntity(new ArrayList<Task>() {{
            add(task4);
        }});

        Task task5 = tasks.get(4);
        task5.setUser(users.get(2));
        dao.updateEntity(new ArrayList<Task>() {{
            add(task5);
        }});



        printSeparator();
        List<User> list= dao.getUsersByRoleName("intern");
        System.out.println("Users that are interns: ");
        for (User e : list){
            System.out.println(e);
        }

        printSeparator();
        List<User> listAM= dao.getUsersByDisciplineName(Disciplines.AM);
        System.out.println("Users from AM discipline: ");
        for (User e : listAM){
            System.out.println(e);
        }

        printSeparator();
        User user1 = users.get(0);
        user1.setTasks(new HashSet<>(Arrays.asList(tasks.get(4), tasks.get(0))));
        dao.updateEntity(new ArrayList<User>() {{
            add(user1);
        }});

        printSeparator();
        List<User> toDoTaskList= dao.getUsersByTaskStatus(Status.TODO);
        System.out.println("Users that have tasks in TODO status: ");
        for (User e : toDoTaskList){
            System.out.println(e);
        }

        printSeparator();
        discipline2.setHeadOfDiscipline(users.get(3));
        dao.updateEntity(new ArrayList<Discipline>() {{
            add(discipline2);
        }});

        printSeparator();
        List<Discipline> disciplineList= dao.getDisciplineByNumberOfUsers(2);
        System.out.println("Disciplines that have no more than 2 users: ");
        for (Discipline e : disciplineList){
            System.out.println(e);
        }

        printSeparator();
        dao.deleteUser(users.get(5));

    }

    public static List<Role> rolesFactory() {
        List<Role> list = new ArrayList<>();
        list.add(new Role("intern"));
        list.add(new Role("mentor"));
        list.add(new Role("trainer"));
        list.add(new Role("java engineer"));
        list.add(new Role("AM engineer"));
        return list;
    }

    public static List<Task> tasksFactory() {
        List<Task> list = new ArrayList<>();
        list.add(new Task("CSS task", "create a family tree",  new Date(2020,10,25),
                new Date(2020,10,28), Status.DONE));
        list.add(new Task("HTML task", "create a post card", new Date(2020,10,27),
                new Date(2020,10,31), Status.INREVIEW));
        list.add(new Task("Java task", "STREAM API", new Date(2020,10,25),
                new Date(2020,10,31), Status.PROGRESS));
        list.add(new Task("SQL task", "Views, functions and procedures", new Date(2020,10,25),
                new Date(2020,10,25), Status.DONE));
        list.add(new Task("Spring task", "Spring Security", new Date(2020,10,25),
                new Date(2020,10,25), Status.TODO));
        return list;
    }

    public static List<User> usersFactory(List<Role> roles, List<Task> tasks, List<Discipline> discipline){
        List<User> list = new ArrayList<>();
        list.add(new User("Valeria", "Jucov", "valeria.jucov@endava.com", "VJucov", new Date(2020,10, 1),
                true, new HashSet<>(Arrays.asList(roles.get(0),roles.get(3))), new HashSet<>(),
                discipline.get(0)));
        list.add(new User("Dan", "Velescu", "dan.velescu@endava.com", "DVelescu", new Date(2020,10, 1),
                true, new HashSet<>(Arrays.asList(roles.get(0),roles.get(3))), new HashSet<>(),
                discipline.get(0)));
        list.add(new User("Dmitri", "Sprinceac", "dmitri.sprinceac@endava.com", "DSprinceac", new Date(2020,10, 1),
                true, new HashSet<>(Arrays.asList(roles.get(0),roles.get(3))), new HashSet<>(),
                discipline.get(0)));
        list.add(new User("Ana", "Catarev", "ana.catarev@endava.com", "ACatarev", new Date(2019,10, 1),
                true, new HashSet<>(Arrays.asList(roles.get(2),roles.get(4))), new HashSet<>(),
                discipline.get(1)));
        list.add(new User("Nicolae", "Stropsa", "nicolae.stropsa@endava.com", "NStropsa", new Date(2017,10, 1),
                true, new HashSet<>(Arrays.asList(roles.get(1),roles.get(4))), new HashSet<>(),
                discipline.get(0)));
        list.add(new User("Sorin", "Gorea", "sorin.gorea@endava.com", "SGorea", new Date(2017,10, 1),
                true, new HashSet<>(Arrays.asList(roles.get(1),roles.get(3))), new HashSet<>(),
                discipline.get(0)));
        return list;
    }

    public static List<Discipline> disciplineFactory() {
        List<Discipline> list = new ArrayList<>();
        list.add(new Discipline(Disciplines.AM, new HashSet<>()));
        list.add(new Discipline(Disciplines.DEV, new HashSet<>()));

        return list;
    }

    public static void printSeparator(){
        System.out.println("----------------------------------------------------------------");
    }
 }
