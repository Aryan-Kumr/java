import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class Book {
    private int id;
    private String title;
    private String author;
    private String publisher;
    private double price;

    public Book(int id, String title, String author, String publisher, double price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.price = price;
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

    public String getPublisher() {
        return publisher;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Book ID: " + id + ", Title: " + title + ", Author: " + author + ", Publisher: " + publisher + ", Price: " + price;
    }
}

public class BookDatabase {
    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1, "Book One", "Author A", "Publisher X", 20.5));
        books.add(new Book(2, "Book Two", "Author B", "Publisher Y", 15.0));
        books.add(new Book(3, "Book Three", "Author A", "Publisher Z", 30.0));
        books.add(new Book(4, "Book Four", "Author C", "Publisher X", 25.0));
        books.add(new Book(5, "Book Five", "Author B", "Publisher Y", 10.0));

        // Sort books by price in ascending order and store in another list
        List<Book> sortedBooksByPrice = new ArrayList<>(books);
        Collections.sort(sortedBooksByPrice, Comparator.comparingDouble(Book::getPrice));

        System.out.println("Books sorted by price (ascending):");
        for (Book book : sortedBooksByPrice) {
            System.out.println(book);
        }

        Scanner scanner = new Scanner(System.in);

        // Prompt for an author name and list all books by that author
        System.out.print("\nEnter an author name to list all books by that author: ");
        String authorName = scanner.nextLine();

        System.out.println("Books by " + authorName + ":");
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(authorName)) {
                System.out.println(book);
            }
        }
        // Prompt for a price and list all books with price greater than specified price
        System.out.print("\nEnter a price to list all books with price greater than that value: ");
        double price = scanner.nextDouble();

        List<Book> booksGreaterThanPrice = new ArrayList<>();
        for (Book book : books) {
            if (book.getPrice() > price) {
                booksGreaterThanPrice.add(book);
            }
        }

        System.out.println("Books with price greater than " + price + ":");
        for (Book book : booksGreaterThanPrice) {
            System.out.println(book);
        }
        scanner.close();
    }
}
