package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Book {
    private int id;
    private String title;
    private String author;
    private boolean available;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.available = true;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Book ID: " + id + "\nTitle: " + title + "\nAuthor: " + author + "\nAvailable: " + available;
    }
}

class Member {
    private int id;
    private String name;
    private List<Book> borrowedBooks;

    public Member(int id, String name) {
        this.id = id;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
        book.setAvailable(false);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
        book.setAvailable(true);
    }

    @Override
    public String toString() {
        return "Member ID: " + id + "\nName: " + name + "\nBorrowed Books:\n" + borrowedBooks;
    }
}

public class LibraryManagementApp {
    private static List<Book> books = new ArrayList<>();
    private static List<Member> members = new ArrayList<>();
    private static int bookIdCounter = 1;
    private static int memberIdCounter = 1;

    public static void main(String[] args) {

        books.add(new Book(bookIdCounter++, "To Kill a Mockingbird", "Harper Lee"));
        books.add(new Book(bookIdCounter++, "1984", "George Orwell"));
        books.add(new Book(bookIdCounter++, "The Great Gatsby", "F. Scott Fitzgerald"));
        books.add(new Book(bookIdCounter++, "Pride and Prejudice", "Jane Austen"));
        books.add(new Book(bookIdCounter++, "The Catcher in the Rye", "J.D. Salinger"));
        books.add(new Book(bookIdCounter++, "Brave New World", "Aldous Huxley"));
        books.add(new Book(bookIdCounter++, "The Hobbit", "J.R.R. Tolkien"));
        books.add(new Book(bookIdCounter++, "Moby-Dick", "Herman Melville"));
        books.add(new Book(bookIdCounter++, "War and Peace", "Leo Tolstoy"));
        books.add(new Book(bookIdCounter++, "The Odyssey", "Homer"));

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Library Management System");
            System.out.println("1. Add Book");
            System.out.println("2. List Books");
            System.out.println("3. Add Member");
            System.out.println("4. List Members");
            System.out.println("5. Borrow Book");
            System.out.println("6. Return Book");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addBook(scanner);
                    break;
                case 2:
                    listBooks();
                    break;
                case 3:
                    addMember(scanner);
                    break;
                case 4:
                    listMembers();
                    break;
                case 5:
                    borrowBook(scanner);
                    break;
                case 6:
                    returnBook(scanner);
                    break;
                case 7:
                    System.out.println("Exiting the program.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void addBook(Scanner scanner) {
        System.out.print("Enter book title: ");
        scanner.nextLine();
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();

        Book book = new Book(bookIdCounter++, title, author);
        books.add(book);
        System.out.println("Book added successfully.");
    }

    private static void listBooks() {
        System.out.println("List of Books:");
        for (Book book : books) {
            System.out.println(book);
            System.out.println("--------------------");
        }
    }

    private static void addMember(Scanner scanner) {
        System.out.print("Enter member name: ");
        scanner.nextLine();
        String name = scanner.nextLine();

        Member member = new Member(memberIdCounter++, name);
        members.add(member);
        System.out.println("Member added successfully.");
    }

    private static void listMembers() {
        System.out.println("List of Members:");
        for (Member member : members) {
            System.out.println(member);
            System.out.println("--------------------");
        }
    }

    private static void borrowBook(Scanner scanner) {
        System.out.print("Enter member ID: ");
        int memberId = scanner.nextInt();
        System.out.print("Enter book ID to borrow: ");
        int bookId = scanner.nextInt();

        Member member = findMemberById(memberId);
        Book book = findBookById(bookId);

        if (member != null && book != null) {
            if (book.isAvailable()) {
                member.borrowBook(book);
                System.out.println("Book borrowed successfully.");
            } else {
                System.out.println("Book is not available for borrowing.");
            }
        } else {
            System.out.println("Invalid member ID or book ID.");
        }
    }

    private static void returnBook(Scanner scanner) {
        System.out.print("Enter member ID: ");
        int memberId = scanner.nextInt();
        System.out.print("Enter book ID to return: ");
        int bookId = scanner.nextInt();

        Member member = findMemberById(memberId);
        Book book = findBookById(bookId);

        if (member != null && book != null) {
            if (member.getBorrowedBooks().contains(book)) {
                member.returnBook(book);
                System.out.println("Book returned successfully.");
            } else {
                System.out.println("This book is not borrowed by the member.");
            }
        } else {
            System.out.println("Invalid member ID or book ID.");
        }
    }

    private static Member findMemberById(int memberId) {
        for (Member member : members) {
            if (member.getId() == memberId) {
                return member;
            }
        }
        return null;
    }

    private static Book findBookById(int bookId) {
        for (Book book : books) {
            if (book.getId() == bookId) {
                return book;
            }
        }
        return null;
    }
}

