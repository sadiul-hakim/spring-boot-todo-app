package com.todo.todoApp.controller;

import com.todo.todoApp.entity.Todo;
import com.todo.todoApp.entity.User;
import com.todo.todoApp.repository.TodoRepository;
import com.todo.todoApp.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
import com.todo.todoApp.request.AddTodoRequest;
import com.todo.todoApp.request.AddUserRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserRepository userRepository;
    private TodoRepository todoRepository;

    public UserController(UserRepository userRepository, TodoRepository todoRepository) {
        this.userRepository = userRepository;
        this.todoRepository = todoRepository;
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Long userId){
        return userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
    }

    @GetMapping("/all")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/{userId}/allTodos")
    public List<Todo> getAllTodosOfAUser(@PathVariable Long userId){
        User user=userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        return user.getTodoList();
    }

    @PostMapping
    public User addUser(@RequestBody AddUserRequest addUserRequest){
        User user=new User();
        user.setName(addUserRequest.getName());
        user.setPassword(addUserRequest.getPassword());

        return userRepository.save(user);

    }

    @PostMapping("/{userId}/todos")
    public void addTodo(@PathVariable Long userId,@RequestBody AddTodoRequest todoRequest){
        User user=userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        Todo todo=new Todo();
        todo.setContent(todoRequest.getContent());
        user.getTodoList().add(todo);
        todoRepository.save(todo);
        userRepository.save(user);

    }

    @PostMapping("todos/{todoId}")
    public void toggleTodoCompleted(@PathVariable Long todoId){
        Todo todo=todoRepository.findById(todoId).orElseThrow(NoSuchElementException::new);
        todo.setComplete(!todo.getComplete());
        todoRepository.save(todo);
    }

    @DeleteMapping("/{userId}/todos/{todoId}")
    public void deleteTodo(@PathVariable Long userId,@PathVariable Long todoId){
        User user=userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        Todo todo=todoRepository.findById(todoId).orElseThrow(NoSuchElementException::new);

       user.getTodoList().remove(todo);
       todoRepository.delete(todo);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId){
        User user=userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        userRepository.delete(user);
    }


}
