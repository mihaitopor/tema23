package org.example;

import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import view.UI;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest
{


    StudentValidator studentValidator = new StudentValidator();
    TemaValidator temaValidator = new TemaValidator();
    String filenameStudent = "fisiere/Studenti.xml";
    String filenameTema = "fisiere/Teme.xml";
    String filenameNota = "fisiere/Note.xml";
    StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
    TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
    NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
    NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
    Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
//BBT
    @Test
    public void addStudent() {

        Student new_student = new Student("2133","Mihai",937,"etc@yahoo.com");

        service.addStudent(new_student);
        assertEquals(service.findStudent("2133"),new_student);

        service.deleteStudent("2133");
    }

    @Test
    public void addStudentEmptyId() {

        Student new_student = new Student("","Mihai",937,"etc@yahoo.com");


        try{
            service.addStudent(new_student);
            assert(false);
        }
        catch(Exception e) {
            assert(true);
        }
    }

    @Test
    public void addStudentUnderZeroId() {

        Student new_student = new Student("-1","Mihai",937,"etc@yahoo.com");


        try{
            service.addStudent(new_student);
            assert(false);
        }
        catch(Exception e) {
            assert(true);
        }
    }

    @Test
    public void addStudentNullId() {

        Student new_student = new Student(null,"Mihai",937,"etc@yahoo.com");


        try{
            service.addStudent(new_student);
            assert(false);
        }
        catch(Exception e) {
            assert(true);
        }
    }

    @Test
    public void addStudentUnderZeroGroup() {

        Student new_student = new Student("2133","Mihai",-4,"etc@yahoo.com");


        try{
            service.addStudent(new_student);
            assert(false);
        }
        catch(Exception e) {
            assert(true);
        }
    }

    @Test
    public void addStudentEmptyEmail() {

        Student new_student = new Student("2133","Mihai",937,"");


        try{
            service.addStudent(new_student);
            assert(false);
        }
        catch(Exception e) {
            assert(true);
        }
    }

    @Test
    public void addStudentNullEmail() {

        Student new_student = new Student("2133","Mihai",937,null);
        try{
            service.addStudent(new_student);
            assert(false);
        }
        catch(Exception e) {
            assert(true);
        }
    }

    @Test
    public void addStudentNullName() {

        Student new_student = new Student("2133",null,937,"topor@yahoo.com");


        try{
            service.addStudent(new_student);
            assert(false);
        }
        catch(Exception e) {
            assert(true);
        }
    }

    @Test
    public void addStudentEmptyName() {

        Student new_student = new Student("2133","",937,"topor@yahoo.com");


        try{
            service.addStudent(new_student);
            assert(false);
        }
        catch(Exception e) {
            assert(true);
        }
    }


    //WBT
    @Test
    public void addAssignmentValidID() {

        Tema new_tema = new Tema("1234","Test homework", 7, 5);

        service.addTema(new_tema);
        assertEquals(service.findTema("1234"),new_tema);

        service.deleteTema("1234");
    }

    @Test
    public void addAssignmentInvalidID() {

        Tema new_tema = new Tema("","Test homework", 5, 4);

        try{
            service.addTema(new_tema);
        }
        catch(Exception e) {
            assertEquals(e.getMessage(),"Numar tema invalid!");
        }
        service.deleteTema("1234");
    }

    @Test
    public void addAssignementSuccess(){
        Tema new_tema = new Tema("999","test", 10, 8);

        service.addTema(new_tema);
        assertEquals(service.findTema("999"),new_tema);

        service.deleteTema("999");
    }
    @Test
    public void addAssignementFailID(){
        Tema new_tema = new Tema("","test", 99, 98);

        try{
            service.addTema(new_tema);
        }
        catch(Exception e) {
            assertEquals(e.getMessage(),"Numar tema invalid!");
        }

    }
    @Test
    public void addAssignementFailDescription(){
        Tema new_tema = new Tema("1111","", 99, 98);

        try{
            service.addTema(new_tema);
        }
        catch(Exception e) {
            assertEquals(e.getMessage(),"Descriere invalida!");
        }

    }
    @Test
    public void addAssignementFailDeadline(){
        Tema new_tema = new Tema("1111","Test", 0, 98);

        try{
            service.addTema(new_tema);
        }
        catch(Exception e) {
            assertEquals(e.getMessage(),"Deadlineul trebuie sa fie intre 1-14.");
        }

    }
    @Test
    public void addAssignementFailDeadlineMinus(){
        Tema new_tema = new Tema("1111","Test", -100, 98);

        try{
            service.addTema(new_tema);
        }
        catch(Exception e) {
            assertEquals(e.getMessage(),"Deadlineul trebuie sa fie intre 1-14.");
        }

    }
    @Test
    public void addAssignementFailDeadlinePlus(){
        Tema new_tema = new Tema("1111","Test", 16, 15);

        try{
            service.addTema(new_tema);
        }
        catch(Exception e) {
            assertEquals(e.getMessage(),"Deadlineul trebuie sa fie intre 1-14.");
        }

    }
    @Test
    public void addAssignementFailPrimire(){
        Tema new_tema = new Tema("1111","Test", 10, 0);

        try{
            service.addTema(new_tema);
        }
        catch(Exception e) {
            assertEquals(e.getMessage(),"Saptamana primirii trebuie sa fie intre 1-14.");
        }

    }
    @Test
    public void addAssignementFailPrimireMinus(){
        Tema new_tema = new Tema("1111","Test", 10, -100);

        try{
            service.addTema(new_tema);
        }
        catch(Exception e) {
            assertEquals(e.getMessage(),"Saptamana primirii trebuie sa fie intre 1-14.");
        }

    }
    @Test
    public void addAssignementFailPrimirePlus(){
        Tema new_tema = new Tema("1111","Test", 10, 15);

        try{
            service.addTema(new_tema);
        }
        catch(Exception e) {
            assertEquals(e.getMessage(),"Saptamana primirii trebuie sa fie intre 1-14.");
        }

    }
    @Test
    public void addAssignementFailDeadlinePrimireSwitch(){
        Tema new_tema = new Tema("1111","Test", 5, 8);

        try{
            service.addTema(new_tema);
        }
        catch(Exception e) {
            assertEquals(e.getMessage(),"Primire < Deadline!");
        }

    }

    @Test
    public void addAssignmentIdString(){
        Tema new_tema = new Tema("aba","test", 10, 8);

        try{
            service.addTema(new_tema);
        }
        catch(Exception e) {
            assertEquals(e.getMessage(),"Id trebuie sa fie numar!");
        }

    }

    @Test
    public void addAssignmentIdNegativ(){
        Tema new_tema = new Tema("-100","test", 10, 8);

        try{
            service.addTema(new_tema);
        }
        catch(Exception e) {
            assertEquals(e.getMessage(),"Id trebuie sa fie pozitiv!");
        }

    }

    @Test
    public void addAssignmentIdStringNumar(){
        Tema new_tema = new Tema("10a","test", 10, 8);

        try{
            service.addTema(new_tema);
        }
        catch(Exception e) {
            assertEquals(e.getMessage(),"Id trebuie sa fie numar!");
        }

    }

    //Integration test
    @Test
    public void addStudentIntegration() {

        Student new_student = new Student("2133","Mihai",937,"etc@yahoo.com");

        service.addStudent(new_student);
        assertEquals(service.findStudent("2133"),new_student);

        service.deleteStudent("2133");
    }

    @Test
    public void addAssignmentIntegration() {

        Tema new_tema = new Tema("1234","Test homework", 7, 5);

        service.addTema(new_tema);
        assertEquals(service.findTema("1234"),new_tema);

        service.deleteTema("1234");
    }

    @Test
    public void addNotaIntegration() {

        Nota new_nota = new Nota("123","4","1",10, LocalDate.now());

        try {
            service.addNota(new_nota,"nu e bine");
            assert(false);
        }
        catch(Exception e){
            assert(true);
        }
    }

    @Test
    public void allIntegration() {
        try {
            addStudentIntegration();
            addAssignmentIntegration();
            addNotaIntegration();
            assert(true);
        }
        catch(Exception e){
            assert(false);
        }
    }

    //Integration homework
    @Test
    public void addStudentIntegrationHG() {

        Student new_student = new Student("2133","Mihai",937,"etc@yahoo.com");

        service.addStudent(new_student);
        assertEquals(service.findStudent("2133"),new_student);

        service.deleteStudent("2133");
    }

    @Test
    public void addAssignmentIntegrationHG() {
        addStudentIntegrationHG();

        Tema new_tema = new Tema("1234","Test homework", 7, 5);

        service.addTema(new_tema);
        assertEquals(service.findTema("1234"),new_tema);

        service.deleteTema("1234");
    }

    @Test
    public void addNotaIntegrationHG() {
        
        addAssignmentIntegrationHG();

        Nota new_nota = new Nota("123","4","1",10, LocalDate.now());

        try {
            service.addNota(new_nota,"nu e bine");
            assert(false);
        }
        catch(Exception e){
            assert(true);
        }
    }
}