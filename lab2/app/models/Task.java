package models;

import java.util.*;

import play.db.ebean.*;
import play.data.validation.Constraints.*;

import javax.persistence.*;

@Entity
public class Task extends Model{
    
  @Id
  public Long id;
  
  @Required
  public String label;
  
  @Required
  public String description;
  
  @Required
  public int priority;
  
  public boolean done;
  
  public static Finder<Long,Task> find = new Finder(
	Long.class, Task.class);
  
  public static List<Task> all() {
	  List<Task> bdList = find.all();
	  Comparator<Task> comparador = new Comparator<Task>() {
			@Override
			public int compare(Task arg0, Task arg1) {
				return arg0.getPriority() - arg1.getPriority();
			}
	  };
	  Collections.sort(bdList, comparador);
	  return bdList;
  }

  public static void create(Task task) {
	  task.save();
  }

  public static void delete(Long id) {
	  find.ref(id).delete();
  }   
  
  public static void updateStatus(Long id) {
	 Task task = (Task) find.ref(id);
	 task.setDone(! task.isDone());
	 task.update();
  }
  
  public Long getId() {
	  return id;
  }
  
  public void setId(Long newId) {
	  id = newId;
  }
  
  public String getLabel() {
	  return label;
  }
  
  public void setLabel(String newLabel) {
	  label = newLabel;
  }
  
  public String getDescription() {
	  return description;
  }
  
  public void setDescription(String newDescription) {
	  description = newDescription;
  }
  
  public int getPriority() {
	  return priority;
  }
  
  public void setPriority(int newPriority) {
	  priority = newPriority;
  }
  
  public boolean isDone() {
	  return done;
  }
  
  public void setDone(boolean d) {
	  done = d;
  }
}
