import java.util.*;

public class Main {

    public static void main(String[] args) {
        Set<Task> tasks = TaskData.getTasks("all");
        sortAndPrint("All Tasks", tasks);

        Set<Task> annstTasks = TaskData.getTasks("Ann");
        sortAndPrint("Ann's Tasks", tasks);

        Set<Task> bobsTasks = TaskData.getTasks("Bob");
        Set<Task> carolsTasks = TaskData.getTasks("Carol");
        List<Set<Task>> sets = List.of(annstTasks, bobsTasks, carolsTasks);

        Set<Task> assignedTasks = getUnionList(sets);
        sortAndPrint("Assigned Tasks", assignedTasks);

        Set<Task> everyTask = getUnionList(List.of(tasks,assignedTasks));
        sortAndPrint("The True All Tasks", everyTask);

        Set<Task> missingTasks = getDifference(everyTask, tasks);
        sortAndPrint("Missing Tasks", missingTasks);

        Set<Task> unassignedTasks = getDifference(tasks, assignedTasks);
        sortAndPrint("Unassigned Tasks", unassignedTasks);

        Set<Task> overlap = getUnionList(List.of(
                getIntersect(annstTasks, bobsTasks),
                getIntersect(bobsTasks, carolsTasks),
                getIntersect(annstTasks, carolsTasks)));
        sortAndPrint("Assigned to Multiples", overlap);

        List<Task> overlapping = new ArrayList<>();
        for(Set<Task> set : sets){
            Set<Task> dupes = getIntersect(set, overlap);
            overlapping.addAll(dupes);
        }
        sortAndPrint("Overlapping", overlapping);
    }

    private static void sortAndPrint(String header, Collection<Task> collection){
        sortAndPrint(header, collection, null);
    }

    private static void sortAndPrint(String header, Collection<Task> collection, Comparator<Task> sorter){
        String lineSeparator = "_".repeat(90);
        System.out.println(lineSeparator);
        System.out.println(header);
        System.out.println(lineSeparator);

        List<Task> list = new ArrayList<>(collection);
        list.sort(sorter);
        list.forEach(System.out::println);
    }

    private static Set<Task> getUnionList(List<Set<Task>> sets){
        Set<Task> union = new HashSet<>();
        for(var taskSet : sets){
            union.addAll(taskSet);
        }
        return union;
    }

    private static Set<Task> getIntersect(Set<Task> a, Set<Task> b){
        Set<Task> intersect = new HashSet<>(a);
        intersect.retainAll(b);
        return intersect;
    }

    private static Set<Task> getDifference(Set<Task> a, Set<Task> b){
        Set<Task> result = new HashSet<>(a);
        result.removeAll(b);
        return  result;
    }
}
