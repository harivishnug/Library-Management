import java.util.*;

class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = true; 
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public boolean isAvailable() {
        return isAvailable;
    }
    public void setAvailable(boolean available) {
        isAvailable = available;
    }
    public void displayBookInfo() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("ISBN: " + isbn);
        System.out.println("Available: " + (isAvailable ? "Yes" : "No"));
        System.out.println("-------------------------");
    }
}

public class Main {
    private static List<Book> books = new ArrayList<>();

    public static void addBook(String title, String author, String isbn) {
        Book book = new Book(title, author, isbn);
        books.add(book);
        System.out.println("Book added successfully.");
    }

    public static void updateBook(String isbn, String newTitle, String newAuthor) {
        Book book = findBookByIsbn(isbn);
        if (book != null) {
            book.setTitle(newTitle);
            book.setAuthor(newAuthor);
            System.out.println("Book details updated successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }

    public static void deleteBook(String isbn) {
        Book book = findBookByIsbn(isbn);
        if (book != null) {
            books.remove(book);
            System.out.println("Book deleted successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }

    public static void checkOutBook(String isbn) {
        Book book = findBookByIsbn(isbn);
        if (book != null && book.isAvailable()) {
            book.setAvailable(false);
            System.out.println("Book checked out successfully.");
        } else {
            System.out.println("Book is either not available or doesn't exist.");
        }
    }

    public static void returnBook(String isbn) {
        Book book = findBookByIsbn(isbn);
        if (book != null && !book.isAvailable()) {
            book.setAvailable(true);
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("Book is not checked out or doesn't exist.");
        }
    }

    private static Book findBookByIsbn(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }

    public static void listAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            for (Book book : books) {
                book.displayBookInfo();
            }
        }
    }

    public static void searchBooks(String query) {
        boolean found = false;
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                    book.getAuthor().toLowerCase().contains(query.toLowerCase())) {
                book.displayBookInfo();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No books found.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        while (true) {
            System.out.println("Welcome to the Library Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Update Book");
            System.out.println("3. Delete Book");
            System.out.println("4. List All Books");
            System.out.println("5. Search Books");
            System.out.println("6. Check Out Book");
            System.out.println("7. Return Book");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter book author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter book ISBN: ");
                    String isbn = scanner.nextLine();
                    addBook(title, author, isbn);
                    break;

                case 2:
                    System.out.print("Enter book ISBN to update: ");
                    String updateIsbn = scanner.nextLine();
                    System.out.print("Enter new book title: ");
                    String newTitle = scanner.nextLine();
                    System.out.print("Enter new book author: ");
                    String newAuthor = scanner.nextLine();
                    updateBook(updateIsbn, newTitle, newAuthor);
                    break;

                case 3:
                    System.out.print("Enter book ISBN to delete: ");
                    String deleteIsbn = scanner.nextLine();
                    deleteBook(deleteIsbn);
                    break;

                case 4:
                    listAllBooks();
                    break;

                case 5:
                    System.out.print("Enter title or author to search: ");
                    String query = scanner.nextLine();
                    searchBooks(query);
                    break;

                case 6:
                    System.out.print("Enter book ISBN to check out: ");
                    String checkOutIsbn = scanner.nextLine();
                    checkOutBook(checkOutIsbn);
                    break;

                case 7:
                    System.out.print("Enter book ISBN to return: ");
                    String returnIsbn = scanner.nextLine();
                    returnBook(returnIsbn);
                    break;

                case 8:
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
